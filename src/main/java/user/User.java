package user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter@Setter
@Builder
@AllArgsConstructor
public class User {
    private UUID id;
    private String name;
    private LocalDate birthDay;
    private Passport passport;
    private Sex sex;
    private int age;
}
