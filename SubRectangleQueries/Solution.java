class SubrectangleQueries {
    int[][] rectangle;
    
    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
    }
    
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        if (row1 > row2 || col1 > col2) {
            return;
        }
        
        for (int i = row1; i <= row2 && i < rectangle.length; i++) {
            for (int j = col1; j <= col2 && j < rectangle[i].length; j++) {
                rectangle[i][j] = newValue;
            }
        }
    }
    
    public int getValue(int row, int col) {
        if (row >= rectangle.length || col >= rectangle[0].length) {
            return 0;
        }
        return rectangle[row][col];
    }
}

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * SubrectangleQueries obj = new SubrectangleQueries(rectangle);
 * obj.updateSubrectangle(row1,col1,row2,col2,newValue);
 * int param_2 = obj.getValue(row,col);
 */
