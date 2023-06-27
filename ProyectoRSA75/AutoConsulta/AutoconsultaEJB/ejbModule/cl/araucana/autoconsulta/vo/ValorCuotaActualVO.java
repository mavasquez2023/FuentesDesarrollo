/*    */ package cl.araucana.autoconsulta.vo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ValorCuotaActualVO
/*    */   implements Serializable
/*    */ {
/*  7 */   private float valorCuotaActual = 0.0F;
/*  8 */   private String fechaValorCuotaActual = null;
           private float ultimoValorCuota = 0;
/*    */ 
/*    */   public float getValorCuotaActual() {
/* 11 */     return this.valorCuotaActual;
/*    */   }
/*    */ 
/*    */   public void setValorCuotaActual(float valorCuotaActual) {
/* 15 */     this.valorCuotaActual = valorCuotaActual;
/*    */   }
/*    */ 
/*    */   public String getFechaValorCuotaActual() {
/* 19 */     return this.fechaValorCuotaActual;
/*    */   }
/*    */ 
/*    */   public void setFechaValorCuotaActual(String fechaValorCuotaActaul) {
/* 23 */     this.fechaValorCuotaActual = fechaValorCuotaActaul;
/*    */   }
					public float getUltimoValorCuota() {
						return ultimoValorCuota;
					}
					public void setUltimoValorCuota(float ultimoValorCuota) {
						this.ultimoValorCuota = ultimoValorCuota;
					}

/*    */ }
