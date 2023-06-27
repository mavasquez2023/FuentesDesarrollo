package cl.araucana.cp.utils;

import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.pdf.publishers.PDFPublisher;
import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishOptions;
import cl.araucana.pdf.publishers.Publisher;
import cl.araucana.pdf.publishers.PublisherManager;
import cl.araucana.pdf.publishers.Scope;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;

public class PublicadorPDF
{
	static Logger logger = Logger.getLogger(PublicadorPDF.class);

	/*
	 * se recibe una lista de indices y una lista de rutas para acceder a archivos a publicar.
 	 * se necesitan los parametros:
	 *  - publisherName,
	 *  - docTypeName,
	 *  - idLogPDF,
	 *  - periodo,
	 */
	public static void publica(ParametrosHash paramHash, List indices, List pathNames) throws Exception
	{
		
		PDFPublisher pdfPublisher = null;
		int result = 1;
		boolean commit = false;

		if (indices == null || pathNames == null || indices.size() != pathNames.size())
			throw new Exception("parametros indices/pathNames invalidos");
		int ndate = Scope.convertToNDate(paramHash.get("" + Constants.PARAM_PERIODO) + "01");
		Scope replaceModeScope = new Scope("Periodo = " + ndate);
		logger.debug("periodo:" + paramHash.get("" + Constants.PARAM_PERIODO) + "::");
		System.out.println("publisher:" + paramHash.get("" + Constants.PARAM_PUBLISHER_NAME) + "::");

		try
		{
			PublisherManager publisherManager = PublisherManager.getInstance();
			Publisher publisher = publisherManager.getPublisher(paramHash.get("" + Constants.PARAM_PUBLISHER_NAME));
			if (publisher == null)
				throw new Exception("publicador desconocido:'" + paramHash.get("" + Constants.PARAM_PUBLISHER_NAME) + "'");

			PublishOptions publishOptions = new PublishOptions();
			publishOptions.setPublisher(publisher);
			publishOptions.setBatchMode(true);
			publishOptions.setPartitioned(false);
			publishOptions.setCompressed(true);
			publishOptions.setReplaceMode(PublishOptions.MODE_REPLACE);
			publishOptions.setLogged(true);
			publishOptions.setLogID(paramHash.get("" + Constants.PARAM_ID_LOG_PDF));

			pdfPublisher = PDFPublisher.newPDFPublisher(paramHash.get("" + Constants.PARAM_DOC_TYPE_NAME_PDF), 0, publishOptions);

			/*
			 * Defines replace mode scope to control duplicated docIDs.
			 */
			pdfPublisher.setReplaceModeScope(replaceModeScope);

			/*
			 * Initiates a new publication transaction.
			 */
			pdfPublisher.reset();

			for (int i = 0; i < indices.size(); i++)
			{
				String docIndex = (String)indices.get(i);
				String docPathName = (String)pathNames.get(i);

				logger.debug("Publishing " + "\"" + docIndex + "\" -> " + docPathName + " ...\n");
				pdfPublisher.publish(docIndex, docPathName);
			}

			commit = true;
		} catch (Exception e)
		{
			logger.error("error publicando PDF", e);
		} finally
		{
			if (pdfPublisher != null)
			{
				try
				{
					if (commit)
					{
						pdfPublisher.flush();
						logger.info("Publication OK.");
					} else
					{
						pdfPublisher.discard();
						logger.error("Publication FAILED.");
						result = -1;
					}
					pdfPublisher.close();
				} catch (PDFPublisherException e)
				{
					logger.error("error publicando PDF", e);
					result = -1;
				}
			}
			if (result != 1)
				throw new Exception("error durante la publicacion");
		}
	}
}
