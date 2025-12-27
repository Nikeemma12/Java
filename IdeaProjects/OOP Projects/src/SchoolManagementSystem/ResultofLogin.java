package SchoolManagementSystem;

public class ResultofLogin<T> {

    boolean success;
    T user;
    ResultofLogin(boolean success, T user) {
        this.success = success;
        this.user = user;
    }

    boolean getSuccess () {
        return this.success;
    }
    T getUser() {
        return this.user;
    }
}
