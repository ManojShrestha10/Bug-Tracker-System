package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
