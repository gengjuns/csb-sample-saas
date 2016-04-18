package com.csb.sample.model;

import java.util.List;

public class SaasRequest {

    private String type;

    private String action;

    private Creator creator;

    private Company company;

    private Order order;
    
    private User user;

    private Account account;
    
    private List<String> modules;

    private List<CustomeAttribute> customeAttributes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<String> getModules() {
        return modules;
    }

    public void setModules(List<String> modules) {
        this.modules = modules;
    }

    public List<CustomeAttribute> getCustomeAttributes() {
        return customeAttributes;
    }

    public void setCustomeAttributes(List<CustomeAttribute> customeAttributes) {
        this.customeAttributes = customeAttributes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
