

/*
 * @(#) EmpresaCPDAO.java    1.0 10-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.to.BancoTO;
import cl.araucana.cierrecpe.empresas.to.ConceptosCajaTO;
import cl.araucana.cierrecpe.empresas.to.DetalleComprobantesTO;
import cl.araucana.cierrecpe.empresas.to.EstadisticaPagoTO;
import cl.araucana.cierrecpe.empresas.to.InformeTO;
import cl.araucana.cierrecpe.empresas.to.ResumenCierreComprobantesTO;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;
import cl.recursos.Today;

/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 02/03/2015 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo007@gmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class InformesCierreCPDAO implements DAO_Interface {
private ConectaDB2 db2;
private static Logger logger = LogManager.getLogger();	
	
	public InformesCierreCPDAO(ConectaDB2 db2){
		this.db2= db2;
	}
	
	public Collection selectEmpresasNuevas(int periodo, int periodoant) throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select distinct R.server, R.pwcccdhol, R.empresa, R.pwccconv, R.pwcctipro, R.pwccrazso, R.pwcctotpa, R.pwcctottr, R.pwcctelem, R.pwccnomre, R.pwccdomem, R.pwcccomem, R.pwccciuem, "); 
		query.append("case when S.pwdctipem='CCAF' then S.pwdcnomem else '-' end ");
		query.append("from ( ");
		query.append("select distinct case substr(pwccnumco, 1, 2) when '81' then 'EXTRANET' "); 
		query.append("when  '84' then 'COTIWEB4' ");
		query.append("when '89' then 'PREVIPASS' end as server, ");
		query.append("pwcccdhol, pwccrutem || '-' || pwccdigem as empresa, pwccconv, pwcctipro, pwccrazso, pwcctotpa, pwcctottr, pwcctelem, pwccnomre, pwccdomem, pwcccomem, pwccciuem, pwccnumco ");
		query.append("from pwdtad.pwf5000 a left join pwdtad.pwf5100 b ");
		query.append("on a.pwccnumco= b.pwdcnumco ");
		query.append("where pwcccopro= ? ");
		query.append("and pwcccdhol !=122 ");
		query.append("and pwcctipro !='S' ");
		query.append("and pwccrutem not in (select pwccrutem from pwdtad.pwf5000 where pwcccopro= ?)) as R ");
		query.append("left join pwdtad.pwf5100 S ");
		query.append("on R.pwccnumco= S.pwdcnumco ");
		query.append("and S.pwdctipem='CCAF' ");
		query.append("order by 1, 2, 3 ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//Se ejecuta la query
		db2.setStatement(1, periodo);
		db2.setStatement(2, periodoant);
		db2.executeQuery();
		List lista= new ArrayList();
		while (db2.next()) {
			String server=db2.getString(1);
			int grupoConvenio=db2.getInt(2);
			String rutEmpresa=db2.getString(3);
			int convenio=db2.getInt(4);
			String tipoNomina=db2.getString(5);
			String razonSocial=db2.getString(6);
			long total=db2.getInt(7);
			int numtra=db2.getInt(8);
			String telefono=db2.getString(9);
			String nombreRL=db2.getString(10);
			String domicilio=db2.getString(11);
			String comuna=db2.getString(12);
			String ciudad=db2.getString(13);
			String caja=db2.getString(14);
			
			//int formaPago= db2.getInt(13);
			InformeTO informe= new InformeTO();
			informe.setServer(server);
			informe.setGrupoConvenio(grupoConvenio);
			informe.setRutEmpresa(rutEmpresa);
			informe.setConvenio(convenio);
			informe.setTipoNomina(tipoNomina);
			informe.setRazonSocial(razonSocial);
			informe.setTotal(total);
			informe.setNumtra(numtra);
			informe.setTelefono(telefono);
			informe.setNombreRL(nombreRL);
			informe.setDomicilio(domicilio);
			informe.setComuna(comuna);
			informe.setCiudad(ciudad);
			informe.setCaja(caja);
			//resumen.setFormaPago(formaPago);
			lista.add(informe);
		}
		return lista;
	}
	
	public Collection selectEmpresasNoPagaron(int periodo, int periodoant) throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select distinct R.server, R.pwcccdhol, R.empresa, R.pwccconv, R.pwcctipro, R.pwccrazso, R.pwcctotpa, R.pwcctottr, R.pwcctelem, R.pwccnomre, R.pwccdomem, R.pwcccomem, R.pwccciuem, "); 
		query.append("case when S.pwdctipem='CCAF' then S.pwdcnomem else '-' end ");
		query.append("from ( ");
		query.append("select distinct case substr(pwccnumco, 1, 2) when '81' then 'EXTRANET' "); 
		query.append("when  '84' then 'COTIWEB4' ");
		query.append("when '89' then 'PREVIPASS' end as server, ");
		query.append("pwcccdhol, pwccrutem || '-' || pwccdigem as empresa, pwccconv, pwcctipro, pwccrazso, pwcctotpa, pwcctottr, pwcctelem, pwccnomre, pwccdomem, pwcccomem, pwccciuem, pwccnumco ");
		query.append("from pwdtad.pwf5000 a left join pwdtad.pwf5100 b ");
		query.append("on a.pwccnumco= b.pwdcnumco ");
		query.append("where pwcccopro= ? ");
		query.append("and pwcccdhol !=122 ");
		query.append("and pwcctipro !='S' ");
		query.append("and pwccrutem not in (select pwccrutem from pwdtad.pwf5000 where pwcccopro= ?)) as R ");
		query.append("left join pwdtad.pwf5100 S ");
		query.append("on R.pwccnumco= S.pwdcnumco ");
		query.append("and S.pwdctipem='CCAF' ");
		query.append("order by 1, 2, 3 ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//Se ejecuta la query
		db2.setStatement(1, periodoant);
		db2.setStatement(2, periodo);
		db2.executeQuery();
		List lista= new ArrayList();
		while (db2.next()) {
			String server=db2.getString(1);
			int grupoConvenio=db2.getInt(2);
			String rutEmpresa=db2.getString(3);
			int convenio=db2.getInt(4);
			String tipoNomina=db2.getString(5);
			String razonSocial=db2.getString(6);
			long total=db2.getInt(7);
			int numtra=db2.getInt(8);
			String telefono=db2.getString(9);
			String nombreRL=db2.getString(10);
			String domicilio=db2.getString(11);
			String comuna=db2.getString(12);
			String ciudad=db2.getString(13);
			String caja=db2.getString(14);
			
			//int formaPago= db2.getInt(13);
			InformeTO informe= new InformeTO();
			informe.setServer(server);
			informe.setGrupoConvenio(grupoConvenio);
			informe.setRutEmpresa(rutEmpresa);
			informe.setConvenio(convenio);
			informe.setTipoNomina(tipoNomina);
			informe.setRazonSocial(razonSocial);
			informe.setTotal(total);
			informe.setNumtra(numtra);
			informe.setTelefono(telefono);
			informe.setNombreRL(nombreRL);
			informe.setDomicilio(domicilio);
			informe.setComuna(comuna);
			informe.setCiudad(ciudad);
			informe.setCaja(caja);
			//resumen.setFormaPago(formaPago);
			lista.add(informe);
		}
		return lista;
	}
	
	public Collection selectEmpresasActivasPrevipass(int periodo) throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select distinct R.server, R.pwcccdhol, R.empresa, R.pwccconv, R.pwcctipro, R.pwccrazso, R.pwccdomem, R.pwcccomem, substr(R.pwccregem, 1, 2) as Region, R.pwcctelem, R.pwccnomre, R.pwcctotpa, R.pwcctottr, "); 
		query.append("case when S.pwdctipem='CCAF' then S.pwdcnomem else '-' end ");
		query.append("from ( ");
		query.append("select distinct case substr(pwccnumco, 1, 2) when '81' then 'EXTRANET' "); 
		query.append("when  '84' then 'COTIWEB4' ");
		query.append("when  '86' then 'SILWEB' ");
		query.append("when '89' then 'PREVIPASS' end as server, ");
		query.append("pwcccdhol, pwccrutem || '-' || pwccdigem as empresa, pwccconv, pwcctipro, pwccrazso, pwccdomem, pwcccomem, pwccregem, pwcctelem, pwccnomre, pwcctotpa, pwcctottr, pwccnumco ");
		query.append("from pwdtad.pwf5000 a left join pwdtad.pwf5100 b ");
		query.append("on a.pwccnumco= b.pwdcnumco ");
		query.append("where pwcccopro= ? ");
		query.append("and pwcccdhol !=122 ) as R ");
		query.append("left join pwdtad.pwf5100 S ");
		query.append("on R.pwccnumco= S.pwdcnumco ");
		query.append("and S.pwdctipem='CCAF' ");
		query.append("order by 1, 2, 3, 4 ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//Se ejecuta la query
		db2.setStatement(1, periodo);
		db2.executeQuery();
		List lista= new ArrayList();
		while (db2.next()) {
			String server=db2.getString(1);
			int grupoConvenio=db2.getInt(2);
			String rutEmpresa=db2.getString(3);
			int convenio=db2.getInt(4);
			String tipoNomina=db2.getString(5);
			String razonSocial=db2.getString(6);
			String domicilio=db2.getString(7);
			String comuna=db2.getString(8);
			String region=db2.getString(9);
			String telefono=db2.getString(10);
			String nombreRL=db2.getString(11);
			long total=db2.getInt(12);
			int numtra=db2.getInt(13);
			String caja=db2.getString(14);
			
			//int formaPago= db2.getInt(13);
			InformeTO informe= new InformeTO();
			informe.setServer(server);
			informe.setGrupoConvenio(grupoConvenio);
			informe.setRutEmpresa(rutEmpresa);
			informe.setConvenio(convenio);
			informe.setTipoNomina(tipoNomina);
			informe.setRazonSocial(razonSocial);
			informe.setTelefono(telefono);
			informe.setNombreRL(nombreRL);
			informe.setDomicilio(domicilio);
			informe.setComuna(comuna);
			informe.setRegion(region);
			informe.setNumtra(numtra);
			informe.setTotal(total);
			informe.setCaja(caja);
			lista.add(informe);
		}
		return lista;
	}
	
	public Map selectComprobantesFormasDePago(int periodo, Map conceptosCaja) throws SQLException{
		StringBuffer query= new StringBuffer();
		//int year= (int)Math.floor(periodo/100);
		//int month= periodo-year*100;
		//String fechaini= year + "" + month + "01";
		//String fechafin= year + "" + month + "" + ultimoDia(year, month-1); 
		query.append("select distinct case substr(pwccnumco, 1, 2) when '81' then 'EXTRANET' "); 
		query.append("when  '84' then 'COTIWEB4' ");
		query.append("when  '86' then 'SILWEB' ");
		query.append("when '89' then 'PREVIPASS' end as server, ");
		query.append("PWCCCIERRE, PWCCCDHOL, PWCCRUTEM, PWCCRAZSO, PWCCTOTPA, PWCCFOLTES, PWCCFEPAG, PWCCFORPAG, ");
		query.append("SUM(CASE when PWTCTIPEM='AFP' OR PWTCTIPEM='APV' OR PWTCTIPEM='DEP.CONV.'  THEN PWTCMONTO ELSE 0 END) as AFP, ");
		query.append("SUM(CASE when PWTCTIPEM='ISAPRE' THEN PWTCMONTO ELSE 0 END) as ISAPRE, ");
		query.append("SUM(CASE when PWTCTIPEM='IPS' THEN PWTCMONTO ELSE 0 END) as IPS,  ");
		query.append("SUM(CASE when PWTCTIPEM='MUTUAL' THEN PWTCMONTO ELSE 0 END) as MUTUAL, ");
		query.append("SUM(CASE when PWTCTIPEM='CCAF' THEN PWTCMONTO ELSE 0 END) as CCAF ");
		query.append("from pwdtad.pwf5000 a join pwdtad.pwf5200 b ");
		query.append("on a.pwccnumco= b.pwtcnumco ");
		//query.append("where pwccfepag >= " + fechaini + " ");
		//query.append("and pwccfepag <= " + fechafin + " ");
		query.append("and pwcccopro = ? ");
		query.append("and int(pwccfoltes)>0 ");
		query.append("and pwcctipro||pwccrutem <> 'S70016160' ");
		//query.append("and pwcccdhol !=122 ");
		query.append("group by pwccnumco, PWCCCIERRE, PWCCCDHOL, PWCCRUTEM, PWCCRAZSO, PWCCTOTPA, PWCCFOLTES, PWCCFEPAG, PWCCFORPAG ");
		query.append("order by 2, 7, 6 ");

		logger.finest("Query=" + query.toString());
		//System.out.println("query=" + query);
		db2.prepareQuery(query.toString());
		//Se ejecuta la query
		
		//String anio= String.valueOf(periodo).substring(0, 4);
		//String mes= String.valueOf(periodo).substring(4);
		//fecha= fecha.substring(0, 4)+ "-" + fecha.substring(4)+ "-";
		//String fechaini=fecha + "01";
		//String fechafin=fecha + "31";

		db2.setStatement(1, periodo);
		//db2.setStatement(2, fechafin);
		db2.executeQuery();
		Map lista= new HashMap();
		while (db2.next()) {
			String server=db2.getString(1);
			int cierre=db2.getInt(2);
			int grupoConvenio=db2.getInt(3);
			String rutEmpresa=db2.getString(4);
			String razonSocial=db2.getString(5);
			long total=db2.getLong(6);
			int folio= db2.getInt(7);
			//fecha en formato aaaammdd
			int fechaPago= db2.getInt(8);
			char formaPago= db2.getString(9).charAt(0);
			long afp= db2.getLong(10);
			long isapre= db2.getLong(11);
			long ips= db2.getLong(12);
			long mutual= db2.getLong(13);
			long ccaf= db2.getLong(14);
			
			InformeTO informe= new InformeTO();
			informe.setServer(server);
			informe.setCierre(cierre);
			informe.setGrupoConvenio(grupoConvenio);
			informe.setRutEmpresa(rutEmpresa);
			informe.setRazonSocial(razonSocial);
			informe.setTotal(total);
			informe.setFolio(folio);
			
			//se obtiene fecha en formato dd-mm-aaaa
			String formatFechaPago= Today.formatFecha(String.valueOf(fechaPago), "-");
			informe.setFechaPago(formatFechaPago);
			informe.setFormaPago(formaPago);
			
			
			
			Map detalle = new HashMap();
			detalle.put("AFP", new Long(afp));
			detalle.put("ISAPRE", new Long(isapre));
			detalle.put("IPS", new Long(ips));
			detalle.put("MUTUAL", new Long(mutual));
			detalle.put("CCAF", new Long(ccaf));
			
			ConceptosCajaTO cajaTO= (ConceptosCajaTO)conceptosCaja.get(new Integer(folio));
			if(cajaTO!=null){
				informe.setCaja(cajaTO.getCaja());
				detalle.put("ASFAM", new Long(cajaTO.getAsfam()));
				detalle.put("APORTE", new Long(cajaTO.getAporte()));
				detalle.put("CREDITO", new Long(cajaTO.getCredito()));
				detalle.put("LEASING", new Long(cajaTO.getLeasing()));
				detalle.put("VIDA", new Long(cajaTO.getVida()));
				detalle.put("DENTAL", new Long(cajaTO.getDental()));
				detalle.put("SFE", new Long(cajaTO.getSfe()));
			}else{
				informe.setCaja("");
			}
				
			informe.setPagos(detalle);
			lista.put(new Integer(folio), informe);
		}
		return lista;
	}
	
	public Map selectConceptosCaja(int periodo) throws SQLException{
		StringBuffer query= new StringBuffer();
		//int year= (int)Math.floor(periodo/100);
		//int month= periodo-year*100;
		//String fechaini= year + "" + month + "01";
		//String fechafin= year + "" + month + "" + ultimoDia(year, month-1);
		query.append("select PWCCFOLTES, ");
		query.append("SUM(CASE when PWDCCONCE='Asignación Familiar' THEN PWDCMONTO ELSE 0 END) as ASFAM, ");
		query.append("SUM(CASE when PWDCCONCE='0.6%' THEN PWDCMONTO ELSE 0 END) as APORTE, ");
		query.append("SUM(CASE when PWDCCONCE='Créditos' THEN PWDCMONTO ELSE 0 END) as CREDITO, ");
		query.append("SUM(CASE when PWDCCONCE='Leasing' THEN PWDCMONTO ELSE 0 END) as LEASING, ");
		query.append("SUM(CASE when PWDCCONCE='Seguros de Vida' THEN PWDCMONTO ELSE 0 END) as SEGURO_VIDA, ");
		query.append("SUM(CASE when PWDCCONCE='Convenios Dentales' THEN PWDCMONTO ELSE 0 END) as CONVENIO_DENTAL, ");
		query.append("SUM(CASE when PWDCCONCE='S.F.E.' THEN PWDCMONTO ELSE 0 END) as SFE, ");
		query.append("PWDCNOMEM as NOMCAJA ");
		query.append("from pwdtad.pwf5000 a join pwdtad.pwf5100 b ");
		query.append("on a.pwccnumco= b.pwdcnumco ");
		//query.append("where pwccfepag >= " + fechaini + " ");
		//query.append("and pwccfepag <= " + fechafin + " ");
		query.append("and pwcccopro = ? ");
		query.append("and int(pwccfoltes)>0 ");
		query.append("and pwcctipro||pwccrutem <> 'S70016160' ");
		query.append("and PWDCTIPEM='CCAF' ");
		//query.append("and pwcccdhol !=122 ");
		query.append("group by PWCCFOLTES, PWDCNOMEM ");
		query.append("order by 1 ");

		logger.finest("Query=" + query.toString());
		//System.out.println("query=" + query);
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		//Se ejecuta la query
		db2.executeQuery();
		
		Map lista= new HashMap();
		while (db2.next()) {
			int folio= db2.getInt(1);
			long asfam= db2.getLong(2);
			long aporte= db2.getLong(3);
			long credito= db2.getLong(4);
			long leasing= db2.getLong(5);
			long vida= db2.getLong(6);
			long dental= db2.getLong(7);
			long sfe= db2.getLong(8);
			String caja= db2.getString(9);
			if(caja==null){
				caja="";
			}
			
			ConceptosCajaTO conceptosTO= new ConceptosCajaTO();
			conceptosTO.setAsfam(asfam);
			conceptosTO.setAporte(aporte);
			conceptosTO.setCredito(credito);
			conceptosTO.setLeasing(leasing);
			conceptosTO.setVida(vida);
			conceptosTO.setDental(dental);
			conceptosTO.setSfe(sfe);
			conceptosTO.setCaja(caja);
			lista.put(new Integer(folio), conceptosTO);
		}
		logger.info("Número de Pagos Conceptos caja = " + lista.size());
		return lista;
	}
	
	public List selectEmpresasconSPL(Map informes) throws SQLException{
		Set folios= informes.keySet();
		String lista_folios= folios.toString();
		lista_folios= lista_folios.substring(1, lista_folios.length()-1);
		//System.out.println(lista_folios);
		StringBuffer query= new StringBuffer();
		query.append("select D.FOLIO, P.FCH_CONTABLE, COALESCE(P.REG_LIBRO_BANCO, 0), COALESCE(M.DESCRIPCION, ''), COALESCE(M.CODBANCO, 0), COALESCE(C.CTACTE, ''), COALESCE(P.ID_PAGO, 0) as id_pago, COALESCE(P.id_convenio, 0) ");
		query.append("from spldta.detpago D "); 
		query.append("join spldta.pago P ");
		query.append("on P.id_pago= D.id_pago ");
		query.append("left join spldta.convenio C ");
		query.append("on P.id_convenio= C.id_convenio ");
		query.append("left join spldta.mediopago M ");
		query.append("on P.id_convenio= M.id_mediopago ");
		query.append("where P.pagado=1 ");
		query.append("and D.folio in ( ");
		query.append(lista_folios);
		query.append(") ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.executeQuery();
		List id_pagos= new ArrayList();
		while (db2.next()) {
			int folio=db2.getInt(1);
			Date date_contable= db2.getDate(2);
			int registro= db2.getInt(3);
			String nombre_banco=db2.getString(4);
			int idBanco=db2.getInt(5);
			String idCuenta=db2.getString(6);
			int idPago= db2.getInt(7);
			int medioPago= db2.getInt(8);
			InformeTO infoTO= (InformeTO)informes.get(new Integer(folio));
			if (infoTO!=null){
				BancoTO banco= new BancoTO();
				banco.setNombre(nombre_banco);
				banco.setIdBanco(idBanco);
				banco.setIdCuenta(idCuenta);
				banco.setIdPago(idPago);
				banco.setMedioPago(medioPago);
				if(idPago>0){
					id_pagos.add(new Integer(idPago));
				}
				banco.setTipoRegistro(registro);
				if(date_contable!= null){
					banco.setFechaContable(date_contable.toString());
				}else{
					banco.setFechaContable("");
				}
				infoTO.setBanco(banco);				
				informes.put(new Integer(folio), infoTO);
			}
		}
		logger.info("Total Pagos con SPL: " + id_pagos.size());
		return id_pagos;
	}
	
	public List selectEmpresasconPAC(Map informes) throws SQLException{
		Set folios= informes.keySet();
		String lista_folios= folios.toString();
		lista_folios= lista_folios.substring(1, lista_folios.length()-1);
		//System.out.println(lista_folios);
		StringBuffer query= new StringBuffer();
		query.append("select D.FOLIO, B.FCH_CONTABLE, 0, COALESCE(M.NOMBRE_BANCO, ''), COALESCE(M.CODIGO_BANCO, 0), COALESCE(M.CTACTE, ''), COALESCE(B.ID_BOLETA, 0) as id_boleta, COALESCE(B.ID_MEDIOPAGO, 0), B.monto ");
		query.append("from pwdtad.pwf5700 D "); 
		query.append("join pwdtad.pwf5600 B ");
		query.append("on B.id_boleta= D.id_boleta ");
		query.append("left join pwdtad.mediopago M ");
		query.append("on B.id_mediopago= M.id_mediopago ");
		query.append("where D.folio in ( ");
		query.append(lista_folios);
		query.append(") ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.executeQuery();
		List id_pagos= new ArrayList();
		while (db2.next()) {
			int folio=db2.getInt(1);
			Date date_contable= db2.getDate(2);
			int registro= db2.getInt(3);
			String nombre_banco=db2.getString(4);
			int idBanco=db2.getInt(5);
			String idCuenta=db2.getString(6);
			int idBoleta= db2.getInt(7);
			int medioPago= db2.getInt(8);
			long montoBoleta= db2.getLong(9);
			InformeTO infoTO= (InformeTO)informes.get(new Integer(folio));
			if (infoTO!=null){
				BancoTO banco= new BancoTO();
				banco.setNombre(nombre_banco);
				banco.setIdBanco(idBanco);
				banco.setIdCuenta(idCuenta);
				banco.setIdPago(idBoleta);
				banco.setMedioPago(medioPago);
				if(idBoleta>0){
					id_pagos.add(new Integer(idBoleta));
				}
				banco.setTipoRegistro(registro);
				banco.setMontoConsolidado(montoBoleta);
				if(date_contable!= null){
					banco.setFechaContable(date_contable.toString());
				}else{
					banco.setFechaContable("");
				}
				infoTO.setBanco(banco);				
				informes.put(new Integer(folio), infoTO);
			}
		}
		logger.info("Total Pagos con PAC: " + id_pagos.size());
		return id_pagos;
	}
	
	/*public List selectEmpresasconSPL(Map informes, String tabla_folios) throws SQLException{
		Set folios= informes.keySet();
		String lista_folios= folios.toString();
		lista_folios= lista_folios.substring(1, lista_folios.length()-1);
		//System.out.println(lista_folios);
		StringBuffer query= new StringBuffer();
		query.append("select T.TE3WA, T.TE41A as FORMA_PAGO, T.TE40A as FECHA_PAGO, T.TE1TA as HORA, P.FCH_CONTABLE, COALESCE(P.REG_LIBRO_BANCO, 0), COALESCE(M.DESCRIPCION, ''), COALESCE(M.CODBANCO, 0), COALESCE(C.CTACTE, ''), COALESCE(P.ID_PAGO, 0), COALESCE(P.id_convenio, 0) ");
		query.append("from " + tabla_folios + " T "); 
		query.append("left join spldta.detpago D ");
		query.append("on te3wa=folio ");
		query.append("left join spldta.pago P ");
		query.append("on P.id_pago= D.id_pago ");
		query.append("and P.pagado=1 ");
		query.append("left join spldta.convenio C ");
		query.append("on P.id_convenio= C.id_convenio ");
		query.append("left join spldta.mediopago M ");
		query.append("on P.id_convenio= M.id_mediopago ");
		query.append("where te3ya='C' ");
		query.append("and te3wa in ( ");
		query.append(lista_folios);
		query.append(") ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.executeQuery();
		int cantidad=0;
		List id_pagos= new ArrayList();
		while (db2.next()) {
			int folio=db2.getInt(1);
			String forma_pago= db2.getString(2);
			Date date= db2.getDate(3);
			Time time= db2.getTime(4);
			Date date_contable= db2.getDate(5);
			int registro= db2.getInt(6);
			String nombre_banco=db2.getString(7);
			int idBanco=db2.getInt(8);
			String idCuenta=db2.getString(9);
			int idPago= db2.getInt(10);
			int medioPago= db2.getInt(11);
			InformeTO infoTO= (InformeTO)informes.get(new Integer(folio));
			if (infoTO!=null){
				BancoTO banco= new BancoTO();
				banco.setNombre(nombre_banco);
				banco.setIdBanco(idBanco);
				banco.setIdCuenta(idCuenta);
				banco.setIdPago(idPago);
				banco.setMedioPago(medioPago);
				if(idPago>0){
					id_pagos.add(new Integer(idPago));
				}
				banco.setTipoRegistro(registro);
				if(date_contable!= null){
					banco.setFechaContable(date_contable.toString());
				}else{
					banco.setFechaContable("");
				}
				infoTO.setBanco(banco);
				infoTO.setFormaPago(forma_pago.charAt(0));
				if(forma_pago.equals("P") && idPago==0){
					//BOLETA
					infoTO.setFormaPago('B');
				}
				infoTO.setFechaPago(date.toString());
				infoTO.setHoraPago(time.toString());
				
				informes.put(new Integer(folio), infoTO);
			cantidad++;
			}
		}
		logger.info("Total empresas con SPL o Boleta en " + tabla_folios + ": " + cantidad);
		logger.info("Numero de Id_pagos=" + id_pagos.size());
		return id_pagos;
	}*/
	
	public Map selectPagosxFechaContable(List idPagos) throws SQLException{
			String lista_idpagos=idPagos.toString();
			lista_idpagos= lista_idpagos.substring(1, lista_idpagos.length()-1);
			if(lista_idpagos.equals("")){
				lista_idpagos="0";
			}
			//System.out.println(lista_folios);
			StringBuffer query= new StringBuffer();
			query.append("select id_convenio, fch_contable, sum(monto) ");
			query.append("from spldta.pago "); 
			query.append("where id_pago in ( ");
			query.append(lista_idpagos);
			query.append(") ");
			query.append("group by id_convenio, fch_contable ");
			query.append("order by fch_contable, id_convenio ");
			
			logger.finest("Query=" + query.toString());
			db2.prepareQuery(query.toString());
			db2.executeQuery();
			Map listapagosxfecha= new HashMap();
			while (db2.next()) {
				int id_convenio=db2.getInt(1);
				Date date= db2.getDate(2);
				long monto= db2.getLong(3);
				listapagosxfecha.put(id_convenio +"_"+ date.toString(), new Long(monto));
			}	
			logger.info("Numero de PagosxFecha= " + listapagosxfecha.size());
			return listapagosxfecha;
	}
	
public Object select(Object pk) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	return null;
}
/* (sin Javadoc)
 * @see cl.araucana.planillascp.dao.DAO_Interface#delete(java.lang.Object)
 */
public void delete(Object pk) throws SQLException {
	// TODO Apéndice de método generado automáticamente

}

/* (sin Javadoc)
 * @see cl.araucana.planillascp.dao.DAO_Interface#insert(java.lang.Object)
 */
public int insert(Object obj) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	return 0;
}

public int update(Object obj) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	return 0;
}

public int updateFechaspago(int folio, int fecha, char forma) throws SQLException {
	StringBuffer query= new StringBuffer();
	query.append("UPDATE PWDTAD.PWF5000 SET PWCCFECPAG= ?, PWCCFORPAG=? WHERE PWCCFOLTES= ? ");
	logger.finest("Query=" + query.toString());
	db2.prepareQuery(query.toString());
	db2.setStatement(1, fecha);
	db2.setStatement(2, forma);
	db2.setStatement(3, folio);
	return db2.executeUpdate();
}

//mes parte en 0
public int ultimoDia(int anio, int mes){
	int val = 0;
	switch(mes) {
	case 12: case 2: case 4: case 6: case 7: case 9: case 11: case 0:
		val = 31; 
		break;
	case 1: 
		if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0))) {
			val = 29;
		} else {
			val = 28;
		}
		break;
	case 3: case 5: case 8: case 10: 
		val = 30; 
		break;
	}
	
	return val;
}


}

