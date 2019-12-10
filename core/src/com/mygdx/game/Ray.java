package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Ray {
    public static float nextPos = 0;
    private float d;
    Vector2 pos;
    Vector2 dir;

    Ray(Vector2 pos, float angle){
        this.pos = pos;
        dir = new Vector2(20, 0).setAngle(angle);
    }

    public void look(float x, float y, Boundary[] walls){

    }

    public Vector2 cast(Boundary wall){
        final float x1 = wall.getX1();
        final float y1 = wall.getY1();
        final float x2 = wall.getX2();
        final float y2 = wall.getY2();

        final float x3 = pos.x;
        final float y3 = pos.y;
        final float x4 = pos.x + dir.x;
        final float y4 = pos.y + dir.y;

        final float den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 -x4);

        if (den == 0){
            return null;
        }

        final float t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
        final float u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;

        if (t > 0 && t < 1 && u > 0) {
            Vector2 pt = new Vector2();
            pt.x = x1 + t * (x2 - x1);
            pt.y = y1 + t * (y2 - y1);
            return pt;
        } else {
            return null;
        }
    }

    public void getScreenPos(Boundary[] walls){
        Vector2 line = new Vector2();
        float record = 8000;

        Vector2 closest = null;
        for (Boundary wall: walls) {
            final Vector2 pt = cast(wall);
            if (pt != null) {
                final float d = Vector2.dst(pos.x, pos.y, pt.x, pt.y);
                if (d < record){
                    record = d;
                    closest = pt;
                }
            }
        }
        if (closest != null){
            d = Vector2.dst(pos.x, pos.y, closest.x, closest.y);
            double a = Math.toRadians(dir.angle() - Window.angle);
            d *= Math.cos(a);

            line = new Vector2(closest.x, closest.y);
            line.sub(pos);
            line.setLength(100);
            line.add(pos);

            float height = (int)(((0.5 * 800) / d) * 40);//(float)  300 - (d)*2;
            if (height < 0){
                height = 0;
            }
            final float x1 = nextPos * (800f/Particle.rays.length);
            final float y1 = (800 - height)/2;//(800 - height)/2;
            final float width = (800f/Particle.rays.length);
            Window.shapeRenderer.setColor((800 - 2*d)/1000,(800 - 2*d)/1000,(800 - 2*d)/1000,100);
            Window.shapeRenderer.rect(x1, y1, width, height);
        }
        nextPos += 1;
    }

    public void render(Boundary[] walls){
        float record = 8000;
        Vector2 closest = null;
        for (Boundary wall: walls) {
            final Vector2 pt = cast(wall);
            if (pt != null) {
                final float d = Vector2.dst(pos.x, pos.y, pt.x, pt.y);
                if (d < record){
                    record = d;
                    closest = pt;
                }
            }
        }
        if (closest != null){
            Window.shapeRenderer.line(pos.x, pos.y, closest.x, closest.y);
        }
    }


    public float getLengthOfRay(){
        return d;
    }
}
