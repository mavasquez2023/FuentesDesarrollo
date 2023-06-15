// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 21-02-2023 10:31:45
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   StrutsValidator.java

package cl.araucana.common.ui.struts;

import java.io.Serializable;
import java.text.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.validator.*;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.validator.FieldChecks;
import org.apache.struts.validator.Resources;

public class StrutsValidator
    implements Serializable
{

    public StrutsValidator()
    {
    }

    public static boolean validateRango(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        Logger logger = Logger.getLogger(cl.araucana.common.ui.struts.StrutsValidator.class);
        logger.debug("VALIDADOR de Rango");
        String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
        String rangoi = field.getVarValue("rangoi");
        String valueRangoi = ValidatorUtil.getValueAsString(bean, rangoi);
        int ri = Integer.parseInt(valueRangoi);
        int rf = Integer.parseInt(value);
        logger.debug("ri=" + ri);
        logger.debug("rf=" + rf);
        if(ri >= rf)
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        } else
        {
            return true;
        }
    }

    public static boolean validateTalonario(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        Logger logger = Logger.getLogger(cl.araucana.common.ui.struts.StrutsValidator.class);
        logger.debug("VALIDADOR de Talonario");
        String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
        String inicio = field.getVarValue("inicioSecuencia");
        String valueInicio = ValidatorUtil.getValueAsString(bean, inicio);
        int ini = Integer.parseInt(valueInicio);
        int fin = Integer.parseInt(value);
        logger.debug("ini=" + ini);
        logger.debug("fin=" + fin);
        if(ini >= fin)
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        } else
        {
            return true;
        }
    }

    public static boolean validateDetalle(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        Logger logger = Logger.getLogger(cl.araucana.common.ui.struts.StrutsValidator.class);
        logger.debug("VALIDADOR DETALLE");
        String valueAporteIsapre = ValidatorUtil.getValueAsString(bean, field.getProperty());
        String prestacion = field.getVarValue("valorPrestacion");
        String valuePrestacion = ValidatorUtil.getValueAsString(bean, prestacion);
        String descuento = field.getVarValue("descuento");
        String valueDescuento = ValidatorUtil.getValueAsString(bean, descuento);
        int desc = Integer.parseInt(valueDescuento);
        int aporte = Integer.parseInt(valueAporteIsapre);
        int presta = Integer.parseInt(valuePrestacion);
        logger.debug("desc=" + desc);
        logger.debug("aporte" + aporte);
        logger.debug("presta=" + presta);
        if(desc + aporte > presta)
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        } else
        {
            return true;
        }
    }

    public static boolean validateFechaMenor(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        Logger logger = Logger.getLogger(cl.araucana.common.ui.struts.StrutsValidator.class);
        logger.debug("VALIDADOR Fecha Menor");
        String valueFechaMenor = ValidatorUtil.getValueAsString(bean, field.getProperty());
        String fechaMayor = field.getVarValue("fechaMayor");
        String valueFechaMayor = ValidatorUtil.getValueAsString(bean, fechaMayor);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try
        {
            Date fechai = formato.parse(valueFechaMenor);
            Date fechaf = null;
            if(fechaMayor == null)
                fechaf = new Date();
            else
                fechaf = formato.parse(valueFechaMenor);
            if(fechai.after(fechaf))
            {
                errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
                return false;
            }
        }
        catch(ParseException e)
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        }
        return true;
    }

    public static boolean validateIntMayor(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        Logger logger = Logger.getLogger(cl.araucana.common.ui.struts.StrutsValidator.class);
        logger.debug("VALIDADOR Int Mayor");
        String valueInt = ValidatorUtil.getValueAsString(bean, field.getProperty());
        int entero = Integer.parseInt(valueInt);
        String parametro = field.getVarValue("parametro");
        int entero2 = Integer.parseInt(parametro);
        logger.debug("entero=" + entero);
        logger.debug("entero2=" + entero2);
        if(entero <= entero2)
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        } else
        {
            return true;
        }
    }

    public static boolean validateRequiredIfExclusive(Object bean, ValidatorAction va, Field field, ActionErrors errors, Validator val, HttpServletRequest request)
    {
        Logger logger = Logger.getLogger(cl.araucana.common.ui.struts.StrutsValidator.class);
        if(FieldChecks.validateRequiredIf(bean, va, field, errors, val, request))
        {
            int cont = 0;
            ArrayList campo = new ArrayList();
            ArrayList campoValor = new ArrayList();
            ArrayList campoTest = new ArrayList();
            String join = field.getVarValue("fieldJoin");
            logger.debug("join: " + join);
            do
            {
                String key = field.getVarValue("field[" + cont + "]");
                if(key == null)
                    break;
                campo.add(cont, key);
                key = field.getVarValue("fieldValue[" + cont + "]");
                campoValor.add(cont, key);
                key = field.getVarValue("fieldTest[" + cont + "]");
                campoTest.add(cont, key);
                cont++;
            } while(true);
            boolean requiredTest = false;
            if(join == null)
                join = "OR";
            if(join.equals("OR"))
            {
                for(int t = 0; t < campo.size(); t++)
                {
                    logger.debug("Entre al for t=" + t);
                    logger.debug("campoTest.get(t):" + (String)campoTest.get(t));
                    if(((String)campoTest.get(t)).equals("EQUAL"))
                    {
                        if(!((String)campoValor.get(t)).equals(ValidatorUtil.getValueAsString(bean, (String)campo.get(t))))
                            continue;
                        requiredTest = true;
                        break;
                    }
                    if(((String)campoTest.get(t)).equals("NOTEQUAL"))
                    {
                        if(((String)campoValor.get(t)).equals(ValidatorUtil.getValueAsString(bean, (String)campo.get(t))))
                            continue;
                        requiredTest = true;
                        break;
                    }
                    if(((String)campoTest.get(t)).equals("NOTNULL"))
                    {
                        if(campoValor.get(t) == null)
                            continue;
                        requiredTest = true;
                        break;
                    }
                    if(!((String)campoTest.get(t)).equals("NULL") || campoValor.get(t) != null)
                        continue;
                    requiredTest = true;
                    break;
                }

            } else
            if(join.equals("AND"))
            {
                requiredTest = true;
                for(int t = 0; t < campo.size(); t++)
                {
                    logger.debug("campoTest.get(t):" + (String)campoTest.get(t));
                    if(!((String)campoTest.get(t)).equals("EQUAL"))
                    {
                        if(!((String)campoValor.get(t)).equals(ValidatorUtil.getValueAsString(bean, (String)campo.get(t))))
                            continue;
                        requiredTest = false;
                        break;
                    }
                    if(((String)campoTest.get(t)).equals("NOTEQUAL"))
                    {
                        if(((String)campoValor.get(t)).equals(ValidatorUtil.getValueAsString(bean, (String)campo.get(t))))
                            continue;
                        requiredTest = true;
                        break;
                    }
                    if(!((String)campoTest.get(t)).equals("NOTNULL"))
                    {
                        if(campoValor.get(t) == null)
                            continue;
                        requiredTest = false;
                        break;
                    }
                    if(((String)campoTest.get(t)).equals("NULL") || campoValor.get(t) != null)
                        continue;
                    requiredTest = false;
                    break;
                }

            }
            logger.debug("retornando requiredTest=" + requiredTest);
            return requiredTest;
        } else
        {
            return false;
        }
    }

    public static boolean validateStringRUTif(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        Logger logger = Logger.getLogger(cl.araucana.common.ui.struts.StrutsValidator.class);
        String fieldif = field.getVarValue("field");
        String valueif = field.getVarValue("fieldValue");
        logger.debug("Condicional: " + fieldif + "=" + valueif);
        String realvalue = ValidatorUtil.getValueAsString(bean, fieldif);
        if(!realvalue.equalsIgnoreCase(valueif))
            return true;
        String rut = ValidatorUtil.getValueAsString(bean, field.getProperty());
        if(rut.length() < 2)
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        }
        logger.debug("pos guion: " + rut.indexOf("-"));
        if(rut.indexOf("-") > 0)
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        }
        String dvValue = rut.substring(rut.length() - 1);
        rut = rut.substring(0, rut.length() - 1);
        logger.debug("RUT a validar: " + rut + "-" + dvValue);
        char dvr = '0';
        int suma = 0;
        int mul = 2;
        for(int i = rut.length() - 1; i >= 0; i--)
        {
            suma += Character.digit(rut.charAt(i), 10) * mul;
            if(mul == 7)
                mul = 2;
            else
                mul++;
        }

        logger.debug("suma:" + suma);
        int res = suma % 11;
        logger.debug("res:" + res);
        if(res == 1)
            dvr = 'k';
        else
        if(res == 0)
        {
            dvr = '0';
        } else
        {
            int dvi = 11 - res;
            dvr = Character.forDigit(dvi, 10);
        }
        logger.debug("dvr:" + dvr);
        if(dvr != Character.toLowerCase(dvValue.charAt(0)))
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        } else
        {
            return true;
        }
    }

    public static boolean validateRUT(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        Logger logger = Logger.getLogger(cl.araucana.common.ui.struts.StrutsValidator.class);
        String rut = ValidatorUtil.getValueAsString(bean, field.getProperty());
        if(rut.length() < 2)
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        }
        String dvValue = ValidatorUtil.getValueAsString(bean, field.getVarValue("dv"));
        logger.debug("dvValue:" + dvValue);
        char dvr = '0';
        int suma = 0;
        int mul = 2;
        for(int i = rut.length() - 1; i >= 0; i--)
        {
            suma += Character.digit(rut.charAt(i), 10) * mul;
            if(mul == 7)
                mul = 2;
            else
                mul++;
        }

        logger.debug("suma:" + suma);
        int res = suma % 11;
        logger.debug("res:" + res);
        if(res == 1)
            dvr = 'k';
        else
        if(res == 0)
        {
            dvr = '0';
        } else
        {
            int dvi = 11 - res;
            dvr = Character.forDigit(dvi, 10);
        }
        logger.debug("dvr:" + dvr);
        if(dvr != Character.toLowerCase(dvValue.charAt(0)))
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        } else
        {
            return true;
        }
    }

    public static boolean validateRutSinDv(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        Logger logger = Logger.getLogger(cl.araucana.common.ui.struts.StrutsValidator.class);
        String rut = ValidatorUtil.getValueAsString(bean, field.getProperty());
        if(rut.length() < 4)
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        }
        int posicionDv = rut.indexOf("-");
        String dvValue = ValidatorUtil.getValueAsString(bean, field.getVarValue("digito"));
        logger.debug("dv " + dvValue);
        logger.debug("POSICION GUION " + posicionDv);
        if(posicionDv < 1 && dvValue == "")
        {
            logger.debug("AQUI 1");
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        }
        logger.debug("AQUI 2");
        if(posicionDv > 0)
        {
            logger.debug("AQUI 3");
            if(posicionDv != rut.length() - 2)
            {
                logger.debug("AQUI 4");
                errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
                return false;
            }
            logger.debug("AQUI 5");
            String rutSinDv = rut.substring(0, posicionDv);
            dvValue = rut.substring(posicionDv + 1);
            rut = rutSinDv;
        } else
        if(dvValue == null)
        {
            logger.debug("AQUI 6");
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        }
        logger.debug("AQUI 7");
        if(dvValue.trim().length() == 0)
        {
            logger.debug("AQUI 8");
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        }
        logger.debug("AQUI 8");
        logger.debug("dvValue:" + dvValue);
        char dvr = '0';
        int suma = 0;
        int mul = 2;
        for(int i = rut.length() - 1; i >= 0; i--)
        {
            suma += Character.digit(rut.charAt(i), 10) * mul;
            if(mul == 7)
                mul = 2;
            else
                mul++;
        }

        logger.debug("suma:" + suma);
        int res = suma % 11;
        logger.debug("res:" + res);
        if(res == 1)
            dvr = 'k';
        else
        if(res == 0)
        {
            dvr = '0';
        } else
        {
            int dvi = 11 - res;
            dvr = Character.forDigit(dvi, 10);
        }
        logger.debug("dvr:" + dvr);
        if(dvr != Character.toLowerCase(dvValue.charAt(0)))
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        } else
        {
            return true;
        }
    }

    public static boolean validatePeriodo(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        Logger logger = Logger.getLogger(cl.araucana.common.ui.struts.StrutsValidator.class);
        logger.debug("VALIDADOR de Periodo");
        String fechaInicial = ValidatorUtil.getValueAsString(bean, field.getProperty());
        String fechaFinal = ValidatorUtil.getValueAsString(bean, field.getVarValue("fechaFinal"));
        String datePatternStrict = field.getVarValue("datePatternStrict");
        logger.debug("fecha inicial = " + fechaInicial);
        logger.debug("fecha final = " + fechaFinal);
        logger.debug("datePatternStrict = " + datePatternStrict);
        if(fechaFinal.equals(""))
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(datePatternStrict == null ? "dd/MM/yyyy" : datePatternStrict);
        Date fchInicial = formatter.parse(fechaInicial, new ParsePosition(0));
        Date fchFinal = formatter.parse(fechaFinal, new ParsePosition(0));
        logger.debug("fchInicial = " + fchInicial);
        logger.debug("fchFinal = " + fchFinal);
        logger.debug("valor de comparacion: " + fchFinal.compareTo(fchInicial));
        if(fchFinal.compareTo(fchInicial) < 0)
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        } else
        {
            return true;
        }
    }

    public static boolean validatePeriodoBonificacion(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        Logger logger = Logger.getLogger(cl.araucana.common.ui.struts.StrutsValidator.class);
        logger.debug("VALIDADOR de Periodo Bonificacion");
        String fechaInicial = ValidatorUtil.getValueAsString(bean, field.getProperty());
        String fechaFinal = ValidatorUtil.getValueAsString(bean, field.getVarValue("fechaFinal"));
        logger.debug("fecha inicial = " + fechaInicial);
        logger.debug("fecha final = " + fechaFinal);
        if(fechaInicial.equals("") && fechaFinal.equals(""))
            return true;
        if(fechaFinal.equals(""))
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date fchInicial = formatter.parse(fechaInicial, new ParsePosition(0));
        Date fchFinal = formatter.parse(fechaFinal, new ParsePosition(0));
        logger.debug("valor de comparacion: " + fchFinal.compareTo(fchInicial));
        if(fchFinal.compareTo(fchInicial) < 0)
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        } else
        {
            return true;
        }
    }

    public static boolean validatePeriodoPosibleFechaFinalNula(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        Logger logger = Logger.getLogger(cl.araucana.common.ui.struts.StrutsValidator.class);
        logger.debug("VALIDADOR de Periodo Posible Fecha Final Nula");
        String fechaInicial = ValidatorUtil.getValueAsString(bean, field.getProperty());
        String fechaFinal = ValidatorUtil.getValueAsString(bean, field.getVarValue("fechaFinal"));
        logger.debug("fecha inicial = " + fechaInicial);
        logger.debug("fecha final = " + fechaFinal);
        if(fechaFinal.equals(""))
            return true;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date fchInicial = formatter.parse(fechaInicial, new ParsePosition(0));
        Date fchFinal = formatter.parse(fechaFinal, new ParsePosition(0));
        logger.debug("valor de comparacion: " + fchFinal.compareTo(fchInicial));
        if(fchFinal.compareTo(fchInicial) < 0)
        {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
            return false;
        } else
        {
            return true;
        }
    }
}
