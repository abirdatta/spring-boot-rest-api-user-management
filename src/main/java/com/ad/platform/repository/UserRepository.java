package com.ad.platform.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ad.platform.model.User;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    List<User> removeByFirstName(String firstName);
}
