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

public class UDPServer {

    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Khởi tạo socket UDP và cổng lắng nghe
            socket = new DatagramSocket(12345);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                // Nhận dữ liệu từ client
                socket.receive(packet);

                String data = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received data: " + data);

                // Xử lý dữ liệu
                // Gửi phản hồi về client
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                String response = "Hello, client!";
                byte[] responseData = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                socket.send(responsePacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
