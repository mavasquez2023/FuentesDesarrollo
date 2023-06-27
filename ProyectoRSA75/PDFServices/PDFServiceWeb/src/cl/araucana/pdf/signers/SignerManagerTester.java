

/*
 * @(#) SignerManagerTester.java    1.0 22-06-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.signers;


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
 *            <TD> 22-06-2008 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> AUTHOR <BR> EMAIL </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author AUTHOR (EMAIL)
 *
 * @version 1.0
 */
public class SignerManagerTester {

	public static void main(String[] args) throws Exception {

		SignerManager signerManager = SignerManager.getInstance();

		Signer signer = null;

		if (args.length == 0) {
			signer = signerManager.getDefaultSigner();
		} else {
			signer = signerManager.getSigner(args[0]);
		}

		if (signer == null) {
			System.out.println("Cannot get signer.");

			System.exit(1);
		}

		System.out.println("signer: " + signer.getName());

		SignOptions options = new SignOptions();

		options.setSigner(signer);

		PDFSigner.newPDFSigner(options);
	}
}
