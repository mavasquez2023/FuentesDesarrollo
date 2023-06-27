

/*
 * @(#) ResourceManager.java    1.0 12-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.ondemand;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import cl.araucana.core.util.AbsoluteDateTime;
import cl.araucana.core.util.logging.LogManager;

import cl.araucana.pdfservice.PDFServiceException;

import cl.araucana.pdfservice.ondemand.entities.Address;
import cl.araucana.pdfservice.ondemand.entities.Assignment;
import cl.araucana.pdfservice.ondemand.entities.DocType;
import cl.araucana.pdfservice.ondemand.entities.Listener;
import cl.araucana.pdfservice.ondemand.entities.Resource;
import cl.araucana.pdfservice.ondemand.entities.ResourceType;
import cl.araucana.pdfservice.ondemand.entities.User;


/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 12-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class ResourceManager {

	private static final Logger logger = LogManager.getLogger();
	private static ResourceManager instance;
	
	private DataSource dataSource;
	private String schemaName;
	private boolean dump;
	
	private Connection connection;
	
	private Map usersByID;
	private Map usersByName;
	
	private Map docTypesByID;
	private Map docTypesByName;
	
	private Map resourceTypesByID;
	private Map resourceTypesByName;
	
	private Map resourcesByID;
	private Map resourcesByName;
	
	private Map listenersByID;
	
	private Map addressesByID;

	/*
	 * Assignments's maps:
	 * 		UserID				Map<userID,Map<Assignment,Assignment>>
	 * 		UserID_docTypeID	Map<userID.docTypeID,Assignment>
	 * 		ResourceID			Map<resourceID,Map<Assignment,Assignment>>
	 */	
	
	private Map assignmentsByUserID;
	private Map assignmentsByUserID_DocTypeID;
	private Map assignmentsByResourceID;
	
	private int nextResourceID;
	private int nextUserID;
	
	public static ResourceManager getInstance() {
		return instance;
	}
	
	public static void init(DataSource dataSource, String schemaName,
			boolean dump) throws PDFServiceException {
		
		ResourceManager instance =
				new ResourceManager(dataSource, schemaName, dump);
		
		logger.info("Loading ODPS data from schema '" + schemaName + "' ...");
		
		instance.loadAll();
		
		ResourceManager.instance = instance;
		
		logger.info("ODPS data loaded.");
	}
	
	private ResourceManager(DataSource dataSource, String schemaName,
			boolean dump) {
		
		this.dataSource = dataSource;
		this.schemaName = schemaName;
		this.dump = dump;
	}
	
	private void loadAll() throws PDFServiceException {
		
		cleanup();
		
		connection = null;
		
		try {
			connection = dataSource.getConnection();
			
			loadDocTypes();
			loadAddresses();
			loadListeners();
			loadResourceTypes();
			
			loadUsers();
			loadResources();
			loadAssigments();
		} catch (SQLException e) {
			String message = "ODPS data load failed";
			
			logger.log(Level.SEVERE, message + ":", e);
			
			throw new PDFServiceException(message, e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
	}
		
	private void cleanup() {
		usersByID = new TreeMap();
		usersByName = new TreeMap();

		docTypesByID = new TreeMap();
		docTypesByName = new TreeMap();

		resourceTypesByID = new TreeMap();
		resourceTypesByName = new TreeMap();

		resourcesByID = new TreeMap();
		resourcesByName = new TreeMap();

		listenersByID = new TreeMap();
		addressesByID = new TreeMap();

		assignmentsByUserID = new TreeMap();
		assignmentsByUserID_DocTypeID = new TreeMap();
		assignmentsByResourceID = new TreeMap();		
	}
	
	private void loadDocTypes() throws SQLException {
		
		String sql =
			  "SELECT id, name, description, classname"
			+ "  FROM " + tn("DOC_TYPE");

		Statement stmt = null;
		ResultSet rs = null;
	
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
	
			while (rs.next()) {
				DocType docType = new DocType();
				
				docType.setID(rs.getInt(1));
				docType.setName(rs.getString(2).trim());
				docType.setDescription(rs.getString(3).trim());
				docType.setClassName(rs.getString(4).trim());
				
				docTypesByID.put(new Integer(docType.getID()), docType);
				docTypesByName.put(docType.getName(), docType);				
			}
		} finally {
			release(stmt, rs);
		}
		
		loadedData("Document Types", docTypesByName);
	}
	
	private void loadAddresses() throws SQLException {
		
		String sql =
			  "SELECT id, servername, description"
			+ "  FROM " + tn("ADDRESS");

		Statement stmt = null;
		ResultSet rs = null;
	
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
	
			while (rs.next()) {
				Address address = new Address();
				
				address.setID(rs.getString(1).trim());
				address.setServerName(rs.getString(2).trim());
				address.setDescription(rs.getString(3).trim());
				
				addressesByID.put(address.getID(), address);
			}
		} finally {
			release(stmt, rs);
		}
	
		loadedData("Address", addressesByID);
	}
	
	private void loadListeners() throws SQLException {
		
		String sql =
			  "SELECT id, description, classname"
			+ "  FROM " + tn("LISTENER");

		Statement stmt = null;
		ResultSet rs = null;
	
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
	
			while (rs.next()) {
				Listener listener = new Listener();
				
				listener.setID(rs.getInt(1));
				listener.setDescription(rs.getString(2).trim());
				listener.setClassName(rs.getString(3).trim());
				
				listenersByID.put(new Integer(listener.getID()), listener);
			}
		} finally {
			release(stmt, rs);
		}
		
		loadedData("Listeners", listenersByID);
	}	
	
	private void loadResourceTypes() throws SQLException {
		
		String sql =
			  "SELECT id, name, description, classname"
			+ "  FROM " + tn("RESOURCE_TYPE");

		Statement stmt = null;
		ResultSet rs = null;
	
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
	
			while (rs.next()) {
				ResourceType resourceType = new ResourceType();
				
				resourceType.setID(rs.getInt(1));
				resourceType.setName(rs.getString(2).trim());
				resourceType.setDescription(rs.getString(3).trim());
				resourceType.setClassName(rs.getString(4).trim());
				
				resourceTypesByID.put(
						new Integer(resourceType.getID()), resourceType);
				
				resourceTypesByName.put(
						resourceType.getName(), resourceType);			
			}
		} finally {
			release(stmt, rs);
		}
		
		loadedData("Resource Types", resourceTypesByName);
	}
	
	private void loadUsers() throws SQLException {

		String sql =
			  "SELECT id, name, email"
			+ "  FROM " + tn("USER");

		int maxUserID = 0;
		Statement stmt = null;
		ResultSet rs = null;
	
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				User user = new User();
				int userID = rs.getInt(1);
				
				if (userID > maxUserID) {
					maxUserID = userID;
				}
				
				user.setID(userID);
				user.setName(rs.getString(2).trim());
				user.setEmail(rs.getString(3).trim());
				
				usersByID.put(new Integer(user.getID()), user);
				usersByName.put(user.getName(), user);			
			}
		} finally {
			release(stmt, rs);
		}
		
		nextUserID = maxUserID + 1;
		
		loadedData("Users", usersByName);
	}

	private void loadResources() throws SQLException {
	
		String sql =
			  "SELECT id, name, id_res_type, description,"
			+ "       address, params, extras"
			+ "  FROM " + tn("RESOURCE");

		int maxResourceID = 0;
		Statement stmt = null;
		ResultSet rs = null;
	
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
	
			while (rs.next()) {
				Resource resource = new Resource();
				int resourceID = rs.getInt(1);
				
				if (resourceID > maxResourceID) {
					maxResourceID = resourceID;
				}
				
				resource.setID(resourceID);				
				resource.setName(rs.getString(2).trim());
				
				ResourceType resourceType =
						(ResourceType)
								resourceTypesByID.get(
										new Integer(rs.getInt(3)));
						
				resource.setResourceType(resourceType);
				resource.setDescription(rs.getString(4).trim());
				resource.setAddress(rs.getString(5).trim());
				resource.setParams(rs.getString(6).trim());
				resource.setExtras(rs.getString(7).trim());
				
				resourcesByID.put(new Integer(resourceID), resource);
				resourcesByName.put(resource.getName(), resource);	
			}
		} finally {
			release(stmt, rs);
		}
		
		nextResourceID = maxResourceID + 1;
		
		loadedData("Resources", resourcesByName);
	}

	private void loadAssigments() throws SQLException {

		String sql =
			  "SELECT id_user, id_doc_type, id_resource"
			+ "  FROM " + tn("ASSIGNMENT");

		Statement stmt = null;
		ResultSet rs = null;
	
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
	
			while (rs.next()) {
				Integer userID = new Integer(rs.getInt(1));
				Integer docTypeID = new Integer(rs.getInt(2));
				Integer resourceID = new Integer(rs.getInt(3));
				
				User user = (User) usersByID.get(userID);
				DocType docType = (DocType) docTypesByID.get(docTypeID);
				Resource resource = (Resource) resourcesByID.get(resourceID);
				
				Assignment assignment = new Assignment();
				
				assignment.setUser(user);
				assignment.setDocType(docType);
				assignment.setResource(resource);
				
				Map assignments =
					(Map) assignmentsByUserID.get(userID);
				
				if (assignments == null) {
					assignments = new TreeMap();
				}
				
				assignments.put(assignment, assignment);
				assignmentsByUserID.put(userID, assignments);
				
				String composeKey = userID + "." + docTypeID;
				
				assignmentsByUserID_DocTypeID.put(composeKey, assignment);
				
				assignments = (Map) assignmentsByResourceID.get(resourceID);
				
				if (assignments == null) {
					assignments = new TreeMap();
				}
				
				assignments.put(assignment, assignment);
				assignmentsByResourceID.put(resourceID, assignments);
			}
		} finally {
			release(stmt, rs);
		}
		
		loadedData("Assignments by userID", assignmentsByUserID);
		
		loadedData(
				"Assignments by userID docTyeID",
				assignmentsByUserID_DocTypeID);
		
		loadedData("Assignments by resourceID", assignmentsByResourceID);
	}

	public Session getSession() throws PDFServiceException {
		
		return new Session();
	}
	
	private void loadedData(String title, Map map) {
		loadedData(title, map, true);
	}
	
	private void loadedData(String title, Map map, boolean dump) {
		if (!dump) {
			return;
		}
		
		Iterator iterator = map.values().iterator();
		
		logger.finest(title + ":");
		
		while (iterator.hasNext()) {
			logger.finest("    " + iterator.next());
		}
		
		logger.finest("");
	}
	
	private String tn(String name) {
		return schemaName + "." + "\"" + name + "\"";
	}
	
	private void release(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}
	}
	
	private void release(Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}
	}	
	
	public class Session {
		
		protected Connection connection;
		
		private Session() throws PDFServiceException {
		}

		private void open() throws PDFServiceException {
			
			if (connection != null) {
				return;
			}
			
			try {
				connection = dataSource.getConnection();
			} catch (SQLException e) {
				throw new PDFServiceException("Cannot get resource session");
			}
		}
		
		public ResourceType getResourceType(int id) {
			return (ResourceType) resourceTypesByID.get(new Integer(id));
		}
		
		public ResourceType getResourceType(String name) {
			return (ResourceType) resourceTypesByName.get(name);
		}
		
		public DocType getDocType(int id) {
			return (DocType) docTypesByID.get(new Integer(id));
		}
		
		public DocType getDocType(String name) {
			return (DocType) docTypesByName.get(name);
		}
		
		public Collection getDocTypeNames() {
			return docTypesByName.keySet();
		}
		
		public Listener getListener(int id) {
			return (Listener) listenersByID.get(new Integer(id));
		}
		
		public Address getAddress(String id) {
			return (Address) addressesByID.get(id);
		}
		
		public User getUser(int id) {
			return (User) usersByID.get(new Integer(id));
		}
		
		public User getUser(String name) {
			return (User) usersByName.get(name);
		}

		public Resource getResource(int id) {
			return (Resource) resourcesByID.get(new Integer(id));
		}
		
		public Resource getResource(String name) {
			return (Resource) resourcesByName.get(name);
		}
		
		public Map getAssignments(User user) {
			Integer userID = new Integer(user.getID());
			
			return (Map) assignmentsByUserID.get(userID);
		}
		
		public Map getAssignments(Resource resource) {
			Integer resourceID = new Integer(resource.getID());
			
			return (Map) assignmentsByResourceID.get(resourceID);
		}
		
		public Assignment getAssignment(User user, DocType docType) {
			return getAssignment(user.getID(), docType.getID());
		}
		
		public Assignment getAssignment(int userID, int docTypeID) {
			String key = userID + "." + docTypeID;
			
			return (Assignment) assignmentsByUserID_DocTypeID.get(key);
		}	
		
		public void addUser(User user) throws PDFServiceException {
			
			String userName = user.getName();
			
			if (usersByName.get(userName) != null) {
				throw new PDFServiceException(
						"User '" + userName + "' duplicated");
			}
			
			open();
			
			Integer userID = new Integer(getNewUserID());
			
			user.setID(userID.intValue());
			
			String sql =
				  "INSERT INTO " + tn("USER")
				+ "       VALUES(?,?,?)";

			PreparedStatement stmt = null;
		
			try {
				stmt = connection.prepareStatement(sql);
				
				stmt.setInt(1, user.getID());
				stmt.setString(2, user.getName());
				stmt.setString(3, user.getEmail());
				
				int nAffectedRows = stmt.executeUpdate();
				
				if (nAffectedRows != 1) {
					throw new SQLException(
							  "Unexpected #affected rows "
							+ "'" + nAffectedRows + "'");
				}
			} catch (SQLException e) {
				String message = "Cannot add user '" + user.getName() + "'";
				
				logger.log(Level.SEVERE, message + ":", e);
				
				throw new PDFServiceException(message, e);
			} finally {
				release(stmt);
			}
			
			usersByID.put(userID, user);
			usersByName.put(user.getName(), user);
			
			logger.info(user.toString());
			
			loadedData("*Users [ID]", usersByID, dump);
			loadedData("*Users [Name]", usersByName, dump);
		}
		
		public void addResource(Resource resource) throws PDFServiceException {
			
			String resourceName = resource.getName();
			
			if (resourcesByName.get(resourceName) != null) {
				throw new PDFServiceException(
						"Resource '" + resourceName + "' duplicated");
			}
			
			open();
			
			Integer resourceID = new Integer(getNewResourceID());
			
			resource.setID(resourceID.intValue());
			
			String sql =
				  "INSERT INTO " + tn("RESOURCE")
				+ "       VALUES(?,?,?,?,?,?,?)";
			
			PreparedStatement stmt = null;
			
			try {
				stmt = connection.prepareStatement(sql);
				
				stmt.setInt(1, resource.getID());
				stmt.setString(2, resource.getName());
				stmt.setInt(3, resource.getResourceType().getID());
				stmt.setString(4, resource.getDescription());
				stmt.setString(5, resource.getAddress());
				stmt.setString(6, resource.getParams());
				stmt.setString(7, resource.getExtras());
				
				int nAffectedRows = stmt.executeUpdate();
				
				if (nAffectedRows != 1) {
					throw new SQLException(
							  "Unexpected #affected rows "
							+ "'" + nAffectedRows + "'");
				}
			} catch (SQLException e) {
				String message =
						"Cannot add resource '" + resource.getName() + "'";
				
				logger.log(Level.SEVERE, message + ":", e);
				
				throw new PDFServiceException(message, e);
			} finally {
				release(stmt);
			}

			resourcesByID.put(resourceID, resource);
			resourcesByName.put(resource.getName(), resource);
			
			logger.info(resource.toString());
			
			loadedData("*Resources [ID]", resourcesByID, dump);
			loadedData("*Resources [name]", resourcesByName, dump);
		}
		
		public void updateResource(Resource resource)
				throws PDFServiceException {
			
			Integer resourceID = new Integer(resource.getID());
			
			if (resourcesByID.get(resourceID) == null) {
				throw new PDFServiceException(
						"Resource '" + resourceID + "' not found");
			}
			
			open();
			
			String sql =
				  "UPDATE " + tn("RESOURCE")
				+ "   SET id_res_type = ?,"
				+ "       description = ?,"
				+ "       address = ?,"
				+ "       params = ?,"
				+ "       extras = ?"
				+ " WHERE id = ?";
			
			PreparedStatement stmt = null;
			
			try {
				stmt = connection.prepareStatement(sql);
				
				stmt.setInt(1, resource.getResourceType().getID());
				stmt.setString(2, resource.getDescription());
				stmt.setString(3, resource.getAddress());
				stmt.setString(4, resource.getParams());
				stmt.setString(5, resource.getExtras());
				stmt.setInt(6, resourceID.intValue());
				
				int nAffectedRows = stmt.executeUpdate();
				
				if (nAffectedRows != 1) {
					throw new SQLException(
							  "Unexpected #affected rows "
							+ "'" + nAffectedRows + "'");
				}
			} catch (SQLException e) {
				String message =
						"Cannot update resource '" + resource.getName() + "'";
				
				logger.log(Level.SEVERE, message + ":", e);
				
				throw new PDFServiceException(message, e);
			} finally {
				release(stmt);
			}

			resourcesByID.put(resourceID, resource);
			resourcesByName.put(resource.getName(), resource);
			
			logger.info(resource.toString());
			
			loadedData("*Resources [ID]", resourcesByID, dump);
			loadedData("*Resources [name]", resourcesByName, dump);
		}		
		
		public void removeResource(Resource resource)
				throws PDFServiceException {
		
			Integer resourceID = new Integer(resource.getID());
			
			if (resourcesByID.get(resourceID) == null) {
				return;
			}
			
			open();
			
			String sql =
				  "DELETE FROM " + tn("RESOURCE")
				+ " WHERE id = ?";
			
			PreparedStatement stmt = null;
			
			try {
				stmt = connection.prepareStatement(sql);
				
				stmt.setInt(1, resourceID.intValue());
				
				int nAffectedRows = stmt.executeUpdate();
				
				if (nAffectedRows != 1) {
					throw new SQLException(
							  "Unexpected #affected rows "
							+ "'" + nAffectedRows + "'");
				}
			} catch (SQLException e) {
				String message =
						"Cannot remove resource '" + resource.getName() + "'";
				
				logger.log(Level.SEVERE, message + ":", e);
				
				throw new PDFServiceException(message, e);
			} finally {
				release(stmt);
			}
			
			resourcesByID.remove(new Integer(resource.getID()));
			resourcesByName.remove(resource.getName());
			
			logger.info(resource.toString());
			
			loadedData("*Resources [ID]", resourcesByID, dump);
			loadedData("*Resources [name]", resourcesByName, dump);		
		}	
		
		public void addAssignment(User user, DocType docType, Resource resource,
				Timestamp registeredAt) throws PDFServiceException {
			
			Integer userID = new Integer(user.getID());
			Integer docTypeID = new Integer(docType.getID());
			Integer resourceID = new Integer(resource.getID());
			
			String composeKey = userID + "." + docTypeID;
			
			Assignment assignment =
					(Assignment) assignmentsByUserID_DocTypeID.get(composeKey);
			
			if (assignment != null) {
				
				/*
				 * Checks if another resource is assigned.
				 */
				if (assignment.getResource().getID() != resourceID.intValue()) {
					return;
				}
			}

			open();
			
			String sql =
				  "INSERT INTO " + tn("ASSIGNMENT")
				+ "       VALUES(?,?,?,?)";
			
			PreparedStatement stmt = null;
			
			try {
				stmt = connection.prepareStatement(sql);
				
				stmt.setInt(1, userID.intValue());
				stmt.setInt(2, docTypeID.intValue());
				stmt.setInt(3, resourceID.intValue());
				stmt.setTimestamp(4, registeredAt);
				
				int nAffectedRows = stmt.executeUpdate();
				
				if (nAffectedRows != 1) {
					throw new SQLException(
							  "Unexpected #affected rows "
							+ "'" + nAffectedRows + "'");
				}
			} catch (SQLException e) {
				String message =
						  "Cannot add assignment "
						+ "(" + user.getName() + "," + docType.getName() + ")";					
				
				logger.log(Level.SEVERE, message + ":", e);
				
				throw new PDFServiceException(message, e);
			} finally {
				release(stmt);
			}
			
			logger.info(
					  "(" + user.getName() + "," + docType.getName() + ") "
					+ "--> " + resource.getName() + " "
					+ "[" + resource.getResourceType().getName() + "].");
			
			Map assignments = (Map) assignmentsByUserID.get(userID);
			
			if (assignments == null) {
				assignments = new TreeMap();
			}
			
			assignment =
					new Assignment(
							user,
							docType,
							resource,
							new AbsoluteDateTime(registeredAt));
			
			assignments.put(assignment, assignment);
			assignmentsByUserID.put(userID, assignments);
			
			loadedData("*Assigments User[" + userID + "]", assignments, dump);
			
			assignmentsByUserID_DocTypeID.put(composeKey, assignment);
			
			assignments = (Map) assignmentsByResourceID.get(resourceID);
			
			if (assignments == null) {
				assignments = new TreeMap();
			}
			
			assignments.put(assignment, assignment);
			assignmentsByResourceID.put(resourceID, assignments);
			
			loadedData(
					"*Assigments Resource[" + resourceID + "]",
					assignments,
					dump);
		}
		
		public void removeAssignment(User user, DocType docType)
				throws PDFServiceException {
			
			Integer userID = new Integer(user.getID());
			Integer docTypeID = new Integer(docType.getID());
			
			String composeKey = userID + "." + docTypeID;
			
			Assignment assignment =
					(Assignment) assignmentsByUserID_DocTypeID.get(composeKey);
			
			if (assignment == null) {
				return;
			}
			
			open();
			
			String sql =
				  "DELETE FROM " + tn("ASSIGNMENT")
				+ " WHERE id_user = ?"
				+ "   AND id_doc_type = ?";
			
			PreparedStatement stmt = null;
			
			try {
				stmt = connection.prepareStatement(sql);
				
				stmt.setInt(1, userID.intValue());
				stmt.setInt(2, docTypeID.intValue());
				
				int nAffectedRows = stmt.executeUpdate();
				
				if (nAffectedRows != 1) {
					throw new SQLException(
							  "Unexpected #affected rows "
							+ "'" + nAffectedRows + "'");
				}
			} catch (SQLException e) {
				String message =
						  "Cannot remove assignment "
						+ "(" + user.getName() + "," + docType.getName() + ")";
				
				logger.log(Level.SEVERE, message + ":", e);
				
				throw new PDFServiceException(message, e);
			} finally {
				release(stmt);
			}
			
			assignmentsByUserID_DocTypeID.remove(composeKey);
			
			Map assignments = (Map) assignmentsByUserID.get(userID);
			
			assignments.remove(assignment);
			
			Integer resourceID = new Integer(assignment.getResource().getID());
			
			assignments = (Map) assignmentsByResourceID.get(resourceID);
			
			assignments.remove(assignment);
			
			loadedData(
					"*Assigments User[" + userID + "]",
					(Map) assignmentsByUserID.get(userID),
					dump);
		}
		
		public List getAssignments(ResourceType resourceType)
				throws PDFServiceException {
			
			open();
			
			int resourceTypeID = resourceType.getID();
			
			String sql =
				    "SELECT id_user, id_doc_type, id_resource, registeredAt"
	              + "  FROM " + tn("ASSIGNMENT") + " a,"
	              + "       " + tn("RESOURCE") + " r"
	              + " WHERE a.ID_RESOURCE = r.ID"
	              + "   AND r.id_res_type = " + resourceTypeID;		  

			PreparedStatement stmt = null;
			ResultSet rs = null;
			List assignments = new ArrayList();
		
			try {
				stmt = connection.prepareStatement(sql);
				rs = stmt.executeQuery();
		
				while (rs.next()) {
					Integer userID = new Integer(rs.getInt(1));
					Integer docTypeID = new Integer(rs.getInt(2));
					Integer resourceID = new Integer(rs.getInt(3));
					Date registeredAt = (Date) rs.getTimestamp(4);
					
					User user = (User) usersByID.get(userID);
					DocType docType = (DocType) docTypesByID.get(docTypeID);
					
					Resource resource =
							(Resource) resourcesByID.get(resourceID);
					
					if (user == null || docType == null || resource == null) {
						logger.warning(
								  "Data was manipulated externally. "
								+ "It will be ignored.");
						
						continue;
					}
					
					Assignment assignment = new Assignment();
					
					assignment.setUser(user);
					assignment.setDocType(docType);
					assignment.setResource(resource);
					
					assignment.setRegisteredAt(
							new AbsoluteDateTime(registeredAt));
					
					assignments.add(assignment);
				}
			} catch (SQLException e) {
				String message =
					  "Cannot get assignments to resource type "
					+ "'" + resourceTypeID + "'";
			
				logger.log(Level.SEVERE, message + ":", e);
			
				throw new PDFServiceException(message, e);				
			} finally {
				release(stmt, rs);
			}
			
			return assignments;
		}
		
		public void close() {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
				
				connection = null;
			}
		}
		
		private synchronized int getNewResourceID() throws PDFServiceException {
			
			return nextResourceID++;
		}

		private synchronized int getNewUserID() throws PDFServiceException {
			
			return nextUserID++;
		}		
	}
}
