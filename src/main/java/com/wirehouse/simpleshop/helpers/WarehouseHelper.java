package com.wirehouse.simpleshop.helpers;

import com.wirehouse.simpleshop.dto.ItemResponse;
import com.wirehouse.simpleshop.dto.StockResponse;
import com.wirehouse.simpleshop.dto.VariantResponse;
import com.wirehouse.simpleshop.model.*;
import com.wirehouse.simpleshop.model.Variant;
import com.wirehouse.simpleshop.repository.StockRepository;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WarehouseHelper {

    private static StockRepository stockRepository;

    public ItemResponse toItemResponse(Item item) {
        ItemResponse response = new ItemResponse();
        response.setId(item.getId());
        response.setName(item.getName());
        response.setDescription(item.getDescription());
        response.setCategory(item.getCategory());
        response.setActive(item.getActive());
        response.setVariants(item.getVariants());
        return response;
    }

    public VariantResponse toVariantResponse(Variant variant) {
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

    public StockResponse toStockResponse(Stock stock) {
        StockResponse response = new StockResponse();
        response.setId(stock.getId());
        response.setVariantId(stock.getVariant().getId());
        response.setQuantity(stock.getQuantity());
        response.setReserved(stock.getReserved());
        return response;
    }
}
