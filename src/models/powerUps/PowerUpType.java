package models.powerUps;

import utils.Asset;

public enum PowerUpType {
    ExtraTime(Asset.EXTRA_TIME),
    Hint(Asset.HINT),
    ProtectionVest(Asset.PROTECTION_VEST),
    PlasticBottle(Asset.PLASTIC_BOTTLE),
    ExtraLife(Asset.EXTRA_LIFE);

    public final Asset asset;

    PowerUpType(Asset asset) {
        this.asset = asset;
    }
}
