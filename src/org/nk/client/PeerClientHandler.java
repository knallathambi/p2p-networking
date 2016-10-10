package org.nk.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class PeerClientHandler implements Runnable{
	
	private Socket clientSocket;
	private BufferedReader in;
	private BufferedWriter out;
	
	public PeerClientHandler(Socket clientSocket){
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		
		try {
			System.out.println("Client connected.. "+this.clientSocket.getRemoteSocketAddress().toString());
			
			this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			this.out = new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()));
			
			while(true){
				
				String input = in.readLine();
                if (input == null || input.equals(".")) {
                    break;
                }
				System.out.println("Message received - "+input);
				this.out.write("Processed");
				this.out.newLine();
				this.out.flush();
			}
			
			this.clientSocket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
