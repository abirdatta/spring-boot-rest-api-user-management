package com.ad.platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ad.platform.model.UserType;

@Repository
public interface UserTypeRepository extends CrudRepository<UserType, Long>{

    
}
