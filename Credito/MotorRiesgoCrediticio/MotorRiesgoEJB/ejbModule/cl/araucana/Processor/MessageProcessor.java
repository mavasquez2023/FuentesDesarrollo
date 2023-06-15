package cl.araucana.Processor;

import cse.model.businessobject.Monto;
import cse.model.businessobject.Rut;
import cse.model.exception.ScoringModuleException;
import cse.model.service.CondicionesOtorgamientoService;
import cse.model.service.data.EvaluarCondicionesResponse;
import cse.model.service.impl.CondicionesOtorgamientoServiceImpl;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageProcessor
{
  private static Logger logger = Logger.getLogger(As400DAO.class.getName());

  public boolean ProcesaSolicitud(String solicitud)
  {
    StringTokenizer tokenizer = new StringTokenizer(solicitud, "&");
    String id = tokenizer.nextToken();
    String as400id = tokenizer.nextToken();
    String payload = tokenizer.nextToken();

    if (as400id.equals("nulo"))
    {
      return false;
    }

    Map parametros = StructuredStringHelper.partition(payload);

    EvaluarCondicionesResponse respuesta = null;
    CondicionesOtorgamientoService service = null;
    As400DAO helper = null;
    try {
      service = new CondicionesOtorgamientoServiceImpl();

      respuesta = service.evaluarCondicionesOtorgamientoFull(
        new Rut((String)parametros.get("rut"), (String)parametros.get("digito")), 
        Integer.parseInt((String)parametros.get("tipoAfiliado")), 
        new Monto(Integer.parseInt((String)parametros.get("monto"))), 
        (String)parametros.get("genero"), 
        (String)parametros.get("fechaNac"), 
        (String)parametros.get("estadoCivil"), 
        Double.parseDouble((String)parametros.get("remuneracion")), 
        (String)parametros.get("diasMorosidad"), 
        Integer.parseInt((String)parametros.get("numCreditosAnteriores")), 
        Integer.parseInt((String)parametros.get("numDiasLicencia")), 
        (String)parametros.get("antiguedadLaboral"), 
        new Rut((String)parametros.get("rutEmpleador"), (String)parametros.get("digitoEmpleador")), 
        (String)parametros.get("clasifRiesgoEmpresa"), 
        (String)parametros.get("codSectorEmpresa"), 
        (String)parametros.get("nroTrabajadoresEmpresa"), 
        (String)parametros.get("esProspectoLeyInsolvencia"), 
        (String)parametros.get("perfilEmpresaGR"));

      helper = new As400DAO(id, as400id, respuesta, (HashMap)parametros, payload);
      try
      {
        helper.updateRecord();
      } catch (SQLException e) {
        logger.log(Level.SEVERE, "Problemas al tratar de ingresar respuesta de servicio al AS/400. Se intentara informar del error al AS/400", 
          e);
      }
    }
    catch (NumberFormatException e) {
      try {
        logger.log(Level.SEVERE, "Problemas en la evaluacion crediticia. Se informara al AS/400", 
          e);
        helper = new As400DAO(id, as400id, null, (HashMap)parametros, payload);
        helper.informarError(1);
      } catch (SQLException e1) {
        logger.log(Level.SEVERE, "Problemas al tratar de reportar error al AS/400", e);
      }
      return false;
    }
    catch (ScoringModuleException e) {
      try {
        logger.log(Level.SEVERE, "Problemas en la evaluacion crediticia. Se informara al AS/400", 
          e);
        helper = new As400DAO(id, as400id, null, (HashMap)parametros, payload);
        helper.informarError(1);
      } catch (SQLException e1) {
        logger.log(Level.SEVERE, "Problemas al tratar de reportar error al AS/400", e);
      }

      return false;
    }
    return true;
  }
}