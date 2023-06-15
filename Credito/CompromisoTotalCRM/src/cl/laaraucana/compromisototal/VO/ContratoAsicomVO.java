package cl.laaraucana.compromisototal.VO;

public class ContratoAsicomVO {

	private String folio_credito;
	private String tipo_credito;
	private String tipo_producto;
	private String tasa_interes;
	private String monto_adeudado;
	private String monto_impuestos;
	private String monto_cuota;
	private String estado_credito;
	private String indicador_reprogramacion;
	private String rol_asociado_relacion;
	private String rol_pagador;
	private String cantidad_cuotas;
	private String oficina_otorgamiento;
	private String oficina_originadora;

	public ContratoAsicomVO(String folio_credito, String tipo_credito,
			String tipo_producto, String tasa_interes, String monto_adeudado,
			String monto_impuestos, String monto_cuota, String estado_credito,
			String indicador_reprogramacion, String rol_asociado_relacion,
			String rol_pagador, String cantidad_cuotas,
			String oficina_otorgamiento, String oficina_originadora) {
		super();
		this.folio_credito = folio_credito;
		this.tipo_credito = tipo_credito;
		this.tipo_producto = tipo_producto;
		this.tasa_interes = tasa_interes;
		this.monto_adeudado = monto_adeudado;
		this.monto_impuestos = monto_impuestos;
		this.monto_cuota = monto_cuota;
		this.estado_credito = estado_credito;
		this.indicador_reprogramacion = indicador_reprogramacion;
		this.rol_asociado_relacion = rol_asociado_relacion;
		this.rol_pagador = rol_pagador;
		this.cantidad_cuotas = cantidad_cuotas;
		this.oficina_otorgamiento = oficina_otorgamiento;
		this.oficina_originadora = oficina_originadora;
	}

	public String getFolio_credito() {
		return folio_credito;
	}

	public void setFolio_credito(String folio_credito) {
		this.folio_credito = folio_credito;
	}

	public String getTipo_credito() {
		return tipo_credito;
	}

	public void setTipo_credito(String tipo_credito) {
		this.tipo_credito = tipo_credito;
	}

	public String getTipo_producto() {
		return tipo_producto;
	}

	public void setTipo_producto(String tipo_producto) {
		this.tipo_producto = tipo_producto;
	}

	public String getTasa_interes() {
		return tasa_interes;
	}

	public void setTasa_interes(String tasa_interes) {
		this.tasa_interes = tasa_interes;
	}

	public String getMonto_adeudado() {
		return monto_adeudado;
	}

	public void setMonto_adeudado(String monto_adeudado) {
		this.monto_adeudado = monto_adeudado;
	}

	public String getMonto_impuestos() {
		return monto_impuestos;
	}

	public void setMonto_impuestos(String monto_impuestos) {
		this.monto_impuestos = monto_impuestos;
	}

	public String getMonto_cuota() {
		return monto_cuota;
	}

	public void setMonto_cuota(String monto_cuota) {
		this.monto_cuota = monto_cuota;
	}

	public String getEstado_credito() {
		return estado_credito;
	}

	public void setEstado_credito(String estado_credito) {
		this.estado_credito = estado_credito;
	}

	public String getIndicador_reprogramacion() {
		return indicador_reprogramacion;
	}

	public void setIndicador_reprogramacion(String indicador_reprogramacion) {
		this.indicador_reprogramacion = indicador_reprogramacion;
	}

	public String getRol_asociado_relacion() {
		return rol_asociado_relacion;
	}

	public void setRol_asociado_relacion(String rol_asociado_relacion) {
		this.rol_asociado_relacion = rol_asociado_relacion;
	}

	public String getRol_pagador() {
		return rol_pagador;
	}

	public void setRol_pagador(String rol_pagador) {
		this.rol_pagador = rol_pagador;
	}

	public String getCantidad_cuotas() {
		return cantidad_cuotas;
	}

	public void setCantidad_cuotas(String cantidad_cuotas) {
		this.cantidad_cuotas = cantidad_cuotas;
	}

	public String getOficina_otorgamiento() {
		return oficina_otorgamiento;
	}

	public void setOficina_otorgamiento(String oficina_otorgamiento) {
		this.oficina_otorgamiento = oficina_otorgamiento;
	}

	public String getOficina_originadora() {
		return oficina_originadora;
	}

	public void setOficina_originadora(String oficina_originadora) {
		this.oficina_originadora = oficina_originadora;
	}

}
