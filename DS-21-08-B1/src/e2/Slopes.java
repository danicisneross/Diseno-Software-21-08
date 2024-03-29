package e2;

public class Slopes {

    /**
     * Check that the matrix is valid.
     */

    public static boolean argumentosValidos(char[][] slopeMap, int right, int down){ //en vez de public -> private
        int longc = slopeMap[0].length, longf = slopeMap.length;
        boolean control = false;

        if (((right < 1) || (right >= longc)) || ((down < 1) || (down >= longf))) {
            control = true;
        } else {
            for (int c = 0; c < longc; c++) {
                if (slopeMap[c].length != longf) {
                    control = true;
                    break;
                }
            }
        }
        return control;
    }

    /**
     * Traverses the slope map making the right and down movements and
     * returns the number of trees found along the way.
     * @param slopeMap A square matrix representing the slope with spaces
     * represented as "." and trees represented as "#".
     * @param right Movement to the right.
     * @param down Downward movement.
     * @return Number of trees.
     * @throws IllegalArgumentException if the matrix is incorrect because:
     *      - It is not square.
     *      - It has characters other than "." and "#".
     *      - right >= number of columns or right < 1.
     *      - down >= number of rows of the matrix or down < 1.
     */

    public static int downTheSlope(char[][] slopeMap, int right, int down) {
        int stopbr = 0, stopbd = 0, trees = 0, i = 0, iter = 0, ited = 0, longc = slopeMap[0].length, longf = slopeMap.length;

        if (argumentosValidos(slopeMap, right, down)) {
            throw new IllegalArgumentException();
        } else {
            for (int j = 0; (j < longf); j++) { //un while para comprobar que no ha llegado al final de la matriz  while (filas < longf)
                if (iter == 0) {
                    stopbr += right;
                }

                if (i == 0 && j != 0 && down != 1) {
                    stopbr = iter;
                } else if (i == 0 && j != 0) {
                    stopbr = iter - 1;
                }

                if (ited == 0 && j != 0 && iter == 0 && down != 1) {
                    stopbr--;
                }

                for (; (i < longc) && (i <= stopbr); i++) {
                    if (slopeMap[j][i] == '.' || slopeMap[j][i] == '#') {
                        if (slopeMap[j][i] == '#') {
                            trees++;
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                i--;
                iter = stopbr - i;
                ited = stopbd - j;
                if (ited == 0) {
                    stopbd += down;
                }
                if (i == (longc - 1) && iter != 0 && ited == 0) {
                    i = 0;
                    j--;
                }
            }
            return trees;
        }
    }

    /**
     * Traverses the slope map making the right and down movements and
     * returns the number of trees found along the way.
     * Since it "jumps" from the initial position to the final position,
     * only takes into account the trees on those initial / final positions.
     * Params, return value and thrown expections as in downTheSlope...
     */

    public static int jumpTheSlope(char[][] slopeMap, int right, int down) {
        int stopbr = 0, trees = 0, i = 0, longc = slopeMap[0].length, longf = slopeMap.length, aux;

        if (argumentosValidos(slopeMap, right, down)) {
            throw new IllegalArgumentException();
        } else {
            for (int j = 0; (j < longf); j = j + down) {
                stopbr += right;
                for (; (i < longc) && (i < stopbr); i = i + right) {
                    if (slopeMap[j][i] == '.' || slopeMap[j][i] == '#') {
                        if (slopeMap[j][i] == '#') {
                            trees++;
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                if (i > (longc - 1)) {
                    stopbr = 0;
                    aux = i - right;
                    if (aux == (longc - 1)) {
                        i = right - 1;
                    } else {
                        i = i - longc;
                    }
                }
            }
            return trees;
        }
    }
}