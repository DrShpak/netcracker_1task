import annotations.Injectable;
import user.User;

public class Test2 {

    @Injectable
    public User user;

    public Test2(User user) {
        this.user = user;
    }

    public Test2() {

    }
}
