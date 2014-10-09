package edu.upc.eetac.dsa.draja.ClasesEsenciales3;


import java.net.*; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;


public class ServidorTCP {
	
public static void main(String[] args) throws IOException{
		
		ServerSocket serverSocket =null;
		int puerto=10007;
		
		try{
			//Probamos que nuestro servidor escuche por el puerto 10007.
			serverSocket = new ServerSocket (puerto);
		}catch(IOException ioe){
			System.err.println("No se puede escuchar por el puerto: "+puerto);
			System.exit(1);
		}
		
		Socket ClientSocket=null;
		System.out.println("Esperando para la conexión...");
		
		//Probamos que cuando el Servidor detecte una conexión, esta no falle.
		ClientSocket=serverSocket.accept();

		
		//La conexión ha sido aceptada satisfactoriamente.
		System.out.println("Conexión satisfactoria.");
		System.out.println("Esperando para la entrada...");
		
		//Creamos los objetos para poder enviar y recibir de un cliente.
		PrintWriter out= new PrintWriter(ClientSocket.getOutputStream(),true);

		BufferedReader in =new BufferedReader( new InputStreamReader(ClientSocket.getInputStream()));
		
		String inputLine;
		while((inputLine=in.readLine())!= null){
			
			System.out.println("Servidor---> Recibimos: "+inputLine);
			
			//Sacar fecha del sistema
			Date fecha = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY  HH: mm: ss");
			String fechastring = formato.format(fecha);
			System.out.println("Servidor---> "+fechastring);
			
			//Enviamos la fecha al cliente.			
			out.println(fechastring);
			System.out.println("Servidor---> Hemos enviado correctamente los datos al CLiente.");
					
		}
		
		//Cerramos objetos.
		out.close();
		in.close();
		//Cerramos Connexiones.
		ClientSocket.close();
		serverSocket.close();
		System.out.println("Servidor---> Hemos cerrado todo.");
	}
}