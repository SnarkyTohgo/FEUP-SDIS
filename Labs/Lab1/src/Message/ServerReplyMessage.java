package Message;

import java.util.ArrayList;

public class ServerReplyMessage implements Message {
    String message;

    public ServerReplyMessage(String op, ArrayList<String> params) {
        this.write(op, params);
    }

    @Override
    public void write(String op, ArrayList<String> params) {
        ArrayList<String> paramsCpy = params;
        this.message = paramsCpy.get(2) + "\n";
        paramsCpy.remove(2);

        for (String param : paramsCpy)
            this.message += param + " ";

    }

    @Override
    public void read() {
        System.out.println("SERVER REPLY MESSAGE: " + this.message);
    }

    @Override
    public String get() {
        return this.message;
    }
}
