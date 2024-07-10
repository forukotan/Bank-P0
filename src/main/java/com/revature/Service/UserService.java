package com.revature.Service;

import com.revature.Entity.User;
import com.revature.exception.LoginFail;
import com.revature.repository.UserDao;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }
    public User validateNewCredentials(User newUserCredentials){
        //1. check if lengths are correct
            if(checkUserNamePasswordLength(newUserCredentials)){
                if(checkUsernameIsUnique(newUserCredentials)){
                    return userDao.createUser(newUserCredentials);

                }
            }
            throw new RuntimeException("place holder info");
        //2. check is user name is unique
        //.3.1 persist if user data is valid

    }

    public User checkLoginCredentials(User credentials){
        for(User user : userDao.getAllUsers()){
            boolean usernameMatches = user.getUsername().equals(credentials.getUsername());
            boolean passwordMatches = user.getPassword().equals(credentials.getPassword());
            if(usernameMatches && passwordMatches )
            {
                return credentials;
            }
        }
        throw new LoginFail("Credentials are invalid : please try again");

    }
    private boolean  checkUserNamePasswordLength(User newUserCredentials){
        boolean usernameValidLength = newUserCredentials.getUsername().length() <=30;
        boolean passwordValidLength = newUserCredentials.getPassword().length() <=30;
        return passwordValidLength && usernameValidLength;
    }

    private boolean checkUsernameIsUnique(User newUserCredentials){
        boolean usernameIsUnique= true;
        List<User> users =userDao.getAllUsers();
        for(User user: users){
            if(newUserCredentials.getUsername().equals((user.getUsername()))){
                usernameIsUnique =false;
                break;
            }
        }

        return usernameIsUnique;
    }


}
