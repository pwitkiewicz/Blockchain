package app;

import app.multithreaded.MultiThreadedRunner;
import app.singlethreaded.SingleThreadedRunner;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // run multi threaded version on default
        if(args.length == 0 || args[0].equals("-M")) {
            MultiThreadedRunner.run();
        }
        else if(args[0].equals("-S")) {
            SingleThreadedRunner.run();
        }
        else {
            System.out.println("Wrong parameter (use -S for single-threaded version or -M for multithreaded. Terminating.");
        }
    }
}
