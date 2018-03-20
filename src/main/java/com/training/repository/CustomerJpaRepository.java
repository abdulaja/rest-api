package com.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.training.model.Customer;

public interface CustomerJpaRepository extends PagingAndSortingRepository<Customer, Integer> {

	@Query(value = "select * from customer where lower(firstname) like %?1%", nativeQuery = true)
	List<Customer> findByParam(String firstname);

	List<Customer> findByFirstname(String firstname);

	List<Customer> findByLastname(String lastname);
	
	List<Customer> findById(Long id);

}
