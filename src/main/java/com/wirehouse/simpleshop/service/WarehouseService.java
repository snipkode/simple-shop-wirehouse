package com.wirehouse.simpleshop.service;

import com.wirehouse.simpleshop.dto.*;
import com.wirehouse.simpleshop.model.Item;
import com.wirehouse.simpleshop.model.Variant;
import com.wirehouse.simpleshop.model.Stock;
import com.wirehouse.simpleshop.repository.ItemRepository;
import com.wirehouse.simpleshop.repository.VariantRepository;
import com.wirehouse.simpleshop.repository.StockRepository;
import com.wirehouse.simpleshop.exception.ItemNotFoundException;
import com.wirehouse.simpleshop.helpers.WarehouseHelper;
import com.wirehouse.simpleshop.exception.InsufficientStockException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service buat handle logic warehouse
 * Termasuk CRUD item, variant, dan manajemen stock
 */

@Service
@RequiredArgsConstructor
public class WarehouseService {
    
    final ItemRepository itemRepository;
    final VariantRepository variantRepository;
    final StockRepository stockRepository;

    /**
     * Bikin item baru + variants + stock
     * @param itemRequest data item yang mau dibikin
     * @return ItemResponse item yang udah dibikin
     */
    @Transactional
    public ItemResponse createItem(ItemRequest itemRequest) {
        // Bikin itemnya
        Item item = Item.builder()
                .name(itemRequest.getName())
                .description(itemRequest.getDescription())
                .category(itemRequest.getCategory())
                .active(itemRequest.getActive() != null ? itemRequest.getActive() : true)
                .build();

        item = itemRepository.save(item);

        // Bikin variants kalo ada
        if (itemRequest.getVariants() != null) {
            for (VariantRequest variantRequest : itemRequest.getVariants()) {
                Variant variant = Variant.builder()
                        .item(item)  // Hubungin variant ke item
                        .name(variantRequest.getName())
                        .price(variantRequest.getPrice())
                        .active(variantRequest.getActive() != null ? variantRequest.getActive() : true)
                        .build();

                variant = variantRepository.save(variant);

                // Bikin stock buat variant ini
                if (variantRequest.getInitialStock() != null) {
                    Stock stock = Stock.builder()
                            .variant(variant)  // Hubungin stock ke variant
                            .quantity(variantRequest.getInitialStock())
                            .reserved(false)
                            .build();

                    stockRepository.save(stock);
                }
            }
        }

        // Ambil lagi item + variants-nya (buat nampilin di response yang lengkap)
        item = itemRepository.findByIdWithVariants(item.getId()).orElseThrow();
        return WarehouseHelper.toItemResponse(item);
    }

    /**
     * Get semua item yang aktif
     * @return List<ItemResponse> semua item yang aktif
     */
    public List<ItemResponse> getAllItems() {
        return itemRepository.findAllActiveWithVariants().stream()
                .map(WarehouseHelper::toItemResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get item berdasarkan ID
     * @param id ID item yang dicari
     * @return ItemResponse item yang ketemu
     * @throws ItemNotFoundException kalo item gak ketemu
     */
    public ItemResponse getItemById(Long id) {
        Item item = itemRepository.findByIdWithVariants(id).orElseThrow(() ->
                new ItemNotFoundException("Item gak ketemu id: " + id));
        return WarehouseHelper.toItemResponse(item);
    }

    @Transactional
    public ItemResponse updateItem(Long id, ItemRequest itemRequest) {
        Item item = itemRepository.findByIdWithVariants(id).orElseThrow(() ->
                new ItemNotFoundException("Item not found with id: " + id));

        item.setName(itemRequest.getName());
        item.setDescription(itemRequest.getDescription());
        item.setCategory(itemRequest.getCategory());
        item.setActive(itemRequest.getActive() != null ? itemRequest.getActive() : true);

        item = itemRepository.save(item);

        // Update variants if provided
        if (itemRequest.getVariants() != null) {
            // Delete existing variants first
            List<Variant> existingVariants = variantRepository.findByItemIdAndActiveTrue(id);
            for (Variant existingVariant : existingVariants) {
                existingVariant.setActive(false); // Soft delete
                variantRepository.save(existingVariant);
            }

            // Create new variants
            for (VariantRequest variantRequest : itemRequest.getVariants()) {
                Variant variant = Variant.builder()
                        .item(item)
                        .name(variantRequest.getName())
                        .price(variantRequest.getPrice())
                        .active(variantRequest.getActive() != null ? variantRequest.getActive() : true)
                        .build();

                variant = variantRepository.save(variant);

                // Create or update stock for the variant
                if (variantRequest.getInitialStock() != null) {
                    Stock existingStock = stockRepository.findByVariantId(variant.getId());
                    if (existingStock != null) {
                        existingStock.setQuantity(variantRequest.getInitialStock());
                        stockRepository.save(existingStock);
                    } else {
                        Stock stock = Stock.builder()
                                .variant(variant)
                                .quantity(variantRequest.getInitialStock())
                                .reserved(false)
                                .build();

                        stockRepository.save(stock);
                    }
                }
            }
        }

        // Fetch the updated item with its variants
        item = itemRepository.findByIdWithVariants(item.getId()).orElseThrow();
        return WarehouseHelper.toItemResponse(item);
    }

    @Transactional
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException("Item not found with id: " + id));

        // Soft delete the item
        item.setActive(false);
        itemRepository.save(item);
    }

    @Transactional
    public VariantResponse createVariantForItem(Long itemId, VariantRequest variantRequest) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new ItemNotFoundException("Item not found with id: " + itemId));

        Variant variant = Variant.builder()
                .item(item)
                .name(variantRequest.getName())
                .price(variantRequest.getPrice())
                .active(variantRequest.getActive() != null ? variantRequest.getActive() : true)
                .build();

        variant = variantRepository.save(variant);

        // Create stock for the variant
        if (variantRequest.getInitialStock() != null) {
            Stock stock = Stock.builder()
                    .variant(variant)
                    .quantity(variantRequest.getInitialStock())
                    .reserved(false)
                    .build();

            stockRepository.save(stock);
        }

        return toVariantResponse(variant);
    }

    public List<VariantResponse> getVariantsByItem(Long itemId) {
        List<Variant> variants = variantRepository.findByItemIdAndActiveTrue(itemId);
        return variants.stream()
                .map(this::toVariantResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public StockResponse updateStock(Long variantId, StockRequest stockRequest) {
        Stock stock = stockRepository.findByVariantId(variantId);
        if (stock == null) {
            // Create new stock if it doesn't exist
            Variant variant = variantRepository.findById(variantId).orElseThrow(() ->
                    new ItemNotFoundException("Variant not found with id: " + variantId));
            
            stock = Stock.builder()
                    .variant(variant)
                    .quantity(stockRequest.getQuantity())
                    .reserved(false)
                    .build();
        } else {
            stock.setQuantity(stockRequest.getQuantity());
        }

        stock = stockRepository.save(stock);
        return WarehouseHelper.toStockResponse(stock);
    }

    public StockResponse getStockByVariant(Long variantId) {
        Stock stock = stockRepository.findByVariantId(variantId);
        if (stock == null) {
            throw new ItemNotFoundException("Stock not found for variant id: " + variantId);
        }
        return WarehouseHelper.toStockResponse(stock);
    }

    /**
     * Reservasi stock buat dicegah dijual ke orang lain
     * Ini buat gak ada race condition pas checkout
     * @param variantId ID variant yang mau di-reserve
     * @param quantity jumlah yang mau di-reserve
     * @return boolean true kalo berhasil, false kalo gagal
     * @throws IllegalArgumentException kalo quantity <= 0
     * @throws ItemNotFoundException kalo variant gak ketemu
     * @throws InsufficientStockException kalo stock kurang
     */
    @Transactional
    public boolean reserveStock(Long variantId, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Stock stock = stockRepository.findByVariantId(variantId);
        if (stock == null) {
            throw new ItemNotFoundException("Stock gak ketemu buat variant id: " + variantId);
        }

        if (stock.getQuantity() >= quantity) {
            stock.setQuantity(stock.getQuantity() - quantity);
            stock.setReserved(true);
            stockRepository.save(stock);
            return true;
        }

        throw new InsufficientStockException("Stock gak cukup. Diminta: " + quantity + ", Tersedia: " + stock.getQuantity());
    }

    @Transactional
    public boolean updateStockQuantity(Long variantId, Integer quantityChange) {
        Stock stock = stockRepository.findByVariantId(variantId);
        if (stock == null) {
            throw new ItemNotFoundException("Stock not found for variant id: " + variantId);
        }

        Integer newQuantity = stock.getQuantity() + quantityChange;
        if (newQuantity < 0) {
            throw new InsufficientStockException("Not enough stock available. Requested to reduce by: " + Math.abs(quantityChange) +
                ", but only have: " + stock.getQuantity());
        }

        stock.setQuantity(newQuantity);
        stockRepository.save(stock);
        return true;
    }

    @Transactional
    public VariantResponse updateVariantPrice(Long variantId, UpdatePriceRequest updatePriceRequest){
        Variant variant = variantRepository.findById(variantId).orElseThrow(() -> 
            new ItemNotFoundException("Variant tidak ditemukan dengan id :" + variantId));
        variant.setPrice(updatePriceRequest.getPrice());
        variant = variantRepository.save(variant);
        return toVariantResponse(variant);
    }

    private VariantResponse toVariantResponse(Variant variant) {
        VariantResponse response = new VariantResponse();
        response.setId(variant.getId());
        response.setName(variant.getName());
        response.setPrice(variant.getPrice());
        response.setActive(variant.getActive());
        
        Stock stock = stockRepository.findByVariantId(variant.getId());
        if (stock != null) {
            response.setStock(stock);
        }
        
        return response;
    }
}