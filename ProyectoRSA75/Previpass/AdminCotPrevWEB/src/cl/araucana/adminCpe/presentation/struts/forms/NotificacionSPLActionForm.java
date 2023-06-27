package cl.araucana.adminCpe.presentation.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/*
* @(#) ListarEmpresasActionForm.java 1.5 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
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
