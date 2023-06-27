// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 29-10-2018 13:09:07
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Constants.java

package cl.araucana.cr.pdfservice.nc;


public interface Constants
{

    public static final int SELECTION_ALL = 0;
    public static final int SELECTION_BY_OFICINA = 1;
    public static final int SELECTION_BY_EMPRESA = 2;
    public static final int SELECTION_BY_FOLIO = 3;
    public static final String selectionName[] = {
        "all", "oficina", "empresa", "folio"
    };
    public static final int FILTER_AFILIADOS = 0;
    public static final int FILTER_PENSIONADOS = 1;
    public static final int FILTER_NONE = -1;
    public static final int FILTER_DEFAULT = 0;
    public static final int MAX_CUOTAS_PER_PAGE = 19;

}
