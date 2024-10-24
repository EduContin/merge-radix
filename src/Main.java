import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000, 500000, 1000000};
        int numRuns = 5;
        long[][] mergeSortTimes = new long[sizes.length][numRuns];
        long[][] radixSortTimes = new long[sizes.length][numRuns];
        long[][] mergeSortSwaps = new long[sizes.length][numRuns];
        long[][] radixSortSwaps = new long[sizes.length][numRuns];
        long[][] mergeSortIterations = new long[sizes.length][numRuns];
        long[][] radixSortIterations = new long[sizes.length][numRuns];

        for (int i = 0; i < sizes.length; i++) {
            for (int run = 0; run < numRuns; run++) {
                int[] array = generateArray(sizes[i], run);
                int[] arrayCopy = array.clone();

                // MergeSort
                MergeSort mergeSort = new MergeSort();
                long startTime = System.nanoTime();
                mergeSort.sort(array);
                long endTime = System.nanoTime();
                mergeSortTimes[i][run] = (endTime - startTime) / 1000000; // Convert to milliseconds
                mergeSortSwaps[i][run] = mergeSort.getSwaps();
                mergeSortIterations[i][run] = mergeSort.getIterations();

                // RadixSort
                RadixSort radixSort = new RadixSort();
                startTime = System.nanoTime();
                radixSort.sort(arrayCopy);
                endTime = System.nanoTime();
                radixSortTimes[i][run] = (endTime - startTime) / 1000000;
                radixSortSwaps[i][run] = radixSort.getSwaps();
                radixSortIterations[i][run] = radixSort.getIterations();
            }
        }

        // Save results to CSV
        saveResults("results.csv", sizes, numRuns,
                mergeSortTimes, radixSortTimes,
                mergeSortSwaps, radixSortSwaps,
                mergeSortIterations, radixSortIterations);
    }

    private static int[] generateArray(int size, int seed) {
        Random random = new Random(seed);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000000);
        }
        return array;
    }

    private static void saveResults(String filename, int[] sizes, int numRuns,
                                    long[][] mergeTimes, long[][] radixTimes,
                                    long[][] mergeSwaps, long[][] radixSwaps,
                                    long[][] mergeIterations, long[][] radixIterations) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Size,Run,MergeTime,RadixTime,MergeSwaps,RadixSwaps,MergeIterations,RadixIterations\n");
            for (int i = 0; i < sizes.length; i++) {
                for (int run = 0; run < numRuns; run++) {
                    writer.write(String.format("%d,%d,%d,%d,%d,%d,%d,%d\n",
                            sizes[i], run + 1,
                            mergeTimes[i][run], radixTimes[i][run],
                            mergeSwaps[i][run], radixSwaps[i][run],
                            mergeIterations[i][run], radixIterations[i][run]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}