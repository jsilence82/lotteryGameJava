package Lottery;

import java.util.List;
import java.util.Scanner;

public class GameLoop {

    public GameLoop() {
        while (true) {
            try {
                LotteryGame game = new LotteryGame();
                List<Integer> userNumbers = game.getUserNumbers();
                List<Integer> lotteryNumbers = game.getLotteryNumbers();
                List<Integer> checkNumbers = game.checkLottoNumbers(userNumbers, lotteryNumbers);
                int rightAnswers = checkNumbers.size();
                String payout = game.lottoWinnings(rightAnswers);

                System.out.println("\nThe lotto numbers: " + numbersToString(lotteryNumbers));
                System.out.println("Your numbers: " + numbersToString(userNumbers));
                System.out.println("\nYou hit the numbers: " + numbersToString(checkNumbers));
                System.out.println("\nYou guessed " + rightAnswers + " correctly.");
                System.out.println(payout);
            } catch (Exception e) {
                System.out.println("Oops! Something went wrong.");
            } finally {
                boolean replayLoop = true;
                while (replayLoop) {
                    System.out.print("\nWould you like to play again? (Y/N) ");
                    Scanner input = new Scanner(System.in);
                    String replay = input.nextLine();
                    if (replay.equalsIgnoreCase("Y")) {
                        replayLoop = false;
                    } else if (replay.equalsIgnoreCase("N")) {
                        System.exit(0);
                    } else {
                        System.out.println("Please answer Y or N.");
                    }
                }
            }
        }
    }

    private String numbersToString(List<Integer> toBeFormatted) {
        return toBeFormatted.toString().substring(1, toBeFormatted.toString().length() - 1);
    }
}
