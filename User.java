package InbarSarIsrael323101485NirDor212779243;

public abstract class User {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(User other) {
        this.name = other.name;
        this.password = other.password;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public boolean setPassword(String password) {
        this.password = password;
        return true;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String toString() {
        return "Name: " + this.name + ", Password: " + this.password;
    }
}

