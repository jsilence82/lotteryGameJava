import java.util.*;
import static java.util.stream.Collectors.toList;


public class lotteryGame {
    //Get selected size number without duplicate
    public static List<Integer> getLotteryNumbers(int size, int min, int max) {
        ArrayList numbers = new ArrayList();
        Random random = new Random();
        while (numbers.size() < size) {
            //Get Random numbers between range
            int randomNumber = random.nextInt((max - min) + 1) + min;
            //Check for duplicate values
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
                }
            }
        Integer[] results = (Integer[]) numbers.toArray(new Integer[0]);
        return List.of(results);
    }

    public static List<Integer> getUserNumbers(){
        List<Integer> userNumbers = new ArrayList<>(Collections.emptyList());
        int i = 1;
        System.out.println("Enter your lucky numbers (1 to 50)");
        do {
            Scanner input = null;
            try {
                System.out.printf("Pick number %s: ", i);
                input = new Scanner(System.in);
                int a = input.nextInt();
                if (a < 0 || a > 50) {
                    System.out.println("Your number should be between 1 and 50. Try again.");
                    continue;
                }
                userNumbers.add(a);
                i++;
            } catch (InputMismatchException type_error) {
                System.out.println("That's not a number. Please enter a number.");
                input.next();
            }
        } while (i <= 7);

        System.out.println(userNumbers); //For testing only

        return userNumbers;
    }
    public static List<Integer> checkLottoNumbers(List<Integer> userNumbers, List<Integer> lotteryNumbers){
        return userNumbers.stream().filter(lotteryNumbers::contains).collect(toList());
    }

    public static String lottoWinnings(int rightAnswers){
        if (rightAnswers == 7){
            return "You won $1,000,000!";
        } else if (rightAnswers == 6) {
            return "You won $50,000!";
        } else if (rightAnswers == 5) {
            return "You won $10,000!";
        } else if (rightAnswers == 4) {
            return "You won $5,000!";
        } else if (rightAnswers == 3) {
            return "You won $1,000!";
        } else if (rightAnswers == 2) {
            return "You won $500!";
        } else if (rightAnswers == 1) {
            return "You won $100!";
        }else
            return "Sorry you didn't win. Try again";
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the lottery game");
        System.out.println("Let's Go!");
        boolean gameLoop = true;
        while (gameLoop) {
            try {

                List<Integer> userNumbers = getUserNumbers();
                //Select Seven random number without duplicate between 0 and 50
                List<Integer> lotteryNumbers = getLotteryNumbers(7, 1, 50);

                System.out.println(lotteryNumbers + " "); //For testing


                List<Integer> checkNumbers = checkLottoNumbers(userNumbers, lotteryNumbers);

                System.out.println(checkNumbers); //For testing

                int rightAnswers = checkNumbers.size();

                String payout = lottoWinnings(rightAnswers);
                System.out.println(payout);
            }
            finally {
                boolean playAgain = true;
                while (playAgain) {
                    System.out.print("Would you like to play again? (Y/N) ");
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
