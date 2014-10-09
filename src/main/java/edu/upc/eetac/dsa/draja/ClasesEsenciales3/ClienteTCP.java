package edu.upc.eetac.dsa.draja.ClasesEsenciales3;

import java.io.*;
import java.net.*;

public class ClienteTCP {
	
public static void main(String[] args) throws IOException{
		
		//Asignamos una IP y puert al atributo ServerHostName
		String ServerHostName= new String ("127.0.0.1");
		int puerto= 10007;
		if(args.length>0)
			ServerHostName=args[0];
		System.out.println("Intentando conectar con el host " + ServerHostName+ " en el puerto "+ puerto);
		
		//Nombramos los objetos para la creación del Socket TCP.
		//Al crear un objeto Socket, estamos trabajando en protocolo TCP, si quisieramos UDP seria DatagramSocket.
		Socket ecoSocket = null;
		//Creamos el objeto para poder enviar los datos al servidor.
		PrintWriter out=null;
		//Creamos el objeto para poder leer los datos del servidor.
		BufferedReader in = null;
				
		try{
			//Rellenamos el objeto ecoSocketcon @IP y el puerto del servidor.
			ecoSocket = new Socket (ServerHostName,puerto);
			//Decimos de quien tienee que escribir ( al servidor )
			out = new PrintWriter ( ecoSocket.getOutputStream(),true);
			//Decimos a quien le tiene que leer lo que nos entra ( desde el servidor ).
			in = new BufferedReader ( new InputStreamReader ( ecoSocket.getInputStream()));	
		}catch(UnknownHostException uhe){
			System.err.println("No se ha encontrado el host: "+ ServerHostName);
			System.exit(1);
		}catch(IOException ioe){
			System.err.println("No se ha podido coger I/O de"+ "la conexión de: "+ ServerHostName);
			System.exit(1);
		}
		
		
		//Atributo para escribir/leer al servidor.
		String UserOutput;
		
		//Escribimos al Servidor.
		UserOutput="0";
		out.println(UserOutput);
		System.out.println("Cliente---> Hemos enviado correctamente los datos al servidor.");
		
		//Leemos lo que nos dice el Servidor
		System.out.println("Cliente---> Servidor: "+in.readLine());


		//Escribimos al Servidor.
		UserOutput="1";
		out.println(UserOutput);
		System.out.println("Cliente---> Hemos enviado correctamente los datos al servidor.");
				
		//Leemos lo que nos dice el Servidor
		System.out.println("Cliente---> Servidor: "+in.readLine());
		
		out.close();
		in.close();
		ecoSocket.close();
		System.out.println("Cliente---> Hemos cerrado todo.");
		
		
	}
}
