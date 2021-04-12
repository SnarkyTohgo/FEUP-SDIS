package Utils;

public class ChunkInfo {
    private String fileName;
    private long fileSize,  pos, chunkSize;

    /**
     * @brief Default constructor
     */
    public ChunkInfo() {}

    /**
     * @brief Constructor with arguments
     * @param fileName name of the file
     * @param fileSize size of the file
     * @param pos position in bytes of the chunk
     * @param chunkSize size of the chunk
     */
    public ChunkInfo(String fileName, long fileSize, long pos, long chunkSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.pos = pos;
        this.chunkSize = chunkSize;
    }

    /**
     * @brief Fetch the value of the name of the file
     * @return Returns the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @brief Fetch the value of the size of the file
     * @return Returns the file size
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * @brief Fetch the value of the position of the chunk in bytes
     * @return Returns the position of the chunk in bytes
     */
    public long getPos() {
        return pos;
    }

    /**
     * @brief Fetch the value of the size of the chunk
     * @return Returns the size of the chunk
     */
    public long getChunkSize() {
        return chunkSize;
    }

    /**
     * @brief Set the value of the name of the file
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @brief Set the value of the size of the file
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @brief Set the value of the position in bytes of the chunk
     */
    public void setPos(long pos) {
        this.pos = pos;
    }

    /**
     * @brief Set the value of the size of the chunk
     */
    public void setChunkSize(long chunkSize) {
        this.chunkSize = chunkSize;
    }
}
