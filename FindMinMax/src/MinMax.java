
public class MinMax {

  /**
   * Helper class to store the result.
   * 
   * This class is useful for testing - so that the result of finding the contigous sum can be
   * stored and then verified
   * 
   * NOTES:
   * 
   * To compile copy the file to a desired location
   * 
   * $> javac MinMax.java
   * 
   * $> java MinMax
   * 
   * Or it could be compiled in Eclipse and other IDEs as well.
   * 
   * @author Prashanth
   *
   */
  private class MinMaxResult {
    private Long swMin;
    private Long swMax;

    public MinMaxResult(Long swMin, Long swMax) {
      this.swMin = swMin;
      this.swMax = swMax;
    }

    public Long getMin() {
      return swMin;
    }

    public Long getMax() {
      return swMax;
    }
  }

  /**
   * helper function to get the sum of the first n numbers where n is the min(numElem -
   * startingIndex, slidingWindowSize)
   * 
   * @param array
   * @param index - starting index;
   * @param slidingWindowSize - window size;
   * @param numElem - total number of elements in the array
   * @return
   */
  private Long getSum(int array[], int index, int slidingWindowSize, int numElem) {
    Long sum = 0L;
    for (int i = index; i < numElem && i < index + slidingWindowSize; i++) {
      sum += array[i];
    }
    return sum;
  }

  /**
   * Internal method to compute the min,max sum and store it in a MinMaxResult - this is useful for
   * testing.
   * 
   * @param array
   * @param numElem total number of elements in the array
   * @param slidingWindowSize window size
   * @return
   */
  public MinMaxResult FindMinMaxImpl(int array[], int numElem, int slidingWindowSize) {
    Long swMin;
    Long swMax;

    /*
     * get the sum of the first set of elements in the window
     * 
     */
    Long prevSum = getSum(array, 0, numElem, slidingWindowSize);

    swMin = swMax = prevSum;

    /*
     * note, we terminate early, for example, if there are 5 elements and the window size is 3, the
     * loop terminates at index 2, which is the last full window
     */
    for (int i = 1; i > 0 && i <= numElem - slidingWindowSize; i++) {

      /* compute the new sum with the next element in the set */
      Long newSum = prevSum - array[i - 1] + array[i + slidingWindowSize - 1];

      /* update the min,max being computed */
      if (newSum < swMin) {
        swMin = newSum;
      }
      if (newSum > swMax) {
        swMax = newSum;
      }

      /* update sum for the next iteration */
      prevSum = newSum;
    }

    /* return the result in MinMaxResult */
    return new MinMaxResult(swMin, swMax);
  }

  void FindMinMax(int array[], int numElem, int slidingWindowSize) {

    /* calls into the helper */
    MinMaxResult mResult = FindMinMaxImpl(array, numElem, slidingWindowSize);

    /* print the result in the format */
    System.out.println("Minimum sum on any contiguous subset of size " + slidingWindowSize + " : "
        + mResult.getMin());
    System.out.println("Maximum sum on any contiguous subset of size " + slidingWindowSize + " : "
        + mResult.getMax());
  }

  /* multiple test cases below */
  /* basic test */
  private void testCase1(MinMax mmX) {
    int[] arr = {1, 2, 4, 5, 3};
    MinMaxResult mmR = mmX.FindMinMaxImpl(arr, arr.length, 2);
    if (mmR.getMax() != 9 && mmR.getMin() != 2) {
      System.out
          .println("testCase1 failed (max = " + mmR.getMax() + " min = " + mmR.getMin() + ")");
      return;
    }
    System.out.println("testCase1 succeeded");
  }

  /* test 2 with a number of elements less than the sliding window size */
  private void testCase2(MinMax mmX) {
    int[] arr = {1};
    MinMaxResult mmR = mmX.FindMinMaxImpl(arr, arr.length, 2);
    if (mmR.getMax() != 1 && mmR.getMin() != 1) {
      System.out
          .println("testCase1 failed (max = " + mmR.getMax() + " min = " + mmR.getMin() + ")");
      return;
    }
    System.out.println("testCase2 succeeded");
  }

  /* test 3 with an empty array */
  private void testCase3(MinMax mmX) {
    int[] arr = {};
    MinMaxResult mmR = mmX.FindMinMaxImpl(arr, arr.length, 2);
    if (mmR.getMax() != 0 && mmR.getMin() != 0) {
      System.out
          .println("testCase1 failed (max = " + mmR.getMax() + " min = " + mmR.getMin() + ")");
      return;
    }
    System.out.println("testCase3 succeeded");
  }

  /* test 4 tests for overflow */
  private void testCase4(MinMax mmX) {
    int[] arr = {Integer.MAX_VALUE, Integer.MAX_VALUE, 2};
    MinMaxResult mmR = mmX.FindMinMaxImpl(arr, arr.length, 2);

    Long minL = new Long(Integer.MAX_VALUE);
    minL += 2;
    Long maxL = new Long(Integer.MAX_VALUE);
    maxL *= 2;

    if (mmR.getMax().compareTo(maxL) != 0 && mmR.getMin().compareTo(minL) != 0) {
      System.out
          .println("testCase1 failed (max = " + mmR.getMax() + " min = " + mmR.getMin() + ")");
      return;
    }
    System.out.println("testCase4 succeeded");
  }

  public static void main(String[] args) {
    MinMax mmX = new MinMax();

    /* Uncomment to run the test cases */
    // mmX.testCase1(mmX);
    // mmX.testCase2(mmX);
    // mmX.testCase3(mmX);
    // mmX.testCase4(mmX);

    /* Sample usage */
    int[] arr = {1, 2, 4, 5, 3};
    mmX.FindMinMax(arr, arr.length, 2);
  }
}
