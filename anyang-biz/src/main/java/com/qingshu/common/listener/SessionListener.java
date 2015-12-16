package com.qingshu.common.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.qingshu.common.util.SessionManager;


/**
 * 
 * 监听session
 * 
 * Application Lifecycle Listener implementation class SessionListener
 * 
 * 
 */

public class SessionListener implements HttpSessionListener {

	/**
	 * 
	 * @author Long
	 * 
	 * @dateTime 2012-3-31 下午12:00:33
	 */

	public static Map<String, HttpSession> userMap = new HashMap<String, HttpSession>();

	/**
	 * 
	 * @author Long
	 * 
	 * @dateTime 2012-3-31 下午12:00:29
	 */

	private SessionManager sessionManager = SessionManager.getInstance();

	/**
	 * 
	 * Default constructor.
	 */

	public SessionListener() {


	}

	/**
	 * 
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */

	public void sessionCreated(HttpSessionEvent se) {


		sessionManager.AddSession(se.getSession());

	}

	/**
	 * 
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */

	public void sessionDestroyed(HttpSessionEvent se) {


		sessionManager.DelSession(se.getSession());

	}

}
