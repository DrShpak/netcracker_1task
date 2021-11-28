package test;

import loader.Loader;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import repository.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFileLoader {

    private final String path = "src/main/resources/test.csv";
    private final Repository repo = new Repository();

    @SneakyThrows
    @Test
    public void load() {
        Loader.load(path, repo);
        assertEquals(3, repo.getCurrentIndex());
    }
}
