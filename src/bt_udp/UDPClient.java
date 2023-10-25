/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt_udp;

/**
 *
 * @author Duong Nguyen
 */
import java.io.*;
import java.net.*;

public class UDPClient {

    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Khởi tạo socket UDP
            socket = new DatagramSocket();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            // Gửi dữ liệu đến server
            String message = "Hello, server!";
            byte[] requestData = message.getBytes();
            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress, serverPort);
            socket.send(requestPacket);

            // Nhận phản hồi từ server
            byte[] buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Received response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
