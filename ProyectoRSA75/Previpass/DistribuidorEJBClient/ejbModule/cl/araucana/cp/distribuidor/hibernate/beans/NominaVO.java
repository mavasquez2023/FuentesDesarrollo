package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import cl.araucana.cp.distribuidor.base.Constants;

public abstract class NominaVO implements Comparable, Serializable
{
	private static final long serialVersionUID = 5189627364349801203L;
	protected int idConvenio;
	protected int rutEmpresa;

	protected EmpresaVO empresa;
	protected EstadoVO estado;
	protected ConvenioVO convenio;
	protected int idEstado;
	protected long idCodigoBarras;
	protected long montoNum;
	protected boolean mostrarChkBox;
	protected int idEnvio;
	protected String nombre;
	protected Timestamp recibida;
	protected Timestamp aceptada;
	protected long crc;
	protected int numReenvios;
	protected int numCotizaciones;
	protected int numCotizOK;
	protected int numCotizCorregidas;
	protected String accion1, accion2, accion3, accion4, accion5, accion6, accion7, accion8, accion9; // NOMINA_EN_LINEA 
	protected String accion10; // Para separar la información de la nómina en solo una "acción" 
	protected String monto;
	protected String idformateado;
	protected int informaSIS;

	public abstract Class getTipoCotizacion();

	public abstract Class getTipoCotizacionPendiente();

	public abstract Class getTipoCausaAviso();

	public abstract Class getTipoCausa();

	public abstract char getTipoProceso();

	public NominaVO()
	{
		super();
	}

	public NominaVO(int idConvenio, int rutEmpresa)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = rutEmpresa;
	}

	public void restNumCotizOK()
	{
		this.numCotizOK--;
	}

	public void restNumCotizaciones()
	{
		this.numCotizaciones--;
	}

	public void sumNumCotizOK()
	{
		this.numCotizOK++;
	}

	public void sumNumCotizaciones()
	{
		this.numCotizaciones++;
	}

	public void sumNumCotizCorr()
	{
		this.numCotizCorregidas++;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idConvenio;
		result = PRIME * result + this.rutEmpresa;
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
		final NominaVO other = (NominaVO) obj;
		if (this.idConvenio != other.idConvenio)
			return false;
		if (this.rutEmpresa != other.rutEmpresa)
			return false;
		return true;
	}

	public Timestamp getAceptada()
	{
		return this.aceptada;
	}

	public void setAceptada(Timestamp aceptada)
	{
		this.aceptada = aceptada;
	}

	public long getCrc()
	{
		return this.crc;
	}

	public void setCrc(long crc)
	{
		this.crc = crc;
	}

	public long getIdCodigoBarras()
	{
		return this.idCodigoBarras;
	}

	public void setIdCodigoBarras(long idCodigoBarras)
	{
		this.idCodigoBarras = idCodigoBarras;
	}

	public int getIdEnvio()
	{
		return this.idEnvio;
	}

	public void setIdEnvio(int idEnvio)
	{
		this.idEnvio = idEnvio;
	}

	public int getIdEstado()
	{
		return this.idEstado;
	}

	public void setIdEstado(int idEstado)
	{
		this.idEstado = idEstado;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getNumCotizaciones()
	{
		return this.numCotizaciones;
	}

	public void setNumCotizaciones(int numCotizaciones)
	{
		this.numCotizaciones = numCotizaciones;
	}

	public int getNumCotizCorregidas()
	{
		return this.numCotizCorregidas;
	}

	public void setNumCotizCorregidas(int numCotizCorregidas)
	{
		this.numCotizCorregidas = numCotizCorregidas;
	}

	public int getNumCotizOK()
	{
		return this.numCotizOK;
	}

	public void setNumCotizOK(int numCotizOK)
	{
		this.numCotizOK = numCotizOK;
	}

	public int getNumReenvios()
	{
		return this.numReenvios;
	}

	public void setNumReenvios(int numReenvios)
	{
		this.numReenvios = numReenvios;
	}

	public void addNumReenvios()
	{
		this.numReenvios++;
	}

	public Timestamp getRecibida()
	{
		return this.recibida;
	}

	public void setRecibida(Timestamp recibida)
	{
		this.recibida = recibida;
	}

	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	public EstadoVO getEstado()
	{
		return this.estado;
	}

	public void setEstado(EstadoVO estado)
	{
		this.estado = estado;
	}

	public String getIdformateado()
	{
		return this.idformateado;
	}

	public void setIdformateado(String idformateado)
	{
		this.idformateado = idformateado;
	}

	public int getIdConvenio()
	{
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}

	public EmpresaVO getEmpresa()
	{
		return this.empresa;
	}

	public void setEmpresa(EmpresaVO empresa)
	{
		this.empresa = empresa;
	}

	public ConvenioVO getConvenio()
	{
		return this.convenio;
	}

	public void setConvenio(ConvenioVO convenio)
	{
		this.convenio = convenio;
	}

	public long getMontoNum()
	{
		return this.montoNum;
	}

	public void setMontoNum(long montoNum)
	{
		this.montoNum = montoNum;
	}

	public boolean isMostrarChkBox()
	{
		return this.mostrarChkBox;
	}

	public void setMostrarChkBox(boolean mostrarChkBox)
	{
		this.mostrarChkBox = mostrarChkBox;
	}

	public String getAccion1()
	{
		return this.accion1;
	}

	public void setAccion1(String accion1)
	{
		this.accion1 = accion1;
	}

	public String getAccion2()
	{
		return this.accion2;
	}

	public void setAccion2(String accion2)
	{
		this.accion2 = accion2;
	}

	public String getAccion3()
	{
		return this.accion3;
	}

	public void setAccion3(String accion3)
	{
		this.accion3 = accion3;
	}

	public String getAccion4()
	{
		return this.accion4;
	}

	public void setAccion4(String accion4)
	{
		this.accion4 = accion4;
	}

	public String getAccion5()
	{
		return this.accion5;
	}

	public void setAccion5(String accion5)
	{
		this.accion5 = accion5;
	}

	public String getAccion6()
	{
		return this.accion6;
	}

	public void setAccion6(String accion6)
	{
		this.accion6 = accion6;
	}

	public String getAccion7()
	{
		return this.accion7;
	}

	public void setAccion7(String accion7)
	{
		this.accion7 = accion7;
	}

	public String getMonto()
	{
		return this.monto;
	}

	public void setMonto(String monto)
	{
		this.monto = monto;
	}

	public String toString()
	{

		return "NominaVO[idEmpresa: " + this.rutEmpresa + ", IdConvenio: " + this.getIdConvenio() + ", idCodigoBarras: " + this.getIdCodigoBarras() + ", IdEstado: " + this.getIdEstado() + "]";
	}

	public int compareTo(Object otraNomina)
	{
		NominaVO otra = (NominaVO) otraNomina;
		Map mapaOrden = new HashMap();
		if (this.getIdEstado() != otra.getIdEstado())
		{
//			mapaOrden.put(new Integer(Constants.EST_NOM_CREADA_EN_LINEA), new Integer(1)); // NOMINA_EN_LINEA
//			mapaOrden.put(new Integer(Constants.EST_NOM_NO_ENVIADA), new Integer(2));
//			mapaOrden.put(new Integer(Constants.EST_NOM_EN_PROCESO), new Integer(3));
//			mapaOrden.put(new Integer(Constants.EST_NOM_EN_EJB), new Integer(4));
//			mapaOrden.put(new Integer(Constants.EST_NOM_NO_CARGADA), new Integer(5));
//			mapaOrden.put(new Integer(Constants.EST_NOM_CON_ERRORES), new Integer(6));
//			mapaOrden.put(new Integer(Constants.EST_NOM_POR_PAGAR), new Integer(7));
//			mapaOrden.put(new Integer(Constants.EST_NOM_PRECURSADA), new Integer(8));
//			mapaOrden.put(new Integer(Constants.EST_NOM_PAGADO_PARCIALMENTE), new Integer(9));
//			mapaOrden.put(new Integer(Constants.EST_NOM_PAGADO), new Integer(10));
			//jlandero 09-07-2010 cambia el orden de presentación
			/*mapaOrden.put(new Integer(Constants.EST_NOM_CREADA_EN_LINEA), new Integer(1)); // NOMINA_EN_LINEA
			mapaOrden.put(new Integer(Constants.EST_NOM_EN_PROCESO), new Integer(2));
			mapaOrden.put(new Integer(Constants.EST_NOM_EN_EJB), new Integer(3));			
			mapaOrden.put(new Integer(Constants.EST_NOM_CON_ERRORES), new Integer(4));
			mapaOrden.put(new Integer(Constants.EST_NOM_NO_CARGADA), new Integer(5));
			mapaOrden.put(new Integer(Constants.EST_NOM_POR_PAGAR), new Integer(6));
			mapaOrden.put(new Integer(Constants.EST_NOM_PAGADO), new Integer(7));
			mapaOrden.put(new Integer(Constants.EST_NOM_PAGADO_PARCIALMENTE), new Integer(8));
			mapaOrden.put(new Integer(Constants.EST_NOM_PRECURSADA), new Integer(9));
			mapaOrden.put(new Integer(Constants.EST_NOM_NO_ENVIADA), new Integer(10));
			mapaOrden.put(new Integer(Constants.EST_NOM_NO_PROCESADA), new Integer(11));*/
			//csanchez 23-01-2012 Se cambia orden de presentación para agregar nuevo estado: PREPAGADO
			mapaOrden.put(new Integer(Constants.EST_NOM_CREADA_EN_LINEA), new Integer(1)); // NOMINA_EN_LINEA
			mapaOrden.put(new Integer(Constants.EST_NOM_EN_PROCESO), new Integer(2));
			mapaOrden.put(new Integer(Constants.EST_NOM_EN_EJB), new Integer(3));			
			mapaOrden.put(new Integer(Constants.EST_NOM_CON_ERRORES), new Integer(4));
			mapaOrden.put(new Integer(Constants.EST_NOM_NO_CARGADA), new Integer(5));
			mapaOrden.put(new Integer(Constants.EST_NOM_POR_PAGAR), new Integer(6));
			mapaOrden.put(new Integer(Constants.EST_NOM_PREPAGADA), new Integer(7));
			mapaOrden.put(new Integer(Constants.EST_NOM_PAGADO), new Integer(8));
			mapaOrden.put(new Integer(Constants.EST_NOM_PAGADO_PARCIALMENTE), new Integer(9));
			mapaOrden.put(new Integer(Constants.EST_NOM_PRECURSADA), new Integer(10));
			mapaOrden.put(new Integer(Constants.EST_NOM_NO_ENVIADA), new Integer(11));
			mapaOrden.put(new Integer(Constants.EST_NOM_NO_PROCESADA), new Integer(12));
			
			Integer este = (Integer) mapaOrden.get(new Integer(this.getIdEstado()));
			Integer otro = (Integer) mapaOrden.get(new Integer(otra.getIdEstado()));

			return este.compareTo(otro);
		} else if (this.rutEmpresa != otra.getRutEmpresa())
		{
			return (new Integer(this.rutEmpresa)).compareTo(new Integer(otra.getRutEmpresa()));
		} else
			return (new Integer(this.idConvenio)).compareTo(new Integer(otra.getIdConvenio()));
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.rutEmpresa));
		parametros.put("2", String.valueOf(this.idConvenio));
		parametros.put("3", String.valueOf(this.idEstado));
		parametros.put("4", String.valueOf(this.idCodigoBarras));
		parametros.put("5", String.valueOf(this.idEnvio));
		parametros.put("6", String.valueOf(this.nombre));
		parametros.put("7", String.valueOf(this.recibida));
		parametros.put("8", String.valueOf(this.aceptada));
		parametros.put("9", String.valueOf(this.crc));
		parametros.put("10", String.valueOf(this.numReenvios));
		parametros.put("11", String.valueOf(this.numCotizaciones));
		parametros.put("12", String.valueOf(this.numCotizOK));
		parametros.put("13", String.valueOf(this.numCotizCorregidas));
		parametros.put("14", String.valueOf(this.informaSIS));
		return parametros;
	}

	public static String formatMonto(String monto)
	{
		String montoStr = "";
		int pos = 0;
		int largo = monto.length();
		while (largo > 0)
		{
			if (pos > 0 && pos % 3 == 0)
				montoStr = "." + montoStr;
			pos++;
			montoStr = "" + monto.charAt(largo - 1) + montoStr;
			largo = monto.substring(0, largo - 1).length();
		}
		return montoStr;
	}

	public String getFormatoRut()
	{
		// se usa directamente desde jsp
		int M = 0, S = 1, T = this.rutEmpresa;
		for (; T != 0; T /= 10)
			S = (S + T % 10 * (9 - M++ % 6)) % 11;
		char dv = (char) (S != 0 ? S + 47 : 75);
		String rut = formatMonto(this.rutEmpresa + "") + "-" + dv;
		if (rut.trim().length() > 1)
			return rut;
		return "";
	}

	public String getAccion8()
	{
		return this.accion8;
	}

	public void setAccion8(String accion8)
	{
		this.accion8 = accion8;
	}

//	 NOMINA_EN_LINEA
	public String getAccion9() 
	{
		return this.accion9;
	}

	public void setAccion9(String accion9) 
	{
		this.accion9 = accion9;
	}
	
	public String getID() {
		String sIdConvenio;
		
		if (this.idConvenio < 10) {
			sIdConvenio = "0" + this.idConvenio;
		} else {
			sIdConvenio = "" + this.idConvenio;
		}
		
		return this.rutEmpresa + "." + getTipoProceso() + sIdConvenio;
	}

	public static NominaVO getInstance(int rut, int convenio, char tipoProceso)
	{
		switch (tipoProceso)
		{
		case 'R':
			return new NominaREVO(convenio, rut);
		case 'G':
			return new NominaGRVO(convenio, rut);
		case 'A':
			return new NominaRAVO(convenio, rut);
		case 'D':
			return new NominaDCVO(convenio, rut);
		}
		return null;
	}

	abstract public String getTipoCotizacionReal();

	public int getInformaSIS()
	{
		return this.informaSIS;
	}

	public void setInformaSIS(int informaSIS)
	{
		this.informaSIS = informaSIS;
	}

	public String getAccion10()
	{
		return this.accion10;
	}

	public void setAccion10(String accion10)
	{
		this.accion10 = accion10;
	}
}
