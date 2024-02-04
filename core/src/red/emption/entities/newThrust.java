package red.emption.entities;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import java.util.ArrayList;

//    First declare newThrust
//    Setpoints
//    update
//    draw

public class newThrust
{

    Mesh mesh;
    float[] ver,dummyVer;
    boolean shoulDraw=false;

    float x1,x2,y1,y2,memoryAngle,r=69.9213844f,theta=16.6207569f;
    float exhaustVelocity=1000,timer,lifeTime,dumm,moreSpeed;
    float xour,your;
    boolean same=true;

    ArrayList<Float> ax1 = new ArrayList<>();
    ArrayList<Float> ay1 = new ArrayList<>();
    ArrayList<Float> ax2 = new ArrayList<>();
    ArrayList<Float> ay2 = new ArrayList<>();
    ArrayList<Float> aangle = new ArrayList<>();

    boolean move = false;


    public newThrust(){
        mesh = new Mesh(false,200,0,
                new VertexAttribute(VertexAttributes.Usage.Position
                        ,2,"a_position") ,
                new VertexAttribute(VertexAttributes.Usage.TextureCoordinates
                        ,2,"a_texCoord")
        );


        timer=0f;
        lifeTime=2f;
        dumm=18;
        moreSpeed=1;

    }
    public void setVel(float velPer){
        exhaustVelocity=velPer;
    }
    public void setPoints(float x, float y,float angle)
    {
        // will move everything in if(){}
        x1=x+(r*MathUtils.sinDeg( theta+angle));
        x2=x-(r*MathUtils.sinDeg( theta-angle));
        y1=y-(r*MathUtils.cosDeg( theta+angle));
        y2=y-(r*MathUtils.cosDeg( theta-angle));
        memoryAngle=angle;
        if(ax1.isEmpty())ax1.add(x1);
        if(ay1.isEmpty())ay1.add(y1);
        if(ax2.isEmpty())ax2.add(x2);
        if(ay2.isEmpty())ay2.add(y2);
        if(aangle.isEmpty())aangle.add(memoryAngle);
        if(move){

            ax1.add(x1);
            ay1.add(y1);
            ax2.add(x2);
            ay2.add(y2);
            aangle.add(memoryAngle);

            //This doesn"t check for change in direction(angle)



        }
        if(!move){
            timer=lifeTime+1;
            aangle.set(aangle.size()-1,memoryAngle);
            ax1.set(ax1.size()-1,x1);
            ax2.set(ax2.size()-1,x2);
            ay1.set(ay1.size()-1,y1);
            ay2.set(ay2.size()-1,y2);
        }


    }
    private boolean arrayIzGreaterThan0()
    {
        if(aangle.size()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void update(float dt)
    {
//        if(move){
//          aangle.set(0,(float)MathUtils.random(360));
//        }
        timer++;
        tryMoveAndRemove(dt);


        if(arrayIzGreaterThan0())
        {

            tryMove(dt);


            ver=new float[aangle.size()*8];
            for ( int i=0;i<aangle.size();i++)
            {
                ver[i*8]=ax1.get(i);
                ver[ (i*8)+1] = ay1.get(i);
                ver[ (i*8)+2] = (float) i / (float)(aangle.size()-1f) ;
                ver[ (i*8)+3] = 1f;
                ver[( i*8)+4] = ax2.get(i);
                ver[( i*8)+5] = ay2.get(i);
                ver[( i*8)+6] = (float) i / (float)(aangle.size()-1f) ;
                ver[ (i*8)+7] = 0;
            }


            shoulDraw=true;


        }



        // updateArrays();

    }
    public void moo(boolean b){
        this.move=b;
    }

    private void tryMove(float dt) {
        // if(!move)moreSpeed=0;

        //  if(move)

        //  moreSpeed+=0.01f;
        // float dt=dtt+moreSpeed;
        // if(moreSpeed>lifeTime/10){moreSpeed=0;}
        //if(!move){exhaustVelocity=3000;}else{exhaustVelocity=1000;}
        for(int i=0;i<aangle.size()
                -1
        /* -1 is used so all points move except two
         which are attached to ship(rocket)
         */
                ;i++)
        {
            ax1.set(i,ax1.get(i)+(exhaustVelocity*dt*MathUtils.sinDeg(aangle.get(i))));
            ay1.set(i,ay1.get(i)-(exhaustVelocity*dt*MathUtils.cosDeg(aangle.get(i))));
            ax2.set(i,ax2.get(i)+(exhaustVelocity*dt*MathUtils.sinDeg(aangle.get(i))));
            ay2.set(i,ay2.get(i)-(exhaustVelocity*dt*MathUtils.cosDeg(aangle.get(i))));
        }

    }


    private void tryMoveAndRemove(float dt)
    {





        if(timer>lifeTime)
        {

            aangle.remove(0);
            ax1.remove(0);
            ay1.remove(0);
            ax2.remove(0);
            ay2.remove(0);
            timer=0;
        }
        if(aangle.size()>=10)
        {
            aangle.remove(0);
            ax1.remove(0);
            ay1.remove(0);
            ax2.remove(0);
            ay2.remove(0);
        }
//        if(!move){
//            if(dumm>0)
//            {
//                dumm--;}
//            if(dumm==0)dumm=5;
//            if(aangle.size()>dumm){
//                aangle.remove(0);
//                ax1.remove(0);
//                ay1.remove(0);
//                ax2.remove(0);
//                ay2.remove(0);
//            }
//        }


    }
    private void updateArrays()
    {


        if(!same)
        {
            ax1.add(x1);
            ay1.add(y1);
            ax2.add(x2);
            ay2.add(y2);
            aangle.add(memoryAngle);
            same=true;
        }

    }
    public void drawThrust(ShaderProgram shader)
    {



        if(shoulDraw){
            mesh.setVertices(ver);
            mesh.render(shader,5); //GL20.GL_TRIANGLE_STRIP

        }else{
            ////  mesh=null;
        }
    }

}
