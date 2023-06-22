package cl.araucana.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cl.araucana.wssiagf.TramoBusinessTO;

public class Test2 {
	public static void main(String [] args) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		List tramosRetroactivos = new ArrayList();
		tramosRetroactivos.add(new TramoBusinessTO(2013, sdf.parse("20130101")));
		tramosRetroactivos.add(new TramoBusinessTO(2015, sdf.parse("20150101")));
		tramosRetroactivos.add(new TramoBusinessTO(2015, sdf.parse("20150701")));
		tramosRetroactivos.add(new TramoBusinessTO(2014, sdf.parse("20140101")));
		tramosRetroactivos.add(new TramoBusinessTO(2016, sdf.parse("20160701")));
		tramosRetroactivos.add(new TramoBusinessTO(2016, sdf.parse("20160101")));
		tramosRetroactivos.add(new TramoBusinessTO(2017, sdf.parse("20170101")));
		tramosRetroactivos.add(new TramoBusinessTO(2016, sdf.parse("20160801")));
		
		Collections.sort(tramosRetroactivos);
		for(int i = 0, count = 1; i < tramosRetroactivos.size()-1; i++){
			TramoBusinessTO tramo = (TramoBusinessTO)tramosRetroactivos.get(i);
			TramoBusinessTO tramoSiguiente = (TramoBusinessTO)tramosRetroactivos.get(i+1);
			
			String anoTramo = String.valueOf(tramo.getYear()).substring(0, 4);
			String anoTramoSiguiente = String.valueOf(tramoSiguiente.getYear()).substring(0, 4);
			
			if(anoTramo.equals(anoTramoSiguiente)){
				if(tramo.getYear() < 9999){
					tramo.setYear((tramo.getYear() * 100) + count);
				}
				tramoSiguiente.setYear((tramoSiguiente.getYear() * 100) + ++count);
			}else{
				count = 1;
			}
		}
		
		for(Iterator i = tramosRetroactivos.iterator(); i.hasNext(); ){
			TramoBusinessTO tramoBusinessTO = (TramoBusinessTO) i.next();
			System.out.println("Año: " + tramoBusinessTO.getYear() + "-" + "Fecha:" + sdf.format(tramoBusinessTO.getFechaInicioTramo()));
		}
	}
}

