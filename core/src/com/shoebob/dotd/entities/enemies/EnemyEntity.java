package com.shoebob.dotd.entities.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.shoebob.dotd.components.BodyComponent;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.components.SpriteAnimationComponent;
import com.shoebob.dotd.components.VelocityComponent;
import com.shoebob.dotd.entities.Entity;
import com.shoebob.dotd.game.Consts;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.systems.AnimationSystem;
import com.shoebob.dotd.systems.LocationSystem;

public class EnemyEntity implements Entity {
    public PositionComponent position;
    public BodyComponent body;
    public VelocityComponent velocity;
    public SpriteAnimationComponent animation;

    public EnemyEntity(PositionComponent pos) {
        this.position = pos;
        this.body = new BodyComponent(16, 16);
        this.velocity = new VelocityComponent();
        this.animation = new SpriteAnimationComponent();

        animation.currentAnimation = Consts.AnimationComponents.zombieIdle;
        animation.idleAnimation = Consts.AnimationComponents.zombieIdle;
        animation.moveFAnimation = Consts.AnimationComponents.zombieWalkF;
        animation.moveRAnimation = Consts.AnimationComponents.zombieWalkR;
        animation.moveLAnimation = Consts.AnimationComponents.zombieWalkL;
        animation.moveBAnimation = Consts.AnimationComponents.zombieWalkB;
    }

    @Override
    public void create() {
        // LEAVE EMPTY
    }

    @Override
    public void update(DotD game) {
        float expX = 0, expY = 0;


        velocity.vector.set(expX, expY);
        velocity.vector.nor();

        animation.currentAnimation = AnimationSystem.getSpriteAnimation(animation, velocity);

        LocationSystem.addVelocity(position, velocity);

        game.batch.draw(animation.currentAnimation.animation.getKeyFrame(game.statetime, true), position.x, position.y, body.width, body.height);

    }

    @Override
    public void dispose() {

    }
}
