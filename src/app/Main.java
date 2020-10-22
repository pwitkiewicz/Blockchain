package app;

import app.singlethreaded.SingleThreadedRunner;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Would you like to run single or multi threaded version of the program (S/M)? :");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        scanner.close();

        if(answer.equals("S")) {
            SingleThreadedRunner.run();
        }
        else if(answer.equals("M")) {

        }
        else {
            System.out.println("Wrong parameter, terminating.");
        }
    }
}
