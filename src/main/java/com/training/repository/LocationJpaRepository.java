package com.training.repository;

import com.training.model.profile.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LocationJpaRepository extends PagingAndSortingRepository<Location, Integer> {

    List<Location> findByProfileId(Integer profileId);

}
