package Server;

import utils.ConstsHelper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.TreeMap;

public class Server extends Thread {
    public static Server instance = null;

    private static TreeMap lookupTable;

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    private Server(Integer port) throws SocketException {
        lookupTable = new TreeMap<String, String>();
        socket = new DatagramSocket(port);
    }

    public static Server getInstance(Integer port) throws SocketException {
        if (instance == null)
            instance = new Server(port);

        return instance;
    }

    public void run() {
        running = true;

        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress address = packet.getAddress();
            int port = packet.getPort();

            packet = new DatagramPacket(buf, buf.length, address, port);
            String request = new String(packet.getData(), 0, packet.getLength());

            if (request.equals("end")) {
                running = false;
                continue;
            }

            try {
                String[] msg = request.split(" ", 3);
                String reply = "default reply";

                if (msg[0].equals("REGISTER"))
                    reply = String.valueOf(register(msg[1], msg[2]));


                if (msg[0].equals("LOOKUP"))
                    reply = lookup(msg[1]);


                buf = reply.getBytes();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }

    public Integer register(String DNS, String IP) {
        if (!lookupTable.containsKey(DNS)) {
            lookupTable.put(DNS, IP);
            return lookupTable.size();
        }

        return ConstsHelper.Server.IP_ALREADY_REGISTERED;
    }

    public String lookup(String DNS) {
        if (lookupTable.containsKey(DNS))
            return (String) lookupTable.get(DNS);

        return ConstsHelper.Server.NOT_FOUND;
    }
}
