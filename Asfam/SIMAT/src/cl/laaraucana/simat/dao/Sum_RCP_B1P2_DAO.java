package cl.laaraucana.simat.dao;

import cl.laaraucana.simat.entidades.SumVO;

public interface Sum_RCP_B1P2_DAO {

	public SumVO getSumB1P2_CCAF_prenatal(SumVO sumSQL) throws Exception;

	public SumVO getSumB1P2_ISP_prenatal(SumVO sumSQL) throws Exception;

	public SumVO getSumB1P2_EntidadEmpleadora_prenatal(SumVO sumSQL) throws Exception;

	public SumVO getSumB1P2_CCAF_postNatal(SumVO sumSQL) throws Exception;

	public SumVO getSumB1P2_ISP_postNatal(SumVO sumSQL) throws Exception;

	public SumVO getSumB1P2_EntidadEmpleadora_postNatal(SumVO sumSQL) throws Exception;

	public SumVO getSumB1P2_CCAF_Parental(SumVO sumSQL) throws Exception;

	public SumVO getSumB1P2_ISP_Parental(SumVO sumSQL) throws Exception;

	public SumVO getSumB1P2_EntidadEmpleadora_Parental(SumVO sumSQL) throws Exception;

	public SumVO getSumB1P2_CCAF_EnfGraveNM(SumVO sumSQL) throws Exception;

	public SumVO getSumB1P2_ISP_EnfGraveNM(SumVO sumSQL) throws Exception;

	public SumVO getSumB1P2_EntidadEmpleadora_EnfGraveNM(SumVO sumSQL) throws Exception;

}
