package Lottery;

public class StartGame {

    public static void main(String[] args) {
        new AsciiArt();
        System.out.println("\nWelcome to the lottery game");
        System.out.println("Good Luck!");
        new GameLoop();
    }
}
