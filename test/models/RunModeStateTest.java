package models;

import models.alien.Alien;
import models.alien.AlienType;
import models.alien.TimeWastingAlien;
import models.objects.Obj;
import models.objects.ObjectType;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RunModeStateTest {

    @Test
    void setWidth() {
        var room = new Room("Student Center", 1);
        room.getObjects().add(new Obj(1, 2, 3, 4, ObjectType.CHALK_BOARD));
        var state = new RunModeState(new Room[]{ room });
        assertTrue(state.repOk());
        assertThrows(IllegalArgumentException.class, () -> { state.setWidth(-1); });
        assertTrue(state.repOk());
        assertThrows(IllegalArgumentException.class, () -> { state.setWidth(0); });
        assertTrue(state.repOk());
        state.setWidth(100);
        assertEquals(100, state.getWidth());
        assertTrue(state.repOk());
    }

    @Test
    void setHeight() {
        var room = new Room("Student Center", 1);
        room.getObjects().add(new Obj(1, 2, 3, 4, ObjectType.CHALK_BOARD));
        var state = new RunModeState(new Room[]{ room });
        assertTrue(state.repOk());
        assertThrows(IllegalArgumentException.class, () -> { state.setHeight(-1); });
        assertTrue(state.repOk());
        assertThrows(IllegalArgumentException.class, () -> { state.setHeight(0); });
        assertTrue(state.repOk());
        state.setHeight(100);
        assertEquals(100, state.getHeight());
        assertTrue(state.repOk());
    }

    @Test
    void setAliens() {
        var room = new Room("Student Center", 1);
        room.getObjects().add(new Obj(1, 2, 3, 4, ObjectType.CHALK_BOARD));
        var state = new RunModeState(new Room[]{ room });
        assertTrue(state.repOk());
        assertTrue(state.repOk());
        state.getTimeWastingAliens().add(new TimeWastingAlien(1,1,64,64));
        assertEquals(1, state.getTimeWastingAliens().size());
        assertTrue(state.repOk());

    }

    @Test
    void setRooms() {
        var room = new Room("Student Center", 1);
        room.getObjects().add(new Obj(1, 2, 3, 4, ObjectType.CHALK_BOARD));
        var state = new RunModeState(new Room[]{ room });
        assertTrue(state.repOk());
        assertThrows(IllegalArgumentException.class, () -> { state.setRooms(null); });
        assertTrue(state.repOk());
        assertThrows(IllegalArgumentException.class, () -> { state.setRooms(new Room[]{}); });
        assertTrue(state.repOk());
        state.setRooms(new Room[]{room});
        assertEquals(room, state.getRooms()[0]);
        assertTrue(state.repOk());

    }

    @Test
    void incCurrentRoom() {
        var room = new Room("Student Center", 1);
        var room2 = new Room("SNA", 1);
        room.getObjects().add(new Obj(1, 2, 3, 4, ObjectType.CHALK_BOARD));
        room2.getObjects().add(new Obj(1, 2, 3, 4, ObjectType.CHALK_BOARD));
        var state = new RunModeState(new Room[]{ room });
        assertTrue(state.repOk());
        assertThrows(IllegalArgumentException.class, state::incCurrentRoom);
    }

    @Test
    void setPowerUps() {
        var room = new Room("Student Center", 1);
        room.getObjects().add(new Obj(1, 2, 3, 4, ObjectType.CHALK_BOARD));
        var state = new RunModeState(new Room[]{ room });
        assertTrue(state.repOk());
        assertThrows(IllegalArgumentException.class, () -> { state.setPowerUps(null);});
    }

    @Test
    void setPlayer() {
        var room = new Room("Student Center", 1);
        room.getObjects().add(new Obj(1, 2, 3, 4, ObjectType.CHALK_BOARD));
        var state = new RunModeState(new Room[]{ room });
        assertTrue(state.repOk());
        state.setPlayer(new Player(5, 10, 10, 64, 64));
        assertTrue(state.repOk());
        assertThrows(IllegalArgumentException.class, () -> { state.setPlayer(null);});

    }

    @Test
    void setDoor() {
        var room = new Room("Student Center", 1);
        room.getObjects().add(new Obj(1, 2, 3, 4, ObjectType.CHALK_BOARD));
        var state = new RunModeState(new Room[]{ room });
        assertTrue(state.repOk());
        state.setDoor(new Door(200, 100, 100, 100));
        assertTrue(state.repOk());
        assertThrows(IllegalArgumentException.class, () -> { state.setDoor(null);});
    }

    @Test
    void setShowKeyFor() {
        var room = new Room("Student Center", 1);
        var object = new Obj(1,2,3,4,ObjectType.CHALK_BOARD);
        room.getObjects().add(object);
        var state = new RunModeState(new Room[]{ room });
        state.setShowKeyFor(3);
        assertTrue(state.repOk());
        assertThrows(IllegalArgumentException.class, () -> { state.setShowKeyFor(-1);});
    }
}