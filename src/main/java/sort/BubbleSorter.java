package sort;

import contracts.Contract;

import java.util.Comparator;

public class BubbleSort implements ISorter {
    @Override
    // если -1 то a < b
    public Contract[] sort(Contract[] contracts, Comparator<Contract> comp) {
        for (int i = 0; i < contracts.length; i++) {
            for (int j = 1; j < (contracts.length - i); j++) {
                if (comp.compare(contracts[j - 1], contracts[j]) > 0) {
                    var temp = contracts[j - 1];
                    contracts[j - 1] = contracts[j];
                    contracts[j] = temp;
                }

            }
        }
        return contracts;
    }
}
