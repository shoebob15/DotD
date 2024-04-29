package com.shoebob.dotd.components;

import com.shoebob.dotd.game.Consts;
import com.shoebob.dotd.util.AttachableAnimation;

public class AnimatedSpriteComponent implements Component {
    public AttachableAnimation currentAnimation = Consts.AttachedAnimations.playerIdleAnimation;

    public AttachableAnimation idleAnimation = Consts.AttachedAnimations.playerIdleAnimation;

    public AttachableAnimation moveRAnimation = Consts.AttachedAnimations.playerWalkRAnimation;

    public AttachableAnimation moveLAnimation = Consts.AttachedAnimations.playerWalkLAnimation;

    public AttachableAnimation moveBAnimation = Consts.AttachedAnimations.playerWalkBAnimation;

    public AttachableAnimation moveFAnimation = Consts.AttachedAnimations.playerWalkFAnimation;
}
