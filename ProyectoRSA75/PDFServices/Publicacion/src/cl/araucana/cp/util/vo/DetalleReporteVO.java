package cl.araucana.cp.util.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class DetalleReporteVO implements Serializable
{
	private static final long serialVersionUID = 3519667232418475696L;

	private int idReporte;
	private int idTipoSeccion;
	private String montos;
	private String montosOrden;

	private int numCollsMostrar;
	private int[] mMostrar = new int[12];

	private static final String[] aMontos = { "monto1", "monto2", "monto3", "monto4", "monto5", "monto6", "monto7", "monto8", "monto9", "monto10", "monto11", "monto12" };

	public DetalleReporteVO(int idReporte, int idTipoSeccion, String montos, String montosOrden)
	{
		super();
		this.idReporte = idReporte;
		this.idTipoSeccion = idTipoSeccion;
		this.montos = montos;
		this.montosOrden = montosOrden;
	}

	public DetalleReporteVO()
	{
		super();
	}

	public int getNumCollsOrigin()
	{
		int i = 11;
		for (; i > 0; i--)
			if (this.mMostrar[i] != -1)
				break;
		return i + 1;
	}

	public int cuentaCollsMostrar()
	{
		int count = 0;
		for (int i = 0; i < this.montos.length(); i++)
			if (this.montos.charAt(i) == '1')
				count++;

		this.numCollsMostrar = count;
		return count;
	}

	public int[] getMMostrar()
	{
		return this.mMostrar;
	}

	public void setMMostrar(int[] mostrar)
	{
		this.mMostrar = mostrar;
	}

	public int getNumCollsMostrar()
	{
		return this.numCollsMostrar;
	}

	public void setNumCollsMostrar(int numCollsMostrar)
	{
		this.numCollsMostrar = numCollsMostrar;
	}

	public int getIdReporte()
	{
		return this.idReporte;
	}

	public void setIdReporte(int idReporte)
	{
		this.idReporte = idReporte;
	}

	public int getIdTipoSeccion()
	{
		return this.idTipoSeccion;
	}

	public void setIdTipoSeccion(int idTipoSeccion)
	{
		this.idTipoSeccion = idTipoSeccion;
	}

	public String getMontos()
	{
		return this.montos;
	}

	public void setMontos(String montos)
	{
		this.montos = montos;
	}

	public String getMontosOrden()
	{
		return this.montosOrden;
	}

	/**
	 * montos orden
	 * 
	 * @param montosOrden
	 */
	public void setMontosOrden(String montosOrden)
	{
		this.montosOrden = montosOrden;
	}

	public List getListaMontos()
	{
		SortedMap mapMonto = new TreeMap();
		for (int i = 0; i < 12; i++)
		{
			if (this.montos.charAt(i) == '1')
				mapMonto.put(new Integer(this.montosOrden.substring(2 * i, 2 * (i + 1))), new Integer(i));
		}

		return new ArrayList(mapMonto.values());
	}

	/**
	 * string
	 */
	public String toString()
	{
		String sRes = "DetalleReporteVO[idReporte: " + this.idReporte + ", idTipoSeccion: " + this.idTipoSeccion + ", montos: [";

		for (Iterator it = this.getListaMontos().iterator(); it.hasNext();)
			sRes += aMontos[((Integer) it.next()).intValue()] + ", ";
		sRes = sRes.substring(0, sRes.length() - 2) + "]]";

		return sRes;
	}

	public int[] generaArray() throws Exception
	{
		try
		{
			int[] mostrarTmp = new int[12];
			int[] ordenTmp = new int[12];
			int count = 0;
			for (int i = 0; i < this.montos.length(); i++)
			{
				if (this.montos.charAt(i) == '1')
				{
					mostrarTmp[count] = i;// cual m mostrar
					int pos = 2 * i;
					ordenTmp[count] = new Integer(this.montosOrden.substring(pos, pos + 2)).intValue() - 1; // en que orden mostrarlo
					count++;
				}
			}

			this.numCollsMostrar = count;
			for (int i = 0; i < this.montos.length(); i++)
				this.mMostrar[i] = -1;
			int i = 0;
			for (; i < count; i++)
				this.mMostrar[ordenTmp[i]] = mostrarTmp[i];

			/*
			 * this.logger.info("numMostrar:" + this.numCollsMostrar + "::"); for (i = 0; i < this.mMostrar.length; i++) this.logger.info("i:" + i + ":valor:" + this.mMostrar[i] + "::");
			 */
			return this.mMostrar;
		} catch (Exception e)
		{
			return null;
		}
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idReporte;
		result = PRIME * result + this.idTipoSeccion;
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
		final DetalleReporteVO other = (DetalleReporteVO) obj;
		if (this.idReporte != other.idReporte)
			return false;
		if (this.idTipoSeccion != other.idTipoSeccion)
			return false;
		return true;
	}
}
