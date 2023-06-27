/*     */ package cl.araucana.pruebas;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class Prueba
/*     */ {
/*     */   public static void main(String[] args)
/*     */   {
/*  28 */     getDouble();
/*     */   }
/*     */ 
/*     */   public static void getDouble() {
/*  32 */     double tipVTabla = 0.0D; double tipPTabla = 0.0D;
/*     */ 
/*  35 */     double tipV = 0.29D;
/*     */ 
/*  37 */     double tipP = tipV;
/*  38 */     System.out.println(Math.pow(2.0D, 3.0D));
/*  39 */     double exp = 0.08333333333333333D;
/*  40 */     double x = (Math.pow(1.0D + tipV, exp) - 1.0D) / 100.0D;
/*     */ 
/*  42 */     System.out.print(x / 100.0D);
/*  43 */     Locale chile = new Locale("en_CL", "", "");
/*  44 */     NumberFormat form = NumberFormat.getInstance(chile);
/*     */     try
/*     */     {
/*  47 */       ((DecimalFormat)form).toPattern();
/*  48 */       Number n = form.parse(form.format(x / 100.0D));
/*  49 */       System.out.println(n.doubleValue());
/*     */     }
/*     */     catch (ParseException e1)
/*     */     {
/*  53 */       e1.printStackTrace();
/*     */     }
/*     */ 
/*  56 */     NumberFormat nf = NumberFormat.getCurrencyInstance();
/*  57 */     DecimalFormat df = (DecimalFormat)nf;
/*  58 */     df.setMinimumFractionDigits(2);
/*  59 */     df.setMaximumFractionDigits(2);
/*  60 */     df.setDecimalSeparatorAlwaysShown(true);
/*  61 */     String pattern = "#.########";
/*  62 */     df.applyPattern(pattern);
/*  63 */     System.out.println(df.format(x));
/*     */   }
/*     */ 
/*     */   public static void format()
/*     */   {
/* 107 */     SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM yyyy");
/* 108 */     SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyyMM");
/*     */ 
/* 111 */     Date today = null;
/*     */     try {
/* 113 */       today = dateFormatter1.parse("200508");
/*     */     }
/*     */     catch (ParseException e) {
/* 116 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 120 */     String dateOut = dateFormatter.format(today);
/* 121 */     System.out.print(dateOut);
/*     */   }
/*     */ }

/* Location:           C:\tmp\araucaFte\prod\ear\AutoconsultaEJB\
 * Qualified Name:     cl.araucana.pruebas.Prueba
 * JD-Core Version:    0.6.0
 */