package cl.laaraucana.rendicionpagonomina.vo;

import java.util.HashMap;

public class DetalleCreditoVO {
	

	private String numeroCuenta = null;
	private String bancoDestino = null;
	private Integer rutAfiliado = null;
	private String dvRutAfiliado = null;
	private String nombreAfiliado = null;
	private Integer monto = null ;
	private String numeroFactura = null;
	private String numeroOrdenCompra = null;
	private String tipoPago = null;
	private String correoElectronico = null;
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getBancoDestino() {
		return bancoDestino;
	}
	public void setBancoDestino(String bancoDestino) {
		this.bancoDestino = bancoDestino;
	}
	public Integer getRutAfiliado() {
		return rutAfiliado;
	}
	public void setRutAfiliado(Integer rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}
	public String getDvRutAfiliado() {
		return dvRutAfiliado;
	}
	public void setDvRutAfiliado(String dvRutAfiliado) {
		this.dvRutAfiliado = dvRutAfiliado;
	}
	public String getNombreAfiliado() {
		return nombreAfiliado;
	}
	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}
	public Integer getMonto() {
		return monto;
	}
	public void setMonto(Integer monto) {
		this.monto = monto;
	}
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public String getNumeroOrdenCompra() {
		return numeroOrdenCompra;
	}
	public void setNumeroOrdenCompra(String numeroOrdenCompra) {
		this.numeroOrdenCompra = numeroOrdenCompra;
	}
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	@Override
	public String toString() {
		return "DetalleCreditoVO [numeroCuenta=" + numeroCuenta + ", bancoDestino=" + bancoDestino + ", rutAfiliado="
				+ rutAfiliado + ", dvRutAfiliado=" + dvRutAfiliado + ", nombreAfiliado=" + nombreAfiliado + ", monto="
				+ monto + ", numeroFactura=" + numeroFactura + ", numeroOrdenCompra=" + numeroOrdenCompra
				+ ", tipoPago=" + tipoPago + ", correoElectronico=" + correoElectronico + "]";
	}

	public DetalleCreditoVO(HashMap<String, Object> data) {
		/*
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("banco_destino", 2) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("rut_afiliado", 3) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("dv_afiliado", 4) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("nombre_afiliado", 5) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("monto_transferencia", 6) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("nro_factura", 7) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("nro_orden_compra", 8) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("tipo_pago", 9) );
		formato.getColumnas().add( ColumnaFormatoVO.createColumnCSVFile("correo_electronico", 11) );
		
	*/
	
		if(data!=null) {
			String key = "banco_destino";
			if(data.get(key)!=null) {
				bancoDestino = data.get(key).toString();
			}
			key = "num_cuenta_cargo";
			if(data.get(key)!=null) {
				numeroCuenta = data.get(key).toString();
			}
			key = "rut_afiliado";
			if(data.get(key)!=null) {
				rutAfiliado = Integer.parseInt( data.get(key).toString().trim() ) ;
			}
			key = "dv_afiliado";
			if(data.get(key)!=null) {
				dvRutAfiliado = data.get(key).toString().trim();
			}
			key = "nombre_afiliado";
			if(data.get(key)!=null) {
				nombreAfiliado = data.get(key).toString().trim();
			}
			key = "monto_transferencia";
			if(data.get(key)!=null) {
				monto = Integer.parseInt( data.get(key).toString().replaceAll(" ", "") );
			}
			key = "nro_factura";
			if(data.get(key)!=null) {
				numeroFactura = data.get(key).toString().trim();
			}
			key = "nro_orden_compra";
			if(data.get(key)!=null) {
				numeroOrdenCompra = data.get(key).toString().trim();
			}
			key = "tipo_pago";
			if(data.get(key)!=null) {
				tipoPago = data.get(key).toString().trim();
			}
			key = "correo_electronico";
			if(data.get(key)!=null) {
				correoElectronico = data.get(key).toString().trim();
			}
			
		}
	}
	
	
}
