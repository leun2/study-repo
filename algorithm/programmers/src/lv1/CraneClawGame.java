package lv1;

import java.util.Stack;

public class CraneClawGame {

    public int solution(int[][] board, int[] moves) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;

        for (int move : moves) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][move - 1] > 0) {
                    int picked = board[i][move - 1];
                    board[i][move - 1] = 0;

                    if (!stack.isEmpty() && stack.peek() == picked) {
                        stack.pop();
                        result += 2;
                    } else {
                        stack.push(picked);
                    }
                    break;
                }
            }
        }
        return result;
    }
}
