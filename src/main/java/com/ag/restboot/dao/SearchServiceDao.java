package com.ag.restboot.dao;

import java.util.List;

import com.ag.restboot.bean.Employee;
import com.ag.restboot.bean.SearchParam;

public interface SearchServiceDao {
	String getUserDetailsUsingProcedure(SearchParam searchParam);

	List<Employee> getEmployees();

	String addEmployees(Employee employee);
}