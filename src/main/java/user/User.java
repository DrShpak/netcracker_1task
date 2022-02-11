package user;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    private UUID id = UUID.randomUUID();

    @CsvBindByName
    private String name;

    @CsvBindByName
    @CsvDate("dd.MM.yyyy")
    private LocalDate birthDay;

    @CsvCustomBindByName(converter = PassportConverter.class)
    private Passport passport;

    @CsvBindByName
    private Sex sex;

    @CsvBindByName
    private int age;
}
