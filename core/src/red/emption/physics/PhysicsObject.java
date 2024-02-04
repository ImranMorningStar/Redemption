package red.emption.physics;

import com.badlogic.gdx.math.MathUtils;

public class PhysicsObject {

    protected float
    x,
    y,
    vel,
    velX,
    velY,
    accn,
    angle,
    rotSpeed;
    protected float[] verts;

    public void setPosition(float x, float y){
        this.x=x;
        this.y=y;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public float getVelX() {

        return velX;
    }
    public float getVelY() {

        return velY;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public void setVel(float vel) {
        this.vel = vel;
        velX = vel * MathUtils.cosDeg(angle);
        velY = vel * MathUtils.sinDeg(angle);
    }
}
