package cl.araucana.cp.receipt;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import cl.araucana.cp.distribuidor.base.Constants;

public class NominaChecker
{

	private static Logger logger = Logger.getLogger(NominaChecker.class);
	private static NominaChecker instance = new NominaChecker();

	private NominaChecker()
	{
	}

	public static NominaChecker getInstance()
	{
		return instance;
	}

	public void check(String idNomina, String idsTiposNominas, byte[] data) throws NominaCheckException
	{
		final int MAX_RUT_LENGTH = 9;

		/*
		 * 
		 * Regla #1: Identificador de Nómina.
		 * 
		 * <rutEmpresa> . <tipoNomina> <convenio>
		 * 
		 */
		int dotIndex = idNomina.indexOf('.');

		if (dotIndex < 1 || dotIndex != idNomina.length() - 4 || dotIndex > MAX_RUT_LENGTH)
			throw new NominaCheckException(Constants.EST_RECH_ID_INVALIDO, (String)Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_ID_INVALIDO));

		int rutEmpresa = 0;
		try
		{
			rutEmpresa = Integer.parseInt(idNomina.substring(0, dotIndex));

			if (rutEmpresa <= 0)
				throw new NominaCheckException(Constants.EST_RECH_RUT_INVALIDO, (String)Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_RUT_INVALIDO));
		} catch (NumberFormatException e)
		{
			throw new NominaCheckException(Constants.EST_RECH_RUT_INVALIDO, (String)Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_RUT_INVALIDO));
		}
		//29/072010
		char tipoNomina = idNomina.toUpperCase().charAt(dotIndex + 1);

		int tipoNominaIndex = idsTiposNominas.indexOf(tipoNomina);

		if (tipoNominaIndex < 0)
			throw new NominaCheckException(Constants.EST_RECH_TIPO_INVALIDO, (String)Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_TIPO_INVALIDO));

		int convenio = 0;
		try
		{
			convenio = Integer.parseInt(idNomina.substring(dotIndex + 2));

			if (convenio <= 0)
				throw new NominaCheckException(Constants.EST_RECH_CONV_INVALIDO, (String)Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_CONV_INVALIDO));
		} catch (NumberFormatException e)
		{
			throw new NominaCheckException(Constants.EST_RECH_CONV_INVALIDO, (String)Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_CONV_INVALIDO));
		}

		/*
		 * Regla #2: Nómina no puede estar vacia.
		 */

		if (data.length == 0)
			throw new NominaCheckException(Constants.EST_RECH_VACIA, (String)Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_VACIA));

		/*
		 * Regla #3: Nómina debe ser texto plano con lineas de largo fijo.
		 */
		try
		{
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			BufferedReader br = new BufferedReader(new InputStreamReader(bais));
			while (br.readLine() != null)
			{}
		} catch (Exception e)
		{
			logger.error("ERROR validando encoding:", e);
			throw new NominaCheckException(Constants.EST_RECH_SIN_FORMATO, (String)Constants.ESTADO_RECHAZO.get("" + Constants.EST_RECH_SIN_FORMATO));
		}
	}
}
