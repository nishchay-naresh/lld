package com.nishchay.oops.tesco.ruleengine.pojo;

import java.util.List;

public class MaxQuantityPerProductRule implements Rule {

    private final int maxLimit;

    public MaxQuantityPerProductRule(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    @Override
    public boolean validate(List<Item> items) {
        for (Item item : items) {
            if (item.quantity() > maxLimit) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getName() {
        return "MaxQuantityPerProductRule";
    }
}