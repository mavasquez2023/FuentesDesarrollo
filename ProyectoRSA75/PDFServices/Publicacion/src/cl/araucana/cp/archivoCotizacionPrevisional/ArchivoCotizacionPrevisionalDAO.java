package cl.araucana.cp.archivoCotizacionPrevisional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import month.listMonth;

import com.ibm.websphere.security.Result;


import utilPub.UtilPub;

public class ArchivoCotizacionPrevisionalDAO {

	public List GerenaConsultaDetalle(String tabla, String empresa, String convenio, String fecha, String holding)
	{
		String SQL = "";
		Connection conexion = null;
		Statement stmt = null;
		ResultSet result = null;
		
		List lista = new ArrayList();
		try {
			//nos conectamos a la BD para obtener los datos de cabecera de la Empresa
			UtilPub util = new UtilPub();
			
			conexion = util.getConnection();
			stmt = conexion.createStatement();
			String tablaTemp = "TEMPAC" + String.valueOf(Math.random());
			tablaTemp = tablaTemp.replace('.', 'P');
			SQL = this.GeneraConsultaDetalleDao(tabla, empresa, convenio, fecha, holding, tablaTemp);
//			try {
//				result = stmt.executeQuery("select * from QTEMP.TEMPARCHIVOCOTIZACION");
//				if (result.next()) {
//					stmt.executeQuery("drop table qtemp.tempArchivoCotizacion");
//				}
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
			
			
			
			conexion.prepareStatement(SQL);
			System.out.println("tabla temporal = " +SQL);
			stmt.execute(SQL);
			conexion.commit();
			
			//entra a buscar los registros distintos de P
			if (tabla.equals("PWF6100"))
			{
				SQL = this.GeneraConsultaDetalleDao2(tabla, empresa, convenio, fecha, holding, tablaTemp);
				conexion.prepareStatement(SQL);
				System.out.println("tabla temporal = " +SQL);
				stmt.execute(SQL);
				conexion.commit();
			}
			
			
			
			result = stmt.executeQuery(this.generaDetalleHomologado(tabla, tablaTemp));
			
			
			Object[] campos;
		/*	
			Rut, 	0
			Dv, 	1
			Apellidos, 	2
			Nombre, 	3
			sexo, 	4
			Nacionalidad,  	5
			sucursal, 	6
			TipoPago, 	7
			periododesde, 	8
			periodohasta, 	9
			RegimenPrevisional, 	10
			Tipotrabajador, 	11
			TipoLinea, 	12
			dias, 	13
			codigomovimientopersonal	14
			MVfechadesde, 	15
			MVfechahasta, 	16
			Tramo , 	17
			totalHaberes, 	18
			totalHaberes2, 	19
			totalHaberes3, 	20
			totalHaberes4, 	21
			asignacionfamiliar, 	22
			asignacionfamiliarretroactiva, 	23
			ReintegroCargasfamiliares, 	24
			SolicitudTrabajadorJoven, 	25
			entidad,	26
			rentaimponible, 	27
			montocotizado, 	28
			CotizacionSegInvSobre, 	29
			CuentaAhoVolAFP, 	30
			PorcenCotiTrabPesado, 	31
			CotiTrabPesado,	32
			RIseguroCesantiaX,  	33
			RIseguroCesantiaY,	34
			entidadAPV,	35
			cotizacionAPV, 	36
			depositoConvAPV,  	37
			rentaImpIPS,  	38
			cotizObligIPS,  	39
			desCargasFamiliares, 	40
			institucionSalud,	41
			rentaImpIsapre, 	42
			Moneda, 	43
			cotizacionObl, 	44
			cotizacionVol, 	45
			codigoCC,	46
			imponibleCC, 	47
			creditoCC, 	48
			descuentoDentalCC, 	49
			leasingCC, 	50
			segurovidaCC, 	51
			cargasCC, 	52
			CotizacionCCAFNoAfi, 	53
			codigoMT,	54
			imponibleMT, 	55
			cotizacionMT, 	56
			aporteTrabajador, 	57
			aporteEmpleador, 	58
			datosPagadorSubsidio,	59
			ordenAPV, 	60
			ordenMOV, 	61
			numeroapv, 	62
			numeroMOV	63
			cotizacionOblPENSION 64
*/
			
			while (result.next()) {
				 campos = new Object[65];
				 campos[0]= util.validaNULL(result.getObject(1));
				 campos[1]= util.validaNULL(result.getObject(2));
				 campos[2]= util.validaNULL(result.getObject(3));
				 campos[3]= util.validaNULL(result.getObject(4));
				 campos[4]= util.validaNULL(result.getObject(5));
				 campos[5]= util.validaNULL(result.getObject(6));
				 campos[6]= util.validaNULL(result.getObject(7));
				 campos[7]= util.validaNULL(result.getObject(8));
				 campos[8]= util.validaNULL(result.getObject(9));
				 campos[9]= util.validaNULL(result.getObject(10));
				 campos[10]= util.validaNULL(result.getObject(11));
				 campos[11]= util.validaNULL(result.getObject(12));
				 campos[12]= util.validaNULL(result.getObject(13));
				 campos[13]= util.validaNULL(result.getObject(14));
				 campos[14]= util.validaNULL(result.getObject(15));
				 campos[15]= util.validaNULL(result.getObject(16));
				 campos[16]= util.validaNULL(result.getObject(17));
				 campos[17]= util.validaNULL(result.getObject(18));
				 campos[18]= util.validaNULL(result.getObject(19));
				 campos[19]= util.validaNULL(result.getObject(20));
				 campos[20]= util.validaNULL(result.getObject(21));
				 campos[21]= util.validaNULL(result.getObject(22));
				 campos[22]= util.validaNULL(result.getObject(23));
				 campos[23]= util.validaNULL(result.getObject(24));
				 campos[24]= util.validaNULL(result.getObject(25));
				 campos[25]= util.validaNULL(result.getObject(26));
				 campos[26]= util.validaNULL(result.getObject(27));
				 campos[27]= util.validaNULL(result.getObject(28));
				 campos[28]= util.validaNULL(result.getObject(29));
				 campos[29]= util.validaNULL(result.getObject(30));
				 campos[30]= util.validaNULL(result.getObject(31));
				 campos[31]= util.validaNULL(result.getObject(32));
				 campos[32]= util.validaNULL(result.getObject(33));
				 campos[33]= util.validaNULLNUM(result.getObject(34));
				 campos[34]= util.validaNULLNUM(result.getObject(35));
				 campos[35]= util.validaNULL(result.getObject(36));
				 campos[36]= util.validaNULL(result.getObject(37));
				 campos[37]= util.validaNULL(result.getObject(38));
				 campos[38]= util.validaNULL(result.getObject(39));
				 campos[39]= util.validaNULL(result.getObject(40));
				 campos[40]= util.validaNULL(result.getObject(41));
				 campos[41]= util.validaNULL(result.getObject(42));
				 campos[42]= util.validaNULL(result.getObject(43));
				 campos[43]= util.validaNULL(result.getObject(44));
				 campos[44]= util.validaNULLNUM(result.getObject(45));
				 campos[45]= util.validaNULLNUM(result.getObject(46));
				 campos[46]= util.validaNULL(result.getObject(47));
				 campos[47]= util.validaNULL(result.getObject(48));
				 campos[48]= util.validaNULL(result.getObject(49));
				 campos[49]= util.validaNULL(result.getObject(50));
				 campos[50]= util.validaNULL(result.getObject(51));
				 campos[51]= util.validaNULL(result.getObject(52));
				 campos[52]= util.validaNULL(result.getObject(53));
				 campos[53]= util.validaNULL(result.getObject(54));
				 campos[54]= util.validaNULL(result.getObject(55));
				 campos[55]= util.validaNULL(result.getObject(56));
				 campos[56]= util.validaNULL(result.getObject(57));
				 campos[57]= util.validaNULL(result.getObject(58));
				 campos[58]= util.validaNULL(result.getObject(59));
				 campos[59]= util.validaNULL(result.getObject(60));
				 campos[60]= util.validaNULLNUM(result.getObject(61));
				 campos[61]= util.validaNULLNUM(result.getObject(62));
				 campos[62]= util.validaNULLNUM(result.getObject(63));
				 campos[63]= util.validaNULLNUM(result.getObject(64));
				 campos[64]= util.validaNULLNUM(result.getObject(66));

				lista.add(campos);
			}
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}finally
		{
			try{
				if (null != result){
					result.close();
                } 
                if (null != stmt){
                	stmt.close();
                }  
                if (null != conexion){
             	   	conexion.close();
                }  
            }catch(SQLException e){
            	System.out.println("error: "+ e);
            }
		}
		
		return lista;
	}

	private String GeneraConsultaDetalleDao2(String tabla, String empresa,
			String convenio, String fecha, String holding, String tablaTemp) {
		
		String SQL = " ";
		
		if (tabla.equals("PWF6100")) {
			
			
			SQL += " insert into QTEMP."+tablaTemp+" ( select "+
			" rd.pwdcrutaf as Rut, "+
			" rd.pwdcdigaf as Dv, "+
			" rc.pwccapeaf as Apellidos, "+
			" rc.pwccnomaf as Nombre, "+
		 	" case when rc.PWCCADIC1 IS NULL then 'M'   " +
		 	"		when rc.PWCCADIC1=' '  then  'M'  " +
		 	"		else  rc.PWCCADIC1 end as sexo, "+
			" rc.PWCCADIC2 as Nacionalidad,  "+
			" rc.PWCCSUCUR as sucursal, "+
			" '01' TipoPago, "+
			empresa + " as RUTE, "+
			convenio + " CONVENIO, "+
			fecha.substring(4, 6)+ " MES, "+
			fecha.substring(0, 4)+ " AÑO, "+
			" rd.pwdcmesre*10000+rd.pwdcanore as periododesde, "+
			" rd.pwdcmesre*10000+rd.pwdcanore as periodohasta, "+
			" case left(coalesce(rd.pwdcentid, 'NULL'), 4)   when 'NULL' then 'SIP' when 'INP(' then 'INP' else 'AFP' end as RegimenPrevisional, "+
			" '0' Tipotrabajador, "+
			" '00' TipoLinea, "+
			" (select diasT.pwdcmonco "+
			" from pwdtad.pwf6100 as diasT"+
			" where diasT.pwdcrutem = rd.pwdcrutem"+
			" and diasT.pwdcrutaf = rd.pwdcrutaf"+
			" and diasT.pwdcconve = rd.pwdcconve"+
			" and diasT.pwdcmesre = rd.pwdcmesre"+
			" and diasT.pwdcanore = rd.pwdcanore"+
			" and diasT.pwdctipen = 'H') as dias, "+
			" mv.PWDCMOVPE as codigomovimientopersonal, "+ //movimientopersonal
			" mv.PWDCFECINI as MVfechadesde, "+
			" mv.PWDCFECTER as MVfechahasta, "+
			
	//falta tramo
			
			" case when rd.pwdcremim between 1 and 202516 then 'A'  "+
			"     when rd.pwdcremim between 202517 and 317407 then 'B' "+
			"     when rd.pwdcremim between 317408 and 495047 then 'C' "+
			"     when rd.pwdcremim > 495047 then 'D' "+
			"     else '' "+
			" end as Tramo , "+
			
			
			" rd.pwdcremim as totalHaberes, "+
			" rd.pwdcremim as totalHaberes2, "+
			" rd.pwdcremim as totalHaberes3, "+
			" rd.pwdcremim as totalHaberes4, "+
			
			" (select dd.pwdcmonco "+
			" from pwdtad.pwf6100 as dd"+
			" where dd.pwdcrutem = rd.pwdcrutem"+
			" and dd.pwdcrutaf = rd.pwdcrutaf"+
			" and dd.pwdcconve = rd.pwdcconve"+
			" and dd.pwdcmesre = rd.pwdcmesre"+
			" and dd.pwdcanore = rd.pwdcanore"+
			" and dd.pwdctipen = 'C') as asignacionfamiliar, "+
			" (select dd.pwdcmonco "+
			" from pwdtad.pwf6100 as dd"+
			" where dd.pwdcrutem = rd.pwdcrutem"+
			" and dd.pwdcrutaf = rd.pwdcrutaf"+
			" and dd.pwdcconve = rd.pwdcconve"+
			" and dd.pwdcmesre = rd.pwdcmesre"+
			" and dd.pwdcanore = rd.pwdcanore"+
			" and dd.pwdctipen = 'T') as asignacionfamiliarretroactiva, "+
			" (select dd.pwdcmonco "+
			" from pwdtad.pwf6100 as dd"+
			" where dd.pwdcrutem = rd.pwdcrutem"+
			" and dd.pwdcrutaf = rd.pwdcrutaf"+
			" and dd.pwdcconve = rd.pwdcconve"+
			" and dd.pwdcmesre = rd.pwdcmesre"+
			" and dd.pwdcanore = rd.pwdcanore"+
			" and dd.pwdctipen = 'U') as ReintegroCargasfamiliares, "+
			" 'N' as SolicitudTrabajadorJoven, "+
			" case when POSITION('CUPRUM' in rd.PWDCENTID) <> 0 then 'CUPRUM'"+
			" when POSITION('HABITAT' in rd.PWDCENTID) <> 0 then 'HABITAT'"+
			" when POSITION('PROVIDA' in rd.PWDCENTID) <> 0 then 'PROVIDA'"+
			" when POSITION('PLANVITAL' in rd.PWDCENTID) <> 0 then 'PLANVITAL'"+
			" when POSITION('CAPITAL' in rd.PWDCENTID) <> 0 then 'CAPITAL'"+
			" when POSITION('MODELO' in rd.PWDCENTID) <> 0 then 'MODELO'"+
			" else '' "+
			" end as entidad, "+  //entidad
			
			" rd.PWDCREMIM as rentaimponible, "+
			"'0'  as montocotizado, "+ //aqui carlos
			" (select dd.pwdcmonco "+
			" from pwdtad.pwf6100 as dd"+
			" where dd.pwdcrutem = rd.pwdcrutem"+
			" and dd.pwdcrutaf = rd.pwdcrutaf"+
			" and dd.pwdcconve = rd.pwdcconve"+
			" and dd.pwdcmesre = rd.pwdcmesre"+
			" and dd.pwdcanore = rd.pwdcanore"+
			" and dd.pwdctipen = 'I') as CotizacionSegInvSobre, "+
			" (select dd.pwdcmonco "+
			" from pwdtad.pwf6100 as dd"+
			" where dd.pwdcrutem = rd.pwdcrutem"+
			" and dd.pwdcrutaf = rd.pwdcrutaf"+
			" and dd.pwdcconve = rd.pwdcconve"+
			" and dd.pwdcmesre = rd.pwdcmesre"+
			" and dd.pwdcanore = rd.pwdcanore"+
			" and dd.pwdctipen = 'A') as CuentaAhoVolAFP, "+
			" '00,00' as PorcenCotiTrabPesado, "+
			" (select dd.pwdcmonco "+
			" from pwdtad.pwf6100 as dd"+
			" where dd.pwdcrutem = rd.pwdcrutem"+
			" and dd.pwdcrutaf = rd.pwdcrutaf"+
			" and dd.pwdcconve = rd.pwdcconve"+
			" and dd.pwdcmesre = rd.pwdcmesre"+
			" and dd.pwdcanore = rd.pwdcanore"+
			" and dd.pwdctipen = 'K') as CotiTrabPesado, "+
			
			" case when POSITION('CUPRUM' in cc.PWDCENTID) <> 0 then 'CUPRUM'  " +
			"	when POSITION('HABITAT' in cc.PWDCENTID) <> 0 then 'HABITAT'  " +
			"	when POSITION('PROVIDA' in cc.PWDCENTID) <> 0 then 'PROVIDA'  " +
			"	when POSITION('PLANVITAL' in cc.PWDCENTID) <> 0 then 'PLANVITAL'  " +
			"	when POSITION('CAPITAL' in cc.PWDCENTID) <> 0 then 'CAPITAL'  " +
			"	when POSITION('MODELO' in cc.PWDCENTID) <> 0 then 'MODELO'  " +
			"	else '00'  end as entidadAPV, "+
			
			" cc.pwdcmonco as cotizacionAPV, "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6102 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'D') as depositoConvAPV,  "+
			" (select dd.PWDCREMIM  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'S') as rentaImpIPS,  "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'S') as cotizObligIPS,  "+
			
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'C' "+
			" and POSITION('INP (CAJA)' in dd.PWDCENTID) <> 0 ) as desCargasFamiliares, "+
			
			" (select case when POSITION('CRUZ BLANCA' in dd.PWDCENTID) <> 0 then 'CRUZ BLANCA'"+
			"     when POSITION('COLMENA' in dd.PWDCENTID) <> 0 then 'COLMENA'"+
			"     when POSITION('CONSALUD' in dd.PWDCENTID) <> 0 then 'CONSALUD'"+
			"     when POSITION('VIDA TRES' in dd.PWDCENTID) <> 0 then 'VIDA TRES'"+
			"     when POSITION('BANMEDICA' in dd.PWDCENTID) <> 0 then 'BANMEDICA'"+
			"     when POSITION('INP' in dd.PWDCENTID) <> 0 then 'INP'"+
			"     when POSITION('CHUQUICAMATA' in dd.PWDCENTID) <> 0 then 'CHUQUICAMATA'"+
			"     when POSITION('FERROSALUD' in dd.PWDCENTID) <> 0 then 'FERROSALUD'"+
			"     when POSITION('FUSAT' in dd.PWDCENTID) <> 0 then 'FUSAT'"+
			"     when POSITION('BANCO' in dd.PWDCENTID) <> 0 then 'BANCO'"+
			"     when POSITION('MAS VIDA' in dd.PWDCENTID) <> 0 then 'MAS VIDA'"+
			"     when POSITION('RIO BLANCO' in dd.PWDCENTID) <> 0 then 'RIO BLANCO'"+
			"     when POSITION('SAN LORENZO' in dd.PWDCENTID) <> 0 then 'SAN LORENZO'"+
			"     when POSITION('CRUZ DEL NORTE' in dd.PWDCENTID) <> 0 then 'CRUZ DEL NORTE'"+
			" else '' end "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'S') as institucionSalud, "+ //institucion salud


			" (select dd.PWDCREMIM  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'S') as rentaImpIsapre, "+
			" '1' as Moneda, "+

			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'S') as cotizacionObl, "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'J') as cotizacionVol, "+
			" (select distinct case when POSITION('ARAUCANA' in dd.PWDCENTID) <> 0 then 'ARAUCANA'"+
			"     when POSITION('HEROES' in dd.PWDCENTID) <> 0 then 'HEROES'"+
			"     when POSITION('ANDES' in dd.PWDCENTID) <> 0 then 'ANDES'"+
			"     when POSITION('SEPTIEMBRE' in dd.PWDCENTID) <> 0 then 'SEPTIEMBRE'"+
			"     when POSITION('GABRIELA MISTRAL' in dd.PWDCENTID) <> 0 then 'GABRIELA MISTRAL'"+
			"     else ''"+
			"     end "+ 
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen in ('C','R','L','O','U','D','V'))  as codigoCC, "+ //codigo cc
			
			" (select dd.PWDCREMIM  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'C') as imponibleCC, "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'R') as creditoCC, "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'D') as descuentoDentalCC, "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'L') as leasingCC, "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'V') as segurovidaCC, "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'C') as cargasCC, "+
			
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'O') as CotizacionCCAFNoAfi, "+
			
			
			" (select case when POSITION('ACHS' in dd.PWDCENTID) <> 0 then 'ACHS'"+
			"     when POSITION('CCHC' in dd.PWDCENTID) <> 0 then 'CCHC'"+
			"     when POSITION('SEGURIDAD DEL TRABAJO' in dd.PWDCENTID) <> 0 then 'SEGURIDAD DEL TRABAJO'"+
			"     else ''"+
			"     end "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'M') as codigoMT, "+ //codigo mt
			
			" (select PWDCREMIM  "+
			" from pwdtad.pwf6100 as diasT  "+
			" where diasT.pwdcrutem = rd.pwdcrutem  "+
			" and diasT.pwdcrutaf = rd.pwdcrutaf  "+
			" and diasT.pwdcconve = rd.pwdcconve  "+
			" and diasT.pwdcmesre = rd.pwdcmesre  "+
			" and diasT.pwdcanore = rd.pwdcanore  "+
			" and diasT.pwdctipen = 'M') as imponibleMT, "+
			" (select pwdcmonco  "+
			" from pwdtad.pwf6100 as diasT  "+
			" where diasT.pwdcrutem = rd.pwdcrutem  "+
			" and diasT.pwdcrutaf = rd.pwdcrutaf  "+
			" and diasT.pwdcconve = rd.pwdcconve  "+
			" and diasT.pwdcmesre = rd.pwdcmesre  "+
			" and diasT.pwdcanore = rd.pwdcanore  "+
			" and diasT.pwdctipen = 'M') as cotizacionMT, "+
			
			
			
			" (select pwdcmonco  "+
			" from pwdtad.pwf6100 as diasT  "+
			" where diasT.pwdcrutem = rd.pwdcrutem  "+
			" and diasT.pwdcrutaf = rd.pwdcrutaf  "+
			" and diasT.pwdcconve = rd.pwdcconve  "+
			" and diasT.pwdcmesre = rd.pwdcmesre  "+
			" and diasT.pwdcanore = rd.pwdcanore  "+
			" and diasT.pwdctipen = 'X') as aporteTrabajador, "+
			" (select pwdcmonco  "+
			" from pwdtad.pwf6100 as diasT  "+
			" where diasT.pwdcrutem = rd.pwdcrutem  "+
			" and diasT.pwdcrutaf = rd.pwdcrutaf  "+
			" and diasT.pwdcconve = rd.pwdcconve  "+
			" and diasT.pwdcmesre = rd.pwdcmesre  "+
			" and diasT.pwdcanore = rd.pwdcanore  "+
			" and diasT.pwdctipen = 'Y') as aporteEmpleador, "+
			
			//pagadores subsidios
			" case when POSITION('CRUZ BLANCA' in mv.PWDCENTID) <> 0 then 'CRUZ BLANCA'"+
			"     when POSITION('COLMENA' in mv.PWDCENTID) <> 0 then 'COLMENA'"+
			"     when POSITION('CONSALUD' in mv.PWDCENTID) <> 0 then 'CONSALUD'"+
			"     when POSITION('VIDA TRES' in mv.PWDCENTID) <> 0 then 'VIDA TRES'"+
			"     when POSITION('BANMEDICA' in mv.PWDCENTID) <> 0 then 'BANMEDICA'"+
			"     when POSITION('INP' in mv.PWDCENTID) <> 0 then 'INP'"+
			"     when POSITION('CHUQUICAMATA' in mv.PWDCENTID) <> 0 then 'CHUQUICAMATA'"+
			"     when POSITION('FERROSALUD' in mv.PWDCENTID) <> 0 then 'FERROSALUD'"+
			"     when POSITION('FUSAT' in mv.PWDCENTID) <> 0 then 'FUSAT'"+
			"     when POSITION('BANCO' in mv.PWDCENTID) <> 0 then 'BANCO'"+
			"     when POSITION('MAS VIDA' in mv.PWDCENTID) <> 0 then 'MAS VIDA'"+
			"     when POSITION('RIO BLANCO' in mv.PWDCENTID) <> 0 then 'RIO BLANCO'"+
			"     when POSITION('SAN LORENZO' in mv.PWDCENTID) <> 0 then 'SAN LORENZO'"+
			"     when POSITION('CRUZ DEL NORTE' in mv.PWDCENTID) <> 0 then 'CRUZ DEL NORTE'"+
			" when POSITION('ACHS' in mv.PWDCENTID) <> 0 then 'ACHS'"+
			"     when POSITION('CCHC' in mv.PWDCENTID) <> 0 then 'CCHC'"+
			"     when POSITION('SEGURIDAD DEL TRABAJO' in mv.PWDCENTID) <> 0 then 'SEGURIDAD DEL TRABAJO'  "+
			"     when POSITION('SEGURIDAD LABORAL' in mv.PWDCENTID) <> 0 then 'SEGURIDAD LABORAL'  "+
			"    else '' end as datosPagadorSubsidio, "+
			
			" cc.pwdctipen as ordenAPV, "+
			" mv.PWDCCORR as ordenMOV, "+
			" (select count(pwdcmonco) from pwdtad.pwf6103 as dd where"+
			"                        dd.pwdcrutaf = rd.pwdcrutaf  "+
			"                        and rd.pwdcmesre = dd.pwdcmesre"+
			"                        and rd.pwdcanore = dd.pwdcanore   "+
			"                        and rd.pwdcconve = dd.pwdcconve"+
			"           			 and rd.pwdcrutem = dd.pwdcrutem"+
			"           			 and rd.pwdccdhol = dd.pwdccdhol) as numeroapv, "+
			" (select count(pwdcrutaf) from pwdtad.pwf6104 as dd where"+
			"                        dd.pwdcrutaf = rd.pwdcrutaf  "+
			"                        and rd.pwdcmesre = dd.pwdcmesre"+
			"                        and rd.pwdcanore = dd.pwdcanore   "+
			"                        and rd.pwdcconve = dd.pwdcconve" +
			"           			 and rd.pwdcrutem = dd.pwdcrutem"+
			"           			 and rd.pwdccdhol = dd.pwdccdhol) as numeroMOV, "+
			" CASE WHEN rd.pwdctipen = 'P' THEN 1 "+
            "                    WHEN rd.pwdctipen = 'S' THEN 2 "+
            "                    WHEN rd.pwdctipen = 'O' THEN 3 "+
            "                    WHEN rd.pwdctipen = 'M' THEN 4 "+
            "                    WHEN rd.pwdctipen = 'I' THEN 5 "+
            "                    WHEN rd.pwdctipen = 'X' THEN 6 "+
            "                    ELSE 7 END AS ordentipo "+
//            " (select dd.pwdcmonco  "+
//			" from pwdtad.pwf6100 as dd  "+
//			" where dd.pwdcrutem = rd.pwdcrutem  "+
//			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
//			" and dd.pwdcconve = rd.pwdcconve  "+
//			" and dd.pwdcmesre = rd.pwdcmesre  "+
//			" and dd.pwdcanore = rd.pwdcanore  "+
//			" and dd.pwdctipen = 'P' and POSITION('Pensi' in dd.pwdcentid) <> 0 ) as cotizacionOblPENSION "+
			
			" from pwdtad.pwf6100 as rd   "+
			" join pwdtad.pwf6000 as rc on rd.pwdcrutaf = rc.pwccrutaf "+
			"        				 and rd.pwdcrutem = rc.pwccrutem"+
			"           			 and rd.pwdccdhol = rc.pwcccdhol"+
			"                        and rd.pwdcconve = rc.pwccconve"+
			"                left join pwdtad.pwf6103 as cc on cc.pwdcrutaf = rd.pwdcrutaf  "+
			"           			 and rd.pwdcrutem = cc.pwdcrutem"+
			"           			 and rd.pwdccdhol = cc.pwdccdhol"+
			"                        and rd.pwdcmesre = cc.pwdcmesre"+
			"                        and rd.pwdcanore = cc.pwdcanore"+
			"                        and rd.pwdcconve = cc.pwdcconve"+
			"                left join pwdtad.pwf6104 as mv on mv.pwdcrutaf = rd.pwdcrutaf  "+
			"           			 and rd.pwdcrutem = mv.pwdcrutem"+
			"           			 and rd.pwdccdhol = mv.pwdccdhol"+
			"                        and rd.pwdcmesre = mv.pwdcmesre"+
			"                        and rd.pwdcanore = mv.pwdcanore"+
			"                        and rd.pwdcconve = mv.pwdcconve "+
			" where rd.pwdcrutem = "+ empresa + 
			"  and rd.pwdcconve = "+ convenio +
			"  and rd.pwdcmesre = '"+ fecha.substring(4, 6)+ "'" +
			"  and rd.pwdcanore = "+ fecha.substring(0, 4)+
			"  and rd.pwdctipen in ('Y','X','M','O','S','I') "+
			" AND NOT EXISTS (SELECT Rut FROM QTEMP."+tablaTemp+
			" WHERE Rut = rd.pwdcrutaf ) )";
			
		}
		return SQL;

	}

	private String GeneraConsultaDetalleDao(String tabla, String empresa, String convenio, String fecha, String holding, String tablaTemp)
	{
		String SQL = "create table QTEMP."+ tablaTemp +" as (";
			
		if (tabla.equals("PWF6100")) {
			SQL += " select "+
			" rd.pwdcrutaf as Rut, "+
			" rd.pwdcdigaf as Dv, "+
			" rc.pwccapeaf as Apellidos, "+
			" rc.pwccnomaf as Nombre, "+
		 	" case when rc.PWCCADIC1 IS NULL then 'M'   " +
		 	"		when rc.PWCCADIC1=' '  then  'M'  " +
		 	"		else  rc.PWCCADIC1 end as sexo, "+
			" rc.PWCCADIC2 as Nacionalidad,  "+
			" rc.PWCCSUCUR as sucursal, "+
			" '01' TipoPago, "+
			empresa + " as RUTE, "+
			convenio + " CONVENIO, "+
			fecha.substring(4, 6)+ " MES, "+
			fecha.substring(0, 4)+ " AÑO, "+
			" rd.pwdcmesre*10000+rd.pwdcanore as periododesde, "+
			" rd.pwdcmesre*10000+rd.pwdcanore as periodohasta, "+
			" case left(coalesce(rd.pwdcentid, 'NULL'), 4)   when 'NULL' then 'SIP' when 'INP(' then 'INP' else 'AFP' end as RegimenPrevisional, "+
			" '0' Tipotrabajador, "+
			" '00' TipoLinea, "+
			" (select diasT.pwdcmonco "+
			" from pwdtad.pwf6100 as diasT"+
			" where diasT.pwdcrutem = rd.pwdcrutem"+
			" and diasT.pwdcrutaf = rd.pwdcrutaf"+
			" and diasT.pwdcconve = rd.pwdcconve"+
			" and diasT.pwdcmesre = rd.pwdcmesre"+
			" and diasT.pwdcanore = rd.pwdcanore"+
			" and diasT.pwdctipen = 'H') as dias, "+
			" mv.PWDCMOVPE as codigomovimientopersonal, "+ //movimientopersonal
			" mv.PWDCFECINI as MVfechadesde, "+
			" mv.PWDCFECTER as MVfechahasta, "+
			
	//falta tramo
			
			" case when rd.pwdcremim between 1 and 202516 then 'A'  "+
			"     when rd.pwdcremim between 202517 and 317407 then 'B' "+
			"     when rd.pwdcremim between 317408 and 495047 then 'C' "+
			"     when rd.pwdcremim > 495047 then 'D' "+
			"     else '' "+
			" end as Tramo , "+
			
			
			" rd.pwdcremim as totalHaberes, "+
			" rd.pwdcremim as totalHaberes2, "+
			" rd.pwdcremim as totalHaberes3, "+
			" rd.pwdcremim as totalHaberes4, "+
			
			" (select dd.pwdcmonco "+
			" from pwdtad.pwf6100 as dd"+
			" where dd.pwdcrutem = rd.pwdcrutem"+
			" and dd.pwdcrutaf = rd.pwdcrutaf"+
			" and dd.pwdcconve = rd.pwdcconve"+
			" and dd.pwdcmesre = rd.pwdcmesre"+
			" and dd.pwdcanore = rd.pwdcanore"+
			" and dd.pwdctipen = 'C') as asignacionfamiliar, "+
			" (select dd.pwdcmonco "+
			" from pwdtad.pwf6100 as dd"+
			" where dd.pwdcrutem = rd.pwdcrutem"+
			" and dd.pwdcrutaf = rd.pwdcrutaf"+
			" and dd.pwdcconve = rd.pwdcconve"+
			" and dd.pwdcmesre = rd.pwdcmesre"+
			" and dd.pwdcanore = rd.pwdcanore"+
			" and dd.pwdctipen = 'T') as asignacionfamiliarretroactiva, "+
			" (select dd.pwdcmonco "+
			" from pwdtad.pwf6100 as dd"+
			" where dd.pwdcrutem = rd.pwdcrutem"+
			" and dd.pwdcrutaf = rd.pwdcrutaf"+
			" and dd.pwdcconve = rd.pwdcconve"+
			" and dd.pwdcmesre = rd.pwdcmesre"+
			" and dd.pwdcanore = rd.pwdcanore"+
			" and dd.pwdctipen = 'U') as ReintegroCargasfamiliares, "+
			" 'N' as SolicitudTrabajadorJoven, "+
			" case when POSITION('CUPRUM' in rd.PWDCENTID) <> 0 then 'CUPRUM'"+
			" when POSITION('HABITAT' in rd.PWDCENTID) <> 0 then 'HABITAT'"+
			" when POSITION('PROVIDA' in rd.PWDCENTID) <> 0 then 'PROVIDA'"+
			" when POSITION('PLANVITAL' in rd.PWDCENTID) <> 0 then 'PLANVITAL'"+
			" when POSITION('CAPITAL' in rd.PWDCENTID) <> 0 then 'CAPITAL'"+
			" when POSITION('MODELO' in rd.PWDCENTID) <> 0 then 'MODELO'"+
			" else '' "+
			" end as entidad, "+  //entidad
			
			" rd.PWDCREMIM as rentaimponible, "+
			" rd.PWDCMONCO as montocotizado, "+
			" (select dd.pwdcmonco "+
			" from pwdtad.pwf6100 as dd"+
			" where dd.pwdcrutem = rd.pwdcrutem"+
			" and dd.pwdcrutaf = rd.pwdcrutaf"+
			" and dd.pwdcconve = rd.pwdcconve"+
			" and dd.pwdcmesre = rd.pwdcmesre"+
			" and dd.pwdcanore = rd.pwdcanore"+
			" and dd.pwdctipen = 'I') as CotizacionSegInvSobre, "+
			" (select dd.pwdcmonco "+
			" from pwdtad.pwf6100 as dd"+
			" where dd.pwdcrutem = rd.pwdcrutem"+
			" and dd.pwdcrutaf = rd.pwdcrutaf"+
			" and dd.pwdcconve = rd.pwdcconve"+
			" and dd.pwdcmesre = rd.pwdcmesre"+
			" and dd.pwdcanore = rd.pwdcanore"+
			" and dd.pwdctipen = 'A') as CuentaAhoVolAFP, "+
			" '00,00' as PorcenCotiTrabPesado, "+
			" (select dd.pwdcmonco "+
			" from pwdtad.pwf6100 as dd"+
			" where dd.pwdcrutem = rd.pwdcrutem"+
			" and dd.pwdcrutaf = rd.pwdcrutaf"+
			" and dd.pwdcconve = rd.pwdcconve"+
			" and dd.pwdcmesre = rd.pwdcmesre"+
			" and dd.pwdcanore = rd.pwdcanore"+
			" and dd.pwdctipen = 'K') as CotiTrabPesado, "+
			
			" case when POSITION('CUPRUM' in cc.PWDCENTID) <> 0 then 'CUPRUM'  " +
			"	when POSITION('HABITAT' in cc.PWDCENTID) <> 0 then 'HABITAT'  " +
			"	when POSITION('PROVIDA' in cc.PWDCENTID) <> 0 then 'PROVIDA'  " +
			"	when POSITION('PLANVITAL' in cc.PWDCENTID) <> 0 then 'PLANVITAL'  " +
			"	when POSITION('CAPITAL' in cc.PWDCENTID) <> 0 then 'CAPITAL'  " +
			"	when POSITION('MODELO' in cc.PWDCENTID) <> 0 then 'MODELO'  " +
			"	else '00'  end as entidadAPV, "+
			
			" cc.pwdcmonco as cotizacionAPV, "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6102 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'D') as depositoConvAPV,  "+
			" (select dd.PWDCREMIM  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'S') as rentaImpIPS,  "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'S') as cotizObligIPS,  "+
			
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'C' "+
			" and POSITION('INP (CAJA)' in dd.PWDCENTID) <> 0 ) as desCargasFamiliares, "+
			
			" (select case when POSITION('CRUZ BLANCA' in dd.PWDCENTID) <> 0 then 'CRUZ BLANCA'"+
			"     when POSITION('COLMENA' in dd.PWDCENTID) <> 0 then 'COLMENA'"+
			"     when POSITION('CONSALUD' in dd.PWDCENTID) <> 0 then 'CONSALUD'"+
			"     when POSITION('VIDA TRES' in dd.PWDCENTID) <> 0 then 'VIDA TRES'"+
			"     when POSITION('BANMEDICA' in dd.PWDCENTID) <> 0 then 'BANMEDICA'"+
			"     when POSITION('INP' in dd.PWDCENTID) <> 0 then 'INP'"+
			"     when POSITION('CHUQUICAMATA' in dd.PWDCENTID) <> 0 then 'CHUQUICAMATA'"+
			"     when POSITION('FERROSALUD' in dd.PWDCENTID) <> 0 then 'FERROSALUD'"+
			"     when POSITION('OPTIMA' in dd.PWDCENTID) <> 0 then 'OPTIMA'"+
			"     when POSITION('FUSAT' in dd.PWDCENTID) <> 0 then 'FUSAT'"+
			"     when POSITION('BANCO' in dd.PWDCENTID) <> 0 then 'BANCO'"+
			"     when POSITION('MAS VIDA' in dd.PWDCENTID) <> 0 then 'MAS VIDA'"+
			"     when POSITION('RIO BLANCO' in dd.PWDCENTID) <> 0 then 'RIO BLANCO'"+
			"     when POSITION('SAN LORENZO' in dd.PWDCENTID) <> 0 then 'SAN LORENZO'"+
			"     when POSITION('CRUZ DEL NORTE' in dd.PWDCENTID) <> 0 then 'CRUZ DEL NORTE'"+
			" else '' end "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'S') as institucionSalud, "+ //institucion salud


			" (select dd.PWDCREMIM  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'S') as rentaImpIsapre, "+
			" '1' as Moneda, "+
//campo a evaluar
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'S') as cotizacionObl, "+
			
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'J') as cotizacionVol, "+
			" (select distinct case when POSITION('ARAUCANA' in dd.PWDCENTID) <> 0 then 'ARAUCANA'"+
			"     when POSITION('HEROES' in dd.PWDCENTID) <> 0 then 'HEROES'"+
			"     when POSITION('ANDES' in dd.PWDCENTID) <> 0 then 'ANDES'"+
			"     when POSITION('SEPTIEMBRE' in dd.PWDCENTID) <> 0 then 'SEPTIEMBRE'"+
			"     when POSITION('GABRIELA MISTRAL' in dd.PWDCENTID) <> 0 then 'GABRIELA MISTRAL'"+
			"     else ''"+
			"     end "+ 
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen in ('C','R','L','O','U','D','V'))  as codigoCC, "+ //codigo cc
			
			" (select dd.PWDCREMIM  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'C') as imponibleCC, "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'R') as creditoCC, "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'D') as descuentoDentalCC, "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'L') as leasingCC, "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'V') as segurovidaCC, "+
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'C') as cargasCC, "+
			
			" (select dd.pwdcmonco  "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'O') as CotizacionCCAFNoAfi, "+
			
			
			" (select case when POSITION('ACHS' in dd.PWDCENTID) <> 0 then 'ACHS'"+
			"     when POSITION('CCHC' in dd.PWDCENTID) <> 0 then 'CCHC'"+
			"     when POSITION('SEGURIDAD DEL TRABAJO' in dd.PWDCENTID) <> 0 then 'SEGURIDAD DEL TRABAJO'"+
			"     else ''"+
			"     end "+
			" from pwdtad.pwf6100 as dd  "+
			" where dd.pwdcrutem = rd.pwdcrutem  "+
			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
			" and dd.pwdcconve = rd.pwdcconve  "+
			" and dd.pwdcmesre = rd.pwdcmesre  "+
			" and dd.pwdcanore = rd.pwdcanore  "+
			" and dd.pwdctipen = 'M') as codigoMT, "+ //codigo mt
			
			" (select PWDCREMIM  "+
			" from pwdtad.pwf6100 as diasT  "+
			" where diasT.pwdcrutem = rd.pwdcrutem  "+
			" and diasT.pwdcrutaf = rd.pwdcrutaf  "+
			" and diasT.pwdcconve = rd.pwdcconve  "+
			" and diasT.pwdcmesre = rd.pwdcmesre  "+
			" and diasT.pwdcanore = rd.pwdcanore  "+
			" and diasT.pwdctipen = 'M') as imponibleMT, "+
			" (select pwdcmonco  "+
			" from pwdtad.pwf6100 as diasT  "+
			" where diasT.pwdcrutem = rd.pwdcrutem  "+
			" and diasT.pwdcrutaf = rd.pwdcrutaf  "+
			" and diasT.pwdcconve = rd.pwdcconve  "+
			" and diasT.pwdcmesre = rd.pwdcmesre  "+
			" and diasT.pwdcanore = rd.pwdcanore  "+
			" and diasT.pwdctipen = 'M') as cotizacionMT, "+
			
			
			
			" (select pwdcmonco  "+
			" from pwdtad.pwf6100 as diasT  "+
			" where diasT.pwdcrutem = rd.pwdcrutem  "+
			" and diasT.pwdcrutaf = rd.pwdcrutaf  "+
			" and diasT.pwdcconve = rd.pwdcconve  "+
			" and diasT.pwdcmesre = rd.pwdcmesre  "+
			" and diasT.pwdcanore = rd.pwdcanore  "+
			" and diasT.pwdctipen = 'X') as aporteTrabajador, "+
			" (select pwdcmonco  "+
			" from pwdtad.pwf6100 as diasT  "+
			" where diasT.pwdcrutem = rd.pwdcrutem  "+
			" and diasT.pwdcrutaf = rd.pwdcrutaf  "+
			" and diasT.pwdcconve = rd.pwdcconve  "+
			" and diasT.pwdcmesre = rd.pwdcmesre  "+
			" and diasT.pwdcanore = rd.pwdcanore  "+
			" and diasT.pwdctipen = 'Y') as aporteEmpleador, "+
			
			//pagadores subsidios
			" case when POSITION('CRUZ BLANCA' in mv.PWDCENTID) <> 0 then 'CRUZ BLANCA'"+
			"     when POSITION('COLMENA' in mv.PWDCENTID) <> 0 then 'COLMENA'"+
			"     when POSITION('CONSALUD' in mv.PWDCENTID) <> 0 then 'CONSALUD'"+
			"     when POSITION('VIDA TRES' in mv.PWDCENTID) <> 0 then 'VIDA TRES'"+
			"     when POSITION('BANMEDICA' in mv.PWDCENTID) <> 0 then 'BANMEDICA'"+
			"     when POSITION('INP' in mv.PWDCENTID) <> 0 then 'INP'"+
			"     when POSITION('CHUQUICAMATA' in mv.PWDCENTID) <> 0 then 'CHUQUICAMATA'"+
			"     when POSITION('FERROSALUD' in mv.PWDCENTID) <> 0 then 'FERROSALUD'"+
			"     when POSITION('OPTIMA' in mv.PWDCENTID) <> 0 then 'OPTIMA'"+
			"     when POSITION('FUSAT' in mv.PWDCENTID) <> 0 then 'FUSAT'"+
			"     when POSITION('BANCO' in mv.PWDCENTID) <> 0 then 'BANCO'"+
			"     when POSITION('MAS VIDA' in mv.PWDCENTID) <> 0 then 'MAS VIDA'"+
			"     when POSITION('RIO BLANCO' in mv.PWDCENTID) <> 0 then 'RIO BLANCO'"+
			"     when POSITION('SAN LORENZO' in mv.PWDCENTID) <> 0 then 'SAN LORENZO'"+
			"     when POSITION('CRUZ DEL NORTE' in mv.PWDCENTID) <> 0 then 'CRUZ DEL NORTE'"+
			" when POSITION('ACHS' in mv.PWDCENTID) <> 0 then 'ACHS'"+
			"     when POSITION('CCHC' in mv.PWDCENTID) <> 0 then 'CCHC'"+
			"     when POSITION('SEGURIDAD DEL TRABAJO' in mv.PWDCENTID) <> 0 then 'SEGURIDAD DEL TRABAJO'  "+
			"     when POSITION('SEGURIDAD LABORAL' in mv.PWDCENTID) <> 0 then 'SEGURIDAD LABORAL'  "+
			"    else '' end as datosPagadorSubsidio, "+
			
			" cc.pwdctipen as ordenAPV, "+
			" mv.PWDCCORR as ordenMOV, "+
			" (select count(pwdcmonco) from pwdtad.pwf6103 as dd where"+
			"                        dd.pwdcrutaf = rd.pwdcrutaf  "+
			"                        and rd.pwdcmesre = dd.pwdcmesre"+
			"                        and rd.pwdcanore = dd.pwdcanore   "+
			"                        and rd.pwdcconve = dd.pwdcconve"+
			"           			 and rd.pwdcrutem = dd.pwdcrutem"+
			"           			 and rd.pwdccdhol = dd.pwdccdhol) as numeroapv, "+
			" (select count(pwdcrutaf) from pwdtad.pwf6104 as dd where"+
			"                        dd.pwdcrutaf = rd.pwdcrutaf  "+
			"                        and rd.pwdcmesre = dd.pwdcmesre"+
			"                        and rd.pwdcanore = dd.pwdcanore   "+
			"                        and rd.pwdcconve = dd.pwdcconve" +
			"           			 and rd.pwdcrutem = dd.pwdcrutem"+
			"           			 and rd.pwdccdhol = dd.pwdccdhol) as numeroMOV, "+
			" 1 as ordentipo "+
//			" (select dd.pwdcmonco  "+
//			" from pwdtad.pwf6100 as dd  "+
//			" where dd.pwdcrutem = rd.pwdcrutem  "+
//			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
//			" and dd.pwdcconve = rd.pwdcconve  "+
//			" and dd.pwdcmesre = rd.pwdcmesre  "+
//			" and dd.pwdcanore = rd.pwdcanore  "+
//			" and dd.pwdctipen = 'P' and POSITION('Pensi' in dd.pwdcentid) <> 0 ) as cotizacionOblPENSION "+
			
			" from pwdtad.pwf6100 as rd   "+
			" join pwdtad.pwf6000 as rc on rd.pwdcrutaf = rc.pwccrutaf "+
			"        				 and rd.pwdcrutem = rc.pwccrutem"+
			"           			 and rd.pwdccdhol = rc.pwcccdhol"+
			"                        and rd.pwdcconve = rc.pwccconve"+
			"                left join pwdtad.pwf6103 as cc on cc.pwdcrutaf = rd.pwdcrutaf  "+
			"           			 and rd.pwdcrutem = cc.pwdcrutem"+
			"           			 and rd.pwdccdhol = cc.pwdccdhol"+
			"                        and rd.pwdcmesre = cc.pwdcmesre"+
			"                        and rd.pwdcanore = cc.pwdcanore"+
			"                        and rd.pwdcconve = cc.pwdcconve"+
			"                left join pwdtad.pwf6104 as mv on mv.pwdcrutaf = rd.pwdcrutaf  "+
			"           			 and rd.pwdcrutem = mv.pwdcrutem"+
			"           			 and rd.pwdccdhol = mv.pwdccdhol"+
			"                        and rd.pwdcmesre = mv.pwdcmesre"+
			"                        and rd.pwdcanore = mv.pwdcanore"+
			"                        and rd.pwdcconve = mv.pwdcconve "+
			" where rd.pwdcrutem = "+ empresa + 
			"  and rd.pwdcconve = "+ convenio +
			"  and rd.pwdcmesre = '"+ fecha.substring(4, 6)+ "'" +
			"  and rd.pwdcanore = "+ fecha.substring(0, 4)+
			"  and rd.pwdctipen = 'P' "+
			"  order by rut, ordenapv, ordenmov ";
			
		}
		else {  //GRATIFICACIONES Y RELIQUIDACIONES
			
			SQL += " select "+
			" rd.pwdcrutaf as Rut, "+ 
			" rd.pwdcdigaf as Dv, "+
			" rc.pwccapeaf as Apellidos, "+ 
			empresa + " as RUTE, "+
			convenio + " CONVENIO, "+
			fecha.substring(4, 6)+ " MES, "+
			fecha.substring(0, 4)+ " AÑO, "+
			" rc.pwccnomaf as Nombre, "+ 
			" case when rc.PWCCADIC1 IS NULL then 'M'   " +
		 	"		when rc.PWCCADIC1=' '  then  'M'  " +
		 	"		else  rc.PWCCADIC1 end as sexo, "+
			" rc.PWCCADIC2 as Nacionalidad,  "+ 
			" rc.PWCCSUCUR as sucursal, "+ 
			" '02' TipoPago, "+ 
			" rd.pwdcmesre*10000+rd.pwdcanore as periododesde, "+ 
			" rd.pwdcmesre*10000+rd.pwdcanore as periodohasta, "+ 
			" (select case left(coalesce(dd.pwdcentid, 'NULL'), 4)   "+ 
			"     when 'NULL' then 'SIP' "+ 
			"     when 'INP(' then 'INP' else 'AFP' end "+ 
			"    from pwdtad."+tabla+" as dd "+ 
			"     where dd.pwdcrutem = rd.pwdcrutem "+ 
			"         and dd.pwdcrutaf = rd.pwdcrutaf  "+ 
            "                     and dd.pwdcconve = rd.pwdcconve "+ 
            "                     and dd.pwdcmesre = rd.pwdcmesre "+ 
            "                     and dd.pwdcanore = rd.pwdcanore "+ 
            "                     and dd.pwdctipen = 'P') "+ 
			"  as RegimenPrevisional,  "+ 
			" '0' Tipotrabajador, "+ 
			" '30' as dias, "+ 
			" '00' TipoLinea, "+ 
			" '0' codigomovimientopersonal, "+ 
			" '' as MVfechadesde, "+ 
			" '' as MVfechahasta, "+ 
			" '' as Tramo , "+ 
			
			" (select dd.pwdcremim "+ 
			"    from pwdtad."+tabla+" as dd "+ 
			"     where dd.pwdcrutem = rd.pwdcrutem "+ 
			"         and dd.pwdcrutaf = rd.pwdcrutaf "+ 
            "                     and dd.pwdcconve = rd.pwdcconve "+ 
            "                     and dd.pwdcmesre = rd.pwdcmesre "+ 
            "                     and dd.pwdcanore = rd.pwdcanore "+ 
            "                     and dd.pwdctipen = 'P') "+ 
			"  as totalHaberes, "+ 
			
			" (select dd.pwdcremim "+ 
			"    from pwdtad."+tabla+" as dd "+ 
			"     where dd.pwdcrutem = rd.pwdcrutem "+ 
			"         and dd.pwdcrutaf = rd.pwdcrutaf "+ 
            "                     and dd.pwdcconve = rd.pwdcconve "+ 
            "                     and dd.pwdcmesre = rd.pwdcmesre "+ 
            "                     and dd.pwdcanore = rd.pwdcanore "+ 
            "                     and dd.pwdctipen = 'S') "+ 
			"  as totalHaberes2, "+ 
			
			" (select dd.pwdcremim "+ 
			"    from pwdtad."+tabla+" as dd "+ 
			"     where dd.pwdcrutem = rd.pwdcrutem "+ 
			"         and dd.pwdcrutaf = rd.pwdcrutaf "+ 
            "                     and dd.pwdcconve = rd.pwdcconve "+ 
            "                     and dd.pwdcmesre = rd.pwdcmesre "+ 
            "                     and dd.pwdcanore = rd.pwdcanore "+ 
            "                     and dd.pwdctipen = 'M') "+ 
			"  as totalHaberes3, "+ 
			
			" (select dd.pwdcremim "+ 
			"    from pwdtad."+tabla+" as dd "+ 
			"     where dd.pwdcrutem = rd.pwdcrutem "+ 
			"         and dd.pwdcrutaf = rd.pwdcrutaf "+ 
            "                     and dd.pwdcconve = rd.pwdcconve "+ 
            "                     and dd.pwdcmesre = rd.pwdcmesre "+ 
            "                     and dd.pwdcanore = rd.pwdcanore "+ 
            "                     and dd.pwdctipen = 'C') "+ 
			"  as totalHaberes4, "+ 
			
			
			" '0' as asignacionfamiliar, "+ 
			" '0' as asignacionfamiliarretroactiva, "+ 
			" '0' as ReintegroCargasfamiliares, "+ 
			" 'N' as SolicitudTrabajadorJoven, "+ 
			" (select case when POSITION('CUPRUM' in dd.PWDCENTID) <> 0 then 'CUPRUM' "+ 
			"     when POSITION('HABITAT' in dd.PWDCENTID) <> 0 then 'HABITAT' "+ 
			"     when POSITION('PROVIDA' in dd.PWDCENTID) <> 0 then 'PROVIDA' "+ 
			"     when POSITION('PLANVITAL' in dd.PWDCENTID) <> 0 then 'PLANVITAL' "+
			"     when POSITION('CAPITAL' in dd.PWDCENTID) <> 0 then 'CAPITAL' "+
			"     when POSITION('MODELO' in dd.PWDCENTID) <> 0 then 'MODELO' "+
			"     else '00' "+
			"     end "+
			"    from pwdtad."+tabla+" as dd "+
			"     where dd.pwdcrutem = rd.pwdcrutem "+
			"         and dd.pwdcrutaf = rd.pwdcrutaf  "+
            "                     and dd.pwdcconve = rd.pwdcconve "+
            "                     and dd.pwdcmesre = rd.pwdcmesre "+
            "                     and dd.pwdcanore = rd.pwdcanore "+
            "                     and dd.pwdctipen = 'P')  "+
			"  as entidad,  "+
			" (select dd.pwdcremim "+
			"    from pwdtad."+tabla+" as dd "+
			"     where dd.pwdcrutem = rd.pwdcrutem "+
			"         and dd.pwdcrutaf = rd.pwdcrutaf  "+
            "                     and dd.pwdcconve = rd.pwdcconve "+
            "                     and dd.pwdcmesre = rd.pwdcmesre "+
            "                     and dd.pwdcanore = rd.pwdcanore "+
            "                     and dd.pwdctipen = 'P')  "+
            "                      as rentaimponible, "+
            "             (select dd.PWDCMONCO "+
			"    from pwdtad."+tabla+" as dd "+
			"     where dd.pwdcrutem = rd.pwdcrutem "+
			"         and dd.pwdcrutaf = rd.pwdcrutaf  "+
            "                     and dd.pwdcconve = rd.pwdcconve "+
            "                     and dd.pwdcmesre = rd.pwdcmesre "+
            "                     and dd.pwdcanore = rd.pwdcanore "+
            "                     and dd.pwdctipen = 'P')   "+
			" as montocotizado,  "+
			" (select dd.pwdcmonco  "+
			" from PWDTAD."+tabla+" as dd "+
			" where dd.pwdcrutem = rd.pwdcrutem "+
			" and dd.pwdcrutaf = rd.pwdcrutaf "+
			" and dd.pwdcconve = rd.pwdcconve "+
			" and dd.pwdcmesre = rd.pwdcmesre "+
			" and dd.pwdcanore = rd.pwdcanore "+
			" and dd.pwdctipen = 'I') as CotizacionSegInvSobre,  "+
			" '0' as CuentaAhoVolAFP,  "+
			" '00,00' as PorcenCotiTrabPesado,  "+
			" '0' as CotiTrabPesado,  "+
			" '000' entidadAPV,  "+
			" '0'cotizacionAPV,  "+
			" '0' as depositoConvAPV,"+
			" '0' as rentaImpIPS, "+
			" '0' as cotizObligIPS, "+
			" '0' as desCargasFamiliares,  "+
			" (select case when POSITION('CRUZ BLANCA' in dd.PWDCENTID) <> 0 then 'CRUZ BLANCA'"+
			"     when POSITION('COLMENA' in dd.PWDCENTID) <> 0 then 'COLMENA'"+
			"     when POSITION('CONSALUD' in dd.PWDCENTID) <> 0 then 'CONSALUD'"+
			"     when POSITION('VIDA TRES' in dd.PWDCENTID) <> 0 then 'VIDA TRES'"+
			"     when POSITION('BANMEDICA' in dd.PWDCENTID) <> 0 then 'BANMEDICA'"+
			"     when POSITION('INP' in dd.PWDCENTID) <> 0 then 'INP'"+
			"     when POSITION('CHUQUICAMATA' in dd.PWDCENTID) <> 0 then 'CHUQUICAMATA'"+
			"     when POSITION('FERROSALUD' in dd.PWDCENTID) <> 0 then 'FERROSALUD'"+
			"     when POSITION('OPTIMA' in dd.PWDCENTID) <> 0 then 'OPTIMA'"+
			"     when POSITION('FUSAT' in dd.PWDCENTID) <> 0 then 'FUSAT'"+
			"     when POSITION('BANCO' in dd.PWDCENTID) <> 0 then 'BANCO'"+
			"     when POSITION('MAS VIDA' in dd.PWDCENTID) <> 0 then 'MAS VIDA'"+
			"     when POSITION('RIO BLANCO' in dd.PWDCENTID) <> 0 then 'RIO BLANCO'"+
			"     when POSITION('SAN LORENZO' in dd.PWDCENTID) <> 0 then 'SAN LORENZO'"+
			"     when POSITION('CRUZ DEL NORTE' in dd.PWDCENTID) <> 0 then 'CRUZ DEL NORTE'"+
			"     end as institucionSalud "+
			" from PWDTAD."+tabla+" as dd   "+
			" where dd.pwdcrutem = rd.pwdcrutem   "+
			" and dd.pwdcrutaf = rd.pwdcrutaf   "+
			" and dd.pwdcconve = rd.pwdcconve   "+
			" and dd.pwdcmesre = rd.pwdcmesre   "+
			" and dd.pwdcanore = rd.pwdcanore   "+
			" and dd.pwdctipen = 'S') as institucionSalud,  "+
			" (select dd.PWDCREMIM   "+
			" from PWDTAD."+tabla+" as dd   "+
			" where dd.pwdcrutem = rd.pwdcrutem   "+
			" and dd.pwdcrutaf = rd.pwdcrutaf   "+
			" and dd.pwdcconve = rd.pwdcconve   "+
			" and dd.pwdcmesre = rd.pwdcmesre   "+
			" and dd.pwdcanore = rd.pwdcanore   "+
			" and dd.pwdctipen = 'S') as rentaImpIsapre,  "+
			" '1' as Moneda,  "+
			
			
			
			" (select dd.pwdcmonco   "+
			" from PWDTAD."+tabla+" as dd   "+
			" where dd.pwdcrutem = rd.pwdcrutem   "+
			" and dd.pwdcrutaf = rd.pwdcrutaf   "+
			" and dd.pwdcconve = rd.pwdcconve   "+
			" and dd.pwdcmesre = rd.pwdcmesre   "+
			" and dd.pwdcanore = rd.pwdcanore   "+
			" and dd.pwdctipen = 'S') as cotizacionObl,  "+
			" '0' as cotizacionVol,  "+
			" (select case when POSITION('ARAUCANA' in dd.PWDCENTID) <> 0 then 'ARAUCANA' "+
			"     when POSITION('HEROES' in dd.PWDCENTID) <> 0 then 'HEROES' "+
			"     when POSITION('ANDES' in dd.PWDCENTID) <> 0 then 'ANDES' "+
			"     when POSITION('18 DE SEPTIEMBRE' in dd.PWDCENTID) <> 0 then '18 DE SEPTIEMBRE' "+
			"     when POSITION('GABRIELA MISTRAL' in dd.PWDCENTID) <> 0 then 'GABRIELA MISTRAL' "+
			"     else '00' "+
			"     end as codigoCC "+
			" from PWDTAD."+tabla+" as dd   "+
			" where dd.pwdcrutem = rd.pwdcrutem   "+
			" and dd.pwdcrutaf = rd.pwdcrutaf   "+
			" and dd.pwdcconve = rd.pwdcconve   "+
			" and dd.pwdcmesre = rd.pwdcmesre   "+
			" and dd.pwdcanore = rd.pwdcanore   "+
			" and dd.pwdctipen = 'C')  as codigoCC,  "+
			" (select dd.PWDCREMIM   "+
			" from PWDTAD."+tabla+" as dd   "+
			" where dd.pwdcrutem = rd.pwdcrutem   "+
			" and dd.pwdcrutaf = rd.pwdcrutaf   "+
			" and dd.pwdcconve = rd.pwdcconve   "+
			" and dd.pwdcmesre = rd.pwdcmesre   "+
			" and dd.pwdcanore = rd.pwdcanore   "+
			" and dd.pwdctipen = 'C') as imponibleCC,  "+
			" '0' as creditoCC,  "+
			" '0' as descuentoDentalCC,  "+
			" '0' as leasingCC,  "+
			" '0' as segurovidaCC,  "+
			" '0' as cargasCC,  "+
			" '0' as CotizacionCCAFNoAfi, "+
			" (select case when POSITION('ACHS' in dd.PWDCENTID) <> 0 then 'ACHS' "+
			"     when POSITION('CCHC' in dd.PWDCENTID) <> 0 then 'CCHC' "+
			"     when POSITION('SEGURIDAD DEL TRABAJO' in dd.PWDCENTID) <> 0 then 'SEGURIDAD DEL TRABAJO' "+
			"     else '00' "+
			"     end as entidad "+
			" from PWDTAD."+tabla+" as dd   "+
			" where dd.pwdcrutem = rd.pwdcrutem   "+
			" and dd.pwdcrutaf = rd.pwdcrutaf   "+
			" and dd.pwdcconve = rd.pwdcconve   "+
			" and dd.pwdcmesre = rd.pwdcmesre   "+
			" and dd.pwdcanore = rd.pwdcanore   "+
			" and dd.pwdctipen = 'M') as codigoMT,  "+
			" (select PWDCREMIM   "+
			" from PWDTAD."+tabla+" as diasT   "+
			" where diasT.pwdcrutem = rd.pwdcrutem   "+
			" and diasT.pwdcrutaf = rd.pwdcrutaf   "+
			" and diasT.pwdcconve = rd.pwdcconve   "+
			" and diasT.pwdcmesre = rd.pwdcmesre   "+
			" and diasT.pwdcanore = rd.pwdcanore   "+
			" and diasT.pwdctipen = 'M') as imponibleMT,  "+
			" (select pwdcmonco   "+
			" from PWDTAD."+tabla+" as diasT   "+
			" where diasT.pwdcrutem = rd.pwdcrutem   "+
			" and diasT.pwdcrutaf = rd.pwdcrutaf   "+
			" and diasT.pwdcconve = rd.pwdcconve   "+
			" and diasT.pwdcmesre = rd.pwdcmesre   "+
			" and diasT.pwdcanore = rd.pwdcanore   "+
			" and diasT.pwdctipen = 'M') as cotizacionMT,  "+
			
			
			
			" (select pwdcmonco   "+
			" from PWDTAD."+tabla+" as diasT   "+
			" where diasT.pwdcrutem = rd.pwdcrutem   "+
			" and diasT.pwdcrutaf = rd.pwdcrutaf   "+
			" and diasT.pwdcconve = rd.pwdcconve   "+
			" and diasT.pwdcmesre = rd.pwdcmesre   "+
			" and diasT.pwdcanore = rd.pwdcanore   "+
			" and diasT.pwdctipen = 'X') as aporteTrabajador,  "+
			" (select pwdcmonco   "+
			" from PWDTAD."+tabla+" as diasT   "+
			" where diasT.pwdcrutem = rd.pwdcrutem   "+
			" and diasT.pwdcrutaf = rd.pwdcrutaf   "+
			" and diasT.pwdcconve = rd.pwdcconve   "+
			" and diasT.pwdcmesre = rd.pwdcmesre   "+
			" and diasT.pwdcanore = rd.pwdcanore   "+
			" and diasT.pwdctipen = 'Y') as aporteEmpleador,  "+
			" '' as datosPagadorSubsidio, "+
			" 0 as ordenAPV,  "+
			" 0 as ordenMOV,  "+
			" 0 as numeroapv,  "+
			" 0 as numeroMOV, "+
			" 1 as ordentipo "+
//			" (select dd.pwdcmonco  "+
//			" from PWDTAD."+tabla+" as dd  "+
//			" where dd.pwdcrutem = rd.pwdcrutem  "+
//			" and dd.pwdcrutaf = rd.pwdcrutaf  "+
//			" and dd.pwdcconve = rd.pwdcconve  "+
//			" and dd.pwdcmesre = rd.pwdcmesre  "+
//			" and dd.pwdcanore = rd.pwdcanore  "+
//			" and dd.pwdctipen = 'P' and POSITION('Pensi' in dd.pwdcentid) <> 0 ) as cotizacionOblPENSION "+
			" from pwdtad."+tabla+" as rd    "+
			" 				join pwdtad.pwf6000 as rc " +
			"			on rd.pwdcrutaf = rc.pwccrutaf"+       
			"           and rd.pwdcconve = rc.pwccconve"+
			"           and rd.pwdcrutem = rc.pwccrutem"+
			"           and rd.pwdccdhol = rc.pwcccdhol "+
			"where rd.pwdcrutem = "+ empresa + 
			"  and rd.pwdcconve = "+ convenio +
			"  and rd.pwdcmesre = '"+ fecha.substring(4, 6)+ "'" + 
			"  and rd.pwdcanore = "+ fecha.substring(0, 4)+
			"  order by rut ";
			
		}
		SQL +=" ) with data ";	
		
		System.out.println(SQL);
		return SQL;
		
	}
	public ArrayList GerenaConsultaCabecera(String tabla, String empresa, String convenio, String periodoDesde, String periodoHasta, String holding, String proceso)
	{
		/**************************************/
		ArrayList lista = new ArrayList();
		
		Connection conexion = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			UtilPub util = new UtilPub();
			
			conexion = util.getConnection();
			
			stmt = conexion.createStatement();
			result = null;
			
			System.out.println("Apunto de ejecutar las query");
			
			String SQL = "";
			if (tabla.equals("TODOS")) {

				SQL += "SELECT PWDCRUTEM as code, 'PWF6100' as tabla, CONCAT(PWDCANORE,PWDCMESRE) as fecha,'Remuneraciones' as proceso,PWDCRUTEM as rut,PWDCDIGEM as digito,'' as rsocial,PWDCCONVE as convenio "+
				"FROM PWDTAD.PWF6100 " +
				" join PWDTAD.PWF6000 ON PWCCRUTEM = PWDCRUTEM " +
				" and pwdcrutaf = pwccrutaf  "+      
				"  and pwdcconve = pwccconve "+
				"WHERE CONCAT(PWDCANORE,PWDCMESRE) BETWEEN '"+periodoDesde+"' AND '"+periodoHasta+ "'"+
						" AND PWDCRUTEM IN("+empresa+") " +
						" AND PWDCCONVE IN("+convenio+") " +
						" AND PWDCCDHOL in("+holding+") " +
						//" AND PWDCTIPEN = 'P' "+
						" GROUP BY PWDCRUTEM,  PWDCCONVE, PWDCDIGEM, CONCAT(PWDCANORE,PWDCMESRE)" +
						//",PWCCRAZSO " +
						" UNION " +
				" SELECT PWDCRUTEM as code, 'PWF6101' as tabla, CONCAT(PWDCANORE,PWDCMESRE) as fecha,'Gratificaciones' as proceso,PWDCRUTEM as rut,PWDCDIGEM as digito,'' as rsocial,PWDCCONVE as convenio " +
				"FROM PWDTAD.PWF6101 " +
				" join PWDTAD.PWF6000 ON PWCCRUTEM = PWDCRUTEM " +
				" and pwdcrutaf = pwccrutaf  "+      
				"  and pwdcconve = pwccconve "+
				"where CONCAT(PWDCANORE,PWDCMESRE) BETWEEN '"+periodoDesde+"' AND '"+periodoHasta+ "'"+
						" AND PWDCRUTEM IN("+empresa+") " +
						"AND PWDCCONVE IN("+convenio+") " +
						"AND PWDCCDHOL in("+holding+") " +
						//" AND PWDCTIPEN = 'P' "+
						"GROUP BY PWDCRUTEM,  PWDCCONVE, PWDCDIGEM, CONCAT(PWDCANORE,PWDCMESRE)" +
						//",PWCCRAZSO  " +
						"UNION " +
				" SELECT PWDCRUTEM as code, 'PWF6105' as tabla, CONCAT(PWDCANORE,PWDCMESRE) as fecha,'Reliquidaciones' as proceso,PWDCRUTEM as rut,PWDCDIGEM as digito,'' as rsocial,PWDCCONVE as convenio " +
				"FROM PWDTAD.PWF6105 " +
				" join PWDTAD.PWF6000 ON PWCCRUTEM = PWDCRUTEM " +
				" and pwdcrutaf = pwccrutaf  "+      
				"  and pwdcconve = pwccconve "+
				"where CONCAT(PWDCANORE,PWDCMESRE) BETWEEN '"+periodoDesde+"' AND '"+periodoHasta+ "'"+
						" AND PWDCRUTEM IN("+empresa+") " +
						"AND PWDCCONVE IN("+convenio+") " +
						"AND PWDCCDHOL in("+holding+") " +
						//" AND PWDCTIPEN = 'P' "+
						"GROUP BY PWDCRUTEM,  PWDCCONVE, PWDCDIGEM, CONCAT(PWDCANORE,PWDCMESRE)" +
						//",PWCCRAZSO "+
						" ORDER BY FECHA DESC " ;
						
			}
			else
			{
				SQL += "SELECT PWDCRUTEM as code, '"+tabla+"' as tabla, CONCAT(PWDCANORE,PWDCMESRE) as fecha,'"+proceso.trim()+"' as proceso,PWDCRUTEM as rut,PWDCDIGEM as digito,'' as rsocial,PWDCCONVE as convenio " +
				"FROM PWDTAD."+tabla.trim()+" " +
						" join PWDTAD.PWF6000 ON PWCCRUTEM = PWDCRUTEM " +
						" and pwdcrutaf = pwccrutaf  "+      
						"  and pwdcconve = pwccconve "+
				"WHERE CONCAT(PWDCANORE,PWDCMESRE) BETWEEN '"+periodoDesde+"' AND '"+periodoHasta+ "'"+
						" AND PWDCRUTEM IN("+empresa+") " +
						" AND PWDCCONVE IN("+convenio+") " +
						" AND PWDCCDHOL in("+holding+") " +
						//" AND PWDCTIPEN = 'P' "+
						" GROUP BY PWDCRUTEM,  PWDCCONVE, PWDCDIGEM, CONCAT(PWDCANORE,PWDCMESRE)" +
						//",PWCCRAZSO " +
						" ORDER BY FECHA DESC " ;
			}
			
			System.out.println(SQL);
			result = stmt.executeQuery(SQL);
			
			int x = 0;
			while(result.next()){
				Map params = new HashMap();
				params.put("fecha",result.getObject("fecha").toString());
				params.put("proceso",result.getObject("proceso").toString());
				params.put("rut",result.getObject("rut").toString());
				params.put("digito",result.getObject("digito").toString());
				params.put("rsocial",result.getObject("rsocial").toString());
				params.put("convenio",result.getObject("convenio").toString());
				params.put("tabla",result.getObject("tabla").toString());
				params.put("holding",holding);
				params.put("fechaIni",periodoDesde);
				params.put("fechaFin",periodoHasta);
				params.put("index",String.valueOf(x));
				lista.add(params);
				x++;
			}
			
			result.close();
		} catch (Exception e) {
			System.out.println("error: "+e.getMessage());
		} finally
		{
			try{
                if (null != result){
                	result.close();
                }
                if (null != stmt){
                	stmt.close();
                }  
                if (null != conexion){
             	   	conexion.close();
                }  
            }catch(SQLException e){
            	System.out.println("error: "+ e);
            }
		}
		
		
		return lista;
	}

	public String getRazonSocial(String rut) {

		String razonSocial = "";
		Connection conexion = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			UtilPub util = new UtilPub();
			
			conexion = util.getConnection();
			
			stmt = conexion.createStatement();
			result = null;
			
			System.out.println("Apunto de ejecutar las query");
			
			String SQL = "select PWCCRAZSO from PWDTAD.PWF6000 t2 where t2.pwccrutem = "+ rut +" fetch first 1 rows only";
			
			result = stmt.executeQuery(SQL);
			
			int x = 0;
			while(result.next()){
				razonSocial = result.getString("PWCCRAZSO").trim();
			}
			
			result.close();
		} catch (Exception e) {
			System.out.println("error: "+e.getMessage());
		}finally
		{
			try{
                if (null != result){
                	result.close();
                }
                if (null != stmt){
                	stmt.close();
                }  
                if (null != conexion){
             	   	conexion.close();
                }  
            }catch(SQLException e){
            	System.out.println("error: "+ e);
            }
		}
		
		return razonSocial;
	}
	
	private String generaDetalleHomologado(String tabla, String tablaTemp) {

			String SQL = "";
			
				SQL += " select "+
				" temp.Rut, "+
				" temp.Dv, "+
				" temp.Apellidos, "+
				" temp.Nombre, "+
				" temp.sexo, "+
				" temp.Nacionalidad,  "+
				" temp.sucursal, "+
				" temp.TipoPago, "+
				" temp.periododesde, "+
				" temp.periodohasta, "+
				" temp.RegimenPrevisional, "+
				" temp.Tipotrabajador, "+
				" temp.TipoLinea, "+
				" temp.dias, ";
				
				if (tabla.equals("PWF6100")) {
					SQL+=" (select hom.CODIGODT "+
							" from pwdtad.homologaciondirecciontrabajo as hom"+
							" where hom.codigocaja = ''||temp.codigomovimientopersonal||'' "+
							"	and hom.tipo = 'MovimientoPersonal') as codigomovimientopersonal, "; //movimientopersonal
				}
				else
				{
					SQL+="'' as codigomovimientopersonal, "; //movimientopersonal
				}
				SQL +=" temp.MVfechadesde, "+
				" temp.MVfechahasta, "+
				" temp.Tramo , "+
				" temp.totalHaberes, "+
				" temp.totalHaberes2, "+
				" temp.totalHaberes3, "+
				" temp.totalHaberes4, "+
				
				" temp.asignacionfamiliar, "+
				" temp.asignacionfamiliarretroactiva, "+
				" temp.ReintegroCargasfamiliares, "+
				" temp.SolicitudTrabajadorJoven, "+
				"(select hom.CODIGODT from pwdtad.homologaciondirecciontrabajo as hom" +
				" where hom.codigocaja = ''||temp.entidad||''  "+
				" and hom.tipo = 'AFP') as entidad, "+     //entidad
				
				" temp.rentaimponible, "+
				" temp.montocotizado, "+
				" temp.CotizacionSegInvSobre, "+
				" temp.CuentaAhoVolAFP, "+
				" temp.PorcenCotiTrabPesado, "+
				" temp.CotiTrabPesado, " +
				
				" (select diasT.PWDCREMIM   "+
				" from PWDTAD."+tabla+" as diasT   "+
				" where diasT.pwdcrutem = temp.RUTE   "+
				" and diasT.pwdcrutaf = temp.Rut   "+
				" and diasT.pwdcconve = temp.CONVENIO   "+
				" and diasT.pwdcmesre = temp.MES   "+
				" and diasT.pwdcanore = temp.AÑO   "+
				" and diasT.pwdctipen = 'X') as RIseguroCesantiaX,  "+
				" (select diasT.PWDCREMIM   "+
				" from PWDTAD."+tabla+" as diasT   "+
				" where diasT.pwdcrutem = temp.RUTE   "+
				" and diasT.pwdcrutaf = temp.Rut  "+
				" and diasT.pwdcconve = temp.CONVENIO   "+
				" and diasT.pwdcmesre = temp.MES   "+
				" and diasT.pwdcanore = temp.AÑO   "+
				" and diasT.pwdctipen = 'Y') as RIseguroCesantiaY,  ";
				
				if (tabla.equals("PWF6100")) {
					SQL+=" (select hom.CODIGODT "+
							" from pwdtad.homologaciondirecciontrabajo as hom"+
							" where hom.codigocaja = ''||temp.entidadAPV||'' "+
							"	and hom.tipo = 'APV') as entidadAPV, ";  //entidad apv
				}
				else
				{
					SQL+="'000' as entidadAPV, "; //entidad apv
				}

				SQL +=" cotizacionAPV, "+
				" temp.depositoConvAPV,  "+
				" temp.rentaImpIPS,  "+
				" temp.cotizObligIPS,  "+
				" temp.desCargasFamiliares, "+
				
				"(select hom.CODIGODT from pwdtad.homologaciondirecciontrabajo as hom" +
				" where hom.codigocaja = ''||temp.institucionSalud||'' "+
				" and hom.tipo = 'SALUD') as institucionSalud, "+   //SALUD
				
				" temp.rentaImpIsapre, "+
				" temp.Moneda, "+
				" temp.cotizacionObl, "+
				" temp.cotizacionVol, "+
				
				"(select hom.CODIGODT from pwdtad.homologaciondirecciontrabajo as hom" +
				" where hom.codigocaja = ''||temp.codigoCC||'' "+
				" and hom.tipo = 'CCAF') as codigoCC, "+   //codigo cc

				" temp.imponibleCC, "+
				" temp.creditoCC, "+
				" temp.descuentoDentalCC, "+
				" temp.leasingCC, "+
				" temp.segurovidaCC, "+
				" temp.cargasCC, "+
				" temp.CotizacionCCAFNoAfi, "+
				
				"(select hom.CODIGODT from pwdtad.homologaciondirecciontrabajo as hom " +
				" where hom.codigocaja = ''||temp.codigoMT||'' "+
				" and hom.tipo = 'MUTUAL') as codigoMT, "+  //codigo mt
				
				" temp.imponibleMT, "+
				" temp.cotizacionMT, "+
				" temp.aporteTrabajador, "+
				" temp.aporteEmpleador, "+
				" (select hom.CODIGODT "+
							" from pwdtad.homologaciondirecciontrabajo as hom "+
							" where hom.codigocaja = ''||temp.datosPagadorSubsidio||'' "+
							"	and hom.tipo = 'PAGADORESSUBSIDIO') as datosPagadorSubsidio, "+  //entidad apv

				
				" temp.ordenAPV, "+
				" temp.ordenMOV, "+
				" temp.numeroapv, "+
				" temp.numeroMOV, "+
				" temp.ordentipo, "+
				" (select diasT.pwdcmonco  "+
				" from PWDTAD."+tabla+" as diasT   "+
				" where diasT.pwdcrutem = temp.RUTE   "+
				" and diasT.pwdcrutaf = temp.Rut  "+
				" and diasT.pwdcconve = temp.CONVENIO   "+
				" and diasT.pwdcmesre = temp.MES   "+
				" and diasT.pwdcanore = temp.AÑO   "+
				" and diasT.pwdctipen = 'P' and POSITION('Pensi' in diasT.pwdcentid) <> 0 ) as cotizacionOblPENSION "+

				" from QTEMP." + tablaTemp + " as temp "+
				" order by temp.rut, temp.ordentipo, temp.ordenapv, temp.ordenmov ";
	
			System.out.println(SQL);
			return SQL;
		
	}
}
