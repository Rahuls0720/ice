package src;

import java.net.*;
import java.util.Scanner;
import java.io.*;


public class Client {
	private static final String HOST_ADDRESS = "localhost";
	private static final int TCP_PORT = 7000;
	private Socket socket;

	public Client (Socket socket) {
		this.socket = socket;
	}

	// send message to server
	private void sendMessage(String cmd) throws IOException {
		PrintStream pout = new PrintStream(socket.getOutputStream());
		pout.println(cmd);
		pout.flush();
	}

	// receive message from server
	public String receiveMessage() throws IOException {
		Scanner sc = new Scanner(socket.getInputStream());
		return sc.nextLine();
	}

	public static void main(String[] args) {
		try {
			InetAddress hostIa = InetAddress.getByName(HOST_ADDRESS);
			Client client = new Client(new Socket(hostIa, TCP_PORT));
			System.out.println("established tcp connection with server");

			client.sendMessage("message");
			System.out.println(client.receiveMessage());

			client.socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
