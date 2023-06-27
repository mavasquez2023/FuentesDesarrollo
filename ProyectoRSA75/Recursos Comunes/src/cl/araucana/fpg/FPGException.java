// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 04-09-2015 15:49:53
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FPGException.java

package cl.araucana.fpg;


public class FPGException extends Exception
{

    public FPGException()
    {
    }

    public FPGException(String message)
    {
        super(message);
    }

    public FPGException(Throwable cause)
    {
        super(cause);
    }

    public FPGException(String message, Throwable cause)
    {
        super(message, cause);
    }

    private static final long serialVersionUID = 0xd0e2ef7b53e0ba0L;
}
