import Client.Client;
import Message.ClientRequestMessage;
import Message.Message;
import Server.Server;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Application {
    static Client client;
    static Server server;

    public static void setup() throws SocketException, UnknownHostException {
        client = new Client();

        server = Server.getInstance(4445);
        server.start();
    }

    public static void shutdown() throws IOException {
        String op = "end";
        ArrayList<String> params = new ArrayList<String>();
        Message end = new ClientRequestMessage(op, params);

        client.sendRequest(end);
        client.close();
    }

    public static void main(String[] args) throws IOException {
        setup();

        String opRegister = "REGISTER";
        ArrayList<String> paramsRegister = new ArrayList<String>();
        paramsRegister.add("www.fe.up.pt");
        paramsRegister.add("10.227.240.205");

        Message register = new ClientRequestMessage(opRegister, paramsRegister);
        String echo = client.sendRequest(register);
        System.out.println(echo);

//        String opLookup = "LOOKUP";
//        ArrayList<String> paramsLookup = new ArrayList<String>();
//        paramsLookup.add("www.fe.up.pt");
//
//        Message lookup = new ClientRequestMessage(opLookup, paramsLookup);
//        client.sendRequest(lookup);

        shutdown();
    }
}
