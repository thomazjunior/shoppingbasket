package com.interview.shoppingbasket;

import lombok.Data;

@Data
public class Promotion {
    private String productCode;
    private boolean percentage;
    private String type;
    private double value;

}