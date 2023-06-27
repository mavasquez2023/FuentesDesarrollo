/*
 * Creada el 03-04-2008
 *
 */
package cl.araucana.cierrecpe.empresas.planillas.afp;

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
public class PlanillaAfpPaginaDetalle extends PlanillaBasePaginaDetalle {

	
	private List cotizantes;
	private PlanillaAfpDocumentModel cabeceraPlanilla;
	    
    
    public void addCotizante(PlanillaAfpCotizante cotizante){
    	if (cotizantes==null){
    		cotizantes= new ArrayList();
    	}
    	cotizantes.add(cotizante);
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

	/**
	 * @return el cabeceraPlanilla
	 */
	public PlanillaBase getCabeceraPlanilla() {
		return cabeceraPlanilla;
	}


	/**
	 * @param cabeceraPlanilla el cabeceraPlanilla a establecer
	 */
	public void setCabeceraPlanilla(PlanillaAfpDocumentModel cabeceraPlanilla) {
		this.cabeceraPlanilla = cabeceraPlanilla;
	}

    
}
