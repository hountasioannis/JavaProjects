package gr.aueb.cf.ch1;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Scanner;

public class Server extends Thread {
    static ServerSocket servfd;
    final static int serverPort = 7;
    Socket connectedfd;

    Server(Socket sockfd) {
        this.connectedfd = sockfd;
    }

    @Override
    public void run()  {
        try {
            StringBuilder sb = new StringBuilder();
            PrintWriter outfd = new PrintWriter(connectedfd.getOutputStream(), true);
            BufferedReader infd = new BufferedReader(new InputStreamReader(connectedfd.getInputStream()));
            System.out.println("client connected on port " + serverPort + ".Servicing requests.");

            String inputLine;
            inputLine = infd.readLine();


            File file = new File(inputLine);
            if (file.exists()) {
                Scanner in = new Scanner(file);

                String line = null;
                while (in.hasNextLine()) {
                    line = in.nextLine();
                    sb.append(line).append("\n");
                }

                outfd.println(sb.toString());
            } else {
                outfd.println("file not found");
            }

            connectedfd.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
         try {
             servfd = new ServerSocket();
             servfd.bind(new InetSocketAddress("127.0.0.1", serverPort), 3);
             System.out.println("echo server is ready for accepting connections");
             while (true) {
                Socket connectedfd = servfd.accept();
                new Thread(new Server(connectedfd)).start();
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
