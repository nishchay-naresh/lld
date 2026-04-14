package com.nishchay.oops.tesco.ruleengine.pojo;

import java.util.List;

public class RuleEngine {

    private final List<Rule> rules;

    public RuleEngine(List<Rule> rules) {
        this.rules = rules;
    }

    public String evaluate(List<Item> items) {
        for (Rule rule : rules) {
            if (!rule.validate(items)) {
                return "BREACHED";
            }
        }
        return "MET";
    }
}