package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Thread {
    private static final int TCP_PORT = 7000;
    private static ServerSocket connectionSocket; // generic: for listening for connection request
    private Socket socket; // client specific: "channel" a server can use to communicate with client

    public Server(Socket socket) {
        this.socket = socket;
    }

    // recieve message from client
    private String receiveMessage() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(scanner.nextLine());
        return "";
    }

    // send message to client
    private void sendMessage(String message) {
        PrintWriter pout = null;
        try {
            pout = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pout.flush();
    }

    @Override
    public void run() {
        receiveMessage();
        sendMessage("DO YOU SEE THIS?");
    }

    public static void main(String[] args) throws IOException {
        connectionSocket = new ServerSocket(TCP_PORT);
        while(true) {
            Thread t = new Server(connectionSocket.accept());
            System.out.println("established tcp connection with client");
            t.start();
        }
    }
}
