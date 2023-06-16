package cl.laaraucana.simat.documentos.ArchivosPlanos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import cl.laaraucana.simat.mannagerDB2.ControlDocuMannager;
import cl.laaraucana.simat.mannagerDB2.DatosLicCobMannager;
import cl.laaraucana.simat.mannagerDB2.DatosLicResolMannager;
import cl.laaraucana.simat.mannagerDB2.DocsRevalReemMannager;
import cl.laaraucana.simat.mannagerDB2.ProcedimientosMannager;
import cl.laaraucana.simat.mannagerDB2.ReintegrosMannager;
import cl.laaraucana.simat.mannagerDB2.SubsParentalMannager;
import cl.laaraucana.simat.mannagerDB2.SubsPrePostNmMannager;
import cl.laaraucana.simat.utiles.LectorPropiedades;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

public class CompletarArchivosPlanos {

	public boolean completarPlano_1(String periodo) throws Exception {
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		String nameFile = null;

		int tabla = 1;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		String keyFH = hfa.getKeyFechaHora();
		System.out.println("keyFA: " + keyFH);
		//t1 reintegros			
		ArrayList listaReintegros = new ArrayList();
		ReintegrosMannager mannagerT1 = new ReintegrosMannager();
		listaReintegros = (ArrayList) mannagerT1.BuscarTodo();
		System.out.println("Lista t1 generada, tamaño: " + listaReintegros.size());
		LectorPropiedades lp = new LectorPropiedades();
		ProcedimientosMannager pm = new ProcedimientosMannager();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		//EscritorArchivosPlanosSMB eapSMB=new EscritorArchivosPlanosSMB();
		EscritorArchivosPlanosIFS eapIFS = new EscritorArchivosPlanosIFS();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		//* * * antes de generar, se llama  a copia de historico:* * * * 			
		pm.callProcedureRespaldoHistorico(periodo, String.valueOf(tabla));
		//escribir en TXT	
		nameFile = codEntidad + "_REINTEGROS_" + periodo + ".txt";
		key_a = eapIFS.escitorSMF01(periodo, nameFile, listaReintegros, sdf);
		//escribir en csv
		nameFile = codEntidad + "_REINTEGROS_" + periodo + ".csv";
		key_b = eapIFS.escitorSMF01(periodo, nameFile, listaReintegros, sdf);
		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}
		return key_res;
	}//END completarPlano_1

	public boolean completarPlano_2(String periodo) throws Exception {
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		String key = null;
		int tabla = 2;
		String nameFile = null;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		String keyFH = hfa.getKeyFechaHora();
		System.out.println("keyFA: " + keyFH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		//EscritorArchivosPlanosSMB eapSMB=new EscritorArchivosPlanosSMB();
		EscritorArchivosPlanosIFS eapIFS = new EscritorArchivosPlanosIFS();
		//t2 subsprepostnm
		ArrayList listaSubsPrePostNM = new ArrayList();
		SubsPrePostNmMannager mannagerT2 = new SubsPrePostNmMannager();
		listaSubsPrePostNM = (ArrayList) mannagerT2.BuscarTodo();
		System.out.println("Lista t2 generada, tamaño: " + listaSubsPrePostNM.size());
		tabla = 2;
		//antes de generar, se llama  a copia de historico:
		ProcedimientosMannager pm = new ProcedimientosMannager();
		pm.callProcedureRespaldoHistorico(periodo, String.valueOf(tabla));
		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		//nameFile="Subsprepostnm_"+keyFH+".txt";
		nameFile = codEntidad + "_SUBSPREPOSTNM_" + periodo + ".txt";
		//this.adaptarDatos(listaSubsPrePostNM,tabla,nameFile,periodo);
		key_a = eapIFS.escitorSMF02(periodo, nameFile, listaSubsPrePostNM, sdf);
		nameFile = codEntidad + "_SUBSPREPOSTNM_" + periodo + ".csv";
		key_b = eapIFS.escitorSMF02(periodo, nameFile, listaSubsPrePostNM, sdf);

		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}
		return key_res;
	}

	public boolean completarPlano_3(String periodo) throws Exception {
		String key = null;
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		int tabla = 0;
		String nameFile = null;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		String keyFH = hfa.getKeyFechaHora();
		System.out.println("keyFA: " + keyFH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//EscritorArchivosPlanosSMB eapSMB=new EscritorArchivosPlanosSMB();
		EscritorArchivosPlanosIFS eapIFS = new EscritorArchivosPlanosIFS();
		//t3 subsparental
		ArrayList listaSubsParental = new ArrayList();
		SubsParentalMannager mannagerT3 = new SubsParentalMannager();
		listaSubsParental = (ArrayList) mannagerT3.BuscarTodo();
		System.out.println("Lista t3 generada, tamaño: " + listaSubsParental.size());
		tabla = 3;
		//antes de generar, se llama  a copia de historico:
		ProcedimientosMannager pm = new ProcedimientosMannager();
		pm.callProcedureRespaldoHistorico(periodo, String.valueOf(tabla));
		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");

		nameFile = codEntidad + "_SUBSPARENTAL_" + periodo + ".txt";
		key_a = eapIFS.escitorSMF03(periodo, nameFile, listaSubsParental, sdf);
		nameFile = codEntidad + "_SUBSPARENTAL_" + periodo + ".csv";
		key_b = eapIFS.escitorSMF03(periodo, nameFile, listaSubsParental, sdf);

		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}
		return key_res;
	}

	public boolean completarPlano_4(String periodo) throws Exception {
		String key = null;
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		int tabla = 0;
		String nameFile = null;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		String keyFH = hfa.getKeyFechaHora();
		System.out.println("keyFA: " + keyFH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//EscritorArchivosPlanosSMB eapSMB=new EscritorArchivosPlanosSMB();
		EscritorArchivosPlanosIFS eapIFS = new EscritorArchivosPlanosIFS();
		//		t4 substsvig
		//debe ir vacio, no requiere consulta con mannager a db2

		//SubsTscVigMannager mannagerT4 =new SubsTscVigMannager();
		ArrayList listaSubsTscVig = new ArrayList();
		//listaSubsTscVig=(ArrayList)mannagerT4.BuscarTodo();

		System.out.println("Lista t4 generada, tamaño: " + listaSubsTscVig.size());
		tabla = 4;
		//antes de generar, se llama  a copia de historico:
		ProcedimientosMannager pm = new ProcedimientosMannager();
		//pm.callProcedureRespaldoHistorico(periodo, String.valueOf(tabla));
		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");
		//nameFile="Substscvig_"+keyFH+".txt";
		nameFile = codEntidad + "_SUBSTSCVIG_" + periodo + ".txt";
		key_a = eapIFS.escitorSMF04(periodo, nameFile, listaSubsTscVig, sdf);
		//nameFile="Substscvig_"+keyFH+".csv";
		nameFile = codEntidad + "_SUBSTSCVIG_" + periodo + ".csv";
		key_b = eapIFS.escitorSMF04(periodo, nameFile, listaSubsTscVig, sdf);

		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}
		return key_res;
	}

	public boolean completarPlano_5(String periodo) throws Exception {
		String key = null;
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		int tabla = 0;
		String nameFile = null;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		String keyFH = hfa.getKeyFechaHora();
		System.out.println("keyFA: " + keyFH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//EscritorArchivosPlanosSMB eapSMB=new EscritorArchivosPlanosSMB();
		EscritorArchivosPlanosIFS eapIFS = new EscritorArchivosPlanosIFS();
		//		t5 controldocu			
		ControlDocuMannager mannagerT5 = new ControlDocuMannager();
		ArrayList listaControlDocu = new ArrayList();
		listaControlDocu = (ArrayList) mannagerT5.BuscarTodo();
		System.out.println("Lista t5 generada, tamaño: " + listaControlDocu.size());
		tabla = 5;
		//antes de generar, se llama  a copia de historico:
		ProcedimientosMannager pm = new ProcedimientosMannager();
		pm.callProcedureRespaldoHistorico(periodo, String.valueOf(tabla));

		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");

		nameFile = codEntidad + "_CONTROLDOCU_" + periodo + ".txt";
		key_a = eapIFS.escitorSMF05(periodo, nameFile, listaControlDocu, sdf);
		nameFile = codEntidad + "_CONTROLDOCU_" + periodo + ".csv";
		key_b = eapIFS.escitorSMF05(periodo, nameFile, listaControlDocu, sdf);

		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}
		return key_res;
	}

	public boolean completarPlano_6(String periodo) throws Exception {
		String key = null;
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		int tabla = 0;
		String nameFile = null;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		String keyFH = hfa.getKeyFechaHora();
		System.out.println("keyFA: " + keyFH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//EscritorArchivosPlanosSMB eapSMB=new EscritorArchivosPlanosSMB();
		EscritorArchivosPlanosIFS eapIFS = new EscritorArchivosPlanosIFS();
		//t6 docsrevalreem	
		DocsRevalReemMannager mannagerT6 = new DocsRevalReemMannager();
		ArrayList listaDocsRevalReem = new ArrayList();
		listaDocsRevalReem = (ArrayList) mannagerT6.BuscarTodo();
		System.out.println("Lista t6 generada, tamaño: " + listaDocsRevalReem.size());
		tabla = 6;
		//antes de generar, se llama  a copia de historico:
		ProcedimientosMannager pm = new ProcedimientosMannager();
		pm.callProcedureRespaldoHistorico(periodo, String.valueOf(tabla));
		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");

		nameFile = codEntidad + "_DOCSREVALREEM_" + periodo + ".txt";
		key_a = eapIFS.escitorSMF06(periodo, nameFile, listaDocsRevalReem, sdf);
		nameFile = codEntidad + "_DOCSREVALREEM_" + periodo + ".csv";
		key_b = eapIFS.escitorSMF06(periodo, nameFile, listaDocsRevalReem, sdf);

		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}
		return key_res;
	}

	public boolean completarPlano_7(String periodo) throws Exception {
		String key = null;
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		int tabla = 0;
		String nameFile = null;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		String keyFH = hfa.getKeyFechaHora();
		System.out.println("keyFA: " + keyFH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//EscritorArchivosPlanosSMB eapSMB=new EscritorArchivosPlanosSMB();
		EscritorArchivosPlanosIFS eapIFS = new EscritorArchivosPlanosIFS();
		//t7 datosliccob
		ArrayList listaDatosLicCob = new ArrayList();
		DatosLicCobMannager mannagerT7 = new DatosLicCobMannager();
		listaDatosLicCob = (ArrayList) mannagerT7.BuscarTodo();
		System.out.println("Lista t7 generada, tamaño: " + listaDatosLicCob.size());
		tabla = 7;
		//antes de generar, se llama  a copia de historico:
		ProcedimientosMannager pm = new ProcedimientosMannager();
		pm.callProcedureRespaldoHistorico(periodo, String.valueOf(tabla));
		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");

		nameFile = codEntidad + "_DATOSLICCOB_" + periodo + ".txt";
		key_a = eapIFS.escitorSMF07(periodo, nameFile, listaDatosLicCob, sdf);
		nameFile = codEntidad + "_DATOSLICCOB_" + periodo + ".csv";
		key_b = eapIFS.escitorSMF07(periodo, nameFile, listaDatosLicCob, sdf);

		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}
		return key_res;
	}

	public boolean completarPlano_8(String periodo) throws Exception {
		String key = null;
		boolean key_a = false;
		boolean key_b = false;
		boolean key_res = false;
		int tabla = 0;
		String nameFile = null;
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		String keyFH = hfa.getKeyFechaHora();
		System.out.println("keyFA: " + keyFH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//EscritorArchivosPlanosSMB eapSMB=new EscritorArchivosPlanosSMB();
		EscritorArchivosPlanosIFS eapIFS = new EscritorArchivosPlanosIFS();
		//t8 datoslicresol
		ArrayList listaDatosLicResol = new ArrayList();
		DatosLicResolMannager mannagerT8 = new DatosLicResolMannager();
		listaDatosLicResol = (ArrayList) mannagerT8.BuscarTodo();
		System.out.println("Lista t78 generada, tamaño: " + listaDatosLicResol.size());
		tabla = 8;
		//antes de generar, se llama  a copia de historico:
		ProcedimientosMannager pm = new ProcedimientosMannager();
		pm.callProcedureRespaldoHistorico(periodo, String.valueOf(tabla));
		LectorPropiedades lp = new LectorPropiedades();
		String codEntidad = lp.getAtributoRepositorio("codigo_entidad");

		nameFile = codEntidad + "_DATOSLICRESOL_" + periodo + ".txt";
		key_a = eapIFS.escitorSMF08(periodo, nameFile, listaDatosLicResol, sdf);
		nameFile = codEntidad + "_DATOSLICRESOL_" + periodo + ".csv";
		key_b = eapIFS.escitorSMF08(periodo, nameFile, listaDatosLicResol, sdf);

		if (key_a && key_b) {
			key_res = true;
		} else {
			key_res = false;
		}
		return key_res;
	}//end completarPlano_8	

}//end completarArchivosPlanos
