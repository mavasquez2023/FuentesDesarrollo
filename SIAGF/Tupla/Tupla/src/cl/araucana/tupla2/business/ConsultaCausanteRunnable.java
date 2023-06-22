package cl.araucana.tupla2.business;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import cl.araucana.tupla2.exception.FatalException;
import cl.araucana.tupla2.exception.Tupla2Exception;
import cl.araucana.tupla2.multithread.Queue;
import cl.araucana.tupla2.struts.bussiness.TO.SqlParametersTO;
import cl.araucana.tupla2.struts.bussiness.TO.TramoTO;
import cl.araucana.tupla2.struts.bussiness.TO.TuplaTO;
import cl.araucana.tupla2.struts.jdbcDAO.Araucanajdbcdao;
import cl.araucana.tupla2.struts.utils.ClienteSIAGF;
import cl.araucana.tupla2.struts.utils.Contadores;

public class ConsultaCausanteRunnable extends Thread {
	private Queue q;
	SqlParametersTO oSql;
	private String rut;
	private Contadores contador;
	private FileWriter fatalErrFile;
	private FileWriter fw;

	public ConsultaCausanteRunnable(Queue q, String str, Contadores counts, FileWriter fatal, FileWriter error, SqlParametersTO sqlParams) {
		super(str);
		this.q = q;
		String[] v = str.split(Pattern.quote("|"));
		if (v.length > 1) {
			rut = v[1];
		} else
			rut = str;
		this.contador = counts;
		this.fatalErrFile = fatal;
		this.fw = error;
		this.oSql = sqlParams;
	}

	@Override
	public void run() {
		System.out.println(this.getName() + " trabajando....");

		Araucanajdbcdao dao = new Araucanajdbcdao();
		contador.addRutProcesados(); //countRuts++;

		//Consulta Rut
		ClienteSIAGF siagf = new ClienteSIAGF();
		ArrayList tuplasCausanteList = null;
		int rutInt= 0;
		try {
			String temp[] = rut.split("-");
			rutInt = Integer.parseInt(temp[0]);
			String dvRut = temp[1];
			System.out.println("Consultando por Tuplas RUT: "+rut);
			int numtuplas=0;
			tuplasCausanteList = siagf.consultaCausante(rut, oSql.isRecXml());
			numtuplas= tuplasCausanteList.size();
			//String periodo = dao.getPeriodoTramoVigente();
			
			//Por cada tupla.....
			TuplaTO oTupla = null;
			int countOK=0;
			for (int i = 0; i < numtuplas; i++) {
				oTupla = (TuplaTO) tuplasCausanteList.get(i);
							
				String estado = "N";
				/*if (dao.getEstadoA(rutInt, oTupla.getFecRecCausante(), oTupla.getFecExtCausante(), oSql) > 0)
					estado = "A";
				else if (dao.getEstadoB(rutInt, oTupla.getFecRecCausante(), oTupla.getFecExtCausante(), oSql) > 0)
					estado = "B";
				else if (dao.getEstadoC(rutInt, oTupla.getFecRecCausante(), oTupla.getFecExtCausante(), oSql) > 0)
					estado = "C";
				else if (dao.getEstadoD(rutInt, oTupla.getFecRecCausante(), oTupla.getFecExtCausante(), oSql) > 0)
					estado = "D";
				else if (dao.getEstadoE(rutInt, oTupla.getFecRecCausante(), oTupla.getFecExtCausante(), oSql) > 0)
					estado = "E";
*/
				oTupla.setEstado(estado);
				
				normalizeTupla(oTupla);
				contador.addTupla(); //countTupla++;
				
				oTupla.setId( contador.getCountTuplas() + "");
				System.out.println("Tupla:" + oTupla.getId());
				
				String tuplaResult = dao.guardarTupla(oTupla, oSql);
				if (tuplaResult != null) {
					contador.addError(); //error++;
					try {
						System.out.println("No es posible grabar tupla rut " + rut + ", fecha rec.: " + oTupla.getFecRecCausante() + " (" + tuplaResult + ")");
						fw.write("No es posible grabar tupla rut " + rut + ", fecha rec.: " + oTupla.getFecRecCausante() + " (" + tuplaResult + ")");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//throw new Tupla2Exception("No es posible grabar tupla rut " + rut + " (" + tuplaResult + ")", "" + contador.getCountTuplas());
				}else{
					
					ArrayList tramos = oTupla.getTramo();
					if(tramos != null){
						//int periodoVigenteAnio = Integer.parseInt(periodo.substring(0, 4));
						//System.out.println("============== el anio es "+periodoVigenteAnio);
						for (int j = 0; j < tramos.size(); j++) {
							TramoTO oTramo = (TramoTO) tramos.get(j);
							oTramo.setId(contador.getCountTuplas() + "");
							dao.guardarTramo(oTramo, oSql);
						}
					}
					countOK++;
				}
			}
			if(countOK> 0 ){
				dao.updateConsulta(oSql, rutInt, 1, "OK");
			}
			if(numtuplas>0 && countOK==0){
				dao.updateConsulta(oSql, rutInt, -1, "Tuplas ya Procesadas");
			}
			
		} catch (Tupla2Exception e) {
			try {
				synchronized (q) {
					dao.updateConsulta(oSql, rutInt, -1, e.getAppMsg());
					fw.write(this.getName() + "|" + e.getAppCode() + "|" + e.getAppMsg() + System.getProperty("line.separator"));
				}
			} catch (IOException f) {
				f.printStackTrace();
			}
		} catch (FatalException e) {
			try {
				synchronized (q) {
					dao.updateConsulta(oSql, rutInt, -1, e.getAppMsg());
					fatalErrFile.write(this.getName() + "|" + e.getAppCode() + "|" + e.getAppMsg() + System.getProperty("line.separator"));
				}
			} catch (IOException f) {
				f.printStackTrace();
			}
		}
		System.out.println(this.getName() + " Termina thread");
		q.endProcess();
	}

	private void normalizeTupla(TuplaTO oTupla) {
		if (oTupla.getTupla() == null)
			oTupla.setTupla("0");
		if (oTupla.getCodigo() == null)
			oTupla.setCodigo("0");
		if (oTupla.getFechaEmision() == null)
			oTupla.setFechaEmision("00010101");
		if (oTupla.getTrackID() == null)
			oTupla.setTrackID("0");
		if (oTupla.getCodEstadoTupla() == null)
			oTupla.setCodEstadoTupla("0");
		if (oTupla.getNomEstadoTupla() == null)
			oTupla.setNomEstadoTupla("");
		if (oTupla.getCodTipoCausante() == null)
			oTupla.setCodTipoCausante("0");
		if (oTupla.getNomCausante() == null)
			oTupla.setNomCausante("");
		if (oTupla.getNomTipoCausante() == null)
			oTupla.setNomTipoCausante("");
		if (oTupla.getSexoCausante() == null)
			oTupla.setSexoCausante("");
		if (oTupla.getFecNacCausante() == null)
			oTupla.setFecNacCausante("00010101");
		if (oTupla.getCodRegionCausante() == null)
			oTupla.setCodRegionCausante("0");
		if (oTupla.getNomRegionCausante() == null)
			oTupla.setNomRegionCausante("");
		if (oTupla.getCodComunaCausante() == null)
			oTupla.setCodComunaCausante("0");
		if (oTupla.getNomComunaCausante() == null)
			oTupla.setNomComunaCausante("");
		if (oTupla.getCodTipoBeneficiario() == null)
			oTupla.setCodTipoBeneficiario("0");
		if (oTupla.getCodTipoBeneficiario() == null)
			oTupla.setCodTipoBeneficiario("");
		if (oTupla.getRutBeneficiario() == null)
			oTupla.setRutBeneficiario("0");
		if (oTupla.getNomBeneficiario() == null)
			oTupla.setNomBeneficiario("");
		if (oTupla.getCodRegionBeneficiario() == null)
			oTupla.setCodRegionBeneficiario("0");
		if (oTupla.getNomRegionBeneficiario() == null)
			oTupla.setNomRegionBeneficiario("");
		if (oTupla.getCodComunaBeneficiario() == null)
			oTupla.setCodComunaBeneficiario("0");
		if (oTupla.getNomComunaBeneficiario() == null)
			oTupla.setNomComunaBeneficiario("");
		if (oTupla.getIngPromedio() == null)
			oTupla.setIngPromedio("0");
		if (oTupla.getNomComunaEmpleador() == null)
			oTupla.setNomComunaEmpleador("");
		if (oTupla.getRutEmpleador() == null)
			oTupla.setRutEmpleador("0");
		if (oTupla.getDVEmpleador() == null)
			oTupla.setDVEmpleador("0");
		if (oTupla.getNomEmpleador() == null)
			oTupla.setNomEmpleador("");
		if (oTupla.getActeco() == null)
			oTupla.setActeco("0");
		if (oTupla.getCodRegionEmpleador() == null)
			oTupla.setCodRegionEmpleador("0");
		if (oTupla.getNomRegionEmpleador() == null)
			oTupla.setNomRegionEmpleador("");
		if (oTupla.getCodComunaEmpleador() == null)
			oTupla.setCodComunaEmpleador("0");
		if (oTupla.getFechaTransaccion() == null)
			oTupla.setFechaTransaccion("00010101");
		if (oTupla.getCodEntidadAdm() == null)
			oTupla.setCodEntidadAdm("0");
		if (oTupla.getNomEntidadAdm() == null)
			oTupla.setNomEntidadAdm("");
		if (oTupla.getCodTipoBeneficio() == null)
			oTupla.setCodTipoBeneficio("0");
		if (oTupla.getNomTipoBeneficio() == null)
			oTupla.setNomTipoBeneficio("");
		if (oTupla.getFecRecCausante() == null)
			oTupla.setFecRecCausante("00010101");
		if (oTupla.getFecPagoBeneficio() == null)
			oTupla.setFecPagoBeneficio("00010101");
		if (oTupla.getMontoUnitarioBeneficio() == null)
			oTupla.setMontoUnitarioBeneficio("0");
		if (oTupla.getPuntajeFichaProtSocial() == null)
			oTupla.setPuntajeFichaProtSocial("0");
		if (oTupla.getTramoAsigFam() == null)
			oTupla.setTramoAsigFam("0");
		if (oTupla.getPeriodo() == null)
			oTupla.setPeriodo("0");
		if (oTupla.getNumtramo() == null)
			oTupla.setNumtramo("0");
		if (oTupla.getIngPromedioTramo() == null)
			oTupla.setIngPromedioTramo("0");
		if (oTupla.getMontoUnitarioBeneficiottramo() == null)
			oTupla.setMontoUnitarioBeneficiottramo("0");
		if (oTupla.getCausaExtCausante() == null)
			oTupla.setCausaExtCausante("0");
		if (oTupla.getFecExtCausante() == null)
			oTupla.setFecExtCausante("30000101");
		if (oTupla.getGlosaExtCausante() == null)
			oTupla.setGlosaExtCausante("");
		if (oTupla.getGlosaExtCausante() == null)
			oTupla.setGlosaExtCausante("");
	
		oTupla.setAfsiRube(oTupla.getRutCausante());
		oTupla.setDvBene(oTupla.getDVCausante());
		
		oTupla.setAfiliado(oTupla.getRutBeneficiario());
		oTupla.setDvAfiliado(oTupla.getDVBeneficiario());
		if (oTupla.getTramo1() == null)
			oTupla.setTramo1("0");
		if (oTupla.getTramo2() == null)
			oTupla.setTramo2("0");
		if (oTupla.getTramo3() == null)
			oTupla.setTramo3("0");
		if (oTupla.getTramo4() == null)
			oTupla.setTramo4("0");
		
		
		
	}
}
