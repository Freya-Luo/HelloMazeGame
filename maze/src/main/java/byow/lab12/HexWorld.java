package byow.lab12;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;


/**
 * Draws a world consisting of hexagonal regions
 * This gives the graph with the arbitrary colnum and max hexagons in the centre column.
 * The trick here is I focused on the centre column and extends it to right and left. But what  may
 * mess it up is the 2 arrays. Starting from the leftmost to the rightmost, where the 2 sides should be
 * considered separately.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 35;
    private static final Random RANDOM = new Random(1007);

    public static int WidthCalc(int s, int row){
        if(row >= 2 * s){
            throw new IllegalArgumentException();
        }
        int width = row;
        if(row >= s){
            width = 2 * s - 1- row;
        }
        return s + 2 * width;
    }

    public static int xOffSet(int s, int row){
        if(row >= 2 * s){
            throw new IllegalArgumentException();
        }
        int start = row;
        if(row >= s){
            start = 2 * s - 1- row;
        }
        return -start;
    }

    private static class Position{
        private int x;
        private int y;

        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
    }
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.FLOOR;
            case 3:
                return Tileset.TREE;
            case 4:
                return Tileset.GRASS;
            default:
                return Tileset.NOTHING;
        }
    }
    /**
     * Pin start points in each column -- low left point.
     * */
    private static Position[] PinStartsInEachCol(int num, Position p, int s){
        Position[] yStart = new Position[num];
        for(int i = 0; i < num; i++){
            yStart[i] = new Position(p.getX(),p.getY() + 2 * s * i);
        }
        return yStart;
    }
    /**
     * @Params i: the ith column away form the centre column.
     *          p: the low left point in the centre column.
     *  @Return the ith low left start point.
     *  What defined below is the right side, similar to the left side.
     *  */
    private static Position PinLeftStart(Position p, int s, int i){
        int leftX = p.getX() - i *(2 * s - 1);
        int leftY = p.getY() + i * s;
        return new Position(leftX,leftY);
    }

    private static Position PinRightStart(Position p, int s, int i){
        int rightX = p.getX() + i * (2 * s - 1);
        int rightY = p.getY() + i * s;
        return new Position(rightX,rightY);
    }

    public static void addHexgaon(TETile[][] tiles, Position p, int s){
        TETile t = randomTile();
        for(int i = 0; i < 2 * s ;i += 1){
            int xstart = xOffSet(s,i) + p.getX();
            int rowWidth = WidthCalc(s,i);
            for(int j = xstart; j < xstart + rowWidth; j += 1){
                tiles[j][i+p.getY()] = t;
            }
        }
    }
    /**
     * Return an array of start points in each column.
     * */
    private static Position[] PinStarts(Position p, int s, int colnum){
        Position[] eachcol = new Position[colnum];
        for(int i = 0; i < colnum; i++) {
            if (i < colnum / 2) {
                eachcol[i] = PinLeftStart(p, s, colnum / 2 - i);
            } else if (i > colnum / 2) {
                eachcol[i] = PinRightStart(p, s, i - colnum / 2);
            } else eachcol[colnum / 2] = p;
        }
        return eachcol;
    }
    /**
     * Return an array of the corresponding size of each column.
     * */
    private static int PinSize(int max, int i, int colnum){
        int size;
        if(i < colnum /2) size = max - colnum/2 + i;
        else if(i > colnum /2) size = max - (i - colnum/2);
        else size = max;
        return size;
    }

    public static void Tesselation(TETile[][] tiles, Position p, int s, int colnum, int max){
        Position[] starts = PinStarts(p,s,colnum);
        for(int i = 0; i < starts.length; i++) {
            int size = PinSize(max, i, colnum);
            Position start = starts[i];
            for (Position each : PinStartsInEachCol(size, start, s)) {
                addHexgaon(tiles, each, s);
            }
        }
    }

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Position p = new Position(24,3);
        HexWorld.Tesselation(world,p,2,5,7);
        ter.renderFrame(world);
    }

}
