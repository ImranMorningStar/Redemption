package red.emption.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import red.emption.Cnst;
import red.emption.Redemption;
import red.emption.noise.NoiseGeneretor;
import red.emption.noise.SimplexNoise;

public class Bg {


    private final Texture starTexture;
    private int OriginX=0;
    private int OriginY=0;
    private final ArrayList<Sprite> stars = new ArrayList<>();
    private final int w = (int) Cnst.width;
    NoiseGeneretor generetor;

    public Bg(Redemption red){
        starTexture = red.loader.starTex;
    }

    public void generateStars(){
        for (int i = OriginX;i < OriginX+(2*w);i+=32){
            for (int j = OriginY; j < OriginY+w; j+=32){
                double n = SimplexNoise.noise(i,j);
//                double n = NoiseGeneretor.noise(i*0.1,j*0.1); //n>0.5

                if (n >.9){
                    Sprite star = new Sprite(starTexture);
//                    star.setColor((float)n*(float)n*20,(float)n*30f,(float)n*20,1);
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
//        if (stars.size()>200){
//            for (int i = 0 ; i < 50;i++){
//                stars.remove(0);
//            }
//        }
        stars.clear();
        generateStars();
        System.out.println(w+" ");
    }
    public void drawStars(SpriteBatch batch){
        if (!stars.isEmpty()){
            for (Sprite star : stars){
                star.draw(batch);
            }
        }
    }
    public void dispose(){
        starTexture.dispose();
    }



}
