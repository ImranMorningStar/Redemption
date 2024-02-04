package red.emption.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.BufferUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Random;

import red.emption.Cnst;
import red.emption.managers.GameKeys;
import red.emption.noise.SimplexNoise;

public class Background {
//    ArrayList<Float> verx = new ArrayList<>();
//    ArrayList<Float> very = new ArrayList<>();
    ArrayList<Sprite> stars = new ArrayList<>();
    Pixmap pmp1,pmp2;
    int w = (int) Cnst.width,h = (int) Cnst.height;
    int OriginX=0,OriginY=0;
    Texture tex;
    SimplexNoise simplexNoise;
    private int numStars;
    private FloatBuffer verticesBuffer;
    private float[] vertices;


    public Background(){
//        pmp1 = new Pixmap(10,10,Format.RGBA8888);
//        pmp1.setColor(Color.WHITE);
//        pmp1.fill();
//        pmp1.setColor(Color.BLUE);
//        pmp1.fillCircle(5,5,5);

//
//        pmp2.drawPixmap(pmp1,);
//        createTex(0,0);
        tex = new Texture(Gdx.files.internal("bg/star2.png"));
//        pmp1.dispose();
        new Thread(this::generateStars).start();



    }

    public void setNumStars(int numStars) {
        this.numStars = numStars;
    }


    public void generateStars(){
        for (int i = OriginX;i < OriginX+w;i+=16){
            for (int j = OriginY; j < OriginY+w; j+=16){
                if (SimplexNoise.noise(i,j) > 0.9){
                    Sprite star = new Sprite(tex);
                    star.setPosition(i,j);
                    stars.add(star);
                }
            }
        }
    }

    public int getOriginX() {
        return OriginX;
    }

    public int getOriginY() {
        return OriginY;
    }

    public void update(int x, int y){
        this.OriginX=x;
        this.OriginY=y;
        stars.clear();
        generateStars();
    }
    public void drawStars(SpriteBatch batch){
        if (!stars.isEmpty()){
            for (Sprite star : stars){
                star.draw(batch);
            }
        }
    }
//    public void generateStars(){
//        for (int i = numStars-w ; i <numStars;i+=16){
//            for (int j = numStars-w ; j < numStars; j+=16){
//                if (SimplexNoise.noise(i,j)>0.9){
//                    Sprite star = new Sprite(tex);
//                    star.setPosition(i,j);
//                    stars.add(star);
//                }
//            }
//        }
//    }
    public void render(SpriteBatch batch){
//        Gdx.app.log("gen","run");
        if(GameKeys.isPressed(GameKeys.SPACE)){
            stars.clear();
            setNumStars(getX()+640);
            generateStars();
            System.out.println(w+" "+h);
        }
        if (stars!=null){
            for (Sprite star: stars){
                star.draw(batch);
            }
//            for (int i= 0 ; i<verx.size();i++){
//                float x = verx.get(i);
//                float y = very.get(i);
//                batch.draw(tex,x,y);
//            }
//            batch.draw(tex,vertices,0,numStars*2);
//            batch.draw(tex,verticesBuffer,0,numStars*2);
        }
    }
//    public void createTex(float x , float y){
//        for (int i=0;i<x+w;i++){
//            for(int j=0;j<y+h;j++){
//                double d = simplexNoise.noise(i,j);
////                System.out.println(d);
//                if (d<0.5){
//                    pmp2.drawPixmap(pmp1,(int)i, (int) j);
//                }
//            }
//        }
//    }

    public Texture getTex() {
        return this.tex;
    }

    public int getX() {
        return numStars;
    }

}
