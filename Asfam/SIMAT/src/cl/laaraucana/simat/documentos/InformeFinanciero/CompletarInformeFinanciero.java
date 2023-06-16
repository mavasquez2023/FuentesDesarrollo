package cl.laaraucana.simat.documentos.InformeFinanciero;

import java.util.ArrayList;
import java.util.Iterator;

import cl.laaraucana.simat.entidades.BalanceGeneral;
import cl.laaraucana.simat.entidades.CuentaIF;
import cl.laaraucana.simat.entidades.InformeFinancieroVO;
import cl.laaraucana.simat.mannagerDB2.InformeFinancieroMannager;
import cl.laaraucana.simat.utiles.LectorPropiedades;

/*
 * clase que realiza la lectura del balancegeneral.xls para extraer los valores de las cuentas y exportar al informe financiero
 * 
 * */

public class CompletarInformeFinanciero {

	public InformeFinancieroVO obtenerDatosBalanceGeneral_IF() throws Exception{
		//instanciamos valores en cero
		InformeFinancieroVO infoFinan=new InformeFinancieroVO(0,"","", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
		
		infoFinan=this.getValorCuentasBG(infoFinan);
		//totales
		
		infoFinan.setA_3(this.getIF_A_3(infoFinan));
		infoFinan.setA_4(this.getIF_A_4(infoFinan));
		infoFinan.setA(this.getIF_A(infoFinan));
		infoFinan.setC_6(this.getIF_C_6(infoFinan));
		infoFinan.setC_7(this.getIF_C_7(infoFinan));
		infoFinan.setC_8(this.getIF_C_8(infoFinan));
		infoFinan.setC_9(this.getIF_C_9(infoFinan)); 
		infoFinan.setC(this.getIF_C(infoFinan));
		infoFinan.setE(this.getIF_E(infoFinan));
		infoFinan.setF(this.getIF_F(infoFinan));
		infoFinan.setG(this.getIF_G(infoFinan));
		infoFinan.setD(this.getIF_D(infoFinan));
		infoFinan.setB(this.getIF_B(infoFinan));
		infoFinan.setH(this.getIF_H(infoFinan));
		
//una vez obtenido el valor leido desde las cuentas, lo guardamos en BD, para el momento de su extraccion
		this.safePeriodosIF(infoFinan);
		return infoFinan;
	}
	
	private void safePeriodosIF(InformeFinancieroVO infoFinan) throws Exception{
		//consultar si existe otros periodos, si es asi, eliminarlos.
		InformeFinancieroMannager mannagerIF=new InformeFinancieroMannager();
		this.delPeriodosIF();
		mannagerIF.Insertar(infoFinan);
	}
	
	private void delPeriodosIF() throws Exception{
		//consultar si existe otros periodos, si es asi, eliminarlos.
		InformeFinancieroVO infoFinan=new InformeFinancieroVO();
		InformeFinancieroMannager mannagerIF=new InformeFinancieroMannager();
		ArrayList listaIF=mannagerIF.getTodoInformeFinanciero();
		if(listaIF.size()>0){
			Iterator it=listaIF.iterator();
			while(it.hasNext()){
				infoFinan=(InformeFinancieroVO) it.next();
				mannagerIF.Eliminar(infoFinan);
			}
		}
	}
	
	private InformeFinancieroVO getValorCuentasBG(InformeFinancieroVO infoFinan ) throws Exception{
//se cargan valores y cuentas del balance general
		LectorBalanceExcel lexel=new LectorBalanceExcel();
		LectorPropiedades lp=new LectorPropiedades();
		//String rutaIFOrigen=lp.getAtributoRepositorio("rutaIfsOrigen");
		//String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+"";
		String rutaTemporal=lp.getAtributoRepositorio("rutaLocalTemporales");
		String filename=lp.getAtributoRepositorio("nombreArchivoBalance");
						 
		ArrayList listaBalance=lexel.readWorkbook(filename,rutaTemporal);
		listaBalance.remove(0);
		Iterator itBG = listaBalance.iterator();
		BalanceGeneral bg=new BalanceGeneral();
		Iterator itCif=null;
		CuentaIF cif =null;
//se cargan las cuentas a evaluar., nos da el numero para las iteraciones...
		
		ArrayList listaCIF=this.cargarListaCuentasIF();		
		long temp=0;
		while(itBG.hasNext()){
			bg = (BalanceGeneral) itBG.next();
			temp=0;
			itCif=listaCIF.iterator();
			int vc=0;
			
			while(itCif.hasNext()){
				vc++;
				cif=(CuentaIF) itCif.next();
			}
//			a1 (posiblemente) 7002000002
			
			if(bg.getCuenta().equals("7002000002")){					
				temp=Long.parseLong(infoFinan.getA_1());
				infoFinan.setA_1(Long.parseLong(this.formatAbsoluto(bg.getResultado()))+temp);
				temp=0;
			}	
			//en espera de confirmación
			
			//a2 7002000005	
			if(bg.getCuenta().equals("7002000005")){					
				temp=Long.parseLong(infoFinan.getA_2());
				infoFinan.setA_2(Long.parseLong(this.formatAbsoluto(bg.getResultado()))+temp);
				temp=0;
			}	
			//a3 en cero
			//a31en cero
			//a32 en cero
			//a4,se carga fuera
			
			//a41 en cero
			
			//REQ-800000101 nueva cuenta - reintegros por fiscalizacion
			if(bg.getCuenta().equals("7002000004")){					
				temp=Long.parseLong(infoFinan.getA_4_1());
				infoFinan.setA_4_1(Long.parseLong(this.formatAbsoluto(bg.getResultado()))+temp);
				temp=0;
			}
			
			//a42 7002000003
			if(bg.getCuenta().equals("7002000003")){					
				temp=Long.parseLong(infoFinan.getA_4_2());
				infoFinan.setA_4_2(Long.parseLong(this.formatAbsoluto(bg.getResultado()))+temp);
				temp=0;
			}
			
			//c1 8006000009
			if(bg.getCuenta().equals("8006000009")){
				temp=Long.parseLong(infoFinan.getC_1());
				infoFinan.setC_1(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}				
			//c2 8006000010
			if(bg.getCuenta().equals("8006000010")){
				temp=Long.parseLong(infoFinan.getC_2());
				infoFinan.setC_2(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c3
			if(bg.getCuenta().equals("8006000021")){
				temp=Long.parseLong(infoFinan.getC_3());
				infoFinan.setC_3(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c4 8006000011
			if(bg.getCuenta().equals("8006000011")){
				temp=Long.parseLong(infoFinan.getC_4());
				infoFinan.setC_4(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c5 -- cero
			//c61 8006000022
			if(bg.getCuenta().equals("8006000022")){
				temp=Long.parseLong(infoFinan.getC_6_1());
				infoFinan.setC_6_1(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c62 8006000023
			if(bg.getCuenta().equals("8006000023")){
				temp=Long.parseLong(infoFinan.getC_6_2());
				infoFinan.setC_6_2(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c63 8006000024
			if(bg.getCuenta().equals("8006000024")){
				temp=Long.parseLong(infoFinan.getC_6_3());
				infoFinan.setC_6_3(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c64 8006000025
			if(bg.getCuenta().equals("8006000025")){
				temp=Long.parseLong(infoFinan.getC_6_4());
				infoFinan.setC_6_4(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c65 -cero				
			//c71 8006000026
			if(bg.getCuenta().equals("8006000026")){
				temp=Long.parseLong(infoFinan.getC_7_1());
				infoFinan.setC_7_1(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c72 8006000027
			if(bg.getCuenta().equals("8006000027")){
				temp=Long.parseLong(infoFinan.getC_7_2());
				infoFinan.setC_7_2(Long.parseLong(bg.getResultado())+temp);
			}
			//c73 8006000028
			if(bg.getCuenta().equals("8006000028")){
				temp=Long.parseLong(infoFinan.getC_7_3());
				infoFinan.setC_7_3(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c74 8006000029
			if(bg.getCuenta().equals("8006000029")){
				temp=Long.parseLong(infoFinan.getC_7_4());
				infoFinan.setC_7_4(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c75 -cero
			
			//c81 8006000030
			if(bg.getCuenta().equals("8006000030")){
				temp=Long.parseLong(infoFinan.getC_8_1());
				infoFinan.setC_8_1(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c82 8006000031
			if(bg.getCuenta().equals("8006000031")){
				temp=Long.parseLong(infoFinan.getC_8_2());
				infoFinan.setC_8_2(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c83 8006000032
			if(bg.getCuenta().equals("8006000032")){
				temp=Long.parseLong(infoFinan.getC_8_3());
				infoFinan.setC_8_3(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c84 8006000033
			if(bg.getCuenta().equals("8006000033")){
				temp=Long.parseLong(infoFinan.getC_8_4());
				infoFinan.setC_8_4(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c85 -cero				
			//c91 8006000034
			if(bg.getCuenta().equals("8006000034")){
				temp=Long.parseLong(infoFinan.getC_9_1());
				infoFinan.setC_9_1(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c92 8006000035
			if(bg.getCuenta().equals("8006000035")){
				temp=Long.parseLong(infoFinan.getC_9_2());
				infoFinan.setC_9_2(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c93 8006000036
			if(bg.getCuenta().equals("8006000036")){
				temp=Long.parseLong(infoFinan.getC_9_3());
				infoFinan.setC_9_3(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c94 8006000037
			if(bg.getCuenta().equals("8006000037")){
				temp=Long.parseLong(infoFinan.getC_9_4());
				infoFinan.setC_9_4(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//c95 -cero				
			//e1 8006000012
			if(bg.getCuenta().equals("8006000012")){
				temp=Long.parseLong(infoFinan.getE_1());
				infoFinan.setE_1(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//e2 8006000013
			if(bg.getCuenta().equals("8006000013")){
				temp=Long.parseLong(infoFinan.getE_2());
				infoFinan.setE_2(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//e3 8006000038
			if(bg.getCuenta().equals("8006000038")){
				temp=Long.parseLong(infoFinan.getE_3());
				infoFinan.setE_3(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//e4 8006000014
			if(bg.getCuenta().equals("8006000014")){
				temp=Long.parseLong(infoFinan.getE_4());
				infoFinan.setE_4(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//f1 8006000015
			if(bg.getCuenta().equals("8006000015")){
				temp=Long.parseLong(infoFinan.getF_1());
				infoFinan.setF_1(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//f2 8006000016
			if(bg.getCuenta().equals("8006000016")){
				temp=Long.parseLong(infoFinan.getF_2());
				infoFinan.setF_2(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//f3 8006000039
			if(bg.getCuenta().equals("8006000039")){
				temp=Long.parseLong(infoFinan.getF_3());
				infoFinan.setF_3(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//f4 8006000017
			if(bg.getCuenta().equals("8006000017")){
				temp=Long.parseLong(infoFinan.getF_4());
				infoFinan.setF_4(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			//g en cero
			
			//H				8006000005
			if(bg.getCuenta().equals("8006000005")){
				temp=Long.parseLong(infoFinan.getH());
				infoFinan.setH(Long.parseLong(bg.getResultado())+temp);
				temp=0;
			}
			
		}
		
		return infoFinan;
	}
	
	private long getIF_A(InformeFinancieroVO infoFinan ){
		return (Long.parseLong(infoFinan.getA_1())+Long.parseLong(infoFinan.getA_2())+Long.parseLong(infoFinan.getA_3())+
				Long.parseLong(infoFinan.getA_4()));
	}
	private long getIF_A_3(InformeFinancieroVO infoFinan ){
		return (Long.parseLong(infoFinan.getA_3_1())+Long.parseLong(infoFinan.getA_3_2()));
	}
	private long getIF_A_4(InformeFinancieroVO infoFinan ){
		return (Long.parseLong(infoFinan.getA_4_1())+Long.parseLong(infoFinan.getA_4_2()));
	}
	private long getIF_B(InformeFinancieroVO infoFinan ){
		return (Long.parseLong(infoFinan.getC())+Long.parseLong(infoFinan.getD()));
	}
	private long getIF_C(InformeFinancieroVO infoFinan ){
		return (Long.parseLong(infoFinan.getC_1())+Long.parseLong(infoFinan.getC_2())+Long.parseLong(infoFinan.getC_3())+
				Long.parseLong(infoFinan.getC_4())+Long.parseLong(infoFinan.getC_5())+Long.parseLong(infoFinan.getC_6())+
				Long.parseLong(infoFinan.getC_7())+Long.parseLong(infoFinan.getC_8())+Long.parseLong(infoFinan.getC_9()));
	}
	
	private long getIF_C_6(InformeFinancieroVO infoFinan ){
		return (Long.parseLong(infoFinan.getC_6_1())+Long.parseLong(infoFinan.getC_6_2())+Long.parseLong(infoFinan.getC_6_3())+
				Long.parseLong(infoFinan.getC_6_4())+Long.parseLong(infoFinan.getC_6_5()));
	}
	private long getIF_C_7(InformeFinancieroVO infoFinan ){
		return (Long.parseLong(infoFinan.getC_7_1())+Long.parseLong(infoFinan.getC_7_2())+Long.parseLong(infoFinan.getC_7_3())+
				Long.parseLong(infoFinan.getC_7_4())+Long.parseLong(infoFinan.getC_7_5()));
	}
	private long getIF_C_8(InformeFinancieroVO infoFinan ){
		return (Long.parseLong(infoFinan.getC_8_1())+Long.parseLong(infoFinan.getC_8_2())+Long.parseLong(infoFinan.getC_8_3())+
				Long.parseLong(infoFinan.getC_8_4())+Long.parseLong(infoFinan.getC_8_5()));
	}
	private long getIF_C_9(InformeFinancieroVO infoFinan ){
		return (Long.parseLong(infoFinan.getC_9_1())+
				Long.parseLong(infoFinan.getC_9_2())+
				Long.parseLong(infoFinan.getC_9_3())+
				Long.parseLong(infoFinan.getC_9_4())+
				Long.parseLong(infoFinan.getC_9_5()));
	}
	private long getIF_D(InformeFinancieroVO infoFinan ){
		return (Long.parseLong(infoFinan.getE())+
				Long.parseLong(infoFinan.getF())+
				Long.parseLong(infoFinan.getG()));
	}
	private long getIF_E(InformeFinancieroVO infoFinan ){
		return (Long.parseLong(infoFinan.getE_1())+
				Long.parseLong(infoFinan.getE_2())+
				Long.parseLong(infoFinan.getE_3())+
				Long.parseLong(infoFinan.getE_4())+
				Long.parseLong(infoFinan.getE_5()));
	}
	private long getIF_F(InformeFinancieroVO infoFinan ){
		return (Long.parseLong(infoFinan.getF_1())+
				Long.parseLong(infoFinan.getF_2())+
				Long.parseLong(infoFinan.getF_3())+
				Long.parseLong(infoFinan.getF_4())+
				Long.parseLong(infoFinan.getF_5()));
	}
	private long getIF_G(InformeFinancieroVO infoFinan ){
		return (Long.parseLong(infoFinan.getG_1())+
				Long.parseLong(infoFinan.getG_2())+
				Long.parseLong(infoFinan.getG_3())+
				Long.parseLong(infoFinan.getG_4()));
	}
	private long getIF_H(InformeFinancieroVO infoFinan ){
		
		//return (infoFinan.getA()-infoFinan.getB());
		return (Long.parseLong(infoFinan.getA())-
				Long.parseLong(infoFinan.getB()));
	}
	
	private ArrayList cargarListaCuentasIF(){
		LectorPropiedades lp=new LectorPropiedades();
		String nombre=null;
		ArrayList listaCIF=new ArrayList();
		CuentaIF cif=null;		
		
		cif=new CuentaIF("", "");
		nombre="APORTES_FISCALES";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="REINT_COB_INDEB";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		//REQ-8000001401
		cif=new CuentaIF("", "");
		nombre="REINT_PROD_FISC_SUSE";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="PROV_COTIZ_SUBS_MES";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="CHEQUES_CADUC_MATER";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_MatN_Revalid";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="REM_DEFICIT_MATERNAL";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="SUBSIDIOS_REEMITIDOS";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_MatN_Anulado";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="SUBS_MATERN_PRENATAL";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="SUBS_MATER_POSNATAL";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="SUBS_MATER_HIJO_ENFE";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="COT_FDO_P_PRENATAL";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="COT_FDO_P_POSNATAL";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="COT_FDO_P_HIJO_MENOR";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="COT_SALUD_RENATAL";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="COT_SALUD_POSNATAL";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="COT_SALUD_HIJO_MENOR";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_Gast_PostPar";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_PreN_Caducad";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_PosNt_Caduca";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_Par_Caducado";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_HijoEnf_Cadu";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_PreN_Nulo";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_PosNt_Nulo";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_Par_Nulo";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_HijoEnf_Nulo";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_PreN_Reemitid";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_PosNt_Reemitid";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_Par_Reemitid";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_HijoEnf_Reemitid";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_PreN_Revalid";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_PosNt_Revali";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_Par_Revalida";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="Sub_HijoEnf_Reva";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="PostPar_FondoPen";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		cif=new CuentaIF("", "");
		nombre="PostPar_FondoSal";
		cif.setNombre(nombre);
		cif.setValorCuenta(lp.getAtributoCuentasIF(nombre));
		listaCIF.add(cif);
		
		return listaCIF;
	}
	
	/**metodo que permite obtener el absoluto del valor.**/
	private String formatAbsoluto(String number){
		long n=Long.parseLong(number); 
		return Long.toString(Math.abs(n));
	}
	
	private String formatAbsoluto(long number){	
		return Long.toString(Math.abs(number));
	}
	
}
