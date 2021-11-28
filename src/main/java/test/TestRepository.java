package test;

import contracts.Contract;
import contracts.digitalTV.DigitalTVContract;
import contracts.mobileInternet.MobileInternetContract;
import contracts.digitalTV.TVBundle;
import loader.Loader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.Repository;
import user.Passport;
import user.Sex;
import user.User;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TestRepository {
    private Repository rep = new Repository();
    private UUID id = UUID.randomUUID();
    private Random random = new Random();

    private Contract contract1 = DigitalTVContract
            .builder()
            .id(id)
            .pinNumber(random.nextInt())
            .startDate(LocalDate.of(2010, 12, 12))
            .endDate(LocalDate.of(2022, 2, 4))
            .user(User
                    .builder()
                    .id(UUID.randomUUID())
                    .age(20)
                    .birthDay(LocalDate.of(2000, 5, 16))
                    .name("Егор летов")
                    .sex(Sex.MALE)
                    .passport(new Passport(2014, 123456))
                    .build()
            )
            .tvBundle(TVBundle.TARIFF1)
            .build();

    private Contract contract2 = MobileInternetContract
            .builder()
            .id(UUID.randomUUID())
            .pinNumber(random.nextInt())
            .startDate(LocalDate.of(2010, 12, 12))
            .endDate(LocalDate.of(2022, 2, 4))
            .user(User
                    .builder()
                    .id(UUID.randomUUID())
                    .age(20)
                    .birthDay(LocalDate.of(1946, 10, 10))
                    .name("Эдуард Лимонов")
                    .sex(Sex.MALE)
                    .passport(new Passport(2014, 123456))
                    .build()
            )
            .minutes(100)
            .sms(50)
            .netTraffic(10)
            .build();


    private Contract contract3 = MobileInternetContract
            .builder()
            .id(UUID.randomUUID())
            .pinNumber(random.nextInt())
            .startDate(LocalDate.of(2000, 12, 12))
            .endDate(LocalDate.of(2022, 2, 4))
            .user(User
                    .builder()
                    .id(UUID.randomUUID())
                    .age(20)
                    .birthDay(LocalDate.of(1955, 5, 16))
                    .name("Владимир Путин")
                    .sex(Sex.MALE)
                    .passport(new Passport(1980, 122356))
                    .build()
            )
            .minutes(100)
            .sms(50)
            .netTraffic(10)
            .build();

    @BeforeEach
    void setup() {
        rep.addContracts(contract1, contract2, contract3);
    }


    @Test
    public void addContracts() {
        var actual = rep.getStoredContracts();
        Contract[] expected = {contract1, contract2, contract3};
        assertArrayEquals(expected, actual);
    }

    @Test
    void getContractsNotNull() {
        assertNotNull(rep.getStoredContracts());
    }

    @Test
    void getContractByIdException() {
        assertThrows(NoSuchElementException.class, () -> rep.getContractById(UUID.randomUUID()));
    }

    @Test
    void getContractById() {
        assertNotNull(rep.getContractById(contract1.getId()));
    }

    @Test
    void deleteContractById() {
        rep.deleteContractById(id);
        var actual = rep.getStoredContracts();
        Contract[] expected = {contract2, contract3, null, null};

        //почему так мало информации выводится если использовать assertArrayEquals ????
        assertEquals(Arrays.asList(expected), Arrays.asList(actual));
    }

    @Test
    void deleteContractByIdExceptionCheck() {
        assertThrows(NoSuchElementException.class, () -> rep.deleteContractById(UUID.randomUUID()));
    }
}