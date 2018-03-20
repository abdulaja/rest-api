package com.training.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.common.ResponseCode;
import com.training.model.common.AbstractResponse;
import com.training.model.product.Product;
import com.training.model.product.ProductResponse;
import com.training.repository.ProductJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
public class ProductService {
    @Autowired
    private ProductJpaRepository productJpaRepository;
    private Logger log = LoggerFactory.getLogger(getClass());

    public List<Product> getByName(String name) throws JsonProcessingException {
        log.info("\"{}\" | ".concat("input").concat(" ({}) : {}"), "getByName", "String", name);
        List<Product> products = productJpaRepository.findByName(name);
        log.info("\"{}\" | ".concat("output").concat(" ({}) : {}"), "getByName", "List<product>",
                new ObjectMapper().writeValueAsString(products));
        return products;
    }

    public List<Product> getByCategory(String category) throws JsonProcessingException {
        log.info("\"{}\" | ".concat("input").concat(" ({}) : {}"), "getByCategory", "String", category);
        List<Product> products = productJpaRepository.findByCategory(category);
        log.info("\"{}\" | ".concat("output").concat(" ({}) : {}"), "getByCategory", "List<product>",
                new ObjectMapper().writeValueAsString(products));
        return products;
    }

    public List<Product> getByType(String type) throws JsonProcessingException {
        log.info("\"{}\" | ".concat("input").concat(" ({}) : {}"), "getByType", "String", type);
        List<Product> products = productJpaRepository.findByType(type);
        log.info("\"{}\" | ".concat("output").concat(" ({}) : {}"), "getByType", "List<product>",
                new ObjectMapper().writeValueAsString(products));
        return products;
    }

    public List<Product> getByStock(String stock) throws JsonProcessingException {
        List<Product> products = new ArrayList<>();
        if (!Objects.isNull(stock) && stock != "") {
            log.info("\"{}\" | ".concat("input").concat(" ({}) : {}"), "getByStock", "Long", stock);
            products = productJpaRepository.findByStock(Long.valueOf(stock));
            log.info("\"{}\" | ".concat("output").concat(" ({}) : {}"), "getByStock", "List<product>",
                    new ObjectMapper().writeValueAsString(products));
        }
        return products;
    }

    public ProductResponse getByParam(Product product) throws JsonProcessingException {
        ProductResponse response = new ProductResponse();
        AbstractResponse abstractResponse = new AbstractResponse();
        log.info("\"{}\" | ".concat("input").concat(" ({}) : {}"), "getByParam", "Product", product.toString());
        List<Product> products = new ArrayList<>();
        if (!Objects.isNull(product.getName())) {
            products = productJpaRepository.findByName(product.getName().toLowerCase());
            log.info("\"{}\" | ".concat("output").concat(" ({}) : {}"), "getByParam", "List<product>",
                    new ObjectMapper().writeValueAsString(products));
        } else if (!Objects.isNull(product.getCategory())) {
            products = productJpaRepository.findByCategory(product.getCategory().toLowerCase());
            log.info("\"{}\" | ".concat("output").concat(" ({}) : {}"), "getByParam", "List<product>",
                    new ObjectMapper().writeValueAsString(products));
        } else if (!Objects.isNull(product.getType())) {
            products = productJpaRepository.findByType(product.getType().toLowerCase());
            log.info("\"{}\" | ".concat("output").concat(" ({}) : {}"), "getByParam", "List<product>",
                    new ObjectMapper().writeValueAsString(products));
        } else if (!Objects.isNull(product.getStock())) {
            products = productJpaRepository.findByStock(product.getStock());
            log.info("\"{}\" | ".concat("output").concat(" ({}) : {}"), "getByParam", "List<product>",
                    new ObjectMapper().writeValueAsString(products));
        } else {
            products = productJpaRepository.findByParam("");
            log.info("\"{}\" | ".concat("output").concat(" ({}) : {}"), "getByParam", "List<product>",
                    new ObjectMapper().writeValueAsString(products));
        }

        abstractResponse.setResponseStatus(ResponseCode.SUCCESS.getCode());
        abstractResponse.setResponseMessage(ResponseCode.SUCCESS.getMessage());

        response.setAbstractResponse(abstractResponse);
        response.setProducts(products);

        return response;
    }


    public void saveDataCustomer(Product p_ObjectData) {

        productJpaRepository.save(p_ObjectData);
        log.info("\"{}\" | ".concat("input").concat(" ({}) : {}"), "saveDataProduct", "String", p_ObjectData);
    }

    public boolean isIDExist(Long long1) {
        List<Product> exist = productJpaRepository.findById((long) long1);
        return exist.size() != 0 ? false : true;
    }

    public ProductResponse createDataByHash(Product product, boolean isEdit) throws ParseException {
        ProductResponse response = new ProductResponse();
        AbstractResponse abstractResponse = new AbstractResponse();
        List<Product> products = new ArrayList<>();
        if (isEdit && Objects.isNull(product.getId())) {
            products.add(product);

            abstractResponse.setResponseStatus(ResponseCode.PRODUCT_ID_EMPTY.getCode());
            abstractResponse.setResponseMessage(ResponseCode.PRODUCT_ID_EMPTY.getMessage());
            response.setAbstractResponse(abstractResponse);
            response.setProducts(products);

            return response;
        }

        if (!Objects.isNull(product.getStock())) {
            product = productJpaRepository.save(product);
            if (isEdit) {
                abstractResponse.setResponseStatus(ResponseCode.EDIT_PRODUCT_SUCCESS.getCode());
                abstractResponse.setResponseMessage(ResponseCode.EDIT_PRODUCT_SUCCESS.getMessage());
            } else {
                abstractResponse.setResponseStatus(ResponseCode.ADD_PRODUCT_SUCCESS.getCode());
                abstractResponse.setResponseMessage(ResponseCode.ADD_PRODUCT_SUCCESS.getMessage());
            }
        } else {
            abstractResponse.setResponseStatus(ResponseCode.STOCK_EMPTY.getCode());
            abstractResponse.setResponseMessage(ResponseCode.STOCK_EMPTY.getMessage());
        }
        products.add(product);

        response.setAbstractResponse(abstractResponse);
        response.setProducts(products);

        return response;
    }
}
