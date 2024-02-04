package red.emption.managers;

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
    }
    public void manageBG(int x,int y){
    if (bgs.get(4).getOriginX()>x){
        bgs.set(2,bgs.get(1));
        bgs.set(5,bgs.get(4));
        bgs.set(8,bgs.get(7));

        bgs.set(1,bgs.get(0));
        bgs.set(4,bgs.get(5));
        bgs.set(7,bgs.get(3));

        bgs.get(0).update(bgs.get(0).getOriginX()-w,y);
        bgs.get(3).update(bgs.get(3).getOriginX()-w,y);
        bgs.get(5).update(bgs.get(5).getOriginX()-w,y);
    }
    }



}
