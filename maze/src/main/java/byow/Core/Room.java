package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Room {

    private int width;
    private int height;
    Position rstart;
    private static final int MAX_SIZE = 16;

    public Room(int w, int h, Position p){
        width = w;
        height = h;
        rstart = p;
    }

    /* Include the walls. */
    public static void buildSingleRoom(TETile[][] world,Room room){
        int w = room.width;
        int h = room.height;
        Position s = room.rstart;

        if (s.getX() + w >= world.length -1) {
            w = world.length - s.getX();
        }
        if(s.getY() + h >= world[0].length - 1){
            h = world[0].length - s.getY();
        }
        addFourSidesWalls(s, w, h, world);
        for(int i = 1; i < h - 1; i++){
            Position pos = new Position(s.getX()+1,s.getY()+i);
            addRowFloor(pos, w - 2 ,world);
        }
    }

    private static void addRowFloor(Position pos, int length, TETile[][] world){
        for(int i = 0; i < length; i++){
            world[pos.getX()+i][pos.getY()] = Tileset.WATER;
        }
    }

    private static void addFourSidesWalls(Position pos, int w, int h,TETile[][] world){
        Position Lleft = pos;
        Position Uright = new Position(Lleft.getX() + w -1, Lleft.getY() + h -1);
        addBottomLeftWalls(Lleft,w,h,world);
        addUpperRightWalls(Uright,w,h,world);
    }
    private static void addBottomLeftWalls(Position pos, int w, int h, TETile[][] world){

        for(int i =0; i < w ; i++){
            world[pos.getX()+i][pos.getY()] = Tileset.TREE;
        }
        for(int j = 0; j < h ; j++){
            world[pos.getX()][pos.getY()+j] = Tileset.TREE;
        }

    }
    private static void addUpperRightWalls(Position pos, int w, int h, TETile[][] world){
        for(int i = 0; i < w-1 ; i++){
            world[pos.getX()- i][pos.getY()] = Tileset.TREE;
        }
        for(int j = 0; j < h-1 ; j++){
            world[pos.getX()][pos.getY()-j] = Tileset.TREE;
        }
    }

    public static List<Room> buildRandomRooms(TETile[][] world, Random rand) {
        int width = world.length;
        int height = world[0].length;

        List<BPS> regions = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        BPS root = new BPS(new Position(0, 0), width, height);
        regions.add(root);

        for (int i = 0; i < regions.size(); i++) {
            BPS each = regions.get(i);
            if (each.left == null && each.right == null) {
                if (each.width > MAX_SIZE || each.height > MAX_SIZE) {
                    if (each.canSplit(rand)) {
                        regions.add(each.left);
                        regions.add(each.right);
                    }
                }
            }
        }
        root.assignRoomArea(rand);
        for(BPS each: regions){
            if(each.room != null){
                buildSingleRoom(world,each.room);
                rooms.add(each.room);
            }
        }
        return rooms;
    }

    public int getWidth(){ return width;}

    public int getHeight(){ return height;}
}

