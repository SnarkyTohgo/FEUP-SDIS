import Client.Client;
import Server.Server;

import java.io.IOException;
import java.util.ArrayList;

public class Application {

    public static void main(String[] args)
            throws IOException {

        Server server = new Server();
        Client client = new Client("230.0.0.0", 4321);

        ArrayList<String> messages = new ArrayList<>();
        messages.add("Adriana");
        messages.add("amo-te muito ♥");
        messages.add("vamos para a caminha? ☻");

        for (String message : messages)
            server.send(message, "230.0.0.0", 4321);

    }
}
