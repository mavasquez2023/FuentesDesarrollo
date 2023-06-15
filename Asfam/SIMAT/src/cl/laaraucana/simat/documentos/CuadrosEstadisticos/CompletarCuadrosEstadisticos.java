package cl.laaraucana.simat.documentos.CuadrosEstadisticos;

import cl.laaraucana.simat.entidades.CountVO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_1_VO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_2_VO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_3_VO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_4_VO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_5_VO;
import cl.laaraucana.simat.entidades.CuadroEstadistico_6_VO;
import cl.laaraucana.simat.mannagerDB2.CE1_Mannager;
import cl.laaraucana.simat.mannagerDB2.CE2_Mannager;
import cl.laaraucana.simat.mannagerDB2.CE3_Mannager;
import cl.laaraucana.simat.mannagerDB2.CE4_Mannager;

public class CompletarCuadrosEstadisticos {

	//metodo que completara los datos del cuadro estadistico 1 aplicando las reglas en ibatis 
	public CuadroEstadistico_1_VO obtenerDatosCuadro1() throws Exception {

		int aux = 0;
		CuadroEstadistico_1_VO ce1 = new CuadroEstadistico_1_VO("", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		CE1_Mannager ce1mgr = new CE1_Mannager();
		CountVO countSQL = new CountVO();

		//prenatales
		countSQL = ce1mgr.getCE1_Num_Lic_Aut_SinModificar_Pre_natal(countSQL);
		ce1.setReposoPrenatal_Lic_SinModificacion(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Lic_Aut_Modificadas_Pre_natal(countSQL);
		ce1.setReposoPrenatal_Lic_Modificadas(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Lic_Aut_Reconsideradas_Pre_natal(countSQL);
		ce1.setReposoPrenatal_Lic_Reconsideradas(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Lic_Rechazadas_Pre_natal(countSQL);
		ce1.setReposoPrenatal_Lic_Rechazadas(countSQL.getResultadoCount());
		// total(vertical) prenatal

		countSQL = ce1mgr.getCE1_Num_Dias_Lic_Autorizados_Pre_natal(countSQL);
		ce1.setReposoPrenatal_Dias_Autorizados(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Dias_Lic_Reconsiderados_Pre_natal(countSQL);
		ce1.setReposoPrenatal_Dias_Reconsiderados(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Dias_Lic_Rechazados_Pre_natal(countSQL);
		ce1.setReposoPrenatal_Dias_Rechazados(countSQL.getResultadoCount());

		//postnatales	
		countSQL = ce1mgr.getCE1_Num_Lic_Aut_SinModificar_Post_natal(countSQL);
		ce1.setReposoPostnatal_Lic_SinModificacion(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Lic_Aut_Modificadas_Post_natal(countSQL);
		ce1.setReposoPostnatal_Lic_Modificadas(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Lic_Aut_Reconsideradas_Post_natal(countSQL);
		ce1.setReposoPostnatal_Lic_Reconsideradas(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Lic_Rechazadas_Post_natal(countSQL);
		ce1.setReposoPostnatal_Lic_Rechazadas(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Dias_Lic_Autorizados_Post_natal(countSQL);
		ce1.setReposoPostnatal_Dias_Autorizados(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Dias_Lic_Reconsiderados_Post_natal(countSQL);
		ce1.setReposoPostnatal_Dias_Reconsiderados(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Dias_Lic_Rechazados_Post_natal(countSQL);
		ce1.setReposoPostnatal_Dias_Rechazados(countSQL.getResultadoCount());

		//Enf_menor
		countSQL = ce1mgr.getCE1_Num_Lic_Aut_SinModificar_Enf_menor(countSQL);
		ce1.setEnferGravNiñoMenor1_Lic_SinModificacion(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Lic_Aut_Modificadas_Enf_menor(countSQL);
		ce1.setEnferGravNiñoMenor1_Lic_Modificadas(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Lic_Aut_Reconsideradas_Post_natal(countSQL);
		ce1.setEnferGravNiñoMenor1_Lic_Reconsideradas(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Lic_Rechazadas_Enf_menor(countSQL);
		ce1.setEnferGravNiñoMenor1_Lic_Rechazadas(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Dias_Lic_Autorizados_Enf_menor(countSQL);
		ce1.setEnferGravNiñoMenor1_Dias_Autorizados(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Dias_Lic_Rechazados_Enf_menor(countSQL);
		ce1.setEnferGravNiñoMenor1_Dias_Rechazados(countSQL.getResultadoCount());

		countSQL = ce1mgr.getCE1_Num_Dias_Lic_Reconsiderados_Enf_menor(countSQL);
		ce1.setEnferGravNiñoMenor1_Dias_Reconsiderados(countSQL.getResultadoCount());

		//total vertical(3 columnas)
		aux = ce1.getReposoPrenatal_Lic_SinModificacion() + ce1.getReposoPrenatal_Lic_Modificadas() + ce1.getReposoPrenatal_Lic_Reconsideradas() + ce1.getReposoPrenatal_Lic_Rechazadas();
		ce1.setReposoPrenatal_Lic_total(aux);

		aux = ce1.getReposoPostnatal_Lic_SinModificacion() + ce1.getReposoPostnatal_Lic_Modificadas() + ce1.getReposoPostnatal_Lic_Reconsideradas() + ce1.getReposoPostnatal_Lic_Rechazadas();
		ce1.setReposoPostnatal_Lic_total(aux);

		aux = ce1.getEnferGravNiñoMenor1_Lic_SinModificacion() + ce1.getEnferGravNiñoMenor1_Lic_Modificadas() + ce1.getEnferGravNiñoMenor1_Lic_Reconsideradas()
				+ ce1.getEnferGravNiñoMenor1_Lic_Rechazadas();
		ce1.setEnferGravNiñoMenor1_Lic_total(aux);

		//total horizontal(8 columnas)
		aux = ce1.getReposoPrenatal_Lic_SinModificacion() + ce1.getReposoPostnatal_Lic_SinModificacion() + ce1.getEnferGravNiñoMenor1_Lic_SinModificacion();
		ce1.setTotales_Lic_SinModificacion(aux);

		aux = ce1.getReposoPrenatal_Lic_Modificadas() + ce1.getReposoPostnatal_Lic_Modificadas() + ce1.getEnferGravNiñoMenor1_Lic_Modificadas();
		ce1.setTotales_Lic_Modificadas(aux);

		aux = ce1.getReposoPrenatal_Lic_Reconsideradas() + ce1.getReposoPostnatal_Lic_Reconsideradas() + ce1.getEnferGravNiñoMenor1_Lic_Reconsideradas();
		ce1.setTotales_Lic_Reconsideradas(aux);

		aux = ce1.getReposoPrenatal_Lic_Rechazadas() + ce1.getReposoPostnatal_Lic_Rechazadas() + ce1.getEnferGravNiñoMenor1_Lic_Rechazadas();
		ce1.setTotales_Lic_Rechazadas(aux);

		aux = ce1.getReposoPrenatal_Lic_total() + ce1.getReposoPostnatal_Lic_total() + ce1.getEnferGravNiñoMenor1_Lic_total();
		ce1.setTotales_Lic_total(aux);

		aux = ce1.getReposoPostnatal_Dias_Autorizados() + ce1.getEnferGravNiñoMenor1_Dias_Autorizados() + ce1.getReposoPrenatal_Dias_Autorizados();
		ce1.setTotales_Dias_Autorizados(aux);

		aux = ce1.getReposoPostnatal_Dias_Rechazados() + ce1.getEnferGravNiñoMenor1_Dias_Rechazados() + ce1.getReposoPrenatal_Dias_Rechazados();
		ce1.setTotales_Dias_Rechazados(aux);

		aux = ce1.getReposoPostnatal_Dias_Reconsiderados() + ce1.getEnferGravNiñoMenor1_Dias_Reconsiderados() + ce1.getReposoPrenatal_Dias_Reconsiderados();
		ce1.setTotales_Dias_Reconsiderados(aux);

		return ce1;

	}

	public CuadroEstadistico_2_VO obtenerDatosCuadro2() throws Exception {

		CuadroEstadistico_2_VO ce2 = new CuadroEstadistico_2_VO("", "", "", 0, 0, 0, 0, 0);
		int aux = 0;
		CE2_Mannager ce2mgr = new CE2_Mannager();
		CountVO countSQL = new CountVO();

		countSQL = ce2mgr.getCE2_Num_subs_Iniciados_Pre_natal(countSQL);
		ce2.setSub_Reposo_Prenatal(countSQL.getResultadoCount());

		countSQL = ce2mgr.getCE2_Num_subs_Iniciados_Post_natal(countSQL);
		ce2.setSub_Reposo_Postnatal(countSQL.getResultadoCount());

		countSQL = ce2mgr.getCE2_Num_subs_Iniciados_Parental(countSQL);
		ce2.setSub_Permiso_Postnatal_Parental(countSQL.getResultadoCount());

		countSQL = ce2mgr.getCE2_Num_subs_Iniciados_Enf_menor(countSQL);
		ce2.setSub_Permiso_enferm_Grave_niño_menor(countSQL.getResultadoCount());

		//obtenemos totales
		aux = ce2.getSub_Reposo_Prenatal() + ce2.getSub_Reposo_Postnatal() + ce2.getSub_Permiso_Postnatal_Parental() + ce2.getSub_Permiso_enferm_Grave_niño_menor();
		ce2.setTotales_NumSubs_iniciados(aux);

		return ce2;
	}

	public CuadroEstadistico_3_VO obtenerDatosCuadro3() throws Exception {

		CuadroEstadistico_3_VO ce3 = new CuadroEstadistico_3_VO("", "", "", 0, 0, 0, 0, 0);
		int aux = 0;
		CE3_Mannager ce3mgr = new CE3_Mannager();
		CountVO countSQL = new CountVO();

		countSQL = ce3mgr.getCE3_Sum_Dias_Pagados_Pre_natal(countSQL);
		ce3.setSub_Reposo_Prenatal(countSQL.getResultadoCount());

		countSQL = ce3mgr.getCE3_Sum_Dias_Pagados_Post_natal(countSQL);
		ce3.setSub_Reposo_Postnatal(countSQL.getResultadoCount());

		countSQL = ce3mgr.getCE3_Sum_Dias_Pagados_Enf_menor(countSQL);
		ce3.setSub_Permiso_enferm_Grave_niño_menor(countSQL.getResultadoCount());

		countSQL = ce3mgr.getCE3_Sum_Dias_Pagados_Parental(countSQL);
		ce3.setSub_Permiso_Postnatal_Parental(countSQL.getResultadoCount());

		//obtenemos totales
		aux = 0;
		aux = ce3.getSub_Reposo_Prenatal() + ce3.getSub_Reposo_Postnatal() + ce3.getSub_Permiso_Postnatal_Parental() + ce3.getSub_Permiso_enferm_Grave_niño_menor();
		ce3.setTotal_Dias_Subsidios_Pagados(aux);
		return ce3;
	}

	public CuadroEstadistico_4_VO obtenerDatosCuadro4() throws Exception {

		CuadroEstadistico_4_VO ce4 = new CuadroEstadistico_4_VO("", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0);

		CE4_Mannager ce4mgr = new CE4_Mannager();
		CountVO countSQL = new CountVO();

		countSQL = ce4mgr.getCE4_NumSubs_JornadaCompleta_Iniciados_Parental(countSQL);
		ce4.setSub_PostPar_Iniciados_Jornada_Completa(countSQL.getResultadoCount());

		countSQL = ce4mgr.getCE4_NumSubs_JornadaParcial_Iniciados_Parental(countSQL);
		ce4.setSub_PostPar_Iniciados_Jornada_Parcial(countSQL.getResultadoCount());

		countSQL = ce4mgr.getCE4_NumSubs_JornadaCompleta_Traspasados_Parental(countSQL);
		ce4.setSub_PostPar_Traspasados_Jornada_Completa(countSQL.getResultadoCount());

		countSQL = ce4mgr.getCE4_NumSubs_JornadaParcial_Traspasados_Parental(countSQL);
		ce4.setSub_PostPar_Traspasados_Jornada_Parcial(countSQL.getResultadoCount());

		countSQL = ce4mgr.getCE4_NumSubs_JornadaCompleta_Pagados_Parental(countSQL);
		ce4.setSub_PostPar_Pagados_Jornada_Completa(countSQL.getResultadoCount());

		countSQL = ce4mgr.getCE4_NumSubs_JornadaParcial_Pagados_Parental(countSQL);
		ce4.setSub_PostPar_Pagados_Jornada_Parcial(countSQL.getResultadoCount());

		//sumar totales

		ce4.setSub_PostPar_Iniciados_Total(ce4.getSub_PostPar_Iniciados_Jornada_Completa() + ce4.getSub_PostPar_Iniciados_Jornada_Parcial());
		ce4.setSub_PostPar_Traspasados_Total(ce4.getSub_PostPar_Traspasados_Jornada_Completa() + ce4.getSub_PostPar_Traspasados_Jornada_Parcial());
		ce4.setSub_PostPar_Pagados_Total(ce4.getSub_PostPar_Pagados_Jornada_Completa() + ce4.getSub_PostPar_Pagados_Jornada_Parcial());

		return ce4;
	}

	public CuadroEstadistico_5_VO obtenerDatosCuadro5() {
		//valores siempre en cero
		CuadroEstadistico_5_VO ce5 = new CuadroEstadistico_5_VO("", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		return ce5;
	}

	public CuadroEstadistico_6_VO obtenerDatosCuadro6() {
		//valores siempre en cero
		CuadroEstadistico_6_VO ce6 = new CuadroEstadistico_6_VO("", "", "", 0, 0, 0, 0, 0, 0, 0, 0);
		return ce6;
	}

}//end class
