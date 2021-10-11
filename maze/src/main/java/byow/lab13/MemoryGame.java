package byow.lab13;

import byow.Core.RandomUtils;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    int width;
    int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40,seed);
        game.startGame();

    }

    public MemoryGame(int width, int height,int s) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        rand = new Random(s);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        StringBuilder sb = new StringBuilder(n);
        for(int i = 0; i < n; i++){
            int index = rand.nextInt(CHARACTERS.length);
            sb.append(CHARACTERS[index]);
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.clear();

        Font head = new Font("Times New Roman", Font.BOLD,20);
        StdDraw.setFont(head);
        StdDraw.setPenColor(Color.blue);
        StdDraw.textLeft(1,height-1,"Round: " + round);
        StdDraw.line(0,height-2,width,height -2);
        StdDraw.textRight(width,height-1,ENCOURAGEMENT[round % ENCOURAGEMENT.length]);
        StdDraw.text(width / 2, height - 1,playerTurn? "Your Turn!" : "Please Watch!");

        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.MAGENTA);
        StdDraw.text((double) width / 2, (double) height / 2, s);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for(char c : letters.toCharArray()){
            String sc = Character.toString(c);
            drawFrame(sc);
            StdDraw.pause(500);
            drawFrame("");
            StdDraw.pause(100);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        drawFrame(" ");
        StringBuilder sb = new StringBuilder();
        int cnt = n;
        while(cnt > 0) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                sb.append(c);
                cnt -= 1;
                drawFrame(sb.toString());
            }
        }
        StdDraw.pause(500);
        return sb.toString();
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round = 1;
        gameOver = false;
        playerTurn = false;
        //TODO: Establish Engine loop
        while(!gameOver) {

            playerTurn = false;
            drawFrame("Round: " + round + " Good Luck!");
            StdDraw.pause(1000);

            String popped = generateRandomString(round);
            flashSequence(popped);
            StdDraw.pause(500);
            playerTurn = true;

            String typed = solicitNCharsInput(round);

            if(typed.equals(popped)){
                drawFrame("Correct! So cool!");
                StdDraw.pause(1000);
                round += 1;
            }else{
                gameOver = true;
                drawFrame("Game Over! You made it to round: " + round);
            }

        }


    }

}
