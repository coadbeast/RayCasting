package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Particle {
    Vector2 pos;
    Vector2 dir;
    public static Ray[] rays = new Ray[1000];

    Particle(){
        pos = new Vector2(800/2, 800/2);
        for (int i = 0; i < rays.length; i += 1){
            rays[i] = new Ray(pos, i * (45f/rays.length) - 22.5f);
        }
        dir = new Vector2();
    }

    public void setAngle(float angle){
        for (int i = 0; i < rays.length; i += 1){
            rays[i] = new Ray(pos, i * (45f/rays.length) - 22.5f + angle);
        }
        dir = new Vector2(1,0).setAngle(angle);
    }

    public void look(Boundary[] walls){
        rays[0].nextPos = 0;
        for(int i = 0; i < rays.length; i++){
            rays[i].getScreenPos(walls);
        }
    }

    public void render(Boundary[] walls){
        for (Ray r: rays) {
            r.render(walls);
        }
    }

    public void update(){
        pos = pos.add(dir);
    }

    private float distance(Vector2 object1, Vector2 object2){
        return (float)(Math.pow((object2.x - object1.x), 2) + Math.pow((object2.y - object1.y), 2));
    }


    public boolean collide (){
        if (rays[rays.length/2 - 1].getLengthOfRay() < 10){
            return false;
        }
        return true;
    }
}
