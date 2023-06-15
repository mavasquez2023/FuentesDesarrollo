package cl.liv.export.comun.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.zip.CRC32;

import cl.jfactory.planillas.service.util.UtilLogWorkflow;

public class Funciones {
	public static HashMap<String, String> procesarParametros(String parametros) {
		HashMap<String, String> resultado = new HashMap<String, String>();

		String[] valores = parametros.split(";;");
		if (valores != null) {
			for (int i = 0; i < valores.length; i++) {
				if (valores[i].trim().length() > 1) {
					String key = valores[i].substring(0, valores[i].indexOf(":"));
					String value = valores[i].substring(valores[i].indexOf(":") + 1);
					Mensajes.info("KEY, VALUE: " + key + "," + value);
					resultado.put(key, value);
				}
			}
		}
		return resultado;
	}

	public static String[] getQuery(String query, ArrayList<HashMap<String, String>> querys) {
		for (int i = 0; i < querys.size(); i++) {
			if (query.equals(querys.get(i).get("id"))) {
				String[] data = new String[2];
				data[0] = querys.get(i).get("sql");
				data[1] = querys.get(i).get("datasource");
				return data;
			}
		}
		Mensajes.info("QUERY ID: " + query + ", no encontrada");
		return null;

	}

	public static String getQueryCompletaConParametrosDinamicos(ArrayList<HashMap<String, Object>> querys,
			HashMap<String, Object> query, HashMap<String, String> parametros) {
		UtilLogWorkflow.debug(query.toString());
		if (query != null && query.get("tiene_dinamic") != null) {
			String sql = query.get("sql").toString();
			HashMap<String, Object> dinamics = (HashMap<String, Object>) query.get("dinamic");
			if (dinamics != null) {
				Iterator it = dinamics.entrySet().iterator();
				while (it.hasNext()) {
					Entry pairs = (Entry<String, Object>) it.next();
					String valorParametro = parametros.get(pairs.getKey());
					if (valorParametro != null) {
						if (pairs.getValue().toString().contains("JOIN:")) {

							String subquery = pairs.getValue().toString().split("JOIN:")[1].split(":")[0];

							Mensajes.info("buscando join: " + subquery);
							sql += pairs.getValue().toString().substring(0,
									pairs.getValue().toString().indexOf("JOIN:"))
									+ ""
									+ getQueryCompletaConParametrosDinamicos(querys, getQueryFromId(querys, subquery),
											parametros)
									+ " " + pairs.getValue().toString().split("JOIN:")[1].split(":")[1];
						} else {
							sql += pairs.getValue() + "\n";
						}
					}
				}

			}
			return sql;
		} else {
			return query.get("sql").toString();
		}
	}

	public static HashMap<String, Object> getQueryFromId(ArrayList<HashMap<String, Object>> querys, String id) {
		for (HashMap<String, Object> query : querys) {
			if (query != null && query.get("id").toString().equals(id)) {
				return query;
			}
		}
		return null;
	}

	public static String agregarParametrosAQuery(String query, HashMap<String, String> parametros) {

		String[] pars = query.split("#");

		for (int i = 1; i < pars.length; i++) {
			if (pars[i].trim().length() > 1) {
				if (parametros.get(pars[i]) != null) {
					query = query.replaceAll("#" + pars[i] + "#", parametros.get(pars[i]));
				} else {
					Mensajes.info("PARAMETRO NECESARIO NO ENCONTRADO: " + pars[i]);
				}
			}
		}
		return query;
	}

	public static String agregarParametrosAQuery(String query, HashMap<String, String> parametros,
			HashMap<String, Object> registroIterado) {
		query = agregarParametrosAQuery(query, parametros);
		String[] pars = query.split("==");

		for (int i = 1; i < pars.length; i++) {
			if (pars[i].trim().length() > 1) {
				if (registroIterado.get(pars[i]) != null) {
					query = query.replaceAll("==" + pars[i] + "==", registroIterado.get(pars[i]).toString());
				} else {
					Mensajes.info("PARAMETRO NECESARIO NO ENCONTRADO: " + pars[i]);
				}
			}
		}

		return query;
	}

	public static String agregarParametrosParentAQuery(String query, HashMap<String, Object> parametros) {

		String[] pars = query.split("%");

		for (int i = 1; i < pars.length; i++) {
			if (pars[i].trim().length() > 1) {
				if (parametros.get(pars[i]) != null) {
					query = query.replaceAll("%" + pars[i] + "%", parametros.get(pars[i]).toString());
				} else {
					Mensajes.info("PARAMETRO PARENT NECESARIO NO ENCONTRADO: " + pars[i]);
				}
			}
		}
		Mensajes.info("Query final: " + query);
		return query;
	}

	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32
			// chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static String ejecutarMetodo(String clase, String metodo, String reporte, String pars) {
		Class c;
		try {
			c = Class.forName(clase);

			Object myObject = (Object) c.getConstructor().newInstance();

			Method method = c.getMethod(metodo, String.class, String.class);
			Object retorno = method.invoke(myObject, reporte, pars);
			return retorno.toString();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {

			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String ejecutarMetodo(String clase, String metodo, HashMap<String, Object> data) {
		Class c;
		try {
			c = Class.forName(clase);

			Object myObject = (Object) c.getConstructor().newInstance();

			Method method = c.getMethod(metodo, HashMap.class);
			Object retorno = method.invoke(myObject, data);
			return retorno.toString();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {

			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getNameOutputFile(String name, HashMap<String, String> parametros) {

		Mensajes.info("name antes: " + name);
		String[] pars = name.split("#");

		for (int i = 1; i < pars.length; i++) {
			if (pars[i].trim().length() > 1) {
				if (parametros.get(pars[i]) != null) {
					name = name.replaceAll("#" + pars[i] + "#", parametros.get(pars[i]));
				} else {
					Mensajes.info("PARAMETRO NECESARIO NO ENCONTRADO: " + pars[i]);
				}
			}
		}
		if (name.contains("random")) {
			name = name.replace("random", new Date().getTime() + "");
		}
		Mensajes.info("name despues: " + name);
		return name;
	}

	public static String agregarParametro(String key, String value) {
		return key + ":" + value + ";;";
	}

	public static long checksumInputStream(String filepath) {
		InputStream inputStreamn;
		try {
			inputStreamn = new FileInputStream(filepath);
			CRC32 crc = new CRC32();
			int cnt;
			while ((cnt = inputStreamn.read()) != -1) {
				crc.update(cnt);
			}
			return crc.getValue();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0l;
	}

}
