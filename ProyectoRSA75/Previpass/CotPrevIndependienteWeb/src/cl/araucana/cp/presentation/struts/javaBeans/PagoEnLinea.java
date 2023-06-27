package cl.araucana.cp.presentation.struts.javaBeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
/*
* @(#) PagoEnLinea.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.4
 */
public class PagoEnLinea implements Serializable
{
	private static final long serialVersionUID = 6069086589880448458L;
	private String glosa;
	private String pagador;
	private String correlativo;
	private Date fecha;
	private String urlRetorno;
	private String urlNotificacion;
	private String bancosXml;
	private String result;
	private List folios;
	
	private String rutCliente;

	public PagoEnLinea()
	{
		super();
	}
	/**
	 * pago en linea
	 * @param glosa
	 * @param pagador
	 * @param urlRetorno
	 * @param urlNotificacion
	 */
	public PagoEnLinea(String glosa, String pagador, String urlRetorno, String urlNotificacion)
	{
		super();
		this.glosa = glosa;
		this.pagador = pagador;
		this.urlRetorno = urlRetorno;
		this.urlNotificacion = urlNotificacion;
		this.rutCliente = pagador;
	}
	/**
	 * pago en linea
	 * @param glosa
	 * @param pagador
	 * @param correlativo
	 * @param fecha
	 * @param urlRetorno
	 * @param urlNotificacion
	 * @param bancosXml
	 * @param folios
	 */
	public PagoEnLinea(String glosa, String pagador, String correlativo, Date fecha, String urlRetorno, String urlNotificacion, String bancosXml, List folios)
	{
		super();
		this.glosa = glosa;
		this.pagador = pagador;
		this.correlativo = correlativo;
		this.fecha = fecha;
		this.urlRetorno = urlRetorno;
		this.urlNotificacion = urlNotificacion;
		this.bancosXml = bancosXml;
		this.folios = folios;
		this.rutCliente = pagador;
	}
	/**
	 * agrega folio
	 * @param folio
	 */
	public void addFolio(Folio folio)
	{
		if (this.folios == null)
			this.folios = new ArrayList();
		this.folios.add(folio);
	}
	/**
	 * bancos xml
	 * @return
	 */
	public String getBancosXml()
	{
		return this.bancosXml;
	}
	/**
	 * bancos
	 * @param bancosXml
	 */
	public void setBancos(String bancosXml)
	{
		this.bancosXml = bancosXml;
	}
	/**
	 * correlativo
	 * @return
	 */
	public String getCorrelativo()
	{
		return this.correlativo;
	}
	/**
	 * correlativo
	 * @param correlativo
	 */
	public void setCorrelativo(String correlativo)
	{
		this.correlativo = correlativo;
	}
	/**
	 * fecha
	 * @return
	 */
	public Date getFecha()
	{
		return this.fecha;
	}
	/**
	 * fecha
	 * @param fecha
	 */
	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}
	/**
	 * folios
	 * @return
	 */
	public List getFolios()
	{
		return this.folios;
	}
	/**
	 * folios
	 * @param folios
	 */
	public void setFolios(List folios)
	{
		this.folios = folios;
	}
	/**
	 * glosa
	 * @return
	 */
	public String getGlosa()
	{
		return this.glosa;
	}
	/**
	 * glosa
	 * @param glosa
	 */
	public void setGlosa(String glosa)
	{
		this.glosa = glosa;
	}
	/**
	 * pagador
	 * @return
	 */
	public String getPagador()
	{
		return this.pagador;
	}
	/**
	 * pagador
	 * @param rutEmpresa
	 */
	public void setPagador(String rutEmpresa)
	{
		this.pagador = rutEmpresa;
	}
	/**
	 * url notificacion
	 * @return
	 */
	public String getUrlNotificacion()
	{
		return this.urlNotificacion;
	}
	/**
	 * url notificacion
	 * @param urlNotificacion
	 */
	public void setUrlNotificacion(String urlNotificacion)
	{
		this.urlNotificacion = urlNotificacion;
	}
	/**
	 * url retorno
	 * @return
	 */
	public String getUrlRetorno()
	{
		return this.urlRetorno;
	}
	/**
	 * url retorno
	 * @param urlRetorno
	 */
	public void setUrlRetorno(String urlRetorno)
	{
		this.urlRetorno = urlRetorno;
	}

	/**
	 * xml
	 * @return
	 */
	public String toXml()
	{
		StringBuffer sb =  new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		sb.append("<pago>\n");
		sb.append("<glosa>" + this.glosa + "</glosa>\n");
		sb.append("<pagador>" + this.pagador + "</pagador>\n");
		sb.append("<correlativo>CP" + Math.random() + "</correlativo>\n");
		sb.append("<fecha>" + sdf.format(new Date(System.currentTimeMillis())) + "</fecha>\n");
		sb.append("<urlRetorno>" + this.urlRetorno.trim() + "</urlRetorno>\n");
		sb.append("<urlNotificacion>" + this.urlNotificacion.trim() + "</urlNotificacion>\n");
		sb.append("<rutCliente>" + this.rutCliente.trim() + "</rutCliente>\n");
		sb.append("<bancos>\n");
		sb.append(this.bancosXml + "\n");
		sb.append("</bancos>\n");
		sb.append("<folios>\n");
		for (Iterator it = this.folios.iterator(); it.hasNext();)
			sb.append(((Folio)it.next()).toXml() + "\n");
		sb.append("</folios>\n");
		sb.append("</pago>\n");
		return sb.toString();
	}
	/**
	 * result
	 * @return
	 */
	public String getResult()
	{
		return this.result;
	}
	/**
	 * result
	 * @param result
	 */
	public void setResult(String result)
	{
		this.result = result;
	}
	public String getRutCliente() {
		return rutCliente;
	}
	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}
}
