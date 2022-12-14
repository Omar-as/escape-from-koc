package control;

import models.BuildModeState;
import models.objects.Obj;
import models.objects.ObjectType;

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

    public void insertRandomObject(BuildModeState state, ObjectType type) {
        // TODO: Remove magic numbers
        var newObj = new Obj(0, 0, 50, 50, type);
        var objects = state.getRooms()[state.getCurrentRoom()].getObjects();
        var random = new Random();
        var done = false;

        while (!done) {
            int x = random.nextInt(state.getWidth() - newObj.getWidth());
            int y = random.nextInt(state.getHeight() - newObj.getHeight());

            newObj.setPosition(x, y);

            int collisions = objects.stream()
                    .map(newObj::intersects)
                    .mapToInt(b -> b ? 1 : 0)
                    .sum();

            done = collisions == 0;
        }

        objects.add(newObj);
    }
}
