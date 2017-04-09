package com.ag.restboot.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ag.restboot.model.BookCategory;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer>{
}
