package red.emption.Hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import red.emption.Cnst;
import red.emption.Redemption;


public class Hud {
    Stage stage;
    FitViewport stageViewport;
    Button btup,btright,btleft;
    boolean up=false;
    boolean right=false;
    boolean left=false;
    Redemption red;
    Touchpad touchpad;

    public Hud(Redemption re,SpriteBatch batch){
        this.red=re;
        stageViewport=new FitViewport(Cnst.width,Cnst.height);
        stage=new Stage(stageViewport,batch);
//        btup=createButton(red.loader.btup,Cnst.width/2,200);
//        btright=createButton(red.loader.btrig,(Cnst.width/2)+500,200);
//        btleft=createButton(red.loader.btlef,(Cnst.width/2)-500,200);
//
//        btup.addListener(new InputListener(){
//            @Override
//            public void touchUp (InputEvent event, float x, float y, int pointer, int butto) {
//                //outputLabel.setText("Press a Button");
//                up=false;
//            }
//            @Override
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int butto) {
//                up=true;
//                //outputLabel.setText("Pressed Button");
//
//                return true;
//            }
//
//        });
//
//
//
//        btright.addListener(new InputListener(){
//            @Override
//            public void touchUp (InputEvent event, float x, float y, int pointer, int butto) {
//                //outputLabel.setText("Press a Button");
//                right=false;
//            }
//            @Override
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int butto) {
//                right=true;
//                //outputLabel.setText("Pressed Button");
//
//                return true;
//            }
//
//        });
//
//
//
//        btleft.addListener(new InputListener(){
//            @Override
//            public void touchUp (InputEvent event, float x, float y, int pointer, int butto) {
//                //outputLabel.setText("Press a Button");
//                left=false;
//            }
//            @Override
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int butto) {
//                left=true;
//                //outputLabel.setText("Pressed Button");
//
//                return true;
//            }
//
//        });
//
//

        touchpad=createTouchpad();
        stage.addActor(touchpad);
//        stage.addActor(btup);
//        stage.addActor(btright);
//        stage.addActor(btleft);


    }

    private Button createButton(Texture ButtonTexture,float x, float y) {
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(ButtonTexture));
        Button.ButtonStyle btnStyle = new Button.ButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable);
        Button button = new Button(btnStyle);
        button.setBounds(x, y, 200, 200);
        return button;
    }
    private Touchpad createTouchpad() {
        Skin touchpadSkin = new Skin();
        touchpadSkin.add("touchBackground", new Texture("ui/analog_stick_bg.png"));
        touchpadSkin.add("touchKnob", new Texture("ui/analog_stick_knob.png"));
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = touchpadSkin.getDrawable("touchBackground");
        touchpadStyle.knob = touchpadSkin.getDrawable("touchKnob");
        touchpad = new Touchpad(1, touchpadStyle);
        touchpad.setBounds(Cnst.width-500, 75, 50, 50);
        //   touchpad.setResetOnTouchUp(false);

        return touchpad;
    }

    public Stage getStage(){
        return this.stage;
    }

}
