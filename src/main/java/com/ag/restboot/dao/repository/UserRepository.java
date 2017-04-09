package com.ag.restboot.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.ag.restboot.bean.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Page<User> findAll(Pageable pageable);

	/*Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name,
			String country, Pageable pageable);*/

	User findByNameAllIgnoringCase(String name);

}
