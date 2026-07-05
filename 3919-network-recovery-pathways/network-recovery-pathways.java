class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
    int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        int maxEdge = 0;

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            maxEdge = Math.max(maxEdge, e[2]);
        }

        // Topological Order
        int[] topo = new int[n];
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                q.offer(i);

        int idx = 0;

        while (!q.isEmpty()) {

            int u = q.poll();
            topo[idx++] = u;

            for (int[] edge : graph[u]) {

                int v = edge[0];

                if (--indegree[v] == 0)
                    q.offer(v);
            }
        }

        int low = 0;
        int high = maxEdge;
        int ans = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (check(mid, graph, topo, online, k)) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }

        return ans;
    }

    private boolean check(int minEdge,
                          List<int[]>[] graph,
                          int[] topo,
                          boolean[] online,
                          long k) {

        int n = online.length;

        long INF = Long.MAX_VALUE / 4;

        long[] dp = new long[n];

        Arrays.fill(dp, INF);

        dp[0] = 0;

        for (int u : topo) {

            if (dp[u] == INF)
                continue;

            for (int[] edge : graph[u]) {

                int v = edge[0];
                int cost = edge[1];

                if (cost < minEdge)
                    continue;

                if (v != n - 1 && !online[v])
                    continue;

                dp[v] = Math.min(dp[v], dp[u] + cost);
            }
        }

        return dp[n - 1] <= k;
    }
}