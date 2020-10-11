import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'count_clusters' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_STRING_ARRAY grid as parameter.
     */
    private static void markGrid(List<List<String>> grid, int x, int y, String mark) {
        if (x < 0 || x >= grid.size() || y < 0 || y >= grid.get(x).size()) {
            return;
        }
        if (grid.get(x).get(y).compareTo(mark) == 0) {
            return;
        }
        grid.get(x).remove(y);
        grid.get(x).add(y, mark);
        markGrid(grid, x-1, y, mark);
        markGrid(grid, x+1, y, mark);
        markGrid(grid, x, y-1, mark);
        markGrid(grid, x, y+1, mark);
    }
    
    public static int count_clusters(List<List<String>> grid) {
        int count = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j).compareTo("1") == 0) {
                    markGrid(grid, i, j, "-1");
                    count++;
                }
            }
        }
        return count;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
/*
        int gridRows = Integer.parseInt(bufferedReader.readLine().trim());
        int gridColumns = Integer.parseInt(bufferedReader.readLine().trim());
*/
        int gridRows = 0;
        int gridColumns = 0;
        System.out.println(bufferedReader.readLine().trim());
        List<List<String>> grid = new ArrayList<>();
        
        String[] lines = bufferedReader.readLine().trim().split("\n");
        System.out.println("length : " + lines.length);
        for(String l : lines) {
            List<String> rowList = new LinkedList<>();
            String[] t = l.split("\\s+$");
            rowList.addAll(Arrays.asList(t));
            grid.add(rowList);
        }
/*
        IntStream.range(0, gridRows).forEach(i -> {
            try {
                grid.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
*/
        int result = Result.count_clusters(grid);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

