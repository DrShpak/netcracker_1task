package contracts.mobileInternet;

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
public class MobileInternetContract extends Contract {
    private int minutes;
    private int sms;
    private int netTraffic;

    @Builder
    public MobileInternetContract(UUID id, LocalDate startDate, LocalDate endDate, int pinNumber, User user, int minutes, int sms, int netTraffic) {
        super(id, startDate, endDate, pinNumber, user);
        this.minutes = minutes;
        this.sms = sms;
        this.netTraffic = netTraffic;
    }

    @Override
    public String toString() {
        return "MobileInternetContract. User:{" + getUser().getName()
                + "}; Contract id: " + getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MobileInternetContract that = (MobileInternetContract) o;
        return minutes == that.minutes && sms == that.sms && netTraffic == that.netTraffic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), minutes, sms, netTraffic);
    }
}
