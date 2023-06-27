package cl.araucana.cp.hibernate.beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;

import org.hibernate.Hibernate;

import cl.araucana.cp.distribuidor.hibernate.beans.AuditableVO;

public class DocumentoVO extends AuditableVO
{
	private static final long serialVersionUID = -6140869426437533543L;
	private int id;

	private int idEnvio;
	private int idConvenio;
	private char tipoProceso;
	private int rutEmpresa;

	private String nombre;
	private Blob data;
	
	private String nombreEmpresa;

	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public DocumentoVO()
	{
		super();
	}

	public DocumentoVO(int id)
	{
		super();
		this.id = id;
	}

	public DocumentoVO(int id, int idEnvio, int idConvenio, char tipoProceso, int rutEmpresa, String nombre, Blob data)
	{
		super();
		this.id = id;
		this.idEnvio = idEnvio;
		this.idConvenio = idConvenio;
		this.tipoProceso = tipoProceso;
		this.rutEmpresa = rutEmpresa;
		this.nombre = nombre;
		this.data = data;
	}

	public DocumentoVO(int id, int idEnvio, int idConvenio, char tipoProceso, int rutEmpresa, String nombre)
	{
		super();
		this.id = id;
		this.idEnvio = idEnvio;
		this.idConvenio = idConvenio;
		this.tipoProceso = tipoProceso;
		this.rutEmpresa = rutEmpresa;
		this.nombre = nombre;
	}

	public byte[] getData()
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try
		{
			return this.toByteArrayImpl(this.data, baos);
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

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getIdConvenio()
	{
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
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

	public char getTipoProceso()
	{
		return this.tipoProceso;
	}

	public void setTipoProceso(char tipoNomina)
	{
		this.tipoProceso = tipoNomina;
	}
	
	public String toString()
	{
		long l = 0;
		try
		{
			l = this.data.length();
		} catch (SQLException e)
		{}
		return "DocumentoVO" + this.id + ":" + this.idEnvio + ":" + this.idConvenio + ":" + this.tipoProceso + ":" + this.rutEmpresa + ":" + 
				this.nombre + ":largo data:" + l + "::";
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.idConvenio));
		parametros.put("3", String.valueOf(this.idEnvio));
		parametros.put("4", String.valueOf(this.tipoProceso));
		parametros.put("5", String.valueOf(this.rutEmpresa));
		parametros.put("6", String.valueOf(this.nombre));
		return parametros;
		
	}
}
