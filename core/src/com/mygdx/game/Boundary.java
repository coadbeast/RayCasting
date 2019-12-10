package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Boundary {
    private Vector2 a;
    private Vector2 b;

    Boundary(int x1, int y1,int x2, int y2){
        a = new Vector2(x1, y1);
        b = new Vector2(x2,y2);
    }

    public float getX1(){
        return a.x;
    }
    public float getY1(){
        return a.y;
    }
    public float getX2(){
        return b.x;
    }
    public float getY2(){
        return b.y;
    }

    public void render(){
        Window.shapeRenderer.line(a,b);
    }
}
