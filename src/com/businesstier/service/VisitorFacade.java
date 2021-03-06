package com.businesstier.service;

import java.util.ArrayList;

import com.businesstier.entity.Event;
import com.businesstier.entity.Visitor;
import com.exceptions.FERSGenericException;
/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * 
 * An interface for defining and enforcing operations needed for the Visitor
 * Service Class. It provides the scope of possible database requests made
 * through the VisitorDAO.<br/>
 * 
 *  
 * 
 */
public interface VisitorFacade {

	public boolean createVisitor(Visitor visitor);

	public Visitor searchVisitor(String username, String password);

	public void RegisterVisitor(Visitor visitor, int eventid, int sessionid);

	public ArrayList<Object[]> showRegisteredEvents(Visitor visitor);

	public int updateVisitorDetails(Visitor visitor);

	public void unregisterEvent(Visitor visitor, int eventid, int eventsessionid);

	public int changePassword(Visitor visitor) throws FERSGenericException;
}
