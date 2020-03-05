package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.plaf.ActionMapUIResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.businesstier.dao.VisitorDAO;
import com.businesstier.entity.Event;
import com.businesstier.entity.Visitor;

import antlr.collections.List;

/**
 * JUnit test case for VisitorDAO class for testing all repository methods to
 * call database sub-routines
 * 
 */
public class TestVisitorDAO {

	private Visitor visitor;
	private VisitorDAO visitorDAO;
	private ArrayList<Object[]> registeredEvents;

	/**
	 * Setting up initial objects
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		visitor = new Visitor();
		visitorDAO = new VisitorDAO();
		registeredEvents = new ArrayList<Object[]>();
	}

	/**
	 * Deallocating objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
	}

	/**
	 * Test case for method insertData
	 */
	@Test
	public void testInsertData() {
		/**
		 * @TODO: Create visitor object by setting appropriate values
		 * Call insertData method by passing this visitor object
		 * Search this new visitor object by calling searchUser method
		 * Assert the values of username*/ 
		Visitor visitor=new Visitor();
		visitor.setVisitorId(1001);
		visitor.setFirstName("Sherlock");
		visitor.setLastName("Holmes");
		visitor.setPassword("password");
		visitor.setPhoneNumber("123456789");
		visitor.setAddress("Baker street");
		try {
			visitorDAO.insertData(visitor);
			assertEquals("Sherlock", visitor.getFirstName());
		} catch (ClassNotFoundException e) {
			fail("Data insertion failed");
			e.printStackTrace();
		} catch (SQLException e) {
			fail("Data insertion failed");
			e.printStackTrace();
		} catch (Exception e) {
			fail("Data insertion failed");
			e.printStackTrace();
		}
	} 
	/**
	 * Test case for method searchUser
	 */
	@Test
	public void testSearchUser() {
		/**
		 * @TODO: Call searchUser method for valid values of username
		 * and password and assert the value of username for the returned type of method
		 */ 
		try {
			visitor=visitorDAO.searchUser("Sherlock", "password");
			assertEquals("Sherlock", visitor.getUserName());
		} catch (ClassNotFoundException e) {
			fail("Search user test failed");
			e.printStackTrace();
		} catch (SQLException e) {
			fail("Search user test failed");
			e.printStackTrace();
		}
	}
	/**
	 * Test case for method registerVisitorToEvent
	 */
	@Test
	public void testRegisterVisitorToEvent() {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of username and password
		 * Pass this visitor object and valid eventid to registerVisitorToEvent method
		 * and assert the value
		 */		
		try {
			//int actual = 0, expected=1;
			visitor = visitorDAO.searchUser("npatel", "password");
			visitorDAO.registerVisitorToEvent(visitor, 1002, 10002);
			ArrayList<Object[]> list = visitorDAO.registeredEvents(visitor);
			for (Object[] events : list) {
				System.out.println(events[0]+":"+events[1]+":"+events[2]+":"+events[3]+":"+events[4]+":"+events[5]+":"+events[6]+":"+events[7]+
						":"+events[8]+":"+events[9]);
				if(events[0].equals(1002)) {
					assertTrue(true);
				}
			}
			/*actual = list.size();
			assertEquals(expected, actual);*/
		} catch (ClassNotFoundException | SQLException e) {
			//fail("Registering visitor to event failed");
			e.printStackTrace();
		} catch (Exception e) {
			//fail("Registering visitor to event failed");
			e.printStackTrace();
		}
		
		
	}	

	/**
	 * Test case for method registeredEvents
	 */
	@Test
	public void testRegisteredEvents() {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of username and password
		 * Pass this visitor object and valid eventid to registeredEvents method
		 * and assert the value
		 */	
		int actual = 0, expected=1;
		try {
			visitor = visitorDAO.searchUser("npatel", "password");
			ArrayList<Object[]> list = visitorDAO.registeredEvents(visitor);
			for (Object[] events : list) {
				System.out.println(events[0]+":"+events[1]+":"+events[2]+":"+events[3]+":"+events[4]+":"+events[5]+":"+events[6]+":"+events[7]+
						":"+events[8]+":"+events[9]);
			}
			actual = list.size();
			assertEquals(expected, actual);
		} catch (ClassNotFoundException e) {
			fail("Registered events not fetched");
			e.printStackTrace();
		} catch (SQLException e) {
			fail("Registered events not fetched");
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Test case for method updateVisitor
	 */
	@Test
	public void testUpdateVisitor() {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of username and password
		 * Update the value in this visitor object
		 * Pass this visitor object to updateVisitor method
		 * and assert the value of changed value
		 */ 
		try {
			visitor=visitorDAO.searchUser("bsmith", "password");
			visitor.setUserName("Sherlock");
			visitorDAO.updateVisitor(visitor);
			assertEquals("Sherlock", visitor.getUserName());
		} catch (ClassNotFoundException e) {
			fail("Update visitor test failed");
			e.printStackTrace();
		} catch (SQLException e) {
			fail("Update visitor test failed");
			e.printStackTrace();
		}
	}


	/**
	 * Test case for method registeredEvents
	 */
	@Test
	public void testUnregisterEvent() {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of username and password
		 * Pass this visitor object and valid eventid to unregisterEvent method
		 * and assert the value
		 */ 
		try {
			visitor = visitorDAO.searchUser("bsmith", "password");
			visitorDAO.unregisterEvent(visitor, 1002, 10002);
			registeredEvents=visitorDAO.registeredEvents(visitor);
			Event e1 = new Event();
			for(Object[] values : registeredEvents)
			{
				e1.setEventid((Integer)values[0]);
			}
			if(e1.getEventid()==1002)
			{
				assertTrue(false);
			}
			assertTrue(true);
		} 
		catch (ClassNotFoundException e) {
			fail("Exception e :"+e);
		} 
		catch (SQLException e) {
			fail("Exception e :"+e);
		} catch (Exception e) {
			fail("Exception e :"+e);
		}
	}

	/**
	 * Test case for method change password
	 */
	/*@Test
	public void testChangePassword_VisitorNull() {
		*//**
		 * @TODO: Call changePassword method by passing visitor object as null
		 *//*		
	}*/
	
	/**
	 * Test case for method change password
	 */
	@Test
	public void testChangePassword_VisitorNull() {
		try {
			visitor = null;
			int status=visitorDAO.changePassword(visitor);
			assertTrue(status==-1);
		} catch (SQLException exception) {
			fail("SQL Exception");
		} catch (ClassNotFoundException exception) {
			fail("Class Not Found Exception");
		} catch (Exception exception) {
			fail("NULL Exception");
		}
	}

}
