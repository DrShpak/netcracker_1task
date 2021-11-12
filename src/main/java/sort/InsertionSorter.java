package sort;

import contracts.Contract;
import repository.Repository;

import java.util.Comparator;

public class MergeSorter implements ISorter {
    @Override
    public <T extends Contract> Contract[] sort(Contract[] contracts, Comparator<T> comp) {
        return new Contract[0];
    }

    @Override
    public Repository sort2(Contract[] contracts, Comparator<Contract> comp) {
        for (int i = 1; i < Repository.getCurrentIndex(); i++) {
            Contract key = contracts[i];
            int j = i - 1;
            while (j >= 0 && comp.compare(contracts[j], key) > 0) {
                input[j + 1] = input[j];
                j = j - 1;
            }
            input[j + 1] = key;
        }
        return new Repository();
    }

    private void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}
