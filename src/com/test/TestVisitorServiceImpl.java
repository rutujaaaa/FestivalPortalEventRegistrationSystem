package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.businesstier.dao.VisitorDAO;
import com.businesstier.entity.Event;
import com.businesstier.entity.Visitor;
import com.businesstier.service.VisitorServiceImpl;

/**
 * Junit test class for VisitorServiceImpl
 *
 */
public class TestVisitorServiceImpl {

	private List<Event> visitorList;	
	private Visitor visitor;
	private VisitorServiceImpl visitorServiceImpl;

	/**
	 * Set up the initial methods 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {		
		visitorServiceImpl = new VisitorServiceImpl();
		visitor = new Visitor();
	}

	/**
	 * Deallocates the objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
	}

	/**
	 * Test case for method createVisitor
	 */
	@Test
	public void testCreateVisitor() {
		/**
		 * @TODO: Set the appropriate values for visitor object and
		 * call the method createVisitor by passing an argument of this visitor 
		 * object and then asserting the returned type of this method
		 */		
		visitor.setUserName("sunidhi");
		visitor.setPassword("password");
		visitor.setFirstName("Sunidhi");
		visitor.setLastName("Desai");
		visitor.setPhoneNumber("123456789");
		visitor.setEmail("s@gmail.com");
		visitor.setAddress("Mumbai");
		boolean status=visitorServiceImpl.createVisitor(visitor);
		visitor=visitorServiceImpl.searchVisitor("sunidhi", "password");
		assertEquals("sunidhi", visitor.getUserName());

	}

	/**
	 * Test case for method createVisitor
	 */
	@Test
	public void testSearchVisitor() {
		/**
		 * @TODO: Call searchVisitor method by passing the appropriate arguments 
		 * and then asserting the returned type visitor username with the argument passed
		 */		
		String username="jjones";
		String password="password";
		Visitor v=visitorServiceImpl.searchVisitor(username, password);
		assertEquals(username, v.getUserName());

	}

	/**
	 * Test case for method RegisterVisitor
	 */
	@Test
	public void testRegisterVisitor() {
		/**
		 * @TODO: Call RegisterVisitor method by passing visitor object which 
		 * can be retrieved using searchVisitor method and then asserting the returned
		 * type of RegisterVisitor method 
		 */		
		visitor= visitorServiceImpl.searchVisitor("npatel", "password");
		visitorServiceImpl.RegisterVisitor(visitor, 1002, 10002);
		ArrayList<Object[]> list = visitorServiceImpl.showRegisteredEvents(visitor);
		for (Object[] events : list) {
			System.out.println(events[0]+":"+events[1]+":"+events[2]+":"+events[3]+":"+events[4]+":"+events[5]+":"+events[6]+":"+events[7]+
					":"+events[8]+":"+events[9]);
			if(events[0].equals(1002)) {
				assertTrue(true);
			}
		}
		
		
	}

	/**
	 * Test case for method showRegisteredEvents
	 */
	@Test
	public void testShowRegisteredEvents() {
		/**
		 * @TODO: Call showRegisteredEvents method by passing visitor object which 
		 * can be retrieved using searchVisitor method and then asserting the returned
		 * type of showRegisteredEvents method 
		 */	
		ArrayList<Object[]> visit=new ArrayList<>();	 
		visitor=visitorServiceImpl.searchVisitor("npatel","password"); 
		visit=visitorServiceImpl.showRegisteredEvents(visitor);
		assertEquals(1, visit.size());

		
	}

	/**
	 * Test case for method updateVisitorDetails
	 */
	@Test
	public void testUpdateVisitorDetails() {
		/**
		 * @TODO: Call updateVisitorDetails method by passing the visitor object which
		 * can be retrieved using searchVisitor method and then asserting the returned
		 * type of updateVisitorDetails
		 */		
		int status =0;
		visitor = visitorServiceImpl.searchVisitor("npatel", "password");
		visitor.setAddress("New Jersey");
		status=visitorServiceImpl.updateVisitorDetails(visitor);
		assertEquals(1,status);
	}

	/**
	 * Test case for method unregisterEvent
	 */
	@Test
	public void testUnregisterEvent() {
		/**
		 * @TODO: Call unregisterEvent method by passing the visitor object which can be
		 * retrieved using searchVisitor method and then asserting the returned type 
		 * of unregisterEvent
		 */		
		boolean status = true;
		//VisitorDAO dao=new VisitorDAO(); 
		int eventid = 1002;
		int eventsessionid=10002;
		visitor = visitorServiceImpl.searchVisitor("sunidhi", "password");
		visitorServiceImpl.RegisterVisitor(visitor, eventid, eventsessionid);
		visitorServiceImpl.unregisterEvent(visitor, eventid, eventsessionid);
		ArrayList<Object[]> obj= visitorServiceImpl.showRegisteredEvents(visitor);
		for(Object[] events:obj){
			if(events[0].equals(1002)){
				status = false;
				break;
			}
		}
		assertTrue(status);
	}

}
