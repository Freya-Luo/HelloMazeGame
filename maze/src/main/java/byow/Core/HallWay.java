package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class HallWay {

    private int width;
    private int height;
    Position hstart;

    public HallWay(int w, int h, Position p){
        width = w;
        height = h;
        hstart = p;
    }

    public static void connectByHallWays(TETile[][] world, List<Room> rooms, Random rand) {

        Queue<Room> connected = new LinkedList<>(rooms);
        while (connected.size() > 1) {
            Room room1 = connected.poll();
            Room room2 = connected.peek();

            Position A = pinRandHallWayEnd(room1,rand);
            Position B = pinRandHallWayEnd(room2, rand);

            int diffw = A.getX() - B.getX();
            int diffh = A.getY() - B.getY();

            Position turn = getTurnPoint(A,B,diffh);
            addLHallWays(A,B,turn,diffh,world);

            completeTurnWalls(turn,world);
        }
    }
    private static Position pinRandHallWayEnd(Room r, Random rand){

        int xlbound = r.rstart.getX() +1;
        int xrbound = r.rstart.getX()+ r.getWidth() - 1;
        int ybbound = r.rstart.getY() +1;
        int yubound = r.rstart.getY()+ r.getHeight() - 1;

        int xrand = (xlbound == xrbound) ? xlbound : RandomUtils.uniform(rand,xlbound,xrbound);
        int yrand = (ybbound == yubound ) ? ybbound : RandomUtils.uniform(rand,ybbound,yubound);
        return new Position(xrand,yrand);
    }

    private static Position getTurnPoint(Position A, Position B,
                               int diffInHeight){
        int turnY = diffInHeight > 0 ? B.getY() : A.getY();
        int turnX = turnY == B.getY() ? A.getX() : B.getX();
        return new Position(turnX,turnY);
    }

    private static void addLHallWays(Position A,Position B,Position turn,int diffInHeight, TETile[][] world){
        if(diffInHeight > 0){
            addColHallWays(turn,A,world);
            addRowHallWays(turn,B,world);
        }else{
            addColHallWays(turn,B,world);
            addRowHallWays(turn,A,world);
        }
    }

    private static void addColHallWays(Position turn, Position end,TETile[][] world){
        int[][] adjs = new int[][]{{-1,0},{1,0}};
        for(int j = turn.getY(); j < end.getY(); j++){
            world[turn.getX()][j] = Tileset.WATER;
            for(int[] adj : adjs){
                int x = turn.getX() + adj[0];
                int y = j + adj[1];
                if( world[x][y].equals(Tileset.SAND)){
                    world[x][y] = Tileset.MOUNTAIN;
                }
            }
        }
    }
    private static void addRowHallWays(Position turn, Position end,TETile[][] world){
        int xs = Math.min(turn.getX(),end.getX());
        int xe = Math.max(turn.getX(),end.getX());
        int[][] adjs = new int[][]{{0,-1},{0,1}};
        for(int i = xs; i < xe; i++){
            world[i][turn.getY()] = Tileset.WATER;
            for(int[] adj : adjs){
                int x = i + adj[0];
                int y = turn.getY() + adj[1];
                if( world[x][y].equals(Tileset.SAND)){
                    world[x][y] = Tileset.MOUNTAIN;
                }
            }
        }
    }
    private static void completeTurnWalls(Position turn, TETile[][] world){
        int[][] crevices = new int[][]{{-1,-1},{0,-1},{1,-1}};
        for(int[] patch : crevices) {
            int x = turn.getX() + patch[0];
            int y = turn.getY() + patch[1];
            if (world[x][y].equals(Tileset.SAND)) {
                world[x][y] = Tileset.MOUNTAIN;
            }
        }
    }
}
