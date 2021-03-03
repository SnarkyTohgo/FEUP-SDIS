package Server;

import java.io.IOException;
import java.net.*;

public class Server {
    private static DatagramPacket packet;
    private static DatagramSocket socket;
    private static InetAddress group;

    public Server() {}

    public static void
    send(String message, String ip, int port)
            throws IOException {

        socket = new DatagramSocket();
        group = InetAddress.getByName(ip);

        byte[] msg = message.getBytes();
        packet = new DatagramPacket(msg, msg.length, group, port);

        socket.send(packet);
        socket.close();
    }
}
