package ss8_refactoring;

public class TestTennisGame {
    public static void main(String[] args) {
        TennisGame tennisGame = new TennisGame("Gnourt", "Truong");

        tennisGame.wonPoint("Gnourt");
        tennisGame.wonPoint("Truong");
        tennisGame.wonPoint("Gnourt");
        tennisGame.wonPoint("Gnourt");


        System.out.println("Current score: " + tennisGame.getScore());

        tennisGame.wonPoint("Truong");
        tennisGame.wonPoint("Truong");
        System.out.println("Current score: " + tennisGame.getScore());

        tennisGame.wonPoint("Gnourt");
        System.out.println("Current score: " + tennisGame.getScore());

        tennisGame.wonPoint("Gnourt");
        System.out.println("Current score: " + tennisGame.getScore());

    }
}
