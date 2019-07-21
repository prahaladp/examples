import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private class XY {
        int x;
        int y;
        
        public XY(int x1, int y1) {
            x=x1;
            y=y1;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        
        Integer[][] m= new Integer[matrix.length][matrix[0].length];
        int lx = matrix.length;
        int ly = matrix[0].length;
        for (int i=0; i < matrix.length; i++) {
            Arrays.fill(m[i], -1);
        }
        
        ArrayList<XY> q = new ArrayList<>();
        q.add(new XY(0,0));
        List<XY> s = Arrays.asList(
            new XY(-1,0), new XY(1,0), new XY(0, -1),
            new XY(0, 1)
            );
        int maxl = -1;
        while (q.isEmpty() == false) {
            XY curr = q.remove(q.size()-1);
            int cmaxl = 1;
            int missing = 0;
    			int x = curr.getX();
    			int y = curr.getY();

            for (XY si : s) {
            		int nx = si.getX() + curr.x;
            		int ny = si.getY() + curr.y;
            		m[x][y] = -1;
            		if (!(nx < lx && nx >= 0 && ny >= 0 && ny < ly)) {
            			continue;
            		}
            		if (matrix[nx][ny] > matrix[x][y]) {
            			switch (m[nx][ny]) {
            				case -1:
            					q.add(new XY(nx, ny));
            					missing = missing + 1;
            					break;
            				case -2:
            					missing = missing + 1;
            					break;
            				default:
            					cmaxl = Integer.max(cmaxl, m[nx][ny] + 1;
            			} 
            		} else if (m[nx][ny] == -1) {
            			q.add(new XY(nx, ny));
            		}
            }
            m[x][y] = cmaxl;
            if (missing > 0) {
            		m[x][y] = -2;
            		q.add(0, curr);
            } else if (cmaxl > maxl) {
            		maxl = cmaxl;
            }
        }
        return maxl;
    }
}