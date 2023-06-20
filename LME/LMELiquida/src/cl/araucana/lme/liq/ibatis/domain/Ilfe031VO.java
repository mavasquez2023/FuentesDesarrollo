/*
 * Created on 14-10-2011
 *
 */
package cl.araucana.lme.liq.ibatis.domain;

import java.io.Serializable;

/**
 * @author j-factory
 *
 */
	public class Ilfe031VO implements Serializable{
		private int ideOpe;
		private String codOpe;
		private int rutAfiliado;
		private int nroLicencia;
		private String dvNroLicencia;
		private String enviada;
		private int fechaProceso;
		private String respuestaWS;
		private String marcaProcesado;
		private String nombreEmpleador;
		private int rutEmpleador;
		private String dvRutEmpleador;
		private int fechaRecepcionEmpresa;
		private String direccionEmpleador;
		private String numeroDireccionEmpleador="";
		private String deptoDireccionEmpleador="";
		private String telefonoEmpleador="0";
		private String ciudadEmpleador;
		private String paisEmpleador;
		private String comunaEmpleador;
		private int codigoComunaCompin;
		private int codigoActividadLaboral;
		private int codigoOcupacion;
		private String otraOcupacion;
		private int fechaRecepcionCaja;
		private String codigoTipoRegimenPrevisional;
		private int codigoEntidadPrevisonal;
		private String codigoLetraCaja;
		private String nombreEntidadPrevisional;
		private int codigoCalidadTrabajador;
		private int trabajadorAfiliadoAFC;
		private int contratoDuracionIndefinido;
		private int fechaAfiliacion;
		private int fechaContrato;
		private String codigoEntidadPagadora;
		private String nombreEntidadPagadora;
		private int porcentajeDesahucio;
		private int rentaImponible;
		private String licenciasAnteriores;
		private int codigoTramitacionCCAF;//C5COTRCCAF
		private String tieneMas100;//C5EMP100
		private String codCcaf; //CODCCAF
		private String pwdCcaf; //PWDCCAF
		private String urlOpe; //URLOPE
		private String usuarioModifica;
		private String nombreConsumo;
		private int horaEnvio;
		private String glosaRespuesta;
		private int fechaRespuesta; //FECRESP
		private int horaRespuesta; //HORRESP
		/**
		 * @return the ideOpe
		 */
		public int getIdeOpe() {
			return ideOpe;
		}
		/**
		 * @param ideOpe the ideOpe to set
		 */
		public void setIdeOpe(int ideOpe) {
			this.ideOpe = ideOpe;
		}
		/**
		 * @return the codOpe
		 */
		public String getCodOpe() {
			return codOpe;
		}
		/**
		 * @param codOpe the codOpe to set
		 */
		public void setCodOpe(String codOpe) {
			this.codOpe = codOpe;
		}
		/**
		 * @return the rutAfiliado
		 */
		public int getRutAfiliado() {
			return rutAfiliado;
		}
		/**
		 * @param rutAfiliado the rutAfiliado to set
		 */
		public void setRutAfiliado(int rutAfiliado) {
			this.rutAfiliado = rutAfiliado;
		}
		/**
		 * @return the nroLicencia
		 */
		public int getNroLicencia() {
			return nroLicencia;
		}
		/**
		 * @param nroLicencia the nroLicencia to set
		 */
		public void setNroLicencia(int nroLicencia) {
			this.nroLicencia = nroLicencia;
		}
		/**
		 * @return the dvNroLicencia
		 */
		public String getDvNroLicencia() {
			return dvNroLicencia;
		}
		/**
		 * @param dvNroLicencia the dvNroLicencia to set
		 */
		public void setDvNroLicencia(String dvNroLicencia) {
			this.dvNroLicencia = dvNroLicencia;
		}
		/**
		 * @return the enviada
		 */
		public String getEnviada() {
			return enviada;
		}
		/**
		 * @param enviada the enviada to set
		 */
		public void setEnviada(String enviada) {
			this.enviada = enviada;
		}
		/**
		 * @return the fechaProceso
		 */
		public int getFechaProceso() {
			return fechaProceso;
		}
		/**
		 * @param fechaProceso the fechaProceso to set
		 */
		public void setFechaProceso(int fechaProceso) {
			this.fechaProceso = fechaProceso;
		}
		/**
		 * @return the respuestaWS
		 */
		public String getRespuestaWS() {
			return respuestaWS;
		}
		/**
		 * @param respuestaWS the respuestaWS to set
		 */
		public void setRespuestaWS(String respuestaWS) {
			this.respuestaWS = respuestaWS;
		}
		/**
		 * @return the marcaProcesado
		 */
		public String getMarcaProcesado() {
			return marcaProcesado;
		}
		/**
		 * @param marcaProcesado the marcaProcesado to set
		 */
		public void setMarcaProcesado(String marcaProcesado) {
			this.marcaProcesado = marcaProcesado;
		}
		/**
		 * @return the nombreEmpleador
		 */
		public String getNombreEmpleador() {
			return nombreEmpleador;
		}
		/**
		 * @param nombreEmpleador the nombreEmpleador to set
		 */
		public void setNombreEmpleador(String nombreEmpleador) {
			this.nombreEmpleador = nombreEmpleador;
		}
		/**
		 * @return the rutEmpleador
		 */
		public int getRutEmpleador() {
			return rutEmpleador;
		}
		/**
		 * @param rutEmpleador the rutEmpleador to set
		 */
		public void setRutEmpleador(int rutEmpleador) {
			this.rutEmpleador = rutEmpleador;
		}
		/**
		 * @return the dvRutEmpleador
		 */
		public String getDvRutEmpleador() {
			return dvRutEmpleador;
		}
		/**
		 * @param dvRutEmpleador the dvRutEmpleador to set
		 */
		public void setDvRutEmpleador(String dvRutEmpleador) {
			this.dvRutEmpleador = dvRutEmpleador;
		}
		/**
		 * @return the fechaRecepcionEmpresa
		 */
		public int getFechaRecepcionEmpresa() {
			return fechaRecepcionEmpresa;
		}
		/**
		 * @param fechaRecepcionEmpresa the fechaRecepcionEmpresa to set
		 */
		public void setFechaRecepcionEmpresa(int fechaRecepcionEmpresa) {
			this.fechaRecepcionEmpresa = fechaRecepcionEmpresa;
		}
		/**
		 * @return the direccionEmpleador
		 */
		public String getDireccionEmpleador() {
			return direccionEmpleador;
		}
		/**
		 * @param direccionEmpleador the direccionEmpleador to set
		 */
		public void setDireccionEmpleador(String direccionEmpleador) {
			this.direccionEmpleador = direccionEmpleador;
		}

		/**
		 * @return the numeroDireccionEmpleador
		 */
		public String getNumeroDireccionEmpleador() {
			return numeroDireccionEmpleador;
		}
		/**
		 * @param numeroDireccionEmpleador the numeroDireccionEmpleador to set
		 */
		public void setNumeroDireccionEmpleador(String numeroDireccionEmpleador) {
			this.numeroDireccionEmpleador = numeroDireccionEmpleador;
		}
		/**
		 * @return the deptoDireccionEmpleador
		 */
		public String getDeptoDireccionEmpleador() {
			return deptoDireccionEmpleador;
		}
		/**
		 * @param deptoDireccionEmpleador the deptoDireccionEmpleador to set
		 */
		public void setDeptoDireccionEmpleador(String deptoDireccionEmpleador) {
			this.deptoDireccionEmpleador = deptoDireccionEmpleador;
		}

		/**
		 * @return the telefonoEmpleador
		 */
		public String getTelefonoEmpleador() {
			return telefonoEmpleador;
		}
		/**
		 * @param telefonoEmpleador the telefonoEmpleador to set
		 */
		public void setTelefonoEmpleador(String telefonoEmpleador) {
			this.telefonoEmpleador = telefonoEmpleador;
		}
		/**
		 * @return the ciudadEmpleador
		 */
		public String getCiudadEmpleador() {
			return ciudadEmpleador;
		}
		/**
		 * @param ciudadEmpleador the ciudadEmpleador to set
		 */
		public void setCiudadEmpleador(String ciudadEmpleador) {
			this.ciudadEmpleador = ciudadEmpleador;
		}
		/**
		 * @return the paisEmpleador
		 */
		public String getPaisEmpleador() {
			return paisEmpleador;
		}
		/**
		 * @param paisEmpleador the paisEmpleador to set
		 */
		public void setPaisEmpleador(String paisEmpleador) {
			this.paisEmpleador = paisEmpleador;
		}
		/**
		 * @return the comunaEmpleador
		 */
		public String getComunaEmpleador() {
			return comunaEmpleador;
		}
		/**
		 * @param comunaEmpleador the comunaEmpleador to set
		 */
		public void setComunaEmpleador(String comunaEmpleador) {
			this.comunaEmpleador = comunaEmpleador;
		}
		/**
		 * @return the codComunaCompin
		 */
		public int getCodigoComunaCompin() {
			return codigoComunaCompin;
		}
		/**
		 * @param codComunaCompin the codComunaCompin to set
		 */
		public void setCodigoComunaCompin(int codigoComunaCompin) {
			this.codigoComunaCompin = codigoComunaCompin;
		}
		/**
		 * @return the codigoActivLaboral
		 */
		public int getCodigoActividadLaboral() {
			return codigoActividadLaboral;
		}
		/**
		 * @param codigoActivLaboral the codigoActivLaboral to set
		 */
		public void setCodigoActividadLaboral(int codigoActividadLaboral) {
			this.codigoActividadLaboral = codigoActividadLaboral;
		}
		/**
		 * @return the codigoOcupación
		 */
		public int getCodigoOcupacion() {
			return codigoOcupacion;
		}
		/**
		 * @param codigoOcupación the codigoOcupación to set
		 */
		public void setCodigoOcupacion(int codigoOcupacion) {
			this.codigoOcupacion = codigoOcupacion;
		}
		/**
		 * @return the otraOcupacion
		 */
		public String getOtraOcupacion() {
			return otraOcupacion;
		}
		/**
		 * @param otraOcupacion the otraOcupacion to set
		 */
		public void setOtraOcupacion(String otraOcupacion) {
			this.otraOcupacion = otraOcupacion;
		}
		/**
		 * @return the fechaRecepcionCaja
		 */
		public int getFechaRecepcionCaja() {
			return fechaRecepcionCaja;
		}
		/**
		 * @param fechaRecepcionCaja the fechaRecepcionCaja to set
		 */
		public void setFechaRecepcionCaja(int fechaRecepcionCaja) {
			this.fechaRecepcionCaja = fechaRecepcionCaja;
		}
		/**
		 * @return the codigoTipoRegimenPrevisional
		 */
		public String getCodigoTipoRegimenPrevisional() {
			return codigoTipoRegimenPrevisional;
		}
		/**
		 * @param codigoTipoRegimenPrevisional the codigoTipoRegimenPrevisional to set
		 */
		public void setCodigoTipoRegimenPrevisional(String codigoTipoRegimenPrevisional) {
			this.codigoTipoRegimenPrevisional = codigoTipoRegimenPrevisional;
		}
		/**
		 * @return the codigoEntidadPrevisonal
		 */
		public int getCodigoEntidadPrevisonal() {
			return codigoEntidadPrevisonal;
		}
		/**
		 * @param codigoEntidadPrevisonal the codigoEntidadPrevisonal to set
		 */
		public void setCodigoEntidadPrevisonal(int codigoEntidadPrevisonal) {
			this.codigoEntidadPrevisonal = codigoEntidadPrevisonal;
		}
		/**
		 * @return the codigoLetraCaja
		 */
		public String getCodigoLetraCaja() {
			return codigoLetraCaja;
		}
		/**
		 * @param codigoLetraCaja the codigoLetraCaja to set
		 */
		public void setCodigoLetraCaja(String codigoLetraCaja) {
			this.codigoLetraCaja = codigoLetraCaja;
		}
		/**
		 * @return the nombreEntidadPrevisional
		 */
		public String getNombreEntidadPrevisional() {
			return nombreEntidadPrevisional;
		}
		/**
		 * @param nombreEntidadPrevisional the nombreEntidadPrevisional to set
		 */
		public void setNombreEntidadPrevisional(String nombreEntidadPrevisional) {
			this.nombreEntidadPrevisional = nombreEntidadPrevisional;
		}
		/**
		 * @return the codigoCalidadTrabajador
		 */
		public int getCodigoCalidadTrabajador() {
			return codigoCalidadTrabajador;
		}
		/**
		 * @param codigoCalidadTrabajador the codigoCalidadTrabajador to set
		 */
		public void setCodigoCalidadTrabajador(int codigoCalidadTrabajador) {
			this.codigoCalidadTrabajador = codigoCalidadTrabajador;
		}
		/**
		 * @return the trabajadorAfiliadoAFC
		 */
		public int getTrabajadorAfiliadoAFC() {
			return trabajadorAfiliadoAFC;
		}
		/**
		 * @param trabajadorAfiliadoAFC the trabajadorAfiliadoAFC to set
		 */
		public void setTrabajadorAfiliadoAFC(int trabajadorAfiliadoAFC) {
			this.trabajadorAfiliadoAFC = trabajadorAfiliadoAFC;
		}
		/**
		 * @return the contratoDuracionIndefinido
		 */
		public int getContratoDuracionIndefinido() {
			return contratoDuracionIndefinido;
		}
		/**
		 * @param contratoDuracionIndefinido the contratoDuracionIndefinido to set
		 */
		public void setContratoDuracionIndefinido(int contratoDuracionIndefinido) {
			this.contratoDuracionIndefinido = contratoDuracionIndefinido;
		}
		/**
		 * @return the fechaAfiliacion
		 */
		public int getFechaAfiliacion() {
			return fechaAfiliacion;
		}
		/**
		 * @param fechaAfiliacion the fechaAfiliacion to set
		 */
		public void setFechaAfiliacion(int fechaAfiliacion) {
			this.fechaAfiliacion = fechaAfiliacion;
		}
		/**
		 * @return the fechaCOntrato
		 */
		public int getFechaContrato() {
			return fechaContrato;
		}
		/**
		 * @param fechaCOntrato the fechaCOntrato to set
		 */
		public void setFechaContrato(int fechaContrato) {
			this.fechaContrato = fechaContrato;
		}
		/**
		 * @return the codigoEntidadPagadora
		 */
		public String getCodigoEntidadPagadora() {
			return codigoEntidadPagadora;
		}
		/**
		 * @param codigoEntidadPagadora the codigoEntidadPagadora to set
		 */
		public void setCodigoEntidadPagadora(String codigoEntidadPagadora) {
			this.codigoEntidadPagadora = codigoEntidadPagadora;
		}
		/**
		 * @return the nombreEntidadPagadora
		 */
		public String getNombreEntidadPagadora() {
			return nombreEntidadPagadora;
		}
		/**
		 * @param nombreEntidadPagadora the nombreEntidadPagadora to set
		 */
		public void setNombreEntidadPagadora(String nombreEntidadPagadora) {
			this.nombreEntidadPagadora = nombreEntidadPagadora;
		}
		/**
		 * @return the rentaImponible
		 */
		public int getRentaImponible() {
			return rentaImponible;
		}
		/**
		 * @param rentaImponible the rentaImponible to set
		 */
		public void setRentaImponible(int rentaImponible) {
			this.rentaImponible = rentaImponible;
		}
		/**
		 * @return the licenciasAnteriores
		 */
		public String getLicenciasAnteriores() {
			return licenciasAnteriores;
		}
		/**
		 * @param licenciasAnteriores the licenciasAnteriores to set
		 */
		public void setLicenciasAnteriores(String licenciasAnteriores) {
			this.licenciasAnteriores = licenciasAnteriores;
		}
		
		/**
		 * @return el codigoTramitacionCCAF
		 */
		public int getCodigoTramitacionCCAF() {
			return codigoTramitacionCCAF;
		}
		/**
		 * @param codigoTramitacionCCAF el codigoTramitacionCCAF a establecer
		 */
		public void setCodigoTramitacionCCAF(int codigoTramitacionCCAF) {
			this.codigoTramitacionCCAF = codigoTramitacionCCAF;
		}
		/**
		 * @return el tieneMas100
		 */
		public String getTieneMas100() {
			return tieneMas100;
		}
		/**
		 * @param tieneMas100 el tieneMas100 a establecer
		 */
		public void setTieneMas100(String tieneMas100) {
			this.tieneMas100 = tieneMas100;
		}
		/**
		 * @return the codCcaf
		 */
		public String getCodCcaf() {
			return codCcaf;
		}
		/**
		 * @param codCcaf the codCcaf to set
		 */
		public void setCodCcaf(String codCcaf) {
			this.codCcaf = codCcaf;
		}
		/**
		 * @return the pwdCcaf
		 */
		public String getPwdCcaf() {
			return pwdCcaf;
		}
		/**
		 * @param pwdCcaf the pwdCcaf to set
		 */
		public void setPwdCcaf(String pwdCcaf) {
			this.pwdCcaf = pwdCcaf;
		}
		/**
		 * @return the urlOpe
		 */
		public String getUrlOpe() {
			return urlOpe;
		}
		/**
		 * @param urlOpe the urlOpe to set
		 */
		public void setUrlOpe(String urlOpe) {
			this.urlOpe = urlOpe;
		}
		/**
		 * @return the usuarioModifica
		 */
		public String getUsuarioModifica() {
			return usuarioModifica;
		}
		/**
		 * @param usuarioModifica the usuarioModifica to set
		 */
		public void setUsuarioModifica(String usuarioModifica) {
			this.usuarioModifica = usuarioModifica;
		}
		/**
		 * @return the nombreConsumo
		 */
		public String getNombreConsumo() {
			return nombreConsumo;
		}
		/**
		 * @param nombreConsumo the nombreConsumo to set
		 */
		public void setNombreConsumo(String nombreConsumo) {
			this.nombreConsumo = nombreConsumo;
		}
		/**
		 * @return the horaEnvio
		 */
		public int getHoraEnvio() {
			return horaEnvio;
		}
		/**
		 * @param horaEnvio the horaEnvio to set
		 */
		public void setHoraEnvio(int horaEnvio) {
			this.horaEnvio = horaEnvio;
		}
		/**
		 * @return the glosaRespuesta
		 */
		public String getGlosaRespuesta() {
			return glosaRespuesta;
		}
		/**
		 * @param glosaRespuesta the glosaRespuesta to set
		 */
		public void setGlosaRespuesta(String glosaRespuesta) {
			this.glosaRespuesta = glosaRespuesta;
		}
		/**
		 * @return the fechaRespuesta
		 */
		public int getFechaRespuesta() {
			return fechaRespuesta;
		}
		/**
		 * @param fechaRespuesta the fechaRespuesta to set
		 */
		public void setFechaRespuesta(int fechaRespuesta) {
			this.fechaRespuesta = fechaRespuesta;
		}
		/**
		 * @return the horaRespuesta
		 */
		public int getHoraRespuesta() {
			return horaRespuesta;
		}
		/**
		 * @param horaRespuesta the horaRespuesta to set
		 */
		public void setHoraRespuesta(int horaRespuesta) {
			this.horaRespuesta = horaRespuesta;
		}
		/**
		 * @return the porcentajeDesahucio
		 */
		public int getPorcentajeDesahucio() {
			return porcentajeDesahucio;
		}
		/**
		 * @param porcentajeDesahucio the porcentajeDesahucio to set
		 */
		public void setPorcentajeDesahucio(int porcentajeDesahucio) {
			this.porcentajeDesahucio = porcentajeDesahucio;
		}


		}
