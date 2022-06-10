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
        System.out.println("Enter your picks (1 to 50)");
        do {
            try {
                System.out.printf("Pick number %s: ", i);
                Scanner input = new Scanner(System.in);
                int a = input.nextInt();
                if (a < 0 || a > 50) {
                    System.out.println("Your number should be between 1 and 50. Try again.");
                    continue;
                }
                userNumbers.add(a);
                i++;
            } catch (Exception e) {
                throw new RuntimeException("Please enter a number.");
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
}
