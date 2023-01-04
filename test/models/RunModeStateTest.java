package models;

import models.alien.Alien;
import models.alien.AlienType;
import models.objects.Obj;
import models.objects.ObjectType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunModeStateTest {

    @Test
    void setWidth() {
        var room = new Room("Student Center", 1);
        room.getObjects().add(new Obj(1, 2, 3, 4, ObjectType.CHALK_BOARD));
        var state = new RunModeState(new Alien[]{}, false, new Room[]{ room }, new PowerUp[]{}, new Player(3, 100, 0, 0, 100, 100), new Door(100, 100, 50, 50));
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
        var state = new RunModeState(new Alien[]{}, false, new Room[]{ room }, new PowerUp[]{}, new Player(3, 100, 0, 0, 100, 100), new Door(100, 100, 50, 50));
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
        var state = new RunModeState(new Alien[]{}, false, new Room[]{ room }, new PowerUp[]{}, new Player(3, 100, 0, 0, 100, 100), new Door(100, 100, 50, 50));
        assertTrue(state.repOk());
        assertThrows(IllegalArgumentException.class, () -> { state.setAliens(null); });
        assertTrue(state.repOk());
        state.setAliens(new Alien[]{new Alien(AlienType.TIME_WASTING, 1,1,64,64)});
        assertEquals(1, state.getAliens().length);
        assertTrue(state.repOk());

    }

    @Test
    void setRooms() {
        var room = new Room("Student Center", 1);
        room.getObjects().add(new Obj(1, 2, 3, 4, ObjectType.CHALK_BOARD));
        var state = new RunModeState(new Alien[]{}, false, new Room[]{ room }, new PowerUp[]{}, new Player(3, 100, 0, 0, 100, 100), new Door(100, 100, 50, 50));
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
    }

    @Test
    void setPowerUps() {
    }

    @Test
    void setPlayer() {
    }

    @Test
    void setDoor() {
    }

    @Test
    void setKey() {
    }

    @Test
    void setShowKeyFor() {
    }
}