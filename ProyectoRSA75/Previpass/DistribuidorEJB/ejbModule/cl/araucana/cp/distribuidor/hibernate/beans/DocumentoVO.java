package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.Hibernate;

public class DocumentoVO implements Serializable
{
	private static final long serialVersionUID = -6140869426437533543L;
	private int id;

	private int idEnvio;
	private int idConvenio;
	private char tipoProceso;
	private int rutEmpresa;

	private String nombre;
	private Blob data;

	public byte[] getData()
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try
		{
			return toByteArrayImpl(this.data, baos);
		} catch (Exception e)
		{
		}
		return null;
	}

	private byte[] toByteArrayImpl(Blob fromImageBlob, ByteArrayOutputStream baos) throws SQLException, IOException
	{
		byte buf[] = new byte[4000];
		int dataSize;
		InputStream is = fromImageBlob.getBinaryStream();

		try
		{
			while ((dataSize = is.read(buf)) != -1)
			{
				baos.write(buf, 0, dataSize);
			}
		} finally
		{
			if (is != null)
			{
				is.close();
			}
		}
		return baos.toByteArray();
	}

	public void setData(byte[] data)
	{
		this.data = Hibernate.createBlob(data);
	}

	public void setData(String data)
	{
		this.data = Hibernate.createBlob(data.getBytes());
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getIdEnvio()
	{
		return this.idEnvio;
	}

	public void setIdEnvio(int idEnvio)
	{
		this.idEnvio = idEnvio;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	public int getIdConvenio()
	{
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}

	public char getTipoProceso()
	{
		return this.tipoProceso;
	}

	public void setTipoProceso(char tipoNomina)
	{
		this.tipoProceso = tipoNomina;
	}

}
