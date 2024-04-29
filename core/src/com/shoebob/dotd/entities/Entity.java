package com.shoebob.dotd.entities;

import com.shoebob.dotd.game.DotD;

public interface Entity {
    @Deprecated
    void create(); // create method is stupid and only makes more messy and unreadable code

    void update(DotD game);

    void dispose();
}
