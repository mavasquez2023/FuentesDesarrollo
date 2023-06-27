package cl.araucana.cp.presentation.mgr;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.core.util.AbsoluteDateTime;
import cl.araucana.core.util.Padder;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DescriptorNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EnvioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.beans.DocumentoVO;
import cl.araucana.cp.hibernate.dao.ConvenioDAO;
import cl.araucana.cp.hibernate.dao.EmpresaDAO;
import cl.araucana.cp.hibernate.dao.EnvioDAO;
import cl.araucana.cp.hibernate.dao.NominaDAO;
import cl.araucana.cp.receipt.AcceptedFile;
import cl.araucana.cp.receipt.NominaCheckException;
import cl.araucana.cp.receipt.NominaChecker;
import cl.araucana.cp.receipt.ReceiptReport;
import cl.araucana.cp.receipt.RejectedFile;

/*
 * @(#) ReceptorMgr.java 1.31 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * @author ccostagliola
 * 
 * @version 1.31
 */
public class ReceptorMgr
{
	private EnvioDAO envioDao;
	private NominaDAO nominaDao;
	private EmpresaDAO empresaDao;
	private ConvenioDAO convenioDao;
	private ComprobanteMgr comprobanteMgr;
	private ParametroMgr parametroMgr;
	private MapeosMgr mapeosMgr; 

	private NominaChecker nominaChecker = NominaChecker.getInstance();
	private EnvioVO envio;
	private ReceiptReport receiptReport;
	private static Logger logger = Logger.getLogger(ReceptorMgr.class);

	public ReceptorMgr(Session session)
	{
		this.envioDao = new EnvioDAO(session);
		this.nominaDao = new NominaDAO(session);
		this.empresaDao = new EmpresaDAO(session);
		this.convenioDao = new ConvenioDAO(session);
		this.comprobanteMgr = new ComprobanteMgr(session);
		this.parametroMgr = new ParametroMgr(session);
		this.mapeosMgr = new MapeosMgr(session);
	}

	/**
	 * guarda nomina preceso
	 * 
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param idEnvio
	 * @param nombre
	 * @param CRC
	 * @throws DaoException
	 * @throws Exception
	 */
	public void guardaNominaProceso(int rutEmpresa, int idConvenio, char tipoProceso, int idEnvio, String nombre, long CRC) throws DaoException, Exception
	{
		try
		{
			this.comprobanteMgr.anulaFolioByComprobante(tipoProceso, idConvenio, rutEmpresa);
		} catch (Exception e)
		{
			logger.error("problemas al anular folio", e);
			throw new Exception("problemas al anular folio");
		}
		if (tipoProceso == 'R')
			this.nominaDao.guardaNominaProceso(new NominaREVO(idConvenio, rutEmpresa, Constants.EST_NOM_EN_PROCESO, idEnvio, nombre, new Timestamp(System.currentTimeMillis()), CRC));
		else if (tipoProceso == 'G')
			this.nominaDao.guardaNominaProceso(new NominaGRVO(idConvenio, rutEmpresa, Constants.EST_NOM_EN_PROCESO, idEnvio, nombre, new Timestamp(System.currentTimeMillis()), CRC));
		else if (tipoProceso == 'A')
			this.nominaDao.guardaNominaProceso(new NominaRAVO(idConvenio, rutEmpresa, Constants.EST_NOM_EN_PROCESO, idEnvio, nombre, new Timestamp(System.currentTimeMillis()), CRC));
		else if (tipoProceso == 'D')
			this.nominaDao.guardaNominaProceso(new NominaDCVO(idConvenio, rutEmpresa, Constants.EST_NOM_EN_PROCESO, idEnvio, nombre, new Timestamp(System.currentTimeMillis()), CRC));
	}

	/**
	 * guarda descriptor
	 * 
	 * @param nombreNomina
	 * @param rutEmpresa
	 * @param idRechazo
	 * @param normalSize
	 * @param comprimidoSize
	 * @throws DaoException
	 */
	public void guardaDescriptor(String nombreNomina, int rutEmpresa, int idRechazo, int normalSize, int comprimidoSize) throws DaoException
	{
		int idConvenio = (nombreNomina.lastIndexOf(".") != -1 ? new Integer(nombreNomina.substring(nombreNomina.lastIndexOf(".") + 2)).intValue() : 0);

		DescriptorNominaVO descriptor = new DescriptorNominaVO();
		descriptor.setRutEmpresa(rutEmpresa);
		descriptor.setIdEnvio(this.envio.getId());
		descriptor.setIdConvenio(idConvenio);
		descriptor.setTipoProceso(nombreNomina.toUpperCase().charAt(nombreNomina.lastIndexOf(".") + 1));
		descriptor.setIdRechazo(idRechazo);
		descriptor.setIdGrupoConvenio(0);
		descriptor.setNormalSize(normalSize);
		descriptor.setComprimidoSize(comprimidoSize);
		descriptor.setNumRegistros(0);
		this.envioDao.guardaDescriptor(descriptor);
	}

	/**
	 * guarda documento
	 * 
	 * @param doc
	 * @throws DaoException
	 */
	public void guardaDocumento(DocumentoVO doc) throws DaoException
	{
		this.envioDao.guardaDocumento(doc);
	}

	/**
	 * recibir envio
	 * 
	 * @param request
	 * @param enviador
	 * @return estado del envio recibido, -1 en caso de ocurrir algun problema
	 * @throws Exception
	 */
	public int recibirEnvio(HttpServletRequest request, PersonaVO enviador) throws Exception
	{
		logger.info("ReceptorMgr:recibirEnvio");
		try
		{
			java.util.Date d = new java.util.Date();
			//this.envio = new EnvioVO(0, 0, enviador.getIdPersona().intValue(), new Timestamp(d.getTime()), 0, 0, 0);
			this.envio = new EnvioVO(0, 0, enviador.getIdPersona().intValue(), new Timestamp(d.getTime()), 0, 0, 0, request.getRemoteAddr());
			this.envioDao.guardaEnvio(this.envio);
			processRequest(request, enviador.getIdPersona().intValue());

			this.envio.setDuracion((int) this.receiptReport.getServiceTime());

			logger.info("\n\nReceptorMgr:recibirEnvio:enviador:" + enviador.getIdPersona().intValue() + "::");
			this.envio.setIdEncargado(enviador.getIdPersona().intValue());
			this.envio.setIdEstado(this.receiptReport.getStatus());
			this.envio.setNumAceptadas(this.receiptReport.getNAcceptedFiles());
			this.envio.setNumNominas(this.receiptReport.getNFilesReceived());

			this.envioDao.guardaEnvio(this.envio);
			return this.envio.getIdEstado();

		} catch (DaoException e)
		{
			this.receiptReport.setStatus(Constants.EST_RECH_REGISTRO_EN_BD, (String) Constants.ESTADO_ENVIO.get("" + Constants.EST_RECH_REGISTRO_EN_BD));
			if (this.envio != null) {
				String error = e.getMessage();
				 //En caso de que el detalle de la excepción sea más grande que el tamaño del campo, se corta. 
				if (error.length() > 600)
					error.substring(0, 599);
				this.envio.setExcepcion(error);
				this.envioDao.guardaEnvio(this.envio);
			}
			return -1;
		} catch (Exception e)
		{
			logger.error("Problemas al recibir el envio", e);
			if (this.envio != null) {
				String error = e.getMessage();
				 //En caso de que el detalle de la excepción sea más grande que el tamaño del campo, se corta. 
				if (error.length() > 600)
					error.substring(0, 599);
				this.envio.setExcepcion(error);
				this.envioDao.guardaEnvio(this.envio);
			}
			return -1;
		}
	}

	/**
	 * process
	 * 
	 * @param request
	 * @param enviador
	 * @throws DaoException
	 */
	private void processRequest(HttpServletRequest request, int idEnviador) throws DaoException, Exception
	{
		long ti = System.currentTimeMillis();
		String contentType = request.getContentType();
		int contentLength = request.getContentLength();

		logger.info("NominasReceiver processRequest:");
		logger.info("    contentType   = " + contentType);
		logger.info("    contentLength = " + contentLength);

		if (contentType == null || contentLength == 0)
			return;

		ServletInputStream input = null;
		this.receiptReport = new ReceiptReport();

		this.receiptReport.setReceiptNo(getIdEnvio());
		this.receiptReport.setSender(Utils.formatRut(idEnviador));
		this.receiptReport.setReceived(new AbsoluteDateTime());

		try
		{
			input = request.getInputStream();
		} catch (IOException e)
		{
			this.receiptReport.setServiceTime(System.currentTimeMillis() - ti);
			this.receiptReport.setStatus(1, (String) Constants.ESTADO_ENVIO.get("" + 1));
			logger.warn("Problemas recibiendo archivo", e);
			return;
		}

		try
		{
			receiveZippedData(input, contentLength, idEnviador);
		} catch (DaoException e)
		{
			this.receiptReport.setStatus(Constants.EST_RECH_REGISTRO_EN_BD, (String) Constants.ESTADO_ENVIO.get("" + Constants.EST_RECH_REGISTRO_EN_BD));
			throw e;
		} catch (Exception e)
		{
			this.receiptReport.setStatus(Constants.EST_RECH_REGISTRO_EN_BD, (String) Constants.ESTADO_ENVIO.get("" + Constants.EST_RECH_REGISTRO_EN_BD));
			throw e;
		}

		this.receiptReport.setServiceTime(System.currentTimeMillis() - ti);

		logReceiptReport();
		request.setAttribute("receiptReport", this.receiptReport);
		request.setAttribute("methodName", "Dispatcher");
		request.setAttribute("returnURL", "zipped.js");
	}

	/**
	 * log receipt report
	 * 
	 */
	private void logReceiptReport()
	{
		logger.info("#Envio : " + Padder.lpad(this.receiptReport.getReceiptNo(), 8, '0'));

		logger.info("Remitente : " + this.receiptReport.getSender());
		logger.info("Recibido : " + this.receiptReport.getReceived());

		logger.info("Tiempo de Servicio : " + this.receiptReport.getServiceTime() + " ms");

		int status = this.receiptReport.getStatus();

		if (status == 0)
			logger.info("Estado : OK");
		else
			logger.info("Estado : ERROR " + "[" + this.receiptReport.getStatusMessage() + "]");

		logger.info("#Nominas Recibidas : " + this.receiptReport.getNFilesReceived());

		List acceptedNominas = this.receiptReport.getAcceptedFiles();
		logger.info("Nominas Aceptadas [" + acceptedNominas.size() + "]:");
		for (int i = 0; i < acceptedNominas.size(); i++)
		{
			AcceptedFile acceptedNomina = (AcceptedFile) acceptedNominas.get(i);
			logger.info("\t" + acceptedNomina.getFileName() + " " + acceptedNomina.getComment());
		}

		List rejectedNominas = this.receiptReport.getRejectedFiles();
		logger.info("Nominas Rechazadas [" + rejectedNominas.size() + "]:");
		for (int i = 0; i < rejectedNominas.size(); i++)
		{
			RejectedFile rejectedNomina = (RejectedFile) rejectedNominas.get(i);
			logger.info("\t" + rejectedNomina.getFileName() + "\t" + rejectedNomina.getReason());
		}
	}

	/**
	 * receive zipped data
	 * 
	 * @param input
	 * @param contentLength
	 * @throws DaoException
	 */
	private void receiveZippedData(ServletInputStream input, int contentLength, int idEnviador) throws DaoException
	{
		try
		{
			logger.info("receiveZippedData");
			byte[] zippedData = new byte[contentLength];
			int pending = contentLength;
			int offset = 0;
			int size;
			while (pending > 0 && (size = input.read(zippedData, offset, pending)) > 0)
			{
				pending -= size;
				offset += size;
			}

			if (pending > 0)
			{
				this.receiptReport.setStatus(Constants.EST_RECH_DATA_CORRUPTA, (String) Constants.ESTADO_ENVIO.get("" + Constants.EST_RECH_DATA_CORRUPTA));
				return;
			}
			unzip(zippedData, idEnviador);
			if (this.receiptReport.getNAcceptedFiles() < 1)
				this.receiptReport.setStatus(Constants.EST_RECH_RECEPCION, (String) Constants.ESTADO_ENVIO.get("" + Constants.EST_RECH_RECEPCION));
			else
				this.receiptReport.setStatus(0);
		} catch (DaoException e)
		{
			this.receiptReport.setStatus(Constants.EST_RECH_REGISTRO_EN_BD, (String) Constants.ESTADO_ENVIO.get("" + Constants.EST_RECH_REGISTRO_EN_BD));
			throw e;
		} catch (IOException e)
		{
			this.receiptReport.setStatus(Constants.EST_RECH_ID_INVALIDO, (String) Constants.ESTADO_ENVIO.get("" + Constants.EST_RECH_ID_INVALIDO));
		} catch (Exception e)
		{
			this.receiptReport.setStatus(Constants.EST_RECH_RECEPCION, (String) Constants.ESTADO_ENVIO.get("" + Constants.EST_RECH_RECEPCION));
		} finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				} catch (IOException e)
				{
				}
			}
		}
	}
	
	/**
	 * un zip
	 * 
	 * @param zippedData
	 * @throws DaoException
	 */
	private void unzip(byte[] zippedData, int idEnviador) throws DaoException, Exception
	{
		ByteArrayInputStream input = null;
		ZipInputStream zipInput = null;
		ZipEntry zipEntry = null;
		List zipEntries = new ArrayList();
		HashMap names = new HashMap();
		String idNomina = "";
		String nombreNomina = "";
		int rutEmpresa = 0;
		String idsTiposNominas = this.nominaDao.getIdsTiposNominas();
		try
		{
			input = new ByteArrayInputStream(zippedData);
			zipInput = new ZipInputStream(input);

			while ((zipEntry = zipInput.getNextEntry()) != null)
			{
				zipInput.closeEntry();
				zipEntries.add(zipEntry);
			}

			zipInput.close();
			zipInput = null;

			input.close();
			input = null;

			input = new ByteArrayInputStream(zippedData);
			zipInput = new ZipInputStream(input);

			int nEntry = 0;

			while ((zipEntry = zipInput.getNextEntry()) != null)
			{
				zipEntry = (ZipEntry) zipEntries.get(nEntry++);
				if (zipEntry.isDirectory())
				{
					zipInput.closeEntry();
					this.receiptReport.setStatus(Constants.EST_RECH_OBTENCION_ARCHIVO, (String) Constants.ESTADO_ENVIO.get("" + Constants.EST_RECH_OBTENCION_ARCHIVO));
					continue;
				}
				String name = zipEntry.getName();
				if (name == null || "".equals(name))
				{
					zipInput.closeEntry();
					this.receiptReport.addRejectedFile(idNomina, name, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_OBTENCION_INFO_ARCHIVO));
					continue;
				}

				name = decodeZipEntryName(name);//formato: rutEmpresa/nombreArchivo.T00
				String[] tokens = name.split("/");

				if (tokens.length != 2)
				{
					zipInput.closeEntry();
					this.receiptReport.addRejectedFile(idNomina, name, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_OBTENCION_INFO_ARCHIVO));
					continue;
				}

				try
				{
					rutEmpresa = new Integer(tokens[0]).intValue();
					if (this.empresaDao.getEmpresa(rutEmpresa) == null)//se verifica que la empresa exista
					{
						zipInput.closeEntry();
						this.receiptReport.addRejectedFile(idNomina, name, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_RUT_INVALIDO));
						continue;
					}
				} catch (NumberFormatException e)
				{
					zipInput.closeEntry();
					this.receiptReport.addRejectedFile(idNomina, name, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_RUT_INVALIDO));
					continue;
				}

				if (tokens[1].length() < 5 || tokens[1].charAt(tokens[1].length() - 4) != '.')
				{
					zipInput.closeEntry();
					this.receiptReport.addRejectedFile(idNomina, name, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_OBTENCION_INFO_ARCHIVO));
					continue;
				}
				idNomina = tokens[0] + name.substring(name.length() - 4);
				nombreNomina = tokens[1];

				if (names.get(name) != null)
				{
					zipInput.closeEntry();
					this.receiptReport.addRejectedFile(idNomina, nombreNomina, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_DUPLICADA));
					guardaDescriptor(nombreNomina, rutEmpresa, Constants.EST_RECH_DUPLICADA, (int) zipEntry.getSize(), (int) zipEntry.getCompressedSize());

					continue;
				}

				names.put(name, name);

				boolean checked = false;
				byte[] data = null;
				long crcData = 0;

				try
				{
					int entrySize = (int) zipEntry.getSize();
					data = new byte[entrySize];
					int nTotalBytesReaded = 0;

					while (nTotalBytesReaded < entrySize)
					{
						int nBytesReaded = zipInput.read(data, nTotalBytesReaded, entrySize - nTotalBytesReaded);

						if (nBytesReaded == -1)
							break;
						nTotalBytesReaded += nBytesReaded;
					}

					if (nTotalBytesReaded == entrySize)
					{
						long crc = zipEntry.getCrc();

						CRC32 crc32 = new CRC32();
						crc32.update(data);
						crcData = crc32.getValue();

						// Validación CRC
						if (this.nominaDao.buscaCRC(crcData, nombreNomina.charAt(nombreNomina.lastIndexOf(".") + 1)))
						{
							this.receiptReport.addRejectedFile(idNomina, nombreNomina, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_REENVIO));
							guardaDescriptor(nombreNomina, rutEmpresa, Constants.EST_RECH_REENVIO, (int) zipEntry.getSize(), (int) zipEntry.getCompressedSize());

							continue;
						}
						if (crcData == crc || crc == -1L)
							checked = true;
					}

					// Validación del Periodo
					if (isPeriodoIncorrecto(data, nombreNomina, rutEmpresa))
					{
						this.receiptReport.addRejectedFile(idNomina, nombreNomina, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_PERIODO_INCORRECTO));
						guardaDescriptor(nombreNomina, rutEmpresa, Constants.EST_RECH_PERIODO_INCORRECTO, (int) zipEntry.getSize(), (int) zipEntry.getCompressedSize());

						continue;
					}
					
				} catch (Exception e)
				{
					zipInput.closeEntry();
					this.receiptReport.addRejectedFile(idNomina, nombreNomina, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_DATA_CORRUPTA));
					guardaDescriptor(nombreNomina, rutEmpresa, Constants.EST_RECH_DATA_CORRUPTA, (int) zipEntry.getSize(), (int) zipEntry.getCompressedSize());
					continue;
				}

				if (checked)
				{
					try
					{
						// validar pago de nominas
						int idConvenio = new Integer(nombreNomina.substring(nombreNomina.lastIndexOf(".") + 2)).intValue();
						int validaEstados = this.convenioDao.validaHabilitados(idEnviador, idConvenio, rutEmpresa);
						logger.info("\n\n\nvalidaEstados:" + validaEstados);
						if (validaEstados != 0)
						{
							this.receiptReport.addRejectedFile(idNomina, nombreNomina, (String) Constants.ESTADO_RECHAZO.get("" + validaEstados));
							guardaDescriptor(nombreNomina, rutEmpresa, validaEstados, (int) zipEntry.getSize(), (int) zipEntry.getCompressedSize());
							continue;
						}
						if (this.convenioDao.getNivelPermiso(idEnviador, idConvenio, rutEmpresa) != Constants.NIVEL_ACC_CONV_SUC_EDITOR)
						{
							this.receiptReport.addRejectedFile(idNomina, nombreNomina, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_SIN_PERMISOS));
							guardaDescriptor(nombreNomina, rutEmpresa, Constants.EST_RECH_SIN_PERMISOS, (int) zipEntry.getSize(), (int) zipEntry.getCompressedSize());
							continue;
						}
								
						char tipoNomina = nombreNomina.toUpperCase().charAt(nombreNomina.lastIndexOf(".") + 1);
						NominaVO nomina = this.nominaDao.getNomina(String.valueOf(tipoNomina), rutEmpresa, idConvenio);
						if (isNominaPagada(nomina))
							throw new NominaCheckException(Constants.EST_RECH_NOMINA_PAGADA, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_NOMINA_PAGADA));
						if (isNominaEnProceso(nomina))
							throw new NominaCheckException(Constants.EST_RECH_NOMINA_EN_PROCESO, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_NOMINA_EN_PROCESO));

						this.nominaChecker.check(idNomina, idsTiposNominas, data);
						guardaDescriptor(nombreNomina, rutEmpresa, Constants.EST_RECH_EXITOSO, (int) zipEntry.getSize(), (int) zipEntry.getCompressedSize());

						DocumentoVO documento = new DocumentoVO();
						documento.setRutEmpresa(rutEmpresa);
						// por optimizacion simple, proceso convenio contiene el '.'
						String procesoConvenio = nombreNomina.substring(nombreNomina.lastIndexOf("."));
						documento.setIdConvenio(new Integer(procesoConvenio.substring(2)).intValue());
						documento.setTipoProceso(procesoConvenio.toUpperCase().charAt(1));
						documento.setIdEnvio(this.envio.getId());
						documento.setNombre((nombreNomina.length() <= 40 ? nombreNomina : nombreNomina.substring(0, 40)));
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ZipOutputStream baosZip = new ZipOutputStream(baos);
						ZipEntry dataEmpresa = new ZipEntry(rutEmpresa + procesoConvenio);
						baosZip.putNextEntry(dataEmpresa);
						baosZip.write(data);
						baosZip.close();
						documento.setData(baos.toByteArray());
						guardaDocumento(documento);

						this.envio.addNumNominas();
						guardaNominaProceso(rutEmpresa, documento.getIdConvenio(), documento.getTipoProceso(), documento.getIdEnvio(), documento.getNombre(), crcData);

						this.receiptReport.addAcceptedFile(idNomina, nombreNomina, (int) zipEntry.getSize(), (int) zipEntry.getCompressedSize(), 0);

					} catch (NominaCheckException e)
					{
						this.receiptReport.addRejectedFile(idNomina, nombreNomina, e.getMessage());
						guardaDescriptor(nombreNomina, rutEmpresa, e.getId(), (int) zipEntry.getSize(), (int) zipEntry.getCompressedSize());
					} catch (DaoException e)
					{
						this.receiptReport.addRejectedFile(idNomina, nombreNomina, e.getMessage());
						guardaDescriptor(nombreNomina, rutEmpresa, Constants.EST_RECH_REGISTRO_EN_BD, (int) zipEntry.getSize(), (int) zipEntry.getCompressedSize());
						throw e;
					} catch (NumberFormatException e)
					{
						this.receiptReport.addRejectedFile(idNomina, nombreNomina, e.getMessage());
						guardaDescriptor(nombreNomina, rutEmpresa, Constants.EST_RECH_CONV_INVALIDO, (int) zipEntry.getSize(), (int) zipEntry.getCompressedSize());
						throw e;
					}
				} else
				{
					this.receiptReport.addRejectedFile(idNomina, nombreNomina, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_DATA_CORRUPTA));
					guardaDescriptor(nombreNomina, rutEmpresa, Constants.EST_RECH_DATA_CORRUPTA, (int) zipEntry.getSize(), (int) zipEntry.getCompressedSize());
				}
			}
		} catch (DaoException e)
		{
			this.receiptReport.addRejectedFile(idNomina, nombreNomina, e.getMessage());
			guardaDescriptor(nombreNomina, rutEmpresa, Constants.EST_RECH_REGISTRO_EN_BD, (zipEntry != null ? (int) zipEntry.getSize() : 0), (zipEntry != null ? (int) zipEntry.getCompressedSize() : 0));
			throw e;

		} catch (NumberFormatException e)
		{
			this.receiptReport.addRejectedFile(idNomina, nombreNomina, e.getMessage());
			guardaDescriptor(nombreNomina, rutEmpresa, Constants.EST_RECH_CONV_INVALIDO, (zipEntry != null ? (int) zipEntry.getSize() : 0), (zipEntry != null ? (int) zipEntry.getCompressedSize() : 0));
		} catch (Exception e)
		{
			this.receiptReport.addRejectedFile(idNomina, nombreNomina, (String) Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_RECEPCION));
			guardaDescriptor(nombreNomina, rutEmpresa, Constants.EST_RECH_RECEPCION, (zipEntry != null ? (int) zipEntry.getSize() : 0), (zipEntry != null ? (int) zipEntry.getCompressedSize() : 0));
		} finally
		{
			if (zipEntry != null)
			{
				try
				{
					if (zipInput != null)
						zipInput.closeEntry();
				} catch (IOException e)
				{
				}
			}

			if (zipInput != null)
			{
				try
				{
					zipInput.close();
				} catch (IOException e)
				{
				}
			}

			if (input != null)
			{
				try
				{
					input.close();
				} catch (IOException e)
				{
				}
			}
		}
	}

	/**
	 * decodifica el nombre entregado, reemplazando acentos y ennes
	 * 
	 * @param encodedZipEntryName
	 * @return cadena de caracteres decodificada
	 */
	private String decodeZipEntryName(String encodedZipEntryName)
	{
		StringBuffer decodedZipEntryName = new StringBuffer();
		final String specialChars = "aeiounAEIOUN";
		final String decodedChars = "áéíóúñÁÉÍÓÚÑ";

		for (int i = 0; i < encodedZipEntryName.length(); i++)
		{
			char ch = encodedZipEntryName.charAt(i);

			if (ch == '#')
			{
				if (i + 1 < encodedZipEntryName.length())
				{
					ch = encodedZipEntryName.charAt(i++ + 1);

					if (ch == '#')
					{
						decodedZipEntryName.append('#');
					} else
					{
						int index = specialChars.indexOf(ch);

						if (index == -1)
						{
							decodedZipEntryName.append('#');
							decodedZipEntryName.append(ch);
						} else
							decodedZipEntryName.append(decodedChars.charAt(index));
					}
				} else
				{
					decodedZipEntryName.append('#');
				}
			} else
				decodedZipEntryName.append(ch);
		}
		return decodedZipEntryName.toString();
	}

	/**
	 * retorna el id del envio recien recibido
	 * 
	 * @return id del envio recien recibido
	 */
	public int getIdEnvio()
	{
		if (this.envio == null)
			return 0;
		return this.envio.getId();
	}

	/**
	 * retorna si la nomina esta pagada
	 * 
	 */
	public boolean isNominaPagada(NominaVO nomina)
	{
		if (nomina == null)
			return false;
		int estadoPago = nomina.getIdEstado();
		if (estadoPago == Constants.EST_NOM_PAGADO || estadoPago == Constants.EST_NOM_PAGADO_PARCIALMENTE || estadoPago == Constants.EST_NOM_PRECURSADA)
			return true;
		return false;
	}

	/**
	 * retorna si la nomina esta en proceso
	 * 
	 */
	public boolean isNominaEnProceso(NominaVO nomina)
	{
		return nomina != null && nomina.getIdEstado() == Constants.EST_NOM_EN_EJB;
	}

	/**
	 * si ocurrio un problema durante el envio, o no se encontro nodo de procesamiento, se cambia estado de nominas asociadas a dicho envio a 'no cargada', ya que al registrarlas quedaron en estado
	 * 'en proceso'
	 * 
	 * @param idEnvio
	 *            para identificar las nominas a editar
	 */
	public void rechazaNominas(int idEnvio, boolean rechazoPorNodo)
	{
		try
		{
			List listaDescriptores = this.envioDao.getDescriptores(idEnvio);
			List listaNominas = new ArrayList();
			for (Iterator it = listaDescriptores.iterator(); it.hasNext();)
			{
				DescriptorNominaVO descriptor = (DescriptorNominaVO) it.next();
				//jlandero 12-07-2010 Si el motivo de rechazo de la nómina es crc duplicado, no cambia el estado de la nómina
				if(descriptor.getIdRechazo() != Constants.EST_RECH_REENVIO) {
					NominaVO nomina = null;
					if (descriptor.getTipoProceso() == Constants.TIPO_NOM_REMUNERACION)
						nomina = new NominaREVO(descriptor.getIdConvenio(), descriptor.getRutEmpresa());
					else if (descriptor.getTipoProceso() == Constants.TIPO_NOM_GRATIFICACION)
						nomina = new NominaGRVO(descriptor.getIdConvenio(), descriptor.getRutEmpresa());
					else if (descriptor.getTipoProceso() == Constants.TIPO_NOM_RELIQUIDACION)
						nomina = new NominaRAVO(descriptor.getIdConvenio(), descriptor.getRutEmpresa());
					else
						nomina = new NominaDCVO(descriptor.getIdConvenio(), descriptor.getRutEmpresa());
					listaNominas.add(nomina);
				}
			}
			this.nominaDao.rechazaNominas(listaNominas, rechazoPorNodo);
		} catch (Exception e)
		{
			logger.error("problemas al rechazar nominas", e);
		}
	}

	/**
	 * Determina si el periodo informado en la nómina se corresponde con el que tiene el sistema por parámetro.
	 * 
	 * @param data
	 * @param nombreNomina
	 * @param rutEmpresa
	 * @return
	 * @throws Exception
	 */
	private boolean isPeriodoIncorrecto(byte[] data, String nombreNomina, int rutEmpresa) throws Exception{

		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		BufferedReader br = new BufferedReader(new InputStreamReader(bais));

		String line;
		String mesPeriodoString;
		int mesPeriodo = 0;
		boolean periodoMalo = false;
		try {
			ParametroVO parametroVO = this.parametroMgr.getParametro(Constants.PARAM_PERIODO_INDEPENDIENTE);
			//String periodoInformado = parametroVO.getValor();
			//periodoInformado = periodoInformado.trim().substring(4,6);
			int periodoInformado = Integer.parseInt(parametroVO.getValor().trim().substring(4,6));
			
			int idConvenio = (nombreNomina.lastIndexOf(".") != -1 ? new Integer(nombreNomina.substring(nombreNomina.lastIndexOf(".") + 2)).intValue() : 0);
			String tipoNomina = String.valueOf(nombreNomina.toUpperCase().charAt(nombreNomina.lastIndexOf(".") + 1));
			ConvenioVO convenioVO = this.convenioDao.getConvenio(rutEmpresa, idConvenio);
			GrupoConvenioVO grupoConvenioVO = this.convenioDao.getGrupoConvenio(convenioVO.getIdGrupoConvenio());

			int idMapaNom = 0;
			if (tipoNomina.equals("R") || tipoNomina.equals("r"))
				idMapaNom = grupoConvenioVO.getIdMapaNomRem();
			else if (tipoNomina.equals("A") || tipoNomina.equals("a"))
				idMapaNom = grupoConvenioVO.getIdMapaNomRel();
			else if (tipoNomina.equals("G") || tipoNomina.equals("g"))
				idMapaNom = grupoConvenioVO.getIdMapaNomGra();
			else
				idMapaNom = grupoConvenioVO.getIdMapaNomDep();

			while ((line = br.readLine()) != null) {

				MapeoConceptoVO mapeoConceptoVO = this.mapeosMgr.getMapeoConcepto(tipoNomina, idMapaNom, Constants.CONCEPTO_MES);

				line = corrigeLineaConSeparador(mapeoConceptoVO, line, idMapaNom);

				//SEPARACIÓN POR POSICIÓN
				if (mapeoConceptoVO.getTipoSeparador() == Constants.TIPO_SEPARADOR_POSICION) {
					mesPeriodoString = line.substring(mapeoConceptoVO.getPosicion() - 1, Math.min(mapeoConceptoVO.getPosicion() + mapeoConceptoVO.getLargo() - 1, line.length())).trim();
				//SEPARACIÓN POR CARACTER
				} else {
					String[] elementos = line.split(String.valueOf(mapeoConceptoVO.getCaracterSeparador()));
					mesPeriodoString = elementos[ mapeoConceptoVO.getPosicion()-1 ];
				}

				try {
					mesPeriodo = Integer.parseInt(mesPeriodoString.trim());
				} catch (Exception e) {}

				if (periodoInformado != mesPeriodo) {
					periodoMalo = true;
					break;
				}
			}

			bais.close();
			br.close();
			return periodoMalo;
		} catch (Exception e) {
			logger.error("problemas al obtener el periodo", e);
			return false;
		}
	}
	
	/**
	 * Si la línea tiene menos conceptos que los que indica en mapeo de archivos (para el caso del Separador) se completan estos con caracteres en blanco. 
	 * 
	 * @param mapeosConcep
	 * @param linea
	 * @param cantidadConceptos
	 * @return
	 */
	public String corrigeLineaConSeparador(MapeoConceptoVO mc, String linea, int idMapaNom) {

		if (mc.getTipoSeparador() == Constants.TIPO_SEPARADOR_CARACTER) {
			
			//Se determina la cantidad máxima de conceptos informados en nómina con separador
			int cantidadConceptos;
			char tipoProceso = mc.getTipoProceso();
			
			try {
				cantidadConceptos = this.mapeosMgr.getCantidadDeConceptos(idMapaNom, tipoProceso);
			} catch (Exception e){
				cantidadConceptos = 0;
			}

			linea = corrigeDobleSeparador(mc, linea);
			
			if (cantidadConceptos > 0) {

				String[] elementos = linea.split(String.valueOf(mc.getCaracterSeparador()));

				if (elementos.length < cantidadConceptos) {

					String espacioSeparador = " " + mc.getCaracterSeparador();
					String separadorEspacio = mc.getCaracterSeparador() + " ";
					String ultimoCaracter = String.valueOf(linea.charAt(linea.length()-1));
					String fix;
					if (ultimoCaracter.equals(String.valueOf(mc.getCaracterSeparador())))
						fix = espacioSeparador;
					else
						fix = separadorEspacio;
					for (int i = 0; i < cantidadConceptos - elementos.length; i++) {
						linea = linea + fix;
					}
				}
			}
		}

		return linea;
	}
	

	/**
	 * Corrige la línea cuando el separador aparece junto dos veces o más veces.
	 * 
	 * @param mc
	 * @param line
	 * @return
	 */
	public String corrigeDobleSeparador(MapeoConceptoVO mc, String line) {

		if (mc.getTipoSeparador() == Constants.TIPO_SEPARADOR_CARACTER) {
			String separadorDoble      = mc.getCaracterSeparador().toString() + mc.getCaracterSeparador().toString();
			String separadorConEspacio = mc.getCaracterSeparador() + " " + mc.getCaracterSeparador();
			String lineaLimpia;

			//Se limpia dos veces. En la primera llamada podrían quedar separadores dobles. EJ: ";;;;"
			//1º: "; ;; ;"
			//2º: "; ; ; ;"
			lineaLimpia = line.replaceAll(separadorDoble, separadorConEspacio);
			lineaLimpia = lineaLimpia.replaceAll(separadorDoble, separadorConEspacio);
			return lineaLimpia;
		} else {
			return line;
		}
	}
}