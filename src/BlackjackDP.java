import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BlackjackDP {

    public static void main(String[] args) {

        int[] shuffledDeck;

        if(args.length == 104 )
            shuffledDeck = initializeDeck(0, args);
        else {
            System.out.println("we create random deck");
            shuffledDeck = initializeDeck(1, args);
        }

        int i =0;
        // Print the shuffled deck
        for (int card : shuffledDeck) {
            System.out.print(card + " ");
        }


        for (int card : shuffledDeck) {
            System.out.print(getCardValue(card) + " ");
        }

}


    // Function to calculate the maximum earnings using dynamic programming
    private static int calculateMaxEarnings(int[] deck, int[] inputSequence) {
        int n = inputSequence.length;
        int[][] dp = new int[n + 1][22];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= 21; j++) {
                // Case 1: Standing
                int standEarn = 0;
                if (j <= 21) {
                    standEarn = dp[i + 1][j] + getCardValue(deck[inputSequence[i] - 1]);
                }

                // Case 2: Hitting
                int hitEarn = 0;
                if (j + getCardValue(deck[inputSequence[i] - 1]) <= 21) {
                    hitEarn = dp[i + 1][j + getCardValue(deck[inputSequence[i] - 1])];
                }

                // Choose the maximum of standing and hitting
                dp[i][j] = Math.max(standEarn, hitEarn);
            }
        }

        // Displaying the sequences of moves and current profit after each hand
        int currentEarnings = 0;
        for (int i = 0; i < n; i++) {
            int cardValue = getCardValue(deck[inputSequence[i] - 1]);
            currentEarnings += cardValue;
            System.out.println("Hand " + (i + 1) + ": " + (cardValue > 0 ? "Hit" : "Stand") +
                    " - Current Earnings: " + currentEarnings);
        }

        // Return the final earnings after all hands
        return dp[0][0];
    }

    // Function to get the card value based on the integer representation
    private static int getCardValue(int cardRepresentation) {
        int suitCoefficient = (cardRepresentation - 1) / 13;
        int cardValue = (cardRepresentation - 1) % 13 + 1;
        return Math.min(cardValue, 10) == 1 ? 11 : Math.min(cardValue, 10);
    }


    // Function to initialize the deck
    private static int[] initializeDeck(int control, String[] args) {

        int[] deck = new int[104];

        if (control == 1)
        {

            for (int i = 0; i < 104; i++) {
                deck[i] = (i % 52) + 1;
            }
            // Shuffle the deck using Fisher-Yates algorithm
            Random random = new Random();
            for (int i = 103; i > 0; i--) {
                int j = random.nextInt(i + 1);

                // Swap deck[i] and deck[j]
                int temp = deck[i];
                deck[i] = deck[j];
                deck[j] = temp;
            }}
        else
            for (int i = 0; i < 104; i++) {
                deck[i] = Integer.parseInt(args[i]);
            }

        return deck;
    }
}
