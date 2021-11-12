package test;

import contracts.Contract;
import org.junit.jupiter.api.Test;
import repository.Repository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestSearch {
    Repository repository = InitRepo.InitRepo();

    @Test
    public void search() {
        var expected = repository.search(x -> x.getPinNumber() > 1);
        var actual = new Repository(
                Arrays.stream(repository.getStoredContracts())
                        .filter(x -> x.getPinNumber() > 1)
                        .toArray(Contract[]::new));
        assertEquals(actual, expected);
    }
}
