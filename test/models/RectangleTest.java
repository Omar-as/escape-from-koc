package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void intersects() {

        // Position for this Rectrangle
        var posThis = new Position(5,5);

        // Postion for intersect from top
        var posFar = new Position(10,10);

        // Postion for intersect from top
        var posTop = new Position(5,6);

        // Postion for intersect from bottom
        var posBottom = new Position(5,4);

        // Postion for intersect from right
        var posRight = new Position(6,5);

        // Position for intersect from left
        var posLeft = new Position(4,5);

        // Position for intersect by being inside the other completly
        var posInside = new Position(5,5);

        // This rectangle
        var rectThis = new Rectangle(posThis,2,2);

        // Far from this rectangle
        var rectFar = new Rectangle(posFar,2,2);

        // Top Rectangle
        var rectTop = new Rectangle(posTop,2,2);

        // Bottom Rectrangle
        var rectBottom = new Rectangle(posBottom,2,2);

        // Right Rectangle
        var rectRight = new Rectangle(posRight,2,2);

        // Left Triangle
        var rectLeft = new Rectangle(posLeft,2,2);

        // Inside Rectangle
        var rectInside = new Rectangle(posInside,2,2);


        // CASE1 :  Far From each other
        // Return false since, they are not intersecting
        var doesIntersectFar = rectThis.intersects(rectFar);
        assertFalse(doesIntersectFar);

        // CASE2 :  Intersect from top
        // Should return true since, they are intersecting from top
        var doesIntersectTop = rectThis.intersects(rectTop);
        assertTrue(doesIntersectTop);


        // CASE3 :  Intersect from bottom
        // Should return true since, they are intersecting from bottom
        var doesIntersectBottom = rectThis.intersects(rectBottom);
        assertTrue(doesIntersectBottom);

        // CASE4 :  Intersect from right
        // Should return true since, they are intersecting from right
        var doesIntersectRight = rectThis.intersects(rectRight);
        assertTrue(doesIntersectRight);

        // CASE5 :  Intersect from left
        // Should return true since, they are intersecting from left
        var doesIntersectLeft = rectThis.intersects(rectLeft);
        assertTrue(doesIntersectLeft);

        // CASE6 :  Intersection by being inside each other
        // Should return true since, they are intersecting inside each other
        var doesIntersectInside = rectThis.intersects(rectInside);
        assertTrue(doesIntersectInside);










    }
}