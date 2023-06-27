/**
 * 
 */
package cl.domino.cp;

import lotus.domino.*;
import java.util.*;
import java.io.*;

public class PruebaConexionMQ extends AgentBase {

	String 	fechahora,comxml, foliotes,docID,estado,codigobarraant;


	Session 				s;
	AgentContext 			ac ;
	Database 				db;
	Document 				doc;
	View 					view;
	ViewEntry 				entry;
	ViewEntryCollection 	vec;
	InvokeMQ 					mq;
	
	public void NotesMain() {
		boolean exito=false;
		try {
			s 		= getSession();
			ac 	= s.getAgentContext();
			db 	= ac.getCurrentDatabase();
			mq = new InvokeMQ();
			String t1aux	=	getTodayMillisec();			
			//System.out.println("ANTES DE INVOCAR AGENTE " );
			mq.Execute(docID);
			String		t2aux	=	getTodayMillisec();			
			tiempotot = ( Cambiadouble(t2aux) - Cambiadouble( t1aux) ) /1000 ;
	 	//System.out.println("DESPUES DE INVOCAR AGENTE  -Doc- " +docID + "-"+tiempotot);
		//System.out.println("///////////////////////////////*COMIENZA COMPROBANTE EN DEMANDA/////////////////////////////////////////");			
		//se verifica si esxisten comprobantes en demanda por procesar
		view	= db.getView("COMOND"); 
		vec 		= viewcomp.getAllEntries();
		if ( veccomp.getCount()>0 ) {
			//System.out.println("Numero de comprobantes a procesar= " + veccomp.getCount());
			db    =   s.getDatabase(s.getServerName(), "Cotiza/Cotizarev.nsf");
			//Connect
			mq = new InvokeMQ();
			entrycomp = veccomp.getFirstEntry();
	      	while (entrycomp != null) {
	      		docact= entrycomp.getDocument();
	      		codholding =docact.getItemValueString("codholding");
				rutactual =docact.getItemValueString("rutemp");
				convenio =docact.getItemValueString("convenio");
				proceso =docact.getItemValueString("proceso");
				procesoin=proceso;
	 			System.out.println("/////////COMIENZA COMPROBANTE EN DEMANDA ->"+ rutactual + ", Convenio: " + convenio + ", Proceso: " + proceso);	
	 		if (Referencias() && !rutactual.equals("")){
	 		if (verificaComprobanteCursado(codholding, rutactual, convenio, proceso)){
			      rut 			= rutactual.trim();
			      
			      
			      //rescatando fecha proceso como aaaamm
			      view 	= dbini.getView("fechacierre");
			      entry		= 	view.getEntryByKey("EP");
			      docaux = entry.getDocument();
				 periodo =docaux.getItemValueString("codigoproceso") ;
	    	           //periodo		= "2003-02";
	    	           if (proceso.equalsIgnoreCase("R"))
	    	           	procesoin="";
	             	 v	= new Vector();
				 v.addElement(codholding + "_" + rutactual + "_" + convenio);
				 v.addElement("REVISAR");
				 viewcon 	= db.getView("TRARC" + procesoin);
				 viewcon.refresh();
				 veccon = viewcon.getAllEntriesByKey(v, true);			
	 //System.out.println(rut +"  TIENE " +  veccon.getCount() + "ERRORES" );
				// Verificar estado del comprobante en Tesoreria
				
				if ( veccon.getCount() > 0 ) {
				        	System.out.println("NO PUEDE GENERAR COMPROBANTE " +rut +" " +  veccon.getCount());
				        	docact.replaceItemValue("mensaje","Revise el listado de trabajadores y corriga los errores primero."); 
						docact.replaceItemValue("status","error");
				        	//se anula el que tiene
				}    	               	           
	    	           else {
		        		 if (VerEmpresa()) {

		        		 	// Buscar numero de comprobante
						folio = GenerarNroComprobante();
						InicializaVariables();			            
						if (Procesatrabajadores()){
							if(CrearComprobante()){
								// Eliminar comprobantes submtidos o con errores antiguos
								borrarDocSubmitido();
								exito= true;
						       	// Llamada a MQ
								double tiempotot;		 
								String t1aux	=	getTodayMillisec();			
						     	//System.out.println("ANTES DE INVOCAR AGENTE " );
								 mq.Execute(docID);
								String		t2aux	=	getTodayMillisec();			
								tiempotot = ( Cambiadouble(t2aux) - Cambiadouble( t1aux) ) /1000 ;
								//System.out.println("DESPUES DE INVOCAR AGENTE  -Doc- " +docID + "-"+tiempotot);
		
							}
						}
				          if (!exito){
				          	docact.replaceItemValue("status","error");
				          	System.out.print("Error, Comprobante en demanda No creado  ----------------->");
				          	}
						else
							docact.replaceItemValue("status","ok");
				        }
					   else {
				        	System.out.println("NO HAY DATOS DE EMPRESA " );
					    }  // if (VerEmpresa()) {		    	 		
				//System.out.println("TERMINO  COMPROBANTE" );
				}
				docact.replaceItemValue("Form","copagook");
				docact.save(true, true);
				} //fin if (verificaComprobanteCursado)
				else{
					System.out.println("No puede generar nuevo comprobante, Comprobante se encuentra Cursado");
					docact.replaceItemValue("status","error");
					docact.replaceItemValue("Form","copagook");
					docact.save(true, true);
				}
			} //fin Referencias
			else{
				System.out.println("********************ERROR, eliminando Comprobante en Demanda");
				docact.replaceItemValue("status","error");
				docact.replaceItemValue("Form","copagook");
				docact.save(true, true);
			} 	
			entrycomp = veccomp.getNextEntry();
			docact.recycle();
				
			} //fin While
			} //fin if
					
			} catch(Exception e) {
				System.out.println("CAI EN MAIN CON  " );
				e.printStackTrace();
			}
			try{
				//Cerrando conexión MQ
				if (mq!=null){
					mq.Disconnect();
				}
				//NEW - Limpiando objetos Domino
	         		viewcomp.recycle();
				veccomp.recycle();
	 			db.recycle();
	 			s.recycle();
	 			ac.recycle();
			} catch(Exception e) {
				System.out.println("CAI EN FINALLY NotesMain" );
				e.printStackTrace();
			}
		}
		
		public void InicializaVariables(){
						counter 				=	0;
				            countermut 				=	0;
						  numtra 				=	0;
						  cuentaerror			=	0;
						  estarchivo			=	0;
	     			       totfon					=	0;
	     			       totinp					=0;
	     			       totinpfonimp			=0;
	     			       totinpisaimp			=0;
	     			       totfonafpimp			=0;
	     			       totinpasfimp			=0;
	     			       //totales parciales inp
		     			totinpft					= 0;
		     			totinpit					=0;
						totfont					=	0;
						//total inp
						totinpt					=0;
	     			       totgen					=0;
		     		       totinpf					=	0;
	     			       totinpi					=	0;
		     		       totimpft				=	0;
						 totfont					=	0;
				      	 ccaf6 					=	0;
						montoacctratot		=	0;
						monto06 				=	0;
						montocreccaft		=	0;
						montoleaccaft		=	0;
						montodenccaft		=	0;
						montovidccaft		=	0;
						fin							=  0;
						numtra					=  0;
		                     rutant					=	"";
	                          fin 						=  0;
						totccaf					=  0;
						totalsfe					=0;
						totasfaminp			=0;
						asfaminpt			=0;
						//montootrccaft			= 0;
						ccafdent 				=0;
			     		ccafvidt 					=0;
			     		//ccafotrt 					=0;
			     		ccafcret 				=0;
			     		ccafleat 				=0;
			     		ccaf6t 					=0;
			     		ccafasfamt 			=0;
			     		totccaf					=0;
			     		totccaft 					=0;
						totalccaf				=0;
						totmutimp				=0;
						totmutimp_sininp	=0;
			     		totfonafpimp			=0;
						totinpfonimp			=0;
						totinpisaimp			=0;
						mutxtotal				=0;
						montomutinp			=0;
						totbonextinp			=0;
						totbonextinpt			=0;
		}
		
		public boolean Referencias() {
			try {
				int i ;
				Double d;
				d = new Double(0);
				//afp
				Vector v;
				view=dbc.getView("CIAFP");
			     vec = view.getAllEntries();
	      		entry = vec.getFirstEntry();
				 while (entry != null) {
				  		v = entry.getColumnValues();
						i = Integer.parseInt( v.elementAt(0).toString()); 
						if (	i != 90  && i > 0) {
			     			afplis[i]		=	v.elementAt(1).toString() ; //nombre
						//	afplis1[i][0]	= 	Cambiadouble( v.elementAt(2).toString()); 	//pension
						//	afplis1[i][1]	=	Cambiadouble( v.elementAt(3).toString()); 	//seguro
						}
		//			     System.out.println(" afp "+ afplis[i]);
					     entry = vec.getNextEntry(); 
				}			
				//apv
				vec.recycle();
				
				view	=	dbc.getView("RAPV");
			     vec 	=	view.getAllEntries();
	      		entry	=	vec.getFirstEntry();
				 while (entry != null) {
				  		v = entry.getColumnValues();			  					
				  		d= Double.valueOf(v.elementAt(0).toString());
						i =  d.intValue();
						if (	i < 90 ) {						
			     			apvlis[i]		=	v.elementAt(1).toString() ; //nombre
		     			}
					     entry = vec.getNextEntry(); 
				}					
				vec.recycle();
				
				//isapre
				view=dbc.getView("CISA");
			     vec = view.getAllEntries();
	      		entry = vec.getFirstEntry();
				while (entry != null) {
				  		v = entry.getColumnValues();
				  		
				  		i = Integer.parseInt( v.elementAt(0).toString()); 
						if (	i != 90  && i > 0) {
			     			isalis[i]		=	v.elementAt(1).toString() ; //nombre
						}
					     entry = vec.getNextEntry(); 
				}					
				vec.recycle();
							
				return true;
		}
		catch(Exception e) {
				System.out.println("CAI EN LLENAR REFERENCIAS " );
				e.printStackTrace();
				return false;
		}
	   }

		
		public boolean Procesatrabajadores() {
		  try {
		  		 Vector v	= new Vector();
			      v.addElement(holding);
				 v.addElement(rut);
			      v.addElement(convenio);
			//	 view 		= db.getView("TRAW");
				 view 		= db.getView("TRAWCOM" + procesoin);
				 view.refresh();
			      vec = view.getAllEntriesByKey(v, true);
	     		 ViewEntry entry = vec.getFirstEntry();
				 Document docaux ;
				 rutant="";
			      while (entry != null) {
					   docaux = entry.getDocument();
					   rutact	=docaux.getItemValueString("ruttra") ;
					   copia	=docaux.getItemValueInteger("copia") ;
					   montoimponible 	= (double) docaux.getItemValueDouble("montoimp");
					   montoimponiblemut 	= (double) docaux.getItemValueDouble("montoimpmut");
					
					/*
					//NEW BONO EXTRAORDINARIO>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
					//System.out.println(" cajatrab " + cajatrab);
					if ( cajatrab.equals("00")) {
					   montobono 	= docaux.getItemValueInteger("montobonext");	
					  // System.out.println(" monto bono " + montobono);
					   if (montobono>0){		
								totbonextinp 		=	montobono + totbonextinp;
								Math.round(totbonextinp);
								totbonextinpt++;
							}
					   
					}*/
						
						
					   if (proceso.equals("G")){
					   	montoimponiblemut= montoimponible;
					   }
						if (  ( !rutact.equals(rutant) && copia==1) || ( !rutact.equals(rutant) && proceso.equals("D") ) ) {
							counter++;
							//AFPs
							afp	=docaux.getItemValueString("afpcod").trim() ;
							// dep. convenidos 9/12/2004
							if ( proceso.equals("D")) {
								String auxs 	= docaux.getItemValueString("montodepcon");			
								if ( auxs == null  || auxs.equals("") ) {
									double aux =(double) docaux.getItemValueDouble("montodepcon");		
									totafp[Integer.parseInt(afp)][0] =	totafp[Integer.parseInt(afp)][0]	+	aux;
								} else {
									totafp[Integer.parseInt(afp)][0] =	totafp[Integer.parseInt(afp)][0]	+	Cambiadouble(auxs);
								}			

						auxs 	= docaux.getItemValueString("montoind");			
								if ( auxs == null || auxs.equals("") ) {
									double aux =(double) docaux.getItemValueDouble("montoind");		
									totafp[Integer.parseInt(afp)][0] =	totafp[Integer.parseInt(afp)][0]	+	aux;
								} else {
									totafp[Integer.parseInt(afp)][0] =	totafp[Integer.parseInt(afp)][0]	+	Cambiadouble(auxs);
								}		
			
				//				else
				//					totafp[Integer.parseInt(afp)][0] =	totafp[Integer.parseInt(afp)][0]	+	Cambiadouble(auxs);
								
				//				totafp[Integer.parseInt(afp)][1] ++ ;
			
							//System.out.println("afp-"+afp);
								//System.out.println(auxs);
							//System.out.println("PASO 1-"+totafp[Integer.parseInt(afp)][0]);
				
		
							}
							
							
				//System.out.println("TRA"+ rutact +" afp-"+afp);	
	  						if ( !afp.equals("90") && !afp.equals("00") && !afp.equals("")) {
				//System.out.println("en afp TRA"+ rutact +" afp-"+afp);	
	  							tot 	= 0;
								totc 	= 0;
								totp	=0;
								tottp	=0;
								aux 	= (double) docaux.getItemValueDouble("montoafpobl");			
								tot 	= tot + aux;
								totafp[Integer.parseInt(afp)][4] =	totafp[Integer.parseInt(afp)][4]	+	aux;
								aux 	= (double) docaux.getItemValueDouble("montoafpaho");			
								tot 	= tot + aux;
								totafp[Integer.parseInt(afp)][5] =	totafp[Integer.parseInt(afp)][5]	+	aux;
								aux 	= (double) docaux.getItemValueDouble("montoafpvol");			
								tot 	= tot + aux;	
								totafp[Integer.parseInt(afp)][6] =	totafp[Integer.parseInt(afp)][6]	+	aux;
								totafp[Integer.parseInt(afp)][7] =	totafp[Integer.parseInt(afp)][7]	+	tot;
								totp= tot;						
								aux 	= (double) docaux.getItemValueDouble("montoleyc_1");			
								tot 	= tot + aux;	
								totc 	= totc + aux;
								totafp[Integer.parseInt(afp)][8] =	totafp[Integer.parseInt(afp)][8]	+	aux;							
								aux 	= (double) docaux.getItemValueDouble("montoleyc_2");										
								tot 	= tot + aux;							
								totc 	= totc + aux;
								totafp[Integer.parseInt(afp)][9] =	totafp[Integer.parseInt(afp)][9]	+	aux;							
								aux 	= (double) docaux.getItemValueDouble("montotrapes_1");			
								tot 	= tot + aux;	
								tottp 	= tottp + aux;
								totafp[Integer.parseInt(afp)][10] =	totafp[Integer.parseInt(afp)][10]	+	aux;							
								/*aux 	= (double) docaux.getItemValueDouble("montotrapes_2");			
								tot 	= tot + aux;	
								tottp 	= tottp + aux;
								totafp[Integer.parseInt(afp)][11] =	totafp[Integer.parseInt(afp)][11]	+	aux;
								*/
								//totafp[Integer.parseInt(afp)][12] =	totafp[Integer.parseInt(afp)][12]	+	tottp;
								aux 	= (double) docaux.getItemValueDouble("montoimp");
								totafp[Integer.parseInt(afp)][13] =	totafp[Integer.parseInt(afp)][13]	+	aux;
//	ADVISE SIS
//								System.out.println("CPED- Campo SIS=" + docaux.getItemValueDouble("montoinvalidez"));
								aux 	= (double) docaux.getItemValueDouble("montoinvalidez");
//								System.out.println("CPED- aux=" + aux);
								tot 	= tot + aux;
//								System.out.println("CPED- tot=" + tot);
//								System.out.println("CPED- 1totafp[Integer.parseInt(afp)][14]=" + totafp[Integer.parseInt(afp)][14]);
								totafp[Integer.parseInt(afp)][14] =	totafp[Integer.parseInt(afp)][14]	+	aux;
//								System.out.println("CPED- 2totafp[Integer.parseInt(afp)][14]=" + totafp[Integer.parseInt(afp)][14]);
//	FIN ADVISE
								totafp[Integer.parseInt(afp)][0] =	totafp[Integer.parseInt(afp)][0]	+	tot;
								totafp[Integer.parseInt(afp)][2] =	totafp[Integer.parseInt(afp)][2]	+	totc;

								//if((totp)>0)
									totafp[Integer.parseInt(afp)][1] ++ ;
								if (totc>0)
									totafp[Integer.parseInt(afp)][3] ++ ;
								if (tottp>0)
									totafp[Integer.parseInt(afp)][11] ++ ;
							}
							 else if (afp.equals("00") || afp.equals("90") ){
							 	double totcinp 	= 0;
							 	afpc 	=	docaux.getItemValueString("afpcesinpcod") ;
							 	if (afpc==null)
										afpc="";
								//System.out.println("calculando cesantia INP ruttra= " + rutact);
							 	aux 	= (double) docaux.getItemValueDouble("montoleyc_1_cesinp");			
							 	//System.out.println("valor montoleyc_1_cesinp=" + aux);
							 	//totafp[Integer.parseInt(afpc)][8] =	totafp[Integer.parseInt(afpc)][8]	+	aux;
								totcinp 		= totcinp + aux;						
								aux 	= (double) docaux.getItemValueDouble("montoleyc_2_cesinp");			
								//System.out.println("valor montoleyc_2_cesinp=" + aux);
								//totafp[Integer.parseInt(afpc)][9] =	totafp[Integer.parseInt(afpc)][9]	+	aux;
								totcinp 		= totcinp + aux;	
								//System.out.println("valor tot cesinp=" + totcinp);		
								if ( totcinp > 0 ) {
									/*afpc 	=	docaux.getItemValueString("afpcesinpcod") ;
									if (afpc==null)
										afpc=""; */
									//System.out.println("cod afp=" + afpc + ".");
									if ( !afpc.equals("")) {					
										totafp[Integer.parseInt(afpc)][0] =	totafp[Integer.parseInt(afpc)][0]	+	totcinp;
										totafp[Integer.parseInt(afpc)][2] =	totafp[Integer.parseInt(afpc)][2]	+	totcinp;
										totafp[Integer.parseInt(afpc)][1] ++ ;
										totafp[Integer.parseInt(afpc)][3] ++ ;
										cesinp[Integer.parseInt(afpc)] = true;
								//	System.out.println("SUME afpc=" + afpc + ".");
										//System.out.println("finalizada carga a afp=" + totafp[Integer.parseInt(afpc)][0]);
									}
								}
							 }
				//System.out.println("ProcesOOO-" + proceso );
							//ISAPRE						
							if ( !proceso.equals("D") )
								isa	=docaux.getItemValueString("isaprecod").trim() ;
							else
								isa	="90";
							
							if ( !isa.equals("00") && !isa.equals("") && !isa.equals("90")) {
								tot 	= 0;
								aux 	= (double) docaux.getItemValueDouble("montoisaobl");			
								tot 	= tot + aux;
								totisa[Integer.parseInt(isa)][2] =	totisa[Integer.parseInt(isa)][2]	+	aux;
								aux 	= (double) docaux.getItemValueDouble("montoisaadi");			
								tot 	= tot + aux;
								totisa[Integer.parseInt(isa)][3] =	totisa[Integer.parseInt(isa)][3]	+	aux;
								aux 	= (double) docaux.getItemValueDouble("montoisaley");			
								tot 	= tot + aux;	
								totisa[Integer.parseInt(isa)][4] =	totisa[Integer.parseInt(isa)][4]	+	aux;						
								totisa[Integer.parseInt(isa)][0] =	totisa[Integer.parseInt(isa)][0]	+	tot;
								totisa[Integer.parseInt(isa)][1] ++ ;
								aux 	= (double) docaux.getItemValueDouble("montoimp");	
								totisa[Integer.parseInt(isa)][5] =	totisa[Integer.parseInt(isa)][5]	+	aux;						
							}

							//INP
							if ( afp.equals("00")  ) { 
								//System.out.println("entrando en INP");
								montoinpcal 	= (double) docaux.getItemValueDouble("montoinp");			
								aux2 				= (int) docaux.getItemValueInteger("montoinptotal");			
								//inpotros	 		= (int) docaux.getItemValueInteger("montoinpotros");			
								inpley15386		= (int) docaux.getItemValueInteger("montoinp15386");			
						   		inpdesha 		= (int) docaux.getItemValueInteger("montoinpdes");		
								if ( isa.equals("00") ) { //FONASA
									totinpf	= totinpf	+ (montoinpcal + inpdesha - inpley15386);
									totinpft++;	
									totimpft	= totimpft	+ montoimp;	
									//se acumula el monto imponible para INP con Fonasa
									totinpfonimp		= 	totinpfonimp + montoimponible;
								}
								else {
									totinpi	= totinpi	+ (montoinpcal + inpdesha - inpley15386);
									totinpit++;
									//se acumula el monto imponible para INP con Isapre
									totinpisaimp		= 	totinpisaimp + montoimponible;
								}	
//	System.out.println("saliendo de INP");					
							}	
							//Fonasa SIN inp
						if ( isa.equals("00") &&  !afp.equals("00") ) {
							
							//montoinpcal 	= (int) docaux.getItemValueInteger("montoinp");			
							montoinpcal 	= (double) docaux.getItemValueDouble("montoinp");			
							//inpotros	 		= (int) docaux.getItemValueInteger("montoinpotros");	
							//System.out.println("monto=" + 	montoinpcal + ", otros= " + inpotros);				
							totfon	= totfon + montoinpcal;
							totfont++;
							//System.out.println("Rut trabajador(" + totfont + ")= "+  rutact + ", monto= " + totfon);
							totimpft	= totimpft	+ montoimp	+ montoimpaux;
							//se acumula el monto imponible para Fonasa sin INP
							totfonafpimp		= 	totfonafpimp + montoimponible;
						}
						//APV
						String listaapv="";
						aux 	= (double) docaux.getItemValueDouble("montoapv");			
						if ( aux > 0 ) {
								afp	=docaux.getItemValueString("apvcod") ;
								if (afp==null)
									afp="";
								if ( !afp.trim().equals("90") && !afp.trim().equals("00") && !afp.trim().equals("")) {
									totapv[Integer.parseInt(afp)][0] =	totapv[Integer.parseInt(afp)][0]	+	aux;
									totapv[Integer.parseInt(afp)][1] ++ ;
									listaapv= afp + ";";
								}
						}
						//APV otras
						 for (int i=1; i<=5;i++ ){
							 	aux 	= (double) docaux.getItemValueDouble("montoapv_"  + i);
										if ( aux > 0 ) {
											afp	=docaux.getItemValueString("apvcod_" + i);
											if (afp==null)
												afp="";
											if ( !afp.trim().equals("90") && !afp.trim().equals("00") && !afp.trim().equals("")) {
												totapv[Integer.parseInt(afp)][0] =	totapv[Integer.parseInt(afp)][0]	+	aux;
												if(listaapv.indexOf(afp)==-1){
													totapv[Integer.parseInt(afp)][1] ++ ;
													listaapv= listaapv + afp + ";";
												}
											}
										}	
							 }
		
		
						//Mutual
						
						if (!mutualcod.equals("90") && !proceso.equals("D") ){	
							montoacctra 		= (double) docaux.getItemValueDouble("montoacctra");			
							//montoacctratot		=	montoacctratot + Math.round(montoacctra+0.05);		
							montoacctratot		=	montoacctratot + montoacctra;
							totmutimp= totmutimp + montoimponiblemut;
							//System.out.println("monto imponible mutual= " + montoimponiblemut + ", total acumulado=" + totmutimp); 		
						}
						/* if (mutualcod.equals("01") && !isa.equals("00") && !proceso.equals("D")){
							totmutimp_sininp= totmutimp_sininp + montoimponiblemut;
							countermut ++;
						}*/

						
						
						
						
						
						//Caja 06, credito, leasing
					if ( !cajatrab.trim().equals("00")  && !cajatrab.trim().equals("99")) {
						//Monto 0,6%
							monto06 	= (double) docaux.getItemValueDouble("monto06");	
							if (monto06>0){		
								ccaf6 		=	ccaf6 + monto06;
								ccaf6t++;
							}
						//Credito
							montocreccaf	 	= (double) docaux.getItemValueDouble("montocrecatotal");						
	 					if (montocreccaf>0){
	 						ccafcret++;
							montocreccaft		=	montocreccaft + montocreccaf;
						}
						//Leasing
							montoleaccaf	 	= (double) docaux.getItemValueDouble("montoleacatot");	
						if (montoleaccaf>0){
							ccafleat++;					
							montoleaccaft		=montoleaccaft+montoleaccaf;
						}				
						
						//Seguros de vida
						montovidccaf 	= (double) docaux.getItemValueDouble("montovidcatot");			
						if ( montovidccaf > 0 ) {
								montovidccaft = montovidccaft + montovidccaf;
								ccafvidt++;
						}
						//Convenios Dentales
							montodenccaf 	= (double) docaux.getItemValueDouble("montodencatot");			
							if ( montodenccaf > 0 ) {
								montodenccaft = montodenccaft + montodenccaf;
								ccafdent++;
							}		

				
			
						//Otros Caja
							/*montootrccaf 	= (double) docaux.getItemValueDouble("montootrcatot");			
							if ( montootrccaf > 0 ) {
								montootrccaft = montootrccaft + montootrccaf;
								ccafotrt++;
							}*/
					}								
						
						
	  //  System.out.println(" cajatrab  ->" + cajatrab );        	
						if ( cajatrab.equals("00")  ) {		//no registro si es 00		
							double montoaf,montoaftotal;

							montoaf 				= (double) docaux.getItemValueDouble("montoinpaf");	
							monafret 			= (double) docaux.getItemValueDouble("montoafret");			
							monafrei 			= (double) docaux.getItemValueDouble("montoafrei");			
							montoaftotal 		= (double) docaux.getItemValueDouble("montoaftotal");
							
							
											
							
							
							//Se agrega if por concepto Bono Ley 20.012
							if (!afp.equals("00") ) {
								inpley15386		= (int) docaux.getItemValueInteger("montoinp15386");
								totasfaminp= totasfaminp + ( montoaf + monafret - monafrei) + inpley15386;
							}else{
								totasfaminp= totasfaminp + ( montoaf + monafret - monafrei);
							}
							totinpasfimp= totinpasfimp + montoimponible;
							//System.out.println("Caja para empresa es INP, cod mutual= " + mutualcod + ", monto asfaminp= " + totasfaminp  + ", monto mutual= " + montoacctra);
							/*if (mutualcod.equals("01")){
								montoacctratot= 0;
								totasfaminp= ( totasfaminp - montoacctra);
							}*/
							//System.out.println("monto rebajado asfam= " + totasfaminp);
							if (montoaf>0)
									asfaminpt++;
//			    System.out.println(" monto asfam  ->" + montoaf );        	
						}
						else{
							if ( !cajatrab.equals("90")  &&   !cajatrab.equals("")) {			
								double montoaf,montoaftotal;
								montoaf 				= (double) docaux.getItemValueDouble("montoaf");			
								monafret 			= (double) docaux.getItemValueDouble("montoafret");			
								monafrei 			= (double) docaux.getItemValueDouble("montoafrei");			
								montoaftotal 		= (double) docaux.getItemValueDouble("montoaftotal");			
								totccaf	= totccaf +( montoaf + monafret - monafrei);										
								if (montoaf>0)
									ccafasfamt++;
							}
						}	
				
				if ( proceso.equals("G") ) {		
					fecini					=	docaux.getItemValueString("fecini");
					fecter				=	docaux.getItemValueString("fecter");
				}
				
			 //   System.out.println(" monto asfam  ->" + totasfaminp );        	
			     //System.out.println(" RUT   ->" + rutact  );        	
																   
					} // if !rutact.equals(rutant) 
						 
				        entry = vec.getNextEntry(); 
				        rutant=rutact;
				        docaux.recycle();
				  }		
				  //System.out.println("PASO 2-"+totafp[Integer.parseInt(afp)][0]);
				  							
				return true;
		  }
		 catch(Exception e) {
			     System.out.println("CAI EN PROCESAR TRABAJADORES ->" + rutact );        	
			     e.printStackTrace();
			     return false;
	       }
		}
		
	   
	   public String BlancoCero( String texto) {
				// Si el string contiene blancos retorna el string "0"
				if (texto.trim().equals("")) 
					texto = "0";
				return texto;
	    }
	    public Double CambiaDouble( double valor) {
				// double -> Double
				Double aux = new Double(valor);
				return aux;
	     }
	  public Integer CambiaInt( int valor) {
				// int -> Integer
				Integer aux = new Integer(valor);
				return aux;
	     }
	      public String CambiarCaracteres( String texto) {
				// texto -> double
				texto = texto.replace('á','a');
				texto = texto.replace('é','e');
				texto = texto.replace('è','e');
				texto = texto.replace('í','i');
				texto = texto.replace('ó','o');
				texto = texto.replace('ú','u');
				texto = texto.replace('ü','u');
				texto = texto.replace('ñ','n');
				texto = texto.replace('Ñ','N');
				texto = texto.replace('Á','A');
				texto = texto.replace('É','E');
				texto = texto.replace('Í','I');
				texto = texto.replace('Ó','O');
				texto = texto.replace('Ú','U');
				texto = texto.replace('&','y');
				texto = texto.replace('(',' ');
				texto = texto.replace(')',' ');
				texto = texto.replace('[',' ');
				texto = texto.replace(']',' ');
				texto = texto.replace('\'',' ');
				
		//		texto = texto.replace('.',' ');
				
				texto = texto.replace('\'',' ');
				
				return texto;
	     }
	public static String getTodayMillisec() {
					return new java.util.Date().getTime() + "";
		}
	/*  public Int CambiaInteger( String texto ) {
				// texto -> integer
				Int d1;
				d1 = new Int(0);
				int d2;
				d1=d1.valueOf(texto);
				d2=d1.intValue();
				return d2;
	     }

	*/
	    public double Cambiadouble( String texto) {
				// texto -> double
				Double d1;
				d1 = new Double(0);
				double d2;
				d1=d1.valueOf(texto);
				d2=d1.doubleValue();
				return d2;
	     }

	  public String S( double d ) {
	 		if ( d > 0 ) {
				Double b = new Double(Math.round(d));
				Long l = new Long(Math.round(d));
				//String r = l.toString();
				String s =	l.toString();
//	System.out.println("\nEN ESE  CON d-"+ d );
				if ( s.indexOf('.') > 0 )
					if (s.substring(s.indexOf('.') ,s.length()).equals(".0") ) // Tiene terminacion.0
							s	= s.substring(0,s.indexOf('.') );
				if ( s.indexOf('E') > 0 )	
					s		= s.substring(0,s.indexOf('E'));
				if ( s.indexOf('.')> 0 )
					s		= s.substring(0, s.indexOf('.')) + s.substring( s.indexOf('.')+1,s.length());
				return s;
			}
			else
				return "0";
				
			}
			     
	    public double Cambiadou( Double valor) {
				// Double -> double
				double d2;
				d2=valor.doubleValue();
				return d2;
	     }

	    public int Cambiainteger( Double valor) {
				// Double -> int
				int d2;
				d2=valor.intValue();
				return d2;
	     }


	   public boolean CrearComprobante() {
			int i;
			boolean mensaje_cesinp=false;
			try {
//	System.out.println("TOTAL **"+totafp[7][0]);
				sfeinp="";
				sfeccaf="";
				if ( !IniciarComprobante() ) return false;
				//Totales AFP
				body.appendText("<tr><td class='label'>AFP</td><td class='label'>Pensiones</td><td class='label'>Cesantía</td></tr>");
				detalle.appendText("<tr><td colspan=11 class='columnTitle' style='border-bottom:solid #aaa 1pt'>AFP</td></tr>");
				detalle.appendText("<tr><td class='label' width='150'>Entidad</td><td class='label' align='right'>Obligatorio</td><td class='label'  align='right'>Ahorro</td><td class='label' align='center'>SIS</td><td class='label' align='right'>SubTotal</td><td class='label' align='right'>AFC Tra</td><td class='label' align='right'>AFC Emp</td><td class='label' align='right'>AFC SubTotal</td><td class='label' align='right'>Total</td><td class='label' align='center'>Nro. Tra.</td><td class='label' align='right'>Rem. Imp.</td></tr>");
				afptotf 	= 0;
				afpttotf 	= 0;
				afptotfc  = 0;
				afpttotfc = 0;
				afptotsubt =0;
				afptotftp  = 0;
				afpttotftp  = 0;
				afptotsis  = 0;
				for ( i=1; i< totafp.length ; i++) {
					if ( i <= 9 ) {
						nomitem0 ="af_0"+i;
						nomitem1 ="afp_0"+i;
						nomitem2 ="afpt_0"+i;
						nomitem3 ="afpc_0"+i;
						nomitem4 ="afptp_0"+i;
						nomitem5 ="afpsis_0"+i;
					}
					else {
						nomitem0 ="af_"+i;
						nomitem1 ="afp_"+i;
						nomitem2 ="afpt_"+i;					
						nomitem3 ="afpc_"+i;
						nomitem4 ="afptp_"+i;
						nomitem5 ="afpsis_"+i;
					}

//	System.out.println("tot-"+i+"-"+totafp[i][0]);
//	System.out.println("afplis[i]-"+afplis[i]);

					if ( (totafp[i][0] > 0 || totafp[i][2] > 0) && afplis[i] != null && !afplis[i].equals("INP") ) {
						doctotales.appendItemValue(nomitem0,afplis[i]);
						doctotales.appendItemValue(nomitem1,CambiaDouble(totafp[i][0]));
						doctotales.appendItemValue(nomitem2,CambiaDouble(totafp[i][1]));
						
						// agregar cesantia 20/10/2004
						doctotales.appendItemValue(nomitem3,CambiaDouble(totafp[i][2]));
						
						// agregar trabajos pesados 18/11/2004
						doctotales.appendItemValue(nomitem4,CambiaDouble(totafp[i][10]));
						
//	ADVISE		// agregar SIS 13/07/2009
//						System.out.println("CPED- CambiaDouble(totafp[i][14])=" + CambiaDouble(totafp[i][14]));
						doctotales.appendItemValue(nomitem5,CambiaDouble(totafp[i][14]));

						
						//totales AFP
						afptotf 	= afptotf + totafp[i][0];
						//totales trab AFP
						afpttotf 	= afpttotf + totafp[i][1];
						//Totales Cesantía
						afptotfc 	= afptotfc + totafp[i][2];
						//Totales trab Cesantía
						afpttotfc 	= afpttotfc + totafp[i][3];
						//Totales AFP - Cesantía -TP   +SIS
						afptotsubt	= afptotsubt + totafp[i][7] + totafp[i][14];
						//Totales TP
						afptotftp 	= afptotftp + totafp[i][10];
						//Totales trab TP
						afpttotftp 	= afptotftp + totafp[i][11];
						//Totales trab SIS Advise
						afptotsis 	= afptotsis + totafp[i][14];
						
						//xml
						comxml = comxml + "<AFP>"+
												  "<NOMBRE>"+afplis[i]+"</NOMBRE>" +
												  "<CODIGO>" + i + "</CODIGO>"+
						 						  "<MONTO>"+ ( totafp[i][0] - totafp[i][2] - totafp[i][10])  +"</MONTO>"+
						 						  "<TRABAJADORES>"+ totafp[i][1] +"</TRABAJADORES>"+
												  "</AFP>";
												  
						comxml = comxml + "<AFPC>"+
												  "<NOMBRE>"+afplis[i]+"</NOMBRE>" +
												  "<CODIGO>" + i + "</CODIGO>"+
						 						  "<MONTO>"+ totafp[i][2] +"</MONTO>"+
						 						  "<TRABAJADORES>"+ totafp[i][3] +"</TRABAJADORES>"+
												  "</AFPC>";
												  
						comxml = comxml + "<AFPTP>"+
												  "<NOMBRE>"+afplis[i]+"</NOMBRE>" +
												  "<CODIGO>" + i + "</CODIGO>"+
						 						  "<MONTO>"+ totafp[i][10] +"</MONTO>"+
						 						  "<TRABAJADORES>"+ totafp[i][11] +"</TRABAJADORES>"+
												  "</AFPTP>";
		
						if (cesinp[i]){
							asterisco="*";
							mensaje_cesinp=true;
							}
						else
							asterisco="";
							
						body.appendText("<tr><td>" + afplis[i] + "</td>");
						body.appendText("<td align='right'>" + numEntero(totafp[i][7]) + "</td>");
						body.appendText("<td align='right'>" + numEntero(totafp[i][2]) + "</td>");					
						body.appendText("<td align='right'>" + numEntero(totafp[i][0]) + "</td>");
						body.appendText("<td align='right'>" + numEntero(totafp[i][1]) + "</td></tr>");

						detalle.appendText("<tr><td>" + afplis[i] + "</td>");
						detalle.appendText("<td align='right'>" + numEntero(totafp[i][4]) + asterisco + "</td>");
						detalle.appendText("<td align='right'>" + numEntero(totafp[i][5]) + asterisco + "</td>");
						//ADVISE SIS
						detalle.appendText("<td align='right'>" + numEntero(totafp[i][14]) + asterisco + "</td>");
//	ADVISE
						//detalle.appendText("<td align='right'>" + numEntero(totafp[i][6]) + asterisco + "</td>");
						detalle.appendText("<td align='right'>" + (numEntero(totafp[i][7] + totafp[i][14])) + asterisco + "</td>");
						detalle.appendText("<td align='right'>" + numEntero(totafp[i][8]) + asterisco + "</td>");
						detalle.appendText("<td align='right'>" + numEntero(totafp[i][9]) + asterisco + "</td>");
						detalle.appendText("<td align='right'>" + numEntero(totafp[i][2]) + asterisco + "</td>");
						
						detalle.appendText("<td align='right'><b>" + numEntero(totafp[i][0]) + asterisco + "</b></td>");
						detalle.appendText("<td align='center'>" + numEntero(totafp[i][1]) + "</td>");
						detalle.appendText("<td align='right'>" + numEntero(totafp[i][13]) + asterisco + "</td></tr>");
						
					}
				}
				
				body.appendText("<tr><td class=\"label\"  align='right'  colspan=3>Total</td>"+
				"<td class=\"label\" align='right'>$" + numEntero(afptotf) + "</td>"+
				"<td class=\"label\" align='right'>" + numEntero(afpttotf) + "</td></tr>");
				//detalle.appendText("<tr><td class='label'>Totales</td><td class='label'>&nbsp;</td><td class='label'>&nbsp;</td><td class='label'>&nbsp;</td><td class='label'>&nbsp;</td><td class='label'>&nbsp;</td><td class='label'>&nbsp;</td><td class='label'>&nbsp;</td><td class='label' align='right' style='border-top:solid #aaa 1pt'>$" + numEntero(afptotf) + "</td></tr>");
				detalle.appendText("<tr><td align='left' class='label' colspan=3>Totales</td><td class='label' align='right' style='border-top:solid #aaa 1pt'>$" + numEntero(afptotsis) + "<td class='label' align='right' style='border-top:solid #aaa 1pt'>$" + numEntero(afptotsubt) + "</td><td class='label'>&nbsp;</td><td class='label'>&nbsp;</td><td class='label' align='right' style='border-top:solid #aaa 1pt'>$" + numEntero(afptotfc) + "</td></td><td class='label' align='right' style='border-top:solid #aaa 1pt'>$" + numEntero(afptotf) + "</td></tr>");	
//	MB	SIN SUBTOTALSIS			detalle.appendText("<tr><td align='left' class='label' colspan=4>Totales</td><td class='label' align='right' style='border-top:solid #aaa 1pt'>$" + numEntero(afptotsubt) + "</td><td class='label'>&nbsp;</td><td class='label'>&nbsp;</td><td class='label' align='right' style='border-top:solid #aaa 1pt'>$" + numEntero(afptotfc) + "</td><td class='label' align='right' style='border-top:solid #aaa 1pt'>$" + numEntero(afptotf) + "</td></tr>");	
				if (mensaje_cesinp)
					detalle.appendText("<tr><td align='left' colspan=4>*: incluye pago con cesantía en INP</td></tr>");
				//Totales Isapres
				body.appendText("<tr><td class='label' style='border-top:solid #aaa 1pt'  colspan=3>ISAPRE</td><td style='border-top:solid #aaa 1pt'>&nbsp;</td><td style='border-top:solid #aaa 1pt'>&nbsp;</td></tr>");
				detalle.appendText("<tr><td colspan=11 class='columnTitle' style='border-bottom:solid #aaa 1pt'>ISAPRE</td></tr>");
				detalle.appendText("<tr><td class='label'>Entidad</td><td class='label' align='right'>Obligatorio</td><td class='label' align='right'>Adicional</td><td class='label' align='right'>Bon. 2%</td><td class='label' align='right'>Total</td><td class='label' align='center'>Nro. Tra.</td><td class='label' align='right'>Rem. Imp.</td></tr>");
				isatotf = 0;
				isattotf = 0;
				for ( i=1; i< totisa.length ; i++) {
					if ( i <= 9 ) {
						nomitem1 ="isa_0"+i;
						nomitem2 ="isat_0"+i;
					}
					else {
						nomitem1 ="isa_"+i;
						nomitem2 ="isat_"+i;					
					}
					if ( totisa[i][0] > 0 ) {
						doctotales.appendItemValue(nomitem1,CambiaDouble(totisa[i][0]));
						doctotales.appendItemValue(nomitem2,CambiaDouble(totisa[i][1]));
						isatotf = isatotf 	+totisa[i][0];
						isattotf = isattotf +totisa[i][1];
						
						//xml
						comxml = comxml + "<ISAPRE>"+ 	"<NOMBRE>"+isalis[i]+"</NOMBRE>" +
													 	"<CODIGO>" + i + "</CODIGO>"+
														"<MONTO>"+totisa[i][0] +"</MONTO>"+
						 						 		 "<TRABAJADORES>"+ totisa[i][1] +"</TRABAJADORES>"+										
									 "</ISAPRE>";
				
						
						body.appendText("<tr><td  colspan=3>" + isalis[i] + "</td>");
						body.appendText("<td align='right'>" + numEntero(totisa[i][0]) + "</td>");
						body.appendText("<td align='right'>" + numEntero(totisa[i][1]) + "</td></tr>");
						detalle.appendText("<tr><td width='200'>" + isalis[i] + "</td>");
						detalle.appendText("<td align='right'>" + numEntero(totisa[i][2]) + "</td>");
						detalle.appendText("<td align='right'>" + numEntero(totisa[i][3]) + "</td>");
						detalle.appendText("<td align='right'>" + numEntero(totisa[i][4]) + "</td>");
						detalle.appendText("<td align='right'><b>" + numEntero(totisa[i][0]) + "</b></td>");
						detalle.appendText("<td align='center'>" + numEntero(totisa[i][1]) + "</td>");
						detalle.appendText("<td align='right'>" + numEntero(totisa[i][5]) + "</td></tr>");
					}
				}
				
				body.appendText("<tr><td class=\"label\" align='right'  colspan=3>Total</td>"+
				"<td class=\"label\" align='right'>$" + numEntero(isatotf) + "</td>"+
				"<td class=\"label\" align='right'>" + numEntero(isattotf) + "</td></tr>");
				detalle.appendText("<tr><td class='label' colspan='4'>Total</td><td class='label' align='right' style='border-top:solid #aaa 1pt'>$" + numEntero(isatotf) + "</td></tr>");			
				
				//Totales Otras entidades recaudadoras
				apvtotf=0;
				apvttotf=0;
				if (!(proceso.equalsIgnoreCase("G"))){
					body.appendText("<tr><td class='label' style='border-top:solid #aaa 1pt'  colspan=3>RECAUDADORAS APV</td><td style='border-top:solid #aaa 1pt'>&nbsp;</td><td style='border-top:solid #aaa 1pt'>&nbsp;</td></tr>");
					detalle.appendText("<tr><td colspan=11 class='columnTitle' style='border-bottom:solid #aaa 1pt'>RECAUDADORAS APV</td></tr>");
					detalle.appendText("<tr><td class='label'>Entidad</td><td class='label' align='right'>Total</td><td class='label' align='right'>Nro. Tra.</td></tr>");
					apvtotf = 0;
					apvttotf = 0;
					for ( i=1; i< totapv.length ; i++) {
						if ( totapv[i][0] > 0 ) {
							nomitem1 ="apvm_"+i;
							nomitem2 ="apvt_"+i;
							nomitem3 ="apv_"+i;
							doctotales.appendItemValue(nomitem1,CambiaDouble(totapv[i][0]));
							doctotales.appendItemValue(nomitem2,CambiaDouble(totapv[i][1]));
							doctotales.appendItemValue(nomitem3,apvlis[i]);
							apvtotf = apvtotf 	+totapv[i][0];
							apvttotf = apvttotf +totapv[i][1];
							
							//xml
							comxml = comxml + "<APV>" +
														 "<NOMBRE>" + apvlis[i] + "</NOMBRE>" +
														 "<CODIGO>" + i + "</CODIGO>" +
												 		 "<MONTO>"+ totapv[i][0] +"</MONTO>" +
												 		 "<TRABAJADORES>"+ totapv[i][1]+"</TRABAJADORES>" +
												"</APV>";
			
							
							body.appendText("<tr><td  colspan=3>" + apvlis[i] + "</td>");
							body.appendText("<td align='right'>" + numEntero(totapv[i][0]) + "</td>");
							body.appendText("<td align='right'>" + numEntero(totapv[i][1]) + "</td></tr>");
							detalle.appendText("<tr><td >" + apvlis[i] + "</td>");
							detalle.appendText("<td align='right'>" + numEntero(totapv[i][0]) + "</td>");
							detalle.appendText("<td align='right'>" + numEntero(totapv[i][1]) + "</td></tr>");
							//detalle.appendText("<td align='right'>" + numEntero(totapv[i][1]) + "</td></tr>");
						}
					}
					body.appendText("<tr><td class=\"label\" align='right'  colspan=3>Total</td>"+
					"<td class=\"label\" align='right'>$" + numEntero(apvtotf) + "</td>"+
					"<td class=\"label\" align='right'>" + numEntero(apvttotf) + "</td></tr>");
					detalle.appendText("<tr><td class='label'>Total</td><td class='label' align='right' style='border-top:solid #aaa 1pt'>$" + numEntero(apvtotf) + "</td></tr>");
					
					doctotales.appendItemValue("apvtot",CambiaDouble(apvtotf));
					doctotales.appendItemValue("apvttot",CambiaDouble(apvttotf));
				}
			
				doctotales.appendItemValue("afptot",CambiaDouble(afptotf));
				doctotales.appendItemValue("afpttot",CambiaDouble(afpttotf));
				doctotales.appendItemValue("isatot",CambiaDouble(isatotf));
				doctotales.appendItemValue("isattot",CambiaDouble(isattotf));
		
				montoacctratot =Math.round(montoacctratot);
				totinpf	= 	Math.round(totinpf);
				totinpi	=	Math.round(totinpi);
				totfon		=	Math.round(totfon);
		
				totgen	=	isatotf	+	afptotf	+	apvtotf; // afp,isapre,acctrab, apv
				totinp= totinpf	+	totinpi	+	totfon  - totbonextinp; //NEW BONO totbonextinp
				//System.out.println(" totinp total " + totinp);
				totinpt	= totinpft+totinpit+totfont;
				//System.out.println(" totinpt Trab " + totinpt);
				
				//MUTUAL
				if (mutualcod.equals("01") && !proceso.equals("D")){
							counter= countermut;						
							totmutimp= totmutimp_sininp;
				}
				if ( !mutualcod.equals("") && !mutualcod.equals("90")) { //si tiene mutual
					//System.out.println("tipo calculo mutual es : " + modcalmut ); 	
					if (modcalmut.equals("Total")){
						mutxtotal= totmutimp * ((tasamut+tasamutad)/100);
						montoacctratot= Math.round(mutxtotal);
					}
					if (mutualcod.equals("01")){
							doctotales.replaceItemValue("totmutinp",CambiaDouble(montoacctratot));
							doctotales.replaceItemValue("totmutinpt",  CambiaInt(counter));
							totinp= totinp + montoacctratot;
							montomutinp= montoacctratot;
							montoacctratot=0;
					}
					else{
							doctotales.replaceItemValue("totmut",CambiaDouble(montoacctratot));
							doctotales.replaceItemValue("totmutt",  CambiaInt(counter));
					}
				}
				else {
					doctotales.replaceItemValue("totmut",CambiaDouble(0));
					doctotales.replaceItemValue("totmutt",  CambiaInt(0));
				}
				totgen= totgen + montoacctratot;

//			System.out.println(" totgen 00    ->" + totgen );        	
				if ( cajatrab.trim().equals("") || cajatrab.trim().equals("00")) {
					// caja queda en 0
					doctotales.replaceItemValue("totccaf", CambiaDouble(0));
					doctotales.replaceItemValue("ccaf6",CambiaDouble(0));
					doctotales.replaceItemValue("ccafasfam",CambiaDouble(0));
					doctotales.appendItemValue("ccafcre",CambiaDouble(0));
					doctotales.appendItemValue("ccaflea",CambiaDouble(0));
					// se suma al inp asfam
					//doctotales.appendItemValue("totasfaminp",CambiaDouble(- totccaf));
					//totasfaminp= (double) (totasfaminp + inpley15386);
					//System.out.println(" grabando totasfaminp= " + totasfaminp);
					
					//NEW BONO
					//totbonextinp= (double) (totbonextinp + montobono);
					doctotales.replaceItemValue("totbonextinp",CambiaDouble(totbonextinp));
					doctotales.replaceItemValue("totbonextinpt",CambiaDouble(totbonextinpt));
					//System.out.println(" totbonextinp " + totbonextinp);
					//System.out.println(" totbonextinpt " + totbonextinpt);
					//totinp		= totinp - totbonextinp;
					//totinpt		= totinpt + totbonextinpt;
					
					doctotales.replaceItemValue("totasfaminp",CambiaDouble(totasfaminp));
					doctotales.replaceItemValue("totasfaminpt",CambiaDouble(asfaminpt ));
					totinp		= totinp - totasfaminp;
					//la siguiente linea se agregó 26/10
					doctotales.replaceItemValue("totinp",CambiaDouble(Math.abs(totinp)));
					if ( Math.round( totinp ) >= 0 ) {
						//la siguiente linea se comentó 26/10
						//doctotales.replaceItemValue("totinp",CambiaDouble((totinp)));
						//las siguientes 2  lineas se agregó 26/10
						doctotales.appendItemValue("sfeinp", "S.F.I");	
						sfeinp= sfi;
						}
	 				else{
						doctotales.appendItemValue("sfeinp", "S.F.E");	
						sfeinp= sfe;
						}
						/* las siguientes lineas se comentaron
					if ( Math.round( totinp ) >= 0 )  
						//totgen	=totgen + totinp;
						totgen	=totgen - totasfaminp ;
					else{
						doctotales.appendItemValue("sfeccaf", "S.F.E");
						sfeccaf= sfe;
						} */
			 }	
			 else {
			 	//totinp		= totinpf	+	totinpi	+	totfon;
				//totinpt	= totinpft	+	totinpit	+	totfont;
				/*if ( cajatrab.trim().equals("") || cajatrab.trim().equals("00")) {
					//System.out.println(" CAJA ES inp $" + totasfaminp + "-CAJA "+ cajatrab  );        	
					//No se considera asfam ni 06 %
					doctotales.replaceItemValue("totinp",CambiaDouble((totinp-totasfaminp)));
	 				doctotales.replaceItemValue("totccaf", CambiaDouble(0));
					doctotales.replaceItemValue("ccaf6",CambiaDouble(0));
					doctotales.replaceItemValue("ccafasfam",CambiaDouble(0));
					doctotales.appendItemValue("ccafcre",CambiaDouble(0));
					doctotales.appendItemValue("ccaflea",CambiaDouble(0));
					}				
				else {	*/
					doctotales.appendItemValue("ccafasfam",CambiaDouble(totccaf));	//inlcuye asfam + retroactivos -reintegros?
					doctotales.appendItemValue("ccafcre",CambiaDouble(montocreccaft));
					doctotales.appendItemValue("ccaflea",CambiaDouble(montoleaccaft));
					doctotales.appendItemValue("ccaf6", CambiaDouble(ccaf6));
					
					doctotales.appendItemValue("ccafvid",CambiaDouble(montovidccaft));
					doctotales.appendItemValue("ccafden",CambiaDouble(montodenccaft));
					//doctotales.appendItemValue("ccafotr",CambiaDouble(montootrccaft));
					//doctotales.appendItemValue("totccaf",CambiaDouble(montoleaccaft +montocreccaft +((0.6/100)*totimpft) - totccaf));
					
					//doctotales.replaceItemValue("totccaft", CambiaInt(totccaft));
					doctotales.replaceItemValue("ccaf6t",CambiaInt(ccaf6t));
					doctotales.replaceItemValue("ccafasfamt",CambiaInt(ccafasfamt));
					doctotales.appendItemValue("ccafcret",CambiaInt(ccafcret));
					doctotales.appendItemValue("ccafleat",CambiaInt(ccafleat));
					doctotales.appendItemValue("ccafdent",CambiaInt(ccafdent));
					doctotales.appendItemValue("ccafvidt",CambiaInt(ccafvidt));
					//se elimina concepto otros caja
					//doctotales.appendItemValue("ccafotrt",CambiaInt(ccafotrt));
					
					totalccaf=0;
					if (!cajatrab.trim().equals("02") && !cajatrab.trim().equals("00") ){ //caja distinta a Los Heroes
						//se calcula montos que se compensan
						totalsfe= montocreccaft + montoleaccaft + ccaf6 - totccaf;
						if (totalsfe > 0){
							//totalccaf = totalsfe + montodenccaft + montovidccaft  + montootrccaft;
							//se elimina concepto otros caja 22-11-2004
							totalccaf = totalsfe + montodenccaft + montovidccaft ;	
							doctotales.appendItemValue("sfeccaf",sfi); //marco SFI
							sfeccaf= sfi;
							doctotales.replaceItemValue("totccafsfe",CambiaDouble(totalsfe));
						}
						else{
							//totalccaf = montodenccaft + montovidccaft  + montootrccaft;
							//se elimina concepto otros caja 22-11-2004
							totalccaf = montodenccaft + montovidccaft;
							doctotales.appendItemValue("sfeccaf",sfe); //marco SFE
							sfeccaf= sfe;
							doctotales.replaceItemValue("totccafsfe",CambiaDouble(Math.abs(totalsfe)));
						}
					}
					else if (cajatrab.trim().equals("02")) { //caja Los Heroes
					//se calcula montos que se compensan
						totalsfe= ccaf6 - totccaf;
						if (totalsfe > 0){
							totalccaf = totalsfe + montocreccaft + montoleaccaft;
							doctotales.appendItemValue("sfeccaf",sfi); //marco SFI
							sfeccaf= sfi;
							doctotales.replaceItemValue("totccafsfe",CambiaDouble(totalsfe));
						}
						else{
							totalccaf = montocreccaft + montoleaccaft;
							doctotales.appendItemValue("sfeccaf",sfe); //marco SFE
							sfeccaf= sfe;
							doctotales.replaceItemValue("totccafsfe",CambiaDouble(totalsfe));
						}
					}
					doctotales.replaceItemValue("totccaf",CambiaDouble(totalccaf));			
										
					if ( Math.round(totalccaf) > 0 ) 
							totgen=totgen+ totalccaf; // + total caja
			//	}
			}
			
			//Total INP y verificando SFE o SFI
			if (totinp>0)
				totgen	=	totgen	+	totinp; 
			if ( Math.round( totinp ) >= 0 ) {
					doctotales.appendItemValue("sfeinp", "S.F.I");	
					sfeinp= sfi;
			}else{
					doctotales.appendItemValue("sfeinp", "S.F.E");	
					sfeinp= sfe;
			}
			doctotales.replaceItemValue("totinp",  CambiaDouble(Math.abs(totinp)));

			//INP en Body
			if ( Math.abs(totinp) > 0){
				
				body.appendText("<tr><td class='label' style='border-top:solid #aaa 1pt'  colspan=3>INP</td><td style='border-top:solid #aaa 1pt'>&nbsp;</td><td style='border-top:solid #aaa 1pt'>&nbsp;</td></tr>");
				detalle.appendText("<tr><td colspan=11 class='columnTitle' style='border-bottom:solid #aaa 1pt'>INP</td></tr>");
				detalle.appendText("<tr><td class='label'>Entidad</td><td class='label' align='right'>Total</td><td class='label' align='right'>Nro. Tra</td><td class='label' align='right'>Rem. Imp.</td></tr>");			
				//Fonasa sin INP
				if ( totfon > 0 ) {		
					comxml = comxml	+	"<INP><NOMBRE>Fonasa sin INP</NOMBRE>"+
												"<CODIGO>0</CODIGO>"+
												"<MONTO>"+totfon +"</MONTO>"+
												"<TRABAJADORES>"+totfont+"</TRABAJADORES></INP>";

					body.appendText("<tr><td  colspan=3>" + "Fonasa sin INP" + "</td>");
					body.appendText("<td align='right'>" + numEntero(totfon) + "</td>");
					body.appendText("<td align='right'>" + numEntero(totfont) + "</td></tr>");
				}
				detalle.appendText("<tr><td>" + "Fonasa sin INP" + "</td>");
				detalle.appendText("<td align='right'>" + numEntero(totfon) + "</td>");
				detalle.appendText("<td align='center'>" + numEntero(totfont) + "</td>");						
				detalle.appendText("<td align='right'>" + numEntero(totfonafpimp) + "</td></tr>");						
				//INPcon FONASA
				if ( totinpf > 0 ) {
					comxml = comxml + "<INP><NOMBRE>INP con FONASA</NOMBRE>"+
											"<CODIGO>0</CODIGO>"+
							     			"<MONTO>"+ totinpf +"</MONTO>"+
											"<TRABAJADORES>"+totinpft +"</TRABAJADORES></INP>";

					body.appendText("<tr><td  colspan=3>" + "INP con FONASA" + "</td>");
					body.appendText("<td align='right'>" + numEntero(totinpf) + "</td>");
					body.appendText("<td align='right'>" + numEntero(totinpft) + "</td></tr>");
				}
				detalle.appendText("<tr><td>" + "INP con FONASA" + "</td>");
				detalle.appendText("<td align='right'>" + numEntero(totinpf) + "</td>");
				detalle.appendText("<td align='center'>" + numEntero(totinpft) + "</td>");						
				detalle.appendText("<td align='right'>" + numEntero(totinpfonimp) + "</td></tr>");						
				//INP con Isapre
				if ( totinpi > 0 ) {
					comxml = comxml + 	"<INP><NOMBRE>INP con Isapre</NOMBRE>"+	
												"<CODIGO>0</CODIGO>"+
												"<MONTO>"+ totinpi	+"</MONTO>"+
												"<TRABAJADORES>"+ totinpit +"</TRABAJADORES></INP>";

					body.appendText("<tr><td  colspan=3>" + "INP con Isapre" + "</td>");
					body.appendText("<td align='right'>" + numEntero(totinpi) + "</td>");
					body.appendText("<td align='right'>" + numEntero(totinpit) + "</td></tr>");
				}
				detalle.appendText("<tr><td>" + "INP con Isapre" + "</td>");
				detalle.appendText("<td align='right'>" + numEntero(totinpi) + "</td>");
				detalle.appendText("<td align='center'>" + numEntero(totinpit) + "</td>");						
				detalle.appendText("<td align='right'>" + numEntero(totinpisaimp) + "</td></tr>");						
				//INP Asignación Familiar
				if ( totasfaminp > 0 && cajatrab.trim().equals("00")) {
					comxml = comxml	+	"<INP><NOMBRE>Asignación Familiar</NOMBRE>"+	
												"<CODIGO>0</CODIGO>"+
								 				"<MONTO>"+(totasfaminp)	+"</MONTO>"+
												"<TRABAJADORES> 0 </TRABAJADORES></INP>";

					body.appendText("<tr><td  colspan=3>" + "Asignación Familiar" + "</td>");
					body.appendText("<td align='right'>" + numEntero(totasfaminp) + "</td>");
					body.appendText("<td align='right'>" + "&nbsp;" + "</td></tr>");					
				}
				detalle.appendText("<tr><td>" + "Asignación Familiar" + "</td>");
				detalle.appendText("<td align='right'>" + numEntero(totasfaminp) + "</td>");
				detalle.appendText("<td align='center'>" + numEntero(asfaminpt) + "</td>");		
				detalle.appendText("<td align='right'>" + numEntero(totinpasfimp) + "</td></tr>");
				//INP Accidente del Trabajo
				if ( montomutinp > 0 && mutualcod.trim().equals("01")) {
					comxml = comxml	+	"<INP><NOMBRE>Accidente del Trabajo</NOMBRE>"+	
												"<CODIGO>0</CODIGO>"+
								 				"<MONTO>"+(montomutinp)	+"</MONTO>"+
												"<TRABAJADORES> " + numEntero(counter) + " </TRABAJADORES></INP>";

					body.appendText("<tr><td  colspan=3>" + "Accidente del Trabajo" + "</td>");
					body.appendText("<td align='right'>" + numEntero(montomutinp) + "</td>");
					body.appendText("<td align='right'>" + "&nbsp;" + "</td></tr>");					
				}
				detalle.appendText("<tr><td>" + "Accidente del Trabajo" + "</td>");
				detalle.appendText("<td align='right'>" + numEntero(montomutinp) + "</td>");
				detalle.appendText("<td align='center'>" + numEntero(counter) + "</td>");		
				detalle.appendText("<td align='right'>" + numEntero(totmutimp) + "</td></tr>");	
			
				//NEW Bono Extraordinario>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
				//INP Bono Extraordinario Ley 20.111
				if ( totbonextinp > 0 && cajatrab.trim().equals("00")) {
					body.appendText("<tr><td  colspan=3>" + "Bono Extraordinario Ley 20.111" + "</td>");
					body.appendText("<td align='right'>" + numEntero(totbonextinp) + "</td>");
					body.appendText("<td align='right'>" + numEntero(totbonextinpt) + "</td></tr>");
					detalle.appendText("<tr><td>" + "Bono Extraordinario Ley 20.111" + "</td>");
					detalle.appendText("<td align='right'>" + numEntero(totbonextinp) + "</td>");
					detalle.appendText("<td align='center'>" + numEntero(totbonextinpt) + "</td>");			
				}
						
				
							
				//detalle.appendText("<td align='right'>0</td>");
				//Totales INP
					body.appendText("<tr><td class='label'  align='right'  colspan=3>" + sfeinp + "Total</td>"+
					"<td class=\"label\" align='right'>$" + numEntero(Math.abs(totinp)) + "</td>"+
					"<td class=\"label\" align='right'>" + numEntero(totinpt) + "</td></tr>");	
					detalle.appendText("<tr><td class='label'>Total</td><td class='label' align='right' style='border-top:solid #aaa 1pt'>$" + numEntero(Math.abs(totinp)) + "</td></tr>");
			}
			
			//Mutual en Body
			if ( montoacctratot > 0 ) {
				comxml = comxml + "<MUTUAL><NOMBRE>"+mutnom+"</NOMBRE>"+
				 						 "<CODIGO>0</CODIGO>"+
										 "<MONTO>"+ montoacctratot +"</MONTO>"+
										 "<TRABAJADORES>"+ counter +"</TRABAJADORES>"+
										 "</MUTUAL>";

				body.appendText("<tr><td class='label' style='border-top:solid #aaa 1pt'  colspan=3>MUTUAL</td><td style='border-top:solid #aaa 1pt'>&nbsp;</td><td style='border-top:solid #aaa 1pt'>&nbsp;</td></tr>");
				body.appendText("<tr><td  colspan=3>" + mutnom + "</td>");
				body.appendText("<td align='right'>" + numEntero(montoacctratot) + "</td>");
				body.appendText("<td align='right'>" + numEntero(counter) + "</td></tr>");
				detalle.appendText("<tr><td colspan=11 class='columnTitle' style='border-bottom:solid #aaa 1pt'>MUTUAL</td></tr>");
				detalle.appendText("<tr><td class='label'>Entidad</td><td class='label' align='right'>Total</td><td class='label' align='center'>Nro. Tra.</td><td class='label' align='right'>Rem. Imp.</td></tr>");	
				detalle.appendText("<tr><td>" + mutnom + "</td>");
				detalle.appendText("<td align='right'><b>$" + numEntero(montoacctratot) + "</b></td>");	
				detalle.appendText("<td align='center'>" + numEntero(counter) + "</td>");
				detalle.appendText("<td align='right'>$" + numEntero(totmutimp) + "</td></tr>");	
			
				//Totales Mutual
				body.appendText("<tr><td class='label'  align='right'  colspan=3>Total</td>"+
				"<td class=\"label\" align='right'>$" + numEntero(montoacctratot) + "</td>"+
				"<td class=\"label\" align='right'>" + numEntero(counter) + "</td></tr>");
			}
			
			//CCAF en Body
			if ( totalsfe!=0 || totalccaf!=0 ) {
				comxml = comxml+tab+tab+"<CAJA>";
							
				body.appendText("<tr><td class='label' style='border-top:solid #aaa 1pt'   colspan=3>" + cajanom + "</td><td style='border-top:solid #aaa 1pt'>&nbsp;</td><td style='border-top:solid #aaa 1pt'>&nbsp;</td></tr>");
				detalle.appendText("<tr><td colspan=11 class='columnTitle' style='border-bottom:solid #aaa 1pt'>CCAF</td></tr>");
				detalle.appendText("<tr><td class='label'>Entidad</td><td class='label' align='right'>Créditos*</td><td class='label' align='right'>Leasing*</td><td class='label' align='right'>0,6%*</td><td class='label' align='right'>Asfam*</td><td class='label' align='right'>SubTotal " + sfeccaf +" (*)</td><td class='label' align='right'>Dentales</td><td class='label' align='right'>Seguros</td><td class='label' align='right'>Total</td><tr>");
				detalle.appendText("<tr><td>" + cajanom + "</td>");
				if ( montocreccaft > 0 ){
					comxml = comxml+"<CREDITOS><NOMBRE>Crédito</NOMBRE>"+
												"<CODIGO>0</CODIGO>"+
												"<MONTO>"+ montocreccaft	+"</MONTO>"+
												 "<TRABAJADORES>0</TRABAJADORES>"+									
										"</CREDITOS>";
			
					body.appendText("<tr><td  colspan=3>Créditos</td>");
					body.appendText("<td align='right'>" + numEntero(montocreccaft) + "</td>");
					body.appendText("<td align='right'>" + "&nbsp;" + "</td></tr>");
				}
				if ( montoleaccaft > 0 ){
						comxml = comxml+"<LEASING><NOMBRE>Leasing</NOMBRE>"+
												"<CODIGO>0</CODIGO>"+
												"<MONTO>"+ montoleaccaft +"</MONTO>"+
												 "<TRABAJADORES>0</TRABAJADORES>"+									
											"</LEASING>";
				
					body.appendText("<tr><td  colspan=3>Leasing</td>");
					body.appendText("<td align='right'>" + numEntero(montoleaccaft) + "</td>");
					body.appendText("<td align='right'>" + "&nbsp;" + "</td></tr>");
				}
		
		
				if ( ccaf6 != 0 ){
					comxml = comxml+"<PORCIENTO><NOMBRE> 6 porciento</NOMBRE>"+
												"<CODIGO>0</CODIGO>"+
												"<MONTO>"+ ccaf6 +"</MONTO>"+
												 "<TRABAJADORES>0</TRABAJADORES>"+									
											"</PORCIENTO>";
		
					body.appendText("<tr><td  colspan=3>0.6%</td>");
					body.appendText("<td align='right'>" + numEntero(ccaf6) + "</td>");
					body.appendText("<td align='right'>" + "&nbsp;" + "</td></tr>");
				}
				if ( totccaf != 0 ){
					comxml = comxml + "<ASIGNACION_FAMILIAR><NOMBRE>Asignación familiar</NOMBRE>"+
												 "<CODIGO>0</CODIGO>"+
												 "<MONTO>"+ Math.abs(totccaf) +"</MONTO>"+
												 "<TRABAJADORES>0</TRABAJADORES>"+									
											 "</ASIGNACION_FAMILIAR>";

					body.appendText("<tr><td  colspan=3>Asignación Familiar</td>");
					body.appendText("<td align='right'>" + numEntero(totccaf) + "</td>");
					body.appendText("<td align='right'>" + "&nbsp;" + "</td></tr>");
				}
					
				if (totalsfe!= 0 ){
					body.appendText("<tr><td class=\"label\"  align='right'  colspan=3>SubTotal " + sfeccaf + " (*)</td>"+
					"<td class=\"label\" align='right'>$" + numEntero(Math.abs(totalsfe)) + "</td>"+
					"<td class=\"label\" align='right'>" +"&nbsp;" + "</td></tr>");
				}
				if ( montodenccaft > 0 ){
					comxml = comxml + "<CONVENIOS_DENTALES><NOMBRE>Convenio Dental</NOMBRE>"+
											 "<CODIGO>0</CODIGO>"+
											 "<MONTO>"+ montodenccaft +"</MONTO>"+
											 "<TRABAJADORES>0</TRABAJADORES>"+																 
											 "</CONVENIOS_DENTALES>";
								
					body.appendText("<td  colspan=3>Convenios Dentales</td>");
					body.appendText("<td align='right'>" + numEntero(montodenccaft) + "</td>");
					body.appendText("<td align='right'>" + "&nbsp;" + "</td></tr>");
				}
				if ( montovidccaft > 0 ){
					comxml = comxml+"<SEGUROS_VIDA><NOMBRE>Seguros de Vida</NOMBRE>"+
												"<CODIGO>0</CODIGO>"+
												"<MONTO>"+ montovidccaft +"</MONTO>"+
											 	"<TRABAJADORES>0</TRABAJADORES>"+																 								
											"</SEGUROS_VIDA>";
					
					body.appendText("<td  colspan=3>Seguros de Vida</td>");
					body.appendText("<td align='right'>" + numEntero(montovidccaft) + "</td>");
					body.appendText("<td align='right'>" + "&nbsp;" + "</td></tr>");
				}
				//se elimina concepto otros caja
				/*if ( montootrccaft > 0 ){
					comxml = comxml + "<OTROS><NOMBRE>Otros</NOMBRE>"+
												"<CODIGO>0</CODIGO>"+
												"<MONTO>"+ montootrccaft +"</MONTO>"+
											 	"<TRABAJADORES>0</TRABAJADORES>"+																 								
											 "</OTROS>";
					
					body.appendText("<td  colspan=3>Otros</td>");
					body.appendText("<td align='right'>" + numEntero(montootrccaft) + "</td>");
					body.appendText("<td align='right'>" + "&nbsp;" + "</td></tr>");
				}*/
				// SFE no se usa para Tesoreria
				if (totalsfe != 0 ){
					if ( totalsfe < 0 ) {
						comxml = comxml + "<SFE><NOMBRE>SFE</NOMBRE>"+
												 "<CODIGO>0</CODIGO>"+
												 "<MONTO>"+ Math.abs(totalsfe) +"</MONTO>"+
												 "<TRABAJADORES>0</TRABAJADORES>"+									
											 "</SFE>";
					}
					else {
						comxml = comxml + "<SFE><NOMBRE>SFI</NOMBRE>"+
												"<CODIGO>0</CODIGO>"+
												 "<MONTO>"+ totalsfe +"</MONTO>"+
												 "<TRABAJADORES>0</TRABAJADORES>"+									
											 "</SFE>";
					}
				}		
				
				
				detalle.appendText("<td align='right'>" + numEntero(montocreccaft) + "</td>");
				detalle.appendText("<td align='right'>" + numEntero(montoleaccaft) + "</td>");
				detalle.appendText("<td align='right'>" + numEntero(ccaf6) + "</td>");
				detalle.appendText("<td align='right'>" + numEntero(Math.abs(totccaf)) + "</td>");
				detalle.appendText("<td align='right'>" + numEntero(Math.abs(totalsfe)) + "</td>");
				detalle.appendText("<td align='right'>" + numEntero(montodenccaft) + "</td>");
				detalle.appendText("<td align='right'>" + numEntero(montovidccaft) + "</td>");
				//se elimina concepto otros caja
				//detalle.appendText("<td align='right'>" + numEntero(montootrccaft) + "</td></tr>");
				//Total
				detalle.appendText("<td align='right'><b>$" + numEntero(totalccaf) + "</b></td></tr>");			
				//Totales CCAF
				//detalle.appendText("<tr><td class='label' align='left'  colspan=3>Total</td><td align='right'><b>$" + numEntero(totalccaf)  + "</b></td></tr>");
				detalle.appendText("<tr><td align='left' colspan=4>*: conceptos que se compensan</td></tr>");
				body.appendText("<tr><td class=\"label\"  align='right'  colspan=3>Total</td>"+
				"<td class=\"label\" align='right'>$" + numEntero(totalccaf) + "</td>"+
				"<td class=\"label\">" +"&nbsp;" + "</td></tr>");
							comxml = comxml+tab+"</CAJA>";
							
			}
						//Los SFE se dejan en 0;
						if (totinp<0)
							totinp=0;
						if (totalccaf<0)
							totalccaf=0;
							
				comxml = comxml+"</ENTIDAD>" +
						"<TOTALES>"+
						"<TOTAL_AFP>"+( afptotf - afptotfc - afptotftp )+"</TOTAL_AFP>" +
						"<TRABAJADORES_AFP>"+afpttotf+"</TRABAJADORES_AFP>" +
						"<TOTAL_AFPC>"+afptotfc+"</TOTAL_AFPC>" +
						"<TRABAJADORES_AFPC>"+afpttotfc+"</TRABAJADORES_AFPC>" +
						"<TOTAL_AFPTP>"+afptotftp+"</TOTAL_AFPTP>" +
						"<TRABAJADORES_AFPTP>"+afpttotftp+"</TRABAJADORES_AFPTP>" +
						"<TOTAL_ISAPRE>"+isatotf+"</TOTAL_ISAPRE>" +
						"<TRABAJADORES_ISAPRE>"+isattotf+"</TRABAJADORES_ISAPRE>" + 
						 "<TOTAL_INP>"+ totinp +"</TOTAL_INP>" +
						 "<TRABAJADORES_INP>"+totinpt +"</TRABAJADORES_INP>"+ 
	    					 "<TOTAL_MUTUAL>"+montoacctratot+"</TOTAL_MUTUAL>" +
						"<TRABAJADORES_MUTUAL>"+counter1+"</TRABAJADORES_MUTUAL>" +
					     "<TOTAL_CAJA>"+totalccaf+"</TOTAL_CAJA>"+
						"<TRABAJADORES_CAJA>"+0+"</TRABAJADORES_CAJA>" +					
						"<TOTAL_APV>"+ apvtotf +"</TOTAL_APV>"+					
						"<TRABAJADORES_APV>"+apvttotf+"</TRABAJADORES_APV>" +
						"</TOTALES><MONTO_TOTAL_COMPROBANTE>"+totgen+"</MONTO_TOTAL_COMPROBANTE>"+
						"<TOTAL_TRABAJADORES>"+counter+"</TOTAL_TRABAJADORES>" +
						"<REPRE_LEGAL>"+repleg+"</REPRE_LEGAL>" +
						"<FECHAHORA>"+fechahora.replace('/','-').replace(',',' ')+"</FECHAHORA>" +
						"</COMPROBANTE>";


			
			// LLamar a MQ
			comxml = CambiarCaracteres(comxml);		
			
			doctotales.replaceItemValue("MessageXML",comxml );				
			doctotales.replaceItemValue("Service","COMPROBANTE" );			
		
			//Fin comprobante en body
			body.appendText("</table></center></div>");
			body.appendText("<div style='margin-left:10pt;float:left;width:200pt;'>ESTE COMPROBANTE ES VALIDO HASTA ANTES DEL PLAZO LEGAL DE PAGO DE COTIZACIONES.<br><br><b>Total de Trabajadores: " + numEntero(counter) + "</b>"+
			"<div style='width:160pt;margin-top:20pt;padding:5pt;border-top:dotted 2px #aaa;display:block'>" + repleg + "<br>Representante Legal<br>" + fechahora + "</div>"+
			"</div>");
			body.appendText("<div style='margin-right:10pt;width:150pt;float:right'>MONTO TOTAL A PAGAR:<br><span style='font-size:11pt;font-weight:bold'>$ " + numEntero(totgen) + "</span><br /><br />N° Cheque Banco"+
			"<div style='width:160pt;margin-top:40pt;padding:5pt;display:block'> V°B° y Timbre Cajero</div></div><div style='clear:both;'>&nbsp;</div></div>]" );
			//Fin detalle comprobante
			detalle.appendText("</tr></table></center></div>");
			detalle.appendText("<div style='margin-left:10pt;float:left;width:200pt;'><br><b>Monto Total: $" + numEntero(totgen) + "</b><br><b>Total Trabajadores: " + numEntero(counter) + "</b>"+
			"<div style='width:160pt;margin-top:20pt;padding:5pt;border-top:dotted 2px #aaa;display:block'>" + fechahora + "</div>"+
			"</div>");
			detalle.appendText("</div>]" );
			
	 //   System.out.println(" monto totgen   ->" + totgen );        	
			
			
			doctotales.replaceItemValue("tpemontot", CambiaDouble(totgen ));	
		//	doctotales.appendItemValue("foliotes",foliotes);
			doctotales.appendItemValue("foliotes","0");
					
			doctotales.appendItemValue("creado","web");				
				
			doctotales.save( true, true );
			
			//Copiar a Cotiza el comprobante
			Document docrevisa= doctotales.copyToDatabase(db);
			docID=docrevisa.getNoteID();
			
						
			// Inicio de Envío ded correo con el link al comprobamte
			
	/*
	System.out.println("(1035 ) Inicio Envio correo");
			memo = dmail.createDocument();
			memo.replaceItemValue("form", "memo");
			memo.replaceItemValue("principal", "correo@laaraucana.cl");
			memo.replaceItemValue("from", "correo@laaraucana.cl");
			memo.replaceItemValue("copyto", "crmunoz@laaraucana.cl");
			memo.replaceItemValue("subject", "Comprobante de Pago "+ " " +rut+" "+ razonsoc);
			memo.replaceItemValue("recipients", "vgonzalez@laaraucana.cl");
			memo.replaceItemValue("body", "Comprobante de Pago : http://200.75.8.204/cotiza/cotiza.nsf/0/" + doctotales.getUniversalID());
			memo.save(true, true);		
	System.out.println("(1045) Fin Envio correo");
			memo.recycle();		
			//Fin de envío de correo
	*/
			doctotales.recycle();
			//System.out.println("FIN COMPROBANTE EN DEMANDA");
			return true;
			} catch(Exception e) {
				System.out.println("CAI EN COMPROBANTE EN DEMANDA");
				e.printStackTrace();
				return false;
			}
	   }

		public boolean IniciarComprobante() {
			try {
				String codproceso="";
				
				//Borrar listado de errrores al generar un comprobante
				BorrarListaErrores();
				BorrarComprobante();
		
			
				montoacctratot =Math.round(montoacctratot);
				totinpf	= 	Math.round(totinpf);
				totinpi	=	Math.round(totinpi);
				totfon		=	Math.round(totfon);
				ccaf6		=	Math.round(ccaf6);
			  	//doctotales = dbc.createDocument() ;
			  	doctotales = dbc.createDocument() ;
			  	body = doctotales.createRichTextItem("Body");
			  	detalle = doctotales.createRichTextItem("Detalle");
			  	codigo = doctotales.createRichTextItem("codigobarra");
				String codigobarra= "imagenes/" + folio.trim() + ".jpg";
				String IDDoc= doctotales.getUniversalID();
				try{
					codigo.embedObject(EmbeddedObject.EMBED_ATTACHMENT, null, codigobarra, folio);
					doctotales.replaceItemValue("codbar","<img src='/" + dbc.getFilePath() + "/0/" + IDDoc + "/$FILE/" + folio + ".jpg'>");
				}catch(Exception e){
					System.out.println(">>>>>>>>><NO existe Codigo de Barra: " + codigobarra);
					e.printStackTrace();
				}
				//Agregando Campo Lectores
				addReaders(doctotales);
				
			  	//Fecha de creacion
			  	String fecha;
				String hora;
				fecha= getTodayDMA('/');
				hora= getHHMMSS(':');
				fechahora= fecha + "," +hora;
				doctotales.replaceItemValue("fechahora",fecha+","+hora);
				
				//Periodo de Gratificaciones
				doctotales.replaceItemValue("inigra",fecini);
				doctotales.replaceItemValue("fingra",fecter);
				
			if (proceso.equals("D"))
					doctotales.replaceItemValue("Form","COMPC");
			else	
					doctotales.replaceItemValue("Form","TPE");		

		
				doctotales.replaceItemValue("tpeestado","V");
				doctotales.replaceItemValue("tpeemprut", rut);
				doctotales.replaceItemValue("tpeempnom", razonsoc);
				doctotales.replaceItemValue("convenio", convenio);
				doctotales.replaceItemValue("tpeper", periodo);
				doctotales.replaceItemValue("holding",holding);
				doctotales.replaceItemValue("nomholding",nomholding);
				doctotales.replaceItemValue("tpeempacteco", codacteco);
				doctotales.replaceItemValue("tperepleg",repleg);
				doctotales.replaceItemValue("tpeempdir", calle);
				doctotales.replaceItemValue("tpeempnum", numcalle);
				doctotales.replaceItemValue("tpeemploc", local);
				doctotales.replaceItemValue("tpeempcom", comuna);
				doctotales.replaceItemValue("tpeemciu", ciud);
				doctotales.replaceItemValue("tpeempreg", reg);
				doctotales.replaceItemValue("tpeemtel",tel);
				doctotales.replaceItemValue("tpetott", CambiaInt(counter));
				doctotales.replaceItemValue("tpemontot", CambiaDouble(0));
				doctotales.replaceItemValue("numcompag", folio);	
				doctotales.replaceItemValue("modpago","Mixta");
				doctotales.replaceItemValue("estado","0");
				//System.out.println("holding  " + holding + ", empresa" + razonsoc  + ", mutual= " + mutualcod);

				//doctotales.replaceItemValue("totmut",CambiaDouble(Math.round(montoacctratot)  ));
				//doctotales.replaceItemValue("totmutt",  CambiaInt(counter));
				//caja
				doctotales.replaceItemValue("totccaf", CambiaDouble(totccaf + ccaf6));
				doctotales.replaceItemValue("ccaf6",CambiaDouble(ccaf6));
				doctotales.replaceItemValue("ccafasfam",CambiaDouble(totccaf));
				doctotales.appendItemValue("ccafcre",CambiaDouble(montocreccaft));
				doctotales.appendItemValue("ccaflea",CambiaDouble(montoleaccaft));
				//inp
				doctotales.replaceItemValue("totinp",  CambiaDouble(totinpf + totinpi + totfon - totbonextinp));
				doctotales.replaceItemValue("totinpf", CambiaDouble(totinpf));	
				doctotales.replaceItemValue("totinpi", CambiaDouble(totinpi));
				doctotales.replaceItemValue("totinpft", CambiaDouble(totinpft));	
				doctotales.replaceItemValue("totinpit", CambiaDouble(totinpit));
				doctotales.replaceItemValue("totfon", CambiaDouble(totfon));
				doctotales.replaceItemValue("totfont", CambiaDouble(totfont));
				doctotales.replaceItemValue("totinp", CambiaDouble(totinpf+totinpi+totfon));
				doctotales.replaceItemValue("totinpt", CambiaDouble(totinpft+totinpit+totfont)); //NEW BONO
				doctotales.appendItemValue("totasfaminp",CambiaDouble(0));
				doctotales.replaceItemValue("totbonextinp", CambiaDouble(0)); //NEW BONO
				//System.out.println(" doctotales totbonextinp:= " totbonextinp);
				
				doctotales.appendItemValue("caja",cajanom);
				doctotales.appendItemValue("cajacod",cajatrab);
				doctotales.appendItemValue("mutual",mutnom);
				doctotales.appendItemValue("mutualcod",mutualcod);				
				doctotales.appendItemValue("proceso",proceso);				
				
				doctotales.save( true, true );	
				//xml
					comxml = "<COMPROBANTE><HEADER>"+
								"<FOLIOTES>"+foliotes+"</FOLIOTES>"+
							       "<CODIGOBARRA>"+codigobarraant+"</CODIGOBARRA>"+
							       "<NUMCOMPAG>"+folio+"</NUMCOMPAG>"+
								"<RUT>"+rut+"</RUT>"+
								"<RAZON_SOCIAL>"+razonsoc+"</RAZON_SOCIAL>"+
								"<CONVENIO>"+convenio+"</CONVENIO>"+
								"<CIUDAD>"+ciud+"</CIUDAD>"+
								"<REGION>"+reg+"</REGION>"+
								"<TELEFONO>"+tel+"</TELEFONO>"+
								"<TIPO_PROCESO>"+proceso+"</TIPO_PROCESO>"+
								"<CODIGO_PROCESO>"+periodo+"</CODIGO_PROCESO>"+
							"</HEADER><ENTIDAD>";	
		
				// Body
				styTitle 		= s.createRichTextStyle();		
				styNormal 	= s.createRichTextStyle();
				stySubTitle 	= s.createRichTextStyle();	
				
				styTitle.setBold(RichTextStyle.YES);
				styTitle.setColor(RichTextStyle.COLOR_DARK_BLUE);
	        		styTitle.setEffects(RichTextStyle.EFFECTS_SHADOW);
	        		styTitle.setFont(RichTextStyle.FONT_COURIER);
	        		styTitle.setFontSize(14);	
				
				styNormal.setBold(RichTextStyle.NO);
				styNormal.setColor(RichTextStyle.COLOR_BLACK);
	        		styNormal.setFont(RichTextStyle.FONT_COURIER);
	        		styNormal.setFontSize(10);				

				stySubTitle.setBold(RichTextStyle.YES);
				stySubTitle.setColor(RichTextStyle.COLOR_DARK_BLUE);
	        		stySubTitle.setEffects(RichTextStyle.EFFECTS_SHADOW);
	        		stySubTitle.setFont(RichTextStyle.FONT_COURIER);
	        		stySubTitle.setFontSize(10);	

				body.appendText(""+
	"			[<div><style>"+
	"a {color:#aaa;; text-decoration: none; font-family:Verdana,tahoma, arial;font-size:11px;"+
	"}"+
	"body, td{"+
	"font-family:Verdana, tahoma, arial;"+
	"font-size:11px;"+
	"color:navy;"+
	"border:none;"+
	"}"+
	"table{"+
	"width:90%;"+
	"border:none;"+
	"}"+
	"#mainDiv{"+
	"width:90%;"+
	"text-align:left;"+
	"border:double #bbb 3px;"+
	"padding:0px;"+
	"}"+
	"hr{"+
	"line-style:double;"+
	"height:3px;"+
	"width:100%;"+
	"}"+
	"#mainTitle{"+
	"float:left;"+
	"}"+
	"#barCode{"+
	"float:right;"+
	"}"+
	"#cellTitle{"+
	"font-weight:bold;"+
	"background-color:#ccc;"+
	"}"+
	".subTitle{"+
	"background-color:#eee;"+
	"}"+
	".label{"+
	"font-weight:bold;"+
	"}"+
	".columnTitle{"+
	"font-weight:bold;"+
	"background-color:#ccc;"+
	"}"+
	"</style>"+
	"<div id='mainDiv'><br />"+

	"<table><tr><td><div style='font-size:12pt;font-weight:bold;margin-bottom:4pt'>COMPROBANTE DE PAGO DE COTIZACIONES</div></td>"
				);
	body.appendText("<td align=right><div style='float:right;margin-right:20px'>" + folio + "</div><br />");
	body.appendText("<div style='float:right;margin-right:20px'><img src='/" + dbc.getFilePath() + "/0/" + IDDoc + "/$FILE/" + folio + ".jpg'></div></td></tr>");
	detalle.appendText(""+
	"			[<div><style>"+
	"a {color:#aaa;; text-decoration: none; font-family:Verdana,tahoma, arial;font-size:11px;"+
	"}"+
	"body, td{"+
	"font-family:Verdana, tahoma, arial;"+
	"font-size:11px;"+
	"color:navy;"+
	"border:none;"+
	"}"+
	"table{"+
	"width:90%;"+
	"border:none;"+
	"}"+
	"#mainDiv{"+
	"width:90%;"+
	"text-align:left;"+
	"border:double #bbb 3px;"+
	"padding:0px;"+
	"}"+
	"hr{"+
	"line-style:double;"+
	"height:3px;"+
	"width:100%;"+
	"}"+
	"#mainTitle{"+
	"float:left;"+
	"}"+
	"#barCode{"+
	"float:right;"+
	"}"+
	"#cellTitle{"+
	"font-weight:bold;"+
	"background-color:#ccc;"+
	"}"+
	".subTitle{"+
	"background-color:#eee;"+
	"}"+
	".label{"+
	"font-weight:bold;"+
	"}"+
	".columnTitle{"+
	"font-weight:bold;"+
	"background-color:#ccc;"+
	"}"+
	"</style>"+
	"<div id='mainDiv'><br />"+

	"<table><tr><td><div style='font-size:12pt;font-weight:bold;margin-bottom:4pt'>DETALLE VALIDACION COMPROBANTE DE PAGO</div></td>"
				);
	if (proceso.equalsIgnoreCase("R"))
		codproceso="Remuneraciones";
	else if (proceso.equalsIgnoreCase("G"))
		codproceso="Gratificaciones";
		else if (proceso.equalsIgnoreCase("D"))
			codproceso="Depósitos Convenidos";

	body.appendText("<tr><td colspan=2><div style='font-size:11pt;font-weight:bold;margin-bottom:4pt'>" + codproceso + " Período:" + periodo + "</div></td></tr></table>");
	if (proceso.equalsIgnoreCase("G")){
		body.appendText("<div style='font-size:11pt;font-weight:bold;margin-bottom:4pt'>Correspondiente a " + fecini + " al " + fecter + "</div>");
	}
	body.appendText("<div style='display:block;margin-top:3pt;padding-top:3pt;border-top:double #aaa 3px'><table>");
	body.appendText("<tr><td><div style='font-size:12px;font-weight:bold'>Datos del Holding</div></td></tr>"+
	"<tr><td class='label'>Código: </td><td>" + holding + "</td><td class='label'>Nombre: </td><td>" + nomholding + "</td></tr>");
	body.appendText("<tr><td><div style='font-size:12px;font-weight:bold'>Datos de la Empresa</div></td></tr>"+
	"<tr><td class='label' >Rut: </td><td>" + rut + "</td><td class='label'>Razón Social: </td><td>" + razonsoc + "</td><td class='label'>Convenio: </td><td>" + convenio + "</td></tr>" + 
	"<tr><td class='label'>Ciudad:</td><td>" + ciud + "</td><td class='label'>Región:</td><td>" + reg + "</td><td class='label'>Teléfono:</td><td>" + tel + "</td></tr>" +
	"</table></div>");
	body.appendText("<div style='display:block;margin-top:3pt;margin-bottom:4pt;padding-top:3pt;border-top:double #aaa 3px;border-bottom:double #aaa 3px'>");
	body.appendText("<center><table>");
	body.appendText("<tr><td class='columnTitle' colspan=3>Entidad Previsional</td><td class='columnTitle'>Monto($)</td><td class='columnTitle'>N° Trabajadores</td></tr>");
//	 fin body			 
	detalle.appendText("<td align=right><div style='float:right;margin-right:20px'>" + folio + "</div><br />");
	detalle.appendText("<div style='float:right;margin-right:20px'><img src='/" + dbc.getFilePath() + "/0/" + IDDoc + "/$FILE/" + folio + ".jpg'></div></td></tr>");
	detalle.appendText("<tr><td colspan=2><div style='font-size:11pt;font-weight:bold;margin-bottom:4pt'>" + codproceso + " Período:" + periodo + "</div></td></tr></table>");
	detalle.appendText("<div style='display:block;margin-top:3pt;padding-top:3pt;border-top:double #aaa 3px'><table>");
	detalle.appendText("<tr><td><div style='font-size:12px;font-weight:bold'>Datos del Holding</div></td></tr>"+
	"<tr><td class='label'>Código: </td><td>" + holding + "</td><td class='label'>Nombre: </td><td>" + nomholding + "</td></tr>");
	detalle.appendText("<tr><td><div style='font-size:12px;font-weight:bold'>Datos de la Empresa</div></td></tr>"+
	"<tr><td class='label'>Rut: </td><td>" + rut + "</td><td class='label'>Razón Social: </td><td>" + razonsoc + "</td><td class='label'>Convenio: </td><td>" + convenio + "</td></tr>" + 
	"</table></div>"
	);
	detalle.appendText("<div style='display:block;margin-top:3pt;margin-bottom:4pt;padding-top:3pt;border-top:double #aaa 3px;border-bottom:double #aaa 3px'>");
	detalle.appendText("<center><table border=1>");
//	 fin detalle
		return true;
			}
			catch(Exception e) {
				System.out.println("CAI EN INICIAR COMPROBANTE");
				e.printStackTrace();
				return false;
			}
	   }

		public boolean VerEmpresa() {
			try {
				// Traigo datos de empresa
				rutmap	=	rut;
				view		=	dbc.getView("EMPXH");
				 llave = new Vector();
				 llave.addElement(codholding);
			      llave.addElement(rut);
			      llave.addElement(convenio);
	 			 entry 		=	view.getEntryByKey(llave,true);

			      if ( entry  != null ) {
					Document doc	=	entry.getDocument();
					razonsoc		=CambiarCaracteres(doc.getItemValueString("razonsocemp" )) ;
					razonsoc		=razonsoc.replace('.',' ');
					codacteco		=doc.getItemValueString("actecocod") ;
					repleg			=doc.getItemValueString("nomrepleg" ) + " " + doc.getItemValueString("patrepleg") +" " + doc.getItemValueString("matrepleg");
					calle				=CambiarCaracteres(doc.getItemValueString("dempcalle")) ;
					numcalle		=doc.getItemValueString("dempnum") ;
					local				=doc.getItemValueString("demplocal") ;
					comuna		=CambiarCaracteres(doc.getItemValueString("dempcomuna")) ;
				 	ciud				=CambiarCaracteres(doc.getItemValueString("dempciudad")) ;
					reg				=doc.getItemValueString("dempregion") ;
					tel				=doc.getItemValueString("emptel") ;
					mutual			=doc.getItemValueString("insacctrabemp") ;
					mutualcod	=doc.getItemValueString("insacctracod") ;
					modpago= doc.getItemValueString("modpago") ;
					if (mutual == null ) mutual = "";
					if (mutualcod == null ) mutualcod = "";
					
					//Cálculo de la Mutual por Total o Individual
					modcalmut			=doc.getItemValueString("modcalmut") ;
					
					mail			=	doc.getItemValueString("emailencemp") ;
					holding		=	doc.getItemValueString("emphol") ;
					nomholding		=	doc.getItemValueString("empholnom") ;
					
					if (holding == null ) holding = "999";
						cajaemp		=	doc.getItemValueString("ccafcod");

					Item iteme = doc.getFirstItem("ccafcod");
					cajaemp =iteme.getValueString();
					if (cajaemp == null )  
						cajaemp = "";				
					else 	
						cajatrab		=cajaemp.trim();
					
					cajanom		=	doc.getItemValueString("cajacomsuc");
					mutnom		=	doc.getItemValueString("insacctrabemp");			
					tasamut		=doc.getItemValueDouble("tasafat");
					tasamutad	=doc.getItemValueDouble("tasaaat");	
		     	     
					//rutcodigos	= rut.trim();
//	System.out.println("PROCESO " + proceso + " HOLDING "+ holding +" EMPRESA "+rut + "CAJA "+ cajaemp);
					if ( !holding.trim().equals("999") ) 
						rutmap=holding.trim();

	/*System.out.println("antes de CAMBIARE CAJA ");
					viewcon			=	dbc.getView("CON");
					entrycon 		= 	viewcon.getEntryByKey(rut+convenio);
				      if (entrycon != null) {			      	
//	System.out.println("CAMBIARE CAJA " + rut + convenio +"  CAJA->"+ cajaemp);
		  				vcon = entrycon.getColumnValues();
						cajaemp = vcon.elementAt(1).toString(); // se considera la CCAF del convenio	
	cajanom		=	vcon.elementAt(2).toString();								
//	System.out.println("CAMBIE CAJA " + rut + convenio + "  CAJA->"+cajaemp);

					}
	*/


					}	
			else {
						System.out.println("No hay datos de empresa "+ rut);
						return false;
				}
				//Inicializar contadores
				int i, j;
				for ( i=1; i< totafp.length ; i++)	 {
					for ( j=0; j< totafp[1].length ; j++)
						totafp[i][j]  		= 0;
					cesinp[i]			=false;
				}
				for ( i=1; i< totisa.length ; i++) 	{
					for ( j=0; j< totisa[1].length ; j++)
						totisa[i][j]  		= 0;
				}
				for ( i=1; i< totapv.length ; i++) 	{
					totapv[i][0] =	0;
					totapv[i][1] =	0;						
				}
				for ( i = 0; i< estcam.length; i++ ) {
					estcam[i]		=	1;
					mailarchivo[i]	=	"";
				}
				for ( i = 0; i< aviso.length; i++ ) 
					aviso[i]		=	"";
					
				entry.recycle();	
						
				return true;
			}	
			catch(Exception e) {
				System.out.println("CAI EN VER EMPRESA DE COMPROBANTE");
				e.printStackTrace();
				return false;
			}
	   }
	  
	public static String getTodayDMA(char sep) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		return "" + (c.get(c.DAY_OF_MONTH) < 10 ? "0" : "") + c.get(c.DAY_OF_MONTH) + sep + (c.get(c.MONTH) < 9 ? "0" : "") + (c.get(c.MONTH) + 1) + sep + c.get(c.YEAR);
	}

	public static String getHHMMSS(char sep) {
		StringBuffer hhmmss= new StringBuffer();
		hhmmss.append("HH").append(sep).append("mm").append(sep).append("ss");
		//formato que va ha tener el tiempo
		java.text.DateFormat formato=new java.text.SimpleDateFormat(hhmmss.toString());		java.util.Calendar cal =java.util.Calendar.getInstance();
		java.util.Date hms= cal.getTime();
		String hora=formato.format(hms);	
		return hora;
	}

	public static String numEntero(double valued) {
		String value= Double.toString(valued);
		java.text.DecimalFormat dec;
		java.lang.Double valor = null;

		//
		dec = new java.text.DecimalFormat();
		dec.applyPattern("#,##0");
		valor = java.lang.Double.valueOf(value);

		//
		return dec.format(valor.doubleValue());
	}   

	public String GenerarNroComprobante(){
	try{
		View v 	= dbc.getView("NACP");
		Document docnum= v.getDocumentByKey("NA")	;
		String nro= docnum.getItemValueString("numcodpago");
		double valornuevo= java.lang.Double.valueOf(nro).doubleValue() + 1;
		java.text.DecimalFormat dec = new java.text.DecimalFormat();
		dec.applyPattern("#");
		docnum.replaceItemValue("numcodpago", dec.format(valornuevo));
		docnum.save( true, true );
		return dec.format(valornuevo);
	}
	catch(Exception e){
		System.out.println( "Error en GenerarNroComprobante. Mensaje: " );
		e.printStackTrace();
		return "";
	}
	}

	   		public boolean BorrarComprobante() {
			try {
				Document docaux ;
				//Borra el comprobante 
	     		v			= new Vector();
			      v.addElement(holding);
				 v.addElement(rut);
				 v.addElement(convenio);
				 v.addElement(proceso);			 
				 View view 	= dbc.getView("ICPE");
				 view.refresh();
			      ViewEntryCollection vec 		= view.getAllEntriesByKey(v, true);	
			      foliotes ="0";
			      codigobarraant ="0";
				 if ( vec.getCount() > 0 ) { // tenia comproabante en base cotiza
				 		entry 		=	vec.getFirstEntry();
						if (entry != null && entry.isDocument()) {
						 		docaux= entry.getDocument();
			     				foliotes	=	docaux.getItemValueString("foliotes");
			     				codigobarraant = docaux.getItemValueString("numcompag"); ; //código de barra anterior     				
			     				// Copiarlo a la base consulta
			     				try{
			     						docaux.copyToDatabase(dbcon);
			     				/*	if (!foliotes.equals("0")){
										CALLPGM call=  new CALLPGM();
										call.pgm(foliotes, "CPED", holding, rut, convenio, proceso);
									}*/
			     				}catch(Exception e){
									System.out.println("BASE CONSULTA NO DISPONIBLE, COMPROBANTE NO RESPALDADO");
								}
								docaux.recycle();
								vec.getNextEntry();
			     		}
						
			     		vec.removeAll(true);
				}
				else { // reviso si tiene comprobante en base consulta
					 view 	= dbcon.getView("ICPE");
					 view.refresh();
				      vec 		= view.getAllEntriesByKey(v, true);	
	      			 if ( vec.getCount() > 0 ) { // tenia comprobante en base consulta
				 		entry 		=	vec.getFirstEntry();
						if (entry != null && entry.isDocument()) {
						  		docaux= entry.getDocument();
			     				foliotes	=	docaux.getItemValueString("foliotes");
			     				codigobarraant = docaux.getItemValueString("numcompag"); ; //código de barra anterior     				
			     		}
			     	}
				}	
		
	  //System.out.println( ".... BORRANDO COMPROBANTE ->  "+ vec.getCount()  );
				 //vec.removeAll(true);
				 vec.recycle();
				return true;
			} catch(Exception e) {
				System.out.println("CAI EN BORRAR COMPROBANTE ");
				e.printStackTrace();
				return false;
			}
	   }
		

		public boolean BorrarListaErrores() {
			try {
				//Borra el Listado de errores
	     		 v	= new Vector();
			      v.addElement(holding);
				 v.addElement(rut);
				 v.addElement(convenio);			 
				 v.addElement(proceso);			 
//		System.out.println("VECTOR "+v);
				 view 	= dbc.getView("ILEE"); 
				 view.refresh();
			
			      ViewEntryCollection vec = view.getAllEntriesByKey(v, true);			
				 ViewEntry entry = vec.getFirstEntry();
			      while (entry != null) {
					  docaux = entry.getDocument();
				      //Copiarlo a la Base de Consulta
	      			  docaux.copyToDatabase(dbcon);
	     		      //Borrralo 
		 			  docaux.remove(true);
					  docaux.recycle();
				       entry = vec.getNextEntry(); 
			     	 }
			      
				//System.out.println( "..RESPALDANDO Y BORRANDO "+ vec.getCount() + " LISTADO DE ERRORRES" );
				 //vec.removeAll(true);
				 vec.recycle();	
				return true;
			} catch(Exception e) {
				System.out.println("CAI EN BORRAR LISTA ERRORES");
				e.printStackTrace();
				return false;
			}
	   }
		public void borrarDocSubmitido() {
			try {
						String vs	= codholding + "#" + rut + "#" + convenio + "#" + proceso;
		     		 	/* viewsub			=	db.getView("COMONDOK");
						vecsub		= 	viewsub.getAllEntriesByKey(vs, true);
						vecsub.removeAll(true);*/
						//System.out.println("llave de busqueda comprobantes submitidos= " + vs);
						viewsub			=	db.getView("COMONDF");
						vecsub		= 	viewsub.getAllEntriesByKey(vs, true);
						vecsub.removeAll(true);
						//vecsub.refresh();
			} catch(Exception e) {
				System.out.println("CAI EN BORRAR DOC SUBMITIDO");
				e.printStackTrace();
			}
		}
		public void addReaders(Document doc){
			Item item;
			try{
				String llave= holding + "#" + rut + "#" + convenio;
				View view 	= dbc.getView("EMPXH#E#C");
				Document docemp= view.getDocumentByKey(llave, true);
				item = docemp.getFirstItem("$Admin");
	        		item.copyItemToDocument(doc);
				item = docemp.getFirstItem("$Readers");
	        		item.copyItemToDocument(doc);
			}catch(Exception e) {
				System.out.println("CAI EN addReaders()");
				e.printStackTrace();
			}
		}

		public boolean verificaComprobanteCursado(String codholding, String rutempresa, String convenio, String proceso){
			try{
				Vector llave = new Vector();
				llave.addElement(proceso);
				llave.addElement(codholding);
				llave.addElement(rutempresa);
				llave.addElement(convenio);
				//System.out.println("Verificando comprobante, llave:" + llave);
				View view 	= dbc.getView("CXPHEC");
				Document doc= view.getDocumentByKey(llave, true);
				if (doc==null)
					return true;
				else
					return false;
			}catch(Exception e) {
				System.out.println("CAI EN verificaComprobante");
				e.printStackTrace();
				return false;
			}	
		}
	}

