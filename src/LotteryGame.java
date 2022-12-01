import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class LotteryGame {

    private static List<Integer> getLotteryNumbers() {
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
                System.out.println("Drawing the number... " + number);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        return numbers;
    }

    private static List<Integer> getUserNumbers(){

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
                if (userNumbers.contains(a)){
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
        return userNumbers;
    }

    private static List<Integer> checkLottoNumbers(List<Integer> userNumbers, List<Integer> lotteryNumbers){
        // Compares user entered numbers against the random numbers
        return userNumbers.stream().filter(lotteryNumbers::contains).collect(toList());
    }

    private static String lottoWinnings(int rightAnswers){
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
    static void gameLoop(){
        while (true) {
            try {
                List<Integer> userNumbers = getUserNumbers();
                List<Integer> lotteryNumbers = getLotteryNumbers();
                List<Integer> checkNumbers = checkLottoNumbers(userNumbers, lotteryNumbers);
                int rightAnswers = checkNumbers.size();
                String payout = lottoWinnings(rightAnswers);

                System.out.println("\nThe lotto numbers: " + lotteryNumbers);
                System.out.println("Your numbers: " + userNumbers);
                System.out.println("\nYou hit the numbers: "+ checkNumbers);
                System.out.println("\nYou guessed " + rightAnswers + " correctly.");
                System.out.println(payout);
            } catch(Exception e){
                System.out.println("Oops! Something went wrong.");
            }
            finally {
                boolean replayLoop = true;
                while (replayLoop) {
                    System.out.print("\nWould you like to play again? (Y/N) ");
                    Scanner input = new Scanner(System.in);
                    String replay = input.nextLine();
                    if (replay.equalsIgnoreCase("Y")){
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
        gameLoop();

    }
}
