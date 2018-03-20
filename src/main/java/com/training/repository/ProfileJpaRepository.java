package com.training.repository;

import com.training.model.profile.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfileJpaRepository extends PagingAndSortingRepository<Profile, Integer> {

    @Query(value = "select * from profile where username = ?1 and password = ?2", nativeQuery = true)
    Profile findByParams(String username, String password);

    Profile findById(Integer id);

    Profile findByUsername(String username);

}
