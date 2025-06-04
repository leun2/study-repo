package lv3;

import java.util.Arrays;

public class SharedTaxiFare {
    // 무한대를 나타내는 상수. 경로가 존재하지 않거나, 아직 최단 경로를 찾지 못했을 때 사용합니다.
    // n(최대 200) * max_fare(최대 100,000) = 20,000,000 이므로,
    // 이 값보다 커야 하며, Integer.MAX_VALUE / 2를 사용하면 두 INF를 더해도 오버플로우가 발생하지 않습니다.
    private static final int INF = 200 * 100000 + 1;

    // n: 지점의 개수
    // s: 출발 지점
    // a: A의 도착 지점
    // b: B의 도착 지점
    // fares: 지점 사이의 예상 택시 요금 정보 (c, d, f 형태)
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // Step 1: 인접 행렬(거리 행렬) 초기화
        // dist[i][j]는 i에서 j까지의 최단 거리를 저장할 배열입니다.
        // 지점 번호가 1부터 n까지이므로, 배열 크기를 n+1로 선언하여 인덱스를 직접 지점 번호로 활용합니다.
        int[][] dist = new int[n + 1][n + 1];

        // 모든 거리를 INF(무한대)로 초기화합니다.
        // 이는 아직 경로를 모르거나, 경로가 없음을 의미합니다.
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // 자기 자신으로의 거리는 0입니다.
        }

        // Step 2: 초기 직접 연결된 요금 채우기
        // fares 배열에 주어진 직접 연결된 지점 간의 요금을 dist 배열에 반영합니다.
        // [c, d, f]는 c와 d 사이의 요금이 f라는 뜻이며, 택시는 양방향으로 이동 가능합니다.
        for (int[] fare : fares) {
            int c = fare[0]; // 출발 지점
            int d = fare[1]; // 도착 지점
            int f = fare[2]; // 요금 (거리)
            dist[c][d] = f; // c에서 d로 가는 요금
            dist[d][c] = f; // d에서 c로 가는 요금 (양방향)
        }

        // Step 3: 플로이드-워셜 알고리즘 실행
        // 모든 쌍 간의 최단 경로를 계산하는 핵심 부분입니다.
        // k: 중간 경유 노드. i에서 j로 갈 때 k를 거쳐가는 경로가 더 짧은지 확인합니다.
        // i: 시작 노드
        // j: 도착 노드
        for (int k = 1; k <= n; k++) { // 모든 노드를 중간 경유지로 고려
            for (int i = 1; i <= n; i++) { // 모든 시작 노드에 대해
                for (int j = 1; j <= n; j++) { // 모든 도착 노드에 대해
                    // dist[i][k] 또는 dist[k][j]가 INF인 경우, 해당 경로가 존재하지 않음을 의미합니다.
                    // 이런 경우 더하면 오버플로우가 발생할 수 있으므로, INF가 아닌 경우에만 덧셈을 시도합니다.
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        // 현재 i에서 j로 가는 거리(dist[i][j])와
                        // i에서 k를 거쳐 j로 가는 거리(dist[i][k] + dist[k][j])를 비교하여
                        // 더 짧은 거리를 dist[i][j]에 저장합니다.
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // Step 4: 모든 가능한 합승 지점(k)을 순회하며 최소 총 요금 계산
        // A와 B가 s에서 출발하여 k까지 함께 이동하고, k에서 각자의 목적지(a, b)로 가는 총 요금의 최솟값을 찾습니다.
        // 요금의 합이 int 범위를 넘어설 수 있으므로 long 타입을 사용합니다.
        long minTotalFare = Long.MAX_VALUE;

        // 모든 지점 k를 잠재적인 합승 종료 지점(혹은 중간 경유 지점)으로 가정해봅니다.
        for (int k = 1; k <= n; k++) {
            // 합승이 유효하려면 s에서 k, k에서 a, k에서 b로 가는 경로가 모두 존재해야 합니다.
            // 즉, 해당 거리가 INF가 아니어야 합니다.
            if (dist[s][k] == INF || dist[k][a] == INF || dist[k][b] == INF) {
                continue; // 경로가 없으면 이 k는 유효한 합승 지점이 될 수 없으므로 다음 k로 넘어갑니다.
            }

            // 현재 합승 지점 k를 경유했을 때의 총 예상 요금 계산:
            // (s에서 k까지의 합승 요금) + (k에서 a까지 A 단독 요금) + (k에서 b까지 B 단독 요금)
            long currentFare = (long)dist[s][k] + dist[k][a] + dist[k][b];

            // 현재 계산된 요금이 지금까지의 최소 요금보다 작으면 업데이트합니다.
            minTotalFare = Math.min(minTotalFare, currentFare);
        }

        // 최종적으로 계산된 최소 요금을 int형으로 반환합니다. (문제에서 int 반환 타입 요구)
        return (int) minTotalFare;
    }
}
