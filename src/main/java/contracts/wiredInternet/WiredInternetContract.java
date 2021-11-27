package contracts.wiredInternet;

import contracts.Contract;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import user.User;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class WiredInternetContract extends Contract {
    private double speedOfCon;

    @Builder
    public WiredInternetContract(UUID id, LocalDate startDate, LocalDate endDate, int pinNumber, User user, double speedOfCon) {
        super(id, startDate, endDate, pinNumber, user);
        this.speedOfCon = speedOfCon;
    }

    @Override
    public String toString() {
        return "WiredInternet. User:{" + getUser().getName()
                + "}; Contract id: " + getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WiredInternetContract that = (WiredInternetContract) o;
        return Double.compare(that.speedOfCon, speedOfCon) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speedOfCon);
    }
}
