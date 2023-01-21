package models;


import screens.run.RunModeBackend;
import models.objects.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RunModeBackendTest {

    @Test
    void pickupKey(){
        //  EFFECTS
        //  If key already found: Returns and doesn't do anything
        //  If key not found + Close Enough + Correct Click: Sets key to found
        //  Key not found + Close Enough + Incorrect Click: Returns and doesn't do anything
        //  Key not found + Not Close Enough + Correct Click: Returns and doesn't do anything
        //  Key not found + Not Close Enough + Incorrect Click: Returns and doesn't do anything

        Room studentCenter = new Room("Student Center", 1);
        studentCenter.getObjects().add(new Obj(100, 100, 50, 50, ObjectType.CHALK_BOARD));
        studentCenter.getObjects().add(new Obj(400, 400, 50, 50, ObjectType.TRASH_BIN));
        RunModeState state = new RunModeState(new Room[]{studentCenter});
        var player = state.getPlayer();

        RunModeBackend backend = new RunModeBackend();
        state.setWidth(1000);
        state.setHeight(1000);
        state.setKey(new Random(10));

        // Test Case 1:
        // Key not found + Not Close Enough + Correct Click
        backend.pickupKey(state, 420, 420);
        assertFalse(state.getKey().isFound());

        // Test Case 2:
        // Key not found + Not Close Enough + Incorrect Click
        backend.pickupKey(state, 120, 120);
        assertFalse(state.getKey().isFound());

        // Test Case 3:
        // Key not found + Close Enough + Incorrect Click
        player.setPosition(60, 60);
        backend.pickupKey(state, 120, 120);
        assertFalse(state.getKey().isFound());

        // Test Case 4:
        // Key not found + Close Enough + Correct Click

        player.setPosition(370, 370);
        backend.pickupKey(state, 420, 420);
        assertTrue(state.getKey().isFound());

        // Test Case 5:
        // Key already found
        player.setPosition(60, 60);
        backend.pickupKey(state, 120, 120);
        assertTrue(state.getKey().isFound());
    }

}
