package contracts.contractProxy;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvRecurse;
import contracts.Contract;
import contracts.digitalTV.DigitalTVContract;
import contracts.digitalTV.TVBundle;
import contracts.mobileInternet.MobileInternetContract;
import contracts.wiredInternet.WiredInternetContract;
import lombok.*;
import user.User;

import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ContractProxy {
    @CsvBindByName
    @CsvDate("dd.MM.yyyy")
    private LocalDate startDate;

    @CsvBindByName
    @CsvDate("dd.MM.yyyy")
    private LocalDate endDate;

    @CsvBindByName
    private int pinNumber;

    @CsvRecurse
    private User user;

    @CsvBindByName
    private String additionalInfo;

    @CsvCustomBindByName(converter = TypeConverter.class)
    private Class<? extends Contract> type;

    //todo чет с исключением придумать
    public Contract turnIntoContract() {
        if (MobileInternetContract.class.equals(type)) {
            return getMobileInternetContract();
        } else if (WiredInternetContract.class.equals(type)) {
            return getWiredInternetContract();
        } else if (DigitalTVContract.class.equals(type)) {
            return getDigitalTVContract();
        } else throw new RuntimeException();
    }

    private DigitalTVContract getDigitalTVContract() {
        return DigitalTVContract.builder()
                .id(UUID.randomUUID())
                .startDate(startDate)
                .endDate(endDate)
                .pinNumber(pinNumber)
                .user(user)
                .tvBundle(TVBundle.valueOf(additionalInfo))
                .build();
    }

    private WiredInternetContract getWiredInternetContract() {
        return WiredInternetContract.builder()
                .id(UUID.randomUUID())
                .startDate(startDate)
                .endDate(endDate)
                .pinNumber(pinNumber)
                .user(user)
                .speedOfCon(Integer.parseInt(Pattern.compile("mbps=(\\d+)")
                        .matcher(additionalInfo)
                        .results()
                        .map(m -> m.group(1))
                        .findFirst()
                        .get()))
                .build();
    }

    private MobileInternetContract getMobileInternetContract() {
        return MobileInternetContract.builder()
                .id(UUID.randomUUID())
                .startDate(startDate)
                .endDate(endDate)
                .pinNumber(pinNumber)
                .user(user)
                .minutes(Integer.parseInt(Pattern.compile("min=(\\d+)")
                        .matcher(additionalInfo)
                        .results()
                        .map(m -> m.group(1))
                        .findFirst()
                        .get()))
                .sms(Integer.parseInt(Pattern.compile("sms=(\\d+)")
                        .matcher(additionalInfo)
                        .results()
                        .map(m -> m.group(1))
                        .findFirst()
                        .get()))
                .netTraffic(Integer.parseInt(Pattern.compile("gb=(\\d+)")
                        .matcher(additionalInfo)
                        .results()
                        .map(m -> m.group(1))
                        .findFirst()
                        .get()))
                .build();
    }
}