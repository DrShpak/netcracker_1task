package test;

import annotations.Injector;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import repository.Repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestInject {

    @SneakyThrows
    @Test
    public void load() {
        Repository repo = new Repository();
        Injector.inject(repo);
        assertNotNull(repo.getSorter());
    }
}
