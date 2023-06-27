package cl.araucana.adminCpe.presentation.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/*
* @(#) ListarEmpresasActionForm.java 1.5 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * @author jlandero
 * 
 * @version 1.5
 */
public class NotificacionSPLActionForm extends ActionForm
{
	private static final long serialVersionUID = -5585015647754230297L;
	private String idPagoSpl;
	
	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
	}

	public String getIdPagoSpl() {
		return idPagoSpl;
	}

	public void setIdPagoSpl(String idPagoSpl) {
		this.idPagoSpl = idPagoSpl;
	}
	
	}
