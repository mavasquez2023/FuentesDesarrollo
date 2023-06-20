package cl.laaraucana.rendicionpagonomina.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cl.laaraucana.rendicionpagonomina.ibatis.dao.BeneficioDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.BeneficioDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ConvenioDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ConvenioDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.EstadoDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.EstadoDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ProductoDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ProductoDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BeneficioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.EstadoEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ProductoEntity;
import cl.laaraucana.rendicionpagonomina.utils.DescripcionCodigo;

@Service
public class ParametrosServiceImpl implements ParametrosService{

	private ConvenioDao daoCon= new ConvenioDaoImpl();
	private ProductoDao daoPro= new ProductoDaoImpl();
	private EstadoDao daoEst= new EstadoDaoImpl();
	private BeneficioDao daoBen= new BeneficioDaoImpl();
	
	public void setParams() throws Exception{
		//CONVENIOS MAP
		List<ConvenioEntity> listaConvenios= consultaConvenios();
		Map<String , String> convenios= new HashMap<String, String>();
		for(ConvenioEntity convenio:listaConvenios){
			convenios.put(convenio.getCodigoConvenio(), convenio.getDescripcionConvenio());
		}
		DescripcionCodigo.setConvenios(convenios);
		
		//PRODUCTOS MAP
		List<ProductoEntity> listaProductos= consultaProductos();
		Map<String , String> productos= new HashMap<String, String>();
		for(ProductoEntity producto:listaProductos){
			productos.put(producto.getCodigoProducto(), producto.getDescripcionProducto());
		}
		DescripcionCodigo.setProductos(productos);
		
		//ESTADOS NOMINA MAP
		List<EstadoEntity> listaEstados= consultaEstadosNomina();
		Map<String , String> estados= new HashMap<String, String>();
		for(EstadoEntity estado:listaEstados){
			estados.put(estado.getCodigo(), estado.getDescripcion());
		}
		DescripcionCodigo.setEstados(estados);
		
		//ESTADOS PAGO MAP
		List<EstadoEntity> listaEstadosPago= consultaEstadosPago();
		Map<String , String> estadospago= new HashMap<String, String>();
		for(EstadoEntity estado:listaEstadosPago){
			estadospago.put(estado.getCodigo(), estado.getDescripcion());
		}
		DescripcionCodigo.setEstadosPago(estadospago);
		
		//BENEFICIOS MAP
		List<BeneficioEntity> listaBeneficios= consultaBeneficios();
		Map<String , String> beneficios= new HashMap<String, String>();
		for(BeneficioEntity beneficio:listaBeneficios){
			beneficios.put(beneficio.getCodigoBeneficio(), beneficio.getDescripcionBeneficio());
		}
		DescripcionCodigo.setBeneficios(beneficios);
		
	}
	
	public List<ConvenioEntity> consultaConvenios() throws Exception {
		// TODO Auto-generated method stub
		return daoCon.consultaConvenios();
	}
	
	public List<ConvenioEntity> consultaConveniosActivos(String idUsuario) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> param= null;
		if(idUsuario!=null){
			param= new HashMap<String, String>();
			param.put("idUsuario", idUsuario);
		}
		return daoCon.consultaConveniosActivos(param);
	}
	
	public List<ConvenioEntity> consultaConveniosconPlantilla(String idUsuario, String convenios) throws Exception {
		// TODO Auto-generated method stub
		HashMap params= new HashMap();
		if(idUsuario!=null){
			params.put("idUsuario", idUsuario);
		}
		if(convenios!=null){
			String[] lista_split= convenios.split(",");
			List<String> listaConvenios= new ArrayList<String>();
			for (int i = 0; i < lista_split.length; i++) {
				listaConvenios.add(lista_split[i]);
			}
			params.put("convenios", listaConvenios);

		}
		
		return daoCon.consultaConveniosconPlantilla(params);
	}
	
	public List<ConvenioEntity> consultaConveniosActivosManual(String idUsuario) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> param= null;
		if(idUsuario!=null){
			param= new HashMap<String, String>();
			param.put("idUsuario", idUsuario);
		}
		return daoCon.consultaConveniosActivosManual(param);
	}
	
	public ConvenioEntity getConvenio(int codigo) throws Exception {
		// TODO Auto-generated method stub
		return daoCon.getConvenio(codigo);
	}
	
	@Override
	public List<ProductoEntity> consultaProductos() throws Exception {
		// TODO Auto-generated method stub
		return daoPro.consultaProductos();
	}
	
	@Override
	public List<ProductoEntity> consultaProductosByConvenioCargaManual(int convenio) throws Exception {
		// TODO Auto-generated method stub
		return daoPro.consultaProductosByConvenioCargaManual(convenio);
	}

	@Override
	public List<EstadoEntity> consultaEstadosNomina() throws Exception {
		// TODO Auto-generated method stub
		return daoEst.consultaEstadosNomina();
	}

	@Override
	public List<EstadoEntity> consultaEstadosActivos() throws Exception {
		// TODO Auto-generated method stub
		return daoEst.consultaEstadosActivos();
	}
	
	@Override
	public List<EstadoEntity> consultaEstadosPago() throws Exception {
		// TODO Auto-generated method stub
		return daoEst.consultaEstadosPago();
	}
	
	@Override
	public List<ArchivoManualVO> consultaConveniosTransferencia()
			throws Exception {
		// TODO Auto-generated method stub
		return daoCon.getConvenioTransferencia();
	}

	@Override
	public List<BeneficioEntity> consultaBeneficios() throws Exception {
		// TODO Auto-generated method stub
		return daoBen.consultaBeneficios();
	}

	@Override
	public List<BeneficioEntity> consultaBeneficiosByParams(
			HashMap<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		return daoBen.consultaBeneficiosByParams(params);
	}

}
