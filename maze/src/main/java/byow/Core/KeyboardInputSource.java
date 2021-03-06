package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

public class KeyboardInputSource implements InputSource{

    public KeyboardInputSource(){}

    @Override
    public char getNextKey(){
        while(true){
            if(StdDraw.hasNextKeyTyped()){
                return Character.toUpperCase(StdDraw.nextKeyTyped());
            }
        }
    }
    @Override
    public boolean hasNextKey(){
        return true;
    }
}
