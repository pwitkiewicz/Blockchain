public class Main {
    public static void main(String[] args) {

        Blockchain blockchain = new Blockchain();
        for (int i = 0; i < 10; i++) {
            blockchain.addBlock();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(blockchain.getBlock(i)+"\n");
        }
    }
}
