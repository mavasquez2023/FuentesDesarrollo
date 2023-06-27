package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.cp.distribuidor.base.Constants;


public class SeccionDNPVO extends AuditableVO
{	
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(SeccionDNPVO.class);
	private static final long serialVersionUID = 1348330688280789768L;
	
	
	private long idCodigoBarra;
	private int idTipoSeccion;
	private long[] m = new long[12];
	private long mm1;
	private long mm2;
	private long mm3;
	private long mm4;
	private long mm5;
	private long mm6;
	private long mm7;
	private long mm8;
	private long mm9;
	private long mm10;
	private long mm11;
	private long mm12;
	private int numTrabajadores;
	private int tipoPago = Constants.EST_SECCION_PAGO; //por defecto pago
	private TipoSeccionVO tipoSeccion;
	private ConfigPDFVO configPDF = new ConfigPDFVO();

	private long total;
	private List detallesSeccion;

	private ComprobanteVO comprobante;
	
	private int idEmpresa;
	private int periodo;
	private int idConvenio;	
	private String tipoPagoDNP;
	private String tipoProceso;	
	

	public ComprobanteVO getComprobante()
	{
		return this.comprobante;
	}

	public void setComprobante(ComprobanteVO comprobante)
	{
		this.comprobante = comprobante;
	}

	public SeccionDNPVO()
	{
		super();
	}

	public SeccionDNPVO(int numTrabajadores, int tipoPago, int idTipoSeccion)
	{
		super();
		this.idTipoSeccion = idTipoSeccion;
		this.numTrabajadores = numTrabajadores;
		this.tipoPago = tipoPago;
	}

	public SeccionDNPVO(SeccionDNPVO SeccionDNPVO, ComprobanteVO cmp)
	{
		super();
		this.idCodigoBarra = 0;
		this.idTipoSeccion = SeccionDNPVO.getIdTipoSeccion();
		this.mm1 = SeccionDNPVO.getMm1();
		this.mm2 = SeccionDNPVO.getMm2();
		this.mm3 = SeccionDNPVO.getMm3();
		this.mm4 = SeccionDNPVO.getMm4();
		this.mm5 = SeccionDNPVO.getMm5();
		this.mm6 = SeccionDNPVO.getMm6();
		this.mm7 = SeccionDNPVO.getMm7();
		this.mm8 = SeccionDNPVO.getMm8();
		this.mm9 = SeccionDNPVO.getMm9();
		this.mm10 = SeccionDNPVO.getMm10();
		this.mm11 = SeccionDNPVO.getMm11();
		this.mm12 = SeccionDNPVO.getMm12();
		this.numTrabajadores = SeccionDNPVO.getNumTrabajadores();
		this.tipoPago = SeccionDNPVO.getTipoPago();
		List detalles = new ArrayList();
		if (SeccionDNPVO.getDetallesSeccion() != null)
		{
			for (Iterator it = SeccionDNPVO.getDetallesSeccion().iterator(); it.hasNext();)
			{
				DetalleSeccionDNPVO secc = new DetalleSeccionDNPVO((DetalleSeccionDNPVO) it.next(), this);
				detalles.add(secc);
			}
		}
		this.detallesSeccion = detalles;
		this.configPDF = new ConfigPDFVO(SeccionDNPVO.getConfigPDF());
		this.comprobante = cmp;
	}

	public long[] calcularTotalSeccionesReal()
	{
		long[] real = new long[12];
		if (this.detallesSeccion != null)
		{
			for (Iterator it = this.detallesSeccion.iterator(); it.hasNext();)
			{
				DetalleSeccionDNPVO det = (DetalleSeccionDNPVO) it.next();
				if (!(det.getTipoPago() == Constants.EST_SECCION_NO_PAGO || det.getTipoPago() == Constants.EST_SECCION_DNP))
				{
					det.refreshListaM();
					double[] mDetalle = det.getM();
					for (int i = 0; i < mDetalle.length; i++)
						real[i] += mDetalle[i];
				}
			}
		}

		return real;
	}

	public int calcularTotalTrabajadores()
	{
		int real = 0;
		if (this.detallesSeccion != null)
		{
			for (Iterator it = this.detallesSeccion.iterator(); it.hasNext();)
			{
				DetalleSeccionDNPVO det = (DetalleSeccionDNPVO) it.next();
				if (!(det.getTipoPago() == Constants.EST_SECCION_NO_PAGO || det.getTipoPago() == Constants.EST_SECCION_DNP))
					real += det.getNumTrabajadores();
			}
		}
		return real;
	}

	public void refreshListaM()
	{
		this.m = new long[12];
		this.m[0] = this.mm1;
		this.m[1] = this.mm2;
		this.m[2] = this.mm3;
		this.m[3] = this.mm4;
		this.m[4] = this.mm5;
		this.m[5] = this.mm6;
		this.m[6] = this.mm7;
		this.m[7] = this.mm8;
		this.m[8] = this.mm9;
		this.m[9] = this.mm10;
		this.m[10] = this.mm11;
		this.m[11] = this.mm12;

		this.total = 0;
		for (int i = 0; i < 12; i++)
			this.total += this.m[i];
	}

	public void showM()
	{
		for (int i = 0; i < 12; i++)
			logger.info(i + "::" + this.m[i] + "::");
	}

	public void refreshMUnitarios()
	{
		if (this.m != null)
		{
			this.mm1 = this.m[0];
			this.mm2 = this.m[1];
			this.mm3 = this.m[2];
			this.mm4 = this.m[3];
			this.mm5 = this.m[4];
			this.mm6 = this.m[5];
			this.mm7 = this.m[6];
			this.mm8 = this.m[7];
			this.mm9 = this.m[8];
			this.mm10 = this.m[9];
			this.mm11 = this.m[10];
			this.mm12 = this.m[11];
		}
	}

	public DetalleSeccionDNPVO getDetalleSeccion(int idDetalleSeccion)
	{
		if (this.detallesSeccion != null)
			for (Iterator itDetSecc = this.detallesSeccion.iterator(); itDetSecc.hasNext();)
			{
				DetalleSeccionDNPVO detalle = (DetalleSeccionDNPVO) itDetSecc.next();
				if (idDetalleSeccion == detalle.getIdDetalleSeccion())
					return detalle;
			}
		else
			this.detallesSeccion = new ArrayList();
		return null;
	}

	public void sumaTotales()
	{
		this.numTrabajadores = 0;
		this.m = new long[12];
		if (this.detallesSeccion != null)
		{
			List detCeros = new ArrayList();
			for (Iterator it = this.detallesSeccion.iterator(); it.hasNext();)
			{
				long tot = 0;
				DetalleSeccionDNPVO det = (DetalleSeccionDNPVO) it.next();
				double[] mDetalle = det.getM();
				for (int i = 0; i < mDetalle.length; i++)
				{
					this.m[i] += mDetalle[i];
					tot += mDetalle[i];
				}
				if (tot == 0)
				{
					detCeros.add(det);
					continue;
				}
				this.numTrabajadores += det.getNumTrabajadores();
			}
			this.detallesSeccion.removeAll(detCeros);
		}
	}

	public void setDetallesSeccion(int idDetalleSeccion, int numTrabajadores, double[] mTmp)
	{
		if (this.detallesSeccion == null)
		{
			this.detallesSeccion = new ArrayList();
			this.detallesSeccion.add(new DetalleSeccionDNPVO(this.idTipoSeccion, idDetalleSeccion, numTrabajadores, this.tipoPago, mTmp));
		} else
		{
			boolean flag = false;
			for (int i = 0; i < this.detallesSeccion.size(); i++)
			{
				DetalleSeccionDNPVO det = (DetalleSeccionDNPVO) this.detallesSeccion.get(i);
				if (idDetalleSeccion == det.getIdDetalleSeccion())
				{
					flag = true;
					det.addM(mTmp);
					if (numTrabajadores > 0)
						det.addNumTrabajador();
					else if (numTrabajadores < 0)
						det.restaNumTrabajador();
					this.detallesSeccion.set(i, det); // si ya estaba suma los montos
					break;
				}
			}
			if (!flag) // si no lo encontro
				this.detallesSeccion.add(new DetalleSeccionDNPVO(this.idTipoSeccion, idDetalleSeccion, numTrabajadores, this.tipoPago, mTmp));
		}
	}

	public void setDetallesSeccionTotalCaja(int idDetalleSeccion, int posSubTotal, int posTotal, long subTotal, long total)
	{
		for (int i = 0; i < this.detallesSeccion.size(); i++)
		{
			DetalleSeccionDNPVO det = (DetalleSeccionDNPVO) this.detallesSeccion.get(i);
			if (idDetalleSeccion == det.getIdDetalleSeccion())
			{
				det.setMArbitrario(posSubTotal, subTotal);
				det.setMArbitrario(posTotal, total);
				this.detallesSeccion.set(i, det);
				break;
			}
		}
	}
	// se usa para total INP
	public void setDetallesSeccionArbitrario(int idDetalleSeccion, int posicion, long total)
	{
		for (int i = 0; i < this.detallesSeccion.size(); i++)
		{
			DetalleSeccionDNPVO det = (DetalleSeccionDNPVO) this.detallesSeccion.get(i);
			if (idDetalleSeccion == det.getIdDetalleSeccion())
			{
				det.setMArbitrario(posicion, total);
				this.detallesSeccion.set(i, det);
				break;
			}
		}
	}
	
	public long getIdCodigoBarra()
	{
		return this.idCodigoBarra;
	}

	public void setIdCodigoBarra(long idCodigoBarra)
	{
		this.idCodigoBarra = idCodigoBarra;
	}

	public int getIdTipoSeccion()
	{
		return this.idTipoSeccion;
	}

	public void setIdTipoSeccion(int idTipoSeccion)
	{
		this.idTipoSeccion = idTipoSeccion;
	}

	public long getM(int index)
	{
		return this.m[index];
	}

	public long[] getM()
	{
		return this.m;
	}

	public void setM(int index, long value)
	{
		this.m[index] = value;
	}

	public void setM(long[] m)
	{
		this.m = m;
	}

	public int getNumTrabajadores()
	{
		return this.numTrabajadores;
	}

	public void setNumTrabajadores(int trabajadores)
	{
		this.numTrabajadores = trabajadores;
	}

	public long getMm1()
	{
		return this.mm1;
	}

	public void setMm1(long mm1)
	{
		this.mm1 = mm1;
	}

	public long getMm10()
	{
		return this.mm10;
	}

	public void setMm10(long mm10)
	{
		this.mm10 = mm10;
	}

	public long getMm11()
	{
		return this.mm11;
	}

	public void setMm11(long mm11)
	{
		this.mm11 = mm11;
	}

	public long getMm12()
	{
		return this.mm12;
	}

	public void setMm12(long mm12)
	{
		this.mm12 = mm12;
	}

	public long getMm2()
	{
		return this.mm2;
	}

	public void setMm2(long mm2)
	{
		this.mm2 = mm2;
	}

	public long getMm3()
	{
		return this.mm3;
	}

	public void setMm3(long mm3)
	{
		this.mm3 = mm3;
	}

	public long getMm4()
	{
		return this.mm4;
	}

	public void setMm4(long mm4)
	{
		this.mm4 = mm4;
	}

	public long getMm5()
	{
		return this.mm5;
	}

	public void setMm5(long mm5)
	{
		this.mm5 = mm5;
	}

	public long getMm6()
	{
		return this.mm6;
	}

	public void setMm6(long mm6)
	{
		this.mm6 = mm6;
	}

	public long getMm7()
	{
		return this.mm7;
	}

	public void setMm7(long mm7)
	{
		this.mm7 = mm7;
	}

	public long getMm8()
	{
		return this.mm8;
	}

	public void setMm8(long mm8)
	{
		this.mm8 = mm8;
	}

	public long getMm9()
	{
		return this.mm9;
	}

	public void setMm9(long mm9)
	{
		this.mm9 = mm9;
	}

	public List getDetallesSeccion()
	{
		return this.detallesSeccion;
	}

	public void setDetallesSeccion(List detallesSeccion)
	{
		this.detallesSeccion = detallesSeccion;
	}

	public long getTotal()
	{
		return this.total;
	}

	public void setTotal(long total)
	{
		this.total = total;
	}

	public String toString()
	{
		String str = ":idTipo:" + this.idTipoSeccion + ":idCodigoBarra:" + this.idCodigoBarra + ":numTrabajadores:" + this.numTrabajadores + ":tipoPago:" + this.tipoPago + ":nDet:"
				+ (this.detallesSeccion != null ? this.detallesSeccion.size() : -1) + "::\n";
		/*
		 * if (this.detallesSeccion != null) for (int i = 0; i < this.detallesSeccion.size(); i++) { DetalleSeccionDNPVO det = (DetalleSeccionDNPVO) this.detallesSeccion.get(i); str += "\t" +
		 * ToStringBuilder.reflectionToString(det, ToStringStyle.SHORT_PREFIX_STYLE) + ":\n"; }
		 */
		return str;
	}

	public TipoSeccionVO getTipoSeccion()
	{
		return this.tipoSeccion;
	}

	public void setTipoSeccion(TipoSeccionVO tipoSeccion)
	{
		this.tipoSeccion = tipoSeccion;
	}


	public int getTipoPago()
	{
		return this.tipoPago;
	}

	public void setTipoPago(int tipoPago)
	{
		this.tipoPago = tipoPago;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (this.idCodigoBarra ^ (this.idCodigoBarra >>> 32));
		result = PRIME * result + this.idTipoSeccion;
		return result;
	}

	public ConfigPDFVO getConfigPDF()
	{
		return this.configPDF;
	}

	public void setConfigPDF(ConfigPDFVO configPDF)
	{
		this.configPDF = configPDF;
	}

	public void setConfigPDF(String asterisco, String mostrar, int[] numTrab)
	{
		this.configPDF.setMxAsterisco(asterisco);
		this.configPDF.setMxNoMostrar(mostrar);
		this.configPDF.setNumTrab(numTrab);
	}

	public void setMArbitrario(int posicion, long valor)
	{
		this.m[posicion] = valor;
		refreshMUnitarios();
		if (this.detallesSeccion != null)
			for (Iterator it = this.detallesSeccion.iterator(); it.hasNext();)
			{
				DetalleSeccionDNPVO det = (DetalleSeccionDNPVO) it.next();
				det.setMArbitrario(posicion, valor);
			}
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SeccionDNPVO other = (SeccionDNPVO) obj;
		if (this.idCodigoBarra != other.idCodigoBarra)
			return false;
		if (this.idTipoSeccion != other.idTipoSeccion)
			return false;
		return true;
	}

	public void cleanM()
	{
		for (int i = 0; i < 12; i++)
			this.m[i] = 0;
		refreshMUnitarios();
		if (this.detallesSeccion != null)
			for (Iterator it = this.detallesSeccion.iterator(); it.hasNext();)
			{
				DetalleSeccionDNPVO det = (DetalleSeccionDNPVO) it.next();
				det.cleanM();
			}
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();

		parametros.put("1", String.valueOf(this.idCodigoBarra));
		parametros.put("2", String.valueOf(this.idTipoSeccion));
		parametros.put("3", String.valueOf(this.mm1));
		parametros.put("4", String.valueOf(this.mm2));
		parametros.put("5", String.valueOf(this.mm3));
		parametros.put("6", String.valueOf(this.mm4));
		parametros.put("7", String.valueOf(this.mm5));
		parametros.put("8", String.valueOf(this.mm6));
		parametros.put("9", String.valueOf(this.mm7));
		parametros.put("10", String.valueOf(this.mm8));
		parametros.put("11", String.valueOf(this.mm9));
		parametros.put("12", String.valueOf(this.mm10));
		parametros.put("13", String.valueOf(this.mm11));
		parametros.put("14", String.valueOf(this.mm12));
		parametros.put("15", String.valueOf(this.numTrabajadores));
		parametros.put("16", String.valueOf(this.tipoPago));
		return parametros;
	}

	public int calcularTotalSeccionIndice(int[] showColls)
	{
		int i = 0;
		while (i < showColls.length - 1)
		{
			if (showColls[i] != -1 && showColls[i + 1] == -1)
					break;
			i++;
		}
		return showColls[i];
	}

	public long calculaTotal()
	{
		long suma = 0;
		refreshListaM();
		String[] totalM = ((String)Constants.TOTAL_MX_SECCION.get("" + this.idTipoSeccion)).split("#");
		for (int i = 0; i < totalM.length; i++)
			suma += this.m[new Integer(totalM[i]).intValue()];
		this.total = suma;
		return suma;
	}

	public int getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(int idConvenio) {
		this.idConvenio = idConvenio;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public String getTipoPagoDNP() {
		return tipoPagoDNP;
	}

	public void setTipoPagoDNP(String tipoPagoDNP) {
		this.tipoPagoDNP = tipoPagoDNP;
	}

	public String getTipoProceso() {
		return tipoProceso;
	}

	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}
}
