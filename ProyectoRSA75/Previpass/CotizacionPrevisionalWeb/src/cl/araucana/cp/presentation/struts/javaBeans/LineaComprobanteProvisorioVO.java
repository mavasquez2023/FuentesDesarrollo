package cl.araucana.cp.presentation.struts.javaBeans;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;

/*
 * @(#) LineaComprobanteProvisorioVO.java 1.8 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.8
 */
public class LineaComprobanteProvisorioVO
{

	// Llave primaria de la seccion o el detalle
	private long IdCodigoBarra;
	private int IdTipoSeccion, IdTipoDetalle;

	private String glosa;
	private long total;
	private int tipoPago;
	private Boolean pagableIndividual, dnp;
	private List detalle;
	private boolean mostrar3;

	private boolean chkP, chkDNP, chkNP;
	
	private String sfe;
	private String sfi;

	public int getHash()
	{
		return this.hashCode();
	}

	public LineaComprobanteProvisorioVO()
	{
		this.IdCodigoBarra = 0;
		this.IdTipoSeccion = 0;
		this.IdTipoDetalle = 0;
		this.chkP = false;
		this.chkDNP = false;
		this.chkNP = false;
		this.mostrar3 = false;
	}

	/**
	 * linea comprobante provisorio vo
	 * 
	 * @param glosa
	 * @param total
	 * @param tipoPago
	 * @param pagableIndividual
	 */
	public LineaComprobanteProvisorioVO(String glosa, long total, int tipoPago, boolean pagableIndividual)
	{
		this.IdCodigoBarra = 0;
		this.IdTipoSeccion = 0;
		this.IdTipoDetalle = 0;
		this.chkP = false;
		this.chkDNP = false;
		this.chkNP = false;
		this.mostrar3 = false;

		this.glosa = glosa;
		this.total = total;
		this.tipoPago = tipoPago;
		this.detalle = new ArrayList();
		this.pagableIndividual = new Boolean(pagableIndividual);
	}

	/**
	 * linea comprobante provisorio vo
	 * 
	 * @param IdCodigoBarra
	 * @param IdTipoSeccion
	 * @param IdTipoDetalle
	 */
	public LineaComprobanteProvisorioVO(long IdCodigoBarra, int IdTipoSeccion, int IdTipoDetalle)
	{
		this.IdCodigoBarra = IdCodigoBarra;
		this.IdTipoSeccion = IdTipoSeccion;
		this.IdTipoDetalle = IdTipoDetalle;
		this.chkP = false;
		this.chkDNP = false;
		this.chkNP = false;
		this.mostrar3 = false;
	}

	/**
	 * linea comprobante provisorio vo
	 * 
	 * @param w
	 */
	public LineaComprobanteProvisorioVO(String w)
	{
		this.IdCodigoBarra = 0;
		this.IdTipoSeccion = 0;
		this.IdTipoDetalle = 0;
		this.chkP = false;
		this.chkDNP = false;
		this.chkNP = false;
		this.mostrar3 = false;

		this.detalle = ListUtils.lazyList(new ArrayList(), new Factory()
		{
			public Object create()
			{
				return new LineaComprobanteProvisorioVO("");
			}
		});
	}

	/**
	 * linea comprobante provisorio vo
	 */
	public String toString()
	{

		return "LineaComprobanteProvisorioVO[idCodigoBarra: " + this.IdCodigoBarra + ", idTipoSeccion: " + this.IdTipoSeccion + ", idTipoDetalle: " + this.IdTipoDetalle + ", glosa: \""
				+ this.getGlosa() + "\", total: " + this.getTotal() + ", tipoPago: " + this.getTipoPago() + ", pagableIndividual: " + this.getPagableIndividual() + ", DNP: " + this.getDnp() + "]";
	}

	/**
	 * dnp
	 * 
	 * @return
	 */
	public Boolean getDnp()
	{
		return this.dnp;
	}

	/**
	 * dnp
	 * 
	 * @param dnp
	 */
	public void setDnp(Boolean dnp)
	{
		this.dnp = dnp;
	}

	/**
	 * glosa
	 * 
	 * @return
	 */
	public String getGlosa()
	{
		return this.glosa;
	}

	/**
	 * glosa
	 * 
	 * @param glosa
	 */
	public void setGlosa(String glosa)
	{
		this.glosa = glosa;
	}

	/**
	 * pagable individual
	 * 
	 * @return
	 */
	public Boolean getPagableIndividual()
	{
		return this.pagableIndividual;
	}

	/**
	 * pagable individual
	 * 
	 * @param pagableIndividual
	 */
	public void setPagableIndividual(Boolean pagableIndividual)
	{
		this.pagableIndividual = pagableIndividual;
	}

	/**
	 * tipo pago
	 * 
	 * @return
	 */
	public int getTipoPago()
	{
		return this.tipoPago;
	}

	/**
	 * tipo pago
	 * 
	 * @param tipoPago
	 */
	public void setTipoPago(int tipoPago)
	{
		this.tipoPago = tipoPago;
	}

	/**
	 * total
	 * 
	 * @return
	 */
	public long getTotal()
	{
		return this.total;
	}

	/**
	 * total
	 * 
	 * @param total
	 */
	public void setTotal(long total)
	{
		this.total = total;
	}

	/**
	 * detalle
	 * 
	 * @return
	 */
	public List getDetalle()
	{
		return this.detalle;
	}

	/**
	 * detalle
	 * 
	 * @param detalle
	 */
	public void setDetalle(List detalle)
	{
		this.detalle = detalle;
	}

	/**
	 * id codigo barra
	 * 
	 * @return
	 */
	public long getIdCodigoBarra()
	{
		return this.IdCodigoBarra;
	}

	/**
	 * id codigo barra
	 * 
	 * @param idCodigoBarra
	 */
	public void setIdCodigoBarra(long idCodigoBarra)
	{
		this.IdCodigoBarra = idCodigoBarra;
	}

	/**
	 * id tipo detalle
	 * 
	 * @return
	 */
	public int getIdTipoDetalle()
	{
		return this.IdTipoDetalle;
	}

	/**
	 * id tipo detalle
	 * 
	 * @param idTipoDetalle
	 */
	public void setIdTipoDetalle(int idTipoDetalle)
	{
		this.IdTipoDetalle = idTipoDetalle;
	}

	/**
	 * id tipo seccion
	 * 
	 * @return
	 */
	public int getIdTipoSeccion()
	{
		return this.IdTipoSeccion;
	}

	/**
	 * id tipo seccion
	 * 
	 * @param idTipoSeccion
	 */
	public void setIdTipoSeccion(int idTipoSeccion)
	{
		this.IdTipoSeccion = idTipoSeccion;
	}

	/**
	 * hash code
	 */
	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (this.IdCodigoBarra ^ (this.IdCodigoBarra >>> 32));
		result = PRIME * result + this.IdTipoDetalle;
		result = PRIME * result + this.IdTipoSeccion;
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final LineaComprobanteProvisorioVO other = (LineaComprobanteProvisorioVO) obj;
		if (this.IdCodigoBarra != other.IdCodigoBarra)
			return false;
		if (this.IdTipoDetalle != other.IdTipoDetalle)
			return false;
		if (this.IdTipoSeccion != other.IdTipoSeccion)
			return false;
		return true;
	}

	/**
	 * chk dnp
	 * 
	 * @return
	 */
	public boolean isChkDNP()
	{
		return this.chkDNP;
	}

	/**
	 * chk dnp
	 * 
	 * @param chkDNP
	 */
	public void setChkDNP(boolean chkDNP)
	{
		this.chkDNP = chkDNP;
	}

	/**
	 * chk np
	 * 
	 * @return
	 */
	public boolean isChkNP()
	{
		return this.chkNP;
	}

	/**
	 * chk np
	 * 
	 * @param chkNP
	 */
	public void setChkNP(boolean chkNP)
	{
		this.chkNP = chkNP;
	}

	/**
	 * ch kp
	 * 
	 * @return
	 */
	public boolean isChkP()
	{
		return this.chkP;
	}

	/**
	 * chkp
	 * 
	 * @param chkP
	 */
	public void setChkP(boolean chkP)
	{
		this.chkP = chkP;
	}

	/**
	 * mostrar 3
	 * 
	 * @return
	 */
	public boolean isMostrar3()
	{
		return this.mostrar3;
	}

	/**
	 * mostrar 3
	 * 
	 * @param mostrar3
	 */
	public void setMostrar3(boolean mostrar3)
	{
		this.mostrar3 = mostrar3;
	}

	public String getSfe() {
		return sfe;
	}

	public void setSfe(String sfe) {
		this.sfe = sfe;
	}

	public String getSfi() {
		return sfi;
	}

	public void setSfi(String sfi) {
		this.sfi = sfi;
	}
}
