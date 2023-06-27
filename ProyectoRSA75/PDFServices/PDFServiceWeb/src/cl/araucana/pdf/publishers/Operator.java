

package cl.araucana.pdf.publishers;


/*
 * @(#) Operator.java    1.0 24-05-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


/**
 * This class represents all supported operators for a {@link Scope} instance.
 * These are a subset of <i>SQL</i> supported operators.
 *
 * <BR>
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
 *            <TD> 24-05-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germ�n Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versi�n inicial. </TD>
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
public class Operator {

	/**
	 * <b>EQ</b>uals operator. 
	 */
	public static final Operator EQ = new Operator("=");
	
	/**
	 * <b>N</b>ot <b>E</b>quals operator. 
	 */	
	public static final Operator NE = new Operator("<>");
	
	/**
	 * <b>G</b>reater or <b>E</b>quals operator 
	 */		
	public static final Operator GE = new Operator(">=");
	
	/**
	 * <b>G</b>reater <b>T</b>han operator.  
	 */	
	public static final Operator GT = new Operator(">");

	/**
	 * <b>L</b>ess or <b>E</b>quals operator.  
	 */	
	public static final Operator LE = new Operator("<=");
	
	/**
	 * <b>L</b>ess <b>T</b>han operator. 
	 */		
	public static final Operator LT = new Operator("<");
	
	/**
	 * <b>BETWEEN</b> operator. 
	 */		
	public static final Operator BETWEEN = new Operator("BETWEEN", 3);
	
	/**
	 * <b>LIKE</b> operator. 
	 */		
	public static final Operator LIKE = new Operator("LIKE");
	
	/**
	 * <b>IN</b> operator. 
	 */		
	public static final Operator IN = new Operator("IN", 0);

	private String symbol;
	private int operands;

	private Operator(String symbol) {
		this(symbol, 2);
	}

	private Operator(String symbol, int operands) {
		this.symbol = symbol;
		this.operands = operands;
	}

	/**
	 * Gets operator symbol.
	 * 
	 * @return Operator symbol.
	 */
	public String symbol() {
		return symbol;
	}

	/**
	 * Gets number of operator operands.
	 * 
	 * @return Number of operator operands.
	 */
	public int operands() {
	   return operands;
	}

	/**
	 * Returns a string representation of the operator as symbol and number
	 * of operands.
	 * 
	 * @return String representation.
	 */		
	public String toString() {
		return symbol + " [" + operands + "]";
	}
}
