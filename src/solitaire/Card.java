package solitaire;

/**
 * This class describes the card model
 */
class Card {

    /**
     * This method takes a card from the deck
     *
     * @param deck the shuffled deck of cards
     * @param cnt ordinal index of the card that is taken from the deck
     * @return a card taken from the deck
     */
    static String getCard(String[] deck, int cnt) {
        return deck[cnt];
    }

    /**
     * This method puts a card on the main slots of the playing
     * field
     *
     * @param playingField current state of the playing field
     * @param slotNumber slot number selected by the player
     * @param takenCard the card to put on the field
     * @return playing field with a placed card
     */
    static String[][] putOnField(String[][] playingField, int slotNumber, String takenCard) {
        if (slotNumber <= 5) {
            playingField[0][slotNumber - 1] = takenCard;
        } else if (slotNumber <= 10) {
            playingField[1][slotNumber - 6] = takenCard;
        } else if (slotNumber <= 13) {
            playingField[2][slotNumber - 10] = takenCard;
        } else {
            playingField[3][slotNumber - 13] = takenCard;
        }
        return playingField;
    }

    /**
     * This method puts the card in the discard pile
     *
     * @param pile current state of the discard pile
     * @param slotNumber slot number selected by the player
     * @param takenCard the card to put in the discard pile
     * @return the discard pile with a placed card
     */
    static String[] putInPile(String[] pile, int slotNumber, String takenCard) {
        pile[slotNumber - 17] = takenCard;
        return pile;
    }
}
