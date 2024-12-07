import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ModifyToOneDigit {
    public static void main(String[] args) throws IOException {
        int[] arr = Files.readAllLines(Path.of(args[0])).stream()
                .mapToInt(x -> Integer.valueOf(x).intValue()).toArray();

        System.out.println(calculateOperations(arr));
    }

    static int calculateOperations(int[] arr) {
        int midVal = findMedian(arr);
        return Arrays.stream(arr).map(x -> Math.abs(x - midVal)).sum();
    }

    static boolean abortSort(int[] arr, int l, int r) {
        int mid = arr.length % 2 != 0 ? arr.length / 2 + 1 : arr.length / 2;

        boolean inRange = mid >= l && mid <= r;
        if (!inRange) return true;

        return false;
    }

    static int findMedian(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        int mid = arr[arr.length / 2];
        return mid;
    }

    static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;

        if (abortSort(arr, l, r)) return;
//        System.out.println("l" + l + " r" + r);
        int indexCenter = partition(arr, l, r);
        quickSort(arr, l, indexCenter - 1);
        quickSort(arr, indexCenter + 1, r);
    }

    static int partition(int[] arr, int l, int r) {
        int l_inner = l;
        int r_inner = r + 1;
        int midVal = arr[l];
        int midPos = l;
        while (true) {
            new String();
            while (less(arr[++l_inner], midVal, arr)) {
                if (l_inner == r) break;
            }
            while (less(midVal, arr[--r_inner], arr)) {
                if (r_inner == l) break;
            }
            if (l_inner >= r_inner) break;
            swap(arr, l_inner, r_inner);
        }
        swap(arr, midPos, r_inner);

        int swappedFirst = r_inner;
        return swappedFirst;
    }


    static boolean less(int val1, int val2, int[] arr) {
        return val1 < val2;
    }

    static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}