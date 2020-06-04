package api;

public class UserNotFoundExpection extends RuntimeException {

    public UserNotFoundExpection(Long id) {
        super("Could not find employee " + id);
    }
}
