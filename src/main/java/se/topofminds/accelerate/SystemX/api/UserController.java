package se.topofminds.accelerate.SystemX.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.topofminds.accelerate.SystemX.entity.User;
import se.topofminds.accelerate.SystemX.repository.UserRepo;

@RestController
public class UserController {
    @Autowired
    UserRepo repo;

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        repo.save(user);
    }
}
