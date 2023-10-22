package solitaire;

/**
 * This class describes the model of a result counter
 */
class Counter {

    /**
     * This method translates the points into the scores
     *
     * @param points number of points in a row or column
     * @param bjCase flag for special scoring in the first or last column
     * @return number of scores
     */
    private static int pointsToScores(int points, boolean bjCase) {
        int scores;
        switch (points) {
            case 21:
                scores = bjCase ? 10 : 7;
                break;
            case 20:
                scores = 5;
                break;
            case 19:
                scores = 4;
                break;
            case 18:
                scores = 3;
                break;
            case 17:
                scores = 2;
                break;
            default:
                scores = 1;
        }
        return scores;
    }

    /**
     * This method counts the total number of scores in all rows of the playing
     * field
     *
     * @param playingField final state of the playing field
     * @return number of scores in all rows
     */
    static int countRowScore(String[][] playingField) {
        int totalRowScore = 0; // number of scores in all rows
        for (int i = 0; i < 4; i++) {
            int[] rowPoints = new int[5]; // collects variations of points depending on the number of aces in a row
            int cntAces = 0; // used to count the number of aces in each row
            for (int j = 0; j < 5; j++) {
                if (playingField[i][j] != null) {
                    char rankChar = playingField[i][j].charAt(0);
                    if (rankChar >= 50 && rankChar <= 57) {
                        rowPoints[0] += Character.getNumericValue(rankChar);
                    } else if (rankChar == 65) {
                        rowPoints[0] += 11;
                        cntAces++;
                    } else {
                        rowPoints[0] += 10;
                    }
                }
            }
            for (int m = 4; m > 0; m--) {
                rowPoints[m] = rowPoints[0] - 10 * cntAces;
                if (cntAces > 0) {
                    cntAces--;
                }
            }
            for (int n = 0; n < 5; n++) {
                if (rowPoints[n] <= 21) {
                    totalRowScore += pointsToScores(rowPoints[n], false);
                    break;
                }
            }
        }
        return totalRowScore;
    }

    /**
     * This method counts the total number of scores in all columns of the
     * playing field
     *
     * @param playingField final state of the playing field
     * @return number of scores in all columns
     */
    static int countColumnScore(String[][] playingField) {
        int totalColumnScore = 0; // number of scores in all columns
        boolean bjCase; // flag for special scoring in the first and last columns
        for (int i = 0; i < 5; i++) {
            int cntAces = 0; // used to count the number of aces in each column
            int[] columnPoints = new int[5]; // collects variations of points depending on the number of aces in a column
            for (int j = 0; j < 4; j++) {
                if (playingField[j][i] != null) {
                    char rankChar = playingField[j][i].charAt(0);
                    if (rankChar >= 50 && rankChar <= 57) {
                        columnPoints[0] += Character.getNumericValue(rankChar);
                    } else if (rankChar == 65) {
                        columnPoints[0] += 11;
                        cntAces++;
                    } else {
                        columnPoints[0] += 10;
                    }
                }
            }
            for (int m = 4; m > 0; m--) {
                columnPoints[m] = columnPoints[0] - 10 * cntAces;
                if (cntAces > 0) {
                    cntAces--;
                }
            }
            bjCase = (i == 0 || i == 4) && columnPoints[0] == 21;
            for (int n = 0; n < 5; n++) {
                if (columnPoints[n] <= 21) {
                    totalColumnScore += pointsToScores(columnPoints[n], bjCase);
                    break;
                }
            }
        }
        return totalColumnScore;
    }
}
