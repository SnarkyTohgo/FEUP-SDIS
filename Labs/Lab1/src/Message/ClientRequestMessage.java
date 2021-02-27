package Message;

import java.util.ArrayList;

public class ClientRequestMessage implements Message {
    String message;

    public ClientRequestMessage(String op, ArrayList<String> params) {
        this.write(op, params);
    }

    @Override
    public void write(String op, ArrayList<String> params) {
        this.message = op + " ";

        for (String param : params)
            this.message += param + " ";
    }

    @Override
    public void read() {
        System.out.println("CLIENT REQUEST MESSAGE: " + this.message);
    }

    @Override
    public String get() {
        return this.message;
    }
}
