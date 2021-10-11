package byow.Core;

/** This is the main entry point for the program. This class simply parses
 *  the command line inputs, and lets the byow.Core.Engine class take over
 *  in either keyboard or input string mode.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Can only have one argument - the input string");
            System.exit(0);
        } else if (args.length == 1) {
            Engine engine = new Engine();
            engine.interactWithInputString(args[0]);
            System.out.println(engine.toString());
        } else {
            Engine engine = new Engine();
            engine.interactWithKeyboard();
        }
    }
}

/**
 * Instruction:
 *    a) If you wanna quit the game at the beginning of or during the game, press ' : ' then ' q ', otherwise,
 *       follow the interface instruction.
 *    b) Every step was recorded as single Character in a StringBuilder called "tape" to load the game next time.
 *    c) If you have escaped the world or you are attacked, then the game will not be saved -- Load (L) game
 *       will simply exit from the program.
 *    d) The interactWithInputString() can be used either in the load game section or by giving an argument to
 *       to the program.
 *    e) The code has been designed to give the default avatar icon and whole map world if the user did not choose
 *       their preference.
 *    f) Hit exit : Big Cong! Escaped the world;
 *       Hit stop : Wrong exit! Continue explore;
 *       Hit Alien: Being eaten. Game over;
 *       You can quit and restart the whole game whenever you want!
 */
