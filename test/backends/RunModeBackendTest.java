package backends;

import managers.KeyManager;
import models.*;
import models.objects.Obj;
import models.objects.ObjectType;
import screens.run.RunModeBackend;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RunModeBackendTest {

    @org.junit.jupiter.api.Test
    void movePlayer() {
        // Initialize
        var scRoom   = new Room("Student Center", 5);
        var caseRoom = new Room("CASE Building", 7);
        var sosRoom  = new Room("SOS Building", 10);
        var sciRoom  = new Room("SCI Building", 14);
        var engRoom  = new Room("ENG Building", 19);
        var snaRoom  = new Room("SNA Building", 25);


        // Setup objects in the student center
        scRoom.getObjects().add(new Obj(0, 0, 50, 50, ObjectType.CHALK_BOARD));
        scRoom.getObjects().add(new Obj(100, 100, 50, 50, ObjectType.CHALK_BOARD));
        scRoom.getObjects().add(new Obj(200, 200, 50, 50, ObjectType.CHALK_BOARD));
        scRoom.getObjects().add(new Obj(300, 300, 50, 50, ObjectType.CHALK_BOARD));


        var state = new RunModeState(new Room[]{ scRoom, caseRoom, sosRoom, sciRoom, engRoom, snaRoom });
        state.setWidth(1000);
        state.setHeight(1000);
        var backend = new RunModeBackend();
        var player = state.getPlayer();
        //case 1
        KeyManager.getInstance().setKeyPressed(KeyEvent.VK_UP,true);
        backend.movePlayer(state);
        assertEquals(0, player.getPosition().getX());
        assertEquals(0, player.getPosition().getY());
        KeyManager.getInstance().setKeyPressed(KeyEvent.VK_UP,false);
        player.setPosition(new Position(500,500));
        //case 2
        KeyManager.getInstance().setKeyPressed(KeyEvent.VK_DOWN,true);
        backend.movePlayer(state);
        assertEquals(500,player.getPosition().getX());
        assertEquals(505,player.getPosition().getY());
        KeyManager.getInstance().setKeyPressed(KeyEvent.VK_DOWN,false);
        player.setPosition(new Position(500,500));
        //case 3
        KeyManager.getInstance().setKeyPressed(KeyEvent.VK_LEFT,true);
        backend.movePlayer(state);
        assertEquals(495,player.getPosition().getX());
        assertEquals(500,player.getPosition().getY());
        KeyManager.getInstance().setKeyPressed(KeyEvent.VK_LEFT,false);
        player.setPosition(new Position(500,500));
        //case 4
        KeyManager.getInstance().setKeyPressed(KeyEvent.VK_RIGHT,true);
        backend.movePlayer(state);
        assertEquals(505,player.getPosition().getX());
        assertEquals(500,player.getPosition().getY());
        KeyManager.getInstance().setKeyPressed(KeyEvent.VK_RIGHT,false);
        player.setPosition(new Position(500,500));
        //case 5
        KeyManager.getInstance().setKeyPressed(KeyEvent.VK_RIGHT,true);
        KeyManager.getInstance().setKeyPressed(KeyEvent.VK_UP,true);
        backend.movePlayer(state);
        assertEquals(503,player.getPosition().getX());
        assertEquals(497,player.getPosition().getY());
        KeyManager.getInstance().setKeyPressed(KeyEvent.VK_RIGHT,false);
        KeyManager.getInstance().setKeyPressed(KeyEvent.VK_UP,false);
        player.setPosition(new Position(500,500));


    }
}