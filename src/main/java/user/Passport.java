package user;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Passport {
    @CsvBindByName
    private int series;

    @CsvBindByName
    private int number;
}
