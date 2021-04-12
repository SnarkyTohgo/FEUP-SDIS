package Utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import static Utils.Consts.Chunk.*;

public class Chunk {
    private int repDeg;
    private byte[] data;

    /**
     * @brief Default constructor
     */
    public Chunk() {
        repDeg = 0;
    }

    /**
     * @brief Constructor with arguments
     * @param data data that constitutes the chunk
     */
    public Chunk(byte[] data) {
        repDeg = 0;
        this.data = data;
    }

    /**
     * @brief Fetch the value of the data bytes
     * @return Returns the data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * @brief Set the value of the data bytes
     * @param data new data
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    /**
     * @brief Fetch the value of the replication degree
     * @return Returns the replication degree
     */
    public int getRepDeg() {
        return repDeg;
    }

    /**
     * @brief Set the value of the replication degree
     * @param repDeg new repDeg
     */
    public void setRepDeg(int repDeg) {
        this.repDeg = repDeg;
    }

    /**
     * @brief Calculates number of splits and remaining file size from the chunk position,
     *        then tries to access the file in a non-sequential random position, setting a byte counter
     *        to count the number of bytes that need to be written. Updates the position in the file
     *        and while the file size is bigger than the chunk's size it calculates the remaining bytes.
     *        Proceeds to read from the file, sets position to 0 and limits used capacity and recalculates
     *        buffer capacity that is being used
     *
     * @param info ChunkInfo object containing the information required to perform this operation
     *
     * @return returns the new chunk
     * @throws Exception
     */
    public static Chunk getChunk(ChunkInfo info) throws Exception {
        Chunk chunk = new Chunk();
        long size = info.getFileSize() - (info.getChunkSize()*info.getPos());

        try (RandomAccessFile file = new RandomAccessFile(info.getFileName(), "r");
             FileChannel channel = file.getChannel()) {

            channel.position(info.getPos()*info.getChunkSize());
            ByteBuffer buffer;

            if (size >= info.getChunkSize()) {
                buffer = ByteBuffer.allocate((int) info.getChunkSize());
                size -= info.getChunkSize();
            } else {
                buffer = ByteBuffer.allocate((int) size);
            }

            channel.read(buffer);
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);

            chunk.setData(data);
        }
        return chunk;
    }

    public static Chunk getChunk(String fileName, long pos) throws Exception {
        ChunkInfo info = new ChunkInfo(fileName, Files.size(Paths.get(fileName)), pos, CHUNK_SIZE);
        return getChunk(info);
    }

    /**
     * @brief Creates a folder if it does not exists and saves the chunk in the folder
     * @param info SenderInfo object containing the information required to perform this operation
     */
    public static void saveChunk(SenderInfo info) {
        String filename = info.getChunkNo() + SUFFIX;
        File dir = new File(BACKUP_FOLDER + "/" + info.getHash() + info.getId());

        if (!dir.exists())
            dir.mkdirs();

        try {
            FileOutputStream fos = new FileOutputStream(BACKUP_FOLDER + "/" + info.getHash() + "/" + info.getId() + "/" + info.getRepDeg() + "-" + info.getChunkNo() + SUFFIX);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("chunk saved successfully");
    }

    /**
     * @brief Gets data from a chunk
     *
     * @param fileName
     * @param buffer
     *
     * @return Returns true if file exists and is not a directory, false otherwise
     * @throws IOException
     */
    public static boolean getData(String fileName, byte[] buffer) throws IOException {
        File file = new File(BACKUP_FOLDER + "/" + fileName);

        if (file.exists() && !file.isDirectory()) {
            buffer = Files.readAllBytes(file.toPath());
            String header = new String(buffer, 0, buffer.length);

            System.out.println("Truncated chunk data: " + header.substring(0, 20));

            return true;

        }
        return false;
    }
}
