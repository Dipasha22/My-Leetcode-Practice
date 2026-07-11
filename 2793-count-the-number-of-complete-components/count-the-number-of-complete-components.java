import java.util.*;

class Solution {

    List<Integer>[] graph;
    boolean[] vis;

    public int countCompleteComponents(int n, int[][] edges) {

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        vis = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {

                List<Integer> component = new ArrayList<>();
                dfs(i, component);

                int vertices = component.size();

                int degreeSum = 0;
                for (int node : component)
                    degreeSum += graph[node].size();

                int edgeCount = degreeSum / 2;

                if (edgeCount == vertices * (vertices - 1) / 2)
                    ans++;
            }
        }

        return ans;
    }

    private void dfs(int node, List<Integer> component) {

        vis[node] = true;
        component.add(node);

        for (int next : graph[node]) {
            if (!vis[next])
                dfs(next, component);
        }
    }
}