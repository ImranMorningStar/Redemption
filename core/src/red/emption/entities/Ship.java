package red.emption.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;

import red.emption.Cnst;
import red.emption.Redemption;

public class Ship {

    Sprite shipSprite;
    Redemption redemption;
    float w = Cnst.width;
    float h = Cnst.height;
    public float maxSpeed,accn,dccn,accTimer,rotSpeed,rad,dx,dy,x,y,spw=0,sph=0;
    public  boolean up,down,left,right;

    newThrust nth;



    public Ship(Redemption re){

        maxSpeed = 500;
        accn = 400;
        dccn = 200;
        rad = 3.1415f/2f;
        rotSpeed = 3;
        x = w/2;
        y=h/2;
        this.redemption = re;
        nth=new newThrust();
        shipSprite = new Sprite(redemption.loader.shipTex);
        spw = shipSprite.getWidth()/2;
        sph = shipSprite.getHeight()/2;
        shipSprite.setScale(1/3f);

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void drawThrust(ShaderProgram s){
        nth.drawThrust(s);
    }
    public void draw(SpriteBatch batch){


        shipSprite.draw(batch);
//        System.out.println(shipSprite.getRotation());
//        Gdx.app.log("ang",);

    }
    public void update(float dt){
        if(left){
            rad+=rotSpeed*dt;
        } else if (right) {
            rad -=rotSpeed*dt;
        }
        if(up){
            nth.moo(true);
            dx+= MathUtils.cos(rad)*accn*dt;
            dy+= MathUtils.sin(rad)*accn*dt;
            accTimer+=dt;
            if (accTimer>0.1f){
                accTimer=0f;
            }
        } else {
            accTimer=0f;
        }
        //deccn
        float vec = (float) Math.sqrt(dx*dx + dy*dy);
        if (vec>0){
            dx-=(dx/vec)*dccn*dt;
            dy-=(dy/vec)*dccn*dt;
        }
        if (vec>maxSpeed){
            dx= (dx/vec)*maxSpeed;
            dy= (dy/vec)*maxSpeed;
        }
        //setpos
        x+=dx*dt;
        y+=dy*dt;
        shipSprite.setPosition(x-spw,y-sph);
        shipSprite.setRotation((MathUtils.radiansToDegrees*rad)-90);
        nth.setVel(vec);
        nth.setPoints(x,y,shipSprite.getRotation());
        nth.update(dt);

       // wrap();
    }

    private void wrap() {
        if (x<0) x = w;
        if (x>w) x=0;
        if (y<0) y=h;
        if (y>h) y=0;
    }

}
