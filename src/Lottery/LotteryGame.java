package Lottery;

import java.util.List;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class LotteryGame {

    public List<Integer> getLotteryNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        while (numbers.size() < 7) {   //Get Random numbers between range
            int randomNumber = random.nextInt((50 - 1) + 1) + 1;   //Check for duplicate values
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }

        System.out.println("\nHere comes the winning lotto numbers....");
        for (Integer number : numbers) {
            try {
                Thread.sleep(1000);
                System.out.println("Drawing the number ... " + number);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        Collections.sort(numbers);
        return numbers;
    }

    public List<Integer> getUserNumbers() {

        List<Integer> userNumbers = new ArrayList<>();
        int pickNumber = 1;
        System.out.println("\nEnter your lucky numbers (1 to 50)");
        do {
            Scanner input = null;
            try {
                System.out.printf("Pick %s: ", pickNumber);
                input = new Scanner(System.in);
                int a = input.nextInt();
                if (a < 1 || a > 50) {
                    System.out.println("Your number should be between 1 and 50. Try again.");
                    continue;
                }
                if (userNumbers.contains(a)) {
                    System.out.println("You already picked that number. Try again.");
                    continue;
                }
                userNumbers.add(a);
                pickNumber++;
            } catch (InputMismatchException type_error) {
                System.out.println("That's not a number. Please enter a number.");
                assert input != null;
                input.next();
            }
        } while (pickNumber <= 7);
        Collections.sort(userNumbers);
        return userNumbers;
    }

    public List<Integer> checkLottoNumbers(List<Integer> userNumbers, List<Integer> lotteryNumbers) {
        // Compares user entered numbers against the random numbers
        return userNumbers.stream().filter(lotteryNumbers::contains).collect(toList());
    }

    public String lottoWinnings(int rightAnswers) {
        return switch (rightAnswers) {
            case 1 -> "You won $100!";
            case 2 -> "You won $500!";
            case 3 -> "You won $1,000!";
            case 4 -> "You won $5,000!";
            case 5 -> "You won $10,000!";
            case 6 -> "You won $50,000!";
            case 7 -> "You won $1,000,000!";
            default -> "Sorry you didn't win. Try again";
        };
    }
}
