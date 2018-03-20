package com.training.model.product;

import java.io.Serializable;

public class ProductRequest implements Serializable {
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
