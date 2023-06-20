/**
 * 
 */
package cl.araucana.arsdoc.common;

import cl.araucana.arsdoc.vo.FilaVO;
import cl.araucana.arsdoc.vo.IndiceVO;
import com.schema.util.StringTokenizerUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class CommandLine
{
  static Logger logger = Logger.getLogger(CommandLine.class);
  private int exitVal;
  private String cmd;
  private String archivo;
  private String directorioQuery;
  private String delim;

  public CommandLine(String cmd, String archivo, String directorioQuery, String delim)
  {
    this.cmd = cmd;
    this.archivo = archivo;
    this.directorioQuery = directorioQuery;
    this.delim = delim;
  }

  public IndiceVO ejecutarComando()
    throws Exception
  {
    logger.debug("!--> CommandLine cmd:" + this.cmd);
    Runtime rt = Runtime.getRuntime();
    Process proc = rt.exec(this.cmd);

    IndiceVO indice = null;

    ArsdocLog errorGobbler = new ArsdocLog(proc.getErrorStream(), "LOG");

    ArsdocLog outputGobbler = new ArsdocLog(proc.getInputStream(), "OUTPUT");

    errorGobbler.start();
    outputGobbler.start();

    int exitVal = proc.waitFor();
    logger.debug("!--> exe exitVal :" + exitVal);

    if (exitVal == 0) {
      try {
        indice = obtenerArchivo();
      } catch (Exception ex) {
        logger.debug("ComandLine -> Debug: " + ex.getMessage());
        logger.error("ComandLine -> Excepción: ", ex);
      }
      logger.debug("!--> exe arsdoc : query terminado ok.");
    } else if (exitVal == 3)
    {
      try
      {
        indice = obtenerArchivo();
        indice.setMaxHitsTrunco(true);
      } catch (Exception ex) {
        throw new Exception("Usuario No Válido.");
      }
    } else {
      /*if (exitVal == 3) {
        throw new Exception("La conexión con el servidor no pudo ser establecida.");
      }*/
      if (exitVal == 1)
        throw new Exception("Uno de los parámetros enviados está mal escrito o no corresponde a los fields.");
      if (exitVal == 127)
        throw new Exception("Comando no válido para la consulta arsdoc.");
    }
    return indice;
  }

  private IndiceVO obtenerArchivo()
    throws Exception
  {
    IndiceVO indice = new IndiceVO();
    indice.setNombreArchivo(this.archivo);

    FileReader fr = new FileReader(this.directorioQuery + this.archivo);
    BufferedReader br = new BufferedReader(fr);

    ArrayList filas = new ArrayList();
    String line;
    while ((line = br.readLine()) != null)
    {
      FilaVO fila = new FilaVO();
      StringTokenizerUtils tokensFila = new StringTokenizerUtils(line, this.delim);
      fila.setColumnas(tokensFila.getTokens());
      filas.add(fila);
    }
    br.close();

    indice.setFilas((FilaVO[])filas.toArray(new FilaVO[filas.size()]));

    File f = new File(this.directorioQuery + this.archivo);
    f.delete();

    return indice;
  }
}
