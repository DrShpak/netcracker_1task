package test;

import contracts.digitalTV.DigitalTVContract;
import contracts.digitalTV.TVBundle;
import contracts.mobileInternet.MobileInternetContract;
import repository.Repository;
import user.Passport;
import user.Sex;
import user.User;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

public class InitRepo {
    private static UUID id = UUID.randomUUID();
    private static  Random random = new Random();

    public static Repository InitializeRepo() {
        var repo = new Repository();
        repo.addContracts(
                DigitalTVContract
                .builder()
                .id(id)
                .pinNumber(3)
                .startDate(LocalDate.of(2010, 12, 12))
                .endDate(LocalDate.of(2022, 2, 4))
                .user(User
                        .builder()
                        .id(UUID.randomUUID())
                        .age(20)
                        .birthDay(LocalDate.of(2000, 5, 16))
                        .name("Эдуард Лимонов")
                        .sex(Sex.MALE)
                        .passport(new Passport(2014, 123456))
                        .build()
                )
                .tvBundle(TVBundle.TARIFF1)
                .build(),
        MobileInternetContract
                .builder()
                .id(UUID.randomUUID())
                .pinNumber(random.nextInt())
                .startDate(LocalDate.of(2010, 12, 12))
                .endDate(LocalDate.of(2022, 2, 4))
                .user(User
                        .builder()
                        .id(UUID.randomUUID())
                        .age(20)
                        .birthDay(LocalDate.of(2000, 5, 16))
                        .name("Александр Овечкин")
                        .sex(Sex.MALE)
                        .passport(new Passport(2014, 123456))
                        .build()
                )
                .minutes(100)
                .sms(50)
                .netTraffic(10)
                .build(),
        MobileInternetContract
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
                        .name("Егор Летов")
                        .sex(Sex.MALE)
                        .passport(new Passport(1980, 122356))
                        .build()
                )
                .minutes(100)
                .sms(50)
                .netTraffic(10)
                .build());
        return repo;
    }
}