package byow.Core;

import javax.management.relation.RelationNotFoundException;
import java.util.Random;

public class BPS {

    private static final int MIN_SIZE = 7;

    protected int width;
    protected int height;
    Position start;
    Room room;
    BPS left;
    BPS right;

    public BPS(Position pos, int w, int h){
        start = pos;
        width = w;
        height = h;
        left = null;
        right = null;
        room = null;
    }

    public boolean canSplit(Random rand){

        if(left != null || right != null) return false;
        if(width < MIN_SIZE || height < MIN_SIZE) return false;

        boolean splitByH;
        if(width >= height * 1.25){
            splitByH = false;
        }else if(height >= width * 1.25){
            splitByH = true;
        }else
            splitByH = RandomUtils.bernoulli(rand);

        int childSize = (splitByH? height : width) - MIN_SIZE;
        if(childSize < MIN_SIZE){
            return false;
        }

        int splitSize = RandomUtils.uniform(rand,childSize);
        if(splitByH){
            left = new BPS(start,width,splitSize);
            Position rStart = new Position(start.getX(),start.getY() + splitSize);
            right = new BPS(rStart,width,height - splitSize);
        }else{
            left = new BPS(start,splitSize,height);
            Position rStart = new Position(start.getX() + splitSize,start.getY());
            right = new BPS(rStart,width - splitSize,height);
        }
        return true;
    }

    public void assignRoomArea(Random rand){

        if(width < MIN_SIZE || height < MIN_SIZE) return;
        if(left != null){
            left.assignRoomArea(rand);
            right.assignRoomArea(rand);
        }else{

            int xStart = RandomUtils.uniform(rand,width - MIN_SIZE + 1);
            int yStart = RandomUtils.uniform(rand,height - MIN_SIZE + 1);

            Position childstart = new Position(start.getX() +xStart,start.getY() + yStart);
            int room_w = Math.max(MIN_SIZE,RandomUtils.uniform(rand,width-xStart));
            int room_h = Math.max(MIN_SIZE,RandomUtils.uniform(rand,height - yStart));
            room = new Room(room_w,room_h,childstart);
        }
    }
}
