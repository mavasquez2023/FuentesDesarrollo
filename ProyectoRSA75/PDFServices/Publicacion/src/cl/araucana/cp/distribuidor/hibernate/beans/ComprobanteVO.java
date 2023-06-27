package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.cp.distribuidor.base.Constants;

public class ComprobanteVO extends AuditableVO
{
	private static final long serialVersionUID = 3519665744618475696L;
	private static Logger logger = Logger.getLogger(ComprobanteVO.class);
	long idCodigoBarra;
	long folioTesoreria;
	char idEstado;
	int idDocumento;
	Timestamp emitido;
	byte formaPago;
	byte medioPago;
	Timestamp pagado;
	long total;
	int numTrabajadores;
	int cierre;
	List secciones;

	public ComprobanteVO()
	{
		super();
	}

	public ComprobanteVO(ComprobanteVO otro)
	{
		super();
		this.idCodigoBarra = 0;
		this.folioTesoreria = 0;
		this.idEstado = otro.getIdEstado();
		this.idDocumento = otro.getIdDocumento();
		this.emitido = otro.getEmitido();
		this.formaPago = otro.getFormaPago();
		this.medioPago = otro.getMedioPago();
		this.pagado = otro.getPagado();
		this.total = otro.getTotal();
		this.numTrabajadores = otro.getNumTrabajadores();
		this.cierre = otro.getCierre();
		List seccs = new ArrayList();
		if (otro.getSecciones() != null)
		{
			for (Iterator it = otro.getSecciones().iterator(); it.hasNext();)
			{
				SeccionVO secc = new SeccionVO((SeccionVO)it.next(), this);
				seccs.add(secc);
			}
		}
		this.secciones = seccs;
	}

	public ComprobanteVO(long idCodigoBarra)
	{
		super();
		this.idCodigoBarra = idCodigoBarra;
	}

	public ComprobanteVO(long folio, char idEstado, int idDocumento, Timestamp emitido, byte formaPago, byte medioPago, Timestamp pagado, int numTrabajadores, int cierre)
	{
		super();
		this.folioTesoreria = folio;
		this.idEstado = idEstado;
		this.idDocumento = idDocumento;
		this.emitido = emitido;
		this.formaPago = formaPago;
		this.medioPago = medioPago;
		this.pagado = pagado;
		this.numTrabajadores = numTrabajadores;
		this.cierre = cierre;
	}

	public ComprobanteVO(long idCodigoBarra, long folioTesoreria, char idEstado, int idDocumento, Timestamp emitido, byte formaPago, byte medioPago, Timestamp pagado, long total, int trabajadores, int cierre, List secciones)
	{
		super();
		this.idCodigoBarra = idCodigoBarra;
		this.folioTesoreria = folioTesoreria;
		this.idEstado = idEstado;
		this.idDocumento = idDocumento;
		this.emitido = emitido;
		this.formaPago = formaPago;
		this.medioPago = medioPago;
		this.pagado = pagado;
		this.total = total;
		this.numTrabajadores = trabajadores;
		this.cierre = cierre;
		this.secciones = secciones;
	}

	public boolean tieneAlgoParaPago()
	{
		if (this.secciones != null)
			for (Iterator itSecc = this.secciones.iterator(); itSecc.hasNext();)
			{
				SeccionVO seccion = (SeccionVO) itSecc.next();
				if (seccion.getTipoPago() == Constants.EST_SECCION_PAGO)
					return true;
				if (seccion.getTipoPago() == Constants.EST_SECCION_INDEF)
					for (Iterator itDet = seccion.getDetallesSeccion().iterator(); itDet.hasNext();)
					{
						DetalleSeccionVO detalle = (DetalleSeccionVO)itDet.next();
						if (detalle.getTipoPago() == Constants.EST_SECCION_PAGO)
							return true;
					}
			}
		return false;
	}

	public boolean tienePagoParcial()
	{
		if (this.secciones != null)
			for (Iterator itSecc = this.secciones.iterator(); itSecc.hasNext();)
			{
				SeccionVO seccion = (SeccionVO) itSecc.next();
				if (seccion.getTipoPago() == Constants.EST_SECCION_DNP || seccion.getTipoPago() == Constants.EST_SECCION_NO_PAGO)
					return true;
				if (seccion.getTipoPago() == Constants.EST_SECCION_INDEF)
					for (Iterator itDet = seccion.getDetallesSeccion().iterator(); itDet.hasNext();)
					{
						DetalleSeccionVO detalle = (DetalleSeccionVO)itDet.next();
						if (detalle.getTipoPago() == Constants.EST_SECCION_DNP || detalle.getTipoPago() == Constants.EST_SECCION_NO_PAGO)
							return true;
					}
			}
		return false;
	}
	
	public SeccionVO getSeccion(int idTipoSeccion)
	{
		if (this.secciones != null)
			for (Iterator itSecc = this.secciones.iterator(); itSecc.hasNext();)
			{
				SeccionVO seccion = (SeccionVO) itSecc.next();
				if (idTipoSeccion == seccion.getIdTipoSeccion())
					return seccion;
			}
		return null;
	}

	public long getMonto(int idTipoSeccion, int idDetalleSeccion, int idMonto)
	{
		SeccionVO seccion = getSeccion(idTipoSeccion);
		if (seccion == null || seccion.getTipoPago() == Constants.EST_SECCION_NO_PAGO || seccion.getTipoPago() == Constants.EST_SECCION_DNP)
		{
			logger.debug("sale 1");
			return 0;
		}
		DetalleSeccionVO detalle = seccion.getDetalleSeccion(idDetalleSeccion);
		if (detalle == null || (seccion.getTipoPago() == Constants.EST_SECCION_INDEF && detalle.getTipoPago() != Constants.EST_SECCION_PAGO))
		{
			logger.debug("sale 1");
			return 0;
		}
		detalle.refreshListaM();
		return (long)detalle.getM(idMonto - 1);
	}

	public long getMonto(int idTipoSeccion, int idMonto)
	{
		SeccionVO seccion = getSeccion(idTipoSeccion);
		if (seccion == null || seccion.getTipoPago() == Constants.EST_SECCION_NO_PAGO || seccion.getTipoPago() == Constants.EST_SECCION_DNP)
		{
			logger.debug("sale 1");
			return 0;
		}
		if (seccion.getTipoPago() == Constants.EST_SECCION_PAGO)
			return seccion.getM(idMonto - 1);
		//indefinida, depende de cada detalle
		List detalles = seccion.getDetallesSeccion();
		long monto = 0;
		for (Iterator it = detalles.iterator(); it.hasNext();)
		{
			DetalleSeccionVO det = (DetalleSeccionVO)it.next();
			det.refreshListaM();
			if (det.getTipoPago() == Constants.EST_SECCION_PAGO)
				monto += det.getM(idMonto - 1);
		}
		return monto;
	}
	
	public void refreshListaM()
	{
		if (this.secciones != null)
			for (Iterator itSecc = this.secciones.iterator(); itSecc.hasNext();)
			{
				SeccionVO seccion = (SeccionVO) itSecc.next();
				seccion.refreshListaM();
			}
	}

	public int getCierre()
	{
		return this.cierre;
	}
	public void setCierre(int cierre)
	{
		this.cierre = cierre;
	}
	public Timestamp getEmitido()
	{
		return this.emitido;
	}
	public void setEmitido(Timestamp emitido)
	{
		this.emitido = emitido;
	}
	public long getFolioTesoreria()
	{
		return this.folioTesoreria;
	}
	public void setFolioTesoreria(long folioTesoreria)
	{
		this.folioTesoreria = folioTesoreria;
	}
	public byte getFormaPago()
	{
		return this.formaPago;
	}
	public void setFormaPago(byte formaPago)
	{
		this.formaPago = formaPago;
	}
	public long getIdCodigoBarra()
	{
		return this.idCodigoBarra;
	}
	public void setIdCodigoBarra(long idCodigoBarra)
	{
		this.idCodigoBarra = idCodigoBarra;
	}
	public int getIdDocumento()
	{
		return this.idDocumento;
	}
	public void setIdDocumento(int idDocumento)
	{
		this.idDocumento = idDocumento;
	}
	public char getIdEstado()
	{
		return this.idEstado;
	}
	public void setIdEstado(char idEstado)
	{
		this.idEstado = idEstado;
	}
	public byte getMedioPago()
	{
		return this.medioPago;
	}
	public void setMedioPago(byte medioPago)
	{
		this.medioPago = medioPago;
	}
	public int getNumTrabajadores()
	{
		return this.numTrabajadores;
	}
	public void setNumTrabajadores(int trabajadores)
	{
		this.numTrabajadores = trabajadores;
	}
	public Timestamp getPagado()
	{
		return this.pagado;
	}
	public void setPagado(Timestamp pagado)
	{
		this.pagado = pagado;
	}
	public List getSecciones()
	{
		return this.secciones;
	}
	public void setSecciones(List secciones)
	{
		this.secciones = secciones;
	}
	public long getTotal()
	{
		return this.total;
	}
	public void setTotal(long total)
	{
		this.total = total;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		
		parametros.put("1", String.valueOf(this.idCodigoBarra));
		parametros.put("2", String.valueOf(this.folioTesoreria));
		parametros.put("3", String.valueOf(this.idEstado));
		parametros.put("4", String.valueOf(this.idDocumento));
		parametros.put("5", String.valueOf(this.emitido));
		parametros.put("6", String.valueOf(this.formaPago));
		parametros.put("7", String.valueOf(this.medioPago));
		parametros.put("8", String.valueOf(this.pagado));
		parametros.put("9", String.valueOf(this.total));
		parametros.put("10", String.valueOf(this.numTrabajadores));
		parametros.put("11", String.valueOf(this.cierre));
				
		return parametros;
		
	}	
}
