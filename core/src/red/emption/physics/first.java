package red.emption.physics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class first extends PhysicsObject{
    Texture tex;
    float mass;

    public first (float x, float y){
        this.x = x;
        this.y = y;
        tex = new Texture("ui/analog_stick_knob.png");
        angle=0;
        velX=vel=velY=0;
       this. rotSpeed=30;
       mass = 50;
    }
    public void move(float dt){
       this. x += getVelX()*dt;
       this. y += getVelY()*dt;
    }
    public void rotate(float dt){
        this.angle+=rotSpeed*dt;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void draw(SpriteBatch batch){
        batch.draw(tex,x,y,50,50);
    }
    public void checkCollision(first f){
        float dx = this.x-f.x;
        if(dx<0)dx=-dx;

        float dy = this.y-f.y;
        if (dy<0)dy=-dy;



        if (dx<50&&dy<50){
            float v_x = (mass*velX + f.mass*f.velX)/(mass+f.mass);
            float actVelx = 2*v_x-velX;
            float otx = 2*v_x-f.velX;

            float v_y = (mass*velY + f.mass*f.velY)/(mass+f.mass);
            float actVely = 2*v_y-velY;
            float oty = 2 * v_y -f.velY;
            this.setVelX(actVelx);
            this.setVelY(actVely);
            f.setVelX(otx);
            f.setVelY(oty);
        }
    }
}
