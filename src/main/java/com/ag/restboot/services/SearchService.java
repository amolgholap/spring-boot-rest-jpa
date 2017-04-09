package com.ag.restboot.services;

import java.util.List;

import com.ag.restboot.bean.SearchParam;
import com.ag.restboot.bean.User;
import com.ag.restboot.model.BookCategory;

public interface SearchService {

	String getUserDetails(SearchParam searchParam);
	User getUser(Long id, String name);
	String addUser(String name);
	String addBooks();
	List<BookCategory> getBooks(String name);
	
}