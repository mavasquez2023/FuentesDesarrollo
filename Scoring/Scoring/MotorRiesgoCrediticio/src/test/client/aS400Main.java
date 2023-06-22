package test.client;

import com.ibm.as400.access.*;

public class aS400Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ref.: http://javadoc.midrange.com/jtopen/com/ibm/as400/access/ProgramCall.html
		// Compilado contra J2SE-1.4, JTOpen 7.7 
		String host = "146.83.1.5";
		String userName = "SISTEMAS";
		String password = "SISTEMAS";
	    AS400 system = new AS400(host, userName, password);
	    ProgramCall program = new ProgramCall(system);
	    try
	    {
	        // Initialize the name of the program to run.
	        String programName = "/QSYS.LIB/CROBJ.LIB/CRC011.PGM";
	        // Set up the 3 parameters.
	        ProgramParameter[] parameterList = new ProgramParameter[3];
	        // Parametro 1 es de entada, tipo texto.
	        AS400Text nametext = new AS400Text(114);
	        //String parameter = "020339366005510                                                                                                   ";
	        String parameter = "022105723605510                                                                                                   ";
	        parameterList[0] = new ProgramParameter(nametext.toBytes(parameter));
	        
	        // Parametros 2 y 3 son de control y s20alida, solo se especifica el largo.
	        parameterList[1] = new ProgramParameter(2);
	        parameterList[2] = new ProgramParameter(165);
	        
	        // Set the program name and parameter list.
	        program.setProgram(programName, parameterList);
	        // Run the program.
	        if (program.run() != true)
	        {
	            // Aquí se maneja el error.
	            System.out.println("Program failed!");
	            // Show the messages.
	            AS400Message[] messagelist = program.getMessageList();
	            for (int i = 0; i < messagelist.length; ++i)
	            {
	                // Show each message.
	                System.out.println(messagelist[i]);
	            }
	        }
	        // Else no error, get output data.
	        else
	        {
	        	// Lectura del parametro de control.
	        	AS400Text controlParameters = new AS400Text(2);
	        	byte[] outputData = parameterList[1].getOutputData();
	            Object outputDataObject = controlParameters.toObject(outputData); 
	            System.out.println(outputDataObject);
	        	
	        	// Lectura del parametro de salida
	            AS400Text text = new AS400Text(165);
	            outputData = parameterList[2].getOutputData();
	            outputDataObject = text.toObject(outputData); 
	            System.out.println(outputDataObject);
	        }
	    }
	    catch (Exception e)
	    {
	        System.out.println("Program " + program.getProgram() + " issued an exception!");
	        e.printStackTrace();
	    }
	    // Done with the system.
	    system.disconnectAllServices();
	}
}
