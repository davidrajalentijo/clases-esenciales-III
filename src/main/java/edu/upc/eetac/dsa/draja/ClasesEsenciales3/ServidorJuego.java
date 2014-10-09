package edu.upc.eetac.dsa.draja.ClasesEsenciales3;

import java.net.*;
import java.io.*;

import edu.upc.eetac.dsa.draja.ClasesEsenciales3.ThreadUser;

public class ServidorJuego extends Thread {
	
public static void main(String [] args) throws IOException{
		
		//Instanciamos los sockets y el puerto
		ServerSocket ServSocket=null;
		Socket ClientSocket1=null, ClientSocket2=null;//Cogeremos 2  Usuarios para el juego
		int puerto=1010,i=0;
		InetAddress IpUser1=null,IpUser2=null;
		
		//Instanciamos para poder escribir/leer a un Usuario.
		
		PrintWriter out= null;
		BufferedReader in1 = null;
		BufferedReader in2 = null;
		String Linea=null, nombre1=null, nombre2=null;
		String [] trozos;
		
		
		//Probamos de escuchar por el puerto que queremos
		try{
			ServSocket = new ServerSocket(puerto);
		}catch(IOException ioe){
			System.err.println("No se puede escuchar por el puerto: "+puerto);
			System.exit(1);
		}catch(NullPointerException npe){
			npe.printStackTrace();
		}
		
		System.out.println("Esperando connexión...");
		
		while(true){
			ClientSocket1 = ServSocket.accept();
			IpUser1= ClientSocket1.getInetAddress();
			System.out.println("Conexión establecida con: "+IpUser1+" con id de partida: "+i);
			out= new PrintWriter (ClientSocket1.getOutputStream(),true );
			in1 = new BufferedReader ( new InputStreamReader ( ClientSocket1.getInputStream()));
			while((Linea=in1.readLine())!=null){
				trozos=Linea.split(" ");
				if(trozos[0]=="PLAY"){
					nombre1= trozos[1];
					break;
				}
				
			}
			out.println("WAIT ");
			System.out.println("Hemos enviado al Usuario1 el WAIT...");
			System.out.println("Esperando al 2º jugador");

			
			ClientSocket2 = ServSocket.accept();
			IpUser2= ClientSocket2.getInetAddress();
			System.out.println("Conexión establecida con el segundo jugador: "+IpUser2+" con id de partida: "+i);
			in2 = new BufferedReader ( new InputStreamReader ( ClientSocket2.getInputStream()));
			System.out.println("El juego ya puede empezar.");
			while((Linea=in2.readLine())!=null){
				trozos=Linea.split(" ");
				if(trozos[0]=="PLAY"){
					nombre2= trozos[1];
					break;
				}
				
			}
			ThreadUser User = new ThreadUser(ClientSocket1,ClientSocket2,nombre1,nombre2,i);
			User.start();
			i++;
		}
		
	}

}
