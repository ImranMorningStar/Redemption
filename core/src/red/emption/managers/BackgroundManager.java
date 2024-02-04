package red.emption.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import red.emption.Cnst;
import red.emption.Redemption;
import red.emption.entities.Bg;

public class BackgroundManager {
    ArrayList<Bg> bgs = new ArrayList<>(9);
    int w = (int) Cnst.width;
    public BackgroundManager(Redemption red){
        for (int i = 0; i < 9 ; i ++){
            bgs.add(new Bg(red));
        }
        if (bgs != null) {
            bgs.get(0).update(-w, -w);
            bgs.get(1).update(0, -w);
            bgs.get(2).update(w, -w);
            bgs.get(3).update(-w, 0);
            bgs.get(4).update(0, 0);
            bgs.get(5).update(w, 0);
            bgs.get(6).update(-w, w);
            bgs.get(7).update(0, w);
            bgs.get(8).update(w, w);
        }

    }
    public void manageBG(int x,int y){
    if (bgs.get(4).getOriginX()>x){
        bgs.set(2,bgs.get(1));
        bgs.set(5,bgs.get(4));
        bgs.set(8,bgs.get(7));

        bgs.set(1,bgs.get(0));
        bgs.set(4,bgs.get(3));
        bgs.set(7,bgs.get(6));

        bgs.get(0).update(bgs.get(0).getOriginX()-w,bgs.get(0).getOriginY());
        bgs.get(3).update(bgs.get(3).getOriginX()-w,bgs.get(3).getOriginY());
        bgs.get(6).update(bgs.get(6).getOriginX()-w,bgs.get(6).getOriginY());

        System.out.println(bgs.get(4).getOriginX()+" >x ");
    }
    else if (bgs.get(4).getOriginX()+w<x){
        bgs.set(0,bgs.get(1));
        bgs.set(3,bgs.get(4));
        bgs.set(6,bgs.get(7));

        bgs.set(1,bgs.get(2));
        bgs.set(4,bgs.get(5));
        bgs.set(7,bgs.get(8));

        bgs.get(2).update(bgs.get(2).getOriginX()+w,bgs.get(2).getOriginY());
        bgs.get(5).update(bgs.get(5).getOriginX()+w,bgs.get(5).getOriginY());
        bgs.get(8).update(bgs.get(8).getOriginX()+w,bgs.get(8).getOriginY());

        System.out.println(bgs.get(4).getOriginX()+" <x ");
    }
    else if (bgs.get(4).getOriginY()>y){
        bgs.set(6,bgs.get(3));
        bgs.set(7,bgs.get(4));
        bgs.set(8,bgs.get(5));

        bgs.set(3,bgs.get(0));
        bgs.set(4,bgs.get(1));
        bgs.set(5,bgs.get(2));

        bgs.get(0).update(bgs.get(0).getOriginX(),bgs.get(0).getOriginY()-w);
        bgs.get(1).update(bgs.get(1).getOriginX(),bgs.get(1).getOriginY()-w);
        bgs.get(2).update(bgs.get(2).getOriginX(),bgs.get(2).getOriginY()-w);

        System.out.println(bgs.get(4).getOriginX()+" >y");
    }
    else if (bgs.get(4).getOriginY()+w<y){
        bgs.set(0,bgs.get(3));
        bgs.set(1,bgs.get(4));
        bgs.set(2,bgs.get(5));

        System.out.println(bgs.get(3).getOriginX()+" >x ");
        System.out.println(bgs.get(0).getOriginX()+" >x ");

        bgs.set(3,bgs.get(6));
        bgs.set(4,bgs.get(7));
        bgs.set(5,bgs.get(8));

        bgs.get(6).update(bgs.get(6).getOriginX(),bgs.get(0).getOriginY()+w);
        bgs.get(7).update(bgs.get(7).getOriginX(),bgs.get(1).getOriginY()+w);
        bgs.get(8).update(bgs.get(8).getOriginX(),bgs.get(2).getOriginY()+w);

        System.out.println(bgs.get(4).getOriginX()+" <y ");
    }
    }

    public void draw(SpriteBatch batch){
        for (Bg bg : bgs){
            bg.drawStars(batch);
        }
    }

    public void dispose(){
        for (Bg bg : bgs){
            bg.dispose();
        }
    }


}
