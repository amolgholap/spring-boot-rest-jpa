package com.ag.restboot.dao;

import com.ag.restboot.bean.SearchParam;

public interface SearchServiceDao {
	String getUserDetailsUsingProcedure(SearchParam searchParam);
}