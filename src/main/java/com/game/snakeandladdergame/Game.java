/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.snakeandladdergame;

import java.util.Scanner;

/**
 *
 * @author Vivek
 */
public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
   
        // Array of players 
        System.out.println("ENTER THE NUMBER OF PLAYERS ");
        int numPlayers = sc.nextInt();
        // create a board object for the game
        Board board = new Board(numPlayers);
        
        Player[] players = new Player[numPlayers];
        for (int i=0;i<numPlayers;i++){
            System.out.println("Enter the name of the player " + i+1 +" : ");
            String name = sc.next();
            players[i] = new Player(name);
        }
        // set players names and starting positions on the board
        board.setPlayers(players);
        
        board.startGame();
        sc.close();
    }
   
    
}
