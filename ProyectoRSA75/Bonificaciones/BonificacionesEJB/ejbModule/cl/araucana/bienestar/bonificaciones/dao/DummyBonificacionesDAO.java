package cl.araucana.bienestar.bonificaciones.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.bienestar.bonificaciones.model.AporteCobertura;
import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.model.CoberturaAdicional;
import cl.araucana.bienestar.bonificaciones.model.Concepto;
import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Cuota;
import cl.araucana.bienestar.bonificaciones.model.DetalleCaso;
import cl.araucana.bienestar.bonificaciones.model.Evento;
import cl.araucana.bienestar.bonificaciones.model.InformacionAsiento;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.model.Profesional;
import cl.araucana.bienestar.bonificaciones.model.Rango;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.model.Talonario;
import cl.araucana.bienestar.bonificaciones.model.Vale;
import cl.araucana.bienestar.bonificaciones.model.VigenciaRango;
import cl.araucana.bienestar.bonificaciones.vo.AgrupacionCobertura;
import cl.araucana.bienestar.bonificaciones.vo.CasoAbiertoVO;
import cl.araucana.bienestar.bonificaciones.vo.CasoVO;
import cl.araucana.bienestar.bonificaciones.vo.CasosDescontadosSinFormatoVO;
import cl.araucana.bienestar.bonificaciones.vo.ContabilidadPendienteVO;
import cl.araucana.bienestar.bonificaciones.vo.ContabilidadVO;
import cl.araucana.bienestar.bonificaciones.vo.CuotaPendienteVO;
import cl.araucana.bienestar.bonificaciones.vo.CuotaPrestamoVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosInconsistenciaVO;
import cl.araucana.bienestar.bonificaciones.vo.DescuentoAplicadosSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.DescuentoPendienteSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.DescuentoTotalSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.DescuentosVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleAporteBienestarVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleBancoVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleCasosDescontadosConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosConveniosVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosOficinaVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleInformeReembolsosVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleMovimientoPreCasoVO;
import cl.araucana.bienestar.bonificaciones.vo.DetallePagoConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.InfoMovimientoPreCasoVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeDescuentosConveniosVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeDescuentosVO;
import cl.araucana.bienestar.bonificaciones.vo.InformePagoConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoConPrestamoVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoConvenioPendienteCuotaVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoConvenioPendienteVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoPendienteVO;
import cl.araucana.bienestar.bonificaciones.vo.ParamOperacionesVO;
import cl.araucana.bienestar.bonificaciones.vo.RangoCoberturaSinFormatoVO;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoTotalVO;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoVO;
import cl.araucana.bienestar.bonificaciones.vo.ResumenMovimientosConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.TalonarioVO;
import cl.araucana.common.BusinessException;

import com.schema.util.InstanceGenerator;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class DummyBonificacionesDAO implements BonificacionesDAO {

	Logger logger = Logger.getLogger(DummyBonificacionesDAO.class);
 
			
	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	public DummyBonificacionesDAO(){
		
	}	
	
	/** 
	 * Obtiene la lista de Socios de Bienestar
	 * @param Socio
	 * @return ArrayList de Socio
	 * @throws Exception
	 */
	public ArrayList getListaSocios(Socio socio) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Socio.class);	
	}
	
	/** 
	 * Obtiene un Socio de Bienestar
	 * @param rut del Socio
	 * @return Socio
	 * @throws Exception
	 */
	public Socio getSocio(String rut) throws Exception, BusinessException {

		return (Socio)InstanceGenerator.build(Socio.class);
	}
	
	/** 
	 * Obtiene la lista de Cargas Familiares registradas en Bienestar
	 * @param Carga
	 * @return ArrayList de Carga
	 * @throws Exception
	 */
	public ArrayList getListaCargas(Carga carga) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Carga.class);
				
	}

	/** 
	 * Obtiene una Carga Familiar desde Bienestar
	 * @param rut de la carga familiar buscada
	 * @return Carga
	 * @throws Exception
	 */
	public Carga getCarga(String rutCarga, String rutSocio) throws Exception, BusinessException {

		return (Carga)InstanceGenerator.build(Carga.class);
			
	}
	
	/** 
	 * Obtiene la lista de Cargas Familiares dependientes
	 * de un Socio que esten registradas en Bienestar
	 * @param rut del Socio
	 * @return ArrayList de Carga
	 * @throws Exception
	 */
	public ArrayList getCargasSocio(String rutSocio) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Carga.class);
		
	}

	/**
	 * Crea una nueva carga en Bienestar
	 * @param carga: el Objeto Carga
	 */
	public void insertCarga(Carga carga) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Modifica los datos de una carga en Bienestar
	 * @param carga: el Objeto Carga
	 */
	public void updateCarga(Carga carga) throws Exception, BusinessException {

		return;
		
	}
	
	/** 
	 * Obtiene la lista de conceptos existentes
	 * @param tipo de concepto
	 * @return ArrayList de Concepto
	 * @throws Exception
	 */
	public ArrayList getConceptos() throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Concepto.class);
			
	}
	
	/** 
	 * Obtiene un concepto existente
	 * @param codigo y tipo de concepto
	 * @return Concepto
	 * @throws Exception
	 */
	public Concepto getConcepto(long codigo) throws Exception, BusinessException {

		return (Concepto)InstanceGenerator.build(Concepto.class);
				
	}
	
	/**
	 * Crea un nuevo concepto en Bienestar
	 * @param concepto: el Objeto Concepto
	 */
	public void insertConcepto(Concepto concepto) throws Exception, BusinessException {

		return;
		
	}
	
	
	
	/**
	 * Actualiza un concepto de Bienestar
	 * @param concepto: el Objeto Concepto
	 */
	public void updateConcepto(Concepto concepto) throws Exception, BusinessException {

		return;
		
	}
	
	
	/**
	 * Elimina un concepto de Bienestar
	 * @param concepto: el Objeto Concepto
	 */
	public void deleteConcepto(Concepto concepto) throws Exception, BusinessException {

		return;
		
	}
	
	
	/**
	 * Registra el detalle del aporte de una cobertura para
	 * un detalle de caso en particular
	 * @param AporteCobertura
	 * @throws Exception
	 * @throws BusinessException
	 */

	public void registraAporteCobertura(AporteCobertura aporteCobertura) throws Exception, BusinessException {
	 

		return;
		
	}
	
	
	
	
	/**
	 * Elimina el detalle del aporte de bienestar pora el caso y cobertura
	 * @param long casoId, int idDetalle
	 */
	public void deleteAporteCoberturaByDetalle(long casoId, int idDetalle) throws Exception, BusinessException {

		return;
		
	}
	
	
	/** 
	 * Obtiene la lista de coberturas adicionales que tiene una cobertura
	 * @return ArrayList de CoberturaAdicional
	 * @throws Exception
	 */
	public ArrayList getRelacionCoberturaAdicional(long codigoCobertura) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(CoberturaAdicional.class);
				
	}
	
	
	/**
	 * Actualiza una relación coberturaAdicional
	 * @param CoberturaAdicional
	 */
	public void updateRelacionCoberturaAdicional(CoberturaAdicional coberturaAdicional) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Registra una relación CoberturaAdicional
	 * @param CoberturaAdicional
	 * @throws Exception
	 * @throws BusinessException
	 */

	public void registraRelacionCoberturaAdicional(CoberturaAdicional coberturaAdicional) throws Exception, BusinessException {
		
		return;
		
	}
	
	
	/**
	 * Elimina todas las relaciones donde el codigoCobertura pasado como parametro
	 * corresponda al codigo de coberturaAdicional
	 * @param codigo Cobertura
	 */
	public void deleteRelacionesCoberturaAdicionalByAdicional(long codigoCobertura) throws Exception, BusinessException {

		return;
		
	}
	
	
	/**
	 * Elimina la relación coberturaAdiconal para una cobertura
	 * @param codigo Cobertura
	 */
	public void deleteRelacionCoberturaAdicional(long codigoCobertura) throws Exception, BusinessException {

		return;
		
	}
	
	/** 
	 * Busca si la cobertura pasada como parámetro
	 * tiene la definición de rangos en otra cobertura,
	 * en caso de ser así, significa que ambas coberturas
	 * utilizan los mismos aportes de bienestar. Si n o tiene devuelve cero
	 * @param long codigoCobertura 
	 * @return AgrupacionCobertura
	 * 	!= null si existe la relación
	 *  = null si no existe la relación
	 * @throws Exception
	 */
	public AgrupacionCobertura getRelacionAgrupacionCobertura(long codigoCobertura) throws Exception, BusinessException {

		return (AgrupacionCobertura)InstanceGenerator.build(AgrupacionCobertura.class);
		
 }
	
	/** 
	 * Busca todas la relaciones que posee una cobertura maestra
	 * @param long codigoCoberturaMaestra 
	 * @return ArrayList de AgrupacionCobertura
	 * @throws Exception
	 */
	public ArrayList getRelacionAgrupacionCoberturaByCoberturaMaestra(long codigoCobertura) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(AgrupacionCobertura.class);
			
	}	
		
	
	/** 
	 * Obtiene la lista de Coberturas (Bonificaciones) del tipo y estado solicitado
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getListaCoberturasPorTipoAndEstado(Cobertura cobertura) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Cobertura.class);
		
	}
	
	/** 
	 * Obtiene la lista de Coberturas que no esten en la lista de productos de un convenio
	 * @param codigo de convenio
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getComplementoProductos(long codigoConvenio, Cobertura cobertura) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Cobertura.class);
			
	}
	
	
	/** 
	 * Obtiene la lista de Coberturas (Bonificaciones) existentes
	 * @param Cobertura
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getCoberturas(Cobertura cobertura) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Cobertura.class);
		
	}
	
	

	/** 
	 * Obtiene una Cobertura (Bonificacion) existente
	 * @param codigo de cobertura
	 * @return Cobertura
	 * @throws Exception
	 */
	public Cobertura getCobertura(long codigoCobertura) throws Exception, BusinessException {

		return (Cobertura)InstanceGenerator.build(Cobertura.class);
			
	}
	
	/**
	 * Crea una nueva cobertura en Bienestar
	 * @param Cobertura
	 */
	public void insertCobertura(Cobertura cobertura) throws Exception,BusinessException {

		return;
		
	}
	
	/**
	 * Actualiza una cobertura en Bienestar
	 * @param Cobertura
	 */
	public void updateCobertura(Cobertura cobertura) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Crea un nuevo  Pago ya realizado
	 * @param PagoConvenioPendienteVO
	 */
	public void insertPagoConvenio(PagoConvenioPendienteVO pagoConvenio) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Actualiza un VigenciaRango
	 * @param VigenciaRango
	 */
	public void updateVigenciaRango(VigenciaRango vigenciaRango) throws Exception, BusinessException {

		return;
		
	}
	
	
	/**
	 * Elimina un VigenciaRango
	 * @param VigenciaRango
	 */
	public void deleteVigenciaRango(VigenciaRango vigenciaRango) throws Exception, BusinessException {

		return;
		
	}
	
	/** 
	 * Obtiene todos los Rangos de una Cobertura (Bonificacion) existentes
	 * Obtiene el rango vigente si existe, los rangos historicos y el rango futuro si existe
	 * @param codigo Cobertura
	 * @return ArrayList RangoCoberturaSinFormatoVO
	 * @throws Exception
	 */	 
	public ArrayList getAllRangosCobertura(long codigoCobertura) throws Exception,BusinessException{

		return (ArrayList)InstanceGenerator.buildCollection(RangoCoberturaSinFormatoVO.class);
		
	}
	
	/** 
	 * Obtiene el mayor codigo de rangoCobertura existente en la BD para la cobertura especificada
	 * @param codigo Cobertura
	 * @return int maximo actual
	 * @throws Exception
	 */	 
	public int getMaximoRangoByCobertura(long codigoCobertura) throws Exception,BusinessException{

		return 1;
		
	}
	
	
	/**
	 * Crea un nuevo rango asociado a una cobertura en Bienestar
	 * @param rango: el Objeto Rango
	 * @param long codigoCobertura
	 * @param long codigoGrupoRango
	 */
	public void insertRangoCobertura(Rango rango,long codigoCobertura, long codigoGrupoRango) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Elimina los rangos de cobertura en Bienestar
	 * @param long codigoCobertura
	 * @param long codigoGrupoRango
	 */
	public void deleteRangosCobertura(long codigoCobertura, long codigoGrupoRango) throws Exception, BusinessException {

		return;
		
	}
	
	/** 
	 * Obtiene la lista de Convenios existentes
	 * @param Convenio
	 * @return ArrayList de Convenio
	 * @throws Exception
	 */
	public ArrayList getConvenios(Convenio convenio, long codigoCobertura) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Convenio.class);
			
	}
	
	
	/** 
	 * Obtiene un Convenio existente
	 * @param codigo de Convenio
	 * @return Convenio
	 * @throws Exception
	 */
	public Convenio getConvenio(long codigoConvenio) throws Exception, BusinessException {

		return (Convenio)InstanceGenerator.build(Convenio.class);
			
	}
	
	/**
	 * Crea un nuevo Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void insertConvenio(Convenio convenio) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Modifica un Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void updateConvenio(Convenio convenio) throws Exception, BusinessException {

		return;
		
	}
	
	/** 
	 * Obtiene la lista de Talonarios segun parametros de filtro
	 * @param talonario, Objeto alonario paa opciones de filtro y codigo convenio
	 * @return ArrayList de Talonario
	 * @throws Exception
	 */
	public ArrayList getTalonarios(Talonario talonario, long codigoConvenio) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Talonario.class);
			
	}
	
	
	/** 
	 * Obtiene un Talonario existente
	 * @param codigo
	 * @return ArrayList de Talonario
	 * @throws Exception
	 */
	public Talonario getTalonario(long codigoTalonario) throws Exception, BusinessException {

		return (Talonario)InstanceGenerator.build(Talonario.class);
			
	}
	
	/** 
	 * Obtiene la lista de Talonarios segun parametros de filtro
	 * @param talonario, Objeto alonario para opciones de filtro
	 * @return ArrayList de TalonarioVO
	 * @throws Exception
	 */
	public ArrayList getTalonariosVO(TalonarioVO talonarioVO) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(TalonarioVO.class);
				
	}
	
	/**
	 * Crea un nuevo Talonario asociado a un Convenio en Bienestar
	 * @param talonario: el Objeto Talonario y codigo de convenio
	 */
	public void insertTalonario(Talonario talonario, long codigoConvenio) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Actualiza la informacion de un Talonario asociado a un Convenio en Bienestar
	 * @param talonario: el Objeto Talonario y codigo de convenio
	 */
	public void updateTalonario(Talonario talonario) throws Exception, BusinessException {

		return;
		
	}
	
	/** 
	 * Obtiene la lista de Productos asociados a un convenio
	 * @param producto Producto
	 * @return ArrayList de Producto
	 * @throws Exception
	 */
	public ArrayList getProductos(Producto producto, long codigoConvenio) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Producto.class);
			
	}

	/** 
	 * Obtiene un Producto asociado a un convenio
	 * @param codigo de producto y codigo de convenio
	 * @return producto
	 * @throws Exception
	 */
	public Producto getProducto(long codProducto, long codConvenio) throws Exception, BusinessException {

		return (Producto)InstanceGenerator.build(Producto.class);
			
	}
	
	/**
	 * Crea un nuevo Producto asociado a un Convenio en Bienestar
	 * @param producto: el Objeto Producto y codigo de convenio
	 */
	public void insertProducto(Producto producto, long codigoConvenio) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Actualiza un Producto asociado a un Convenio en Bienestar
	 * @param producto: el Objeto Producto y codigo de convenio
	 */
	public void updateProducto(Producto producto, long codigoConvenio) throws Exception, BusinessException {

		return;
		
	}
	
	
	/**
	 * Actualiza la cuenta de gastos de un Producto asociado a un Convenio en Bienestar
	 * @param producto: el Objeto Producto y codigo de convenio
	 */
	public void updateCuentaProducto(Producto producto, long codigoConvenio) throws Exception, BusinessException {

		return;
		
	}
	
	
	/**
	 * Actualiza el Estado de los Producto de un mismo código
	 * @param codigoProducto
	 * @param estadoOld
	 * @param estadoNew
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void updateEstadoProductos(long codigoProducto, String estadoOld,String estadoNew) throws Exception, BusinessException {

		return;
		
	}
	
	/** 
	 * Obtiene los Rangos de un Producto existente
	 * @param codigo convenio y codigo de producto
	 * @return ArrayList de Rangos
	 * @throws Exception
	 */
	public ArrayList getRangosProducto(long codigoConvenio, long codigoProducto) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Rango.class);
			
	}
	
	/**
	 * Crea un nuevo rango asociado a un producto
	 * @param producto: el Objeto Producto, codigo de Convenio y codigo de producto
	 */
	public void insertRangoProducto(Rango rango,long codigoConvenio, long codigoCobertura) throws Exception ,BusinessException{

		return;
		
	}
	
	
	
	/**
	 * Elimina los rangos asociado a un Producto
	 * @param codigo de Convenio y codigo de producto
	 */
	public void deleteRangosProducto(long codigoConvenio, long codigoProducto) throws Exception, BusinessException {

		return;
		
	}
	
	/** 
	 * Obtiene una lista de Vales
	 * @param vale Objeto Vale
	 * @return ArrayList de Vale
	 * @throws Exception
	 */
	public ArrayList getVales(Vale vale, String socioRut, long codigoConvenio) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Vale.class);
			
	}
	
	
	/**
	 * Asocia un Vale con un Socio
	 * @param vale, Objeto Vale y rut de Socio
	 */
	public void insertVale(Vale vale, String rutSocio) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Actualiza la informacion de un Vale
	 * @param vale, Objeto Vale y rut de Socio
	 */
	public void updateVale(Vale vale, String rutSocio) throws Exception, BusinessException {

		return;
		
	}
	
	
	/** 
	 * Obtiene el ID disponible de la tabla pasada como parametro
	 * Es para mantener un valor que sirva como identity
	 * @param nombre de la tabla que necesita un nuevo ID
	 * @return ID disponible
	 * @throws Exception
	 */
	public long getIDDisponible(String tabla) throws Exception, BusinessException {

		return 1;
				
	}
	
	/** 
	 * Actualiza el ID disponible de la tabla pasada como parametro
	 * Es para mantener un valor que sirva como identity
	 * @param nombre de la tabla que necesita un nuevo ID
	 * @param nuevo valor disponible
	 * @return ID disponible
	 * @throws Exception
	 */
	public int updateIDDisponible(String tabla, double nuevoValorDisponible) throws Exception,BusinessException {

		return 1;
		
	}
	
	/**
	 * Obtiene un nuevo ID para la tabla pasada como parametro
	 * @param nombre de la tabla que necesita un nuevo ID
	 */
	public long getID(String tabla) throws Exception, BusinessException {
		
		return 1;
		
	}
	
	
	/** 
	 * Obtiene una lista de Eventos
	 * @param codigo de caso (caso ID)
	 * @param evento, Objeto Evento con filtros posibles
	 * @return ArrayList de Evento
	 * @throws Exception
	 */
	public ArrayList getEventos(long casoId, Evento evento) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Evento.class);
				
	}
	
	
	/**
	 * Crea un Evento asociado a un Caso
	 * @param caso Id y Evento con los datos 
	 */
	public void insertEvento(long casoId, Evento evento) throws Exception, BusinessException {

		return;
		
	}
	
	/** 
	 * Obtiene una lista de Detalle de Caso
	 * @param codigo de caso (caso ID)
	 * @return ArrayList de Detalle Caso
	 * @throws Exception
	 */
	public ArrayList getDetallesCaso(long casoId) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(DetalleCaso.class);
			
	}
	
	/**
	 * Crea un Detalle asociado a un Caso
	 * @param caso Id y Detalle Caso con los datos 
	 */
	public void insertDetalle(long casoId, long codigoConvenio, DetalleCaso detalleCaso) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Modifica un Detalle asociado a un Caso
	 * @param caso Id y Detalle caso con los datos 
	 */
	public void updateDetalle(long casoId, DetalleCaso detalleCaso) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Elimina un Detalle asociado a un Caso
	 * @param caso Id y Detalle caso con los datos 
	 */
	public void deleteDetalle(long casoId, DetalleCaso detalleCaso) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Elimina los Detalles asociados a un Caso
	 * @param caso Id 
	 */
	public void deleteDetalles(long casoId) throws Exception, BusinessException {
		
		return;
		
	}
	
	/** 
	 * Obtiene una lista de Cuotas
	 * @param casoId y Cuota como Filtro
	 * @return ArrayList de Cuota
	 * @throws Exception
	 */
	public ArrayList getCuotasCaso(long casoId, Cuota cuotaFiltro) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Cuota.class);
			
	}
	
	/**
	 * Crea una cuota asociada a un Caso
	 * @param caso Id y cuota con los datos 
	 */
	public void insertCuotaCaso(long casoId, Cuota cuota) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Modifica una cuota asociada a un Caso
	 * @param caso Id y cuota con los datos 
	 */
	public void updateCuotaCaso(long casoId, Cuota cuota) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Elimina las cuotas asociadas a un Caso
	 * @param caso Id 
	 */
	public void deleteCuotasCaso(long casoId) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Crea un Caso
	 * @param caso
	 * @return ID del Caso insertdo
	 */
	public long insertCaso(Caso caso) throws Exception, BusinessException {

		return 1;
		
	}
	
	/**
	 * Actualiza un Caso
	 * @param caso
	 */
	public void updateCaso(Caso caso) throws Exception, BusinessException {

		return;
		
	}
	
	/** 
	 * Obtiene informacion de un Caso
	 * @param codigo de caso (caso ID)
	 * @return CasoVO
	 * @throws Exception
	 */
	public CasoVO getCasoVO(long casoId) throws Exception, BusinessException {

		return (CasoVO)InstanceGenerator.build(CasoVO.class);
				
	}
	
	
	/** 
	 * Obtiene una lista de casos segun los parametros
	 * @param Caso
	 * @return ArrayList de CasoVO
	 * @throws Exception
	 */
	public ArrayList getCasos(Caso caso, long codigoCobertura) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(CasoVO.class);
				
	}
	
	
	/**
	 * Calcula el aporte de Bienestar en otros casos segun los parametros pasados
	 * siempre para una cobertura especifica.
	 * @param rutSocio
	 * @param rutCarga
	 * @param tipoTope
	 * @param codigoCobertura
	 * @param fechaInicio
	 * @param fechaFin
	 * @return double (aporte de bienestar en otros casos dentro del periodo consultado)
	 * @throws Exception
	 */
	public double calculaAporteBienestar(String rutSocio, String rutCarga, String tipoTope, long codigoCobertura, Date fechaInicio, Date fechaFin, long casoId) throws Exception, BusinessException {
		return 1;
			
	}
	
	/**
	 * Devuelve una lista con los aportes realzados por Bienestar y aportes de Socio,
	 * etc. para los productos de un convenio en particular
	 * @param codigoConvenio
	 * @param fechaInicio
	 * @param fechaFin
	 * @param AporteBienestarVO con filtros
	 * @return ArrayList de DetalleAporteBienestarVO
	 * @throws Exception
	 */
	public ArrayList getResumenAportesBienestar(Date fechaInicio, Date fechaFin, DetalleAporteBienestarVO resumenFiltro) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(DetalleAporteBienestarVO.class);
			
	}
	
	
	/**
	 * Devuelve una lista con los aportes realzados por Bienestar y aportes de Socio,
	 * etc. para los productos de un convenio en particular
	 * @param codigoConvenio
	 * @param fechaInicio
	 * @param fechaFin
	 * @return ArrayList de ResumenMovimientosConvenioVO
	 * @throws Exception
	 */
	public ArrayList movimientosConvenio(long codigoConvenio, Date fechaInicio, Date fechaFin, ResumenMovimientosConvenioVO resumenFiltro) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(ResumenMovimientosConvenioVO.class);
			
	}
		
	/** 
	 * Obtiene una lista con los Casos Por Reembolsar
	 * segun las fecha indicadas
	 * @param ParamOperacionesVO 
	 * @return ArrayList de ReembolsoVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorReembolsar(ParamOperacionesVO param, String rut) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(ReembolsoVO.class);
			
	}
	
	/** 
	 * Obtiene una lista con los Casos Por Descontar
	 * segun las fecha indicadas
	 * @param ParamOperacionesVO
	 * @return ArrayList de DescuentosVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorDescontar(ParamOperacionesVO param) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(DescuentosVO.class);
			
	}
	
	/** 
	 * Obtiene información de la siguiente cuota por cobrar al Socio
	 * @return CuotaPendienteVO
	 * @throws Exception
	 */
	public CuotaPendienteVO getCuotaNoCobrada(long casoId) throws Exception, BusinessException {

		return (CuotaPendienteVO)InstanceGenerator.build(CuotaPendienteVO.class);
				
	}
	
	/** 
	 * Obtiene información de la siguiente cuota por pagar al convenio
	 * @return CuotaPendienteVO
	 * @throws Exception
	 */
	public CuotaPendienteVO getCuotaNoPagada(long casoId) throws Exception, BusinessException {

		return (CuotaPendienteVO)InstanceGenerator.build(CuotaPendienteVO.class);
				
	}
	
	/** 
	 * Obtiene una lista con los Casos Por Pagar
	 * segun el codigo de descuento pasado como parametro
	 * @return ArrayList de PagoConvenioVO
	 * @throws Exception
	 */

	public ArrayList getCasosPorPagar(long codigoDescuento) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(PagoConvenioVO.class);
				
	}
	

	/**
	 * Actualiza el estado e indicador de reembolso de un caso
	 * valida que el caso se encuentre en un estado válido para reembolsar
	 * @param casoReembolso
	 * @param estadoPrevio
	 * @param indicadorPrevio
	 * @return nuúmero de filas afectadas
	 * @throws Exception
	 * @throws BusinessException
	 */

	public int updateIndicadorReembolso(ReembolsoVO casoReembolso, String estadoPrevio, String indicadorPrevio) throws Exception, BusinessException {
		
		return 1;
		
  }
  
	  /**
	   * Actualiza el estado e indicador de egreso de un preCaso
	   * valida que el caso se encuentre en un estado precaso
	   * @param CasoVO casoVo
	   * @param String estadoPrevio
	   * @return número de filas afectadas
	   * @throws Exception
	   * @throws BusinessException
	   */
	  public int updateIndicadorEgresoPreCaso(CasoVO casoVo, String estadoPrevio) throws Exception, BusinessException {
		
		return 1;
		
	}
	
	/**
	 * Actualiza el estado e indicador de ingreso de un preCaso
	 * valida que el caso se encuentre en un estado precaso
	 * @param CasoVO casoVo
	 * @param String estadoPrevio
	 * @return número de filas afectadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int updateIndicadorIngresoPreCaso(CasoVO casoVo, String estadoPrevio) throws Exception, BusinessException {
		 
		return 1;
		
	}	
  
  /**
   * Actualiza el estado e indicador de descuento de un caso
   * valida que el caso se encuentre en un estado válido para descontar
   * @param casoDescuento
   * @param estadoPrevio
   * @param indicadorPrevio
   * @return nuúmero de filas afectadas
   * @throws Exception
   * @throws BusinessException
   */
  public int updateIndicadorDescuento(DescuentosVO casoDescuento, String indicadorPrevio, String estadoPrevio) throws Exception, BusinessException {
	 
		return 1;
	
	}
	
	/**
	 * Actualiza el estado e indicador de Pago de un caso
	 * valida que el caso se encuentre en un estado válido para pagar
	 * @param casoPago
	 * @param indicadorPrevio
	 * @return nuúmero de filas afectadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int updateIndicadorPago(PagoConvenioVO casoPago, String indicadorPrevio, String estadoPrevio) throws Exception, BusinessException {

		return 1;
		
  }
  
	  /**
	   * Actualiza el estado e indicador de Pago Anticipado de un Caso
	   * además, actualiza los datos del pago anticipado:
	   * 	monto abono, monto prestamo, num prestamo
	   * valida que el caso se encuentre en un estado válido para pagar anticipadamente
	   * @param caso
	   * @param indicadorPrevio
	   * @throws Exception
	   * @throws BusinessException
	   */
	  public void updateIndicadorPagoAnticipado(Caso caso) throws Exception, BusinessException {
	
	
	}
  
  /**
   * Registra un Caso a Reembolsar
   * @param casoReembolso
   * @throws Exception
   * @throws BusinessException
   */

  public void insertReembolso(ReembolsoVO casoReembolso) throws Exception, BusinessException {


	
  }
  
  public void insertInfoAdiReembolso(ReembolsoVO casoReembolso) throws Exception, BusinessException{
	  
  }
  
  /**
   * Registra un nuevo Reembolso Total
   * Retorna el codigo de Reembolso generado
   */
  public long insertReembolsoTotal(ReembolsoTotalVO reembolsoTotal) throws Exception, BusinessException {

	return 1;
	 
  }
  
  
  /**
   * Actualiza un Reembolso Total
   * Retorna el codigo de Reembolso generado
   */

  public void updateReembolsoTotal(ReembolsoTotalVO reembolsoTotal) throws Exception, BusinessException {
	
  }

  public void insertEmailReembolsoTotal(ReembolsoTotalVO reembolsoTotal) throws Exception, BusinessException{

  }
  
  /**
   * Registra una Cuota de Prestamos
   * @param cuota
   * @throws Exception
   * @throws BusinessException
   */
  public void insertCuotaPrestamo(CuotaPrestamoVO cuota) throws Exception, BusinessException {

	return;
	
  }
  
  /**
   * Genera un nuevo código de Descuento
   * 
   */
  public long generaCodigoDescuento() throws Exception, BusinessException {

	return 1;
	
  }
  
  /**
   * Registra el total descontado a un socio
   * se suman los casos y los descuentos por los prestamos
   * @param casoReembolso
   * @throws Exception
   * @throws BusinessException
   */

  public void insertDescuentoTotalSocio(DescuentoTotalSocioVO descuentoTotal) throws Exception, BusinessException {

	return;
	
  }
  
  /** 
   * Obtiene las cuotas de los prestamos registradas en Bienestar
   * según los parametros pasados
   * @return ArrayList de CuotaPrestamoVO
   * @throws Exception
   */
  public ArrayList getCuotasPrestamo(CuotaPrestamoVO cuotaPrestamoFiltro) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(CuotaPrestamoVO.class);
			
  }
  
  /** 
   * Obtiene información de las cuotas de los prestamos que
   * se encuentran vigentes
   * 
   * @return ArrayList de CuotaPrestamoVO
   * @throws Exception
   */
  public ArrayList getCuotasPrestamoVigenteSocio(String rut) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(CuotaPrestamoVO.class);
			
  }
  
  /**
   * Registra un Caso a Descontar
   * @param casoDescuento
   * @throws Exception
   * @throws BusinessException
   */
  public void insertDescuento(DescuentosVO casoDescuento) throws Exception, BusinessException {

	return;
	
  }
  
  /** 
   * Obtiene la fecha en la cual se realizo
   * el ultimo proceso de descuento 
   * @return Date
   * @throws Exception
   */
  public Date getFechaUltimoDescuento() throws Exception, BusinessException {
	return new Date();	
  }  
  
  /**
   * Registra un Caso a Pagar
   * @param casoPago
   * @throws Exception
   * @throws BusinessException
   */
  public void insertPago(PagoConvenioVO casoPago) throws Exception, BusinessException {

	return;
	
  }
  
  
  /**
   * Obtiene una lista con los descuentos que se efectuaron en 
   * el mes consultado 
   * @param mes de consulta
   * @return ContabilidadVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getDescuentosMes(long codigoDescuento) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(ContabilidadVO.class);
	
  }
  
  /**
   * Obtiene una lista con casos que se pagaron totalmente en forma anticipada
   * por pago con pretamo y/o abono del socio
   * @param Date fechaInicio, Date fechaFin
   * @return ArrayList de ContabilidadVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getCasosPagadosAnticipadamente(Date fechaInicio, Date fechaFin) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(ContabilidadVO.class);
	
  }  
	
  /**
   * Obtiene una lista con los reembolsos que se efectuaron en
   * el mes consultado
   * @param mes de consulta
   * @return ContabilidadVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getReembolsosMes(Date fechaInicio, Date fechaFin) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(ContabilidadVO.class);
	
  }
  
  /**
   * Obtiene una lista con los codigos de reembolsos por contabilizar 
   * @param mes de consulta
   * @return InformacionAsiento
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getCodigoReembolsosPorContabilizar(Date fechaInicio, Date fechaFin) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(InformacionAsiento.class);
	
  }
	
  /**
   * Obtiene una lista con las cuotas de los préstamos que se
   * descontaron en el mes consultado 
   * @param mes de consulta
   * @return ContabilidadVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getPrestamoMes(long codigoDescuento) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(ContabilidadVO.class);
	
  }
  
  
  /** 
   * Obtiene la información de los totales de un reembolso semanal
   * @param codigoReembolso, si es 0 (cero) los trae todos
   * @return ReembolsoTotalVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getReembolsoTotal(long codigoReembolso) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(ReembolsoTotalVO.class);
		
  }
  
  /** 
   * Obtiene la información de los detalles de un reembolso semanal
   * @param codigoReembolso
   * @return ReembolsoVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getDetallesReembolso(long codigoReembolso) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(ReembolsoVO.class);	
	
  }
  
  /** 
   * Obtiene la información resumida por Oficina de los detalles de un reembolso semanal
   * @param codigoReembolso
   * @return DetalleInformeReembolsosVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getResumenReembolso(long codigoReembolso) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(DetalleInformeReembolsosVO.class);
		
  }
  
  
  /**
   * Actualiza las cuotas de los prestamos con el codigo de 
   * descuento asignado en el proceso
   * 
   */
  public void updateCuotasPrestamos(long codigoDescuento, Date fecha) throws Exception, BusinessException {

	return;
	
  }
  
  /** 
   * Obtiene información de los codigos de descuento generados y la fecha de
   * descuento. (Los cuales se realizan en forma mensual) 
   * @return InformeDescuentosVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getDescuentosRealizados() throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(InformeDescuentosVO.class);
			
  }
  
  /** 
   * Obtiene información de los codigos de descuento en los cuales el socio ha enido
   * descuento. (Los cuales se realizan en forma mensual)
   * @param String rutSocio
   * @return ArrayList de InformeDescuentosVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getDescuentosRealizadosBySocio(String rutSocio) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(InformeDescuentosVO.class);
			
  }    
  
  /** 
   * Obtiene información de los codigos de descuento generados y la fecha de
   * descuento. (Los cuales se realizan en forma mensual)
   * Esta información corresponde a los cobros que se debe realizar a los 
   * convenios (es para los convenios en los cuales ellos pagan el co-pago del socio 
   * para ciertas prestaciones.
   * @param 
   * @return InformeDescuentosConveniosVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getDescuentosConvenios() throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(InformeDescuentosConveniosVO.class);
		
  }
  
  
  /** 
   * Obtiene los montos que se deben descontar a cada convenio luego del proceso de 
   * descuento. (Los cuales se realizan en forma mensual)
   * Esta información corresponde a los cobros que se debe realizar a los 
   * convenios (es para los convenios en los cuales ellos pagan el co-pago del socio 
   * para ciertas prestaciones.
   * @param long codigoConvenio
   * @param long codigoDescuento
   * @return ArrayList de DetalleDescuentosConveniosVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getConveniosConDescuentos(long codigoConvenio, long codigoDescuento) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(DetalleDescuentosConveniosVO.class);
		
  }
  
  /**
   * Genera el detalle del descuento que se debe realizar a un convenio
   * @param codigo de convenio
   * @param long codigoDescuento
   * @return ArrayList de DetalleCasosDescontadosConvenioVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getDetalleDescuentosConvenio(long codigoConvenio,long codigoDescuento) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(DetalleCasosDescontadosConvenioVO.class);
			
  }
  
  /** 
   * Obtiene información de las oficinas que presentan descuento en sus 
   * empleados para el codigo de descuento pasado como parametro
   * @param long codigoDescuento, String codigoOficina
   * @return DetalleDescuentosOficinaVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getOficinasInformeDescuentos(long codigoDescuento, String codigoOficina) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(DetalleDescuentosOficinaVO.class);
			
  }
  
  /** 
   * Obtiene información de los totales descontados a cada socio
   * que presenta descuento para el codigo de descuento pasado como parametro
   * @param long codigoDescuento, String rut, String oficina
   * @return ArrayList de DetalleDescuentosSocioVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getSociosInformeDescuentos(long codigoDescuento, String rut, String oficina) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(DetalleDescuentosSocioVO.class);
		
  }
  
  /** 
   * Obtiene información de los casos descontados a cada socio
   * 2005.05.18 Tambien obtiene información del detalle del caso
   * que presenta descuento para el codigo de descuento pasado como parametro
   * @param long codigoDescuento
   * @param String rut
   * @param String oficina
   * @return ArrayList de CasosDescontadosSinFormatoVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getCasosInformeDescuentos(long codigoDescuento, String rut, String oficina) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(CasosDescontadosSinFormatoVO.class);
		
  }
  
  
  /** 
   * Obtiene información de los codigos de pagos generados y la fecha de
   * pago. (Los cuales se realizan en forma mensual) 
   * @return InformePagoConvenioVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getPagosRealizados() throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(InformePagoConvenioVO.class);
		
  }
  
  
  /** 
   * Obtiene información de los convenios que presentan pago 
   * para el codigo de pago pasado como parametro
   * @param long codigoPago
   * @return DetallePagoConvenioVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getConveniosInformePago(long codigoPago) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(DetallePagoConvenioVO.class);
		
  }
  
  /** 
   * Obtiene información de los pagos que se deben realizar a 
   * los convenios para compras sin cuota
   * @param long codigoDescuento
   * @return ArrayList PagoConvenioPendienteVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getPagoConvenioPendientesSinCuotas(long codigoDescuento) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(PagoConvenioPendienteVO.class);
			
  }
  
  
  /** 
   * Obtiene información de los pagos que se deben realizar a 
   * los convenios para compras con cuota
   * @param long codigoDescuento
   * @return ArrayList PagoConvenioPendienteCuotaVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getPagoConvenioPendientesConCuotas(long codigoDescuento) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(PagoConvenioPendienteCuotaVO.class);
			
  } 
  
  /** 
   * Obtiene información de los codigos de descuento
   * que aún no se han pagado 
   * @return PagoPendienteVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getPagosPorRealizar() throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(PagoPendienteVO.class);
			
  }
  
  /**
   * Registra el codigo del descuento o del reembolso que se considera en 
   * la generación de los asientos contables 
   * 
   */
  public void insertInformacionAsientos(InformacionAsiento datos) throws Exception, BusinessException {

	return;
	
  }
  
  /** 
   * Obtiene información de los periodos
   * que aún no se han contabilizado
   * @param String tipo
   * @return ContabilidadPendienteVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getPeriodosPorContabilizar(String tipo) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(ContabilidadPendienteVO.class);
			
  }
  
  /** 
   * Obtiene el monto total descontado por bienestar en el proceso de descuento or concepto de prestamos
   * para el proceso de descuento consultado.
   * @param long codigoDescuento
   * @return int montoDescuento
   * @throws Exception
   * @throws BusinessException
   */
  public int getMontoTotalDescuentoPrestamos(long codigoDescuento) throws Exception, BusinessException {

	return 1;
			
  }
  
  
  /** 
   * Obtiene el monto total descontado por bienestar en el proceso de descuento 
   * consultado.
   * @param 
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public int getMontoTotalDescuento(long codigoDescuento) throws Exception, BusinessException {

	return 1;
		
  }
  
  
  /** 
   * Obtiene el monto total que se le debe a un convenio 
   * para un periodo de descuento consultado
   * @param long codigoDescuento
   * @return ContabilidadVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getAcreedoresConvenios(long codigoDescuento) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(ContabilidadVO.class);
		
  }
  
  
  /** 
   * Obtiene la información de los descuentos pendientes del socio 
   * @param String rut
   * @return DescuentoPendienteSocioVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getDescuentosPendientesSocio(String rut) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(DescuentoPendienteSocioVO.class);
		
  }
  
  /** 
   * Obtiene el monto total que bienestar aportado durante un mes para 
   * cada tipo de prestacion que presenta movimientos en el mes consultado
   * @param codigoDescuento
   * @param ArrayList codigosReembolso
   * @param Date fechaInicio, Date fechaFin 
   * @return ArrayList de ContabilidadVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getAportesBienestar(long codigoDescuento, ArrayList codigosReembolso, Date fechaInicio, Date fechaFin) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(ContabilidadVO.class);
		
  }
  
  /** 
   * Obtiene información de los descuentos aplicados a un Socio
   * @return ArrayList de DescuentoAplicadosSocioVO
   * @throws Exception
   */
  public ArrayList getDescuentosAplicadosSocio(String rut) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(DescuentoAplicadosSocioVO.class);
			
  }
  
  /** 
   * Obtiene una lista con información de los casos que se encuentran abiertos o en borrador
   * @return ArrayList de CasoAbiertoVO
   * @throws Exception
   */
  public ArrayList getCasosAbiertos(String rut) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(CasoAbiertoVO.class);
		
  }
  
  
  /**
   * Cambia el estado y la fecha del estado de un caso
   * @param CasoAbiertoVO
   * @throws Exception
   * @throws BusinessException
   */
  public void finalizaCaso(CasoAbiertoVO caso) throws Exception, BusinessException {
	
	return;
	
  }
  
  /**
   * Obtiene una lista con los casos cerrados por
   * pago con prestamo 
   * @param Date fechaInicio, Date fechaFin
   * @return ArrayList PagoConPrestamoVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getPagoConPrestamo(Date fechaInicio, Date fechaFin) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(PagoConPrestamoVO.class);
	
  }
  
  
  /**
   * Verifica si el caso existe,
   * si el caso existe retorna Caso,
   * si no existe retorna null 
   * @param 
   * @return Caso si existe, null si no existe
   * @throws Exception
   * @throws BusinessException
   */
  public Caso getCasoByUpLoadFile(Caso caso) throws Exception, BusinessException {

	return (Caso)InstanceGenerator.build(Caso.class);
	
  }
  
  /**
   * Crea un nuevo  VigenciaRango
   * @param VigenciaRango
   */
  public void insertVigenciaRango(VigenciaRango vigenciaRango) throws Exception, BusinessException {

	return;
	
  }
  
  /**
   * Registra la informaciòn de un moviiento registrado en tesoreria
   * debido a un pre-caso
   * @param InfoMovimientoPreCasoVO
   * @return void
   */
  public long insertMovimientoTesoreriaPreCaso(InfoMovimientoPreCasoVO infoMovimientoPreCasoVO) throws Exception, BusinessException {

	return 1;
	
  }
  
  /**
   * Información de los egresos y/o ingresos generados en tesoreria
   * @param long casoId
   * @return ArrayList de InfoMovimientoPreCasoVO
   * @throws Exception
   * @throws BusinessException
   */
  public ArrayList getMovimientosTesoreriaPreCaso(long casoId) throws Exception, BusinessException {

	return (ArrayList)InstanceGenerator.buildCollection(InfoMovimientoPreCasoVO.class);
	
  }  
  
  
  
	/** 
	 * Obtiene la lista de Profesionales
	 * @param Profesional
	 * @return ArrayList de Profesional
	 * @throws Exception
	 */
	public ArrayList getListaProfesionales(Profesional profesional) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(Profesional.class);
			
}


	/** 
	 * Obtiene la informacion de un Profesional
	 * @param String rut
	 * @return Profesional
	 * @throws Exception
	 */
	public Profesional getProfesionalByRut(String rut) throws Exception, BusinessException {

		return (Profesional)InstanceGenerator.build(Profesional.class);
				
	}

	/**
	 * Crea un nuevo profesional en Bienestar
	 * @param Profesional
	 */
	public void insertProfesional(Profesional profesional) throws Exception,BusinessException {
		
		return;
		
	}           
  
	/**
	 * Actualiza los Datos de un profesional en Bienestar
	 * @param socio: el Objeto Profesional
	 */
	public void updateProfesional(Profesional profesional) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Devuelve la suma de los egresos/ingresos previos del mismo caso
	 * @param long casoId
	 * @param String tipo (Ingreso/Egreso) 
	 * @return int, monto
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoMovimientosPrevios(long casoId, String tipo) throws Exception, BusinessException{

		return 1;
		
	}	
	
	/** 
	 * Obtiene una lista de pre-casos que cumplen con las condiciones entregadas
	 * @param Caso casoFiltro
	 * @return ArrayList de CasoVO
	 * @throws Exception
	 */
	public ArrayList getListaPreCasosByFiltro(Caso casoFiltro) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(CasoVO.class);
			
	} 
	
	/**
	 * Registra datos de u socio y cobertura con aportes inconsistentes
	 * @param DatosInconsistenciaVO
	 * @return void
	 */
	public long insertInconsistenciaAportesBienestar(DatosInconsistenciaVO datosInconsistenciaVO) throws Exception, BusinessException {

		return 1;
		
	}
	
	/**
	 * Crea un nuevo detalle con información del detalle de egreso en tesoreria
	 * @param DetalleMovimientoPreCasoVO
	 */
	public void insertDetalleEgreso(DetalleMovimientoPreCasoVO vo) throws Exception,BusinessException {

		return;
		
	}
	
	/**
	 * Obtiene una lista de detalles de egresos de pre-casos dado un numero de folio de tesoreria
	 * @param long folio
	 * @return ArrayList de DetalleMovimientoPreCasoVO
	 * @throws Exception
	 */
	public ArrayList getListaDetallesEgresoByFolio(long folio) throws Exception, BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(DetalleMovimientoPreCasoVO.class);
			
	}
	
	/**
	 * Devuelve la suma de los montos de los detalles de los movimientos previos del
	 * mismo caso y cobertura
	 * @param long casoId, long coberturaCodigo, int idDetalle
	 * @return int, monto
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoCoberturaMovimientosPrevios(long casoId, long coberturaCodigo, int idDetalle) throws Exception, BusinessException{

		return 0;
				
	}

	/* (no Javadoc)
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#getGrupoUsuario(long)
	 */
	public int getGrupoUsuario(long rut) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	/* (no Javadoc)
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#getConveniosRe(cl.araucana.bienestar.bonificaciones.model.Convenio, long, java.lang.String)
	 */
	public ArrayList getConveniosRe(Convenio convenio, long codigoCobertura, String convenios) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	/* (no Javadoc)
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#getCasoPorDescontar(int)
	 */
	public ArrayList getCasoPorDescontar(int casId) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	/* (no Javadoc)
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#getCasoPorDescontar(long)
	 */
	public ArrayList getCasoPorDescontar(long casId) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	/* (no Javadoc)
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#updateCuotasCaso(long, cl.araucana.bienestar.bonificaciones.model.Cuota)
	 */
	public void updateCuotasCaso(long casoId, Cuota cuota) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		
	}

	/* (no Javadoc)
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#getProximoCodigoDescuento()
	 */
	public long getProximoCodigoDescuento() throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	/* (no Javadoc)
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#getCuotasNoCobradas(long)
	 */
	public List getCuotasNoCobradas(long casoId) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	/* (no Javadoc)
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#deleteCuotasCasoNoCobradas(long)
	 */
	public void deleteCuotasCasoNoCobradas(long casoId) {
		// TODO Apéndice de método generado automáticamente
		
	}

	/* (no Javadoc)
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#getDescuentosRealizadosBySocioEnPeriodo(java.lang.String, long)
	 */
	public ArrayList getDescuentosRealizadosBySocioEnPeriodo(String rutSocio, long codigoDescuento) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	/* (no Javadoc)
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#updateDescuentoTotalSocio(cl.araucana.bienestar.bonificaciones.vo.DescuentoTotalSocioVO)
	 */
	public void updateDescuentoTotalSocio(DescuentoTotalSocioVO descuentoTotalSocio) throws BusinessException, Exception {
		// TODO Apéndice de método generado automáticamente
		
	}

	/* (no Javadoc)
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#getDetalleCasosPagoConvenioPendientesConCuotas(long)
	 */
	public ArrayList getDetalleCasosPagoConvenioPendientesConCuotas(long codigoDescuento) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	/* (no Javadoc)
	 * @see cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO#insertDescuentoTotalSocioSaldoDeuda(cl.araucana.bienestar.bonificaciones.vo.DescuentoTotalSocioVO)
	 */
	/*public void insertDescuentoTotalSocioSaldoDeuda(DescuentoTotalSocioVO descuentoTotal) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		
	}*/

	public void insertPagoAnticipado(DescuentosVO casoPagoAnticipado) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		
	}

	public ArrayList getpagoConvenioPendientesAnticipado(long codigoDescuento) throws BusinessException, Exception {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public void sincCargasActivasBonifConAsfam() throws BusinessException, Exception {
		// TODO Apéndice de método generado automáticamente
		
	}

	public void sincCargasInactivasBonifConAsfam() throws BusinessException, Exception {
		// TODO Apéndice de método generado automáticamente
		
	}

	public List getSociosInactivosConCasosAbiertos() throws BusinessException, Exception {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public ArrayList getCasosSocioInactivo(Caso caso, long codigoCobertura) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public List getDetallesCasoReembolso(long idCaso) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public boolean registraElimCobertura(long casoID, int aporteBienestar, long codigoCobertura, int idDetalle, String user) {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

	public boolean deleteAporteCobertura(long casoId) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

	public List getAporteCobertura(long casoId) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public List getBitacoraElimCobertura(long casoId) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public boolean registraBitacoraElimCobertura(long casoID, int aporteBienestar, long codigoCobertura, int idDetalle, String user) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

	public void registraAporteBienestarBonificacionEspecial(AporteCobertura aporteCobertura, String user) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		
	}

	public ArrayList getAportesBienestar_BonificacionEspecial(long codigoDescuento, ArrayList codigosReembolso, Date fechaInicio, Date fechaFin) throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	
	/**
	 * REQ 5065
	 * Busca si ya se encuentra registrado un documento. 
	 * @param rutSocio
	 * @param casoId
	 * @param numDocumento
	 * @param tipoDocumento
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public String getNumeroDocumento(String rutSocio, long casoId, String numDocumento, String tipoDocumento ) throws BusinessException, Exception{
		return null;
	}
	
	public DetalleBancoVO[] getDetalleBancos(long codigoReembolso) throws Exception, BusinessException {
		return null;
	}
	
	/**
	 * Obtiene la información del ultimo reembolso
	 * 
	 * @return ReembolsoTotalVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ReembolsoTotalVO getUltimoReembolsoTotal() throws Exception, BusinessException {
		return new ReembolsoTotalVO();
	}
	
	/**
	 * Obtiene la información de los detalles de un reembolso semanal por Socio
	 * 
	 * @param codigoReembolso
	 * @return lista de ReembolsoSocioVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ReembolsoSocioVO[] getDetallesReembolsoPorSocio(long codigoReembolso) throws Exception {
		return null;
	}
	
	/**
	 * Actualiza el indicador de envío de e-mail para un proceso de reembolso
	 * (Lo cambia a enviado)
	 */

	public void updateIndicadorEnvioEMailReembolso(long codigoReembolso) throws Exception, BusinessException {
			
	}
	
	/**
	 * Obtiene los parámetros configurados
	 * 
	 * @return HashMap con lista de ParametroVO
	 * @throws Exception
	 */
	public HashMap getParametros() throws Exception {
		return null;
	}
	
	/**
	 * Verifica si el usuario tiene casos activo y es disvinculado de la Caja
	 * 
	 * @return HashMap con lista de ParametroVO
	 * @throws Exception
	 */
	public boolean isSocioInactivosConCasosAbiertosByRut(String rut) throws BusinessException,Exception{
		return false;
	}
	
	/**
	 * Cambia el estado, fecha y deja en estado NO los campos (CASINDDES, CASINDBON, CASINDPAG) del caso
	 * 
	 * @param CasoAbiertoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void finalizaCasoEnProceso(Caso caso) throws Exception,BusinessException {
		
	}
		
}
