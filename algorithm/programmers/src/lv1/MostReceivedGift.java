package lv1;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class MostReceivedGift {

    public int solution (String[]friends, String[]gifts){
        Map<String, Integer> map = new LinkedHashMap<>();
        int index = 0;
        for (String name : friends) {
            map.put(name, index);
            index++;
        }

        int[] giftPoint = new int[friends.length];
        int[] totalGift = new int[friends.length];
        int[][] giftMap = new int[friends.length][friends.length];

        for (String s : gifts) {
            String[] friend = s.split(" ");
            giftMap[map.get(friend[0])][map.get(friend[1])]++;
            giftPoint[map.get(friend[0])]++;
            giftPoint[map.get(friend[1])]--;
        }

        for (int i = 0; i < friends.length; i++) {
            for (int j = i+1; j < friends.length; j++) {
                if(giftMap[i][j] > giftMap[j][i]) {
                    totalGift[i]++;
                } else if (giftMap[j][i] > giftMap[i][j]) {
                    totalGift[j]++;
                } else {
                    if(giftPoint[i] < giftPoint[j]){
                        totalGift[j]++;
                    } else if(giftPoint[j] < giftPoint[i]) {
                        totalGift[i]++;
                    }
                }
            }
        }
        return Arrays.stream(totalGift).max().orElse(0);
    }

//    public int solution(String[] friends, String[] gifts) {
//
//        Map<String, Integer> map = new LinkedHashMap<>();
//        int index = 0;
//        for (String name : friends) {
//            map.put(name, index);
//            index++;
//        }
//
//        int[] giftPoint = new int[friends.length]; // 각 친구의 선물 지수 (준 - 받은)
//        int[] nextMonthReceivedGifts = new int[friends.length]; // 다음 달에 각 친구가 받을 선물 수
//        int[][] giftHistory = new int[friends.length][friends.length]; // giftHistory[i][j]는 i가 j에게 준 선물 수
//
//        for (String s : gifts) {
//            String[] friendNames = s.split(" ");
//            int giverIdx = map.get(friendNames[0]);
//            int receiverIdx = map.get(friendNames[1]);
//
//            giftHistory[giverIdx][receiverIdx]++;
//            giftPoint[giverIdx]++;
//            giftPoint[receiverIdx]--;
//        }
//
//        // 모든 친구 쌍에 대해 다음 달에 받을 선물 계산
//        for (int i = 0; i < friends.length; i++) {
//            for (int j = i + 1; j < friends.length; j++) {
//                // i와 j 사이에 선물을 주고받은 기록 비교
//                if (giftHistory[i][j] > giftHistory[j][i]) {
//                    // i가 j에게 더 많이 줬으므로 j는 i에게 선물을 받음 -> i는 선물을 받음
//                    nextMonthReceivedGifts[i]++;
//                } else if (giftHistory[j][i] > giftHistory[i][j]) {
//                    // j가 i에게 더 많이 줬으므로 i는 j에게 선물을 받음 -> j는 선물을 받음
//                    nextMonthReceivedGifts[j]++;
//                } else { // 주고받은 수가 같거나 기록이 없는 경우
//                    // 선물 지수 비교
//                    if (giftPoint[i] > giftPoint[j]) {
//                        // i의 선물 지수가 더 높으므로 j는 i에게 선물을 받음 -> i는 선물을 받음
//                        nextMonthReceivedGifts[i]++;
//                    } else if (giftPoint[j] > giftPoint[i]) {
//                        // j의 선물 지수가 더 높으므로 i는 j에게 선물을 받음 -> j는 선물을 받음
//                        nextMonthReceivedGifts[j]++;
//                    }
//                }
//            }
//        }
//        return Arrays.stream(nextMonthReceivedGifts).max().orElse(0);
//    }
}
