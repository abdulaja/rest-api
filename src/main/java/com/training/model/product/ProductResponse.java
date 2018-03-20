package com.training.model.product;

import com.training.model.common.AbstractResponse;

import java.io.Serializable;
import java.util.List;

public class ProductResponse implements Serializable {
    private AbstractResponse abstractResponse;
    private List<Product> products;

    public AbstractResponse getAbstractResponse() {
        return abstractResponse;
    }

    public void setAbstractResponse(AbstractResponse abstractResponse) {
        this.abstractResponse = abstractResponse;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
