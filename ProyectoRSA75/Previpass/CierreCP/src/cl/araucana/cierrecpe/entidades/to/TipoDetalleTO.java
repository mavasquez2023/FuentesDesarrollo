/*
 * Creado el 15-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.to;

import java.util.Collection;

import cl.araucana.core.business.TO.TransferObject;
import cl.araucana.core.util.Rut;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TipoDetalleTO implements TransferObject {
private int tipoSeccion, tipoDetalle, conceptoEgreso;
private char tipoNomina;
private Rut rutEntidad;
private Collection montos;
/**
 * @return Devuelve tipoDetalle.
 */
public int getTipoDetalle() {
	return tipoDetalle;
}
/**
 * @param tipoDetalle El tipoDetalle a establecer.
 */
public void setTipoDetalle(int tipoDetalle) {
	this.tipoDetalle = tipoDetalle;
}
/**
 * @return Devuelve tipoSeccion.
 */
public int getTipoSeccion() {
	return tipoSeccion;
}
/**
 * @param tipoSeccion El tipoSeccion a establecer.
 */
public void setTipoSeccion(int tipoSeccion) {
	this.tipoSeccion = tipoSeccion;
}
/**
 * @return Devuelve tipoNomina.
 */
public char getTipoNomina() {
	return tipoNomina;
}
/**
 * @param tipoNomina El tipoNomina a establecer.
 */
public void setTipoNomina(char tipoNomina) {
	this.tipoNomina = tipoNomina;
}

/**
 * @return Devuelve rutEntidad.
 */
public Rut getRutEntidad() {
	return rutEntidad;
}
/**
 * @param rutEntidad El rutEntidad a establecer.
 */
public void setRutEntidad(Rut rutEntidad) {
	this.rutEntidad = rutEntidad;
}
public int getConceptoEgreso() {
	return conceptoEgreso;
}
public void setConceptoEgreso(int conceptoEgreso) {
	this.conceptoEgreso = conceptoEgreso;
}
public Collection getMontos() {
	return montos;
}
public void setMontos(Collection montos) {
	this.montos = montos;
}
}
