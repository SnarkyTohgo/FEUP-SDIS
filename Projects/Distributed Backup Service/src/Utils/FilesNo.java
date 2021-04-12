package Utils;

import Exceptions.NoSuchFileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesNo {
    int filesNo;

    public FilesNo() {}

    public FilesNo(String folderName) throws NoSuchFileException {
        File folder = new File(folderName);

        if (!folder.isDirectory())
            throw new NoSuchFileException("Folder not found");

        List<String> fileNames = new ArrayList<>();

        try {
            DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get(folderName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        filesNo = fileNames.size();
    }

    public int getFilesNo() {
        return filesNo;
    }
}
