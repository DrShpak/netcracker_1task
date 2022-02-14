package sort;

import contracts.Contract;
import repository.Repository;

import java.util.Comparator;

public class BubbleSorter implements ISorter {
    @Override
    public Repository sort(Contract[] contracts, Comparator<Contract> comp, int currIndex) {
        for (int i = 0; i < currIndex; i++) {
            for (int j = 1; j < (currIndex - i); j++) {
                if (comp.compare(contracts[j - 1], contracts[j]) > 0) {
                    var temp = contracts[j - 1];
                    contracts[j - 1] = contracts[j];
                    contracts[j] = temp;
                }
            }
        }
        var repo = new Repository(contracts);
        repo.setCurrentIndex(currIndex);
        return repo;
    }
}