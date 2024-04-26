package com.shoebob.dotd.entities;

import com.shoebob.dotd.game.DotD;

public interface Entity {
    void create();

    void update(DotD game);

    void dispose();
}
