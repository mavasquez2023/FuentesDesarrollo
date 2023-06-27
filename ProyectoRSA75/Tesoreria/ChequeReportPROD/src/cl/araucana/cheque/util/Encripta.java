package cl.araucana.cheque.util;

import cl.araucana.core.util.UserPrincipal;

public class Encripta {

	public static int generarClave(String rutEmpresa,String email){
		double clave =  Math.random()*10000;
		return (int) clave;
	}

	public static UserPrincipal decode(String credential)
    {
		UserPrincipal userprincipal1 = UserPrincipal.decodeUserCredentials(credential);
        return userprincipal1;

    }

	public static String encode(String usuario, String clave)
    {
		UserPrincipal userprincipal = new UserPrincipal(usuario, clave);
        return userprincipal.encode();
    }

	public static void main(String args[])
    {

		if(args.length == 2 )
		{
			System.out.println(encode(args[0], args[1]));
		} else
			if(args.length == 1 )
			{
				System.out.println(decode(args[0]));
			}
		System.exit(0);
    }
}
