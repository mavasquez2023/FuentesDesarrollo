package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class ConfigPDFVO implements Serializable
{

	private static final long serialVersionUID = 2079313553282843659L;
	private static Logger logger = Logger.getLogger(ConfigPDFVO.class);
	private int idTipoSeccion;
	private long idCodigoBarra;

	private String mxAsterisco = "";
	private String mxNoMostrar = "";

	private int[] mAsterisco;
	private int[] mNoMostrar;

	private int[] numTrab;
	private int numTrabM1;
	private int numTrabM2;
	private int numTrabM3;
	private int numTrabM4;
	private int numTrabM5;
	private int numTrabM6;
	private int numTrabM7;
	private int numTrabM8;
	private int numTrabM9;
	private int numTrabM10;
	private int numTrabM11;
	private int numTrabM12;

	public ConfigPDFVO()
	{
		super();
	}

	public ConfigPDFVO(int numM)
	{
		super();

		for (int i = 0; i < numM; i++)
		{
			this.mxAsterisco += "0";
			this.mxNoMostrar += "0";
		}
		this.numTrab = new int[numM];
		this.mAsterisco = new int[numM];
		this.mNoMostrar = new int[numM];
	}

	public ConfigPDFVO(int idTipoSeccion, long idCodigoBarra)
	{
		super();
		this.idTipoSeccion = idTipoSeccion;
		this.idCodigoBarra = idCodigoBarra;
	}

	public ConfigPDFVO(int idTipoSeccion, long idCodigoBarra, String mxAsterisco, String mxNoMostrar)
	{
		super();
		this.idTipoSeccion = idTipoSeccion;
		this.idCodigoBarra = idCodigoBarra;
		this.mxAsterisco = mxAsterisco;
		this.mxNoMostrar = mxNoMostrar;
	}

	public ConfigPDFVO(ConfigPDFVO configPDF)
	{
		super();
		this.idTipoSeccion = configPDF.getIdTipoSeccion();
		this.idCodigoBarra = 0;
		this.mxAsterisco = configPDF.getMxAsterisco();
		this.mxNoMostrar = configPDF.getMxNoMostrar();
		this.numTrabM1 = configPDF.getNumTrabM1();
		this.numTrabM2 = configPDF.getNumTrabM2();
		this.numTrabM3 = configPDF.getNumTrabM3();
		this.numTrabM4 = configPDF.getNumTrabM4();
		this.numTrabM5 = configPDF.getNumTrabM5();
		this.numTrabM6 = configPDF.getNumTrabM6();
		this.numTrabM7 = configPDF.getNumTrabM7();
		this.numTrabM8 = configPDF.getNumTrabM8();
		this.numTrabM9 = configPDF.getNumTrabM9();
		this.numTrabM10 = configPDF.getNumTrabM10();
		this.numTrabM11 = configPDF.getNumTrabM11();
		this.numTrabM12 = configPDF.getNumTrabM12();
	}

	public boolean isAddLeyenda()
	{
		for (int i = 0; i < this.mxAsterisco.length(); i++)
			if (this.mxAsterisco.charAt(i) == '1')
				return true;
		logger.debug("no tiene leyenda");
		return false;
	}

	public int countNoMostrar()
	{
		logger.debug("idTipoSeccion:" + this.idTipoSeccion + ":idCodigoBarra:" + this.idCodigoBarra + "::" + this.mxNoMostrar + "::");
		int count = 0;
		for (int i = 0; i < this.mxNoMostrar.length(); i++)
			if (this.mxNoMostrar.charAt(i) == '1')
				count++;
		logger.debug("no mostrar:" + count + "::");
		return count;
	}

	public int[] getNumTrab()
	{
		return this.numTrab;
	}

	public void parse()
	{
		this.mAsterisco = new int[this.mxAsterisco.length()];
		this.mNoMostrar = new int[this.mxNoMostrar.length()];

		for (int i = 0; i < this.mxAsterisco.length(); i++)
			this.mAsterisco[i] = new Integer("" + this.mxAsterisco.charAt(i)).intValue();
		for (int i = 0; i < this.mxNoMostrar.length(); i++)
			this.mNoMostrar[i] = new Integer("" + this.mxNoMostrar.charAt(i)).intValue();
		this.numTrab = new int[12];
		this.numTrab[0] = this.numTrabM1;
		this.numTrab[1] = this.numTrabM2;
		this.numTrab[2] = this.numTrabM3;
		this.numTrab[3] = this.numTrabM4;
		this.numTrab[4] = this.numTrabM5;
		this.numTrab[5] = this.numTrabM6;
		this.numTrab[6] = this.numTrabM7;
		this.numTrab[7] = this.numTrabM8;
		this.numTrab[8] = this.numTrabM9;
		this.numTrab[9] = this.numTrabM10;
		this.numTrab[10] = this.numTrabM11;
		this.numTrab[11] = this.numTrabM12;
	}

	public void setNumTrab(int[] numTrab)
	{
		this.numTrabM1 = numTrab[0];
		this.numTrabM2 = numTrab[1];
		this.numTrabM3 = numTrab[2];
		this.numTrabM4 = numTrab[3];
		this.numTrabM5 = numTrab[4];
		this.numTrabM6 = numTrab[5];
		this.numTrabM7 = numTrab[6];
		this.numTrabM8 = numTrab[7];
		this.numTrabM9 = numTrab[8];
		this.numTrabM10 = numTrab[9];
		this.numTrabM11 = numTrab[10];
		this.numTrabM12 = numTrab[11];
	}

	public void merge(String asterisco, String mostrar)
	{
		logger.debug("merge:" + asterisco + ":mxAsterisco:" + this.mxAsterisco + ":mostrar:" + mostrar + ":mxNoMostrar:" + this.mxNoMostrar + "::");
		if (!asterisco.equals(this.mxAsterisco))
		{
			String tmpAst = this.mxAsterisco;
			this.mxAsterisco = "";
			for (int i = 0; i < tmpAst.length() && i < asterisco.length(); i++)
			{
				if (tmpAst.charAt(i) == '1' || asterisco.charAt(i) == '1')// basta que uno diga "poner asterisco"
					this.mxAsterisco += "1";
				else
					this.mxAsterisco += "0";
			}
		}
		if (!mostrar.equals(this.mxNoMostrar))
		{
			String tmpNMost = this.mxNoMostrar;
			this.mxNoMostrar = "";
			for (int i = 0; i < tmpNMost.length() && i < mostrar.length(); i++)
			{
				if (tmpNMost.charAt(i) == '1' && mostrar.charAt(i) == '1')
					this.mxNoMostrar += "1";
				else
					this.mxNoMostrar += "0";
			}
		}
		logger.debug("despues:" + asterisco + ":mxAsterisco:" + this.mxAsterisco + ":mostrar:" + mostrar + ":mxNoMostrar:" + this.mxNoMostrar + "::");
	}

	public int getNumTrab(int i)
	{
		if (this.numTrab == null)
			parse();
		if (this.numTrab.length > i)
			return this.numTrab[i];
		return 0;
	}

	public int getMAsterisco(int i)
	{
		if (this.mAsterisco == null)
			parse();
		if (this.mAsterisco.length > i)
			return this.mAsterisco[i];
		return 0;
	}

	public int getMNoMostrar(int i)
	{
		try
		{
			if (this.mNoMostrar == null)
				parse();
			logger.debug("getMNoMostrar:i:" + i + ":this.mxNoMostrar:" + this.mxNoMostrar + ":this.mNoMostrar.length:" + this.mNoMostrar.length + "::");
			if (this.mNoMostrar.length >= i)
				return this.mNoMostrar[i];
		} catch (Exception e)
		{
			logger.error("SE CAYO!!! getMNoMostrar:i:" + i + ":this.mxNoMostrar:" + this.mxNoMostrar + ":this.mNoMostrar.length:" + this.mNoMostrar.length + "::");
		}
		return 0;
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

	public String getMxAsterisco()
	{
		return this.mxAsterisco;
	}

	public void setMxAsterisco(String mxAsterisco)
	{
		this.mxAsterisco = mxAsterisco;
	}

	public String getMxNoMostrar()
	{
		return this.mxNoMostrar;
	}

	public void setMxNoMostrar(String mxNoMostrar)
	{
		this.mxNoMostrar = mxNoMostrar;
	}

	public int getNumTrabM1()
	{
		return this.numTrabM1;
	}

	public void setNumTrabM1(int numTrabM1)
	{
		this.numTrabM1 = numTrabM1;
	}

	public int getNumTrabM10()
	{
		return this.numTrabM10;
	}

	public void setNumTrabM10(int numTrabM10)
	{
		this.numTrabM10 = numTrabM10;
	}

	public int getNumTrabM11()
	{
		return this.numTrabM11;
	}

	public void setNumTrabM11(int numTrabM11)
	{
		this.numTrabM11 = numTrabM11;
	}

	public int getNumTrabM12()
	{
		return this.numTrabM12;
	}

	public void setNumTrabM12(int numTrabM12)
	{
		this.numTrabM12 = numTrabM12;
	}

	public int getNumTrabM2()
	{
		return this.numTrabM2;
	}

	public void setNumTrabM2(int numTrabM2)
	{
		this.numTrabM2 = numTrabM2;
	}

	public int getNumTrabM3()
	{
		return this.numTrabM3;
	}

	public void setNumTrabM3(int numTrabM3)
	{
		this.numTrabM3 = numTrabM3;
	}

	public int getNumTrabM4()
	{
		return this.numTrabM4;
	}

	public void setNumTrabM4(int numTrabM4)
	{
		this.numTrabM4 = numTrabM4;
	}

	public int getNumTrabM5()
	{
		return this.numTrabM5;
	}

	public void setNumTrabM5(int numTrabM5)
	{
		this.numTrabM5 = numTrabM5;
	}

	public int getNumTrabM6()
	{
		return this.numTrabM6;
	}

	public void setNumTrabM6(int numTrabM6)
	{
		this.numTrabM6 = numTrabM6;
	}

	public int getNumTrabM7()
	{
		return this.numTrabM7;
	}

	public void setNumTrabM7(int numTrabM7)
	{
		this.numTrabM7 = numTrabM7;
	}

	public int getNumTrabM8()
	{
		return this.numTrabM8;
	}

	public void setNumTrabM8(int numTrabM8)
	{
		this.numTrabM8 = numTrabM8;
	}

	public int getNumTrabM9()
	{
		return this.numTrabM9;
	}

	public void setNumTrabM9(int numTrabM9)
	{
		this.numTrabM9 = numTrabM9;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (this.idCodigoBarra ^ (this.idCodigoBarra >>> 32));
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
		final ConfigPDFVO other = (ConfigPDFVO) obj;
		if (this.idCodigoBarra != other.idCodigoBarra)
			return false;
		if (this.idTipoSeccion != other.idTipoSeccion)
			return false;
		return true;
	}
}
