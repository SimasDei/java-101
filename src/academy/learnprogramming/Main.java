package academy.learnprogramming;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Class used to scan/read user input
        var scanner = new Scanner(System.in);

        System.out.println(Doctor.intro());
        var userInput = "";

        while (!userInput.equalsIgnoreCase("quit")) {
            userInput = scanner.nextLine();
            String response = Doctor.response(userInput);
            System.out.println(response);
        }

        scanner.close();
    }
}
