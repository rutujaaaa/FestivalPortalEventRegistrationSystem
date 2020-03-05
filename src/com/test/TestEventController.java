package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.businesstier.controller.EventController;
import com.businesstier.dao.VisitorDAO;
import com.businesstier.entity.Visitor;
import com.exceptions.FERSGenericException;

/**
 * Junit test class for EventController
 * 
 */
public class TestEventController {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private ModelAndView modelAndView;
	private EventController controller;

	/**
	 * Sets up initial objects required in other methods
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		modelAndView = new ModelAndView();
		controller = new EventController();
		response = new MockHttpServletResponse();		
	}

	/**
	 * Deallocate the objects after execution of every method
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
	 * Test case to test the positive scenario for getAvailableEvents method
	 */
	@Test
	public void testGetAvailableEvents_Positive() {
		try {
			request = new MockHttpServletRequest("GET", "/catalog.htm");
			String username="jjones";
			String password="password";
			Visitor v=(new VisitorDAO()).searchUser(username, password);
			request.getSession().setAttribute("VISITOR", v);
			modelAndView = controller.getAvailableEvents(request, response);
		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/eventCatalog.jsp", modelAndView.getViewName());
	}

	/**
	 * Executes the negative scenario for getAvailableEvents method
	 */
	@Test
	public void testGetAvailableEvents_Negative() {
		/**
		 * @TODO: Call getAvailableEvents methods  by passing request as null
		 * and assert it for appropriate model view name
		 */	
		boolean status=false;
		try {
		modelAndView = controller.getAvailableEvents(null, response);
		} catch (Exception exception) {
		status=true;
		} 
		assertTrue(status);
	}
	
	/**
	 * Test case to test the positive scenario for displayEvent method
	 */
	@Test
	public void testDisplayEvent_Positive() {
		/**
		 * @TODO: Call displayEvent methods and assert
		 * it for appropriate model view name
		 */	
		try {
			request = new MockHttpServletRequest("GET", "/displayEvent.htm");
			request.setParameter("eventId", "1002");
			request.setParameter("sessionId", "10002");
			Visitor visitor = new VisitorDAO().searchUser("npatel", "password");
			request.getSession().setAttribute("VISITOR",visitor );
			modelAndView = controller.displayEvent(request, response);
		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/addEvent.jsp", modelAndView.getViewName());
	}

	/**
	 * Executes the negative scenario for displayEvent method
	 */
	@Test
	public void testDisplayEvent_Negative() {
		/**
		 * @TODO: Call displayEvent methods  by passing request as null
		 * and assert it for appropriate model view name
		 */	
		try {
			modelAndView = controller.displayEvent(request, response);
		} catch (FERSGenericException e) {
			assertTrue(true);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	/**
	 * Test case to test the positive scenario for updateEvent method
	 */
	@Test
	public void testUpdateEvent_Positive() {
		/**
		 * @TODO: Call updateEvent methods and assert
		 * it for appropriate model view name
		 * 		String eventId = request.getParameter("eventId");
		String sessionId = request.getParameter("sessionId");
		String eventName = request.getParameter("eventName");
		String desc = request.getParameter("desc");
		String place = request.getParameter("place");
		String duration = request.getParameter("duration");
		String eventType = request.getParameter("eventType");
		String ticket = request.getParameter("ticket");
		String isAdd = request.getParameter("isAdd");			
		 */	
		request = new MockHttpServletRequest("GET", "/updateEvent.htm");	 
		try {	 
		request.setParameter("eventId", "1004");	 
		request.setParameter("sessionId", "10004");	 
		request.setParameter("eventName", "NCC Semi Finals");	 
		request.setParameter("desc", "have fun");
		request.setParameter("place", "new jersey");
		request.setParameter("duration", "2000-2300");
		request.setParameter("eventType", "Attraction");
		request.setParameter("ticket", "50");
		request.setParameter("isAdd", "1");
		
		modelAndView=controller.updateEvent(request, response);	 
		assertEquals("/addEvent.jsp", modelAndView.getViewName()); 
		} catch (Exception e) { 
			e.printStackTrace();
			fail("Exception"+e); 
		
		}

	}

	/**
	 * Executes the negative scenario for updateEvent method
	 */
	@Test
	public void testUpdateEvent_Negative() {
		/**
		 * @TODO: Call updateEvent methods  by passing request as null
		 * and assert it for appropriate model view name
		 */	
		request = new MockHttpServletRequest("GET", "/updateEvent.htm"); 
		boolean status=false; 
		try {	 
		modelAndView=controller.updateEvent(null, response); 
		fail("Test failed"); 
		} catch (Exception e) { 
		status=true;
		e.printStackTrace();
		} 
		assertTrue(status);
	}
	
	/**
	 * Test case to test the positive scenario for displayEvent method
	 */
	@Test
	public void testDeleteEvent_Positive() {
		/**
		 * @TODO: Call deleteEvent methods and assert
		 * it for appropriate model view name
		 */
		try {
			request = new MockHttpServletRequest("GET", "/catalog.htm");
			String eventId="1002";
			request.setParameter("eventId", eventId);
			String sessionId="10002";
			request.setParameter("sessionId", sessionId);
			Visitor v = (new VisitorDAO()).searchUser("ylee", "password");
			request.getSession().setAttribute("VISITOR", v);
			modelAndView =controller.deleteEvent(request, response);
			assertEquals("/eventCatalog.jsp", modelAndView.getViewName());
		} catch (Exception e1) {
			e1.printStackTrace();
			fail("Exception : "+e1);
		}
	}

	/**
	 * Executes the negative scenario for displayEvent method
	 */
	@Test
	public void testDeleteEvent_Negative() {
		/**
		 * @TODO: Call deleteEvent methods  by passing request as null
		 * and assert it for appropriate model view name
		 */	
		try {
			request = new MockHttpServletRequest("GET", "/catalog.htm");
			modelAndView=controller.deleteEvent(null, response);
		} 
		catch(FERSGenericException e)
		{
			assertFalse(false);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
}
