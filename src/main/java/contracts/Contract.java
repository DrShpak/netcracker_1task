package contracts;

import lombok.*;
import user.User;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
//@EqualsAndHashCode
public abstract class Contract {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int pinNumber;
    private User user;

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
