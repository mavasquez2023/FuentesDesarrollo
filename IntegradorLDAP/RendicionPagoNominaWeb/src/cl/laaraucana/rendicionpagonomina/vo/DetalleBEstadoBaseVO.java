package cl.laaraucana.rendicionpagonomina.vo;

import java.util.HashMap;

public class DetalleBEstadoBaseVO {
	/*
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("relleno", 			0, 1) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("rut_afiliado", 		1, 10) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("dv_afiliado", 		10, 11) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("nombre_afiliado", 	11, 41) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("apellido_paterno", 	41, 56) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("apellido_materno", 	56, 71) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("forma_pago", 			71, 73) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("banco_afiliado", 		73, 76) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("numero_cuenta", 		76, 93) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("monto_pago", 			93, 106) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("email_afiliado", 		106, 146) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("correlativo_detalle", 146, 161) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("sucursal_BES", 		161, 164) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("sector_financiero", 	164, 166) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("blancos_456", 		166, 622) );
	formato.getColumnas().add( ColumnaFormatoVO.createColumnForPlainFile("codigo_seguridad", 	622, 626) );
	*/	
	
	private String relleno = null;
	private Integer rutAfiliado = null;
	private String dvAfiliado = null;
	private String nombreAfiliado = null;
	private String apellidoPaterno = null;
	private String apellidoMaterno = null;
	private String formaPago = null;
	private String bancoAfiliado = null;
	private String numeroCuenta = null;
	private Integer montoPago = null;
	private String emailAfiliado = null;
	private String correlativoDetalle = null;
	private String sucursalBES = null;
	private String sectorFinanciero = null;
	private String codigoSeguridad = null;
	
	public DetalleBEstadoBaseVO(HashMap<String, Object> data) {
		if(data!=null) {
			String key = "relleno";
			if(data.get(key)!=null) {
				relleno = data.get(key).toString().trim();
			}
			key = "rut_afiliado";
			if(data.get(key)!=null) {
				rutAfiliado = Integer.parseInt(data.get(key).toString());
			}
			key = "dv_afiliado";
			if(data.get(key)!=null) {
				dvAfiliado = data.get(key).toString().trim();
			}
			key = "nombre_afiliado";
			if(data.get(key)!=null) {
				nombreAfiliado = data.get(key).toString().trim();
			}
			key = "apellido_paterno";
			if(data.get(key)!=null) {
				apellidoPaterno = data.get(key).toString().trim();
			}
			key = "apellido_materno";
			if(data.get(key)!=null) {
				apellidoMaterno = data.get(key).toString().trim();
			}
			key = "forma_pago";
			if(data.get(key)!=null) {
				formaPago = data.get(key).toString().trim();
			}
			key = "banco_afiliado";
			if(data.get(key)!=null) {
				bancoAfiliado = data.get(key).toString().trim();
			}
			key = "numero_cuenta";
			if(data.get(key)!=null) {
				numeroCuenta = data.get(key).toString().trim();
			}
			key = "monto_pago";
			if(data.get(key)!=null) {
				montoPago = Integer.parseInt( data.get(key).toString().trim() ) ;
			}
			key = "email_afiliado";
			if(data.get(key)!=null) {
				emailAfiliado = data.get(key).toString().trim();
			}
			key = "correlativo_detalle";
			if(data.get(key)!=null) {
				correlativoDetalle = data.get(key).toString();
			}
			key = "sucursal_BES";
			if(data.get(key)!=null) {
				sucursalBES = data.get(key).toString( );
			}
			key = "sector_financiero";
			if(data.get(key)!=null) {
				sectorFinanciero = data.get(key).toString( );
			}
			key = "codigo_seguridad";
			if(data.get(key)!=null) {
				codigoSeguridad = data.get(key).toString( );
			}
			
		}
	}

	public String getRelleno() {
		return relleno;
	}

	public void setRelleno(String relleno) {
		this.relleno = relleno;
	}

	public Integer getRutAfiliado() {
		return rutAfiliado;
	}

	public void setRutAfiliado(Integer rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}

	public String getDvAfiliado() {
		return dvAfiliado;
	}

	public void setDvAfiliado(String dvAfiliado) {
		this.dvAfiliado = dvAfiliado;
	}

	public String getNombreAfiliado() {
		return nombreAfiliado;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
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

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getBancoAfiliado() {
		return bancoAfiliado;
	}

	public void setBancoAfiliado(String bancoAfiliado) {
		this.bancoAfiliado = bancoAfiliado;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Integer getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(Integer montoPago) {
		this.montoPago = montoPago;
	}

	public String getEmailAfiliado() {
		return emailAfiliado;
	}

	public void setEmailAfiliado(String emailAfiliado) {
		this.emailAfiliado = emailAfiliado;
	}

	public String getCorrelativoDetalle() {
		return correlativoDetalle;
	}

	public void setCorrelativoDetalle(String correlativoDetalle) {
		this.correlativoDetalle = correlativoDetalle;
	}

	public String getSucursalBES() {
		return sucursalBES;
	}

	public void setSucursalBES(String sucursalBES) {
		this.sucursalBES = sucursalBES;
	}

	public String getSectorFinanciero() {
		return sectorFinanciero;
	}

	public void setSectorFinanciero(String sectorFinanciero) {
		this.sectorFinanciero = sectorFinanciero;
	}

	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	@Override
	public String toString() {
		return "DetalleBEstadoBaseVO [relleno=" + relleno + ", rutAfiliado=" + rutAfiliado + ", dvAfiliado="
				+ dvAfiliado + ", nombreAfiliado=" + nombreAfiliado + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", formaPago=" + formaPago + ", bancoAfiliado="
				+ bancoAfiliado + ", numeroCuenta=" + numeroCuenta + ", montoPago=" + montoPago + ", emailAfiliado="
				+ emailAfiliado + ", correlativoDetalle=" + correlativoDetalle + ", sucursalBES=" + sucursalBES
				+ ", sectorFinanciero=" + sectorFinanciero + ", codigoSeguridad=" + codigoSeguridad + "]";
	}
	
	
	
}
