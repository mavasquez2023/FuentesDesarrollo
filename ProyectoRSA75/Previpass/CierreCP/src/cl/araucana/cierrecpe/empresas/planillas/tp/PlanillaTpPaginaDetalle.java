

/*
 * @(#) PlanillaTpPaginaDetalle.java    1.0 11-ago-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.planillas.tp;

import java.util.ArrayList;
import java.util.List;

import cl.araucana.cierrecpe.empresas.planillas.PlanillaBase;
import cl.araucana.cierrecpe.empresas.planillas.PlanillaBasePaginaDetalle;




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
 *            <TD> 05-05-2008 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Claudio Lillo <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Claudio Lillo (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class PlanillaTpPaginaDetalle extends PlanillaBasePaginaDetalle{

	
	private List cotizantes;
	private PlanillaTpDocumentModel cabeceraPlanilla;
	
	public void addCotizante(PlanillaTpCotizante cotizante){
    	if (cotizantes==null){
    		cotizantes= new ArrayList();
    	}
    	cotizantes.add(cotizante);
    }


	/**
	 * @return el cabeceraPlanilla
	 */
	public PlanillaBase getCabeceraPlanilla() {
		return cabeceraPlanilla;
	}


	/**
	 * @param cabeceraPlanilla el cabeceraPlanilla a establecer
	 */
	public void setCabeceraPlanilla(PlanillaTpDocumentModel cabeceraPlanilla) {
		this.cabeceraPlanilla = cabeceraPlanilla;
	}


	/**
	 * @return el cotizantes
	 */
	public List getCotizantes() {
		return cotizantes;
	}


	/**
	 * @param cotizantes el cotizantes a establecer
	 */
	public void setCotizantes(List cotizantes) {
		this.cotizantes = cotizantes;
	}    
    
}