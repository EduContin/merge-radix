public class MergeSort {
    private long swaps = 0;
    private long iterations = 0;

    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int left, int right) {
        iterations++;
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
            iterations++;
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
            iterations++;
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            iterations++;
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
                swaps++;
            }
            k++;
        }

        while (i < n1) {
            iterations++;
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            iterations++;
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public long getSwaps() {
        return swaps;
    }

    public long getIterations() {
        return iterations;
    }
}