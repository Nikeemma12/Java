package SchoolManagementSystem;

public abstract class Person {
    private String name;
    private String id;
    private String password;
    private String email;

    Person (String name,String email) {
        this.name = name;
        this.id = "";
        this.email = email;
        this.password = "";
    }

    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return this.name;
    }

    void setId(String id) {
        this.id = id;
    }
    String getId() {
        return this.id;
    }

    void setPassword(String password) {
        this.password = password;
    }
    String getPassword() {
        return this.password;
    }

    void setEmail(String email) {
        this.email = email;
    }
    String getEmail() {
        return this.email;
    }
    abstract String getDetails();

    @Override
    public String toString() {
        return this.id + "- " + this.name + ": " + this.email + "_ " + "Password- " +this.password;
    }

    abstract String getid();
}
