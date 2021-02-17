// Created by Albert Kowalski

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    private final int guessLimit = 10;
    private String title;
    private String hiddenTitle;
    private int trial = 0;
    private String guessedChars = "";
    private boolean winner = false;
    private boolean newgame = true;


    public void getMovie()  {
        File file = new File("src/movies.txt");
        try {
            Scanner scanner = new Scanner(file);
            int lines = 0;
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines += 1;
            }
            int movieIndex = (int) (Math.random() * lines) +1;
            scanner = new Scanner(file);
            for (int i=0; i < movieIndex; i++) {
                title = scanner.nextLine();
            }

        } catch(FileNotFoundException exception) {
            System.out.println("Cannot find that file");
            title = "";
        }

    }
    public Game() {
        getMovie();
        hiddenTitle = underscore(title);
        System.out.println("Your hidden title is: " + hiddenTitle);
        play();
    }



    public static String underscore(String movie){
        for (int i=0; i < movie.length(); i++){
            if (movie.charAt(i)==' ' | movie.charAt(i)=='_') {
                ;
            }
            else {
                movie = movie.replace(movie.charAt(i),'_');
            }
        }
    return movie;
    }

    public void guess() {
        if(guessedChars != "") {
            System.out.println("Letters already picked:" + guessedChars);
        }
        System.out.println("You can make " + (guessLimit - trial) + " errors. Pick a letter:");
        Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine();
        while (guessedChars.contains(letter)) {
            System.out.println("You already picked this letter, please pick another!");
            letter = scanner.nextLine();
        }
        guessedChars += (" " + letter);
        char[] hiddenTitleArray = hiddenTitle.toCharArray();
        if (title.contains(letter)) {
            System.out.println(letter + " is in title!");
            for (int i = 0; i < title.length(); i++) {
                if (title.charAt(i) == letter.charAt(0)) {
                    hiddenTitleArray[i] = letter.charAt(0);
                }
            }

        }
        else {
            System.out.println("There is no " + letter + " in title.");
            trial += 1;
        }
        hiddenTitle = new String(hiddenTitleArray);
        System.out.println(hiddenTitle);
        if (hiddenTitle.equals(title)) {
            winner = true;
        }
    }

    public void play() {
        while (trial <= guessLimit) {
            guess();
            if (winner) {
                break;
            }
        }
        if (winner) {
            System.out.println("Congratulations. You won! Do you want to play again? y/n");
        }
        else {
            System.out.println("You Lost. Title of movie was: " + title + ". Do you want to play again? y/n");
        }
        Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine();

        while (newgame) {
            if (letter.contains("y") | letter.contains("Y")) {
                Game game = new Game();
            }
            else if (letter.contains("n") | letter.contains("N")) {
                newgame = false;
            }
            else {
                System.out.println("Do you want to play again? y/n");
                letter = scanner.nextLine();
            }
        }
    }


    public static void main(String[] args) {
        Game game = new Game();

    }
}
