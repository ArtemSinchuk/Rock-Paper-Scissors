/*
 * created by Artem Sinchuk
 * 18.04.2024
 */

import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Welcome to rock, paper, scissors game!");

        while (true) {
            System.out.println("Enter your choice (rock, paper, scissors or quit):");
            String userInput = console.nextLine().toLowerCase();

            if (userInput.equals("quit")) {
                System.out.println("Thanks for playing!");
                break;
            }

            String[] choices = {"rock", "paper", "scissors"};
            int computerChoice = (int) (Math.random() * 3);
            String computerInput = choices[computerChoice];

            System.out.println("Computer chose: " + computerInput);

            if (userInput.equals(computerInput)) {
                System.out.println("It's a tie!");
            } else if ((userInput.equals("rock") && computerInput.equals("scissors"))
                    || (userInput.equals("paper") && computerInput.equals("rock"))
                    || (userInput.equals("scissors") && computerInput.equals("paper"))) {
                System.out.println("You win!");
            } else {
                System.out.println("Computer wins!");
            }
        }
    }
}