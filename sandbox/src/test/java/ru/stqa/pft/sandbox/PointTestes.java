package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class PointTestes {
    @Test
    public void testdistance2(){
        Point one = new Point(3,2);
        Point two = new Point(5,7);
        assert one.distance2(two) == 5.385164807134504;
    }
}
