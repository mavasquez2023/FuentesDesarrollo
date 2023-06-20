package cl.laaraucana.rendicionpagonomina.vo;

import java.util.HashMap;

public class Detalle24HVO {
	

	private Integer rutAfiliado = null;
	private String dvRutAfiliado = null;
	private String apellidoPaterno = null;
	private String apellidoMaterno = null;
	private String nombre = null;
	private String codigoBanco = null;
	private String numeroCuenta = null;
	private String codigoFormaPago = null;
	private Integer monto = null;
	private String referencia1 = null;
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
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigoBanco() {
		return codigoBanco;
	}
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getCodigoFormaPago() {
		return codigoFormaPago;
	}
	public void setCodigoFormaPago(String codigoFormaPago) {
		this.codigoFormaPago = codigoFormaPago;
	}
	public Integer getMonto() {
		return monto;
	}
	public void setMonto(Integer monto) {
		this.monto = monto;
	}
	public String getReferencia1() {
		return referencia1;
	}
	public void setReferencia1(String referencia1) {
		this.referencia1 = referencia1;
	}
	

	@Override
	public String toString() {
		return "Detalle24HVO [rutAfiliado=" + rutAfiliado + ", dvRutAfiliado=" + dvRutAfiliado + ", apellidoPaterno="
				+ apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", nombre=" + nombre + ", codigoBanco="
				+ codigoBanco + ", numeroCuenta=" + numeroCuenta + ", codigoFormaPago=" + codigoFormaPago + ", monto="
				+ monto + ", referencia1=" + referencia1 + "]";
	}
	public Detalle24HVO(HashMap<String, Object> data) {
		if(data!=null) {
			String key = "rut_afiliado";
			if(data.get(key)!=null) {
				rutAfiliado = Integer.parseInt(data.get(key).toString());
			}
			key = "dv_rut_afiliado";
			if(data.get(key)!=null) {
				dvRutAfiliado = data.get(key).toString().trim();
			}
			key = "apellido_paterno";
			if(data.get(key)!=null) {
				apellidoPaterno = data.get(key).toString().trim();
			}
			key = "apellido_materno";
			if(data.get(key)!=null) {
				apellidoMaterno = data.get(key).toString().trim();
			}
			key = "nombre";
			if(data.get(key)!=null) {
				nombre = data.get(key).toString().trim();
			}
			key = "codigo_banco";
			if(data.get(key)!=null) {
				codigoBanco = data.get(key).toString().trim();
			}
			key = "numero_cuenta";
			if(data.get(key)!=null) {
				numeroCuenta = data.get(key).toString().trim();
			}
			key = "codigo_forma_pago";
			if(data.get(key)!=null) {
				codigoFormaPago = data.get(key).toString().trim();
			}
			key = "referencia_1";
			if(data.get(key)!=null) {
				referencia1 = data.get(key).toString().trim();
			}
			key = "monto";
			if(data.get(key)!=null) {
				monto = Integer.parseInt( data.get(key).toString().replaceAll(" ", "") );
			}
			
		}
	}
	
	
}
