public class RadixSort {
    private long swaps = 0;
    private long iterations = 0;

    public void sort(int[] arr) {
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
            iterations++;
        }
    }

    private int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            iterations++;
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            iterations++;
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            iterations++;
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            iterations++;
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
            swaps++;
        }

        for (int i = 0; i < n; i++) {
            iterations++;
            arr[i] = output[i];
        }
    }

    public long getSwaps() {
        return swaps;
    }

    public long getIterations() {
        return iterations;
    }
}