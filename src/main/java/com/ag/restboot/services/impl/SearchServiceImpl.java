package com.ag.restboot.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ag.restboot.bean.SearchParam;
import com.ag.restboot.bean.User;
import com.ag.restboot.dao.repository.UserRepository;
import com.ag.restboot.dao.SearchServiceDao;
import com.ag.restboot.dao.impl.SearchServiceDaoImpl;
import com.ag.restboot.dao.repository.BookCategoryRepository;
import com.ag.restboot.model.Book;
import com.ag.restboot.model.BookCategory;
import com.ag.restboot.services.SearchService;

@Component
@Transactional
public class SearchServiceImpl implements SearchService{
	private final UserRepository userRepository;
	private static final Logger logger = LoggerFactory
			.getLogger(SearchServiceDaoImpl.class);
	public SearchServiceImpl(UserRepository cityRepository) {
		this.userRepository = cityRepository;
	}
	@Autowired
	private SearchServiceDao searchDAO;
	
	@Autowired
    private BookCategoryRepository bookCategoryRepository;

	@Override
	public String getUserDetails(SearchParam searchParam) {
		String result="";
		logger.info("ENTER:SearchServiceImpl:getUserDetails");
		try {
			//searchDAO = (SearchServiceDao) SpringApplicationContext.getBean("SearchServiceDaoImpl");
			result = searchDAO.getUserDetailsUsingProcedure(searchParam);
			logger.info(" out put "+result);

		} catch (Exception e) {
			logger.error("error in SearchServiceImpl: getUserDetails() exception  :  " + e);
		}
		logger.info("EXIT:SearchServiceImpl:getUserDetails");
		return result;
	}
	
	@Override
	public User getUser(Long id, String name) {
		Assert.notNull(id, "Name must not be null");
		Assert.notNull(name, "Country must not be null");
		
		return this.userRepository.findByNameAllIgnoringCase(name);
	}

	@Override
	public String addUser(String name) {
		User usr = new User(name);
		usr=this.userRepository.save(usr);
		return "User Created In Databse ID: "+usr.getId()+" Name: "+usr.getName();
	}

	@Override
	public String addBooks() {
		List<BookCategory> lsBkCat= new ArrayList<BookCategory>();
        // save a couple of categories
        final BookCategory categoryA = new BookCategory("Category A");
        Set bookAs = new HashSet<Book>(){{
            add(new Book("Book A1", categoryA));
            add(new Book("Book A2", categoryA));
            add(new Book("Book A3", categoryA));
        }};
        categoryA.setBooks(bookAs);

        final BookCategory categoryB = new BookCategory("Category B");
        Set bookBs = new HashSet<Book>(){{
            add(new Book("Book B1", categoryB));
            add(new Book("Book B2", categoryB));
            add(new Book("Book B3", categoryB));
        }};
        categoryB.setBooks(bookBs);

        bookCategoryRepository.save(new HashSet<BookCategory>() {{
            add(categoryA);
            add(categoryB);
        }});

        // fetch all categories
        String sOutPut="";
        String separator="";
        for (BookCategory bookCategory : bookCategoryRepository.findAll()) {
        	sOutPut=sOutPut+separator+(bookCategory.toString());
        	separator="\n";
        }
        lsBkCat.add(categoryA);
        lsBkCat.add(categoryB);
        return sOutPut;
    }

}