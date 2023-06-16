package cl.laaraucana.simat.documentos.JasperReport;

import java.awt.image.BufferedImage;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class CompletadorCupon {

	public CuponPagoVO completarCuponPago() {

		CuponPagoVO cp = new CuponPagoVO();

		cp.setCliente(this.getDatosCliente());
		cp.setValores(this.getDatosValores());
		cp.setRegistro(this.getDatosRegistro());

		return cp;
	}

	private ClienteVO getDatosCliente() {
		ClienteVO cliente = new ClienteVO();
		cliente.setCiudad("santiago");
		cliente.setFecha_emision("xx-xx-xxxx");
		cliente.setNombre("userTest");
		cliente.setRegion("metropolitana");
		cliente.setRut("11111111-k");
		cliente.setTelefono("32-0000000");
		return cliente;
	}

	private ValoresVO getDatosValores() {
		ValoresVO valores = new ValoresVO();
		valores.setCapital("000000000000000");
		valores.setDescuentos("000000000000000");
		valores.setGastos_cobranza("000000000000000");
		valores.setInteres("000000000000000");
		valores.setSeguros("000000000000000");
		valores.setTotal_pagar("000000000000000");

		return valores;
	}

	private RegistroCuponVO getDatosRegistro() {
		RegistroCuponVO registro = new RegistroCuponVO();
		registro.setCodeBar(this.getCodeBarImage());
		registro.setFecha("xx-xx-xxxx");
		registro.setFirma("firmaTest");
		registro.setHora("--:--:--");
		return registro;
	}

	private BufferedImage getCodeBarImage() {

		BufferedImage imagen2 = null;
		try {
			Barcode barcode;

			barcode = BarcodeFactory.createCode39("16Z-26066-00", false);
			barcode.setDrawingText(false);
			barcode.setBarWidth(2);
			barcode.setBarHeight(50);

			imagen2 = BarcodeImageHandler.getImage(barcode);

			// Necesitamos un canal de salida donde escribir la imajen
			//FileOutputStream fos = new FileOutputStream("c:/mybarcode3950x2clabel.jpg");

			//Permite que la utilidad de imajenes de Barbecue haga todo el trabajo sucio
			//BarcodeImageHandler.writeJPEG(barcode, fos);
			//BarcodeImageHandler.outputBarcodeAsJPEGImage(barcode, fos);
			//BarcodeImageHandler.outputBarcodeAsJPEGImage(barcode, fos);

		} catch (Exception e) {
			System.out.println("CATCH: " + e);
		}

		return imagen2;
	}
}
