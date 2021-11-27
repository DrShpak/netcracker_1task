package test;

import contracts.Contract;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.Repository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TestSorter {

    Repository repository;

    @BeforeEach
    public void init() {
        repository = InitRepo.InitializeRepo();
    }

    @Test
    public void testBubbleSort() {
        var actual = repository.bubbleSort(Comparator.comparing(Contract::getStartDate)).getStoredContracts();
        var expected = Arrays.stream(repository.getStoredContracts())
                .sorted(Comparator.comparing(Contract::getStartDate))
                .toArray(Contract[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testInsertionSort() {
        var actual = repository.insertionSort(Comparator.comparingInt(Contract::getPinNumber)).getStoredContracts();
        var expected = Arrays.stream(repository.getStoredContracts())
                .sorted(Comparator.comparingInt(Contract::getPinNumber))
                .toArray(Contract[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getByIndex() {
        var actual = repository.insertionSort(Comparator.comparingInt(Contract::getPinNumber));
        var expected = new Repository(Arrays.stream(repository.getStoredContracts())
                .sorted(Comparator.comparingInt(Contract::getPinNumber))
                .toArray(Contract[]::new));
        assertEquals(expected.getByIndex(0), actual.getByIndex(0));
    }

    @Test
    public void noSuchElementByIndex() {
        assertThrows(NoSuchElementException.class, () -> repository.getByIndex(-1));
    }
}
