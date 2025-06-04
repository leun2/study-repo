package lv1;

public class TwoThousandSixteenYears {

    public String solution(int a, int b) {
        String[] dayNames = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        int[] daysInMonth = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int totalDays = 0;

        for (int i = 1; i < a; i++) {
            totalDays += daysInMonth[i];
        }

        totalDays += (b - 1);

        int startDayIndex = 5;

        int finalDayIndex = (startDayIndex + totalDays) % 7;

        return dayNames[finalDayIndex];
    }
}
