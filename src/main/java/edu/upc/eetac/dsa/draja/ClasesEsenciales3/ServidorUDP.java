package edu.upc.eetac.dsa.draja.ClasesEsenciales3;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ServidorUDP {
	
	public static void main (String args []) throws IOException{
		 int i;
		
		try{
			//Creamos el Socket UDP del servidor ( puerto 10009 )
			DatagramSocket ServerSocket = new DatagramSocket(10009);
			
			//Creamos los atributos tipo byte para enviar/leer del cliente y los paquetes.
			byte[] RecievData = null;
			byte[] SendData= new byte[1024];
			DatagramPacket RecievPacket= null;
			DatagramPacket SendPacket=null;
			String sentencia=null;//Mensaje tipo string.
			
			while(( i=10)!=0){// Acepta 10 clientes
				
				RecievData= new byte[1024];
				
				//Creamos un paquete para poder recibir la info
				 RecievPacket= new DatagramPacket ( RecievData, RecievData.length);
				System.out.println("Esperando un paquete datagrama...");
				
				//Preparamos el socket para poder recibir paquetes
				ServerSocket.receive(RecievPacket);
				
				sentencia = new String (RecievPacket.getData());
				
				//Para saber de quien es el paquete:
				InetAddress IPAddress = RecievPacket.getAddress(); 
				  
		        int port = RecievPacket.getPort(); 
		  
		        System.out.println ("From: " + IPAddress + ":" + port);
		        System.out.println ("Message: " + sentencia);
		        
		      //Sacar fecha del sistema
				Date fecha = new Date();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY  HH: mm: ss");
				String fechastring = formato.format(fecha);
				System.out.println("Servidor---> "+fechastring);
				
				//Enviamos los datos al cliente
				SendData=fechastring.getBytes();//Bytes
				SendPacket= new DatagramPacket(SendData, SendData.length,IPAddress,port);//Paquete
				ServerSocket.send(SendPacket);
				
				i--;
				
			}
			ServerSocket.close();
		}catch(SocketException se){
			se.printStackTrace();
		}
	}

}
