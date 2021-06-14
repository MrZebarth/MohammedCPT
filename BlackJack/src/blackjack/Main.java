/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.*;

/**
 *
 *
 * @author baharoon
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to Blackjack!\n♠*♣*♥*♦*♠*♣*♥*♦*♠*♣*♥*♦\n\nPress [Enter] to continue.");
        in.nextLine();

        System.out.println("You have 1000 points. If you win a round, you get 100 points. \nIf you lose a round, you lose 100 points. If you lose 10 times in a row, you lose. \nPress [Enter] to continue.");
        in.nextLine();

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        Deck playerCards = new Deck();
        Deck dealerCards = new Deck();

        int points = 1000;

        while (points > 0) {

            boolean endRound = false;

            System.out.println("Dealing....\n");

            playerCards.draw(playingDeck);
            playerCards.draw(playingDeck);

            dealerCards.draw(playingDeck);
            dealerCards.draw(playingDeck);

            while (true) {
                System.out.println("Your hand:" + playerCards.toString());
                System.out.println("Your hand value is: " + playerCards.cardsValue() + "\n");
                System.out.println("Dealer hand: " + dealerCards.getCard(0).toString() + " and (hidden)\n");
                System.out.println("Would you like to [1]Hit or [2]Stand?");
                int response = in.nextInt();

                if (response == 1) {
                    playerCards.draw(playingDeck);
                    System.out.println("You draw a:" + playerCards.getCard(playerCards.deckSize() - 1).toString() + "\n");

                    if (playerCards.cardsValue() > 21) {
                        System.out.println("Bust. Currently valued at: " + playerCards.cardsValue() + "\n");
                        points -= 100;
                        System.out.println("You have " + points + " points.\n");
                        endRound = true;
                        break;
                    }
                }

                if (response == 2) {
                    break;
                }

            }

            System.out.println("Dealer Cards:" + dealerCards.toString() + "\n");

            if ((dealerCards.cardsValue() > playerCards.cardsValue()) && endRound == false) {
                System.out.println("Dealer beats you " + dealerCards.cardsValue() + " to " + playerCards.cardsValue() + "\n");
                points -= 100;
                System.out.println("You have " + points + " points.\n");
                endRound = true;
            }

            while ((dealerCards.cardsValue() < 17) && endRound == false) {
                dealerCards.draw(playingDeck);
                System.out.println("Dealer draws: " + dealerCards.getCard(dealerCards.deckSize() - 1).toString());
            }

            System.out.println("Dealers hand value: " + dealerCards.cardsValue() + "\n");

            if ((dealerCards.cardsValue() > 21) && endRound == false) {
                System.out.println("Dealer busts. You win!\n");
                points += 100;
                System.out.println("You have " + points + " points.\n");
                endRound = true;
            }

            if ((dealerCards.cardsValue() == playerCards.cardsValue()) && endRound == false) {
                System.out.println("Push.\n");
                System.out.println("You have " + points + " points.\n");
                endRound = true;
            }

            if ((playerCards.cardsValue() > dealerCards.cardsValue()) && endRound == false) {
                System.out.println("You win the round!\n");
                points += 100;
                System.out.println("You have " + points + " points.\n");
                endRound = true;

            } else if (endRound == false) {
                System.out.println("Dealer wins the round.\n");
                points -= 100;
                System.out.println("You have " + points + " points.\n");
            }

            playerCards.moveAllToDeck(playingDeck);
            dealerCards.moveAllToDeck(playingDeck);

            System.out.println("End of round.\n\n♠*♣*♥*♦*♠*♣*♥*♦*♠*♣*♥*♦\n");

        }
        System.out.println("Game over! You lost all your points.");

        in.close();

    }
}
