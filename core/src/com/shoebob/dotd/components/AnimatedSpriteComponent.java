package com.shoebob.dotd.components;

import com.shoebob.dotd.util.AttachableAnimation;
import com.shoebob.dotd.util.Consts;

public class AnimatedSpriteComponent {
    public AttachableAnimation currentAnimation = Consts.AttachedAnimations.playerIdleAnimation;

    public AttachableAnimation idleAnimation = Consts.AttachedAnimations.playerIdleAnimation;

    public AttachableAnimation moveRAnimation = Consts.AttachedAnimations.playerWalkRAnimation;

    public AttachableAnimation moveLAnimation = Consts.AttachedAnimations.playerWalkLAnimation;

    public AttachableAnimation moveBAnimation = Consts.AttachedAnimations.playerWalkBAnimation;

    public AttachableAnimation moveFAnimation = Consts.AttachedAnimations.playerWalkFAnimation;
}
