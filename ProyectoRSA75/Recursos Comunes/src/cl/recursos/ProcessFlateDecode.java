/**
 * 
 */
package cl.recursos;

/**
 * @author usist24
 *
 */
import java.io.*;
import java.util.*;
import java.util.zip.*;
 
class ProcessFlateDecode {
 
	private File f;
	public ProcessFlateDecode(File f){
		this.f=f;
	}
	public void process() throws Exception{
 
		ArrayList list = new ArrayList();
 
		FileInputStream ins = new FileInputStream(f);
 
		while(true){
			int data = ins.read();
			if(data==-1) break;
 
			list.add( new Integer(data) );
		}
		ins.close();
 
 
		int compressedDataLength = list.size();
		byte[] output = new byte[compressedDataLength];
		for(int i=0; i<list.size(); i++){
			Integer o=(Integer)list.get(i);
			output[i]=(byte)(o.intValue());
		}
 
		Inflater decompresser = new Inflater();
		decompresser.setInput(output, 0, compressedDataLength);
		byte[] result = new byte[100];
		int resultLength = decompresser.inflate(result);
 
		decompresser.end();
 
		String outputString = new String(result, 0, resultLength, "UTF-8");
		System.out.println(outputString);
	}
 
	public static void main(String[] args) throws Exception{
		new ProcessFlateDecode(new File("C:/tmp/219.txt")).process();
	}
}
