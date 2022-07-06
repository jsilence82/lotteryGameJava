// A lottery game. It was originally written in Python. Written in Java as a learning exercise.

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class lotteryGame {
    // Generates 7 random lotto numbers without duplicates. Prints "Drawing numbers" with time delay.
    // Returns the winning lotto numbers as integer list.
    public static List<Integer> getLotteryNumbers(int size, int min, int max) {
        List<Integer> numbers = new ArrayList<>(Collections.emptyList());
        Random random = new Random();
        while (numbers.size() < size) {
            //Get Random numbers between range
            int randomNumber = random.nextInt((max - min) + 1) + min;
            //Check for duplicate values
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
                }
            }
        Integer[] results = numbers.toArray(new Integer[0]);

        System.out.println("\nHere comes the winning lotto numbers....");
        for (Integer number : numbers) {
            try {
                Thread.sleep(1000);
                System.out.println("Drawing the number... " + number);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        return List.of(results);
    }

    public static List<Integer> getUserNumbers(){
        // Method to get the user numbers. With exceptions should user enter an invalid value or had already picked the
        // number. Returns userNumbers as an integer list.
        List<Integer> userNumbers = new ArrayList<>(Collections.emptyList());
        int i = 1;
        System.out.println("\nEnter your lucky numbers (1 to 50)");
        do {
            Scanner input = null;
            try {
                System.out.printf("Pick %s: ", i);
                input = new Scanner(System.in);
                int a = input.nextInt();
                if (a < 1 || a > 50) {
                    System.out.println("Your number should be between 1 and 50. Try again.");
                    continue;
                }
                if (userNumbers.contains(a)){
                    System.out.println("You already picked that number. Try again.");
                    continue;
                }
                userNumbers.add(a);
                i++;
            } catch (InputMismatchException type_error) {
                System.out.println("That's not a number. Please enter a number.");
                assert input != null;
                input.next();
            }
        } while (i <= 7);
        return userNumbers;
    }

    public static List<Integer> checkLottoNumbers(List<Integer> userNumbers, List<Integer> lotteryNumbers){
        // Compares user entered numbers against the random numbers
        return userNumbers.stream().filter(lotteryNumbers::contains).collect(toList());
    }

    public static String lottoWinnings(int rightAnswers){
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

    public static void main(String[] args) {
        // Generate ASCII art
        BufferedImage image = new BufferedImage(144, 32, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("Dialog", Font.PLAIN, 24));
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("LOTTERY", 6, 24);

        for (int y = 0; y < 32; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < 144; x++)
                sb.append(image.getRGB(x, y) == -16777216 ? " " : image.getRGB(x, y) == -1 ? "#" : "*");
            if (sb.toString().trim().isEmpty()) continue;
            System.out.println(sb);
        }

        System.out.println("\nWelcome to the lottery game");
        System.out.println("Good Luck!");
        boolean gameLoop = true;
        while (gameLoop) {
            try {
                // Setting Lists and variables-
                List<Integer> userNumbers = getUserNumbers();
                List<Integer> lotteryNumbers = getLotteryNumbers(7, 1, 50);
                List<Integer> checkNumbers = checkLottoNumbers(userNumbers, lotteryNumbers);
                int rightAnswers = checkNumbers.size();
                String payout = lottoWinnings(rightAnswers);

                // End game printout
                System.out.println("\nThe lotto numbers: " + lotteryNumbers);
                System.out.println("Your numbers: " + userNumbers);
                System.out.println("\nYou hit the numbers: "+ checkNumbers);
                System.out.println("\nYou guessed " + rightAnswers + " correctly.");
                System.out.println(payout);
            }
            finally {
                // While loop to ask if user would like to keep playing.
                boolean playAgain = true;
                while (playAgain) {
                    System.out.print("\nWould you like to play again? (Y/N) ");
                    Scanner input = new Scanner(System.in);
                    String replay = input.nextLine();
                    if (replay.equals("y") || replay.equals("Y")){
                        playAgain = false;
                    } else if (replay.equals("N") || replay.equals("n")) {
                        playAgain = false;
                        gameLoop = false;
                    } else {
                        System.out.println("Please answer Y or N.");
                    }
                }
            }
        }
    }
}
