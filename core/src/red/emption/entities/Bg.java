package red.emption.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import red.emption.Cnst;
import red.emption.Redemption;
import red.emption.noise.NoiseGeneretor;
import red.emption.noise.SimplexNoise;
import red.emption.noise.ValueNoise;

public class Bg {


    private final Texture starTexture;
    ValueNoise noise;
//    lehmer le;
    int OriginX=0;
    int OriginY=0;
    private final ArrayList<Sprite> stars = new ArrayList<>();
    private final int w = (int) Cnst.width;
    // NoiseGeneretor generetor;

    public Bg(Redemption red){
        starTexture = red.loader.starTex;

    }



    //    generateStars only generates if OriginX+w > OriginX || OriginY+w > OriginY
//    to-do make methods to generate for OriginX+w " < " OriginX || OriginY+w " < " OriginY
    public void generateStars_greaterX(){

    }
    public void generateStars_greaterY(){

    }
    public void generateStars_lessX(){

    }
    public void generateStars_lessY(){

    }
    public void generateStars(){
        for (int i = OriginX;i < OriginX+(w);i+=w/32){
            for (int j = OriginY; j < OriginY+w; j+=w/32){
                noise= new ValueNoise(i<<16|j);
                //double n = SimplexNoise.noise(i*100,j/200); //(i*4,j/100)
                //double op = (i*i*i+j*j*j)%100/100.0;
                double op = noise.generateNoise(i*1,j*1);
                //le=new lehmer(i,j);
                //  double n = ImprovedNoise.noise(i*.0001,j*.0001,.001);
//                double n = NoiseGeneretor.noise(i*0.1,j*0.1); //n>0.5

//             if(le.rndInt(0,50)==0){
//                 Sprite star = new Sprite(starTexture);
////                    star.setColor((float)n*(float)n*20,(float)n*30f,(float)n*20,1);
//                 star.setPosition(i,j);
//                 star.setColor(Color.PINK);
//                 stars.add(star);
//             }
                if (op>.98){
                    Sprite star = new Sprite(starTexture);
//                    star.setColor((float)n*(float)n*20,(float)n*30f,(float)n*20,1);
                    star.setPosition(i,j);
                    //star.setPosition(i+((w*(float)(op))/20f),j+((w*(float)(op))/32f));
                    stars.add(star);
                }
//                if(op>0.2&&op<0.3){
//                    Sprite star = new Sprite(starTexture);
////                    star.setColor((float)n*(float)n*20,(float)n*30f,(float)n*20,1);
//                    star.setPosition(i,j);
//                    //star.setPosition(i+((w*(float)(op)*3f)/32f),j-((w*(float)(op)*3f)/20f));
//                    star.setColor(Color.CYAN);
//                    stars.add(star);
//                }


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
//        new Thread(new Runnable(){
//
//                @Override
//                public void run() {
        generateStars();

//                }
//           }).start();

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