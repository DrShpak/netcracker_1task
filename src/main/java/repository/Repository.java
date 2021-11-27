package repository;

import contracts.Contract;
import lombok.Getter;
import sort.BubbleSorter;
import sort.InsertionSorter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.function.Predicate;

public class Repository {

    /**
     * initial capacity of repository array
     */
    private final int capacity = 1;


    private Contract[] storedContracts;
    @Getter //like a length
    private int currentIndex;

    public Repository() {
        storedContracts = new Contract[capacity];
    }

    public Repository(Contract[] contracts) {
        storedContracts = contracts;
    }

    /**
     * Add one or few contracts to array storedContracts
     *
     * @param contracts Contracts to be added
     */
    public void addContracts(Contract... contracts) {
        for (Contract currContract : contracts) {
            if (currentIndex == storedContracts.length) {
                storedContracts = expandArr(storedContracts);
            }
            storedContracts[currentIndex] = currContract;
            currentIndex++;
        }
    }

    /**
     * Delete contract by id
     *
     * @param id Contract's ID
     */
    public void deleteContractById(UUID id) {
        var isExist = false;
        for (int i = 0; i < currentIndex; i++) {
            if (storedContracts[i].getId().equals(id)) {
                storedContracts = deleteContract(storedContracts, i);
                isExist = true;
                currentIndex--;
                break;
            }
        }
        if (!isExist)
            throw new NoSuchElementException("Contract with this UUID doesn't exist!");
    }


    /**
     * Delete element (contract) from the repository array. This method create a new repository array.
     *
     * @param old   Old repository array
     * @param index The contract index in the repository array to be deleted
     * @return New repository array without deleted contract
     */
    private Contract[] deleteContract(Contract[] old, int index) {
        var temp = new Contract[old.length];
        System.arraycopy(old, 0, temp, 0, index);
        System.arraycopy(old, index + 1, temp, index, currentIndex - index - 1);
        return temp;
    }

    /**
     * Get contract by given id
     *
     * @param id The contract index to be obtained
     * @return The contract with given index
     */
    public Contract getContractById(UUID id) {
        return Arrays.stream(storedContracts)
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Contract with id: " + id + " doesn't exist."));
    }

    /**
     * Expand capacity of the repository array
     *
     * @param old The old repository array
     * @return The new repository array with increased capacity
     */
    private Contract[] expandArr(Contract[] old) {
        var temp = Arrays.copyOf(old, old.length);
        old = new Contract[temp.length * 2];
        System.arraycopy(temp, 0, old, 0, temp.length);
        return old;
    }

    /**
     * Search by a given criterion
     * это жесткий костыль, но пока так
     *
     * @param criterion A given criterion
     * @param actual    Class of the
     * @param <T>       Generics
     * @return Result of searching
     */
    public <T extends Contract> Repository search(Predicate<T> criterion, Class<T> actual) {
        return new Repository(Arrays.stream(storedContracts)
                .filter(actual::isInstance)
                .filter(x -> criterion.test((T) x))
                .toArray(Contract[]::new));
    }


    /**
     * @param criterion A given criterion
     * @return New Repository with filtered contracts
     */
    public Repository search(Predicate<Contract> criterion) {
        return new Repository(Arrays.stream(storedContracts)
                .filter(x -> {
                        try {
                            return criterion.test(x);
                        } catch (NullPointerException e) {
                            return false;
                        }
                })
                .toArray(Contract[]::new));
}


    public Repository bubbleSort(Comparator<Contract> comp) {
        return new BubbleSorter().sort(storedContracts, comp, currentIndex);
    }

    public Repository insertionSort(Comparator<Contract> comp) {
        return new InsertionSorter().sort(storedContracts, comp, currentIndex);
    }

    public Contract getByIndex(int index) {
        if (index >= currentIndex || index < 0)
            throw new NoSuchElementException("Incorrect index!");
        return storedContracts[index];
    }

    public Contract[] getStoredContracts() {
        return Arrays.copyOf(storedContracts, currentIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return Arrays.equals(storedContracts, that.storedContracts);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(storedContracts);
    }
}