package red.emption.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;

import red.emption.noise.ValueNoise;

public class asteroid
{
    ValueNoise noise;
    int seed;
    double noiseVal;
    float maxRad=200;
    float[] verts;
    int x,y;
    PolygonRegion region;
    PolygonSprite poly;
    PolygonSpriteBatch batch;
    TextureRegion texturere;
    Texture  texture;
    int numVerts;

    private static final float minrad = 100;
    public asteroid()
    {
        noiseVal=.5;
        texture = new Texture("thrust/my12.png");
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //texture.getTextureData().prepare();

        texturere = new TextureRegion(texture);
        batch=new PolygonSpriteBatch();
        float[] v = createAsteroid();
        region = new PolygonRegion(texturere, v, new EarClippingTriangulator().computeTriangles(v).toArray());

        poly=new PolygonSprite(region);
//        poly.scale(1/50f);
        //this.texturere=texe;

    }

    public void forPoint(int x,int y)
    {
        this.x=x;
        this.y=y;
        noise=new ValueNoise(x|y);
        noiseVal=noise.generateNoise(x,y);
//        numVerts=(int)(noiseVal*10)+((int)(noiseVal-(int)noiseVal)*10);
        numVerts=10;
//        while(numVerts<3){
//            numVerts+=10;
//        }
        if(numVerts>=3)
        {
            float[] v = createAsteroid();
            region = new PolygonRegion(texturere, v, new EarClippingTriangulator().computeTriangles(v).toArray());

            poly.setRegion(region);
            poly.setOrigin(x,y);


            //draw();
        }
    }
//    public void setEg(){
//        poly.setRegion(region);
//    }

    public float[] createAsteroid()
    {

        float angle;
        float theta=360f/(float)numVerts;
        verts=new float[numVerts*2];
        for(int i=0;i<numVerts;i++)
        {
            angle=i*theta;
            noise=new ValueNoise(x|y+i*i);
            float scale=//MathUtils.random(0,1);
                    (float)noise.generateNoise((i+10)*19,(i+13)*8);
            if(scale<0){scale=-scale;}
            verts[i*2]=x+MathUtils.cosDeg(angle)*(minrad+( scale*(maxRad-minrad)));
            verts[(i*2)+1]=y+MathUtils.sinDeg(angle)*(minrad+(scale*(maxRad-minrad)));

        }


        return verts;
    }
    public void begin(Matrix4 mat)
    {
        batch.begin();
        batch.setProjectionMatrix(mat);
    }
    public void end(){
        batch.end();
    }
    public void draw(){

        poly.rotate(1);
        poly.translateX(1*MathUtils.cosDeg(poly.getRotation()));
        poly.translateY(1*MathUtils.sinDeg(poly.getRotation()));
        // poly.setPosition(0,0);
        poly.draw(batch);
    }





}