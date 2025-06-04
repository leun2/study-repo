package lv1;

public class NeighboringSpace {

    public int solution(String[][] board, int h, int w) {

        int result = 0;
        String s = board[h][w];
        if (h + 1 < board.length && s.equals(board[h + 1][w])) {
            result++;
        }
        if (h - 1 >= 0 && s.equals(board[h - 1][w])) {
            result++;
        }

        if (w + 1 < board[h].length && s.equals(board[h][w + 1])) {
            result++;
        }
        if (w - 1 >= 0 && s.equals(board[h][w - 1])) {
            result++;
        }

        return result;
    }
}
