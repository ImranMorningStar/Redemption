package red.emption.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import red.emption.Cnst;
import red.emption.Hud.Hud;
import red.emption.Redemption;
import red.emption.entities.Background;
import red.emption.entities.Bg;
import red.emption.entities.Ship;
import red.emption.managers.BackgroundManager;
import red.emption.managers.GameKeys;
import red.emption.noise.SimplexNoise;
import red.emption.shade;

public class GameScreen implements Screen {
    SimplexNoise simplexNoise;
    Redemption redemption;
    BackgroundManager backgroundManager;
    Ship ship;
    Hud hud;
    shade Shade;
    SpriteBatch batch;
    OrthographicCamera cam;
    Viewport view;
    float w = Cnst.width;
    float h = Cnst.height;

    ShaderProgram s;
    Mesh mesh;
    float[] ver = new  float[16];

    //tets
    Texture tex;


    public GameScreen(Redemption redemption){
        this.redemption=redemption;

    }



    @Override
    public void show() {

        ship = new Ship(redemption);
        Shade = new shade(redemption);


        tex = redemption.loader.th;
        cam = new OrthographicCamera();
        view = new FitViewport(w,h,cam);
        cam.position.set(w/2,h/2,0);
        cam.update();
        s = new ShaderProgram(redemption.loader.vert,redemption.loader.frag);

        batch = new SpriteBatch();
        backgroundManager = new BackgroundManager(redemption);
        hud = new Hud(redemption,batch);


//        Gdx.input.setInputProcessor(hud.getStage());





    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        update(delta);

        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA,GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glActiveTexture(GL30.GL_TEXTURE0);
//        Gdx.gl30.glActiveTexture(GL30.GL_TEXTURE0);
        tex.bind();
//        redemption.loader.shipTex.bind(0);
        s.bind();
        s.setUniformi("u_texture",0);
        s.setUniformMatrix("u_projTrans",cam.combined);
        ship.drawThrust(s);
//        System.out.println((float) simplexNoise.noise(cam.position.x,cam.position.y));
//        cam.update();
//        ship.update(delta);

        batch.begin();
        batch.setProjectionMatrix(cam.combined);
        backgroundManager.draw(batch);
//        batch.draw(tex,0,0,w,h);
        ship.draw(batch);

//        float xx = 0,yy=0;
//        if (ship.y>yy+h){
//            bg.createTex(ship.x, ship.y);
//            yy=yy+h;
//
//        }
        //batch.draw(bg.getTex(),ship.x,ship.y);

        batch.end();

//        batch.setProjectionMatrix(hud.getStage().getCamera().combined); //set the spriteBatch to draw what our stageViewport sees
//        hud.getStage().act(delta); //act the Hud
//        hud.getStage().draw();
//


    }

    private void update(float dt) {
        if(GameKeys.isPressed(GameKeys.SPACE)){
            System.out.println(ship.getX()+" ");
        }
        handleinput();
        ship.update(dt);
        cam.position.set(MathUtils.lerp(cam.position.x,ship.x,2*dt),MathUtils.lerp(cam.position.y,ship.y,2*dt),0);
        cam.update();
        backgroundManager.manageBG((int)ship.getX(), (int) ship.getY());
//        if ((w/2)+ship.getX()>bg.getOriginX()+2*w){
//            bg.update((int) (bg.getOriginX()+w), (int) (bg.getOriginY()));
//        }

    }

    private void handleinput() {
        ship.setLeft(GameKeys.isDown(GameKeys.LEFT));
        ship.setRight(GameKeys.isDown(GameKeys.RIGHT));
        ship.setUp(GameKeys.isDown(GameKeys.UP));
//        ship.setUp(Gdx.input.isTouched());
    }


    @Override
    public void resize(int width, int height) {
    view.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundManager.dispose();
        s.dispose();

    }
}
