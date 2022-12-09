package models.objects;

import utils.Asset;
import utils.Constants;

public class TrashBin extends Obj {
    public TrashBin(int xPosition, int yPosition) {
        super(xPosition, yPosition, 50, 50, Asset.TRASH_BIN);
    }
}
