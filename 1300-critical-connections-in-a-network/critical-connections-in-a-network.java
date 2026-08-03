class Solution {
    int time=0;
    List<List<Integer>> result;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] adj= new ArrayList[n];

       for(int i=0;i<n;i++){
        adj[i]= new ArrayList<>();
       }

        for(List<Integer> edge:connections)
        {
            int a= edge.get(0);
            int b= edge.get(1);
            adj[a].add(b);
            adj[b].add(a);
        }
    boolean[] visited= new boolean[n];
    int[] timestamp= new int[n];

    result= new ArrayList<>();
   for (int i = 0; i < n; i++) {
    if (!visited[i]) {
        dfs(adj, visited, timestamp, i, -1);
    }
}
    return result;

    }
    void dfs(List<Integer>[] adj, boolean[] visited,int[] timestamp, int vertex, int prev){
        visited[vertex]=true;
        timestamp[vertex]=time++;
        int currTimestamp= timestamp[vertex];

        for(int v: adj[vertex])
        {
            if(v==prev)continue;
            if (!visited[v]) {
    dfs(adj, visited, timestamp, v, vertex);

    timestamp[vertex] = Math.min(timestamp[vertex], timestamp[v]);

    if (currTimestamp < timestamp[v]) {
        result.add(Arrays.asList(vertex, v));
    }
} else {
    timestamp[vertex] = Math.min(timestamp[vertex], timestamp[v]);
}

        }
    }
}