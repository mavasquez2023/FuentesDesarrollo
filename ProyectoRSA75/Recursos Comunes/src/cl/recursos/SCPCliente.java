/**
 * 
 */
package cl.recursos;

/**
 * @author Usist24
 *
 */
public class SCPCliente {
	private void EjecutarSSH(String pServidor, String pUsuario, String pPassword, String pComando)
	{	
		SshStream SSH = null;
		try
	        {
			// instancia del objeto SSHShell
	                SSH = new SshStream(pServidor, pUsuario, pPassword);
			// ejecutar el comando
			SSH.Write(pComando);
			// confirmar
			SSH.flush();
		}
		catch (Exception ex) 
		        { throw ex; }
		finally 
		{ 
			//cerrar conexion SSH
	                SSH.Close();
		}  // end try
	} // end EjecutarSHH
	 
	private void BajarSCP(String pServidor, String pUsuario, String pPassword, String pFileRemoto, String pFileLocal)
	{
		// instancia del objeto SCP
		Scp SCP = new SshStream(pServidor, pUsuario, pPassword);
		try
	        {
			// crear conexion SSH
	                SCP.Connect();
			// Bajar el archivo
	                SCP.From(pFileRemoto, pFileLocal, false);
		}
		catch (Exception ex) 
		       { throw ex; }
		finally 
		{ 
			//cerrar conexion SCP
	                SCP.Close();
		}  // end try
	} // end BajarSCP
	 
	private void SubirSCP(String pServidor, String pUsuario, String pPassword, String pFileRemoto, String pFileLocal)
	{
		// instancia del objeto SCP
		Scp SCP = new SshStream(pServidor, pUsuario, pPassword);
		try
	        {
			// crear conexion SSH
	               SCP.Connect();
			// Subir el archivo
	                SCP.To(pFileLocal, pFileRemoto, false);
		}
		catch (Exception ex) 
		       { throw ex; }
		finally 
		{ 
			//cerrar conexion SCP
	                SCP.Close();
		}  // end try
	} // end SubirSCP
}
