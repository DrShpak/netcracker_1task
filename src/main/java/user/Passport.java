package user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Passport {
    private int serialNumber;
    private int number;
}
