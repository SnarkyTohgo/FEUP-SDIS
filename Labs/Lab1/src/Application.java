import Client.Client;
import Server.Server;
import Message.ClientRequestMessage;
import Message.Message;
import Message.MessageFactory;
import Message.NoSuchMessageException;

import utils.MessageType;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Application {
    static Client client;
    static Server server;
    static String echo;
    static MessageFactory msgFactory;

    public static void setup() throws SocketException, UnknownHostException {
        msgFactory = new MessageFactory();

        client = new Client();
        Server.getInstance(4445).start();
    }

    public static void shutdown() throws IOException {
        String op = "end";
        ArrayList<String> params = new ArrayList<>();
        Message end = new ClientRequestMessage(op, params);

        client.sendRequest(end);
        client.close();
    }

    public static void main(String[] args) throws IOException, NoSuchMessageException {
        setup();

        // REGISTER REQUEST EXAMPLE
        String opRegister = "REGISTER";
        ArrayList<String> paramsRegister = new ArrayList<>();
        paramsRegister.add("www.fe.up.pt");
        paramsRegister.add("10.227.240.205");

        Message register = msgFactory.makeMessage(MessageType.REQUEST, opRegister, paramsRegister);
        echo = client.sendRequest(register);
        System.out.println(echo);

        // LOOKUP REQUEST EXAMPLE
        String opLookup = "LOOKUP";
        ArrayList<String> paramsLookup = new ArrayList<>();
        paramsLookup.add("www.fe.up.pt");

        Message lookup = msgFactory.makeMessage(MessageType.REQUEST, opLookup, paramsLookup);
        echo = client.sendRequest(lookup);
        System.out.println(echo);

        shutdown();
    }
}
