package cl.laaraucana.simat.documentos.ILF4501;

import cl.laaraucana.simat.entidades.CountVO;
import cl.laaraucana.simat.entidades.CuadroILF4501VO;
import cl.laaraucana.simat.entidades.SumVO;
import cl.laaraucana.simat.mannagerDB2.Count_Lic_autorizadasMannager;
import cl.laaraucana.simat.mannagerDB2.Count_Pers_SubsidiadasMannager;
import cl.laaraucana.simat.mannagerDB2.Count_Subs_IniciadosMannager;
import cl.laaraucana.simat.mannagerDB2.Sum_Dias_Subs_PagadosMannager;
import cl.laaraucana.simat.mannagerDB2.Sum_Monto_Subs_PagadosMannager;

/*
 *clase que ejecuta querys en ibatis para hacer, conteo y sumatoria de campos de los datos de las tablas para generar el cuadro ilf4501
 * */

public class CompletarILF4501 {

	public CuadroILF4501VO completadorILF4501(CuadroILF4501VO ilf) throws Exception {

		//[columna 1] numero de licencias autorizadas
		ilf = this.getLicenciasAutorizadas(ilf);

		//[columna 2]numero de subsidios iniciados
		ilf = this.getSubsIniciados(ilf);

		//[columna 3]numero de Dias subs pagados
		ilf = this.getDiasSubPagados(ilf);
		//[columna 4] numero de personas subsidiadas: contar rut distintos plano 2 y 3
		ilf = this.getPersonasSubsidiadas(ilf);
		//[columna 5] montos de subsidio pagados. 
		ilf = this.getMontoPagadoSubs(ilf);
		//[fila total]
		ilf = this.getTotalILF(ilf);

		return ilf;
	}

	public CuadroILF4501VO getLicenciasAutorizadas(CuadroILF4501VO ilf) throws Exception {
		//aplicara reglas de cuadro estadistico 1 (contar licencias autorizadas)mas evaluacion de vinculo con beneficiario.
		//columna 1_ total autorizadas por reposo prenatal

		CountVO countSQL = new CountVO();
		Count_Lic_autorizadasMannager counLicMannager = new Count_Lic_autorizadasMannager();

		countSQL = counLicMannager.getNum_Lic_Autorizadas_Pre_natal(countSQL);
		ilf.setRep_Prenatal_Lic_autorizadas(countSQL.getResultadoCount());
		countSQL = counLicMannager.getNum_Lic_Autorizadas_Post_natal_Madre(countSQL);
		ilf.setRep_Postnatal_madre_Lic_autorizadas(countSQL.getResultadoCount());
		countSQL = counLicMannager.getNum_Lic_Autorizadas_Post_natal_Padre(countSQL);
		ilf.setRep_Postnatal_padre_Lic_autorizadas(countSQL.getResultadoCount());
		/* sin regla de llenado en cuadro estadistico 1*/
		ilf.setPer_Parental_madre_Lic_autorizadas(0);
		ilf.setPer_Parental_padre_Lic_autorizadas(0);

		countSQL = counLicMannager.getNum_Lic_Autorizadas_Enf_menor_Madre(countSQL);
		ilf.setPer_EnfHijoMenor1_madre_Lic_autorizadas(countSQL.getResultadoCount());
		countSQL = counLicMannager.getNum_Lic_Autorizadas_Enf_menor_Padre(countSQL);
		ilf.setPer_EnfHijoMenor1_padre_Lic_autorizadas(countSQL.getResultadoCount());

		return ilf;
	}//end getLicAutorizadas

	public CuadroILF4501VO getSubsIniciados(CuadroILF4501VO ilf) throws Exception {
		//columna 2_ subsidios iniciados, reglas de conteo igual al cuadro estadistico 2, agregado vinvulo beneficiario menor
		CountVO countSQL = new CountVO();
		Count_Subs_IniciadosMannager count_Subs_IniciadosMannager = new Count_Subs_IniciadosMannager();

		countSQL = count_Subs_IniciadosMannager.getNum_subs_Iniciados_Pre_natal(countSQL);
		ilf.setRep_Prenatal_Subs_Iniciados(countSQL.getResultadoCount());

		countSQL = count_Subs_IniciadosMannager.getNum_subs_Iniciados_Post_natal_Madre(countSQL);
		ilf.setRep_Postnatal_madre_Subs_Iniciados(countSQL.getResultadoCount());

		countSQL = count_Subs_IniciadosMannager.getNum_subs_Iniciados_Post_natal_Padre(countSQL);
		ilf.setRep_Postnatal_padre_Subs_Iniciados(countSQL.getResultadoCount());

		countSQL = count_Subs_IniciadosMannager.getNum_subs_Iniciados_Parental_Madre(countSQL);
		ilf.setPer_Parental_madre_Subs_Iniciados(countSQL.getResultadoCount());

		countSQL = count_Subs_IniciadosMannager.getNum_subs_Iniciados_Parental_Padre(countSQL);
		ilf.setPer_Parental_padre_Subs_Iniciados(countSQL.getResultadoCount());

		countSQL = count_Subs_IniciadosMannager.getNum_subs_Iniciados_Enf_menor_Madre(countSQL);
		ilf.setPer_EnfHijoMenor1_madre_Subs_Iniciados(countSQL.getResultadoCount());

		countSQL = count_Subs_IniciadosMannager.getNum_subs_Iniciados_Enf_menor_Padre(countSQL);
		ilf.setPer_EnfHijoMenor1_padre_Subs_Iniciados(countSQL.getResultadoCount());

		return ilf;
	}//end getSubsIniciados

	public CuadroILF4501VO getDiasSubPagados(CuadroILF4501VO ilf) throws Exception {
		//reglas de cuadro estadisto 3 mas filtro por vinculo menor

		CountVO countSQL = new CountVO();
		Sum_Dias_Subs_PagadosMannager sum_Dias_Subs_PagadosMannager = new Sum_Dias_Subs_PagadosMannager();

		countSQL = sum_Dias_Subs_PagadosMannager.getSum_Dias_Pagados_Pre_natal(countSQL);
		ilf.setRep_Prenatal_Dias_subPagados(countSQL.getResultadoCount());

		countSQL = sum_Dias_Subs_PagadosMannager.getSum_Dias_Pagados_Post_natal_Madre(countSQL);
		ilf.setRep_Postnatal_madre_Dias_subPagados(countSQL.getResultadoCount());

		countSQL = sum_Dias_Subs_PagadosMannager.getSum_Dias_Pagados_Post_natal_Padre(countSQL);
		ilf.setRep_Postnatal_padre_Dias_subPagados(countSQL.getResultadoCount());

		countSQL = sum_Dias_Subs_PagadosMannager.getSum_Dias_Pagados_Parental_Madre(countSQL);
		ilf.setPer_Parental_madre_Dias_subPagados(countSQL.getResultadoCount());

		countSQL = sum_Dias_Subs_PagadosMannager.getSum_Dias_Pagados_Parental_Padre(countSQL);
		ilf.setPer_Parental_padre_Dias_subPagados(countSQL.getResultadoCount());

		countSQL = sum_Dias_Subs_PagadosMannager.getSum_Dias_Pagados_Enf_menor_Madre(countSQL);
		ilf.setPer_EnfHijoMenor1_madre_Dias_subPagados(countSQL.getResultadoCount());

		countSQL = sum_Dias_Subs_PagadosMannager.getSum_Dias_Pagados_Enf_menor_Padre(countSQL);
		ilf.setPer_EnfHijoMenor1_padre_Dias_subPagados(countSQL.getResultadoCount());

		return ilf;
	}//end getSubsIniciados

	public CuadroILF4501VO getPersonasSubsidiadas(CuadroILF4501VO ilf) throws Exception {
		//columna 4 personas subsidiadas.
		CountVO qps = new CountVO();
		Count_Pers_SubsidiadasMannager qpsMannager = new Count_Pers_SubsidiadasMannager();

		//reglas para contar rut distintos en tablas plano 2(subsprepostNm) y 3(subsParental)
		qps = qpsMannager.num_pers_subs_Pre_natal(qps);
		ilf.setRep_Prenatal_Personas_Subsidiadas(qps.getResultadoCount());
		qps = qpsMannager.num_pers_subs_Post_natal_Madre(qps);
		ilf.setRep_Postnatal_madre_Personas_Subsidiadas(qps.getResultadoCount());
		qps = qpsMannager.num_pers_subs_Post_natal_Padre(qps);
		ilf.setRep_Postnatal_padre_Personas_Subsidiadas(qps.getResultadoCount());
		qps = qpsMannager.num_pers_subs_parental_Madre(qps);
		ilf.setPer_Parental_madre_Personas_Subsidiadas(qps.getResultadoCount());
		qps = qpsMannager.num_pers_subs_parental_Padre(qps);
		ilf.setPer_Parental_padre_Personas_Subsidiadas(qps.getResultadoCount());
		qps = qpsMannager.num_pers_subs_Enf_menor_Madre(qps);
		ilf.setPer_EnfHijoMenor1_madre_Personas_Subsidiadas(qps.getResultadoCount());
		qps = qpsMannager.num_pers_subs_Enf_menor_Padre(qps);
		ilf.setPer_EnfHijoMenor1_padre_Personas_Subsidiadas(qps.getResultadoCount());

		return ilf;
	}//end getSubsIniciados

	public CuadroILF4501VO getMontoPagadoSubs(CuadroILF4501VO ilf) throws Exception {
		//columna 5_ total autorizadas por reposo prenatal

		SumVO countSQL = new SumVO();
		Sum_Monto_Subs_PagadosMannager sum_Monto_Subs_PagadosMannager = new Sum_Monto_Subs_PagadosMannager();

		countSQL = sum_Monto_Subs_PagadosMannager.getSum_Monto_SubsPagado_Pre_natal(countSQL);
		ilf.setRep_Prenatal_Monto_Pagado_Subs(countSQL.getResultadoSum());

		countSQL = sum_Monto_Subs_PagadosMannager.getSum_Monto_SubsPagado_Post_natal_Madre(countSQL);
		ilf.setRep_Postnatal_madre_Monto_Pagado_Subs(countSQL.getResultadoSum());

		countSQL = sum_Monto_Subs_PagadosMannager.getSum_Monto_SubsPagado_Post_natal_Padre(countSQL);
		ilf.setRep_Postnatal_padre_Monto_Pagado_Subs(countSQL.getResultadoSum());

		countSQL = sum_Monto_Subs_PagadosMannager.getSum_Monto_SubsPagado_Parental_Madre(countSQL);
		ilf.setPer_Parental_madre_Monto_Pagado_Subs(countSQL.getResultadoSum());

		countSQL = sum_Monto_Subs_PagadosMannager.getSum_Monto_SubsPagado_Parental_Padre(countSQL);
		ilf.setPer_Parental_padre_Monto_Pagado_Subs(countSQL.getResultadoSum());

		countSQL = sum_Monto_Subs_PagadosMannager.getSum_Monto_SubsPagado_Enf_menor_Madre(countSQL);
		ilf.setPer_EnfHijoMenor1_madre_Monto_Pagado_Subs(countSQL.getResultadoSum());

		countSQL = sum_Monto_Subs_PagadosMannager.getSum_Monto_SubsPagado_Enf_menor_Padre(countSQL);
		ilf.setPer_EnfHijoMenor1_padre_Monto_Pagado_Subs(countSQL.getResultadoSum());

		return ilf;
	}//end getSubsIniciados

	public CuadroILF4501VO getTotalILF(CuadroILF4501VO ilf) {
		//fila total ilf

		ilf.setTotal_Lic_autorizadas(this.getTotal_Lic_autorizadas(ilf));
		ilf.setTotal_Subs_Iniciados(this.getTotal_Subs_Iniciados(ilf));
		ilf.setTotal_Dias_subPagados(this.getTotal_Dias_subPagados(ilf));
		ilf.setTotal_Personas_Subsidiadas(this.getTotal_Personas_Subsidiadas(ilf));
		ilf.setTotal_Monto_Pagado_Subs(this.getTotal_Monto_Pagado_Subs(ilf));
		return ilf;
	}//end getSubsIniciados	

	public int getTotal_Lic_autorizadas(CuadroILF4501VO ilf) {
		return ilf.getPer_EnfHijoMenor1_madre_Lic_autorizadas() + ilf.getPer_EnfHijoMenor1_padre_Lic_autorizadas() + ilf.getPer_Parental_padre_Lic_autorizadas()
				+ ilf.getPer_Parental_madre_Lic_autorizadas() + ilf.getRep_Postnatal_padre_Lic_autorizadas() + ilf.getRep_Postnatal_madre_Lic_autorizadas() + ilf.getRep_Prenatal_Lic_autorizadas();
	}

	public int getTotal_Subs_Iniciados(CuadroILF4501VO ilf) {
		return ilf.getPer_EnfHijoMenor1_madre_Subs_Iniciados() + ilf.getPer_EnfHijoMenor1_padre_Subs_Iniciados() + ilf.getPer_Parental_madre_Subs_Iniciados()
				+ ilf.getPer_Parental_padre_Subs_Iniciados() + ilf.getRep_Postnatal_madre_Subs_Iniciados() + ilf.getRep_Postnatal_padre_Subs_Iniciados() + ilf.getRep_Prenatal_Subs_Iniciados();
	}

	public int getTotal_Dias_subPagados(CuadroILF4501VO ilf) {
		return ilf.getPer_EnfHijoMenor1_madre_Dias_subPagados() + ilf.getPer_EnfHijoMenor1_padre_Dias_subPagados() + ilf.getPer_Parental_madre_Dias_subPagados()
				+ ilf.getPer_Parental_padre_Dias_subPagados() + ilf.getRep_Postnatal_madre_Dias_subPagados() + ilf.getRep_Postnatal_padre_Dias_subPagados() + ilf.getRep_Prenatal_Dias_subPagados();
	}

	public int getTotal_Personas_Subsidiadas(CuadroILF4501VO ilf) {
		return ilf.getPer_EnfHijoMenor1_madre_Personas_Subsidiadas() + ilf.getPer_EnfHijoMenor1_padre_Personas_Subsidiadas() + ilf.getPer_Parental_madre_Personas_Subsidiadas()
				+ ilf.getPer_Parental_padre_Personas_Subsidiadas() + ilf.getRep_Postnatal_madre_Personas_Subsidiadas() + ilf.getRep_Postnatal_padre_Personas_Subsidiadas()
				+ ilf.getRep_Prenatal_Personas_Subsidiadas();
	}

	public long getTotal_Monto_Pagado_Subs(CuadroILF4501VO ilf) {
		return ilf.getPer_EnfHijoMenor1_madre_Monto_Pagado_Subs() + ilf.getPer_EnfHijoMenor1_padre_Monto_Pagado_Subs() + ilf.getPer_Parental_madre_Monto_Pagado_Subs()
				+ ilf.getPer_Parental_padre_Monto_Pagado_Subs() + ilf.getRep_Postnatal_madre_Monto_Pagado_Subs() + ilf.getRep_Postnatal_padre_Monto_Pagado_Subs()
				+ ilf.getRep_Prenatal_Monto_Pagado_Subs();
	}
}//end class
