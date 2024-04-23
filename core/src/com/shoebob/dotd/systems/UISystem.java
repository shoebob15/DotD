package com.shoebob.dotd.systems;

import com.shoebob.dotd.DotDGame;
import com.shoebob.dotd.entities.ui.OverlayEntity;

public class UISystem {
    public static void setOverlayPosition(OverlayEntity entity) {
        entity.gamePos = LocationSystem.vectorToPos(DotDGame.camera.position);
    }
}
