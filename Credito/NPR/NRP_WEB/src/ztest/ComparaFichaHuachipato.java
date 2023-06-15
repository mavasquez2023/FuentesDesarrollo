package ztest;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;

import cl.liv.archivos.comun.txt.ManejoArchivoTXT;

public class ComparaFichaHuachipato {
	public static void main(String[] args) {
		
		ArrayList fichas = new ArrayList();
		
		String PATH_ORIGINAL = "/home/clillo/nrp/output/201807/94637000/94637000.ROA-o";
		
		BufferedReader archivoOriginal =  ManejoArchivoTXT.getOpenedFileToRead(PATH_ORIGINAL, 0, null);
		

		String PATH_NUEVO = "/home/clillo/nrp/output/201807/94637000/94637000.ROA.txt";
		
		BufferedReader archivoNuevo =  ManejoArchivoTXT.getOpenedFileToRead(PATH_NUEVO, 0, null);
		
		String texto = ManejoArchivoTXT.getLineFromFileOpened(archivoOriginal);
		System.out.println(texto);
		while(texto != null){
			if(texto != null && texto.length()>8){
				fichas.add(texto.substring(0,8));
				texto = ManejoArchivoTXT.getLineFromFileOpened(archivoOriginal);
			}
			else{
				texto = null;
			}
		}
		
		System.out.println("fichas encontradas: "+ fichas.size());
		
		String texto2 = ManejoArchivoTXT.getLineFromFileOpened(archivoNuevo);
		System.out.println("texto 2: " + texto2);
		while(texto2 != null){
			if(texto2 != null && texto2.length()>8){
				removeItem(fichas,texto2.substring(0,8));
				texto2 = ManejoArchivoTXT.getLineFromFileOpened(archivoNuevo);
			}
			else{
				texto2 = null;
			}
		}
	
		
		
		
		
		System.out.println("fichas: "+ fichas);
	}
	
	
	public static void removeItem(ArrayList items, String item){

		int aux = -1;
		if(items != null && items.size()>0){
			for(int i=0; i< items.size(); i++){
				if(items.get(i).toString().equals(item)){
					aux = i ;
					break;
				}
			}
		}
		if(aux >=0){
			items.remove(aux);
		}
	}
}
