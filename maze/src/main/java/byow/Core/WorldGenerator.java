package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class WorldGenerator {

    TETile avatar;
    char num;
    List<Room> rooms;
    public WorldGenerator(){
        num = '\0';
        avatar = Tileset.AVATAR;
        rooms = new LinkedList<>();
    }

    public Position BuildWorld(TETile[][] world,Random rand,InputSource input){

        for(int x = 0 ; x < world.length ; x++){
            for(int y = 0; y < world[0].length; y++){
                world[x][y] = Tileset.SAND;
            }
        }
        rooms = Room.buildRandomRooms(world, rand);
        HallWay.connectByHallWays(world,rooms,rand);
        Position avatar = putAvatar(world,rand,input);
        putDoor(world, rand,Tileset.UNLOCKED_DOOR);
        putDoor(world, rand,Tileset.LOCKED_DOOR);
        putAttacker(world,rand);
        return avatar;
    }

    private Position putAvatar(TETile[][] world,Random rand,InputSource input){
        int index = RandomUtils.uniform(rand, rooms.size());
        Room putRoom = rooms.remove(index);

        int birthX = putRoom.rstart.getX() + 1 + RandomUtils.uniform(rand,putRoom.getWidth() -2);
        int birthY = putRoom.rstart.getY() + 1 + RandomUtils.uniform(rand, putRoom.getHeight()-2);

        if(input.getClass().equals(KeyboardInputSource.class)) {
            num = input.getNextKey();
            avatar = Tileset.avatars(num);
            if(avatar != null) world[birthX][birthY] = avatar;
            else world[birthX][birthY] = Tileset.AVATAR;
        }
        else {
            world[birthX][birthY] = Tileset.AVATAR;
        }
        return new Position(birthX, birthY);
    }

    private void putDoor(TETile[][] world, Random rand,TETile t){
        int index = RandomUtils.uniform(rand, rooms.size());
        Room putRoom = rooms.remove(index);

        List<Position> walls = collectedWalls(putRoom,world);
        int i = RandomUtils.uniform(rand, walls.size());
        Position unlocked = walls.get(i);
        world[unlocked.getX()][unlocked.getY()] = t;
    }

    private List<Position> collectedWalls(Room room, TETile[][] world){
        int startX = room.rstart.getX();
        int startY = room.rstart.getY();
        List<Position> walls = new LinkedList<>();

        for (int i = 1; i < room.getWidth() -1; i++){
            if(world[startX + i][startY] != Tileset.WATER){
                walls.add(new Position(startX + i,startY));
            }
        }
        for (int j = 1; j < room.getHeight() -1; j++){
            if(world[startX][startY + j] != Tileset.WATER){
                walls.add(new Position(startX ,startY + j));
            }
        }

        return walls;
    }
    public TETile setAvatar(){
        return avatar;
    }

    private void putAttacker(TETile[][] world,Random rand){
        for(int i =0; i < 3; i ++) {
            int index = rand.nextInt(rooms.size());
            Room putRoom = rooms.get(index);

            int birthX = putRoom.rstart.getX() + 1 + RandomUtils.uniform(rand, putRoom.getWidth() - 2);
            int birthY = putRoom.rstart.getY() + 1 + RandomUtils.uniform(rand, putRoom.getHeight() - 2);
            world[birthX][birthY] = Tileset.ATTACKER;
        }
    }
}
