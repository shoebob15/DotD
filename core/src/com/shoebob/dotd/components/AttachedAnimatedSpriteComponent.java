package com.shoebob.dotd.components;

import com.shoebob.dotd.game.Consts;

public class AttachedAnimatedSpriteComponent implements Component {
    public AttachableAnimationComponent currentAnimation = Consts.AttachedAnimations.playerIdleAnimation;

    public AttachableAnimationComponent idleAnimation = Consts.AttachedAnimations.playerIdleAnimation;

    public AttachableAnimationComponent moveRAnimation = Consts.AttachedAnimations.playerWalkRAnimation;

    public AttachableAnimationComponent moveLAnimation = Consts.AttachedAnimations.playerWalkLAnimation;

    public AttachableAnimationComponent moveBAnimation = Consts.AttachedAnimations.playerWalkBAnimation;

    public AttachableAnimationComponent moveFAnimation = Consts.AttachedAnimations.playerWalkFAnimation;
}
