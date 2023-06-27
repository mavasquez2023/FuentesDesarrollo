

/*
 * @(#) Assigment.java    1.0 12-08-2009
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdfservice.ondemand.entities;

import cl.araucana.core.util.AbsoluteDateTime;


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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 12-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germ�n Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versi�n inicial. </TD>
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
 * @author Germ�n Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class Assignment implements Comparable {

	private User user;
	private DocType docType;
	private Resource resource;
	private AbsoluteDateTime registeredAt;
	
	public Assignment() {
	}

	public Assignment(User user, DocType docType, Resource resource,
			AbsoluteDateTime registeredAt) {
		
		this.user = user;
		this.docType = docType;
		this.resource = resource;
		this.registeredAt = registeredAt;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public DocType getDocType() {
		return docType;
	}

	public void setDocType(DocType docType) {
		this.docType = docType;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	public void setRegisteredAt(AbsoluteDateTime aDateTime) {
		registeredAt = aDateTime;
	}
	
	public AbsoluteDateTime getRegisteredAt() {
		return registeredAt;
	}
	
	public boolean equals(Object o) {
		return compareTo(o) == 0;
	}
	
	public int compareTo(Object o) {
		if (o == null) {
			return +1;
		}
		
		if (!(o instanceof Assignment)) {
			throw new IllegalArgumentException();
		}
		
		Assignment assignment = (Assignment) o;
		int cmp = user.getID() - assignment.user.getID();
		
		if (cmp != 0) {
			return cmp;
		}
		
		cmp = docType.getID() - assignment.docType.getID();
	
		if (cmp != 0) {
			return cmp;
		}
		
		cmp = resource.getID() - assignment.resource.getID();
		
		return cmp;
	}
	
	public String toString() {
		return
				  "user=" + user.getName() + " "
				+ "docType=" + docType.getName() + " "
				+ "resource=" + resource.getName() + " "
				+ "registeredAt=" + registeredAt; 
	}	
}
