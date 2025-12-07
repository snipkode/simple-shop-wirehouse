package com.wirehouse.simpleshop.helpers;

import com.wirehouse.simpleshop.dto.ItemResponse;
import com.wirehouse.simpleshop.dto.StockResponse;
import com.wirehouse.simpleshop.model.*;

import lombok.experimental.UtilityClass;

@UtilityClass
public class WarehouseHelper {

    public static ItemResponse toItemResponse(Item item) {
        ItemResponse response = new ItemResponse();
        response.setId(item.getId());
        response.setName(item.getName());
        response.setDescription(item.getDescription());
        response.setCategory(item.getCategory());
        response.setActive(item.getActive());
        response.setVariants(item.getVariants());
        return response;
    }

    public static StockResponse toStockResponse(Stock stock) {
        StockResponse response = new StockResponse();
        response.setId(stock.getId());
        response.setVariantId(stock.getVariant().getId());
        response.setQuantity(stock.getQuantity());
        response.setReserved(stock.getReserved());
        return response;
    }
}
