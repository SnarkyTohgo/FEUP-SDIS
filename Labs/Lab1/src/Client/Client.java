package Client;

import Message.Message;

import java.io.IOException;
import java.net.*;

public class Client {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    public Client() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String sendRequest(Message msg) throws IOException {
        buf = msg.get().getBytes();

        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);

        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        String response = new String(packet.getData(), 0, packet.getLength());

        return response;
    }

    public void close() {
        socket.close();
    }
}
