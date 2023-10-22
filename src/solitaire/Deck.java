package solitaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum Ranks {
    _2("2"), _3("3"), _4("4"), _5("5"), _6("6"), _7("7"), _8("8"), _9("9"),
    _10("10"), J("J"), Q("Q"), K("K"), A("A");
    private String rank;

    Ranks(String rank) {
        this.rank = rank;
    }

    String getRank() {
        return rank;
    }
}

enum Suits {
    H("H"), S("S"), D("D"), C("C");
    private String suit;

    Suits(String suit) {
        this.suit = suit;
    }

    String getSuit() {
        return suit;
    }
}

/**
 * This class describes the model of a deck of cards
 */
class Deck {

    /**
     * This method creates the initial state of the deck of cards and shuffles cards
     *
     * @return filled deck of cards
     */
    static String[] fillDeck() {
        List<String> deck = new ArrayList<>();
        for (Ranks r : Ranks.values()) {
            for (Suits s : Suits.values()) {
                deck.add(r.getRank() + s.getSuit());
            }
        }
        Collections.shuffle(deck);
        return deck.toArray(new String[0]);
    }
}
