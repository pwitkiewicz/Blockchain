package app.singlethreaded;

import app.SerializationUtil;
import app.StringUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Blockchain implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Block> blockchain;
    private int blockCount = 0;
    private int zeroCount;
    private String filename;

    public Blockchain(int zeroCount, String filename) {
        blockchain = new ArrayList<>();
        this.zeroCount = zeroCount;
        this.filename = filename;
    }

    public Block getBlock(int id) {
        return blockchain.get(id-1);
    }

    public void addBlock() throws IOException {
        String match = "0";
        for (int i = 1; i < zeroCount; i++) {
            match += "0";
        }

        long startTime = System.nanoTime();
        long i = 0;

        if(zeroCount == 0) {
            Block temp = new Block(this, 0);
            this.blockchain.add(temp);

            blockCount++;
            SerializationUtil.serialize(this, filename);

            return;
        }

        while(true) {
            Block temp = new Block(this, i++);
            if(temp.getHash().substring(0, zeroCount).equals(match)) {

                long endTime = System.nanoTime();
                double genTime = (endTime - startTime) * 1e-9;
                temp.setGenTime(genTime);

                this.blockchain.add(temp);

                blockCount++;

                SerializationUtil.serialize(this, filename);
                break;
            }
        }
    }

    private String getHash(String toHash) {
        return StringUtil.applySha256(toHash);
    }

    public int getBlockCount() {
        return blockCount;
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

