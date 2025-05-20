package lv1;

public class FoodFightCompetition {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < food.length; i++) {
            sb.append(String.valueOf(i).repeat(food[i] / 2));
        }

        String left = sb.toString();
        String right = new StringBuilder(left).reverse().toString();

        return left + "0" + right;
    }
}
