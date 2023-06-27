package cl.araucana.cp.receipt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.log4j.Logger;
import org.apache.tools.ant.filters.StringInputStream;

import cl.araucana.cp.exceptions.SesionException;

public class DesEncrypt implements Serializable
{
	private static final long serialVersionUID = 7234039773029207092L;

	private static Cipher cifrador;

	private static SecretKey llave;

	private static Logger logger = Logger.getLogger(DesEncrypt.class);

	static
	{
		try
		{
			llave = KeyGenerator.getInstance("DES").generateKey();
			cifrador = Cipher.getInstance("DES");
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	public DesEncrypt()
	{}

	/**
	 * Paso resumido de una negociacion de credenciales para un canal seguro.
	 * 
	 * @param codificacionLlaveCliente
	 *            llave publica del cliente en codificacion Base64
	 * @return String con la codificacion en Base64 de la llave token encriptada con la llave publica del cliente.
	 */
	public synchronized String codifica(String basuraInicial, long latencia)
	{
		try
		{
			logger.debug("user:" + basuraInicial + "::");
			logger.debug("latencia:" + latencia + "::");
			cifrador.init(Cipher.ENCRYPT_MODE, llave);
			StringInputStream sis = new StringInputStream(basuraInicial + "/" + (System.currentTimeMillis() + latencia));
			logger.debug(basuraInicial);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			OutputStream out = new CipherOutputStream(baos, cifrador);
			int numRead = 0;
			byte[] buf = new byte[1024];
			while ((numRead = sis.read(buf)) >= 0)
				out.write(buf, 0, numRead);
			out.flush();
			out.close();
			StringBuffer sb = new StringBuffer();
			byte[] serializacion = baos.toByteArray();
			for (int i = 0; i < serializacion.length; i++)
				sb.append(Integer.toString(serializacion[i] - Byte.MIN_VALUE + 256, 16).substring(1));
			return sb.toString();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public synchronized String decodifica(String objeto) throws SesionException, Exception
	{
		try
		{
			byte[] codigo = new byte[objeto.length() / 2];
			for (int i = 0, j = 0; i < codigo.length; i++)
				codigo[i] = (byte) (Integer.parseInt(objeto.substring(j++, ++j), 16) + Byte.MIN_VALUE);
			cifrador.init(Cipher.DECRYPT_MODE, llave);
			InputStream sis = new CipherInputStream(new ByteArrayInputStream(codigo), cifrador);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			int numRead = 0;
			byte[] buf = new byte[1024];
			while ((numRead = sis.read(buf)) >= 0)
				os.write(buf, 0, numRead);
			os.flush();
			os.close();
			String traducido = new String(os.toByteArray());
			int index = traducido.indexOf('/');
			if (Long.parseLong(traducido.substring(index + 1)) < System.currentTimeMillis())
				throw new SesionException("Tiempo de vida expirado");
			String result = traducido.substring(0, index);
			logger.debug(result);
			return result;
		} catch (NumberFormatException e)
		{
			throw new SesionException("Tiempo de vida expirado");
		} catch (Exception e)
		{
			throw new SesionException("Problemas al decodificar idUsuario");
		}
	}

	public static void main(String[] args) 
	{
		try 
		{
			String xxx = "11111111";
			long latencia = 600000;
			DesEncrypt de = new DesEncrypt();
			String codificado = de.codifica(xxx, latencia);
			logger.debug("codificado:" + codificado + "::");
			String decodif;
			decodif = de.decodifica("1b3ccbc6440f973d08e3a1f8c5277d9aa290428815afec14");
			logger.debug("decodif:" + decodif + "::");
		} catch (Exception e) 
		{
		}
	}
}
