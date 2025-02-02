package com.example.inventory.dto;

import lombok.Data;

@Data
public class ItemDto {
    private Long itemId;
    private String name;
    private double price;
    private int quantity;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
