package cl.araucana.adminCpe.presentation.struts.forms.grupoConvenio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
/*
* @(#) GruposConvenioActionForm.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author aacuña
 * 
 * @version 1.4
 */
public class GruposConvenioActionForm extends ActionForm
{
	private static final long serialVersionUID = -3205937895029345081L;
	
	private List consulta;
	
	private String idGrupoConvenio;
	private String nombreGrupoConvenio;
	 
	private String nombreHidden;
	private String idHidden;
	
	private int estadoGrupoConvenio;
	private int puedeDeshabilitar;
	
	//Opciones de procesos
	private int genINPPorSucursal;
	private int genMutualPorSucursal;
	private int genCCAFPorSucursal;
	private int calcularMontoINP;
	private int calcularMontoMutual;
	private int calcularMontoCCAF;
	private int calcularMontoTotalSalud;
	private int calcularMontoTotalPrev;
	private int imprimirPlanillas;
	
	//Para mostrar en la ficha
	private Date fechaCreacion;
	
	private int idMapaNomRem;
	private int idMapaNomGra;
	private int idMapaNomRel;
	private int idMapaNomDep;
	private int idMapaCod;
	
	private String descripcionR;
	private String descripcionG;
	private String descripcionA;
	private String descripcionD;
	private String nombreMapeoCodigo;
	
	private List encargadosAccGrupos;
	
	
	private Collection numeroFilas;

	//csanchez
	private int homologacionPrevired;
	private int calcularFonasa;
	private List configuracionesBase;
	private int idGrupoConvenioBase;
	private String listaGrupoConvenio;
	private String listaTipoSeparador;
	private Character caracterSeparador;

	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		
		this.estadoGrupoConvenio = 1;
		
		this.consulta = ListUtils.lazyList(new ArrayList(),
				new Factory() {
					public Object create() {
						return new GrupoConvenioVO();
					}
				});
	}

	/**
	 * consulta
	 * @return
	 */
	public List getConsulta()
	{
		return this.consulta;
	}

	/**
	 * consulta
	 * @param consulta
	 */
	public void setConsulta(List consulta)
	{
		this.consulta = consulta;
	}

	/**
	 * calcular Monto ccaf
	 * @return
	 */
	public int getCalcularMontoCCAF()
	{
		return this.calcularMontoCCAF;
	}

	/**
	 * calcular monto ccaf
	 * @param calcularMontoCCAF
	 */
	public void setCalcularMontoCCAF(int calcularMontoCCAF)
	{
		this.calcularMontoCCAF = calcularMontoCCAF;
	}

	/**
	 * calcular monto inp
	 * @return
	 */
	public int getCalcularMontoINP()
	{
		return this.calcularMontoINP;
	}

	/**
	 * calcular monto inp
	 * @param calcularMontoINP
	 */
	public void setCalcularMontoINP(int calcularMontoINP)
	{
		this.calcularMontoINP = calcularMontoINP;
	}

	/**
	 * calcular monto mutual
	 * @return
	 */
	public int getCalcularMontoMutual()
	{
		return this.calcularMontoMutual;
	}

	/**
	 * calcular monto mutual
	 * @param calcularMontoMutual
	 */
	public void setCalcularMontoMutual(int calcularMontoMutual)
	{
		this.calcularMontoMutual = calcularMontoMutual;
	}

	/**
	 * calcular monto total prevision
	 * @return
	 */
	public int getCalcularMontoTotalPrev()
	{
		return this.calcularMontoTotalPrev;
	}

	/**
	 * calcular monto total prevision
	 * @param calcularMontoTotalPrev
	 */
	public void setCalcularMontoTotalPrev(int calcularMontoTotalPrev)
	{
		this.calcularMontoTotalPrev = calcularMontoTotalPrev;
	}

	/**
	 * calcular monto total salud
	 * @return
	 */
	public int getCalcularMontoTotalSalud()
	{
		return this.calcularMontoTotalSalud;
	}

	/**
	 * calcular monto total salud
	 * @param calcularMontoTotalSalud
	 */
	public void setCalcularMontoTotalSalud(int calcularMontoTotalSalud)
	{
		this.calcularMontoTotalSalud = calcularMontoTotalSalud;
	}
	/**
	 * estado grupo convenio
	 * @return
	 */
	public int getEstadoGrupoConvenio()
	{
		return this.estadoGrupoConvenio;
	}

	/**
	 * estado grupo convenio
	 * @param estadoGrupoConvenio
	 */
	public void setEstadoGrupoConvenio(int estadoGrupoConvenio)
	{
		this.estadoGrupoConvenio = estadoGrupoConvenio;
	}

	/**
	 * gen ccaf por sucursal
	 * @return
	 */
	public int getGenCCAFPorSucursal()
	{
		return this.genCCAFPorSucursal;
	}

	/**
	 * gen ccaf por sucursal
	 * @param genCCAFPorSucursal
	 */
	public void setGenCCAFPorSucursal(int genCCAFPorSucursal)
	{
		this.genCCAFPorSucursal = genCCAFPorSucursal;
	}

	/**
	 * gen inp por sucursal
	 * @return
	 */
	public int getGenINPPorSucursal()
	{
		return this.genINPPorSucursal;
	}

	/**
	 * gen inp por sucursal
	 * @param genINPPorSucursal
	 */
	public void setGenINPPorSucursal(int genINPPorSucursal)
	{
		this.genINPPorSucursal = genINPPorSucursal;
	}

	/**
	 * gen mutual por sucursal
	 * @return
	 */
	public int getGenMutualPorSucursal()
	{
		return this.genMutualPorSucursal;
	}

	/**
	 * gen mutual por sucursal
	 * @param genMutualPorSucursal
	 */
	public void setGenMutualPorSucursal(int genMutualPorSucursal)
	{
		this.genMutualPorSucursal = genMutualPorSucursal;
	}

	/**
	 * id grupo convenio
	 * @return
	 */
	public String getIdGrupoConvenio()
	{
		return this.idGrupoConvenio;
	}

	/**
	 * id grupo convenio
	 * @param idGrupoConvenio
	 */
	public void setIdGrupoConvenio(String idGrupoConvenio)
	{
		this.idGrupoConvenio = idGrupoConvenio;
	}

	/**
	 * imprime planillas
	 * @return
	 */
	public int getImprimirPlanillas()
	{
		return this.imprimirPlanillas;
	}

	/**
	 * imprime planillas
	 * @param imprimirPlanillas
	 */
	public void setImprimirPlanillas(int imprimirPlanillas)
	{
		this.imprimirPlanillas = imprimirPlanillas;
	}

	/**
	 * nombre grupo convenio
	 * @return
	 */
	public String getNombreGrupoConvenio()
	{
		return this.nombreGrupoConvenio;
	}

	/**
	 * nombre grupo convenio
	 * @param nombreGrupoConvenio
	 */
	public void setNombreGrupoConvenio(String nombreGrupoConvenio)
	{
		this.nombreGrupoConvenio = nombreGrupoConvenio;
	}

	/**
	 * fecha cracion
	 * @return
	 */
	public Date getFechaCreacion()
	{
		return this.fechaCreacion;
	}

	/**
	 * fecha creacion
	 * @param fechaCreacion
	 */
	public void setFechaCreacion(Date fechaCreacion)
	{
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * id mapa codigo
	 * @return
	 */
	public int getIdMapaCod()
	{
		return this.idMapaCod;
	}

	/**
	 * id mapa codigo
	 * @param idMapaCod
	 */
	public void setIdMapaCod(int idMapaCod)
	{
		this.idMapaCod = idMapaCod;
	}

	/**
	 * id mapa nombre dep
	 * @return
	 */
	public int getIdMapaNomDep()
	{
		return this.idMapaNomDep;
	}

	/**
	 * id mapa nombre dep
	 * @param idMapaNomDep
	 */
	public void setIdMapaNomDep(int idMapaNomDep)
	{
		this.idMapaNomDep = idMapaNomDep;
	}

	/**
	 * mapa nombre gra
	 * @return
	 */
	public int getIdMapaNomGra()
	{
		return this.idMapaNomGra;
	}

	/**
	 * id mapa nombre gra
	 * @param idMapaNomGra
	 */
	public void setIdMapaNomGra(int idMapaNomGra)
	{
		this.idMapaNomGra = idMapaNomGra;
	}

	/**
	 * id mapa nombre rel
	 * @return
	 */
	public int getIdMapaNomRel()
	{
		return this.idMapaNomRel;
	}

	/**
	 * id mapa nombre rel
	 * @param idMapaNomRel
	 */
	public void setIdMapaNomRel(int idMapaNomRel)
	{
		this.idMapaNomRel = idMapaNomRel;
	}

	/**
	 * id mapa nombre rem
	 * @return
	 */
	public int getIdMapaNomRem()
	{
		return this.idMapaNomRem;
	}

	/**
	 * id mapa nombre rem
	 * @param idMapaNomRem
	 */
	public void setIdMapaNomRem(int idMapaNomRem)
	{
		this.idMapaNomRem = idMapaNomRem;
	}

	/**
	 * descripcionA
	 * @return
	 */
	public String getDescripcionA()
	{
		return this.descripcionA;
	}

	/**
	 * descripcionA
	 * @param descripcionA
	 */
	public void setDescripcionA(String descripcionA)
	{
		this.descripcionA = descripcionA;
	}

	/**
	 * descripcionD
	 * @return
	 */
	public String getDescripcionD()
	{
		return this.descripcionD;
	}

	/**
	 * descripcionD
	 * @param descripcionD
	 */
	public void setDescripcionD(String descripcionD)
	{
		this.descripcionD = descripcionD;
	}

	/**
	 * descripcionG
	 * @return
	 */
	public String getDescripcionG()
	{
		return this.descripcionG;
	}

	/**
	 * descripcionG
	 * @param descripcionG
	 */
	public void setDescripcionG(String descripcionG)
	{
		this.descripcionG = descripcionG;
	}

	/**
	 * descripcionR
	 * @return
	 */
	public String getDescripcionR()
	{
		return this.descripcionR;
	}

	/**
	 * descripcionR
	 * @param descripcionR
	 */
	public void setDescripcionR(String descripcionR)
	{
		this.descripcionR = descripcionR;
	}

	/**
	 * encargados acc grupos
	 * @return
	 */
	public List getEncargadosAccGrupos()
	{
		return this.encargadosAccGrupos;
	}

	/**
	 * encargados acc grupo
	 * @param encargadosAccGrupos
	 */
	public void setEncargadosAccGrupos(List encargadosAccGrupos)
	{
		this.encargadosAccGrupos = encargadosAccGrupos;
	}

	/**
	 * nombre mapeo codigo
	 * @return
	 */
	public String getNombreMapeoCodigo()
	{
		return this.nombreMapeoCodigo;
	}

	/**
	 * nombre mapeo codigo
	 * @param nombreMapeoCodigo
	 */
	public void setNombreMapeoCodigo(String nombreMapeoCodigo)
	{
		this.nombreMapeoCodigo = nombreMapeoCodigo;
	}

	/**
	 * numero filas
	 * @return
	 */
	public Collection getNumeroFilas() {
		return this.numeroFilas;
	}

	/**
	 * numero filas
	 * @param numeroFilas
	 */
	public void setNumeroFilas(Collection numeroFilas) {
		this.numeroFilas = numeroFilas;
	}

	/**
	 * id hidden
	 * @return
	 */
	public String getIdHidden() {
		return this.idHidden;
	}

	/**
	 * id hidden
	 * @param idHidden
	 */
	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
	}

	/**
	 * nombre hidden
	 * @return
	 */
	public String getNombreHidden() {
		return this.nombreHidden;
	}

	/**
	 * nombre hidden
	 * @param nombreHidden
	 */
	public void setNombreHidden(String nombreHidden) {
		this.nombreHidden = nombreHidden;
	}

	/**
	 * puede deshabilitar
	 * @return
	 */
	 public int getPuedeDeshabilitar() {
		return this.puedeDeshabilitar;
	}

	 /**
	  * puede deshabilitar
	  * @param puedeDeshabilitar
	  */
	public void setPuedeDeshabilitar(int puedeDeshabilitar) {
		this.puedeDeshabilitar = puedeDeshabilitar;
	}

	public int getHomologacionPrevired()
	{
		return this.homologacionPrevired;
	}

	public void setHomologacionPrevired(int homologacionPrevired)
	{
		this.homologacionPrevired = homologacionPrevired;
	}

	public int getCalcularFonasa()
	{
		return this.calcularFonasa;
	}

	public void setCalcularFonasa(int calcularFonasa)
	{
		this.calcularFonasa = calcularFonasa;
	}

	public int getIdGrupoConvenioBase()
	{
		return this.idGrupoConvenioBase;
	}

	public void setIdGrupoConvenioBase(int idGrupoConvenioBase)
	{
		this.idGrupoConvenioBase = idGrupoConvenioBase;
	}

	public List getConfiguracionesBase()
	{
		return this.configuracionesBase;
	}

	public void setConfiguracionesBase(List configuracionesBase)
	{
		this.configuracionesBase = configuracionesBase;
	}

	public String getListaGrupoConvenio()
	{
		return this.listaGrupoConvenio;
	}

	public void setListaGrupoConvenio(String listaGrupoConvenio)
	{
		this.listaGrupoConvenio = listaGrupoConvenio;
	}

	public String getListaTipoSeparador()
	{
		return this.listaTipoSeparador;
	}

	public void setListaTipoSeparador(String listaTipoSeparador)
	{
		this.listaTipoSeparador = listaTipoSeparador;
	}

	public Character getCaracterSeparador()
	{
		return this.caracterSeparador;
	}

	public void setCaracterSeparador(Character caracterSeparador)
	{
		this.caracterSeparador = caracterSeparador;
	}

}
