package com.training.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.training.model.product.Product;
import com.training.model.product.ProductResponse;
import com.training.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/getByName", method = RequestMethod.POST)
    private List<Product> getByName(@RequestBody HashMap<String, String> param) throws JsonProcessingException {
        return productService.getByName(param.get("name").toString());
    }

    @RequestMapping(value = "/getByCategory", method = RequestMethod.POST)
    private List<Product> getByCategory(@RequestBody HashMap<String, String> param) throws JsonProcessingException {
        return productService.getByCategory(param.get("category").toString());
    }

    @RequestMapping(value = "/getByType", method = RequestMethod.POST)
    private List<Product> getByType(@RequestBody HashMap<String, String> param) throws JsonProcessingException {
        return productService.getByType(param.get("type").toString());
    }

    @RequestMapping(value = "/getByStock", method = RequestMethod.POST)
    private List<Product> getByStock(@RequestBody HashMap<String, String> param) throws JsonProcessingException {
        return productService.getByStock(param.get("stock").toString());
    }

    @RequestMapping(value = "/getByParam", method = RequestMethod.POST)
    private ProductResponse getByParam(@RequestBody Product product) throws JsonProcessingException {
        return productService.getByParam(product);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ProductResponse createByHash(@RequestBody Product product) throws ParseException {
        return productService.createDataByHash(product, false);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ProductResponse updateByHash(@RequestBody Product product) throws ParseException {
        return productService.createDataByHash(product, true);
    }
}
