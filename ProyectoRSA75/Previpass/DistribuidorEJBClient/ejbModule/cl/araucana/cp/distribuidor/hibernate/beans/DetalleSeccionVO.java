package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.Calendar;
import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.araucana.cp.distribuidor.base.Constants;

public class DetalleSeccionVO extends AuditableVO
{
	private static Logger logger = Logger.getLogger(DetalleSeccionVO.class);
	private static final long serialVersionUID = -4018911022167522235L;
	long idCodigoBarra;
	int idTipoSeccion;
	int idDetalleSeccion;
	private double[] m = new double[12];
	private double m1;
	private double m2;
	private double m3;
	private double m4;
	private double m5;
	private double m6;
	private double m7;
	private double m8;
	private double m9;
	private double m10;
	private double m11;
	private double m12;
	private int numTrabajadores;
	private int tipoPago;
	private long totalDetalleSeccion;
	private SeccionVO seccion;
	private int folioImpresion;

	
	public DetalleSeccionVO()
	{
		super();
	}

	public DetalleSeccionVO(int idTipoSeccion, int idDetalleSeccion, int numTrabajadores, int tipoPago, double []mTmp)
	{
		super();
		this.idTipoSeccion = idTipoSeccion;
		this.idDetalleSeccion = idDetalleSeccion;
		this.numTrabajadores = numTrabajadores;
		
		Calendar now = Calendar.getInstance();
		int dia= now.get(Calendar.DATE);
		System.out.println("Dia actual:" + dia + ", dia FinPagoCaja:" + SeccionVO.getDiaFinPagoCaja());
		if(dia > SeccionVO.getDiaFinPagoCaja() && dia <=15 && ((idTipoSeccion== 1 || idTipoSeccion==20 || idTipoSeccion== 40) || (idTipoSeccion== 6 && idDetalleSeccion>1000))){
			this.tipoPago = Constants.EST_SECCION_NO_PAGO;
		}else{
			this.tipoPago = Constants.EST_SECCION_PAGO;
		}

		
		for (int i = 0; i < mTmp.length; i++)
			this.m[i] = mTmp[i];
		refreshMUnitarios();
	}

	public DetalleSeccionVO(DetalleSeccionVO detalle, SeccionVO secc) 
	{
		super();
		this.idCodigoBarra = 0;
		this.idTipoSeccion = detalle.getIdTipoSeccion();
		this.idDetalleSeccion = detalle.getIdDetalleSeccion();
		this.m1 = detalle.getM1();
		this.m2 = detalle.getM2();
		this.m3 = detalle.getM3();
		this.m4 = detalle.getM4();
		this.m5 = detalle.getM5();
		this.m6 = detalle.getM6();
		this.m7 = detalle.getM7();
		this.m8 = detalle.getM8();
		this.m9 = detalle.getM9();
		this.m10 = detalle.getM10();
		this.m11 = detalle.getM11();
		this.m12 = detalle.getM12();
		this.numTrabajadores = detalle.getNumTrabajadores();
		
		Calendar now = Calendar.getInstance();
		int dia= now.get(Calendar.DATE);
		System.out.println("Dia actual:" + dia + ", dia FinPagoCaja:" + SeccionVO.getDiaFinPagoCaja());
		if(dia > SeccionVO.getDiaFinPagoCaja() && dia <=15 && ((detalle.getIdTipoSeccion()== 1 || detalle.getIdTipoSeccion()==20 || detalle.getIdTipoSeccion()== 40) || (detalle.getIdTipoSeccion()== 6 && detalle.getIdDetalleSeccion()>1000))){
			this.tipoPago = Constants.EST_SECCION_NO_PAGO;
		}else{
			this.tipoPago = detalle.getTipoPago();
		}
		
		this.seccion = secc;
	}
	
	public void addNumTrabajador()
	{
		this.numTrabajadores++;
	}
	
	public void restaNumTrabajador()
	{
		this.numTrabajadores--;
	}
	
	public void addM(double []mTmp)
	{
		for (int i = 0; i < this.m.length; i++)
			this.m[i] += mTmp[i];
		refreshMUnitarios();
	}

	public void refreshListaM()
	{
		this.m = new double[12];
		this.m[0] = this.m1;
		this.m[1] = this.m2;
		this.m[2] = this.m3;
		this.m[3] = this.m4;
		this.m[4] = this.m5;
		this.m[5] = this.m6;
		this.m[6] = this.m7;
		this.m[7] = this.m8;
		this.m[8] = this.m9;
		this.m[9] = this.m10;
		this.m[10] = this.m11;
		this.m[11] = this.m12;
	}

	public void showM()
	{
		String str = "";
		for (int i = 0; i < 12; i++)
			str +=  ":m:"+ this.m[i];
		logger.info(str);
	}
	
	public void refreshMUnitarios()
	{
		if (this.m != null)
		{
			this.m1 = this.m[0];
			this.m2 = this.m[1];
			this.m3 = this.m[2];
			this.m4 = this.m[3];
			this.m5 = this.m[4];
			this.m6 = this.m[5];
			this.m7 = this.m[6];
			this.m8 = this.m[7];
			this.m9 = this.m[8];
			this.m10 = this.m[9];
			this.m11 = this.m[10];
			this.m12 = this.m[11];
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

	public int getIdDetalleSeccion()
	{
		return this.idDetalleSeccion;
	}

	public void setIdDetalleSeccion(int idDetalleSeccion)
	{
		this.idDetalleSeccion = idDetalleSeccion;
	}

	public int getIdTipoSeccion()
	{
		return this.idTipoSeccion;
	}

	public void setIdTipoSeccion(int idTipoSeccion)
	{
		this.idTipoSeccion = idTipoSeccion;
	}

	public double getM(int index)
	{
		return this.m[index];
	}

	public double[] getM()
	{
		return this.m;
	}

	public void setM(double[] m)
	{
		this.m = m;
	}

	public double getM1()
	{
		return this.m1;
	}

	public void setM1(double m1)
	{
		this.m1 = m1;
	}

	public double getM10()
	{
		return this.m10;
	}

	public void setM10(double m10)
	{
		this.m10 = m10;
	}

	public double getM11()
	{
		return this.m11;
	}

	public void setM11(double m11)
	{
		this.m11 = m11;
	}

	public double getM12()
	{
		return this.m12;
	}

	public void setM12(double m12)
	{
		this.m12 = m12;
	}

	public double getM2()
	{
		return this.m2;
	}

	public void setM2(double m2)
	{
		this.m2 = m2;
	}

	public double getM3()
	{
		return this.m3;
	}

	public void setM3(double m3)
	{
		this.m3 = m3;
	}

	public double getM4()
	{
		return this.m4;
	}

	public void setM4(double m4)
	{
		this.m4 = m4;
	}

	public double getM5()
	{
		return this.m5;
	}

	public void setM5(double m5)
	{
		this.m5 = m5;
	}

	public double getM6()
	{
		return this.m6;
	}

	public void setM6(double m6)
	{
		this.m6 = m6;
	}

	public double getM7()
	{
		return this.m7;
	}

	public void setM7(double m7)
	{
		this.m7 = m7;
	}

	public double getM8()
	{
		return this.m8;
	}

	public void setM8(double m8)
	{
		this.m8 = m8;
	}

	public double getM9()
	{
		return this.m9;
	}

	public void setM9(double m9)
	{
		this.m9 = m9;
	}

	public int getNumTrabajadores()
	{
		return this.numTrabajadores;
	}

	public void setNumTrabajadores(int numTrabajadores)
	{
		this.numTrabajadores = numTrabajadores;
	}

	public int getTipoPago()
	{
		return this.tipoPago;
	}

	public void setTipoPago(int tipoPago)
	{
		this.tipoPago = tipoPago;
	}

	public void setMArbitrario(int posicion, long valor)
	{
		this.m[posicion] = valor;
		refreshMUnitarios();
	}

	public void cleanM()
	{
		for (int i = 0; i < 12; i++)
			this.m[i] = 0;
		refreshMUnitarios();
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idCodigoBarra));
		parametros.put("2", String.valueOf(this.idTipoSeccion));
		parametros.put("3", String.valueOf(this.idDetalleSeccion));
		parametros.put("4", String.valueOf(this.m1));
		parametros.put("5", String.valueOf(this.m2));
		parametros.put("6", String.valueOf(this.m3));
		parametros.put("7", String.valueOf(this.m4));
		parametros.put("8", String.valueOf(this.m5));
		parametros.put("9", String.valueOf(this.m6));
		parametros.put("10", String.valueOf(this.m7));
		parametros.put("11", String.valueOf(this.m8));
		parametros.put("12", String.valueOf(this.m9));
		parametros.put("13", String.valueOf(this.m10));
		parametros.put("14", String.valueOf(this.m11));
		parametros.put("15", String.valueOf(this.m12));
		parametros.put("16", String.valueOf(this.numTrabajadores));
		parametros.put("17", String.valueOf(this.tipoPago));
		return parametros;
	}

	public long getTotalDetalleSeccion() 
	{
		return this.totalDetalleSeccion;
	}

	public void setTotalDetalleSeccion(long totalDetalleSeccion) 
	{
		this.totalDetalleSeccion = totalDetalleSeccion;
	}

	public SeccionVO getSeccion() {
		return this.seccion;
	}

	public void setSeccion(SeccionVO seccion) {
		this.seccion = seccion;
	}

	public long calculaTotal()
	{
		long suma = 0;
		refreshListaM();
		String[] totalM = ((String)Constants.TOTAL_MX_SECCION.get("" + this.idTipoSeccion)).split("#");
		for (int i = 0; i < totalM.length; i++)
			suma += this.m[new Integer(totalM[i]).intValue()];
		this.totalDetalleSeccion = suma;
		return suma;
	}

	public int getFolioImpresion() {
		return folioImpresion;
	}

	public void setFolioImpresion(int folioImpresion) {
		this.folioImpresion = folioImpresion;
	}
	
}
