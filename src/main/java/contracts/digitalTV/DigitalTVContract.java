package contracts.digitalTV;

import contracts.Contract;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import user.User;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

//@EqualsAndHashCode
public class DigitalTVContract extends Contract {
    @Getter@Setter
    private TVBundle tvBundle;

    @Builder
    public DigitalTVContract(UUID id, LocalDate startDate, LocalDate endDate, int pinNumber, User user, TVBundle tvBundle) {
        super(id, startDate, endDate, pinNumber, user);
        this.tvBundle = tvBundle;
    }

    @Override
    public String toString() {
        return "DigitalTV. " + super.toString() + ", TVBundle=" + tvBundle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DigitalTVContract that = (DigitalTVContract) o;
        return tvBundle == that.tvBundle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tvBundle);
    }
}
