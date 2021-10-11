package byow.TileEngine;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;


/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {

    public static TETile avatars (char i) {
        Map<Character,TETile> al = new HashMap<>();
        al.put('Z', new TETile('✌', Color.white, Color.red, "you","C:/Users/y233l/Downloads/1.png"));
        al.put('X', new TETile('₪',Color.white, Color.red, "you","C:/Users/y233l/Downloads/2.png"));
        al.put('C', new TETile('￡',Color.white, Color.red, "you","C:/Users/y233l/Downloads/3.png"));
        al.put('V',new TETile('₡',Color.white, Color.red, "you","C:/Users/y233l/Downloads/4.png"));
        return al.get(i);
    }
    public static final TETile AVATAR = new TETile('₡',Color.white, Color.red, "you");
    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall");
    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black,
            "floor");
    public static final TETile NOTHING = new TETile(' ', Color.black, Color.black, "nothing");
    public static final TETile GRASS = new TETile('"', Color.green, Color.black, "grass");
    public static final TETile WATER = new TETile('≈', Color.blue, Color.cyan, "water");
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "flower");
    public static final TETile LOCKED_DOOR = new TETile('█', Color.orange, Color.black,
            "locked door","C:/Users/y233l/Downloads/stop.png");
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "unlocked door","C:/Users/y233l/Downloads/exit.png");
    public static final TETile SAND = new TETile('▒', Color.pink, Color.white, "sand");
    public static final TETile MOUNTAIN = new TETile('▲', Color.darkGray, Color.white, "mountain");
    public static final TETile TREE = new TETile('♠', Color.green, Color.white, "tree");
    public static final TETile ATTACKER = new TETile('₰', Color.DARK_GRAY, Color.white, "attacker","C:/Users/y233l/Downloads/Alien.png");

}




