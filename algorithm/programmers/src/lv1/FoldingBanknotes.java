package lv1;

public class FoldingBanknotes {

    public int solution(int[] wallet, int[] bill) {
        int answer = 0;

        int walletMin = Math.min(wallet[0], wallet[1]);
        int walletMax = Math.max(wallet[0], wallet[1]);

        int currentBillWidth = bill[0];
        int currentBillHeight = bill[1];

        while (true) {
            int currentBillMin = Math.min(currentBillWidth, currentBillHeight);
            int currentBillMax = Math.max(currentBillWidth, currentBillHeight);

            if (currentBillMin <= walletMin && currentBillMax <= walletMax) {
                break;
            }
            if (currentBillWidth >= currentBillHeight) {
                currentBillWidth /= 2;
            } else {
                currentBillHeight /= 2;
            }
            answer++;
        }

        return answer;
    }
}
