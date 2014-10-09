package edu.upc.eetac.dsa.draja.ClasesEsenciales3;

import java.io.*;
import java.net.*;

import edu.upc.eetac.dsa.draja.ClasesEsenciales3.ThreadUsuario;

public class ServidorConcurrenteTCP {

public static void main(String[] args) throws IOException{
		
		ServerSocket ServSocket=null;
		Socket ClientSocket=null;
		int puerto=10007, i=0;
		
		try{
			ServSocket= new ServerSocket(puerto);
		}catch(IOException ioe){
			System.err.println("No se peude escuchar por el puerto: "+puerto);
			System.exit(1);
		}
		
		System.out.println("Esperando para la conexión...");
		
		while(true){
			ClientSocket=ServSocket.accept();

			//La conexión ha sido aceptada satisfactoriamente.
			System.out.println("Conexión satisfactoria.");
			System.out.println("Esperando para la entrada...");

			ThreadUsuario cliente = new ThreadUsuario(ClientSocket,i);
			cliente.start();
			i++;
			
		}
		
	}

	
}
