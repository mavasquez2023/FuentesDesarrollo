package cl.araucana.adminCpe.presentation.struts.actions.entidad;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Session;
import cl.araucana.adminCpe.presentation.mgr.BancoMgr;
import cl.araucana.adminCpe.presentation.mgr.EntidadesMgr;
import cl.araucana.adminCpe.presentation.mgr.FoliacionMgr;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaEntidadFicha;
import cl.araucana.cp.distribuidor.hibernate.beans.CtaCteBancoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPagadoraVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
 * @(#) EdicionEntidadesSaludAction.java 1.21 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.21
 */
public class AdminEntidades
{
	private BancoMgr bancoMgr;
	private FoliacionMgr foliacionMgr;
	private EntidadesMgr entidadesMgr;

	public AdminEntidades(Session session)
	{
		this.bancoMgr = new BancoMgr(session);
		this.foliacionMgr = new FoliacionMgr(session);
		this.entidadesMgr = new EntidadesMgr(session);
	}

	/**
	 * lista entidades
	 * 
	 * @return
	 */
	protected List getEntidades()
	{
		List listaEntidades = new ArrayList();
		EntidadVO entidades = new EntidadVO();
		entidades.setId(1);
		entidades.setNombre("SALUD");
		listaEntidades.add(entidades);
		entidades = new EntidadVO();
		entidades.setId(2);
		entidades.setNombre("SIL");
		listaEntidades.add(entidades);
		entidades = new EntidadVO();
		entidades.setId(3);
		entidades.setNombre("AFP");
		listaEntidades.add(entidades);
		entidades = new EntidadVO();
		entidades.setId(4);
		entidades.setNombre("APV");
		listaEntidades.add(entidades);
		entidades = new EntidadVO();
		entidades.setId(5);
		entidades.setNombre("CCAF");
		listaEntidades.add(entidades);
		entidades = new EntidadVO();
		entidades.setId(6);
		entidades.setNombre("AFC");
		listaEntidades.add(entidades);
		entidades = new EntidadVO();
		entidades.setId(7);
		entidades.setNombre("MUTUAL");
		listaEntidades.add(entidades);
		return listaEntidades;
	}

	/**
	 * actualiza la cuenta corriente del banco
	 * 
	 * @return
	 * @throws DaoException
	 */
	protected EntidadPagadoraVO regularizaBanco(EntidadPagadoraVO entidadPagadoraVO) throws DaoException
	{
		if (entidadPagadoraVO != null && entidadPagadoraVO.getIdCtaCte() != null && !entidadPagadoraVO.getIdCtaCte().trim().equals(""))
		{
			if (!this.bancoMgr.existeCuenta(new CtaCteBancoVO(entidadPagadoraVO.getIdCtoBanco(), entidadPagadoraVO.getIdCtaCte().trim())))
			{
				if (entidadPagadoraVO.getIdCtoBanco() == 0)
					entidadPagadoraVO.setIdCtaCte("0");
				else
				{
					CtaCteBancoVO cta = new CtaCteBancoVO(entidadPagadoraVO.getIdCtoBanco(), entidadPagadoraVO.getIdCtaCte().trim());
					this.bancoMgr.saveCuenta(cta);
				}
			}

			//Para evitar restricción FK_R157_2 al crear "Datos Cuenta Corriente para Transferencia"
			if (entidadPagadoraVO.getIdCtaCteSpl() != null && !entidadPagadoraVO.getIdCtaCteSpl().trim().equals("") ) {
				if (!this.bancoMgr.existeCuenta(new CtaCteBancoVO(entidadPagadoraVO.getIdBancoSpl(), entidadPagadoraVO.getIdCtaCteSpl().trim()))) {
					if (entidadPagadoraVO.getIdBancoSpl() == 0) {
						entidadPagadoraVO.setIdCtaCteSpl("0");
					} else {
						CtaCteBancoVO ctaSpl = new CtaCteBancoVO(entidadPagadoraVO.getIdBancoSpl(), entidadPagadoraVO.getIdCtaCteSpl().trim());
						this.bancoMgr.saveCuenta(ctaSpl);
					}
				}
			}
			
		}
		return entidadPagadoraVO;
	}

	/**
	 * actualiza folios
	 * 
	 * @return
	 * @throws DaoException
	 */
	protected List actualizaFolios(LineaEntidadFicha _folio, int entidadPagadora, String lista, boolean guardaFolio, String folioBorrar)
	{
		List folios = new ArrayList();
		try
		{
			String listaFolios = "";
			if (folioBorrar == null || folioBorrar.equals(""))
				folioBorrar = "-1";
			if (lista != null && lista.length() > 5)
			{
				listaFolios = String.valueOf(lista).substring(0, lista.length() - 1);
				int contador = 0;
				StringTokenizer listaDatos = new StringTokenizer(listaFolios, "*");
				while (listaDatos.hasMoreTokens())
				{
					StringTokenizer datos = new StringTokenizer(listaDatos.nextToken(), "/");
					LineaEntidadFicha lEntidad;
					while (datos.hasMoreTokens())
					{
						lEntidad = new LineaEntidadFicha();
						lEntidad.setIdFoliacion(Integer.parseInt(datos.nextToken()));
						if (lEntidad.getIdFoliacion() == -1)
							lEntidad.setIdFoliacion(0);
						lEntidad.setIdEntPagadora(Integer.parseInt(datos.nextToken()));
						lEntidad.setIdEntPagadora(entidadPagadora);
						lEntidad.setFoliosEnUso(Integer.parseInt(datos.nextToken()));
						lEntidad.setFolioInicial(Integer.parseInt(datos.nextToken()));
						lEntidad.setFolioFinal(Integer.parseInt(datos.nextToken()));
						lEntidad.setFolioActual(Integer.parseInt(datos.nextToken()));
						if (contador != Integer.parseInt(folioBorrar))
							folios.add(lEntidad);
					}
					contador++;
				}
				if (guardaFolio)
				{
					_folio.setIdFoliacion(0);
					_folio.setIdEntPagadora(entidadPagadora);
					folios.add(_folio);
					if (this.foliacionMgr.agregaFolios(folios) == null)
						return null;
				}
			} else if (guardaFolio)
			{
				_folio.setIdFoliacion(0);
				_folio.setIdEntPagadora(entidadPagadora);
				folios.add(_folio);
				if (this.foliacionMgr.agregaFolios(folios) == null)
					return null;
			}
			return folios;
		} catch (Exception ex)
		{
			return null;
		}
	}

	public int updateEntidadPagadora(boolean force, EntidadPagadoraVO entidadPagadoraVO)
	{
		return this.entidadesMgr.updateEntidadPagadora(force, entidadPagadoraVO);
	}

	public int updateEntidadPagadora(EntidadPagadoraVO entidadPagadoraVO)
	{
		return this.entidadesMgr.updateEntidadPagadora(true, entidadPagadoraVO);
	}

	public int updateEntidad(Class tipo, int idCodAntiguo, EntidadVO entidad)
	{
		return this.entidadesMgr.updateEntidad(true, tipo, idCodAntiguo, entidad);
	}

	public int updateEntidad(boolean flag, Class tipo, int idCodAntiguo, EntidadVO entidad)
	{
		return this.entidadesMgr.updateEntidad(flag, tipo, idCodAntiguo, entidad);
	}

	public int delEntidad(Class tipo, EntidadVO entidad)
	{
		return this.entidadesMgr.delEntidad(tipo, entidad);
	}

	public int delEntidadPagadora(EntidadPagadoraVO entidadPagadoraVO)
	{
		return this.entidadesMgr.delEntidadPagadora(entidadPagadoraVO);
	}

	public boolean saveEntidadPagadora(EntidadPagadoraVO entidadPagadoraVO, boolean flag)
	{
		return this.entidadesMgr.saveEntidadPagadora(entidadPagadoraVO, flag);
	}

	public boolean saveEntidadPagadora(EntidadPagadoraVO entidadPagadoraVO)
	{
		return this.saveEntidadPagadora(entidadPagadoraVO, true);
	}

	public boolean saveEntidad(Class tipo, EntidadVO entidad)
	{
		return this.entidadesMgr.saveEntidad(tipo, entidad);
	}

	public String nuevaListaFolios(List lista)
	{
		String retorno = "";
		if (lista != null && lista.size() > 0)
		{
			for (int a = 0; a < lista.size(); a++)
			{
				LineaEntidadFicha lEntidad = (LineaEntidadFicha) lista.get(a);
				retorno += lEntidad.getIdFoliacion() + "/" + lEntidad.getIdEntPagadora() + "/" + lEntidad.getFoliosEnUso() + "/" + lEntidad.getFolioInicial() + "/" + lEntidad.getFolioFinal() + "/"
						+ lEntidad.getFolioActual() + "*";
			}
		}
		return retorno;
	}
}
