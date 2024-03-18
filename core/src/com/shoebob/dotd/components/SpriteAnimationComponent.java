package com.shoebob.dotd.components;

import com.shoebob.dotd.util.AttachableAnimation;
import com.shoebob.dotd.util.Consts;

public class SpriteAnimationComponent {
    public AttachableAnimation idleAnimation = Consts.Animations.playerIdleAnimation;

    public AttachableAnimation moveRAnimation = Consts.Animations.playerWalkRAnimation;

    public AttachableAnimation moveLAnimation = Consts.Animations.playerWalkLAnimation;

    public AttachableAnimation moveBAnimation = Consts.Animations.playerWalkBAnimation;

    public AttachableAnimation moveFAnimation = Consts.Animations.playerWalkFAnimation;
}
