package com.nishchay.oops.tesco.ruleengine.pojo;

import java.util.List;

public class CategoryQuantityRule implements Rule {

    private final String category;
    private final int maxLimit;

    public CategoryQuantityRule(String category, int maxLimit) {
        this.category = category;
        this.maxLimit = maxLimit;
    }

    @Override
    public boolean validate(List<Item> items) {
        int total = 0;

        for (Item item : items) {
            if (item.category().equalsIgnoreCase(category)) {
                total += item.quantity();
            }
        }

        return total <= maxLimit;
    }

    @Override
    public String getName() {
        return "CategoryQuantityRule";
    }
}