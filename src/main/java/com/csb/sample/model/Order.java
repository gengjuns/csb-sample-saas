package com.csb.sample.model;

import java.util.List;

public class Order {

    private String planCode;
    private String freeTrial;
    private String frequency;
    private Integer duration;

    private List<OrderItem> items;

    private List<String> modules;

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public List<String> getModules() {
        return modules;
    }

    public void setModules(List<String> modules) {
        this.modules = modules;
    }

    public String getFreeTrial() {
        return freeTrial;
    }

    public void setFreeTrial(String freeTrial) {
        this.freeTrial = freeTrial;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}
