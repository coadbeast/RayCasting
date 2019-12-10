package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.sun.tools.javac.util.ArrayUtils;
import sun.plugin2.message.GetAuthenticationMessage;
import java.util.Arrays;
import java.util.Random;

public class Maze {
    Boundary[][] horisontal = new Boundary[40][40];
    Boundary[][] vertical = new Boundary[40][40];
    boolean[][] checked = new boolean[40][40];
    int[] genPos = {0,0};

    Maze(){
        for (int x = 0; x < horisontal.length; x++){
            for (int y = 0; y < horisontal[x].length; y++){
                horisontal[x][y] = new Boundary(x*40,y*40,x*40, y*40 + 40);
            }
        }
        for (int x = 0; x < vertical.length; x++){
            for (int y = 0; y < vertical[x].length; y++){
                vertical[x][y] = new Boundary(x*40,y*40,x*40 + 40, y*40);
            }
        }
        checked[0][0] = true;
    }
    public void generate(){
        int nrOfT = 0;
        for (int x = 0; x < vertical.length; x++){
            for (int y = 0; y < vertical[x].length; y++){
                if (checked[x][y] == false) {
                    if (x < 39) {
                        if (checked[x + 1][y] == true) {
                            nrOfT += 1;
                        }
                    }
                    if (x > 0) {
                        if (checked[x - 1][y] == true) {
                            nrOfT += 1;
                        }
                    }
                    if (y < 39) {
                        if (checked[x][y + 1] == true) {
                            nrOfT += 1;
                        }
                    }
                    if (y > 0) {
                        if (checked[x][y - 1] == true) {
                            nrOfT += 1;
                        }
                    }
                }
            }
        }
        if (nrOfT > 0) {
            int[][] positions = new int[nrOfT][2];
            int p = 0;
            for (int x = 0; x < vertical.length; x++) {
                for (int y = 0; y < vertical[x].length; y++) {
                    if (checked[x][y] == false) {
                        if (x < 19) {
                            if (checked[x + 1][y] == true) {
                                positions[p][0] = x;
                                positions[p][1] = y;
                                p += 1;
                            }
                        }
                        if (x > 0) {
                            if (checked[x - 1][y] == true) {
                                positions[p][0] = x;
                                positions[p][1] = y;
                                p += 1;
                            }
                        }
                        if (y < 19) {
                            if (checked[x][y + 1] == true) {
                                positions[p][0] = x;
                                positions[p][1] = y;
                                p += 1;
                            }
                        }
                        if (y > 0) {
                            if (checked[x][y - 1] == true) {
                                positions[p][0] = x;
                                positions[p][1] = y;
                                p += 1;
                            }
                        }
                    }
                }
            }

            Random rand = new Random();
            int r = rand.nextInt(nrOfT);
            int x = positions[r][0];
            int y = positions[r][1];
            int b = rand.nextInt(4);
            if (checked[x][y] == false && b == 0) {
                if (x < 39) {
                    if (checked[x + 1][y] == true) {
                        horisontal[x + 1][y] = new Boundary(0, 0, 0, 0);
                        checked[x][y] = true;
                    }
                }
            }
            if (checked[x][y] == false && b == 1) {
                if (x > 0) {
                    if (checked[x - 1][y] == true) {
                        horisontal[x][y] = new Boundary(0, 0, 0, 0);
                        checked[x][y] = true;
                    }
                }
            }
            if (checked[x][y] == false && b == 2) {
                if (y < 39) {
                    if (checked[x][y + 1] == true) {
                        vertical[x][y + 1] = new Boundary(0, 0, 0, 0);
                        checked[x][y] = true;
                    }
                }
            }
            if (checked[x][y] == false && b == 3) {
                if (y > 0) {
                    if (checked[x][y - 1] == true) {
                        vertical[x][y] = new Boundary(0, 0, 0, 0);
                        checked[x][y] = true;
                    }
                }
            }

        }
    }

    public void render(){
        for (int x = 0; x < horisontal.length; x++){
            for (int y = 0; y < horisontal[x].length; y++){
                horisontal[x][y].render();
                vertical[x][y].render();
            }
        }
    }

    public Boundary[] getHorisontalAndVertical() {
        Boundary[] all = new Boundary[3200];
        int pos = 0;
        for (Boundary[] b: vertical){
            for (Boundary b2: b) {
                all[pos] = b2;
                pos += 1;
            }
        }
        for (Boundary[] b: horisontal){
            for (Boundary b2: b) {
                all[pos] = b2;
                pos += 1;
            }
        }
        for (Boundary b : all){
            System.out.println(b.getX1());
        }
        return all;
    }
}
