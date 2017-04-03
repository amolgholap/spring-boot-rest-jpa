package com.ag.restboot.dao;

import com.ag.restboot.bean.firstdbcheck.SearchParam;

public interface SearchServiceDao {
	String getUserDetailsUsingProcedure(SearchParam searchParam);
}
