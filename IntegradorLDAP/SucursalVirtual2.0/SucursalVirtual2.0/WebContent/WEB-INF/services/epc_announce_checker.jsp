
<%@ page 
    language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="javax.servlet.*, javax.servlet.http.*, java.util.*, java.io.File, java.security.Principal"
%>

<%!
	/*
	 *  Checks is this application has announce text for users.
	 */

	private boolean hasAnnounceText(ServletContext application) {
		String path =
				application.getRealPath(
						"/WEB-INF/services/epc_announce_text.jsp");
			
		if (path != null) {
			File file = new File(path);
		
			return file.exists() && file.isFile() && file.canRead();
		}
		
		return false;
	}
%>

<%
	/*
	 *  Forwards users to announce text page once.
	 */
	 
	Map epcAnnoucements = null;
	Map epcCPAnnouncements = null;

	synchronized (this) {
		epcAnnoucements = (Map) application.getAttribute("epc.announcements");
		epcCPAnnouncements = (Map) application.getAttribute("epc.cp.announcements");
			
		if (epcAnnoucements == null) {
			epcAnnoucements = new HashMap();
			
			application.setAttribute("epc.announcements", epcAnnoucements);
		}

		if (epcCPAnnouncements == null) {
			epcCPAnnouncements = new HashMap();
			
			application.setAttribute("epc.cp.announcements", epcCPAnnouncements);
		}
	}

	Principal principal = (Principal) session.getAttribute("userPrincipal");
	
	if (principal == null) {	// Unexpected case.
		return;
	}
	
	String userName = principal.getName();

	if (userName == null) {		// Unexpected case.
		return;
	}

	String ts = request.getParameter("ts");

	if (ts == null) {	
		boolean needForward =
				checkForward(application, epcAnnoucements, userName);
	
		if (needForward) {
			long timestamp = System.currentTimeMillis();
			
			session.setAttribute("epc.timestamp", new Long(timestamp));
%>
			<jsp:forward page="/WEB-INF/services/epc_announce.jsp">
				<jsp:param name="ts" value="<%= timestamp %>" />
			</jsp:forward>	
<%
		}
	} else {
		Long sessionTS = (Long) session.getAttribute("epc.timestamp");

		if (sessionTS == null) {					// Unexpected case.
			return;
		}

		session.removeAttribute("epc.timestamp");

		long tsValue = 0L;
		
		try {
			tsValue = Long.parseLong(ts);
		} catch (NumberFormatException e) {			// Unexpected case.
			return;		
		}
		
		if (sessionTS.longValue() != tsValue) {		// Unexpected case.
			return;
		}

		/*
		 *  Adds current user to EPC Announcements.
		 */
		
		synchronized (epcAnnoucements) {
			if (hasAnnounceText(application)) {
				epcAnnoucements.put(userName, userName);
			}
		}
	}
%>


<%!
	private boolean checkForward(ServletContext application,
			Map epcAnnoucements, String userName) {
			
		synchronized (epcAnnoucements) {
			if (hasAnnounceText(application)) {
				boolean userAnnounced = epcAnnoucements.get(userName) != null;		
				
				if (!userAnnounced) {
					return true;
				}
			} else if (epcAnnoucements.size() > 0) {
				epcAnnoucements = new HashMap();
				
				application.setAttribute("epc.announcements", epcAnnoucements);
			}
			
			return false;
		}
	}
%>
