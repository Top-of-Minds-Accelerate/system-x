package se.topofminds.accelerate.SystemX.repository;

import org.springframework.data.repository.CrudRepository;
import se.topofminds.accelerate.SystemX.entity.User;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
