package lv1;

public class SecretMap {

    public String[] solution(int n, int[] arr1, int[] arr2) {

        String[] result = new String[n];

        for (int i = 0; i < n; i++) {
            String map1 = String.format("%" + n + "s", Integer.toBinaryString(arr1[i])).replace(' ', '0');
            String map2 = String.format("%" + n + "s", Integer.toBinaryString(arr2[i])).replace(' ', '0');

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if(map1.charAt(j) == '1' || map2.charAt(j) == '1') {
                    sb.append('#');
                } else {
                    sb.append(' ');
                }
            }
            result[i] = sb.toString();
        }
        return result;
    }
}
