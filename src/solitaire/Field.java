package solitaire;

import java.util.Scanner;

/**
 * This class describes the model of a playing field
 */
class Field {

    private static boolean isDigit(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * This method fills the playing field with slot numbers
     *
     * @return initial state of the playing field
     */
    String[][] fillField() {
        String[][] playingField = new String[4][5];
        int cnt = 1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                playingField[i][j] = Integer.toString(cnt);
                cnt++;
            }
        }
        for (int i = 2; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                playingField[i][j] = Integer.toString(cnt);
                cnt++;
            }
        }
        return playingField;
    }

    /**
     * This method creates discard pile
     *
     * @return initial state of the discard pile
     */
    String[] fillPile() {
        String[] pile = new String[4];
        for (int i = 17; i < 21; i++) {
            pile[i - 17] = i + "";
        }
        return pile;
    }

    /**
     * This method displays the current state of the playing field
     *
     * @param playingField current state of the playing field
     * @param freeSlotsInPile number of free slots in discard pile
     */
    void printField(String[][] playingField, String[] pile, int freeSlotsInPile) {
        System.out.println("Current state of the playing field:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (playingField[i][j] != null) {
                    System.out.print(playingField[i][j] + "\t");
                } else {
                    System.out.print("\t");
                }
//                System.out.print(playingField[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("----------------------------------");
        System.out.println("Current state of the discard pile:");
        for (int i = 0; i < 4; i++) {
            System.out.print("    " + pile[i] + "\t");
        }
        System.out.println("\n----------------------------------");
        System.out.println("Free slots on discard pile: " + freeSlotsInPile + "\n");
    }

    /**
     * This method gets the slot number from the player, checks it for
     * compliance with the format and availability
     *
     * @param playingField current state of the playing field
     * @param pile current state of the discard pile
     * @param freeSlotsInPile number of free slots in discard pile
     * @return available number of slot for placing taken card
     */
    int getSlotNumber(String[][] playingField, String[] pile, int freeSlotsInPile) {
        int slot; // number of slot for placing taken card
        boolean slotNotFound = true; // indicates whether the slot availability check has ended
        do {
            Scanner scan = new Scanner(System.in);

            // checking the entered value for format and range
            do {
                System.out.print("Please select a free slot in the range from 1 to 20 inclusive: ");
                while (!scan.hasNextInt()) {
                    System.out.print("You entered an incorrect value! Please select a free slot in the range from 1 to 20 inclusive: ");
                    scan.next();
                }
                slot = scan.nextInt();
                if (slot < 1 || slot > 20) {
                    System.out.print("You entered an incorrect value! ");
                }
            } while (slot < 1 || slot > 20);

            // checking the slot for availability
            if (slot >= 17) {
                if (!(slot + "").equals(pile[slot - 17])) {
                    System.out.print("Already occupied by " + pile[slot - 17] + ".");
                    if (freeSlotsInPile == 0) {
                        System.out.println(" The discard pile is full.");
                    } else {
                        System.out.println("");
                    }
                } else {
                    slotNotFound = false;
                }
            } else {
                if (slot <= 5) {
                    if (isDigit(playingField[0][slot - 1])) {
                        slotNotFound = false;
                    } else {
                        System.out.println("Already occupied by " + playingField[0][slot - 1] + ".");
                    }
                } else if (slot <= 10) {
                    if (isDigit(playingField[1][slot - 6])) {
                        slotNotFound = false;
                    } else {
                        System.out.println("Already occupied by " + playingField[1][slot - 6] + ".");
                    }
                } else if (slot <= 13) {
                    if (isDigit(playingField[2][slot - 10])) {
                        slotNotFound = false;
                    } else {
                        System.out.println("Already occupied by " + playingField[2][slot - 10] + ".");
                    }
                } else {
                    if (isDigit(playingField[3][slot - 13])) {
                        slotNotFound = false;
                    } else {
                        System.out.println("Already occupied by " + playingField[3][slot - 13] + ".");
                    }
                }
            }
        } while (slotNotFound);
        return slot;
    }
}
