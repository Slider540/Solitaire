package solitaire;

/**
 * This class manages the main logic of the game
 */
class BlackjackSolitaireController {

    private int totalScore; // the number of points received by the player

    void play() {
        System.out.println("Hello! Let's play Blackjack Solitaire.\n");

        // creating, filling and shuffling the deck
        String[] deck = Deck.fillDeck();

        // creating and initial filling in the playing field and the pile
        Field field = new Field();
        String[][] playingField = field.fillField();
        String[] pile = field.fillPile();

        int freeSlotsInPile = 4; // number of free slots on discard pile
        int filledSlotsOnField = 0; // number of filled slots on the playing field
        int numberOfCards = 0; // number of cards taken from the deck

        // displays the initial state of the playing field
        field.printField(playingField, pile, freeSlotsInPile);

        // filling in the field with cards
        while (filledSlotsOnField < 16) {
            String takenCard = Card.getCard(deck, numberOfCards);
            numberOfCards++;
            System.out.println("Current card is " + takenCard + ". Where do you want to place it?");
            int slotNumber = field.getSlotNumber(playingField, pile, freeSlotsInPile);
            if (slotNumber <= 16) {
                playingField = Card.putOnField(playingField, slotNumber, takenCard);
                filledSlotsOnField++;
            } else {
                pile = Card.putInPile(pile, slotNumber, takenCard);
                freeSlotsInPile--;
            }
            System.out.println();
            field.printField(playingField, pile, freeSlotsInPile); // displays the current state of the playing field on the screen
        }

        // calculation of results
        totalScore += Counter.countRowScore(playingField);
        totalScore += Counter.countColumnScore(playingField);

        // output the results of the game
        System.out.println("Final score is " + totalScore + ".\nThank you for playing! "
                + "Hope to see you soon!");
    }
}
