package ro.utcn.fulgadan.BookStore.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.fulgadan.BookStore.data.entity.User;
import ro.utcn.fulgadan.BookStore.data.repository.UserJpaRepository;

@Service
public class UserService {
    @Autowired
    UserJpaRepository userJpaRepository;

    //return 0 for admin, 1 for user and 2 for not found
    public Integer checkCredentials(String username, String password) {
        if(username.equals("") || password.equals(""))
            return 2;
        User toCheck = userJpaRepository.findByUsername(username);
        if(toCheck.getPassword().equals(password))
            return toCheck.getType();
        else
            return 2;
    }

    public boolean checkUsername(String username) {
        User toCheck = userJpaRepository.findByUsername(username);
        if (toCheck == null)
            return true;
        else
            return false;
    }

    public void insertUser(User user) {
        userJpaRepository.save(user);
    }

    public User findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }
}
