/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.snakeandladdergame;

/**
 *
 * @author Vivek
 */
public class Player {
    private final String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 1;
    }

    public String getName() {
        return this.name;
    }

    public void setPosition (int pos) {
        this.position = pos;
    }

    public int getPosition(){
        return this.position;
    }
}
