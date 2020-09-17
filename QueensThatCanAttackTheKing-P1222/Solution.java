class Solution {
    private List<Integer> checkqxqy(int x, int y, int[][] queens) {
            for (int i = 0; i < queens.length; i++) {
                int qx = queens[i][0];
                int qy = queens[i][1];
                if (x == qx && y == qy) {
                    List<Integer> xy = new LinkedList<>();
                    xy.add(qx);
                    xy.add(qy);
                    return xy;
                }
            }
        return null;
    }
    
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> res = new LinkedList<>();
        int kx = king[0];
        int ky = king[1];
        
        for (int x = kx-1; x >= 0; x--) {
            List<Integer> xy = checkqxqy(x, ky, queens);
            if (xy != null) {
                res.add(xy);
                break;
            }
        }
        for (int x = kx+1; x < 8; x++) {
            List<Integer> xy = checkqxqy(x, ky, queens);
            if (xy != null) {
                res.add(xy);
                break;
            }
        }
        for (int y = ky-1; y >= 0; y--) {
            List<Integer> xy = checkqxqy(kx, y, queens);
            if (xy != null) {
                res.add(xy);
                break;
            }
        }
        for (int y = ky+1; y < 8; y++) {
            List<Integer> xy = checkqxqy(kx, y, queens);
            if (xy != null) {
                res.add(xy);
                break;
            }
        }
        for (int x=kx-1,y=ky-1; x>=0 && y>=0; x--,y--) {
            List<Integer> xy = checkqxqy(x, y, queens);
            if (xy != null) {
                res.add(xy);
                break;
            }
        }
        for (int x=kx+1,y=ky-1; x<8 && y>=0; x++,y--) {
            List<Integer> xy = checkqxqy(x, y, queens);
            if (xy != null) {
                res.add(xy);
                break;
            }
        }
        for (int x=kx+1,y=ky+1; x<8 && y<8; x++,y++) {
            List<Integer> xy = checkqxqy(x, y, queens);
            if (xy != null) {
                res.add(xy);
                break;
            }
        }
        for (int x=kx-1,y=ky+1; x>=0 && y<8; x--,y++) {
            List<Integer> xy = checkqxqy(x, y, queens);
            if (xy != null) {
                res.add(xy);
                break;
            }
        }

        return res;
    }
}
