/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.snakeandladdergame;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Vivek
 */
public class Board {
    private final int WINNING_POSITION = 100;
    private boolean gameIsEnded = false;
    private int numOfPlayers;
    private Player[] players;
    private static HashMap<Integer,Integer> snakes;
    private static HashMap<Integer,Integer> ladders;
   
    Scanner sc = new Scanner(System.in);
    
    public Board(int numOfPlayers){
        this.numOfPlayers = numOfPlayers;
    }
    
    
    static {
        snakes = new HashMap<> ();
        snakes.put(16, 6);
        snakes.put(47, 26);
        snakes.put(49, 11);
        snakes.put(56, 53);
        snakes.put(62, 19);
        snakes.put(64, 60);
        snakes.put(87, 24);
        snakes.put(93, 73);
        snakes.put(95, 75);
        snakes.put(98, 78);
        
        ladders = new HashMap<>();
        
        ladders.put(12, 38);
        ladders.put(4, 14);
        ladders.put(9, 31);
        ladders.put(21, 42);
        ladders.put(28, 84);
        ladders.put(36, 44);
        ladders.put(51, 67);
        ladders.put(71, 91);
        ladders.put(80, 100); 
    }
    
    public int getDiceRoll() {
     return Dice.roll();   
    }
    
    public void setPlayers(Player[] players){
        this.players = players;
    }
    
    public int movePlayer(Player player, int position){
        int nextPosition;
        if(snakes.containsKey(position)){
            nextPosition = snakes.get(position); 
        }
        else if(ladders.containsKey(position)){
            nextPosition = ladders.get(position);
        }
        else{
            nextPosition = position;
        }
        return nextPosition;
    }
  
    
    public void startGame() {
        int currentPlayerIndex = 0;
        boolean issix;
        int diceRoll;
        
        while (!gameIsEnded) {
            Player currentPlayer = players[currentPlayerIndex];
            System.out.println("It's " + currentPlayer.getName() + "'s turn. Press enter to roll the dice.");
            sc.nextLine();
            
            issix = false;
            diceRoll = getDiceRoll();
            if(diceRoll == 6){
                issix = true;
            }
            
            int previousPosition =  currentPlayer.getPosition();
            int newPosition = previousPosition + diceRoll;
            //currentPlayer.setPosition(newPosition);
            
            
            if (newPosition > WINNING_POSITION) {
                System.out.println("You need " + (WINNING_POSITION - currentPlayer.getPosition()) + " to win. Keep trying!");
            } else {
                currentPlayer.setPosition(newPosition);
                System.out.println(currentPlayer.getName() + " rolled a " + diceRoll + " and moved from " +
                        previousPosition + " to " + newPosition);
                
               
                if (newPosition == WINNING_POSITION) {
                    System.out.println(currentPlayer.getName() + " wins!");
                    gameIsEnded = true;
                    break;
                }
                Integer snake = snakes.get(newPosition);
                if(snake != null){
                    currentPlayer.setPosition(snake);
                    System.out.println("Oops! " + currentPlayer.getName() + " got bitten by a snake and moved back to " +
                            snake);
                }
                
                Integer ladder = ladders.get(newPosition);
                if(ladder != null){
                    currentPlayer.setPosition(ladder);
                      System.out.println("Yay! " + currentPlayer.getName() + " found a ladder and climbed up to " +
                            ladder);
                    if (ladder == WINNING_POSITION) {
                    System.out.println(currentPlayer.getName() + " wins!");
                    gameIsEnded = true;
                    break;
                        }
                    }
                }
                if(issix){
                    System.out.println(currentPlayer.getName()+" rolled a six and can roll once more.");
                    currentPlayerIndex = currentPlayerIndex;
                }else{
                currentPlayerIndex = (currentPlayerIndex + 1) % numOfPlayers;
                }
                //draw the board with new Positions
                drawboard(players);
        }   
    }
    
    
    public char getPlayerIcon(Player[] players, int Position) {
        for (Player p : players){
            if(p.getPosition() == Position) {
                return p.getName().charAt(0);
            }
        }
        return ' ';
    }
    
    
    public void drawboard(Player[] players){
	    int i ,j,k;
            char icon;
	    System.out.println();
	    System.out.println("*****************************");
		for(i = 100; i>0;i-=10){
			for(j = i ; j>i-10;j--){
                                icon = getPlayerIcon(players,j);
                                if(icon != ' '){
                                    System.out.print(icon+"    ||  ");
                                }
				else if(snakes.get(j) != null){
					System.out.print("S"+snakes.get(j)+"  ||  ");
				}
				else if(ladders.get(j) != null){
					System.out.print("L"+ladders.get(j)+"  ||  ");
                                }
                                else{
                                    System.out.print(j + "   ||  ");
                                }
			}
			System.out.println();
                        
			for(k = j-10+1;k<=j;k++){
				icon = getPlayerIcon(players,k);
                                if(icon != ' '){
                                    System.out.print(icon+"   ||  ");
                                }
				else if(snakes.get(k) != null){
					System.out.print("S"+snakes.get(k)+"  ||  ");
				}
				else if(ladders.get(k) != null){
					System.out.print("L"+ladders.get(k)+"  ||  ");
				}
                                else{
                                    System.out.print(k + "   ||  ");
                                }
                        }
                        System.out.println();
                        i = k-1;
                }
                System.out.println();
		System.out.println("*****************************");
    }

}
			
    
    

