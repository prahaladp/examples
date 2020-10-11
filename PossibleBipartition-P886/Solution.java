class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Queue<Integer> q = new LinkedList<>();
        Boolean biPartite = true;
        ArrayList<Integer>[] graph = new ArrayList[N];
        int[] sides = new int[N];

        for (int i = 0; i<sides.length; sides[i] = -1, graph[i] = new ArrayList<>(), i++);
        for (int i = 0; i<dislikes.length;i++) {
            graph[dislikes[i][0]-1].add(dislikes[i][1]-1);
            graph[dislikes[i][1]-1].add(dislikes[i][0]-1);
        }
        for (int i = 0; i < graph.length && biPartite==true; i++) {
            if (sides[i] == -1) {
                sides[i] = 0;
                ((LinkedList<Integer>) q).push(i);
                while (q.isEmpty() == false && biPartite == true) {
                    int n = ((LinkedList<Integer>) q).pop();
                    for (int j = 0; j < graph[n].size() && biPartite == true; j++) {
                        int adjn = graph[n].get(j);
                        //System.out.println(n + " " + sides[n] + ":" + adjn + " " + sides[adjn]);
                        if (adjn == n) {
                            continue;
                        }
                        if (sides[adjn] == -1) {
                            sides[adjn] = sides[n] ^ 1;
                            ((LinkedList<Integer>) q).push(adjn);
                        } else {
                            if (sides[adjn] == sides[n]) {
                                System.out.println(" failed for " + adjn + " " + n);
                                biPartite = false;
                            }
                        }
                    }
                }
            }
        }
        return biPartite;
    }
}
