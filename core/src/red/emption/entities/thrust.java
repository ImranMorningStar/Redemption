package red.emption.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.FloatArray;

import java.util.ArrayList;

import red.emption.Redemption;
import red.emption.managers.GameKeys;

public class thrust {
    Redemption redemption;
//    FloatArray array = new FloatArray(36);
    ArrayList<Float> array = new ArrayList<>();
//    float x=0,y=0,x1=0,y1=0,x2=0,y2=0;

    float x,y,x1,x2,y1,y2;
    float angle;
    float width;
    float height;
    float maxLen = 120f;

    ShaderProgram s;
    Mesh mesh;
    String vert,frag;


    public thrust(Redemption red){
        this.redemption = red;

        mesh = new Mesh(true,20,0,
                new VertexAttribute(VertexAttributes.Usage.Position
                        ,2,"a_position") ,
                new VertexAttribute(VertexAttributes.Usage.TextureCoordinates
                        ,2,"a_texCoord")
        );


        vert = redemption.loader.vert;
        frag = redemption.loader.frag;
        s= new ShaderProgram(vert,frag);
        width = 15f;

    }

    public  void createThrust(Matrix4 matrix4){

        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA,GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glActiveTexture(GL30.GL_TEXTURE0);
        // Gdx.gl30.glActiveTexture(GL30.GL_TEXTURE0);
        redemption.loader.th.bind(0);
        s.bind();
        s.setUniformi("u_texture",0);
        s.setUniformMatrix("u_projTrans",matrix4);
//        if (GameKeys.isDown(GameKeys.SPACE) &&array.size()>=4){
            mesh.setVertices(getVer());
            mesh.render(s,GL30.GL_TRIANGLE_STRIP);



    }
    public float[] getVer(){
        createVer();
        if (array.size()>36){
            int r = array.size()-36;
            for (int i =0;i<r;i++){
                array.remove(i);
            }

        }
        int pts = array.size()*2;
        int gg = (pts/8)-1;
        float [] realVert = new float[pts];
        for (int i = 0; i< gg+1;i++){
            realVert[i*8]=array.get(i*4);
            realVert[(i*8)+1]=array.get((i*4)+1);
            realVert[(i*8)+2]=i/((float)(gg));
            realVert[(i*8)+3]=1;
            realVert[(i*8)+4]=array.get((i*4)+2);
            realVert[(i*8)+5]=array.get((i*4)+3);
            realVert[(i*8)+6]=i/((float)(gg));
            realVert[(i*8)+7]=0;

        }


        for (float f : realVert){
            System.out.println(f);

        }
        return realVert;
//        createVer();
//        return array.toArray();
    }

    public void update(float x,float y , float angle){
//
//        if (GameKeys.isDown(GameKeys.SPACE)){
//            createVer();
////            System.out.println(array.get(0)+"\n"+array.get(1)+"\n"+array.get(array.size-1));
//        }else {array.clear();}
        this.x=x;
        this.y=y;
        this.angle=angle;
        while (this.angle>360){
            this.angle=this.angle-360;
        }
        while (this.angle<0){
            this.angle=this.angle+360;
        }
//        System.out.println(this.angle);
//        x1=x+(width* MathUtils.cos((angle*MathUtils.degreesToRadians)));
//        y1=y+(width* MathUtils.sin((angle*MathUtils.degreesToRadians)));
//        x2=x-(width* MathUtils.cos((angle*MathUtils.degreesToRadians)));
//        y2=y-(width* MathUtils.sin((angle*MathUtils.degreesToRadians)));
        x1 = x+15f;
        y1=y2=y;
        x2=x-15f;


        if (array.isEmpty()){
            array.add(x1);
            array.add(y1);
            array.add(x2);
            array.add(y2);
        }


        createVer();
//        System.out.println(x+"    "+x1);

    }

    public void createVer(){
       if (array.size()>=4) {
           if (y2 != array.get(array.size() - 1) ||
                   x2 != array.get(array.size() - 2) ||
                   y1 != array.get(array.size() - 3) ||
                   x1 != array.get(array.size() - 4)
           ) {

               array.add(x1);
               array.add(y1);
               array.add(x2);
               array.add(y2);

           }
       }


    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    public  void getAngle(float rad){
        this.angle = rad;
    }
    public void getWidth(float spw){
        this.width = spw;
    }
    public  void getHeight(float sph){
        this.height = sph;
    }


}
