package byow.Core;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import static byow.Core.Engine.*;


public class Draw {

    public static void drawStart(){
        StdDraw.clear(Color.BLACK);

        Font head = new Font("Times New Roman", Font.BOLD,(int)(WIDTH*HEIGHT*0.000571*20));
        StdDraw.setFont(head);
        StdDraw.setPenColor(Color.PINK);
        StdDraw.textLeft(1,HEIGHT-1,"@Author: Freya");
        StdDraw.line(0,HEIGHT-2,WIDTH*0.2,HEIGHT-2);

        Font title = new Font("Times New Roman", Font.BOLD,(int)(WIDTH*HEIGHT*0.000571*50));
        StdDraw.setFont(title);
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.text(WIDTH * 0.5,HEIGHT * 0.6,"MY NEW WORLD");

        Font menu = new Font("Times New Roman", Font.BOLD,(int)(WIDTH*HEIGHT*0.000571*25));
        StdDraw.setFont(menu);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(WIDTH * 0.5,HEIGHT*0.45,"New Game (N)");
        StdDraw.text(WIDTH * 0.5,HEIGHT*0.40,"Load Game (L)");
        StdDraw.text(WIDTH * 0.5,HEIGHT*0.35,"Quit (:Q)");


        Font font = new Font("Monaco", Font.ITALIC, TILE_SIZE - 2);
        StdDraw.setFont(font);
        StdDraw.textRight(WIDTH -1,2,"Quit during the game please:");
        StdDraw.textRight(WIDTH -3,1,"Press : and Q. 😀");
        StdDraw.show();
    }
    public static void drawEnd(boolean gameOver,boolean died){
        StdDraw.clear(Color.BLACK);
        Font head = new Font("Times New Roman", Font.BOLD,(int)(WIDTH*HEIGHT*0.000571*40));
        StdDraw.setFont(head);
        StdDraw.setPenColor(Color.red);

        if(!gameOver){
            StdDraw.text(WIDTH * 0.5,HEIGHT * 0.6,"Opps! Wrong exit.");
            StdDraw.text(WIDTH * 0.5,HEIGHT * 0.45,"Please Continue exploring...");
            StdDraw.show();
            Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
            StdDraw.setFont(font);
            return;
        }
        if(died){
            StdDraw.text(WIDTH * 0.5,HEIGHT * 0.6,"Game Over! You were attacked!");
            StdDraw.show();
            Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
            StdDraw.setFont(font);
        }
        else{
            StdDraw.text(WIDTH * 0.5,HEIGHT * 0.6,"Big Cong! You've escaped!");
        }
        Font query = new Font("Times New Roman", Font.ITALIC,(int)(WIDTH*HEIGHT*0.000571*30));
        StdDraw.setFont(query);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(WIDTH * 0.5,HEIGHT * 0.45,"Start a new game? (n / q)");
        StdDraw.show();
    }

    public static void drawInterrupt(){
        StdDraw.clear(Color.BLACK);

        Font head = new Font("Times New Roman", Font.BOLD,(int)(WIDTH*HEIGHT*0.000571*50));
        StdDraw.setFont(head);
        StdDraw.setPenColor(Color.red);
        StdDraw.text(WIDTH * 0.5,HEIGHT * 0.6,"Sorry that you wanna quit.");

        Font query = new Font("Times New Roman", Font.ITALIC,(int)(WIDTH*HEIGHT*0.000571*30));
        StdDraw.setFont(query);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(WIDTH * 0.5,HEIGHT * 0.45,"Sure wanna quit? (n : New game / q : quit)");
        StdDraw.show();
    }

    public static void drawInputSeed(String s ){
        StdDraw.clear(Color.BLACK);
        Font Title = new Font("Monaco", Font.BOLD,(int)(WIDTH*HEIGHT*0.000571*50));
        StdDraw.setFont(Title);
        StdDraw.setPenColor(Color.orange);
        StdDraw.text(WIDTH * 0.5,HEIGHT*0.6,"Welcome To the New World! ");

        Font seed = new Font("Arial", Font.BOLD,(int)(WIDTH*HEIGHT*0.000571*25));
        StdDraw.setFont(seed);
        StdDraw.setPenColor(Color.yellow);
        StdDraw.text(WIDTH * 0.5,HEIGHT*0.4,"Please give me a seed : ");
        StdDraw.text(WIDTH * 0.5,HEIGHT*0.3,s);

        Font f = new Font("Arial", Font.ITALIC,(int)(WIDTH*HEIGHT*0.000571*15));
        StdDraw.setFont(f);
        StdDraw.setPenColor(Color.yellow);
        StdDraw.text(WIDTH * 0.5,HEIGHT*0.2,"Press S when you finish.");
        StdDraw.text(WIDTH * 0.5,HEIGHT*0.17,"Press any key for the default icon.");


        StdDraw.show();
        Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
        StdDraw.setFont(font);
    }

    public static void drawAskHardLevel(){
        StdDraw.clear(Color.white);
        Font Title = new Font("Monaco", Font.BOLD,(int)(WIDTH*HEIGHT*0.000571*30));
        StdDraw.setFont(Title);
        StdDraw.setPenColor(Color.RED);
        StdDraw.text(WIDTH * 0.5,HEIGHT*0.6,"Try harder level? Please press H for hard! ");
        StdDraw.text(WIDTH * 0.5,HEIGHT*0.5,"or press E for easy! ");

        Font explain = new Font("Monaco", Font.ITALIC,(int)(WIDTH*HEIGHT*0.000571*13));
        StdDraw.setFont(explain);
        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.text(WIDTH * 0.5,HEIGHT*0.25,"Easy Mode: You can peek the world before you explore the exit! ");
        StdDraw.text(WIDTH * 0.5,HEIGHT*0.22,"Hard Mode: Sorry! You cannot peek the world. 😀 ");

        Font font = new Font("Monaco", Font.ITALIC, TILE_SIZE - 2);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.textRight(WIDTH-1,2,"Helper: Press ? if");
        StdDraw.textRight(WIDTH-1,1,"you are stuck.");
        StdDraw.show();
    }

    public static void chooseAvatar(){
        Font ava = new Font("Arial", Font.ITALIC,(int)(WIDTH*HEIGHT*0.000571*20));
        StdDraw.setFont(ava);
        StdDraw.setPenColor(Color.white);
        StdDraw.textRight(WIDTH,HEIGHT-1,"Choose your avatar : ");

        Font ava1 = new Font("Arial", Font.ITALIC,(int)(WIDTH*HEIGHT*0.000571*17));
        StdDraw.setFont(ava1);
        StdDraw.text(WIDTH-6,HEIGHT-3,"Z: ");
        StdDraw.text(WIDTH-6,HEIGHT-5,"X: ");
        StdDraw.text(WIDTH-6,HEIGHT-7,"C: ");
        StdDraw.text(WIDTH-6,HEIGHT-9,"V: ");

        StdDraw.picture(WIDTH-4,HEIGHT-3,"C:/Users/y233l/Downloads/1.png");
        StdDraw.picture(WIDTH-4,HEIGHT-5,"C:/Users/y233l/Downloads/2.png");
        StdDraw.picture(WIDTH-4,HEIGHT-7,"C:/Users/y233l/Downloads/3.png");
        StdDraw.picture(WIDTH-4,HEIGHT-9,"C:/Users/y233l/Downloads/4.png");

        StdDraw.show();
        Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
        StdDraw.setFont(font);
    }
    public static void chooseMap(){
        Font m = new Font("Arial", Font.ITALIC,(int)(WIDTH*HEIGHT*0.000571*20));
        StdDraw.setFont(m);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.textRight(WIDTH-1,HEIGHT-1,"Choose map: ");

        Font ava1 = new Font("Arial", Font.ITALIC,(int)(WIDTH*HEIGHT*0.000571*17));
        StdDraw.setFont(ava1);
        StdDraw.text(WIDTH-6,HEIGHT-3,"1: ");
        StdDraw.text(WIDTH-6,HEIGHT-5,"2: ");
        StdDraw.textRight(WIDTH-1,HEIGHT - 7,"Press any key for");
        StdDraw.textRight(WIDTH-1,HEIGHT - 8,"the default map.");

        StdDraw.picture(WIDTH-4,HEIGHT-3,"C:/Users/y233l/Downloads/XY.png",45.0);
        StdDraw.filledRectangle(WIDTH-4,HEIGHT-5,1,0.5);

        StdDraw.show();
        Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
        StdDraw.setFont(font);
    }
    public static void askForHelp(){

        Font head = new Font("Times New Roman", Font.BOLD,(int)(WIDTH*HEIGHT*0.000571*30));
        StdDraw.setFont(head);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(WIDTH * 0.5, HEIGHT - 5, "Aha! Wanna peek the world? (P: peek / B: back)");
        StdDraw.show();
        Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
        StdDraw.setFont(font);
    }


}
