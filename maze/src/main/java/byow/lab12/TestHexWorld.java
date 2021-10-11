package byow.lab12;

import org.junit.Test;

import static byow.lab12.HexWorld.*;
import static org.junit.Assert.assertEquals;

public class TestHexWorld {

    @Test
    public void TestWidthCalc(){
        assertEquals(3, WidthCalc(3, 5));
        assertEquals(5, WidthCalc(3, 4));
        assertEquals(7, WidthCalc(3, 3));
        assertEquals(7, WidthCalc(3, 2));
        assertEquals(5, WidthCalc(3, 1));
        assertEquals(3, WidthCalc(3, 0));
        assertEquals(2, WidthCalc(2, 0));
        assertEquals(4, WidthCalc(2, 1));
        assertEquals(4, WidthCalc(2, 2));
        assertEquals(2, WidthCalc(2, 3));
    }

    @Test
    public void TestxStarrt(){
        assertEquals(0, xOffSet(3, 5));
        assertEquals(-1, xOffSet(3, 4));
        assertEquals(-2, xOffSet(3, 3));
        assertEquals(-2, xOffSet(3, 2));
        assertEquals(-1, xOffSet(3, 1));
        assertEquals(0, xOffSet(3, 0));
        assertEquals(0, xOffSet(2, 0));
        assertEquals(-1, xOffSet(2, 1));
        assertEquals(-1, xOffSet(2, 2));
        assertEquals(0, xOffSet(2, 3));
    }
}
