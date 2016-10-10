package org.nk.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.nk.client.PeerClientHandler;

public class PeerNode implements Runnable{
	
	private ServerSocket server;
	private int port = 5800;
	private boolean isStopped = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(port);
			System.out.println("Server started. Listening to "+port);
			
			while(!isStopped){
				Socket clientSocket = server.accept();
				PeerClientHandler handler = new PeerClientHandler(clientSocket);
				handler.run();
			}
			
			server.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
