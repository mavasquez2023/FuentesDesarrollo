/*    */ package cl.araucana.pruebas;
/*    */ 
/*    */ import com.ibm.as400.access.AS400;
/*    */ import com.ibm.as400.access.AS400Message;
/*    */ import com.ibm.as400.access.AS400ZonedDecimal;
/*    */ import com.ibm.as400.access.ProgramCall;
/*    */ import com.ibm.as400.access.ProgramParameter;
/*    */ import com.ibm.as400.access.QSYSObjectPathName;
/*    */ import java.io.PrintStream;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public class PruebaServicioAS400
/*    */ {
/*    */   public void prueba(long numCuenta, long ultFechaCuatrimestre)
/*    */   {
/* 18 */     System.out.println("starting");
/* 19 */     AS400 as400 = new AS400("146.83.1.2", "SISTEMAS", "SISTEMAS");
/*    */ 
/* 21 */     ProgramParameter[] pList = new ProgramParameter[3];
/*    */ 
/* 23 */     AS400ZonedDecimal cuenta = new AS400ZonedDecimal(6, 0);
/* 24 */     AS400ZonedDecimal ultFecha = new AS400ZonedDecimal(8, 0);
/* 25 */     AS400ZonedDecimal saldo = new AS400ZonedDecimal(13, 0);
/*    */ 
/* 27 */     pList[0] = new ProgramParameter(cuenta.toBytes(numCuenta), 6);
/* 28 */     pList[1] = new ProgramParameter(ultFecha.toBytes(ultFechaCuatrimestre), 8);
/* 29 */     pList[2] = new ProgramParameter(saldo.toBytes(0.0D), 13);
/*    */ 
/* 32 */     QSYSObjectPathName programName = 
/* 33 */       new QSYSObjectPathName("/QSYS.LIB/LSGPGM.LIB/PLH106ACL.PGM");
/* 34 */     ProgramCall llamaPrograma = 
/* 35 */       new ProgramCall(as400, programName.getPath(), pList);
/*    */     try
/*    */     {
/* 38 */       if (!llamaPrograma.run()) {
/* 39 */         AS400Message[] msgList = llamaPrograma.getMessageList();
/* 40 */         System.out.println("El programa no corrió. Errores:");
/* 41 */         for (int i = 0; i < msgList.length; i++) {
/* 42 */           System.out.println(msgList[i].getText());
/*    */         }
/* 44 */         return;
/*    */       }
/* 46 */       System.out.println("Ejecución OK");
/* 47 */       byte[] respuesta = pList[2].getOutputData();
/*    */ 
/* 49 */       BigDecimal bd = (BigDecimal)saldo.toObject(respuesta);
/* 50 */       double saldoCuota = bd.doubleValue() / 100000.0D;
/* 51 */       System.out.println("saldo:  |" + saldoCuota + "|");
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 58 */       System.out.println("Excepción en invocación a Servicio OS/400: " + e.getMessage());
/* 59 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 65 */     PruebaServicioAS400 t = new PruebaServicioAS400();
/* 66 */     t.prueba(43516L, 20100430L);
/*    */   }
/*    */ }

/* Location:           C:\tmp\araucaFte\prod\ear\AutoconsultaEJB\
 * Qualified Name:     cl.araucana.pruebas.PruebaServicioAS400
 * JD-Core Version:    0.6.0
 */