package contracts;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvRecurse;
import lombok.*;
import user.User;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@ToString
public abstract class Contract {
    private UUID id;

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

    public Contract(LocalDate startDate, LocalDate endDate, int pinNumber, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.pinNumber = pinNumber;
        this.user = user;
    }

    public Contract() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return pinNumber == contract.pinNumber && Objects.equals(id, contract.id) && Objects.equals(startDate, contract.startDate) && Objects.equals(endDate, contract.endDate) && Objects.equals(user, contract.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, pinNumber, user);
    }
}
