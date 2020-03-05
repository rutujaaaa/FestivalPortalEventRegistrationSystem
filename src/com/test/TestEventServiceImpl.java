package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.businesstier.dao.EventDAO;
import com.businesstier.entity.Event;
import com.businesstier.entity.EventCoordinator;
import com.businesstier.entity.Visitor;
import com.businesstier.service.EventServiceImpl;
import com.exceptions.FERSGenericException;

/**
 * Junit test case to test class EventServiceImpl
 * 
 */
public class TestEventServiceImpl {

	private List<Object[]> eventList;
	private Visitor visitor;
	private EventServiceImpl eventServiceImpl;

	/**
	 * Set up the objects required before execution of every method
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		eventServiceImpl = new EventServiceImpl();
		visitor = new Visitor();
	}

	/**
	 * Deallocates the objects after execution of every method
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
	 * Test case to test the method getAllEvents
	 */
	@Test
	public void testGetAllEvents() {
		/**
		 * @TODO: Call getAllEvents method and assert it for the size of returned array
		 */	
		eventList=eventServiceImpl.getAllEvents();
		assertTrue(eventList.size()> 0);
	}

	/**
	 * Test case to test the method checkEventsofVisitor
	 */
	@Test
	public void testCheckEventsofVisitor() {
		/**
		 * @TODO: Call checkEventsofVisitor and assert the returned type of this method
		 * for appropriate return type
		 */	
	
		boolean status=false; 
		visitor.setVisitorId(1002);
		status=eventServiceImpl.checkEventsofVisitor(visitor, 1004, 10004); 
		assertTrue(status);
	}

	/**
	 * Test case to test the method updateEventDeletions
	 */
	@Test
	public void testUpdateEventDeletions() {
		/**
		 * @TODO: Call updateEventDeletions and assert the return type of this method
		 */		
		eventServiceImpl.updateEventDeletions(1002, 10002);	 
		eventList = eventServiceImpl.getAllEvents();	 
		for(Object[] values:eventList)
		{ 
			if(values[1].equals("npatel"))
			{	 
				assertTrue(false);
				return;
			}
		} 
		assertTrue(true);
	}
	/**
	 * Junit test case for getEventCoordinator
	 */
	@Test
	public void testGetEventCoordinator() {
		/**
		 * @TODO: Call getAllEventCoordinators and assert the size of return type of this method
		 */	
		assertEquals(eventServiceImpl.getAllEventCoordinators().size()>0, true);
	}

	/**
	 * Junit test case for getEvent
	 */
	@Test
	public void testGetEvent() {
		/**
		 * @TODO: Call getEvent and assert the event id of this event with 
		 * passed event id 
		 */		
		int eventId=1002;
		int sessionId=10002;
		assertEquals(eventId, eventServiceImpl.getEvent(eventId, sessionId).getEventid());
	}

	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testInsertEvent() {
		/**
		 * @TODO: Call insertEvent
		 * Create event object by setting appropriate values
		 * Assert the status of insertEvent method
		 */		
		Event event = new Event();
		//event.setEventid(1008);
		event.setName("Secret Santa");
		event.setDescription("Event during Christmas");
		event.setPlace("USA");
		event.setDuration("1100-1000");
		event.setEventtype("Attraction");
		event.setSeatsavailable("50");
		event.setEventCoordinatorId(101);
		int status=eventServiceImpl.insertEvent(event);
		System.out.println(status);
		assertTrue(status>0);
		
		
	}

	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testUpdateEvent() {
		/**
		 * @TODO: Fetch Event object by calling getAllEvents method 
		 * Update event object by setting appropriate values
		 * Call updateEvent method
		 * Assert the status of updateEvent method
		 */	
		eventList=eventServiceImpl.getAllEvents();
		Event e1 = new Event();
		for(Object[] e:eventList)
		{
			e[6]="500";
			e1.setEventid((Integer)e[0]);
			e1.setSeatsavailable((String)e[6]);
			e1.setSessionId((Integer)e[7]);
			break;
		}
		int status=eventServiceImpl.updateEvent(e1);
		assertEquals(1,status);
	}

	/**
	 * Junit test case for deleteEvent
	 */
	@Test
	public void testDeleteEvent() {
		/**
		 * @TODO: Fetch Event object by calling getAllEvents method 
		 * Update event object by setting appropriate values
		 * Call deleteEvent method
		 * Assert the status of deleteEvent method
		 */	
		eventList = eventServiceImpl.getAllEvents(); 
		Event e1 = new Event(); 
		for(Object[] values:eventList)
		{ 
			e1.setEventid((Integer)values[0]); 
			e1.setSessionId((Integer)values[7]); 
			break;
		} 
		int status =eventServiceImpl.deleteEvent(e1.getEventid(), e1.getSessionId()); 
		assertEquals(1,status);

	}

}
