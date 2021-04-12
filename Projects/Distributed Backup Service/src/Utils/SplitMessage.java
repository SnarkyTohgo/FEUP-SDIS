package Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitMessage {
    List<byte[] > data;

    public SplitMessage() {}

    public SplitMessage(byte[] msg) {
        int pos = 0;
        for (; pos < msg.length - 4; ++pos) {
            if (msg[pos] == 0xD && msg[pos + 1] == 0xA && msg[pos + 2] == 0xD && msg[pos + 3] == 0xA)
                break;
        }

        byte[] header = Arrays.copyOfRange(msg, 0, pos);
        byte[] body = Arrays.copyOfRange(msg, pos + 4, msg.length);

        data = new ArrayList<>();
        data.add(header);
        data.add(body);
    }

    public List<byte[] > getData() {
        return data;
    }
}