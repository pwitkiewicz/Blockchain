import java.util.Date;

public class Block {
    private final Blockchain parent;
    private final int id;
    private final long timeStamp = new Date().getTime();;
    private final String prevBlockHash;
    private final String hash;

    public Block(Blockchain parent, int id) {
        this.parent = parent;
        this.id = id+1;

        if(this.id == 1) {
            this.prevBlockHash = "0";
        } else {
            this.prevBlockHash = parent.getBlock(id - 1).getHash();
        }

        this.hash = StringUtil.applySha256(this.toHash());
    }

    public String toHash() {
        return "Block:\n" +
                "Id: " + id + "\n" +
                "Timestamp: " + timeStamp + "\n" +
                "Hash of the previous block:\n" +
                prevBlockHash + "\n";
    }

    String toValidate() {
        return "Block:\n" +
                "Id: " + id + "\n" +
                "Timestamp: " + timeStamp + "\n" +
                "Hash of the previous block:\n";
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return toHash() + "Hash of the block:\n" + hash;
    }
}
