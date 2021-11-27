import contracts.Contract;
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
import java.util.function.Predicate;

public class Main {
    private static UUID id = UUID.randomUUID();
    private static Random random = new Random();

    private static Contract contract1 = DigitalTVContract
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

    private static Contract contract2 = MobileInternetContract
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
                    .name("Эдуард Лимонов")
                    .sex(Sex.MALE)
                    .passport(new Passport(2014, 123456))
                    .build()
            )
            .minutes(200)
            .sms(50)
            .netTraffic(10)
            .build();


    private static Contract contract3 = MobileInternetContract
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

    public static void main(String[] args) {


//        var rep = new Repository();
//        rep.addContracts(contract1, contract2, contract3);
//        Predicate<MobileInternetContract> test = x -> x.getMinutes() == 100;
//        System.out.println(rep.search(test, MobileInternetContract.class));

//        src/main/java/testCodeNoGit/test.csv
    }
}