/**
 * 
 */
package cl.laaraucana.licenciascompin.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.jfactory.utils.GeneratorXLS;
import cl.laaraucana.licenciascompin.dao.GenericDao;
import cl.laaraucana.licenciascompin.entities.RegistroDocPendientes;
import cl.laaraucana.licenciascompin.entities.RegistroLicencias;
import cl.laaraucana.licenciascompin.util.Configuraciones;
import cl.laaraucana.licenciascompin.util.UtilsFecha;

/**
 * @author J-Factory
 *
 */
@Service
public class ArchivoCargaMasicaImpl implements ArchivoCargaMasiva {

	@Autowired
	private GenericDao<RegistroDocPendientes> daopend;
	@Autowired
	private GenericDao<RegistroLicencias> daonew;
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.licenciascompin.services.ArchivoCargaMasiva#leerRegistrosPendientes()
	 */
	@Override
	public List<RegistroDocPendientes> leerRegistrosPendientes()
			throws Exception {
		List<RegistroDocPendientes> pendientes= daopend.getAllByEstado(RegistroDocPendientes.class);
		String servidor= Configuraciones.getConfig("correo.carpeta.dp");
		for (Iterator iterator = pendientes.iterator(); iterator.hasNext();) {
			RegistroDocPendientes regp = (RegistroDocPendientes) iterator
					.next();
			String rut= regp.getRut();
			String rutCompleto=rut.substring(0, rut.length()-1) + "-" + rut.substring(rut.length()-1);
			regp.setRutCliente(rutCompleto);
			String notas="Rut afiliado : " + rutCompleto + " / Licencia : " + regp.getFolioLicencia() + " / Celular : " + regp.getTelefono() + " / Email : " + regp.getEmail() + 
					" / Ingresada por : " + regp.getTipoAfiliado() + " / Ruta docs : " + servidor + " / Nombre docs : " + regp.getNombreArchivoEnviado();
			regp.setNotas(notas);
		}
		return pendientes;
	}

	/* (non-Javadoc)
	 * @see cl.laaraucana.licenciascompin.services.ArchivoCargaMasiva#leerRegistrosNuevos()
	 */
	@Override
	public List<RegistroLicencias> leerRegistrosNuevos() throws Exception {
		List<RegistroLicencias> nuevos= daonew.getAllByEstado(RegistroLicencias.class);
		String servidor= Configuraciones.getConfig("correo.carpeta");
		for (Iterator iterator = nuevos.iterator(); iterator.hasNext();) {
			RegistroLicencias regp = (RegistroLicencias) iterator
					.next();
			String rut= regp.getRut();
			String rutCompleto=rut.substring(0, rut.length()-1) + "-" + rut.substring(rut.length()-1);
			regp.setRutCliente(rutCompleto);
			String notas="Rut afiliado : " + rutCompleto + " / Licencia : " + regp.getFolioLicencia() + " / Celular : " + regp.getTelefono() + " / Email : " + regp.getEmail() + 
					" / Ingresada por : " + regp.getTipoAfiliado() + " / Ruta docs : " + servidor + " / Nombre docs : " + "" ;
			regp.setNotas(notas);
		}
		return nuevos;
	}

	/* (non-Javadoc)
	 * @see cl.laaraucana.licenciascompin.services.ArchivoCargaMasiva#generarArchivoPendientes(java.util.List)
	 */
	@Override
	public String generarArchivoPendientes(
			List<RegistroDocPendientes> listaPendientes) {
		String namefile= Configuraciones.getConfig("mai.archivo.pendientes");
		String pathname= Configuraciones.getConfig("conpin.carpeta.dp");
		String zic= Configuraciones.getConfig("mai.archivo.zic");
		String categoria= Configuraciones.getConfig("mai.archivo.categoria");
		String estado_registro= Configuraciones.getConfig("mai.archivo.estado");
		String rut_iniciador= Configuraciones.getConfig("mai.archivo.rut.iniciador");
		
		try {
			String fecha= UtilsFecha.getFechaHoyAs400();
			
			for (int i = 1; i < 10; i++) {
				String archivo_a_crear= namefile.replaceAll("#", String.valueOf(i));
				String rutafull=pathname + fecha + "\\" + archivo_a_crear + ".csv";
				File file= new File(rutafull);
				if(!file.exists()){
					pathname= rutafull;
					break;
				}
			}
			GeneratorXLS xls= new GeneratorXLS(pathname);

			//Configurando columnas a desplegar y titulos de estas.
			String[] columnas={"rutCliente", "rutEmpresa", zic, categoria, estado_registro, " ", "notas", "0", "telefono", "email", " ", " ", " ", rut_iniciador, " ", " ", "tipoLicencia", "folioLicencia"};
			String[] titulos={"RUT_Cliente", "RUT_Empresa", "ZIC", "Categoría", "Estado", "Fecha", "Nota Ingresada", "Teléfono Fijo", "Celular", "Email", "Comuna", "Calle", "Número", "Rut Iniciador", "Rut Especialista", "Sucursal", "Tipo_Licencia", "Licencia Médica"};

			xls.generarCSVfromCollection(listaPendientes, columnas, titulos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return pathname;
	}

	/* (non-Javadoc)
	 * @see cl.laaraucana.licenciascompin.services.ArchivoCargaMasiva#generarArchivoNuevos(java.util.List)
	 */
	@Override
	public String generarArchivoNuevos(List<RegistroLicencias> listaNuevos) {
		String namefile= Configuraciones.getConfig("mai.archivo.nuevos");
		String pathname= Configuraciones.getConfig("conpin.carpeta");
		String zic= Configuraciones.getConfig("mai.archivo.zic");
		String categoria= Configuraciones.getConfig("mai.archivo.categoria");
		String estado_registro= Configuraciones.getConfig("mai.archivo.estado");
		String rut_iniciador= Configuraciones.getConfig("mai.archivo.rut.iniciador");
		
		try {
			String fecha= UtilsFecha.getFechaHoyAs400();
			
			for (int i = 1; i < 10; i++) {
				String archivo_a_crear= namefile.replaceAll("#", String.valueOf(i));
				String rutafull=pathname + fecha + "\\" + archivo_a_crear + ".csv";
				File file= new File(rutafull);
				if(!file.exists()){
					pathname= rutafull;
					break;
				}
			}
			GeneratorXLS xls= new GeneratorXLS(pathname);

			//Configurando columnas a desplegar y titulos de estas.
			String[] columnas={"rutCliente", " ", zic, categoria, estado_registro, " ", "notas", "0", "telefono", "email", " ", " ", " ", rut_iniciador, " ", " ", "tipoLicencia", "folioLicencia"};
			String[] titulos={"RUT_Cliente", "RUT_Empresa", "ZIC", "Categoría", "Estado", "Fecha", "Nota Ingresada", "Teléfono Fijo", "Celular", "Email", "Comuna", "Calle", "Número", "Rut Iniciador", "Rut Especialista", "Sucursal", "Tipo_Licencia", "Licencia Médica"};

			xls.generarCSVfromCollection(listaNuevos, columnas, titulos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return pathname;
	}

}
