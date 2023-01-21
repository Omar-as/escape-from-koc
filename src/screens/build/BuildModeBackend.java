package screens.build;

import models.BuildModeState;
import models.Room;
import models.objects.Obj;
import models.objects.ObjectType;
import screens.Backend;
import utils.Constants;
import utils.RandomUtils;

import java.util.Random;

public class BuildModeBackend implements Backend<BuildModeState> {
    @Override
    public void updateState(BuildModeState state) {
    }

    public void liftObject(BuildModeState state, int pressX, int pressY) {
        for (var obj : state.getRooms()[state.getCurrentRoom()].getObjects()) {
            int objX = obj.getPosition().getX();
            int objY = obj.getPosition().getY();
            if (pressX >= objX && pressX <= objX + obj.getWidth() && pressY >= objY && pressY <= objY + obj.getHeight()) {
                state.setSelectedObject(obj);
                break;
            }
        }
    }

    public void dropObject(BuildModeState state) {
        // MODIFIES:
        // state.selectedObject
        // state.rooms
        // EFFECTS :
        // If no object is selected: Do nothing.
        // If an object is selected AND dropped outside the top    border: Unselect and delete object.
        // If an object is selected AND dropped outside the right  border: Unselect and delete object.
        // If an object is selected AND dropped outside the bottom border: Unselect and delete object.
        // If an object is selected AND dropped outside the left   border: Unselect and delete object.
        // If an object is selected AND dropped inside  screen     border: Unselect object.
        var selected = state.getSelectedObject();
        if (selected == null) return;
        var x = selected.getPosition().getX();
        var y = selected.getPosition().getY();
        var w = selected.getWidth();
        var h = selected.getHeight();
        if (x < 0 || x + w >= state.getWidth() || y < 0 || y + h >= state.getHeight()) {
            var objects = state.getRooms()[state.getCurrentRoom()].getObjects();
            objects.remove(selected);
        }
        state.setSelectedObject(null);
    }

    public void moveObject(BuildModeState state, int newX, int newY) {
        var selected = state.getSelectedObject();
        if (selected == null) return;
        var objects = state.getRooms()[state.getCurrentRoom()].getObjects();
        var backupPosition = selected.getPosition();
        selected.setPosition(newX, newY);
        for (var obj : objects)
            if (obj != selected && selected.intersects(obj)) {
                selected.setPosition(backupPosition);
                break;
            }
        if (selected.intersects(state.getDoor())) selected.setPosition(backupPosition.getX(), backupPosition.getY());
    }

    public void insertRandomObjectInCurrentRoom(BuildModeState state, ObjectType type) {
        insertRandomObject(state, state.getRooms()[state.getCurrentRoom()], type);
    }

    public void fillOneRoomRandomly(BuildModeState state, Room room) {
        int NumOfObjs = room.getMinObjects() - room.getObjects().size();
        var random = new Random();
        for (int i = 0; i < NumOfObjs; i++)
            insertRandomObject(state, room, ObjectType.values()[random.nextInt(ObjectType.values().length)]);
    }

    public void fillAllRoomsRandomly(BuildModeState state) {
        Room[] rooms = state.getRooms();
        int numOfRooms = state.getRooms().length;
        for (int i = 0; i < numOfRooms; i++) {
            fillOneRoomRandomly(state, rooms[i]);
        }
    }

    private void insertRandomObject(BuildModeState state, Room room, ObjectType type) {
        var newObj = new Obj(0, 0, Constants.OBJ_DIM, Constants.OBJ_DIM, type);
        RandomUtils.buildModeRandomize(state, room, newObj);
        room.getObjects().add(newObj);
    }
}
