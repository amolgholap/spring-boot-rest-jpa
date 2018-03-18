package com.ag.restboot.rest;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ag.restboot.bean.Employee;
import com.ag.restboot.model.BookCategory;
import com.ag.restboot.services.SearchService;


/**
 * An example of creating a Rest api using Spring Annotations @RestController.
 * 
 * 
 * 
 * @author Amol N. Gholap-Predix2.0
 */
@RestController
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	//@SuppressWarnings("nls")
	@RequestMapping("/")
	public String index(
			@RequestParam(value = "echo", defaultValue = "echo") String echo) {
		return "Greetings from Predix Spring Boot! -Product Config"
				+ (new Date());
	}

	@Autowired
	private SearchService searchService;
	String result = "";
	

	@GetMapping("/user/{activity}/{user}")
	@ResponseBody
	@Transactional
	public String helloWorld(@PathVariable String activity,@PathVariable String user) {
		if("addUser".equalsIgnoreCase(activity)){
			return this.searchService.addUser(user);
		}else if("getUser".equalsIgnoreCase(activity)){
			return this.searchService.getUser((long) 1, user).getName();
		}else if("addBooks".equalsIgnoreCase(activity)){
			return this.searchService.addBooks();
		}
		else{
			return "something wrong";
		}
	}

	//@RequestMapping(value = "/book/{activity}", method = RequestMethod.GET)
	@GetMapping("/book/{activity}")
	@ResponseBody
	public List<BookCategory> getSearchJsonTest(@PathVariable String activity) {
		if("getAllBooks".equalsIgnoreCase(activity))
		return this.searchService.getBooks(activity);
		
		return null;
		/*final BookCategory categoryA = new BookCategory("Category A");
        Set bookAs = new HashSet<Book>();
        Book ak1= new Book();
        ak1.setId(1);
        ak1.setName("new book A");
        bookAs.add(ak1);
        categoryA.setBooks(bookAs);

        final BookCategory categoryB = new BookCategory("Category B");
        Set bookBs = new HashSet<Book>(){{
            add(new Book("Book B1", categoryB));
            add(new Book("Book B2", categoryB));
            add(new Book("Book B3", categoryB));
        }};
        Set bookBs = new HashSet<Book>();
        Book bk1= new Book();
        bk1.setId(1);
        bk1.setName("new book B");
        bookBs.add(bk1);
        categoryB.setBooks(bookBs);
		List<BookCategory> lsBkCt= new ArrayList<BookCategory>();
		lsBkCt.add(categoryA);
		lsBkCt.add(categoryB);
		return lsBkCt;*/
	}
	@GetMapping("/getEmployees")
	@ResponseBody
	public List<Employee> getEmployees() {
		/*logger.info("ENTER:ProductConfiguratorDataServiceImpl: getEmployees");
		List<Employee> alEmpl=null;
			alEmpl = new ArrayList<Employee>();
			
			Employee em2 = new Employee();
			em2.setName("Andrew");
			em2.setId(1);
			em2.setSalary(100);
			Employee em1 = new Employee();
			em1.setName("Feder");
			em1.setId(2);
			em1.setSalary(100);
			Employee em = new Employee();
			em.setName("John");
			em.setId(3);
			em.setSalary(100);
			
			alEmpl.add(em2);
			alEmpl.add(em1);
			alEmpl.add(em);

			return alEmpl;*/
		return searchService.getEmployees();
	}
	@PostMapping(path = "/addEmployees",consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String addEmployees(@RequestBody Employee employee) {
		logger.info("ENTER:ProductConfiguratorDataServiceImpl: addEmployees");
		return searchService.addEmployees(employee);
	}
}