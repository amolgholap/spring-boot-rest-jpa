package com.ag.restboot.dao;

import org.springframework.data.repository.Repository;

import com.ag.restboot.bean.User;

public interface UserRepository extends Repository<User, Long> {

	/*Page<City> findAll(Pageable pageable);*/

	/*Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name,
			String country, Pageable pageable);*/

	User findByIdAndNameAllIgnoringCase(Long id, String name);

}
