package red.emption.FilesLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FileLoader {
    public Texture shipTex,th;
    public String vert,frag;
    public FileLoader(){
        th = new Texture(Gdx.files.internal("thrust/f.png"));
        shipTex = new Texture(Gdx.files.internal("ship/rocket.png"));
        vert = Gdx.files.internal("shaders/vert.glsl").readString();
        frag = Gdx.files.internal("shaders/frag.glsl").readString();

    }

}
