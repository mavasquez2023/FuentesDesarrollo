package etc;

import java.util.ArrayList;

import cl.laaraucana.botonpago.web.cobol.call.ConsultaFolioCall;
import cl.laaraucana.botonpago.web.cobol.vo.EntradaSalidaFolioVO;
import cl.laaraucana.botonpago.web.manager.ManagerCupon;
import cl.laaraucana.botonpago.web.utils.CompletaUtil;
import cl.laaraucana.botonpago.web.utils.Configuraciones;
import cl.laaraucana.botonpago.web.vo.CreditoVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycompcontheader.ClienteQueryCompContHeader;
import cl.laaraucana.botonpago.web.webservices.clientes.querycompcontheader.vo.EntradaCompContHeaderVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycompcontheader.vo.SalidaListaCompContHeaderVO;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		System.out.println(Configuraciones.getMainConfig("version"));

		System.out.println(CompletaUtil.llenaConCeros("2222", 3, true) + CompletaUtil.llenaConCeros("111222333", 9, true));

		ArrayList<String> l = new ArrayList<String>();

		l.add("77093710-8");
		l.add("76786600-3");
		l.add("79947970-2");
		l.add("99543430-K");
		l.add("88220100-7");
		l.add("70954900-6");
		l.add("5932604-K");
		l.add("76103006-K");
		l.add("70954900-6");
		l.add("89876100-2");
		l.add("96512070-K");
		l.add("86825900-0");
		l.add("94637000-2");
		l.add("96820170-0");
		l.add("89837300-2");
		l.add("79507100-8");
		l.add("85660800-K");
		l.add("84900800-5");
		l.add("96608820-6");
		l.add("76318540-0");
		l.add("88847600-8");
		l.add("82754300-4");
		l.add("76190379-9");
		l.add("76190379-9");
		l.add("96633340-5");
		l.add("96633340-5");
		l.add("89408400-6");
		l.add("71440700-7");
		l.add("71440700-7");
		l.add("98000500-3");
		l.add("99566580-8");
		l.add("80752900-5");
		l.add("76911070-4");
		l.add("96633340-5");
		l.add("88847600-8");
		l.add("59004250-1");
		l.add("91935000-8");
		l.add("76799970-4");
		l.add("76190379-9");
		l.add("89408400-6");
		l.add("71420700-8");
		l.add("96512070-K");
		l.add("86547900-K");
		l.add("79763150-7");
		l.add("96571870-2");
		l.add("96512070-K");
		l.add("79669570-6");
		l.add("76060048-2");
		l.add("77880500-6");
		l.add("79541370-7");
		l.add("82181000-0");
		l.add("3811250-3");
		l.add("88847600-8");
		l.add("88847600-8");
		l.add("79763150-7");
		l.add("96651910-K");
		l.add("96512070-K");
		l.add("76799970-4");
		l.add("96512070-K");
		l.add("96502230-9");
		l.add("88847600-8");
		l.add("95185000-4");
		l.add("96708790-4");
		l.add("94612000-6");
		l.add("3263750-7");
		l.add("89942300-3");
		l.add("76134941-4");
		l.add("70913100-1");
		l.add("93439000-8");
		l.add("76190379-9");
		l.add("99577050-4");
		l.add("99577050-4");
		l.add("88541600-4");
		l.add("81537600-5");
		l.add("79777010-8");
		l.add("61107272-4");
		l.add("96512070-K");
		l.add("3263750-7");
		l.add("85461500-9");
		l.add("77424630-4");
		l.add("94226000-8");
		l.add("82392600-6");
		l.add("76134946-5");
		l.add("70954900-6");
		l.add("12676675-0");
		l.add("79517410-9");
		l.add("3263750-7");
		l.add("70017820-K");
		l.add("96502230-9");
		l.add("96570320-9");
		l.add("89654800-K");
		l.add("76106380-4");
		l.add("79544160-3");
		l.add("96502230-9");
		l.add("95185000-4");
		l.add("85188400-9");
		l.add("94612000-6");
		l.add("96755580-0");
		l.add("89864000-0");
		l.add("76068955-6");
		l.add("99577050-4");
		l.add("96588920-5");
		l.add("87719500-7");
		l.add("77486490-3");
		l.add("96671750-5");
		l.add("96592190-7");
		l.add("80314700-0");
		l.add("78015390-3");
		l.add("76134941-4");
		l.add("5347196-K");
		l.add("61107272-4");
		l.add("92658000-0");
		l.add("78054070-2");
		l.add("7052149-0");
		l.add("96993240-7");
		l.add("78406360-7");
		l.add("78054070-2");
		l.add("96541050-3");
		l.add("96500010-0");
		l.add("78482790-9");
		l.add("80895500-8");
		l.add("92658000-0");
		l.add("76819580-3");
		l.add("5143281-9");
		l.add("96704160-2");
		l.add("78043340-K");
		l.add("88723500-7");
		l.add("88486800-9");
		l.add("96985130-K");
		l.add("76190379-9");
		l.add("80899200-0");
		l.add("96545640-6");
		l.add("94760000-1");
		l.add("87710000-6");
		l.add("76106310-3");
		l.add("76190379-9");
		l.add("86740700-6");
		l.add("96608820-6");
		l.add("79507100-8");
		l.add("78865110-4");
		l.add("83551200-2");
		l.add("96608820-6");
		l.add("78280280-1");
		l.add("96500010-0");
		l.add("71545100-K");
		l.add("5455760-4");
		l.add("96500010-0");
		l.add("78401510-6");
		l.add("96541050-3");
		l.add("84017600-2");
		l.add("76106380-4");
		l.add("86547900-K");
		l.add("96572560-1");
		l.add("79562220-9");
		l.add("96500010-0");
		l.add("82623500-4");
		l.add("95492000-3");
		l.add("79773780-1");
		l.add("97023000-9");
		l.add("92035000-3");
		l.add("79559220-2");
		l.add("96565250-7");
		l.add("86740700-6");
		l.add("76245250-2");
		l.add("78430190-7");
		l.add("87747200-0");
		l.add("76106380-4");
		l.add("79940780-9");
		l.add("92717000-0");
		l.add("96500010-0");
		l.add("5795954-1");
		l.add("96500010-0");
		l.add("79901710-5");
		l.add("78062300-4");
		l.add("80895700-0");
		l.add("84472400-4");
		l.add("76211240-K");
		l.add("76055936-9");
		l.add("76003228-K");
		l.add("93685000-6");
		l.add("8106826-7");
		l.add("79982490-6");
		l.add("77874460-0");
		l.add("56025760-0");
		l.add("86701700-3");
		l.add("79674310-7");
		l.add("80947300-7");
		l.add("76012833-3");
		l.add("78446230-7");
		l.add("96500010-0");
		l.add("79518100-8");
		l.add("79642560-1");
		l.add("9347082-6");
		l.add("92191000-2");
		l.add("81312600-1");
		l.add("85861400-7");
		l.add("76002623-9");
		l.add("82067200-3");
		l.add("82031800-5");
		l.add("88983600-8");
		l.add("83187800-2");
		l.add("78646820-5");
		l.add("77372150-5");
		l.add("96655580-7");
		l.add("94483000-6");
		l.add("4535121-1");
		l.add("80895700-0");
		l.add("52001386-5");
		l.add("96672710-1");
		l.add("94760000-1");
		l.add("87841800-K");
		l.add("97032000-8");
		l.add("77650070-4");
		l.add("96608820-6");
		l.add("78396770-7");
		l.add("6924762-8");
		l.add("96717640-0");
		l.add("79904920-1");
		l.add("88810100-4");
		l.add("94927000-9");
		l.add("87717900-1");
		l.add("79674310-7");
		l.add("6612273-5");
		l.add("94760000-1");
		l.add("76190379-9");
		l.add("84182700-7");
		l.add("81695500-9");
		l.add("76091924-1");
		l.add("76134946-5");
		l.add("77115570-7");
		l.add("81624000-K");
		l.add("96711000-0");
		l.add("84760600-2");
		l.add("78099680-3");
		l.add("96529850-9");
		l.add("91237000-3");
		l.add("78570410-K");
		l.add("94760000-1");
		l.add("78192530-6");
		l.add("96717640-0");
		l.add("96682740-8");
		l.add("79535900-1");
		l.add("88599000-2");
		l.add("94760000-1");
		l.add("76134941-4");
		l.add("94483000-6");
		l.add("96506670-5");
		l.add("91237000-3");
		l.add("76833720-9");
		l.add("86464900-9");
		l.add("7113104-1");
		l.add("96608660-2");
		l.add("82073800-4");
		l.add("84865000-5");
		l.add("96583070-7");
		l.add("93178000-K");
		l.add("79992330-0");
		l.add("96752390-9");
		l.add("76190379-9");
		l.add("99566580-8");
		l.add("96666670-6");
		l.add("77959210-3");
		l.add("96583070-7");
		l.add("79500510-2");
		l.add("93734000-1");
		l.add("76245250-2");
		l.add("96512070-K");
		l.add("85208700-5");
		l.add("76190379-9");
		l.add("91237000-3");
		l.add("86269900-9");
		l.add("96822190-6");
		l.add("78892920-K");
		l.add("96671640-1");
		l.add("84021000-6");
		l.add("86269900-9");
		l.add("78884400-K");
		l.add("96666670-6");
		l.add("84644700-8");
		l.add("69200800-6");
		l.add("93685000-6");
		l.add("4238225-6");
		l.add("71293900-1");
		l.add("96903690-8");
		l.add("79535900-1");
		l.add("79609100-2");
		l.add("89538000-8");
		l.add("4838091-3");
		l.add("5411287-4");
		l.add("78790080-1");
		l.add("96752390-9");
		l.add("6425163-5");
		l.add("80895700-0");
		l.add("81537600-5");
		l.add("96570670-4");
		l.add("6653390-5");
		l.add("77185930-5");
		l.add("93129000-2");
		l.add("70387900-4");
		l.add("76257098-K");

		EntradaCompContHeaderVO entrada = new EntradaCompContHeaderVO();
		ClienteQueryCompContHeader mg = new ClienteQueryCompContHeader();
		for (String asd : l) {
			entrada.setFlagTipoCredito("1");
			entrada.setFlagTipoCredito(asd);
			SalidaListaCompContHeaderVO s = (SalidaListaCompContHeaderVO) mg.call(entrada);
			if (s.getCodigoError().equals("3") && s.getListaCreditos().size() > 0) {
				System.out.println(asd);
			}
		}

		//		RecuperacionCall recuperaAs = new RecuperacionCall();
		//		EntradaSalidaRecuperaVO entrada = new EntradaSalidaRecuperaVO();
		//		
		//		entrada.setOfipro("013");
		//		entrada.setCrefol("000376297");
		//		entrada.setTipocr("0");
		//		entrada.setFoltes("0030043350");
		//		entrada.setMonto("00487407");
		//		entrada.setCodError("0");
		//		
		//		entrada = recuperaAs.recuperaCredito(entrada);
		//		
		//		System.out.println(entrada.getOfipro());
		//		System.out.println(entrada.getCrefol());
		//		System.out.println(entrada.getMonto());
		//		System.out.println(entrada.getTipocr());
		//		System.out.println(entrada.getFoltes());
		//		System.out.println(entrada.getCodError());
		//		
		//	
		//		String a = "1";
		//		System.out.println(a);
		//		a = Util.rellenarCampos(a, 1, "0");
		//		System.out.println(a);

	}

	public void creaCupon() throws Exception {
		CreditoVO credito = new CreditoVO();
		credito.setOrigen("A");
		credito.setOperacion("123-123123123");
		credito.setPorcentCondGravamen("1");
		credito.setPorcentCondGastoCob("1");// porCondGas
		credito.setSumaGravamenConCond("1");// valCondGra
		credito.setGastoCobranzaConCond("1");// valCondGas
		credito.setSumaGravamenes("1");// valGrava
		credito.setGastosCobranza("1");// valGastos
		credito.setTotalPagar("1");// mtoPagar
		ManagerCupon.crearCuponDePago(credito, "16502940-2", "L", "");
	}

	public EntradaSalidaFolioVO getFolio() {
		ConsultaFolioCall consulta = new ConsultaFolioCall();
		EntradaSalidaFolioVO entradaSalida = new EntradaSalidaFolioVO();
		entradaSalida.setCodigo("1"); //seberia ser constante
		try {
			entradaSalida = consulta.consultarFolio(entradaSalida);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entradaSalida;
	}

}
