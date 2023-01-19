package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(String productCode, String productName, int quantity) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);

        items.add(basketItem);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void consolidateItems() {
        List<BasketItem> consolidatedItems = new ArrayList<>();

        for (BasketItem i : items) {
            boolean isDuplicate = false;
            for (BasketItem item : consolidatedItems) {
                if (i.getProductCode().equals(item.getProductCode())) {
                    item.setQuantity(item.getQuantity() + i.getQuantity());
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate == false)
                consolidatedItems.add(i);
        }
        items = consolidatedItems;
    }
}
