package sort;

import contracts.Contract;
import repository.Repository;

import java.util.Comparator;

public interface ISorter {
    Repository sort(Contract[] contracts, Comparator<Contract> comp);
}