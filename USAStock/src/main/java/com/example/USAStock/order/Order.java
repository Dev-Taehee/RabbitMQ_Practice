package com.example.USAStock.order;

import lombok.Data;

@Data
public class Order {
    private int id;
    private int stockId;
    private int price;
    private int amount;
    private String type;
}
