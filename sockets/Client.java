package gr.aueb.cf.ch1;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        try {
            InetAddress servAddr = InetAddress.getByName("127.0.0.1");
            final int servPort = 7;
            Socket sockfd = new Socket(servAddr, servPort);

            Scanner in = new Scanner(System.in);
            PrintWriter outfd = new PrintWriter(sockfd.getOutputStream(), true);
            BufferedReader infd = new BufferedReader(new InputStreamReader(sockfd.getInputStream()));

            System.out.println("choose your file");
            String fileName = in.nextLine();

            String path = "C:\\Users\\Public\\" + fileName;
            outfd.println(path);

            String line = null;
            while ((line = infd.readLine()) != null) {
                sb.append(line).append("\n");
            }
            System.out.println("Server says: " + sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

