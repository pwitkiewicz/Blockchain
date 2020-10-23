package app.multithreaded;

import app.SerializationUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedRunner {
    public static void run() {
        String filename = "blockchain.data";
        File blockchainDataFile = new File(filename);
        Blockchain blockchain = null;

        /*if(blockchainDataFile.exists() && blockchainDataFile.isFile()) {
            try {
                blockchain = (Blockchain) SerializationUtil.deserialize(filename);
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }*/

        //if(blockchain == null || !blockchain.validate()) {
            blockchain = new Blockchain(filename);
        //}

        int numberOfZeros = 1;
        ExecutorService executor = Executors.newFixedThreadPool(8);

        double secondsThreshold = 60;

        for (int i = 0; i < 10; i++) {
            List<Miner> miners = makeMinerList(blockchain, numberOfZeros);
            try {
                blockchain.addBlock(executor.invokeAny(miners));
                System.out.println(blockchain.getBlock(blockchain.getBlockCount()));

                if (blockchain.getBlock(blockchain.getBlockCount()).getGenTime() >= secondsThreshold) {
                    numberOfZeros -= 1;
                    System.out.printf("N was decreased to %s\n\n", numberOfZeros);
                } else {
                    numberOfZeros += 1;
                    System.out.printf("N was increased to %s\n\n", numberOfZeros);
                }

            } catch (InterruptedException | ExecutionException | IOException exc) {
                exc.printStackTrace();
            }
        }
        executor.shutdown();
    }

    public static List<Miner> makeMinerList(Blockchain currentBlockchain, int numberOfZeros) {
        List<Miner> minerList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Miner miner = new Miner(i, currentBlockchain, numberOfZeros);
            minerList.add(miner);
        }
        return minerList;
    }
}
