package Utils;

public class SenderInfo {
    private int id, chunkNo, repDeg;
    String hash;
    byte[] data;

    /**
     * @brief Default constructor
     */
    public SenderInfo() {}

    /**
     * @brief Constructor with arguments
     * @param id sender id
     * @param hash original hash
     * @param chunkNo chunk number
     * @param repDeg replication degree
     * @param data data in bytes
     */
    public SenderInfo(int id, String hash, int chunkNo, int repDeg, byte[] data) {
        this.id = id;
        this.hash = hash;
        this.chunkNo = chunkNo;
        this.repDeg = repDeg;
        this.data = data;
    }

    /**
     * @brief Fetch the value of the sender id
     * @return Returns the sender id
     */
    public int getId() {
        return id;
    }

    /**
     * @brief Fetch the value of the original hash
     * @return Returns the original hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @brief Fetch the value of the chunk number
     * @return Returns the number of chunks
     */
    public int getChunkNo() {
        return chunkNo;
    }

    /**
     * @brief Fetch the value of the replication degree
     * @return Returns the replication degree
     */
    public int getRepDeg() {
        return repDeg;
    }

    /**
     * @brief Fetch the value of the data in bytes
     * @return Returns the data in bytes
     */
    public byte[] getData() {
        return data;
    }

    /**
     * @brief Set the value of the sender id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @brief Set the value of the original hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * @brief Set the value of the chunk number
     */
    public void setChunkNo(int chunkNo) {
        this.chunkNo = chunkNo;
    }

    /**
     * @brief Set the value of the replication degree
     */
    public void setRepDeg(int repDeg) {
        this.repDeg = repDeg;
    }

    /**
     * @brief Set the value of the data in bytes
     */
    public void setData(byte[] data) {
        this.data = data;
    }
}
