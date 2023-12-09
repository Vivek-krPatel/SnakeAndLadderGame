/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.snakeandladdergame;

import java.util.Random;

/**
 *
 * @author Vivek
 */
public class Dice {
    private static Random random = new Random();
    
    public static int roll() {
        return random.nextInt(6)+1; 
    }    
    
}
