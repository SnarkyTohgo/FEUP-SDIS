package Client;

import java.io.IOException;
import java.net.*;

public class Client implements Runnable {
    String ip;
    int port;

    private byte[] buf;

    private MulticastSocket socket;
    private InetAddress mcastaddr;
    private InetSocketAddress group;
    private NetworkInterface netIf;

    private DatagramPacket packet;

    public static void main(String[] args) {
        String v_ip = "230.0.0.0";
        int v_port = 4321;

        Thread t = new Thread(new Client(v_ip, v_port));
        t.start();
    }

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void
    receive(String ip, int port)
        throws IOException {

        buf = new byte[1024];

        socket = new MulticastSocket(port);
        mcastaddr = InetAddress.getByName(ip);
        group = new InetSocketAddress(mcastaddr, port);
        netIf = NetworkInterface.getByName("bge0");

        socket.joinGroup(group, netIf);

        while (true) {
            System.out.println("Waiting for multicast message...");

            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
            System.out.println("[Multicast UDP message received] >> "+msg);

            if (msg.equals("end")) {
                System.out.println("Exiting...");
                break;
            }
        }

        socket.leaveGroup(group, netIf);
        socket.close();
    }

    @Override
    public void run() {
        try {
            receive(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
