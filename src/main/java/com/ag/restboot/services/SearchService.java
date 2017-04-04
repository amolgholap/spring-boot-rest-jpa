package com.ag.restboot.services;

import com.ag.restboot.bean.SearchParam;
import com.ag.restboot.bean.User;

public interface SearchService {

	String getUserDetails(SearchParam searchParam);
	User getUser(Long id, String name);
}