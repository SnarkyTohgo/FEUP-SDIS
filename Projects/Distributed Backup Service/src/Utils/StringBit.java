package Utils;

import java.io.File;
import Exceptions.NoSuchFileException;
import java.io.IOException;

public class StringBit {
    private String data;

    public StringBit() {}

    public StringBit(String fileName) throws NoSuchFileException {
        File file = new File(fileName);

        if (!file.exists() || !file.isFile())
            throw new NoSuchFileException("File not found");

        data = fileName + file.length() + file.lastModified();
    }

    public String getData() {
        return data;
    }
}
