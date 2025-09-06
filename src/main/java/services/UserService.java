package services;

import java.util.Optional;
import Entity.User;

public interface UserService {

    // save user details
    User saveUser(User user);

    // fetch user details by id
    Optional<User> getUserById(Long id);

    // update user details
    Optional<User> updateUser(User user);

    // delete user by id
    void deleteUserById(Long id);

}
