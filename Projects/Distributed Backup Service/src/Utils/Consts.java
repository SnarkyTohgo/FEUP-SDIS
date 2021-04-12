package Utils;

public class Consts {
    private Consts() {}

    public static class Application {
        private Application() {}

        public static final int MIN_ARGS = 2;
        public static final int MAX_ARGS = 4;
    }

    public static class Chunk {
        private Chunk() {}

        public static final long CHUNK_SIZE = 64000;
        public static final String BACKUP_FOLDER = "./BACKUPFOLDER/";
        public static final String SUFFIX = ".splitPart";
    }
}
