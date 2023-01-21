package backends;

import models.BuildModeState;
import models.Room;
import models.objects.Obj;
import models.objects.ObjectType;
import org.junit.jupiter.api.Test;
import screens.build.BuildModeBackend;

import static org.junit.jupiter.api.Assertions.*;

class BuildModeBackendTest {

    @Test
    void dropObject() {
        // Initialize
        var scRoom   = new Room("Student Center", 5);
        var caseRoom = new Room("CASE Building", 7);
        var sosRoom  = new Room("SOS Building", 10);
        var sciRoom  = new Room("SCI Building", 14);
        var engRoom  = new Room("ENG Building", 19);
        var snaRoom  = new Room("SNA Building", 25);
        var state = new BuildModeState(new Room[]{ scRoom, caseRoom, sosRoom, sciRoom, engRoom, snaRoom });
        state.setWidth(1000);
        state.setHeight(1000);
        var backend = new BuildModeBackend();

        // Setup objects in the student center
        scRoom.getObjects().add(new Obj(0, 0, 50, 50, ObjectType.CHALK_BOARD));
        scRoom.getObjects().add(new Obj(100, 100, 50, 50, ObjectType.CHALK_BOARD));
        scRoom.getObjects().add(new Obj(200, 200, 50, 50, ObjectType.CHALK_BOARD));
        scRoom.getObjects().add(new Obj(300, 300, 50, 50, ObjectType.CHALK_BOARD));

        // Test Case 1:
        // From Spec:
        // If no object is selected: Do nothing.
        backend.dropObject(state);
        assertNull(state.getSelectedObject());
        assertEquals(4, scRoom.getObjects().size());

        // Test Case 2:
        // From Spec
        // If an object is selected AND dropped inside screen border: Unselect object.
        var obj = scRoom.getObjects().get(0);
        state.setSelectedObject(obj);
        obj.setPosition(400, 400); // Object still inside border
        backend.dropObject(state);
        assertNull(state.getSelectedObject());
        assertEquals(4, scRoom.getObjects().size());

        // Test Case 3:
        // From Spec
        // If an object is selected AND dropped outside the top border: Unselect and delete object.
        obj = scRoom.getObjects().get(0);
        state.setSelectedObject(obj);
        obj.setPosition(400, -10); // Object outside top border
        backend.dropObject(state);
        assertNull(state.getSelectedObject());
        assertEquals(3, scRoom.getObjects().size());

        // Test Case 4:
        // From Spec
        // If an object is selected AND dropped outside the right border: Unselect and delete object.
        obj = scRoom.getObjects().get(0);
        state.setSelectedObject(obj);
        obj.setPosition(1050, 400); // Object outside right border
        backend.dropObject(state);
        assertNull(state.getSelectedObject());
        assertEquals(2, scRoom.getObjects().size());

        // Test Case 5:
        // From Spec
        // If an object is selected AND dropped outside the bottom border: Unselect and delete object.
        obj = scRoom.getObjects().get(0);
        state.setSelectedObject(obj);
        obj.setPosition(400, 1020); // Object outside bottom border
        backend.dropObject(state);
        assertNull(state.getSelectedObject());
        assertEquals(1, scRoom.getObjects().size());

        // Test Case 6:
        // From Spec
        // If an object is selected AND dropped outside the left border: Unselect and delete object.
        obj = scRoom.getObjects().get(0);
        state.setSelectedObject(obj);
        obj.setPosition(-20, 400); // Object outside left border
        backend.dropObject(state);
        assertNull(state.getSelectedObject());
        assertEquals(0, scRoom.getObjects().size());
    }
}