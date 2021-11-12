package sort;

import contracts.Contract;
import repository.Repository;

import java.util.Comparator;

public class BubbleSorter implements ISorter {
    @Override
    @SuppressWarnings("unchecked")
    public Repository sort(Contract[] contracts, Comparator<Contract> comp) {
        for (int i = 0; i < Repository.getCurrentIndex(); i++) {
            for (int j = 1; j < (Repository.getCurrentIndex() - i); j++) {
                if (comp.compare(contracts[j - 1], contracts[j]) > 0) {
                    var temp = contracts[j - 1];
                    contracts[j - 1] = contracts[j];
                    contracts[j] = temp;
                }
            }
        }
        return new Repository(contracts);
    }
}