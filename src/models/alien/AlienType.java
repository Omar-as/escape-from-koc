package models.alien;

import utils.Asset;

public enum AlienType {
    BLIND(Asset.ALIEN_BLIND_IDLE),
    SHOOTER(Asset.ALIEN_SHOOTER),
    TIME_WASTING(Asset.ALIEN_TIME_WASTING);

    public final Asset asset;

    AlienType(Asset asset) {
        this.asset = asset;
    }
}