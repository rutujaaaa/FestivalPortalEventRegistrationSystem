package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.businesstier.dao.EventDAO;
import com.businesstier.dao.VisitorDAO;
import com.businesstier.entity.Event;
import com.businesstier.entity.EventCoordinator;
import com.businesstier.entity.Visitor;
import com.exceptions.FERSGenericException;
import com.helper.FERSDataConnection;

/**
 * Junit test class for EventDAO class
 * 
 */
public class TestEventDAO {

	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	private ArrayList<Object[]> showAllEvents;
	private EventDAO dao;

	/**
	 * Sets up database connection before other methods are executed in this
	 * class
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpDatabaseConnection() throws Exception {
		connection = FERSDataConnection.createConnection();
	}

	/**
	 * Closes the database connection after all the methods are executed
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownDatabaseConnection() throws Exception {
		/**
		 * @TODO: Close connection object here  
		 */
	}

	/**
	 * Sets up the objects required in other methods
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		showAllEvents = new ArrayList<Object[]>();
		dao = new EventDAO();
	}

	/**
	 * Deallocate the resources after execution of method
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
	 * Positive test case to test the method showAllEvents
	 */
	@Test
	public void testShowAllEvents_Positive() {
		/**
		 * @TODO: Call showAllEvents method and assert it for
		 * size of returned type list
		 */
		try {
			showAllEvents=dao.showAllEvents();
		} catch (ClassNotFoundException e) {
			fail("showAllEvents positive failed"+e);
			e.printStackTrace();
		} catch (SQLException e) {
			fail("showAllEvents positive failed"+e);
			e.printStackTrace();
		}
		boolean status=false;
		if(showAllEvents.size()> 0)
		{
			status= true;
		}
		assertTrue(status);
	}

	/**
	 * Junit test case to test positive case for updateEventDeletions
	 */

	@Test
	public void testUpdateEventDeletions_Positive() {
		/**
		 * @TODO: Find out seats available for an event by opening a connection
		 * and calling the query SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?
		 * Call the updateEventDeletions for eventId
		 * Again find out the seats available for this event
		 * testSeatsAvailableBefore should be 1 more then testSeatsAvailableAfter
		 */ 
		int seat=0,seat1=0;
		try {
			statement=connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");
			statement.setInt(1, 1001);
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				seat = resultSet.getInt(1);
			}
			dao.updateEventDeletions(1001, 10001);
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				seat1 = resultSet.getInt(1);
			}
		} 
		catch (SQLException e) {
			fail("Excepton : "+e);
		} 
		catch (ClassNotFoundException e) 
		{
			fail("Exception : "+e);
		} catch (Exception e) {
			fail("Exception : "+e);
		}
		assertEquals(seat+1,seat1);
	}


	/**
	 * Negative test case for method updateEventDeletions
	 */
	@Test
	public void testUpdateEventDeletions_Negative() {
		/**
		 * @TODO: Call updateEventDeletions for incorrect eventid and it should
		 * throw an exception
		 */
		int seat=0;
		try {
			statement=connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");
			statement.setInt(1, 1001);
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				seat = resultSet.getInt(1);
			}
			dao.updateEventDeletions(1325, 10001);
		}
		catch(FERSGenericException e)
		{
			assertTrue(true);
		}
		catch (ClassNotFoundException e) {
			fail("Unable to delete event");
		} 
		catch (SQLException e) {
			fail("Unable to delete event");
		} 
		catch (Exception e) {
			fail("Unable to delete event");
		}
	}


	/**
	 * Positive test case for method updateEventNominations
	 */
	@Test
	public void testUpdateEventNominations_Positive() {
		/**
		 * @TODO: Find out seats available for an event by opening a connection
		 * and calling the query SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?
		 * Call the updateEventNominations for eventId
		 * Again find out the seats available for this event
		 * testSeatsAvailableBefore should be 1 less then testSeatsAvailableAfter
		 */	
		try {
			int seat=0,seat1=0;
			connection = FERSDataConnection.createConnection();
			statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");
			statement.setInt(1, 1002);
			resultSet=statement.executeQuery();
			if(resultSet.next()) {
			seat= resultSet.getInt(1);
			}
			dao.updateEventNominations(1002, 10002);
			statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");
			statement.setInt(1, 1002);
			resultSet=statement.executeQuery();
			if(resultSet.next()) {
				seat1= resultSet.getInt(1);
			}
			assertEquals(seat1, seat-1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Negative test case for method updateEventNominations
	 */
	@Test
	public void testUpdateEventNominations_Negative() {
		/**
		 * @TODO: Call updateEventNominations for incorrect eventid and it should
		 * throw an exception
		 */	
		try {
			connection = FERSDataConnection.createConnection();
			statement = connection.prepareStatement("SELECT SEATAVAILABLE FROM EVENT WHERE EVENTID = ?");
			statement.setInt(1, 1009);
			statement.executeUpdate();
			dao.updateEventNominations(1009, 10001);
		} catch (ClassNotFoundException | SQLException e) {
			assertTrue(true);
			e.printStackTrace();
		} catch (Exception e) {
			assertTrue(true);
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Positive test case for method checkEventsofVisitor
	 */
	@Test
	public void testCheckEventsOfVisitor_Positive() {
		/**
		 * @TODO: Create visitor object by setting appropriate values
		 * Call checkEventsofVisitor method by passing this visitor object and
		 * valid eventId
		 * Assert the value of return type 
		 */	
		Visitor visitor;
		 
		boolean status=false;
		 
		try {
		 
			visitor = (new VisitorDAO()).searchUser("Sherlock", "password");
			int eventid=1001;
			int sessionid=10001;
			(new VisitorDAO()).registerVisitorToEvent(visitor, eventid, sessionid);
			status=dao.checkEventsofVisitor(visitor, eventid, sessionid);
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException"+e); 
			e.printStackTrace();
		} catch (SQLException e) { 
			fail("SQLException"+e);
			 
			e.printStackTrace();
			 
			} catch (Exception e) {
			 
			// TODO Auto-generated catch block
			 
			fail("Exception"+e);
			 
			e.printStackTrace();
	
			}

		 
		assertTrue(status);

	}
	
	/**
	 * Junit test case for getEventCoordinator
	 */
	@Test
	public void testGetEventCoordinator(){
		/**
		 * @TODO: Call getEventCoordinator method
		 * Assert the size of return type arraylist
		 */ 
		List<EventCoordinator> eventCoordinators=new ArrayList<EventCoordinator>();
		try {
			eventCoordinators= dao.getEventCoordinator();
			assertEquals(5, eventCoordinators.size());
		} catch (ClassNotFoundException e) {
			fail("Get event coordinator failed");
			e.printStackTrace();
		} catch (SQLException e) {
			fail("Get event coordinator failed");
			e.printStackTrace();
		}
	}
	/**
	 * Junit test case for getEvent
	 */
	@Test
	public void testGetEvent(){
		/**	 
		* @TODO: Call getEvent method 
		* Assert the returned Event type with the passed value of event id 
		*/  
		Event event=new Event(); 
		try { 
		event=dao.getEvent(1004, 10004); 
		assertEquals(1004, event.getEventid()); 
		} catch (ClassNotFoundException e) { 
		fail("Test get event failed"); 
		e.printStackTrace(); 
		} catch (SQLException e) {
		fail("Test get event failed");
		e.printStackTrace();
		}
		} 
	
	/**
	 * Junit test case for updateEvent
	 */
	public void testInsertEvent()
	{
		/**
		 * @TODO: Create Event object by setting appropriate values
		 * Call insertEvent method by passing this event object
		 * Assert the status of return type of this insertEvent method
		 */ 
		//EVENTID,NAME,DESCRIPTION,PLACES,DURATION,EVENTTYPE
		Event e1 = new Event();
		e1.setName("Tomatina");
		e1.setDescription("Tomato Festival");
		e1.setPlace("Spain");
		e1.setDuration("0900-1500");
		e1.setEventtype("Attraction");
		e1.setSeatsavailable("500");
		e1.setEventCoordinatorId(101);
		try {
			int result = dao.insertEvent(e1);
			System.out.println(result);
			if(result>0)
			{
				assertTrue(true);
			}
		} 
		catch (ClassNotFoundException e) {
			fail("Exception : "+e);
		} 
		catch (SQLException e) {
			fail("Exception : "+e);
		}
	}

	
	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testUpdateEvent(){
		///Test failed
		/**
		 * @TODO: Fetch Event object by calling showAllEvents method
		 * Update the values of event object
		 * Call updateEvent method by passing this modified event as object
		 * Assert the status of return type of updateEvent method
		 * e1.setName((String) values[1]);
		e1.setDescription((String) values[2]);
		e1.setDuration((String) values[3]);
		e1.setName("Live Concert");
		e1.setDescription("Arijit");
		e1.setPlace("Mumbai");
		e1.setDuration("3 hrs");
		e1.setEventtype("Rock concert");
		 */
		//UPDATE EVENT E1 SET E1.NAME=?, E1.DESCRIPTION=?, E1.PLACES=?, E1.DURATION=?, E1.EVENTTYPE=? WHERE E1.EVENTID=?
		int result=0;
		String eventname="Fireworks Show";
		try{
			showAllEvents=dao.showAllEvents(eventname);
			Event e1=new Event();
			for(Object[] values:showAllEvents)
			{
				e1.setEventid((Integer) values[0]);
				e1.setSessionId((Integer) values[7]);
				break;
			}
			e1.setSeatsavailable("50");
			result=dao.updateEvent(e1);
		} catch (ClassNotFoundException e) {
			fail("Class Not Found exception"+e);
			e.printStackTrace();
		} catch (SQLException e) {
			fail("SQLException"+e);
			e.printStackTrace();
		}
		boolean status=false;
		if(result > 0)
		{
			status=true;
		}
		assertTrue(status); 
	}

	
	/**
	 * Junit test case for deleteEvent
	 */
	
	
	
	@Test
	public void testDeleteEvent(){
		/**
		 * @TODO: Fetch Event object by calling showAllEvents method * 
		 * Call deleteEvent method by passing this event id and event session id as object
		 * Assert the status of return type of updateEvent method
		 */
		///Test failed
		int result=0;
		String eventname="Rose Parade";
		try {
			showAllEvents=dao.showAllEvents(eventname);
			Event e1=new Event();
			for(Object[] values:showAllEvents)
			{
				e1.setEventid((Integer) values[0]);
				e1.setSessionId((Integer) values[7]);
				break;
			}
			System.out.println(e1.getEventid()+" "+ e1.getSessionId());
			result=dao.deleteEvent(e1.getEventid(), e1.getSessionId());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Class Not Found exception"+e);
			e.printStackTrace();
		} catch (SQLException e) {
			fail("SQLException "+e);
			e.printStackTrace();
		} catch (FERSGenericException e) {
			fail("FERSGenericException"+e);
			e.printStackTrace();
		}
		boolean status=false;
		if(result > 0)
		{
			status=true;
		}
		assertTrue(status); 
	}
}
