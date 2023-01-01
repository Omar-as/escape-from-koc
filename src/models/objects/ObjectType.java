package models.objects;

import utils.Asset;

public enum ObjectType {
    TRASH_BIN(Asset.TRASH_BIN),
    CHALK_BOARD(Asset.CHALK_BOARD),
    BOOKSHELF(Asset.BOOKSHELF);

    public final Asset asset;

    ObjectType(Asset asset) {
        this.asset = asset;
    }
}
