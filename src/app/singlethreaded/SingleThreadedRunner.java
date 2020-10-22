package app.singlethreaded;

import app.SerializationUtil;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SingleThreadedRunner {
    public static void run() {
        System.out.println("Enter how many zeros the hash must start with: ");

        Scanner scanner = new Scanner(System.in);
        int zeroCount = scanner.nextInt();
        scanner.close();

        String filename = "blockchain" + zeroCount + ".data";
        File blockchainDataFile = new File(filename);
        Blockchain blockchain = null;

        if(blockchainDataFile.exists() && blockchainDataFile.isFile()) {
            try {
                blockchain = (Blockchain) SerializationUtil.deserialize(filename);
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }

        if(blockchain == null || !blockchain.validate()) {
            blockchain = new Blockchain(zeroCount, filename);
        }


        for (int i = 0; i < 5; i++) {
            try {
                blockchain.addBlock();
            } catch (IOException e) {
                System.out.println("Error saving block! :(");
                e.printStackTrace();
            }
        }

        for (int i = 0; i < blockchain.getBlockCount(); i++) {
            System.out.println(blockchain.getBlock(i)+"\n");
        }


    }
}
