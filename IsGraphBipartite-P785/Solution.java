class Solution {
    public boolean isBipartite(int[][] graph) {
        Queue<Integer> q = new LinkedList<>();
        Boolean biPartite = true;
        int[] sides = new int[graph.length];

        for (int i = 0; i<sides.length; sides[i] = -1, i++);
        for (int i = 0; i < graph.length && biPartite==true; i++) {
            if (sides[i] == -1) {
                sides[i] = 0;
                ((LinkedList<Integer>) q).push(i);
                while (q.isEmpty() == false && biPartite == true) {
                    int n = ((LinkedList<Integer>) q).pop();
                    for (int j = 0; j < graph[n].length; j++) {
                        int adjn = graph[n][j];
                        if (sides[adjn] == -1) {
                            sides[adjn] = sides[n] ^ 1;
                            ((LinkedList<Integer>) q).push(adjn);
                        } else {
                            if (sides[adjn] == sides[n]) {
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
