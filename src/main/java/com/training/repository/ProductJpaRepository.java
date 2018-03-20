package com.training.repository;

import com.training.model.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductJpaRepository extends PagingAndSortingRepository<Product, Integer> {
    @Query(value = "select * from product where lower(product_name) like %?1%", nativeQuery = true)
    List<Product> findByParam(String name);

    @Query(value = "select * from product where lower(product_name) like %?1%", nativeQuery = true)
    List<Product> findByName(String name);

    @Query(value = "select * from product where lower(product_category) like %?1%", nativeQuery = true)
    List<Product> findByCategory(String category);

    @Query(value = "select * from product where lower(product_type) like %?1%", nativeQuery = true)
    List<Product> findByType(String type);

    List<Product> findByStock(Long stock);

    List<Product> findById(Long id);

    Product deleteProductById(Long id);
}
