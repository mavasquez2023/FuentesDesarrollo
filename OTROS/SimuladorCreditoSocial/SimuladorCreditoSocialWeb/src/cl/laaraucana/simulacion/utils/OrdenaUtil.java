package cl.laaraucana.simulacion.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cl.laaraucana.simulacion.xml.DatosComunas;

public class OrdenaUtil {
	
	/*
     * Paramterized method to sort Map e.g. HashMap or Hashtable in Java
     * throw NullPointerException if Map contains null key
     */
    public static <K extends Comparable,V extends Comparable> Map<K,V> sortByKeys(Map<K,V> map){
        List<K> keys = new LinkedList<K>(map.keySet());
        Collections.sort(keys);
     
        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
        for(K key: keys){
            sortedMap.put(key, map.get(key));
        }
     
        return sortedMap;
    }
 
    /*
     * Java method to sort Map in Java by value e.g. HashMap or Hashtable
     * throw NullPointerException if Map contains null values
     * It also sort values even if they are duplicates
     */
    public static <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
        List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());
     
        Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {

            @Override
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
     
        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
     
        for(Map.Entry<K,V> entry: entries){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
     
        return sortedMap;
    }
    
    /*
     * Java method to sort Map in Java by value e.g. HashMap or Hashtable
     * throw NullPointerException if Map contains null values
     * It also sort values even if they are duplicates
     */
    public static <K extends Comparable,V extends Comparable> Map<String,DatosComunas> sortByValuesComunas(Map<String,DatosComunas> map){
        List<Map.Entry<String,DatosComunas>> entries = new LinkedList<Map.Entry<String,DatosComunas>>(map.entrySet());
     
        Collections.sort(entries, new Comparator<Map.Entry<String,DatosComunas>>() {

            @Override
            public int compare(Entry<String, DatosComunas> o1, Entry<String, DatosComunas> o2) {
            	DatosComunas comuna1 = (DatosComunas)o1.getValue();
            	DatosComunas comuna2 = (DatosComunas)o2.getValue();
                //return o1.getValue().compareTo(o2.getValue());
                return comuna1.getNombreComuna().compareTo(comuna2.getNombreComuna());
            }
        });
     
        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map<String,DatosComunas> sortedMap = new LinkedHashMap<String,DatosComunas>();
     
        for(Map.Entry<String,DatosComunas> entry: entries){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
     
        return sortedMap;
    }

}
