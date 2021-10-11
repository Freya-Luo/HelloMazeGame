package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 50;
    public static final int HEIGHT = 35;
    public static final int TILE_SIZE = 16;
    private Map<Character, int[]> move = new HashMap<>();

    public TETile[][] world = new TETile[WIDTH][HEIGHT];
    private StringBuilder tape = new StringBuilder();
    private WorldGenerator wg = new WorldGenerator();
    private boolean gameOver;
    private boolean died;
    private Position avatar;
    private char map;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {

        InputSource source = new KeyboardInputSource();
        ter.initialize(WIDTH, HEIGHT);
        gameOver = false;
        died = false;
        setmove();
        Draw.drawStart();

        while (!gameOver) {
            if (tape.length() > 1) {
                drawHelpInfo(new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY()));
            }
            if (source.hasNextKey()) {
                char key = source.getNextKey();
                if (key == 'N' || key == 'L' || key == ':') {
                    startOption(source, key);
                } /* Ask if the users want to peek the world when they get stuck. */
                else if(key == '?'&& (map == '1'|| map == '2')) {
                    Draw.askForHelp();
                    char help = source.getNextKey();
                    if(help == 'P') {
                        ter.renderFrame(world);
                    }else if(help == 'B'){
                        switch (map){
                            case '1':
                                ter.renderVisibleXY(world,avatar);
                                break;
                            case '2':
                                ter.renderVisibleSquare(world,avatar);
                                break;
                            default:
                                ter.renderFrame(world);
                                break;
                        }
                    }
                }
                else{
                    avatarAction(key, move);
                }
            }/** Quit/Be attacked in/Finish/  the game,
               * then we ask the users to restart or exit the program.*/
            if (gameOver) {
                if (source.hasNextKey()) {
                    char c = source.getNextKey();
                    if (c == 'N') {
                        tape = new StringBuilder();
                        startOption(source, c);
                        gameOver = false;
                    }
                    if (c == 'Q') {
                        saveGame(tape.toString());
                        System.exit(0);
                    }
                }
            }
        }
    }

    private void drawHelpInfo(Position mouse) {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textRight(WIDTH-1, 1, world[mouse.getX()][mouse.getY()].description());
        StdDraw.picture(WIDTH-1,HEIGHT-1,"C:/Users/y233l/Downloads/help.png");
        StdDraw.show();
    }

    private void startOption(InputSource input, char key) {

        switch (key) {
            case 'N':
                long seed = getSeed(input);
                Random random = new Random(seed);
                tape.append('N');
                tape.append(seed);
                tape.append('S');
                if(input.getClass().equals(StringInputSource.class)){
                    avatar = wg.BuildWorld(world, random, input);
                    ter.renderFrame(world);
                }
                else if(input.getClass().equals(KeyboardInputSource.class)) {
                    Draw.chooseAvatar();
                    avatar = wg.BuildWorld(world, random, input);
                    tape.append(wg.num);
                    Draw.drawAskHardLevel();
                    if (input.hasNextKey()) {
                        char level = input.getNextKey();
                        Draw.chooseMap();
                        map = input.getNextKey();
                        tape.append(level);
                        tape.append(map);
                        if (level == 'H') {
                            switch (map) {
                                case '1':
                                    ter.renderVisibleXY(world, avatar);
                                    break;
                                case '2':
                                    ter.renderVisibleSquare(world, avatar);
                                    break;
                                default:
                                    ter.renderFrame(world);
                            }
                        } else if (level == 'E') {
                            ter.renderFrame(world);
                        }
                    }
                }
                break;
            case 'L':
                String lastTape = loadGame();
                if (lastTape.equals("") ) {
                    System.out.println("No saved file can be found! Please restart the program. ");
                    System.exit(0);
                }
                else {
                    world = interactWithInputString(lastTape);
                }
                break;
            case ':': /* Objectively quit.*/
                if (input.getNextKey() == 'Q') {
                    Draw.drawInterrupt();
                    gameOver = true;
                }
        }
    }

    private void avatarAction(char key, Map<Character, int[]> directions) {

        tape.append(key);
        if (directions.containsKey(key)) {
            int[] direction = directions.get(key);
            int aX = avatar.getX() + direction[0];
            int aY = avatar.getY() + direction[1];
            if (world[aX][aY] == Tileset.WATER) {
                if(wg.setAvatar() != null) {
                    world[aX][aY] = wg.setAvatar();
                }
                else{
                    world[aX][aY] = Tileset.AVATAR;
                }
                world[avatar.getX()][avatar.getY()] = Tileset.WATER;
                avatar = new Position(aX, aY);
                if(map == '1') {
                    ter.renderVisibleXY(world, avatar);
                }else if(map == '2'){
                    ter.renderVisibleSquare(world,avatar);
                }else{ //This is for the StringInput / default whole map since no map structure has been chosen.
                    ter.renderFrame(world);
                }
            } /** Successfully finish the game.*/
            if (world[aX][aY] == Tileset.UNLOCKED_DOOR) {
                gameOver = true;
                tape = new StringBuilder();
                Draw.drawEnd(gameOver,died);
            }
            if (world[aX][aY] == Tileset.LOCKED_DOOR) {
                Draw.drawEnd(gameOver,died);
            } /** Being attacked by alien.*/
            if (world[aX][aY] == Tileset.ATTACKER) {
                died = true;
                gameOver = true;
                tape = new StringBuilder();
                Draw.drawEnd(gameOver,died);
            }
        }
    }

    private void setmove() {
        move.put('W', new int[]{0, 1});
        move.put('S', new int[]{0, -1});
        move.put('D', new int[]{1, 0});
        move.put('A', new int[]{-1, 0});
    }

    private static void saveGame(String tape) {
        File f = new File("./save_data");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(tape);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }

    }

    private static String loadGame() {
        File f = new File("./save_data");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                return (String) os.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }
        return "";
    }

    public TETile[][] interactWithInputString(String input) {

        InputSource inputSource = new StringInputSource(input);
        ter.initialize(WIDTH, HEIGHT);
        setmove();

        while (inputSource.hasNextKey()) {
            char key = inputSource.getNextKey();
            if (key == 'N') {
                startOption(inputSource, key);
            } else if (!Character.isDigit(key)) {
                avatarAction(key, move);
            }
        }
        TETile[][] finalWorldFrame = world;
        return finalWorldFrame;
    }

    private long getSeed(InputSource input) {
        if (input.getClass().equals(KeyboardInputSource.class)) {
            Draw.drawInputSeed(" ");
        }

        long seed = 0L;
        StringBuilder sb = new StringBuilder();
        while (input.hasNextKey()) {
            char getChar = input.getNextKey();
            if (getChar != 'S' && Character.isDigit(getChar)) {
                seed = seed * 10 + Character.getNumericValue(getChar);
                if (input.getClass().equals(KeyboardInputSource.class)) {
                    sb.append(getChar);
                    Draw.drawInputSeed(sb.toString());
                }
            } else break;

        }
        return seed;
    }
}
