package cl.araucana.cp.distribuidor.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoDetalleVO;

public class Utils
{
	private static Logger logger = Logger.getLogger(Utils.class);
	private Properties mapNombres;
	private HashMap mapeoValores;
	private static Pattern p = Pattern.compile("[^A-Z0-9\\.\\ ]");
	private static Pattern pTexto = Pattern.compile("[^A-Z\\.\\ ]");
	private static Pattern pFloat = Pattern.compile("[^0-9\\.,]");
	private static Pattern pInt = Pattern.compile("[^0-9\\.]");
	private static Pattern pa = Pattern.compile("[ÄÁ]");
	private static Pattern pe = Pattern.compile("[ËÉ]");
	private static Pattern pi = Pattern.compile("[ÏÍ]");
	private static Pattern po = Pattern.compile("[ÖÓ]");
	private static Pattern pu = Pattern.compile("[ÜÚ]");
	private static Pattern pn = Pattern.compile("[Ñ]");
	
	private String valor = "";

	public Utils()
	{
		super();
	}

	public Utils(Properties mapNombres, HashMap mapeoValores)
	{
		super();
		this.mapNombres = mapNombres;
		this.mapeoValores = mapeoValores;
	}

	public String getValor()
	{
		return this.valor;
	}

	public int procesaTexto(boolean reemplazo, String txt)
	{
		if (txt != null)
		{
			this.valor = txt.trim().toUpperCase();
			if (this.valor.equals(""))
				return Constants.TEXTO_VACIO;

			this.valor = pa.matcher(this.valor).replaceAll("A");
			this.valor = pe.matcher(this.valor).replaceAll("E");
			this.valor = pi.matcher(this.valor).replaceAll("I");
			this.valor = po.matcher(this.valor).replaceAll("O");
			this.valor = pu.matcher(this.valor).replaceAll("U");
			this.valor = pn.matcher(this.valor).replaceAll("N");
			if (reemplazo)
				this.valor = pTexto.matcher(this.valor).replaceAll("").trim();
			else if (pTexto.matcher(this.valor).find())
				return Constants.TEXTO_INVALIDO;

			if (this.valor.equals(""))
				return Constants.TEXTO_VACIO;
			return Constants.TEXTO_OK;
		}
		this.valor = "";
		return Constants.TEXTO_INVALIDO;
	}
	
	
	/**
	 * Valida que el txt corresponda solo a caracteres permitidos para los códigos de sucursal.
	 * @param reemplazo
	 * @param txt
	 * @return
	 * 
	 * @author asepulveda 23-04-2010
	 * 
	 */
	public int procesaCodigoSucursal(boolean reemplazo, String txt)
	{
		if (txt != null)
		{
			this.valor = txt.trim().toUpperCase();
			if (this.valor.equals(""))
				return Constants.TEXTO_VACIO;

			this.valor = pa.matcher(this.valor).replaceAll("A");
			this.valor = pe.matcher(this.valor).replaceAll("E");
			this.valor = pi.matcher(this.valor).replaceAll("I");
			this.valor = po.matcher(this.valor).replaceAll("O");
			this.valor = pu.matcher(this.valor).replaceAll("U");
			//this.valor = pn.matcher(this.valor).replaceAll("N");
			if (reemplazo)
				this.valor = p.matcher(this.valor).replaceAll(" ").trim();
			else if (p.matcher(this.valor).find())
				return Constants.TEXTO_INVALIDO;

			if (this.valor.equals(""))
				return Constants.TEXTO_VACIO;
			return Constants.TEXTO_OK;
		}
		this.valor = "";
		return Constants.TEXTO_INVALIDO;
	}	

	public void showValores()
	{
		for (Iterator it = this.mapeoValores.keySet().iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			logger.debug("valores:key:" + key + ":valor:" + this.mapeoValores.get(key) + "::");
		}
	}

	public void showNombres()
	{
		for (Iterator it = this.mapNombres.keySet().iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			logger.debug("nombres:key:" + key + ":valor:" + this.mapNombres.get(key) + "::");
		}
	}

	public static String transforma(String val)
	{
		try
		{
			if (val != null && !val.equals(""))
			{
				String valorTmp = val.trim().toUpperCase();
				valorTmp = pa.matcher(valorTmp).replaceAll("A");
				valorTmp = pe.matcher(valorTmp).replaceAll("E");
				valorTmp = pi.matcher(valorTmp).replaceAll("I");
				valorTmp = po.matcher(valorTmp).replaceAll("O");
				valorTmp = pu.matcher(valorTmp).replaceAll("U");
				valorTmp = pn.matcher(valorTmp).replaceAll("N");
				return p.matcher(valorTmp).replaceAll(" ").trim();
			}
			return "";
		} catch (Exception e)
		{
			logger.error("problemas transforma:", e);
			return "";
		}
	}

	// marco para validad codigos de entidades 
	public static String transformaCodEnt(String val)
	{
		try
		{
			if (val != null && !val.equals(""))
			{
				String valorTmp = val.trim().toUpperCase();
				valorTmp = pa.matcher(valorTmp).replaceAll("A");
				valorTmp = pe.matcher(valorTmp).replaceAll("E");
				valorTmp = pi.matcher(valorTmp).replaceAll("I");
				valorTmp = po.matcher(valorTmp).replaceAll("O");
				valorTmp = pu.matcher(valorTmp).replaceAll("U");
				valorTmp = pn.matcher(valorTmp).replaceAll("N");
				return valorTmp; 
				
			}
			
			return "";
		} catch (Exception e)
		{
			logger.error("problemas transforma:", e);
			return "";
		}
	}
	public static String limpiaFloat(String val)
	{
		try
		{
			if (val != null && !val.equals(""))
			{
				String valorTmp = val.trim();
				return pFloat.matcher(valorTmp.trim()).replaceAll(" ");
			}
			return "";
		} catch (Exception e)
		{
			logger.error("problemas transforma:", e);
			return "";
		}
	}

	public static String limpiaInt(String val)
	{
		try
		{
			if (val != null && !val.equals(""))
			{
				String valorTmp = val.trim();
				return pInt.matcher(valorTmp.trim()).replaceAll(" ");
			}
			return "";
		} catch (Exception e)
		{
			logger.error("problemas transforma:", e);
			return "";
		}
	}

	public String transforma(String val, String defecto)
	{
		try
		{
			String valorTmp = transforma(val);
			return (valorTmp.equals("") ? defecto : valorTmp);
		} catch (Exception e)
		{
			logger.error("problemas transforma:", e);
			return "";
		}
	}

	public String setString(String nombre)
	{
		try
		{
			String campo = "";
			if (this.mapNombres.containsKey(nombre))
				if (this.mapNombres.get(nombre) != null && this.mapeoValores.containsKey(this.mapNombres.get(nombre)))
					campo = ((String) this.mapeoValores.get(this.mapNombres.get(nombre))).trim();
			if (campo != null)
				campo = transforma(campo);
			else
				campo = "";
			return campo;
		} catch (Exception e)
		{
			logger.error("problemas setString:", e);
			return "";
		}
	}

	public String setFloatStr(String nombre)
	{
		try
		{
			String campo = "";
			if (this.mapNombres.containsKey(nombre))
				if (this.mapNombres.get(nombre) != null && this.mapeoValores.containsKey(this.mapNombres.get(nombre)))
					campo = ((String) this.mapeoValores.get(this.mapNombres.get(nombre))).trim();
			if (campo != null)
			{
				try
				{
					return "" + Float.parseFloat(campo);
				} catch (Exception e)
				{}
				return campo;//limpiaFloat(campo);
			}
			return "";
		} catch (Exception e)
		{
			logger.error("problemas setFloatStr:", e);
			return "";
		}
	}

	public String setIntStr(String nombre)
	{
		try
		{
			String campo = "";
			if (this.mapNombres.containsKey(nombre))
				if (this.mapNombres.get(nombre) != null && this.mapeoValores.containsKey(this.mapNombres.get(nombre)))
					campo = ((String) this.mapeoValores.get(this.mapNombres.get(nombre))).trim();
			if (campo != null)
				return campo;//limpiaInt(campo);
			return "";
		} catch (Exception e)
		{
			logger.error("problemas setIntStr:", e);
			return "";
		}
	}

	public int setInt(String nombre)
	{
		try
		{
			if (this.mapNombres.containsKey(nombre))
				if (this.mapNombres.get(nombre) != null && this.mapeoValores.containsKey(this.mapNombres.get(nombre)))
				{
					String strTmp = (String) this.mapeoValores.get(this.mapNombres.get(nombre));
					if (strTmp != null && !strTmp.equals(""))
					{
						try
						{
							return new Integer(strTmp).intValue();
						} catch (NumberFormatException e)
						{
							return -1;
						}
					}
				}
		} catch (Exception e)
		{
			logger.error("problemas setInt:", e);
		}
		return 0;
	}

	public int setInt(String nombre, int defecto)
	{
		try
		{
			if (this.mapNombres.containsKey(nombre))
				if (this.mapNombres.get(nombre) != null && this.mapeoValores.containsKey(this.mapNombres.get(nombre)))
				{
					String strTmp = (String) this.mapeoValores.get(this.mapNombres.get(nombre));
					if (strTmp != null && !strTmp.equals(""))
					{
						try
						{
							return new Integer(strTmp).intValue();
						} catch (NumberFormatException e)
						{
							return -1;
						}
					}

				}
		} catch (Exception e)
		{
			logger.error("problemas setInt:", e);
		}
		return defecto;
	}

	public int transformaRut(String rut)
	{
		try
		{
			String rutTmp = rut.trim();
			if (!rutTmp.equals(""))
				return new Integer(rutTmp.substring(0, rutTmp.length() - 1)).intValue();
		} catch (Exception e)
		{
			logger.error("problemas transformaRut:", e);
		}
		return 0;
	}

	public HashMap getMapeoValores()
	{
		return this.mapeoValores;
	}

	public void setMapeoValores(HashMap mapeoValores)
	{
		this.mapeoValores = mapeoValores;
	}

	public Properties getMapNombres()
	{
		return this.mapNombres;
	}

	public void setMapNombres(Properties mapNombres)
	{
		this.mapNombres = mapNombres;
	}

	public static String codificaAcentos(String s)
	{
		String res = "";
		for (int i = 0; i < s.length(); i++)
		{
			switch (s.charAt(i))
			{
			case 'á':
				res += "&aacute;";
				break;
			case 'é':
				res += "&eacute;";
				break;
			case 'í':
				res += "&iacute;";
				break;
			case 'ó':
				res += "&oacute;";
				break;
			case 'ú':
				res += "&uacute;";
				break;
			case 'ñ':
				res += "&ntilde;";
				break;
			case 'Á':
				res += "&Aacute;";
				break;
			case 'É':
				res += "&Ecute;";
				break;
			case 'Í':
				res += "&Iacute;";
				break;
			case 'Ó':
				res += "&Oacute;";
				break;
			case 'Ú':
				res += "&Oacute;";
				break;
			case 'Ñ':
				res += "&Ntilde;";
				break;
			default:
				res += s.charAt(i);
			}
		}
		return res;
	}

	public static HashMap parseoValores(List listaConceptos, HashMap mapeosConcep, String line)
	{
		HashMap mapeoValores = new HashMap();
		for (Iterator it2 = listaConceptos.iterator(); it2.hasNext();) // asigna valor a cada campo
		{
			ConceptoVO c = (ConceptoVO) it2.next();
			MapeoConceptoVO mc = (MapeoConceptoVO) mapeosConcep.get("" + c.getId());

			if (mc == null || (mc.getPosicion() == 0 && mc.getLargo() == 0) || line.length() < mc.getPosicion())
			{
				c.setValor("");
				mapeoValores.put(c.getNombre().trim(), "");
			} else
			{
				c.setValor(line.substring(mc.getPosicion() - 1, Math.min(mc.getPosicion() + mc.getLargo() - 1, line.length())).trim());
				mapeoValores.put(c.getNombre().trim(), c.getValor().trim());
			}
		}
		return mapeoValores;
	}

	public static String rellena(boolean alignDerecha, int largo, String relleno, long numero)
	{
		return rellena(alignDerecha, largo, relleno, "" + numero);
	}

	public static String rellena(int largo, String relleno, long numero)
	{// por defecto numeros alineados a la derecha
		return rellena(true, largo, relleno, "" + numero);
	}

	public static String rellena(boolean alignDerecha, int largo, String relleno, int numero)
	{
		return rellena(alignDerecha, largo, relleno, "" + numero);
	}

	public static String rellena(int largo, String relleno, int numero)
	{// por defecto numeros alineados a la derecha
		return rellena(true, largo, relleno, "" + numero);
	}

	public static String rellena(int largo, String relleno, String texto)
	{// por defecto numeros alineados a la izq
		return rellena(false, largo, relleno, texto);
	}

	public static String rellena(boolean alignDerecha, int largo, String relleno, String texto)
	{
		String result = "";
		if (texto == null)
			texto = "";
		else if (texto.length() > largo)
			return texto.substring(0, largo);
		if (relleno == null)
			relleno = " ";
		else if (relleno.length() > 1)
			relleno = "" + relleno.charAt(0);
		if (alignDerecha)
		{
			for (int i = 0; i < (largo - texto.length()); i++)
				result += relleno;
			result += texto;

		} else
		{
			result = texto;
			for (int i = texto.length(); i < largo; i++)
				result += relleno;
		}
		return result;
	}

	public static List obtieneFono(String fono)
	{
		List respuesta = new LinkedList();
		if (fono.length() > 0)
		{
			StringTokenizer st = new StringTokenizer(fono, ")");
			if (st.countTokens() == 2 && st.countTokens() > 0)
			{
				String token = st.nextToken();
				respuesta.add(token.substring(1, token.length()));
				respuesta.add(st.nextToken());
			} else
			{
				if (st.hasMoreTokens())
					respuesta.add("");
				respuesta.add(st.nextToken());
			}
		} else
		{
			respuesta.add(null);
			respuesta.add(null);
		}
		return respuesta;
	}

	public static HashMap llenarPaginacionCL(int paginaDestino, List data)
	{
		return llenarPaginacion(paginaDestino, Constants.NUM_REG_PAG_CL, Constants.NUM_PAG_CL, data);
	}

	public static HashMap llenarPaginacionAdmin(int paginaDestino, List data)
	{
		return llenarPaginacion(paginaDestino, Constants.NUM_REG_PAG_ADMIN, Constants.NUM_PAG_ADMIN, data);
	}

	public static HashMap llenarPaginacion(int paginaDestino, int numReg, int numPag, List data)
	{
		logger.debug("\n\n\npaginacion data:" + data.size() + ":paginaDestino:" + paginaDestino + "::");
		HashMap result = new HashMap();
		result.put("data", data);
		result.put("paginas", new ArrayList());
		int nPaginas = (data.size() % numReg == 0 ? data.size() / numReg : (int) Math.round((data.size() / numReg) + 0.5)); // numero de paginas
		int numPagMedio = numPag / 2; // nPaginas antes y despues de la actual a mostrar
		int inicio = 1, fin = nPaginas;

		if (paginaDestino <= numPagMedio)
			fin = Math.min(numPag, nPaginas);
		else if (paginaDestino + numPagMedio >= nPaginas)
			inicio = Math.max(nPaginas - numPag + 1, 1);
		else
		{
			inicio = Math.max(paginaDestino - numPagMedio, 1);
			fin = Math.min(paginaDestino + numPagMedio, nPaginas);
		}
		logger.debug("inicio:" + inicio + ":fin:" + fin + ":nPaginas:" + nPaginas + ":numPagMedio:" + numPagMedio + "::");
		int cursorData = (paginaDestino - 1) * numReg;
		result.put("data", data.subList(cursorData, Math.min(cursorData + numReg, data.size())));

		result.put("paginas", generaPaginacion(paginaDestino, nPaginas, inicio, fin));
		return result;
	}

	public static HashMap llenarPaginacionCL(int paginaDestino, List data, List data2)
	{
		return llenarPaginacion(paginaDestino, Constants.NUM_REG_PAG_CL, Constants.NUM_PAG_CL, data, data2);
	}

	public static HashMap llenarPaginacionAdmin(int paginaDestino, List data, List data2)
	{
		return llenarPaginacion(paginaDestino, Constants.NUM_REG_PAG_ADMIN, Constants.NUM_PAG_ADMIN, data, data2);
	}

	public static HashMap llenarPaginacion(int paginaDestino, int numReg, int numPag, List data, List data2)
	{
		logger.debug("\n\n\npaginacion data1:" + data.size() + ":data2:" + data2.size() + ":paginaDestino:" + paginaDestino + "::");
		HashMap result = new HashMap();
		result.put("data1", data);
		result.put("data2", data2);
		result.put("paginas", new ArrayList());
		int nPaginas = ((data.size() + data2.size()) % numReg == 0 ? (data.size() + data2.size()) / numReg : (int) Math.round(((data.size() + data2.size()) / numReg) + 0.5)); // numero de paginas
		int numPagMedio = (numPag / 2); // nPaginas antes y despues de la actual a mostrar
		int inicio = 1, fin = nPaginas;

		if (paginaDestino <= numPagMedio)
			fin = Math.min(numPag, nPaginas);
		else if (paginaDestino + numPagMedio >= nPaginas)
			inicio = Math.max(nPaginas - numPag + 1, 1);
		else
		{
			inicio = Math.max(paginaDestino - numPagMedio, 1);
			fin = Math.min(paginaDestino + numPagMedio, nPaginas);
		}
		logger.debug("inicio:" + inicio + ":fin:" + fin + ":nPaginas:" + nPaginas + ":numPagMedio:" + numPagMedio + "::");
		int cursorData = (paginaDestino - 1) * numReg;
		int cursorDataFin = cursorData + numReg;
		int numData = data.size();
		int numData2 = data2.size();
		logger.debug("cursorData:" + cursorData + ":cursorDataFin:" + cursorDataFin + ":numData:" + numData + ":numData2:" + numData2 + "::");
		if (numData >= cursorDataFin) // solo primera lista
		{
			result.put("data1", data.subList(cursorData, cursorDataFin));
			result.put("data2", new ArrayList());
		} else if (numData > cursorData) // primera Y segunda lista
		{
			result.put("data1", data.subList(cursorData, numData));
			int pos = Math.min(cursorDataFin - numData, numData2);
			if (pos > 0)
				result.put("data2", data2.subList(0, pos));
			else
				result.put("data2", new ArrayList());
		} else
		// solo segunda lista
		{
			result.put("data1", new ArrayList());
			int pos = Math.min(cursorDataFin - numData, numData2);
			if (pos > 0)
				result.put("data2", data2.subList(cursorData - numData, pos));
			else
				result.put("data2", new ArrayList());
		}

		result.put("paginas", generaPaginacion(paginaDestino, nPaginas, inicio, fin));
		return result;
	}

	public static List generaPaginacion(int paginaDestino, int nPaginas, int inicio, int fin)
	{
		List paginas = new ArrayList();
		if (inicio > 1) // agrega enlace rapido a primera pagina
			paginas.add("<a class=\"links\" href=\"javascript:retornaEnlace('1');\">&lt;&lt;</a>&nbsp;-&nbsp;");
		for (int i = inicio; i <= fin; i++)
		{
			String pagina = "";
			if (i == paginaDestino)
			{
				if (paginaDestino > 1)
					pagina = "&nbsp;-&nbsp;";
				paginas.add(pagina + i);
			} else
			{
				if (i > inicio)
					pagina = "&nbsp;-&nbsp;";
				paginas.add(pagina + "<a class=\"links\" href=\"javascript:retornaEnlace('" + i + "');\">" + i + "</a>");
			}
		}
		if (fin < nPaginas) // agrega enlace rapido a ultima pagina
			paginas.add("&nbsp;-&nbsp;<a class=\"links\" href=\"javascript:retornaEnlace('" + nPaginas + "');\">&gt;&gt;</a>");

		return paginas;
	}

	public static List generaPaginacion(int paginaDestino, int nPaginas, int numPag)
	{
		int numPagMedio = (numPag / 2); // nPaginas antes y despues de la actual a mostrar
		int inicio = 1, fin = nPaginas;

		if (paginaDestino <= numPagMedio)
			fin = Math.min(numPag, nPaginas);
		else if (paginaDestino + numPagMedio >= nPaginas)
			inicio = Math.max(nPaginas - numPag + 1, 1);
		else
		{
			inicio = Math.max(paginaDestino - numPagMedio, 1);
			fin = Math.min(paginaDestino + numPagMedio, nPaginas);
		}
		logger.info("inicio:" + inicio + ":fin:" + fin + ":nPaginas:" + nPaginas + ":numPagMedio:" + numPagMedio + "::");
		
		return generaPaginacion(paginaDestino, nPaginas, inicio, fin);
	}

	public static List limpiaListaApv(List apvs)
	{
		List ret = new ArrayList();
		if (apvs != null)
		{
			for (int a = 0; a < apvs.size() && a < Constants.nAPVs_COTIZANTE; a++)
			{
				ApvVO apv = (ApvVO) apvs.get(a);
				if (apv != null && (apv.getIdEntidadApv() != Constants.SIN_APV || apv.getAhorro() != 0))
					ret.add(apv);
			}
		}
		return ret;
	}

	public static String completaRut(int rut)
	{
		return "" + rut + generaDV(rut);
	}

	public static char generaDV(int rut)
	{
		int M = 0, S = 1, T = rut;
		for (; T != 0; T /= 10)
			S = (S + T % 10 * (9 - M++ % 6)) % 11;
		return (char) (S != 0 ? S + 47 : 75);
	}

	public static String formatRut(int rut)// rut viene sin DV
	{
		if (rut == 0)
			return "";
		return formatMonto(rut) + "-" + generaDV(rut);
	}

	public static String formatMonto(long monto)
	{
		String montoStr = "";
		String montoTmp = "" + (monto < 0 ? monto * -1  : monto);
		String signo = (monto < 0 ? "-" : "");
		int pos = 0;
		int largo = montoTmp.length();
		while (largo > 0)
		{
			if (pos > 0 && pos % 3 == 0)
				montoStr = "." + montoStr;
			pos++;
			montoStr = "" + montoTmp.charAt(largo - 1) + montoStr;
			montoTmp = montoTmp.substring(0, largo - 1);
			largo = montoTmp.length();
		}
		return signo + montoStr;
	}

	public static float desFormatMontoF(String monto)
	{
		if (monto == null || monto.equals("") || monto.equals("0"))
			return 0;
		try
		{
			Pattern patternNumbers = Pattern.compile("[^0-9.]");
			Matcher m = patternNumbers.matcher(monto.replaceAll(",", ".").trim());
			return new Float(m.replaceAll("")).floatValue();
		} catch (Exception e)
		{
			logger.error("problemas desformatMonto:", e);
			return 0;
		}
	}

	public static String formatMontoF(float monto)
	{
		String montoStr = "" + monto;
		String montoTmp = montoStr.substring(0, montoStr.indexOf('.'));
		montoStr = ',' + montoStr.substring(montoStr.indexOf('.') + 1, montoStr.length());
		int pos = 0;
		int largo = montoTmp.length();
		while (largo > 0)
		{
			if (pos > 0 && pos % 3 == 0)
				montoStr = "." + montoStr;
			pos++;
			montoStr = "" + montoTmp.charAt(largo - 1) + montoStr;
			montoTmp = montoTmp.substring(0, largo - 1);
			largo = montoTmp.length();
		}
		return montoStr;
	}

	public static int desFormatMonto(String monto)
	{
		if (monto == null || monto.trim().equals("") || monto.equals("0"))
			return 0;
		try
		{
			Pattern patternNumbers = Pattern.compile("[^0-9]");
			Matcher m = patternNumbers.matcher(monto.trim());
			return new Integer(m.replaceAll("")).intValue();
		} catch (Exception e)
		{
			logger.error("problemas desformatMonto:", e);
			return 0;
		}
	}

	public static int desFormatRut(String rut)
	{
		try
		{
			String rutTmp = rut.trim();
			if (rutTmp.equals(""))
				return 0;
			Pattern patternNumbers = Pattern.compile("[^0-9]");
			Matcher m = patternNumbers.matcher(rutTmp.substring(0, rutTmp.length() - 1));
			return new Integer(m.replaceAll("")).intValue();
		} catch (Exception e)
		{
			return 0;
		}
	}

	public static String toStringArray(Object[] aObj)
	{
		String s = "[";
		if (aObj.length == 0)
			return s + "]";
		for (int i = 0; i < aObj.length - 1; i++)
		{
			s += i + ": \"" + aObj[i] + "\", ";
		}
		s += aObj.length - 1 + ": \"" + aObj[aObj.length - 1] + "\"]";
		return s;
	}

	public static String formatMontoD(double monto)
	{
		Double tmp = new Double(monto);
		if (tmp.longValue() == monto)
			return formatMonto((long) monto);
		return "" + monto;
	}

	public static int limpiaRut(boolean flag, String rut)
	{
		try
		{
			String rutTmp = rut.trim();
			if (flag)
				rutTmp = rutTmp.substring(0, rut.length() - 1);
			Pattern patternNumbers = Pattern.compile("[^0-9]");
			Matcher m = patternNumbers.matcher(rutTmp);
			return new Integer(m.replaceAll("")).intValue();
		} catch (Exception e)
		{
			logger.error("problemas limpia rut:", e);
			return 0;
		}
	}

	/**
	 * retorna digito verificador
	 * 
	 * @param rut
	 * @return
	 */
	public static char retornaDV(String rut)
	{
		return String.valueOf(rut.trim().charAt(rut.trim().length() - 1)).toUpperCase().charAt(0);
	}

	public static String getRutSinDV(String rut)
	{
		if (rut.length() <= 6)
			return rut;
		int rutInt = 0;
		try
		{
			rutInt = new Integer(rut.substring(0, rut.length() - 1)).intValue();
		} catch (NumberFormatException e)
		{
			return rut;
		}
		if (generaDV(rutInt) == rut.substring(rut.length() - 1).charAt(0))
			return "" + rutInt;
		return rut;
	}
	
	public static String[] OrdenarBurbuja(String[] n){
		String temp;
		int t = n.length;
		
		for (int i = 1; i < t; i++) 
		{
			for (int k = t- 1; k >= i; k--) 
			{
				if(Integer.parseInt(n[k].split("-")[0]) > Integer.parseInt(n[k-1].split("-")[0]))
				{
					temp = n[k];
					n[k] = n[k-1];
					n[k-1]= temp;
				}//fin if
			}// fin 2 for
		}//fin 1 for
		
		return n;
	}//fin


	public static HashMap llenarPaginacionCL(int paginaDestino, List data, List data2, List data3)
	{
		return llenarPaginacion(paginaDestino, Constants.NUM_REG_PAG_CL, Constants.NUM_PAG_CL, data, data2, data3);
	}

	public static HashMap llenarPaginacionAdmin(int paginaDestino, List data, List data2, List data3)
	{
		return llenarPaginacion(paginaDestino, Constants.NUM_REG_PAG_ADMIN, Constants.NUM_PAG_ADMIN, data, data2, data3);
	}

	public static HashMap llenarPaginacion(int paginaDestino, int numReg, int numPag, List data, List data2, List data3)
	{
		logger.debug("\n\n\npaginacion data1:" + data.size() + ":data2:" + data2.size() + ":data3:" + data3.size() + ":paginaDestino:" + paginaDestino + "::");
		HashMap result = new HashMap();
		result.put("data1", data);
		result.put("data2", data2);
		result.put("data3", data3);
		result.put("paginas", new ArrayList());

		int suma = data.size() + data2.size() + data3.size();
		int nPaginas = (suma % numReg == 0 ? suma / numReg : (int) Math.round((suma / numReg) + 0.5)); // numero de paginas
		int numPagMedio = (numPag / 2); // nPaginas antes y despues de la actual a mostrar
		int inicio = 1, fin = nPaginas;

		if (paginaDestino <= numPagMedio)
			fin = Math.min(numPag, nPaginas);
		else if (paginaDestino + numPagMedio >= nPaginas)
			inicio = Math.max(nPaginas - numPag + 1, 1);
		else
		{
			inicio = Math.max(paginaDestino - numPagMedio, 1);
			fin = Math.min(paginaDestino + numPagMedio, nPaginas);
		}
		logger.info("inicio:" + inicio + ":fin:" + fin + ":nPaginas:" + nPaginas + ":numPagMedio:" + numPagMedio + "::");
		int cursorData = (paginaDestino - 1) * numReg;
		int cursorDataFin = cursorData + numReg;
		int numData = data.size();
		int numData2 = data2.size();
		int numData3 = data3.size();
		HashMap listaTmp = new HashMap();
		int cont = 1;
		for (Iterator it = data.iterator(); it.hasNext();)
		{
			listaTmp.put(String.valueOf(cont), it.next());
			cont++;
		}
		for (Iterator it = data2.iterator(); it.hasNext();)
		{
			listaTmp.put(String.valueOf(cont), it.next());
			cont++;
		}
		for (Iterator it = data3.iterator(); it.hasNext();)
		{
			listaTmp.put(String.valueOf(cont), it.next());
			cont++;
		}
		List newData1 = new ArrayList();
		List newData2 = new ArrayList();
		List newData3 = new ArrayList();
		for (int cursor = cursorData + 1; cursor < cursorDataFin + 1; cursor++)
		{
			if (cursor <= numData)
				newData1.add(listaTmp.get(String.valueOf(cursor)));
			else if (cursor <= (numData + numData2))
				newData2.add(listaTmp.get(String.valueOf(cursor)));
			else if (cursor <= (numData + numData2 + numData3))
				newData3.add(listaTmp.get(String.valueOf(cursor)));
		}
		result.put("data1", newData1);
		result.put("data2", newData2);
		result.put("data3", newData3);

		result.put("paginas", generaPaginacion(paginaDestino, nPaginas, inicio, fin));
		return result;
	}

	public static TipoDetalleVO getTipoDetalle(char tipoProceso, int idTipoDetalle, List lista) throws Exception
	{
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			TipoDetalleVO td = (TipoDetalleVO) it.next();
			if (idTipoDetalle == td.getIdDetalleSeccion() && tipoProceso == td.getIdTipoNomina())
				return td;
		}
		logger.info("\n\nlista detalles recibida (se buscaba detalle :" + idTipoDetalle + ":tipoProceso:" + tipoProceso + ":size:" + lista.size() + "::");
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			TipoDetalleVO td = (TipoDetalleVO) it.next();
			logger.info("\tidDetalle:" + td.getIdDetalleSeccion() + "::" + td.getNombre() + "::");
		}
		throw new Exception("No se encontro tipoDetalle buscado!:tipoProceso:" + tipoProceso + ":idTipoDetalle:" + idTipoDetalle + "::");
	}

	public static double redondear(double numero, int decimales) 
	{
	    return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
	}

	public static String transforToDB(String txt)
	{
		if (txt == null || txt.trim().equals(""))
			return "";
		String result = "";
		for (int i = 0; i < txt.length(); i++)
			if (txt.charAt(i) == '_')
				result += "\\_";
			else
				result += txt.charAt(i);
		return result;
	}
}
