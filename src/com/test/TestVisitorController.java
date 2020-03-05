package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.businesstier.controller.VisitorController;
import com.businesstier.dao.VisitorDAO;
import com.businesstier.entity.Visitor;
import com.exceptions.FERSGenericException;

/**
 * Junit test case to test the class VisitorController
 * 
 */
public class TestVisitorController {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockHttpSession session;
	private ModelAndView modelAndView;
	private VisitorController controller;
	private VisitorDAO visitorDao;
	private Visitor visitor;
	

	/**
	 * Set up initial methods required before execution of every method
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		modelAndView = new ModelAndView();
		controller = new VisitorController();
		session = new MockHttpSession();
		response = new MockHttpServletResponse();
		visitorDao = new VisitorDAO();
	}

	/**
	 * Deallocate objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
	}

	/**
	 * Positive test case to test the method newVisitor
	 */
	@Test
	public void testNewVisitor_Positive() {
		try {
			request = new MockHttpServletRequest("GET", "/newVistor.htm");

			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			request.setParameter("FIRSTNAME", "TestVFname");
			request.setParameter("LASTNAME", "lname");
			request.setParameter("EMAIL", "mail");
			request.setParameter("PHONENO", "11111");
			request.setParameter("ADDRESS", "testAddress");
			modelAndView = controller.newVisitor(request, response);
		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/registration.jsp", modelAndView.getViewName());
	}

	/**
	 * Negative test case to test the method newVisitor
	 */

	@Test
	public void testNewVisitor_Negative() {
		/**
		 * @TODO: Call newVisitor method by passing request object as null and 
		 * asserting the model view name
		 */ 
		boolean status=false;
		try {
			modelAndView = controller.newVisitor(null, response);
		} catch (Exception exception) {
			status=true;
		}
		assertTrue(status);
	}


	/**
	 * Positive test case to test the method searchVisitor
	 */
	@Test
	public void testSearchVisitor_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set request parameters for USERNAME and PASSWORD for valid values
		 * Call searchVisitor method and assert model view name 
		 */	
		
		try {
			request= new MockHttpServletRequest("GET", "/searchVisitor.htm");
			request.setParameter("USERNAME", "npatel");
			request.setParameter("PASSWORD", "password");
			modelAndView=controller.searchVisitor(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("/visitormain.jsp", modelAndView.getViewName());
	}

	/**
	 * Negative test case of invalid user for method searchVisitor
	 */
	@Test
	public void testSearchVisitor_Negative_InvalidUser() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set request parameters for USERNAME and PASSWORD for invalid values
		 * Call searchVisitor method and assert model view name 
		 */	
		try {
			request= new MockHttpServletRequest("GET", "/searchVisitor.htm");
			request.setParameter("USERNAME", "nikhita");
			request.setParameter("PASSWORD", "password");
			modelAndView=controller.searchVisitor(request, response);
		} catch (Exception e) {
			assertTrue(true);
			e.printStackTrace();
		}
		
	}

	/**
	 * Negative test case for method searchVisitor
	 */
	@Test
	public void testSearchVisitor_Negative() {
		/**
		 * @TODO: Call searchVisitor method by passing request object as null and 
		 * asserting the model view name
		 */	
		try {
			modelAndView=controller.searchVisitor(request, response);
		} catch (Exception e) {
			assertTrue(true);
			e.printStackTrace();
		}
		
	}

	/**
	 * Positive test case for method registerVisitor
	 */
	@Test
	public void testRegisterVisitor_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for USERNAME and PASSWORD for valid values
		 * Call registerVisitor method and assert model view name 
		 */		
		request = new MockHttpServletRequest("GET", "/eventreg.htm");	 
		Visitor visitor; 
		try { 
			visitor = visitorDao.searchUser("npatel", "password"); 
			session.setAttribute("VISITOR", visitor); 
			request.setSession(session);	 
			request.setParameter("eventId", "1004");	 
			request.setParameter("sessionId", "10004"); 
			modelAndView=controller.registerVisitor(request, response); 
			assertEquals("/visitormain.jsp", modelAndView.getViewName());	 
		} catch (ClassNotFoundException e) {	 
			fail("Exception");	 
			e.printStackTrace();	 
		} catch (SQLException e) {	 
			fail("Exception");	 
			e.printStackTrace();	 
		} catch (Exception e) {	 
			fail("Exception"+e);	 
			e.printStackTrace();
		} 

	}	

	/**
	 * Negative test case for method registerVisitor
	 */
	@Test
	public void testRegisterVisitor_Negative() {
		/**
		 * @TODO: Call registerVisitor method by passing request object as null and 
		 * asserting the model view name
		 */	
		request = new MockHttpServletRequest("GET", "/eventreg.htm");	 
		boolean status=false; 
		try { 
			modelAndView=controller.registerVisitor(null, response); 
		} catch (Exception e) { 
			// TODO Auto-generated catch block 
			status=true; 
			e.printStackTrace();
		} 
		assertTrue(status);
	}

	/**
	 * Positive test case for method updateVisitor
	 */
	@Test
	public void testUpdateVisitor_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for all valid user values
		 * Call updateVisitor method and assert model view name 
		 */	
		request = new MockHttpServletRequest("GET", "/newVistor.htm");
		try {
			Visitor visitor = visitorDao.searchUser("npatel", "password");
			visitor.setFirstName("Nona");
			visitor.setEmail("nona.berry@gmail.com");
			request.setParameter("username",visitor.getUserName());
			request.setParameter("password",visitor.getPassword());
			request.setParameter("firstname",visitor.getFirstName());
			request.setParameter("lastname",visitor.getLastName());
			request.setParameter("email",visitor.getEmail());
			request.setParameter("phoneno", visitor.getPhoneNumber());
			request.setParameter("address",visitor.getAddress());
			request.getSession().setAttribute("VISITOR", visitor);
			modelAndView=controller.updateVisitor(request, response);
		} catch (ClassNotFoundException e) {
			fail("Exception : "+e);
		} catch (SQLException e) {
			fail("Exception : "+e);
		} catch (Exception e) {
			fail("Exception : "+e);
		}
		assertTrue(true);
	}

	/**
	 * Negative test case for method updateVisitor
	 */
	@Test
	public void testUpdateVisitor_Negative() {
		/**
		 * @TODO: Call updateVisitor method by passing request object as null and 
		 * asserting the model view name
		 */	
		try 
		{
		request = new MockHttpServletRequest("GET", "/newVistor.htm");
		modelAndView = controller.updateVisitor(null, response);
		}  
		catch(FERSGenericException e)
		{
			assertTrue(true);
		}
		catch (Exception e) { 
		e.printStackTrace();
		}

	}

	/**
	 * Positive test case for method unregisterEvent
	 */
	@Test
	public void testUnregisterEvent_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for all USERNAME, PASSWORD and eventId values
		 * Call unregisterEvent method and assert model view name 
		 */	
		try {
			request = new MockHttpServletRequest("GET", "/eventunreg.htm");
			request.setParameter("eventId", "1004");
			request.setParameter("eventsessionid", "10004");
			Visitor visitor=(visitorDao).searchUser("npatel", "password");
			request.getSession().setAttribute("VISITOR",visitor );
			modelAndView = controller.unregisterEvent(request, response);
		} catch (Exception exception) {
			fail("Exception : "+exception.getMessage());
		}
		assertEquals("/visitormain.jsp", modelAndView.getViewName());
	}

	/**
	 * Negative test case for method unregisterEvent
	 */
	@Test
	public void testUnregisterEvent_Negative() {
		/**
		 * @TODO: Call unregisterEvent method by passing request object as null and 
		 * asserting the model view name
		 */	
		boolean status=false;
		try{
			modelAndView = controller.unregisterEvent(null, response);
			fail("failed : ");
		} catch (FERSGenericException e) {
			status= true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Exception"+e.getMessage());
			e.printStackTrace();
		}
		assertTrue(status);
	}

	/**
	 * Positive test case for search events by name
	 */

	@Test
	public void testSearchEventsByName_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO 
		 * Set request parameters for eventname
		 * Call searchEventsByName method and assert model view name 
		 */ 
		try {
			request = new MockHttpServletRequest("GET", "/newVistor.htm");
			String username="jjones";
			String password="password";
			String eventname="Garden Tour";
			Visitor v=visitorDao.searchUser(username, password);
			request.getSession().setAttribute("VISITOR", v);
			request.setParameter("eventname", eventname);
			modelAndView =controller.searchEventsByName(request, response);
		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/visitormain.jsp", modelAndView.getViewName());
	}


	/**
	 * Positive test case for search events by name catalog
	 */
	@Test
	public void testSearchEventsByNameCatalog_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for eventname
		 * Call searchEventsByNameCatalog method and assert model view name 
		 */		
		
		try {
			request = new MockHttpServletRequest("GET", "/searchEventByNameCatalog.htm");
			Visitor visitor = visitorDao.searchUser("npatel", "password");
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			request.setParameter("eventname", "Fireworks Show");
			modelAndView = controller.searchEventsByNameCatalog(request, response);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		assertEquals("/eventCatalog.jsp", modelAndView.getViewName());
		
	}

	/**
	 * Test case for show events in asc order
	 */
	@Test
	public void testShowEventsAsc() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		
		 * Call showEventsAsc method and assert model view name 
		 */			
		request = new MockHttpServletRequest("GET", "/displayasc.htm");	 
		try {	 
			Visitor visitor=visitorDao.searchUser("Sherlock", "password");	 
			session.setAttribute("VISITOR", visitor);	 
			request.setSession(session); 	 
			modelAndView = controller.showEventsAsc(request, response); 
			assertEquals("/visitormain.jsp", modelAndView.getViewName());	 
		} catch (ClassNotFoundException e) {	 
			fail("Exception");	 
			e.printStackTrace();	 
		} catch (SQLException e) {	 
			fail("Exception"); 
			e.printStackTrace(); 
		} catch (Exception e) { 
			fail("Exception"); 
			e.printStackTrace();
		}

	}

	/**
	 * Test case for show events in desc order
	 */
	@Test
	public void testShowEventsDesc() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		
		 * Call showEventsDesc method and assert model view name 
		 */		
		request = new MockHttpServletRequest("GET","./index.htm");
		try {
			visitor = visitorDao.searchUser("npatel", "password");
			request.getSession().setAttribute("VISITOR", visitor);
			modelAndView = controller.showEventsDesc(request, response);
		} catch (ClassNotFoundException e) {
			fail("Excpetion : "+e);
		} catch (SQLException e) {
			fail("Excpetion : "+e);
		} catch (Exception e) {
			fail("Excpetion : "+e);
		}
		assertEquals("/visitormain.jsp",modelAndView.getViewName());
	}

	/**
	 * Test case for show events catalog asc order
	 */
	@Test
	public void testShowEventsCatalogAsc() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		
		 * Call showEventsCatalogAsc method and assert model view name 
		 */	
		request = new MockHttpServletRequest("GET", "/newVistor.htm");
		try {
			Visitor visitor=(visitorDao).searchUser("npatel", "password");
			request.getSession().setAttribute("VISITOR", visitor);
			modelAndView=controller.showEventsCatalogAsc(request, response);
		} catch (ClassNotFoundException e1) {
			fail("Class Not Found Exception"+e1);
			e1.printStackTrace();
		} catch (SQLException e1) {
			fail("Class Not Found Exception"+e1);
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("/eventCatalog.jsp", modelAndView.getViewName());
	}

	/**
	 * Test case for show events catalog desc
	 */
	@Test
	public void testShowEventsCatalogDesc() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		
		 * Call showEventsCatalogDesc method and assert model view name 
		 */
		request = new MockHttpServletRequest("GET", "/newVistor.htm");
		try {
			Visitor visitor=(visitorDao).searchUser("npatel", "password");
			request.getSession().setAttribute("VISITOR", visitor);
			modelAndView=controller.showEventsCatalogDesc(request, response);
		} catch (ClassNotFoundException e1) {
			fail("Class Not Found Exception"+e1);
			e1.printStackTrace();
		} catch (SQLException e1) {
			fail("Class Not Found Exception"+e1);
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("/eventCatalog.jsp", modelAndView.getViewName());
	}


	/**
	 * Negative test case for search events by name
	 */
	@Test
	public void testSearchEventsByName_Negative() {
		/**
		 * @TODO: Call searchEventsByName method by passing request object as null and 
		 * asserting the model view name
		 */ 
		boolean status=false;
		try {
			modelAndView =controller.searchEventsByName(null, response);
		} catch (Exception exception) {
			status=true;
		}
		assertTrue(status);
	}

	/**
	 * Negative test case for search events by name catalog
	 */
	@Test
	public void testSearchEventsByNameCatalog_Negative() {
		/**
		 * @TODO: Call searchEventsByNameCatalog method by passing request object as null and 
		 * asserting the model view name
		 */		
		try {
			modelAndView = controller.searchEventsByNameCatalog(request, response);
		} catch (Exception e) {
			assertTrue(true);
			e.printStackTrace();
		}
	}

	/**
	 * Negative test case for show events in asc order
	 */
	@Test
	public void testShowEventsAsc_Negative() {
		/**
		 * @TODO: Call showEventsAsc method by passing request object as null and 
		 * asserting the model view name
		 */		
		boolean status=false;	 
		request = new MockHttpServletRequest("GET", "/eventreg.htm"); 
		try { 
			modelAndView=controller.showEventsAsc(null, response); 
			fail("Failed"); 
		} catch (Exception e) { 
			status=true;  
			e.printStackTrace();
		} 
		assertTrue(status);
	}

	/**
	 * Negative test case for show events in desc order
	 * 
	 */
	@Test
	public void testShowEventsDesc_Negative() {
		/**
		 * @TODO: Call showEventsDesc method by passing request object as null and 
		 * asserting the model view name
		 */		
		try {
			request = new MockHttpServletRequest("GET", "/newVistor.htm");
			controller.showEventsDesc(null, response);
		}
		catch(FERSGenericException e)
		{
			assertTrue(true);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Negative test case for show events catalog in asc order
	 */
	@Test
	public void testShowEventsCatalogAsc_Negative() {
		/**
		 * @TODO: Call showEventsCatalogAsc method by passing request object as null and 
		 * asserting the model view name
		 */	
		boolean status=false;
		try {
			modelAndView=controller.showEventsCatalogAsc(null, response);
		} 
		catch(FERSGenericException e){
			status = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(status);
	}

	/**
	 * Negative test case for show events catalog in desc order
	 */
	@Test
	public void testShowEventsCatalogDesc_Negative() {
		/**
		 * @TODO: Call showEventsCatalogDesc method by passing request object as null and 
		 * asserting the model view name
		 */		
	}
	
	
	/**
	 * Positive test case for change password
	 */
	/*@Test
	public void testChangePassword_Positive(){
		*//**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for password
		 * Call changePassword method and assert status as success
		 *//*		
	}
	
	*//**
	 * Negative test case for change password with password as null
	 *//*
	@Test
	public void testChangePassword_PasswordNull(){
		*//**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Do not set request parameters for password
		 * Call changePassword method and assert status as success
		 *//*	
	}
	
	*//**
	 * Negative test case for change password with visitor as null
	 *//*
	@Test
	public void testChangePassword_VisitorNull(){
		*//**
		 * @TODO: Create MockHttpServletRequest object 
		 * Do not set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for password
		 * Call changePassword method and assert status as success
		 *//*		
	}*/
	
	/**
	 * Positive test case for change password
	 */
	@Test
	public void testChangePassword_Positive(){
		try{
			request = new MockHttpServletRequest("GET", "/changePWD.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");	
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			request.setParameter("password", "password3");
			modelAndView = controller.changePassword(request, response);		
		}catch(Exception exception){
			fail("Exception");
		}
		assertEquals("success", modelAndView.getModelMap().get("status"));
		request.setParameter("password", "password");
		modelAndView = controller.changePassword(request, response);
	}
	
	/**
	 * Negative test case for change password with password as null
	 */
	@Test
	public void testChangePassword_PasswordNull(){
		try{
			request = new MockHttpServletRequest("GET", "/changePWD.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");			
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);			
			modelAndView = controller.changePassword(request, response);		
		}catch(Exception exception){
			fail("Exception");
		}
		assertEquals("error", modelAndView.getModelMap().get("status"));
	}
	
	/**
	 * Negative test case for change password with visitor as null
	 */
	@Test
	public void testChangePassword_VisitorNull(){
		try{
			request = new MockHttpServletRequest("GET", "/changePWD.htm");
			Visitor visitor = new Visitor();			
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			request.setParameter("password", "password");
			modelAndView = controller.changePassword(request, response);		
		}catch(Exception exception){
			fail("Exception");
		}
		assertEquals("error", modelAndView.getModelMap().get("status"));
	}
}
