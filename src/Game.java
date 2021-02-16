// Created by Albert Kowalski

import java.io.File;
import java.util.Scanner;

public class Game {
    private static int guessLimit = 10;
    String title;

    public void getMovie() throws Exception {
        File file = new File("src/movies.txt");
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
        System.out.println(title);
    }
    public Game() throws Exception {
        getMovie();
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




    public static void main(String[] args) throws Exception {
        Game game = new Game();
        System.out.println((game.title));
        System.out.println(underscore(game.title));

    }
}
