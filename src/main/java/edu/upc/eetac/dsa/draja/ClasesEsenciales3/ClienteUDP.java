package edu.upc.eetac.dsa.draja.ClasesEsenciales3;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClienteUDP {
	
public static void main (String args []) throws IOException{
		
		try{
			String ServerHostName= new String ("127.0.01");
			
			//??????+
			if(args.length>0)
				ServerHostName = args[0];
			
			//Creamos objeto para poder leer lo que entra en el sistema del cliente
			BufferedReader inFromUser = new BufferedReader ( new InputStreamReader(System.in));
			
			//creamos el Socket UDP del cliente
			DatagramSocket ClientSocket = new DatagramSocket();
			
			//Cogemos la IP del Servidor
			InetAddress IPAddress=InetAddress.getByName(ServerHostName);
			System.out.println("Intentando conectarse a "+IPAddress+" a través del puerto 10009");
			
			byte[] SendData=new byte[1024];
			byte[] RecievData= new byte [1024];
			
			//Creamos el mensaje para enviarselo al servidor
			String mensaje= "Dame la hora.";
			SendData=mensaje.getBytes();
			//Creamos el paquete para enviar al servidor
			DatagramPacket SendPacket= new DatagramPacket(SendData, SendData.length,IPAddress,10009);
			//Enviamos el paquete con el mensaje al servidor
			ClientSocket.send(SendPacket);
			
			
			//Ahora tenemos que crear el paquete para poder recibir lo que nos envia el servidor
			DatagramPacket ReceivePacket= new DatagramPacket(RecievData, RecievData.length,IPAddress,10009);
			
			System.out.println("Esperando a recibir paquete...");
			
			
				ClientSocket.receive(ReceivePacket);
				String msgreceive= new String(ReceivePacket.getData());
				InetAddress returnIPAddress = ReceivePacket.getAddress();
				
				int port = ReceivePacket.getPort();
				
				System.out.println(" El Servidor: "+returnIPAddress+" : "+port);
				System.out.println(msgreceive);
			
			//Cerramos socket
				ClientSocket.close();
			
		}catch(UnknownHostException ex){
			ex.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

}
