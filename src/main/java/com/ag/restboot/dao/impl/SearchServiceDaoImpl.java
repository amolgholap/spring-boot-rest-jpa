package com.ag.restboot.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.ag.restboot.bean.Employee;
import com.ag.restboot.bean.SearchParam;
import com.ag.restboot.dao.SearchServiceDao;

@Component
public class SearchServiceDaoImpl implements SearchServiceDao{

	private static final Logger logger = LoggerFactory
			.getLogger(SearchServiceDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${db.firstdbcheck.user.selectall}")
    private String allUser;
	

	@Override
	public String getUserDetailsUsingProcedure(SearchParam searchParam) {
		logger.info("ENTER:SearchServiceDaoImpl:getUserDetails");
		String result = "";
		String query = "SELECT public.showusers(?)";
		Connection conn=null;
		ResultSet rs =null;
		ResultSet rs1 = null;
		try {

			/*List<Map<String, Object>> moreInfoList = jdbcTemplate
					.queryForList(allUser);*/
			conn =jdbcTemplate.getDataSource().getConnection();
			
			conn.setAutoCommit(false);

			CallableStatement cs = conn.prepareCall(query);
			
			cs.setString(1, "Amol");
			
			rs = cs.executeQuery();
			
			if(rs.next()){
				rs1=(ResultSet)rs.getObject(1);
				if(rs1.next()){
					result="Result from procedure : "+rs1.getString("name");
				}
			}


		} catch (Exception ex) {
			logger.error(
					"Error in SearchServiceDaoImpl : getUserDetails method... : "
							+ ex.getLocalizedMessage(), ex);
		}finally{
			try {
				rs1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.info("EXIT:SearchServiceDaoImpl:getUserDetails");
		return result;
	}


	@Override
	public List<Employee> getEmployees() {
		try{
		return jdbcTemplate.query("select * from employee",new ResultSetExtractor<List<Employee>>(){  
		    @Override  
		     public List<Employee> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  
		      
		        List<Employee> list=new ArrayList<Employee>();  
		        while(rs.next()){  
		        Employee e=new Employee();  
		        e.setId(rs.getInt("id"));  
		        e.setName(rs.getString("name"));  
		        e.setSalary(rs.getInt("salary"));  
		        list.add(e);  
		        }  
		        return list;  
		        }  
		    });
		}catch(Exception e){}
		return null;
	}


	@Override
	public String addEmployees(Employee employee) {
		jdbcTemplate.update("INSERT INTO public.employee(name, salary) VALUES (?,?)", employee.getName(),employee.getSalary());
		return "successfull added";
	}
}