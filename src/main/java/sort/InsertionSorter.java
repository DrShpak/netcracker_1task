package sort;

import contracts.Contract;
import repository.Repository;

import java.util.Comparator;

public class InsertionSorter implements ISorter {

    @Override
    public Repository sort(Contract[] contracts, Comparator<Contract> comp) {
        for (int i = 1; i < Repository.getCurrentIndex(); i++) {
            Contract key = contracts[i];
            int j = i - 1;
            while (j >= 0 && comp.compare(contracts[j], key) > 0) {
                contracts[j + 1] = contracts[j];
                j = j - 1;
            }
            contracts[j + 1] = key;
        }
        return new Repository(contracts);
    }
}
