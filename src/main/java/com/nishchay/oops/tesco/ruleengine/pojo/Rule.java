package com.nishchay.oops.tesco.ruleengine.pojo;

import java.util.List;

public interface Rule {
    boolean validate(List<Item> items);
    String getName();
}