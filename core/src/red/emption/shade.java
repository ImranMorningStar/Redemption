package red.emption;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class shade {
    Redemption redemption;
    String vert,frag;
    ShaderProgram shaderProgram;
    public shade(Redemption re){
        this.redemption=re;
        vert=redemption.loader.vert;
        frag = redemption.loader.frag;
        shaderProgram = new ShaderProgram(vert,frag);
    }

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }
}
