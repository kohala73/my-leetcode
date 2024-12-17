package base;

/**
 * @author xuyj
 * @date 2024/11/25
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 5, 4, 6, 7};

        quickSort(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pi = partition(arr, left, right);
            quickSort(arr, left, pi - 1);
            quickSort(arr, pi + 1, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        //i指向小于pivot的最后一个元素
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                if (i != j) {
                    swap(arr, i, j);
                }
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
