package cl.araucana.adminCpe.presentation.struts.forms.empresa;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/*
 * @(#) DetalleEmpresaActionForm.java 1.5 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author aacuña
 * 
 * @version 1.5
 */
public class DetalleEmpresaActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -7010237720389676562L;

	// Datos empresa
	private String rutEmpresaEd;
	private String razonSocial;
	private String opcActividadEconomica;
	private String opcActividadEconomicaMostrar;
	private String nombreActividadEconomica;

	private String idRepLegal;
	private String nombreCompletoRepLegal;
	private String nombreRepLegal;
	private String apPatRepLegal;
	private String apMatRepLegal;

	private String idAdmin;
	private String nombreCompletoAdmin;
	private String nombreAdmin;
	private String apPatAdmin;
	private String apMatAdmin;

	private String estadoEmpresa;
	private String tipoEmpresa;
	private String fecIni;
	private String diaFecIni;
	private String mesFecIni;
	private String yearFecIni;
	private String vigenciaRepLegal;
	private String diaVigRepLegal;
	private String mesVigRepLegal;
	private String yearVigRepLegal;
	private int opcCalculoMovPersonal;
	private int opcGrupoConvenio;

	// Opciones de procesos
	private int genINPPorSucursal;
	private int genMutualPorSucursal;
	private int genCCAFPorSucursal;
	private int calcularMontoINP;
	private int calcularMontoMutual;
	private int calcularMontoCCAF;
	private int calcularMontoTotalSalud;
	private int calcularMontoTotalPrev;
	private int imprimirPlanillas;

	// Datos casa matriz
	private String codigoCasaMatriz;
	private String nombreCasaMatriz;
	private String direccionCasaMatriz;
	private String noCasaMatriz;
	private String dptoCasaMatriz;
	private String opcRegionCasaMatriz;
	private String opcCiudadCasaMatriz;
	private String opcComunaCasaMatriz;
	private String opcSucursalCasaMatriz;
	private String nombreComunaCasaMatriz;
	private String nombreCiudadCasaMatriz;
	private String nombreRegionCasaMatriz;
	private String emailCasaMatriz;
	private String fonoCasaMatriz;
	private String codigoAreaCasaMatriz;
	private String celCasaMatriz;
	private String faxCasaMatriz;
	private String codigoAreaFaxCasaMatriz;

	// Datos mutual
	private String opcMutual;
	private String opcCalculoIndividual;
	private String numAdherentesMutual;
	private String tasaAdicionalMutual;
	private String nombreMutual;

	private String opcCaja;

	// Combos de edicion
	private List actividadesEconomicas;
	private List regiones;
	private List ciudades;
	private List comunas;
	private List mutuales;
	private List listaSucursales;
	private List cajas;

	/**
	 * opc grupo convenio
	 * 
	 * @return
	 */
	public int getOpcGrupoConvenio()
	{
		return this.opcGrupoConvenio;
	}

	/**
	 * opc grupo convenio
	 * 
	 * @param opcGrupoConvenio
	 */
	public void setOpcGrupoConvenio(int opcGrupoConvenio)
	{
		this.opcGrupoConvenio = opcGrupoConvenio;
	}

	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.regiones = null;
		this.ciudades = null;
		this.comunas = null;
	}

	/**
	 * cel casa matriz
	 * 
	 * @return
	 */
	public String getCelCasaMatriz()
	{
		return this.celCasaMatriz;
	}

	/**
	 * cel casa matriz
	 * 
	 * @param celCasaMatriz
	 */
	public void setCelCasaMatriz(String celCasaMatriz)
	{
		this.celCasaMatriz = celCasaMatriz;
	}

	/**
	 * ciudades
	 * 
	 * @return
	 */
	public List getCiudades()
	{
		return this.ciudades;
	}

	/**
	 * ciudades
	 * 
	 * @param ciudades
	 */
	public void setCiudades(List ciudades)
	{
		this.ciudades = ciudades;
	}

	/**
	 * codigo casa matriz
	 * 
	 * @return
	 */
	public String getCodigoCasaMatriz()
	{
		return this.codigoCasaMatriz;
	}

	/**
	 * codigo casa matriz
	 * 
	 * @param codigoCasaMatriz
	 */
	public void setCodigoCasaMatriz(String codigoCasaMatriz)
	{
		this.codigoCasaMatriz = codigoCasaMatriz;
	}

	/**
	 * comunas
	 * 
	 * @return
	 */
	public List getComunas()
	{
		return this.comunas;
	}

	/**
	 * comunas
	 * 
	 * @param comunas
	 */
	public void setComunas(List comunas)
	{
		this.comunas = comunas;
	}

	/**
	 * dia fecha inicial
	 * 
	 * @return
	 */
	public String getDiaFecIni()
	{
		return this.diaFecIni;
	}

	/**
	 * dia fecha inicial
	 * 
	 * @param diaFecIni
	 */
	public void setDiaFecIni(String diaFecIni)
	{
		this.diaFecIni = diaFecIni;
	}

	/**
	 * direccion casa matriz
	 * 
	 * @return
	 */
	public String getDireccionCasaMatriz()
	{
		return this.direccionCasaMatriz;
	}

	/**
	 * direccion casa matriz
	 * 
	 * @param direccionCasaMatriz
	 */
	public void setDireccionCasaMatriz(String direccionCasaMatriz)
	{
		this.direccionCasaMatriz = direccionCasaMatriz;
	}

	/**
	 * depto casa matriz
	 * 
	 * @return
	 */
	public String getDptoCasaMatriz()
	{
		return this.dptoCasaMatriz;
	}

	/**
	 * depto casa matriz
	 * 
	 * @param dptoCasaMatriz
	 */
	public void setDptoCasaMatriz(String dptoCasaMatriz)
	{
		this.dptoCasaMatriz = dptoCasaMatriz;
	}

	/**
	 * email casa matriz
	 * 
	 * @return
	 */
	public String getEmailCasaMatriz()
	{
		return this.emailCasaMatriz;
	}

	/**
	 * email casa matriz
	 * 
	 * @param emailCasaMatriz
	 */
	public void setEmailCasaMatriz(String emailCasaMatriz)
	{
		this.emailCasaMatriz = emailCasaMatriz;
	}

	/**
	 * estado empresa
	 * 
	 * @return
	 */
	public String getEstadoEmpresa()
	{
		return this.estadoEmpresa;
	}

	/**
	 * estado empresa
	 * 
	 * @param estadoEmpresa
	 */
	public void setEstadoEmpresa(String estadoEmpresa)
	{
		this.estadoEmpresa = estadoEmpresa;
	}

	/**
	 * fax casa matriz
	 * 
	 * @return
	 */
	public String getFaxCasaMatriz()
	{
		return this.faxCasaMatriz;
	}

	/**
	 * fax casa matriz
	 * 
	 * @param faxCasaMatriz
	 */
	public void setFaxCasaMatriz(String faxCasaMatriz)
	{
		this.faxCasaMatriz = faxCasaMatriz;
	}

	/**
	 * fono casa matriz
	 * 
	 * @return
	 */
	public String getFonoCasaMatriz()
	{
		return this.fonoCasaMatriz;
	}

	/**
	 * fono casa matriz
	 * 
	 * @param fonoCasaMatriz
	 */
	public void setFonoCasaMatriz(String fonoCasaMatriz)
	{
		this.fonoCasaMatriz = fonoCasaMatriz;
	}

	/**
	 * mes fecha inicio
	 * 
	 * @return
	 */
	public String getMesFecIni()
	{
		return this.mesFecIni;
	}

	/**
	 * mes fecha inicio
	 * 
	 * @param mesFecIni
	 */
	public void setMesFecIni(String mesFecIni)
	{
		this.mesFecIni = mesFecIni;
	}

	/**
	 * numero casa matriz
	 * 
	 * @return
	 */
	public String getNoCasaMatriz()
	{
		return this.noCasaMatriz;
	}

	/**
	 * numero casa matriz
	 * 
	 * @param noCasaMatriz
	 */
	public void setNoCasaMatriz(String noCasaMatriz)
	{
		this.noCasaMatriz = noCasaMatriz;
	}

	/**
	 * nombre administrador
	 * 
	 * @return
	 */
	public String getNombreAdmin()
	{
		return this.nombreAdmin;
	}

	/**
	 * nombre administrador
	 * 
	 * @param nombreAdmin
	 */
	public void setNombreAdmin(String nombreAdmin)
	{
		this.nombreAdmin = nombreAdmin;
	}

	/**
	 * nombre casa matriz
	 * 
	 * @return
	 */
	public String getNombreCasaMatriz()
	{
		return this.nombreCasaMatriz;
	}

	/**
	 * nombre casa matriz
	 * 
	 * @param nombreCasaMatriz
	 */
	public void setNombreCasaMatriz(String nombreCasaMatriz)
	{
		this.nombreCasaMatriz = nombreCasaMatriz;
	}

	/**
	 * nombre representante legal
	 * 
	 * @return
	 */
	public String getNombreRepLegal()
	{
		return this.nombreRepLegal;
	}

	/**
	 * nombre representante legal
	 * 
	 * @param nombreRepLegal
	 */
	public void setNombreRepLegal(String nombreRepLegal)
	{
		this.nombreRepLegal = nombreRepLegal;
	}

	/**
	 * razon social
	 * 
	 * @return
	 */
	public String getRazonSocial()
	{
		return this.razonSocial;
	}

	/**
	 * razon social
	 * 
	 * @param razonSocial
	 */
	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/*
	 * regiones
	 */
	public List getRegiones()
	{
		return this.regiones;
	}

	/**
	 * regiones
	 * 
	 * @param regiones
	 */
	public void setRegiones(List regiones)
	{
		this.regiones = regiones;
	}

	/**
	 * tipo empresa
	 * 
	 * @return
	 */
	public String getTipoEmpresa()
	{
		return this.tipoEmpresa;
	}

	/**
	 * tipo empresa
	 * 
	 * @param tipoEmpresa
	 */
	public void setTipoEmpresa(String tipoEmpresa)
	{
		this.tipoEmpresa = tipoEmpresa;
	}

	/**
	 * año fecha incio
	 * 
	 * @return
	 */
	public String getYearFecIni()
	{
		return this.yearFecIni;
	}

	/**
	 * año fecha inicio
	 * 
	 * @param yearFecIni
	 */
	public void setYearFecIni(String yearFecIni)
	{
		this.yearFecIni = yearFecIni;
	}

	/**
	 * id administrador
	 * 
	 * @return
	 */
	public String getIdAdmin()
	{
		return this.idAdmin;
	}

	/**
	 * id administrador
	 * 
	 * @param idAdmin
	 */
	public void setIdAdmin(String idAdmin)
	{
		this.idAdmin = idAdmin;
	}

	/**
	 * id representante legal
	 * 
	 * @return
	 */
	public String getIdRepLegal()
	{
		return this.idRepLegal;
	}

	/**
	 * id representante legal
	 * 
	 * @param idRepLegal
	 */
	public void setIdRepLegal(String idRepLegal)
	{
		this.idRepLegal = idRepLegal;
	}

	/**
	 * fecha inicio
	 * 
	 * @return
	 */
	public String getFecIni()
	{
		return this.fecIni;
	}

	/**
	 * fecha inicio
	 * 
	 * @param fecIni
	 */
	public void setFecIni(String fecIni)
	{
		this.fecIni = fecIni;
	}

	/**
	 * vigencia representante legal
	 * 
	 * @return
	 */
	public String getVigenciaRepLegal()
	{
		return this.vigenciaRepLegal;
	}

	/**
	 * vigencia representante legal
	 * 
	 * @param vigenciaRepLegal
	 */
	public void setVigenciaRepLegal(String vigenciaRepLegal)
	{
		this.vigenciaRepLegal = vigenciaRepLegal;
	}

	/**
	 * apellido materno administrador
	 * 
	 * @return
	 */
	public String getApMatAdmin()
	{
		return this.apMatAdmin;
	}

	/**
	 * apellido materno administrador
	 * 
	 * @param apMatAdmin
	 */
	public void setApMatAdmin(String apMatAdmin)
	{
		this.apMatAdmin = apMatAdmin;
	}

	/**
	 * apellido materno representante legal
	 * 
	 * @return
	 */
	public String getApMatRepLegal()
	{
		return this.apMatRepLegal;
	}

	/**
	 * apellido materno representante legal
	 * 
	 * @param apMatRepLegal
	 */
	public void setApMatRepLegal(String apMatRepLegal)
	{
		this.apMatRepLegal = apMatRepLegal;
	}

	/**
	 * apellido paterno administrador
	 * 
	 * @return
	 */
	public String getApPatAdmin()
	{
		return this.apPatAdmin;
	}

	/**
	 * apellido paterno administrador
	 * 
	 * @param apPatAdmin
	 */
	public void setApPatAdmin(String apPatAdmin)
	{
		this.apPatAdmin = apPatAdmin;
	}

	/**
	 * apellido paterno representante legal
	 * 
	 * @return
	 */
	public String getApPatRepLegal()
	{
		return this.apPatRepLegal;
	}

	/**
	 * apellido paterno representante legal
	 * 
	 * @param apPatRepLegal
	 */
	public void setApPatRepLegal(String apPatRepLegal)
	{
		this.apPatRepLegal = apPatRepLegal;
	}

	/**
	 * nombre com0pleto administrador
	 * 
	 * @return
	 */
	public String getNombreCompletoAdmin()
	{
		return this.nombreCompletoAdmin;
	}

	/**
	 * nombre com0pleto administrador
	 * 
	 * @param nombreCompletoAdmin
	 */
	public void setNombreCompletoAdmin(String nombreCompletoAdmin)
	{
		this.nombreCompletoAdmin = nombreCompletoAdmin;
	}

	/**
	 * nombre completo representante legal
	 * 
	 * @return
	 */
	public String getNombreCompletoRepLegal()
	{
		return this.nombreCompletoRepLegal;
	}

	/**
	 * nombre completo representante legal
	 * 
	 * @param nombreCompletoRepLegal
	 */
	public void setNombreCompletoRepLegal(String nombreCompletoRepLegal)
	{
		this.nombreCompletoRepLegal = nombreCompletoRepLegal;
	}

	/**
	 * dia vigincia representante legal
	 * 
	 * @return
	 */
	public String getDiaVigRepLegal()
	{
		return this.diaVigRepLegal;
	}

	/**
	 * dia vigencia representante legal
	 * 
	 * @param diaVigRepLegal
	 */
	public void setDiaVigRepLegal(String diaVigRepLegal)
	{
		this.diaVigRepLegal = diaVigRepLegal;
	}

	/**
	 * mes vigencia representante legal
	 * 
	 * @return
	 */
	public String getMesVigRepLegal()
	{
		return this.mesVigRepLegal;
	}

	/**
	 * mes vigencia representante legal
	 * 
	 * @param mesVigRepLegal
	 */
	public void setMesVigRepLegal(String mesVigRepLegal)
	{
		this.mesVigRepLegal = mesVigRepLegal;
	}

	/**
	 * año vigencia representante legal
	 * 
	 * @return
	 */
	public String getYearVigRepLegal()
	{
		return this.yearVigRepLegal;
	}

	/**
	 * año vigencia representante legal
	 * 
	 * @param yearVigRepLegal
	 */
	public void setYearVigRepLegal(String yearVigRepLegal)
	{
		this.yearVigRepLegal = yearVigRepLegal;
	}

	/**
	 * lista mutuales
	 * 
	 * @return
	 */
	public List getMutuales()
	{
		return this.mutuales;
	}

	/**
	 * mutuales
	 * 
	 * @param mutuales
	 */
	public void setMutuales(List mutuales)
	{
		this.mutuales = mutuales;
	}

	/**
	 * opc calculo individual
	 * 
	 * @return
	 */
	public String getOpcCalculoIndividual()
	{
		return this.opcCalculoIndividual;
	}

	/**
	 * opc calculo individual
	 * 
	 * @param opcCalculoIndividual
	 */
	public void setOpcCalculoIndividual(String opcCalculoIndividual)
	{
		this.opcCalculoIndividual = opcCalculoIndividual;
	}

	/**
	 * opc ciudad casa matriz
	 * 
	 * @return
	 */
	public String getOpcCiudadCasaMatriz()
	{
		return this.opcCiudadCasaMatriz;
	}

	/**
	 * opc ciudad casa matriz
	 * 
	 * @param opcCiudadCasaMatriz
	 */
	public void setOpcCiudadCasaMatriz(String opcCiudadCasaMatriz)
	{
		this.opcCiudadCasaMatriz = opcCiudadCasaMatriz;
	}

	/**
	 * opc comuna casa matriz
	 * 
	 * @return
	 */
	public String getOpcComunaCasaMatriz()
	{
		return this.opcComunaCasaMatriz;
	}

	/**
	 * opc comuna casa matriz
	 * 
	 * @param opcComunaCasaMatriz
	 */
	public void setOpcComunaCasaMatriz(String opcComunaCasaMatriz)
	{
		this.opcComunaCasaMatriz = opcComunaCasaMatriz;
	}

	/**
	 * opc mutual
	 * 
	 * @return
	 */
	public String getOpcMutual()
	{
		return this.opcMutual;
	}

	/**
	 * opc mutual
	 * 
	 * @param opcMutual
	 */
	public void setOpcMutual(String opcMutual)
	{
		this.opcMutual = opcMutual;
	}

	/**
	 * opc region casa matriz
	 * 
	 * @return
	 */
	public String getOpcRegionCasaMatriz()
	{
		return this.opcRegionCasaMatriz;
	}

	/**
	 * opc region casa matriz
	 * 
	 * @param opcRegionCasaMatriz
	 */
	public void setOpcRegionCasaMatriz(String opcRegionCasaMatriz)
	{
		this.opcRegionCasaMatriz = opcRegionCasaMatriz;
	}

	/**
	 * numero adherentes mutual
	 * 
	 * @return
	 */
	public String getNumAdherentesMutual()
	{
		return this.numAdherentesMutual;
	}

	/**
	 * numero adherentes mutual
	 * 
	 * @param numAdherentesMutual
	 */
	public void setNumAdherentesMutual(String numAdherentesMutual)
	{
		this.numAdherentesMutual = numAdherentesMutual;
	}

	/**
	 * tasa adicional mutual
	 * 
	 * @return
	 */
	public String getTasaAdicionalMutual()
	{
		return this.tasaAdicionalMutual;
	}

	/**
	 * tasa adicional mutual
	 * 
	 * @param tasaAdicionalMutual
	 */
	public void setTasaAdicionalMutual(String tasaAdicionalMutual)
	{
		this.tasaAdicionalMutual = tasaAdicionalMutual;
	}

	/**
	 * rut empresa ed
	 * 
	 * @return
	 */
	public String getRutEmpresaEd()
	{
		return this.rutEmpresaEd;
	}

	/**
	 * rut empresa ed
	 * 
	 * @param rutEmpresaEd
	 */
	public void setRutEmpresaEd(String rutEmpresaEd)
	{
		this.rutEmpresaEd = rutEmpresaEd;
	}

	/**
	 * nombre mutual
	 * 
	 * @return
	 */
	public String getNombreMutual()
	{
		return this.nombreMutual;
	}

	/**
	 * nombre mutual
	 * 
	 * @param nombreMutual
	 */
	public void setNombreMutual(String nombreMutual)
	{
		this.nombreMutual = nombreMutual;
	}

	/**
	 * nombre ciudad casa matriz
	 * 
	 * @return
	 */
	public String getNombreCiudadCasaMatriz()
	{
		return this.nombreCiudadCasaMatriz;
	}

	/**
	 * nombre ciudad casa matriz
	 * 
	 * @param nombreCiudadCasaMatriz
	 */
	public void setNombreCiudadCasaMatriz(String nombreCiudadCasaMatriz)
	{
		this.nombreCiudadCasaMatriz = nombreCiudadCasaMatriz;
	}

	/**
	 * nombre comuna casa matriz
	 * 
	 * @return
	 */
	public String getNombreComunaCasaMatriz()
	{
		return this.nombreComunaCasaMatriz;
	}

	/**
	 * nombre comuna casa matriz
	 * 
	 * @param nombreComunaCasaMatriz
	 */
	public void setNombreComunaCasaMatriz(String nombreComunaCasaMatriz)
	{
		this.nombreComunaCasaMatriz = nombreComunaCasaMatriz;
	}

	/**
	 * nombre region casa matriz
	 * 
	 * @return
	 */
	public String getNombreRegionCasaMatriz()
	{
		return this.nombreRegionCasaMatriz;
	}

	/**
	 * nombre region casa matriz
	 * 
	 * @param nombreRegionCasaMatriz
	 */
	public void setNombreRegionCasaMatriz(String nombreRegionCasaMatriz)
	{
		this.nombreRegionCasaMatriz = nombreRegionCasaMatriz;
	}

	/**
	 * lista sucursales
	 * 
	 * @return
	 */
	public List getListaSucursales()
	{
		return this.listaSucursales;
	}

	/**
	 * lista sucursales
	 * 
	 * @param listaSucursales
	 */
	public void setListaSucursales(List listaSucursales)
	{
		this.listaSucursales = listaSucursales;
	}

	/**
	 * opc sucursal casa matriz
	 * 
	 * @return
	 */
	public String getOpcSucursalCasaMatriz()
	{
		return this.opcSucursalCasaMatriz;
	}

	/**
	 * opc sucursal casa matriz
	 * 
	 * @param opcSucursalCasaMatriz
	 */
	public void setOpcSucursalCasaMatriz(String opcSucursalCasaMatriz)
	{
		this.opcSucursalCasaMatriz = opcSucursalCasaMatriz;
	}

	/**
	 * calcular monto ccaf
	 * 
	 * @return
	 */
	public int getCalcularMontoCCAF()
	{
		return this.calcularMontoCCAF;
	}

	/**
	 * calcular monto ccaf
	 * 
	 * @param calcularMontoCCAF
	 */
	public void setCalcularMontoCCAF(int calcularMontoCCAF)
	{
		this.calcularMontoCCAF = calcularMontoCCAF;
	}

	/**
	 * calcular monto inp
	 * 
	 * @return
	 */
	public int getCalcularMontoINP()
	{
		return this.calcularMontoINP;
	}

	/**
	 * calcular monto inp
	 * 
	 * @param calcularMontoINP
	 */
	public void setCalcularMontoINP(int calcularMontoINP)
	{
		this.calcularMontoINP = calcularMontoINP;
	}

	/**
	 * calcular monto mutual
	 * 
	 * @return
	 */
	public int getCalcularMontoMutual()
	{
		return this.calcularMontoMutual;
	}

	/**
	 * calcular minto mutual
	 * 
	 * @param calcularMontoMutual
	 */
	public void setCalcularMontoMutual(int calcularMontoMutual)
	{
		this.calcularMontoMutual = calcularMontoMutual;
	}

	/**
	 * calcular monto toal prevision
	 * 
	 * @return
	 */
	public int getCalcularMontoTotalPrev()
	{
		return this.calcularMontoTotalPrev;
	}

	/**
	 * calcular monto toal prevision
	 * 
	 * @param calcularMontoTotalPrev
	 */
	public void setCalcularMontoTotalPrev(int calcularMontoTotalPrev)
	{
		this.calcularMontoTotalPrev = calcularMontoTotalPrev;
	}

	/**
	 * calcular monto total salud
	 * 
	 * @return
	 */
	public int getCalcularMontoTotalSalud()
	{
		return this.calcularMontoTotalSalud;
	}

	/**
	 * calcular monto toal salud
	 * 
	 * @param calcularMontoTotalSalud
	 */
	public void setCalcularMontoTotalSalud(int calcularMontoTotalSalud)
	{
		this.calcularMontoTotalSalud = calcularMontoTotalSalud;
	}

	/**
	 * gen ccaf por sucursal
	 * 
	 * @return
	 */
	public int getGenCCAFPorSucursal()
	{
		return this.genCCAFPorSucursal;
	}

	/**
	 * gen ccaf por sucursal
	 * 
	 * @param genCCAFPorSucursal
	 */
	public void setGenCCAFPorSucursal(int genCCAFPorSucursal)
	{
		this.genCCAFPorSucursal = genCCAFPorSucursal;
	}

	/**
	 * gen inp por sucursal
	 * 
	 * @return
	 */
	public int getGenINPPorSucursal()
	{
		return this.genINPPorSucursal;
	}

	/**
	 * gen inp por sucursal
	 * 
	 * @param genINPPorSucursal
	 */
	public void setGenINPPorSucursal(int genINPPorSucursal)
	{
		this.genINPPorSucursal = genINPPorSucursal;
	}

	/**
	 * gen mutual por sucursal
	 * 
	 * @return
	 */
	public int getGenMutualPorSucursal()
	{
		return this.genMutualPorSucursal;
	}

	/**
	 * gen mutual por sucursal
	 * 
	 * @param genMutualPorSucursal
	 */
	public void setGenMutualPorSucursal(int genMutualPorSucursal)
	{
		this.genMutualPorSucursal = genMutualPorSucursal;
	}

	/**
	 * imprimir planillas
	 * 
	 * @return
	 */
	public int getImprimirPlanillas()
	{
		return this.imprimirPlanillas;
	}

	/**
	 * imprimir planillas
	 * 
	 * @param imprimirPlanillas
	 */
	public void setImprimirPlanillas(int imprimirPlanillas)
	{
		this.imprimirPlanillas = imprimirPlanillas;
	}

	/**
	 * cajas
	 * 
	 * @return
	 */
	public List getCajas()
	{
		return this.cajas;
	}

	/**
	 * cajas
	 * 
	 * @param cajas
	 */
	public void setCajas(List cajas)
	{
		this.cajas = cajas;
	}

	/**
	 * opc cajas
	 * 
	 * @return
	 */
	public String getOpcCaja()
	{
		return this.opcCaja;
	}

	/**
	 * opc cajas
	 * 
	 * @param opcCaja
	 */
	public void setOpcCaja(String opcCaja)
	{
		this.opcCaja = opcCaja;
	}

	/**
	 * actividades economicas
	 * 
	 * @return
	 */
	public List getActividadesEconomicas()
	{
		return this.actividadesEconomicas;
	}

	/**
	 * actividades economicas
	 * 
	 * @param actividadesEconomicas
	 */
	public void setActividadesEconomicas(List actividadesEconomicas)
	{
		this.actividadesEconomicas = actividadesEconomicas;
	}

	/**
	 * nombre actividades economicas
	 * 
	 * @return
	 */
	public String getNombreActividadEconomica()
	{
		return this.nombreActividadEconomica;
	}

	/**
	 * nombre actividades economicas
	 * 
	 * @param nombreActividadEconomica
	 */
	public void setNombreActividadEconomica(String nombreActividadEconomica)
	{
		this.nombreActividadEconomica = nombreActividadEconomica;
	}

	/**
	 * opc acitvidad economica
	 * 
	 * @return
	 */
	public String getOpcActividadEconomica()
	{
		return this.opcActividadEconomica;
	}

	/**
	 * opc actividad economica
	 * 
	 * @param opcActividadEconomica
	 */
	public void setOpcActividadEconomica(String opcActividadEconomica)
	{
		this.opcActividadEconomica = opcActividadEconomica;
	}

	/**
	 * opc actividad economica mostrar
	 * 
	 * @return
	 */
	public String getOpcActividadEconomicaMostrar()
	{
		return this.opcActividadEconomicaMostrar;
	}

	/**
	 * opc actividad economica mostrar
	 * 
	 * @param opcActividadEconomicaMostrar
	 */
	public void setOpcActividadEconomicaMostrar(String opcActividadEconomicaMostrar)
	{
		this.opcActividadEconomicaMostrar = opcActividadEconomicaMostrar;
	}

	/**
	 * opc calculo mov personal
	 * 
	 * @return
	 */
	public int getOpcCalculoMovPersonal()
	{
		return this.opcCalculoMovPersonal;
	}

	/**
	 * opc calculo mov personal
	 * 
	 * @param opcCalculoMovPersonal
	 */
	public void setOpcCalculoMovPersonal(int opcCalculoMovPersonal)
	{
		this.opcCalculoMovPersonal = opcCalculoMovPersonal;
	}

	/**
	 * codigo area casa matriz
	 * 
	 * @return
	 */
	public String getCodigoAreaCasaMatriz()
	{
		return this.codigoAreaCasaMatriz;
	}

	/**
	 * codigo area casa matriz
	 * 
	 * @param codigoAreaCasaMatriz
	 */
	public void setCodigoAreaCasaMatriz(String codigoAreaCasaMatriz)
	{
		this.codigoAreaCasaMatriz = codigoAreaCasaMatriz;
	}

	/**
	 * codigo area faz casa matriz
	 * 
	 * @return
	 */
	public String getCodigoAreaFaxCasaMatriz()
	{
		return this.codigoAreaFaxCasaMatriz;
	}

	/**
	 * codigo area fax casa matriz
	 * 
	 * @param codigoAreaFaxCasaMatriz
	 */
	public void setCodigoAreaFaxCasaMatriz(String codigoAreaFaxCasaMatriz)
	{
		this.codigoAreaFaxCasaMatriz = codigoAreaFaxCasaMatriz;
	}
}