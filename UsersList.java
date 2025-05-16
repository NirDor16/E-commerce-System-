package InbarSarIsrael323101485NirDor212779243;

import java.util.Arrays;

public class UsersList {
    private User[] users = new User[1];
    private int numsOfUsers = 0;

    public int getSize() {
        return this.numsOfUsers;
    }

    public User[] getUsers() {
        return this.users;
    }

    public String getUserNames() {
        String userNames = "User Names:\n";
        for (int i = 0; i < this.numsOfUsers; i++) {
            userNames += (i + 1) + ". Name: " + this.users[i].getName() + "\n";
        }
        return userNames;
    }

    public void addUser(User user) {
        if (this.numsOfUsers == this.users.length) {
            this.users = Arrays.copyOf(this.users, this.users.length * 2);
        }
        this.users[this.numsOfUsers] = user;
        this.numsOfUsers++;
    }

    public User checkUserExist(String name) {
        for (int i = 0; i < this.numsOfUsers; i++) {
            if (name.equalsIgnoreCase(this.users[i].getName())) {
                return this.users[i];
            }
        }
        return null;
    }

    public User get(int i) throws Exception {
        if (i < 0 || i > this.numsOfUsers - 1) {
            throw new Exception("Incorrect user index: " + i);
        }
        return this.users[i];
    }

    public String toString() {
        String print = "";
        if (this.numsOfUsers == 0) {
            print += "The list is currently empty.";
        } else {
            print += "The names of the users are:";
            for (int i = 0; i < this.numsOfUsers; i++) {
                print += "\n\n" + (i + 1) + ". " + this.users[i];
            }
        }
        return print;
    }
}