import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private final List<Block> blockchain;
    private int blockCount = 0;

    public Blockchain() {
        this.blockchain = new ArrayList<>(100);
    }

    public Block getBlock(int id) {
        return this.blockchain.get(id);
    }

    public void addBlock() {
        this.blockchain.add(new Block(this, blockCount));
        blockCount++;
    }

    private String getHash(String toHash) {
        return StringUtil.applySha256(toHash);
    }

    public boolean validate() {
        for (int i = 1; i < blockchain.size(); i++) {
            String prevBlockHash = blockchain.get(i-1).getHash();
            String currBlockHash = blockchain.get(i).getHash();

            String currBlockHashToVerify = blockchain.get(i).toValidate() + prevBlockHash + "\n";

            if(!currBlockHash.equals(getHash(currBlockHashToVerify))) {
                return false;
            }
        }
        return true;
    }
}

