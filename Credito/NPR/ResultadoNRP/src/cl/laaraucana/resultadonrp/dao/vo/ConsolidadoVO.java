/**
 * 
 */
package cl.laaraucana.resultadonrp.dao.vo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author IBM Software Factory
 *
 */
public class ConsolidadoVO{
	private int periodo;
	
	//Traabajadores
	private int registrosSAP_TRABAJADOR;
	private long montoSAP_TRABAJADOR;
	private int registrosSINACAFF_TRABAJADOR;
	private long montoSINACAFF_TRABAJADOR;
	//PENSIONADO
	private int registrosSAP_PENSIONADO;
	private long montoSAP_PENSIONADO;
	private int registrosSINACAFF_PENSIONADO;
	private long montoSINACAFF_PENSIONADO;
	
	//SubTotales TRABAJADOR
	private int registrosTRABAJADOR;
	private long montoTRABAJADOR;
	
	//Subtotales PENSIONADO
	private int registrosPENSIONADO;
	private long montoPENSIONADO;
	
	//Totales
	private int registrosDEUDORES;
	private long montoDEUDORES;
	
	
	//Total FOLIOS
	private int registrosFOLIOS;
	private long montoFOLIOS;
	private List<FolioVO> folios;
	
	//Productos
	private List<ProductoVO> productos;

	/**
	 * @return the periodo
	 */
	public int getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the registrosSAP_TRABAJADOR
	 */
	public int getRegistrosSAP_TRABAJADOR() {
		return registrosSAP_TRABAJADOR;
	}

	/**
	 * @param registrosSAP_TRABAJADOR the registrosSAP_TRABAJADOR to set
	 */
	public void setRegistrosSAP_TRABAJADOR(int registrosSAP_TRABAJADOR) {
		this.registrosSAP_TRABAJADOR = registrosSAP_TRABAJADOR;
	}

	/**
	 * @return the montoSAP_TRABAJADOR
	 */
	public long getMontoSAP_TRABAJADOR() {
		return montoSAP_TRABAJADOR;
	}

	/**
	 * @param montoSAP_TRABAJADOR the montoSAP_TRABAJADOR to set
	 */
	public void setMontoSAP_TRABAJADOR(long montoSAP_TRABAJADOR) {
		this.montoSAP_TRABAJADOR = montoSAP_TRABAJADOR;
	}

	/**
	 * @return the registrosSINACAFF_TRABAJADOR
	 */
	public int getRegistrosSINACAFF_TRABAJADOR() {
		return registrosSINACAFF_TRABAJADOR;
	}

	/**
	 * @param registrosSINACAFF_TRABAJADOR the registrosSINACAFF_TRABAJADOR to set
	 */
	public void setRegistrosSINACAFF_TRABAJADOR(int registrosSINACAFF_TRABAJADOR) {
		this.registrosSINACAFF_TRABAJADOR = registrosSINACAFF_TRABAJADOR;
	}

	/**
	 * @return the montoSINACAFF_TRABAJADOR
	 */
	public long getMontoSINACAFF_TRABAJADOR() {
		return montoSINACAFF_TRABAJADOR;
	}

	/**
	 * @param montoSINACAFF_TRABAJADOR the montoSINACAFF_TRABAJADOR to set
	 */
	public void setMontoSINACAFF_TRABAJADOR(long montoSINACAFF_TRABAJADOR) {
		this.montoSINACAFF_TRABAJADOR = montoSINACAFF_TRABAJADOR;
	}

	/**
	 * @return the registrosSAP_PENSIONADO
	 */
	public int getRegistrosSAP_PENSIONADO() {
		return registrosSAP_PENSIONADO;
	}

	/**
	 * @param registrosSAP_PENSIONADO the registrosSAP_PENSIONADO to set
	 */
	public void setRegistrosSAP_PENSIONADO(int registrosSAP_PENSIONADO) {
		this.registrosSAP_PENSIONADO = registrosSAP_PENSIONADO;
	}

	/**
	 * @return the montoSAP_PENSIONADO
	 */
	public long getMontoSAP_PENSIONADO() {
		return montoSAP_PENSIONADO;
	}

	/**
	 * @param montoSAP_PENSIONADO the montoSAP_PENSIONADO to set
	 */
	public void setMontoSAP_PENSIONADO(long montoSAP_PENSIONADO) {
		this.montoSAP_PENSIONADO = montoSAP_PENSIONADO;
	}

	/**
	 * @return the registrosSINACAFF_PENSIONADO
	 */
	public int getRegistrosSINACAFF_PENSIONADO() {
		return registrosSINACAFF_PENSIONADO;
	}

	/**
	 * @param registrosSINACAFF_PENSIONADO the registrosSINACAFF_PENSIONADO to set
	 */
	public void setRegistrosSINACAFF_PENSIONADO(int registrosSINACAFF_PENSIONADO) {
		this.registrosSINACAFF_PENSIONADO = registrosSINACAFF_PENSIONADO;
	}

	/**
	 * @return the montoSINACAFF_PENSIONADO
	 */
	public long getMontoSINACAFF_PENSIONADO() {
		return montoSINACAFF_PENSIONADO;
	}

	/**
	 * @param montoSINACAFF_PENSIONADO the montoSINACAFF_PENSIONADO to set
	 */
	public void setMontoSINACAFF_PENSIONADO(long montoSINACAFF_PENSIONADO) {
		this.montoSINACAFF_PENSIONADO = montoSINACAFF_PENSIONADO;
	}

	/**
	 * @return the registrosTRABAJADOR
	 */
	public int getRegistrosTRABAJADOR() {
		return registrosTRABAJADOR;
	}

	/**
	 * @param registrosTRABAJADOR the registrosTRABAJADOR to set
	 */
	public void setRegistrosTRABAJADOR(int registrosTRABAJADOR) {
		this.registrosTRABAJADOR = registrosTRABAJADOR;
	}

	/**
	 * @return the montoTRABAJADOR
	 */
	public long getMontoTRABAJADOR() {
		return montoTRABAJADOR;
	}

	/**
	 * @param montoTRABAJADOR the montoTRABAJADOR to set
	 */
	public void setMontoTRABAJADOR(long montoTRABAJADOR) {
		this.montoTRABAJADOR = montoTRABAJADOR;
	}

	/**
	 * @return the registrosPENSIONADO
	 */
	public int getRegistrosPENSIONADO() {
		return registrosPENSIONADO;
	}

	/**
	 * @param registrosPENSIONADO the registrosPENSIONADO to set
	 */
	public void setRegistrosPENSIONADO(int registrosPENSIONADO) {
		this.registrosPENSIONADO = registrosPENSIONADO;
	}

	/**
	 * @return the montoPENSIONADO
	 */
	public long getMontoPENSIONADO() {
		return montoPENSIONADO;
	}

	/**
	 * @param montoPENSIONADO the montoPENSIONADO to set
	 */
	public void setMontoPENSIONADO(long montoPENSIONADO) {
		this.montoPENSIONADO = montoPENSIONADO;
	}

	/**
	 * @return the registrosDEUDORES
	 */
	public int getRegistrosDEUDORES() {
		return registrosDEUDORES;
	}

	/**
	 * @param registrosDEUDORES the registrosDEUDORES to set
	 */
	public void setRegistrosDEUDORES(int registrosDEUDORES) {
		this.registrosDEUDORES = registrosDEUDORES;
	}

	/**
	 * @return the montoDEUDORES
	 */
	public long getMontoDEUDORES() {
		return montoDEUDORES;
	}

	/**
	 * @param montoDEUDORES the montoDEUDORES to set
	 */
	public void setMontoDEUDORES(long montoDEUDORES) {
		this.montoDEUDORES = montoDEUDORES;
	}

	/**
	 * @return the productos
	 */
	public List<ProductoVO> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<ProductoVO> productos) {
		this.productos = productos;
	}
	
	public void addProducto(ProductoVO producto) {
		if(this.productos==null){
			productos= new ArrayList<ProductoVO>();
		}
		this.productos.add(producto);
		try {
			//Set TipoAfiliado  x Origen
			Field campo_monto= this.getClass().getDeclaredField("monto"+ producto.getOrigen()+"_" + producto.getTipo_afiliado());
			campo_monto.setAccessible(true);
			campo_monto.setLong(this, campo_monto.getLong(this) + producto.getMonto());
			Field campo_registros= this.getClass().getDeclaredField("registros"+ producto.getOrigen()+"_" + producto.getTipo_afiliado());
			campo_registros.setAccessible(true);
			campo_registros.setInt(this, campo_registros.getInt(this) + producto.getRegistros());
			//Set subtotales x TipoAfiliado
			Field subtotal_monto= this.getClass().getDeclaredField("monto" + producto.getTipo_afiliado());
			subtotal_monto.setAccessible(true);
			subtotal_monto.setLong(this, subtotal_monto.getLong(this) + producto.getMonto());
			Field subtotal_registros= this.getClass().getDeclaredField("registros" + producto.getTipo_afiliado());
			subtotal_registros.setAccessible(true);
			subtotal_registros.setInt(this, subtotal_registros.getInt(this) + producto.getRegistros());
			//Set totales
			Field total_monto= this.getClass().getDeclaredField("montoDEUDORES");
			total_monto.setAccessible(true);
			total_monto.setLong(this, total_monto.getLong(this) + producto.getMonto());
			Field total_registros= this.getClass().getDeclaredField("registrosDEUDORES");
			total_registros.setAccessible(true);
			total_registros.setInt(this, total_registros.getInt(this) + producto.getRegistros());
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the registrosFOLIOS
	 */
	public int getRegistrosFOLIOS() {
		return registrosFOLIOS;
	}

	/**
	 * @param registrosFOLIOS the registrosFOLIOS to set
	 */
	public void setRegistrosFOLIOS(int registrosFOLIOS) {
		this.registrosFOLIOS = registrosFOLIOS;
	}
	
	/**
	 * @return the montoFOLIOS
	 */
	public long getMontoFOLIOS() {
		return montoFOLIOS;
	}

	/**
	 * @param montoFOLIOS the montoFOLIOS to set
	 */
	public void setMontoFOLIOS(long montoFOLIOS) {
		this.montoFOLIOS = montoFOLIOS;
	}
	
	
	/**
	 * @return the folios
	 */
	public List<FolioVO> getFOLIOS() {
		return folios;
	}

	/**
	 * @param folios the folios to set
	 */
	public void setFOLIOS(List<FolioVO> folios) {
		this.folios = folios;
	}
	
	public void addFolio(FolioVO folio) {
		if(this.folios==null){
			folios= new ArrayList<FolioVO>();
		}
		this.folios.add(folio);
		try {
			//Set totales
			Field total_monto= this.getClass().getDeclaredField("montoFOLIOS");
			total_monto.setAccessible(true);
			total_monto.setLong(this, total_monto.getLong(this) + folio.getMonto());
			Field total_registros= this.getClass().getDeclaredField("registrosFOLIOS");
			total_registros.setAccessible(true);
			total_registros.setInt(this, total_registros.getInt(this) + folio.getRegistros());
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ConsolidadoVO consolidar = new ConsolidadoVO();
		ProductoVO producto= new ProductoVO();
		producto.setMonto(10000);
		producto.setRegistros(10);
		producto.setOrigen("SAP");
		producto.setTipo_afiliado("TRABAJADOR");
		producto.setProducto("CREDITO");
		consolidar.addProducto(producto);
		producto= new ProductoVO();
		producto.setMonto(15000);
		producto.setRegistros(20);
		producto.setOrigen("SAP");
		producto.setTipo_afiliado("TRABAJADOR");
		producto.setProducto("SAE");
		consolidar.addProducto(producto);
		producto= new ProductoVO();
		producto.setMonto(5000);
		producto.setRegistros(20);
		producto.setOrigen("SINACAFF");
		producto.setTipo_afiliado("TRABAJADOR");
		producto.setProducto("CREDITO");
		consolidar.addProducto(producto);
		producto= new ProductoVO();
		producto.setMonto(1000);
		producto.setRegistros(10);
		producto.setOrigen("SAP");
		producto.setTipo_afiliado("PENSIONADO");
		producto.setProducto("CREDITO");
		consolidar.addProducto(producto);
		
		System.out.println(consolidar.getMontoSAP_TRABAJADOR());
		System.out.println(consolidar.getRegistrosSAP_TRABAJADOR());
		System.out.println(consolidar.getMontoTRABAJADOR());
		System.out.println(consolidar.getRegistrosTRABAJADOR());
		System.out.println(consolidar.getMontoDEUDORES());
		System.out.println(consolidar.getRegistrosDEUDORES());
		
	}
}
