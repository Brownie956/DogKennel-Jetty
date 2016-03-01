package Login;/*Author: Chris Brown
* Date: 15/12/2015
* Description: A class to model a user*/

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<String> dogs = new ArrayList<String>();

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void addDog(String dogName){
        dogs.add(dogName);
    }

    public boolean ownsDog(String dogName){
        return dogs.contains(dogName);
    }

    public void removeDog(String dogName){
        dogs.remove(dogName);
    }

    @Override
    public boolean equals(Object user){
        if(user.getClass() != User.class) return false;
        else {
            User tempUser = (User) user;
            return this.username.equals(tempUser.username) && this.password.equals(tempUser.password);
        }
    }
}
