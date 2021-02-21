package Message;

import java.util.ArrayList;

public interface Message {
    public void write(String op, ArrayList<String> params);
    public void read();
    public String get();
}
