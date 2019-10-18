package se.topofminds.accelerate.SystemX.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import se.topofminds.accelerate.SystemX.entity.Privilege;
import se.topofminds.accelerate.SystemX.entity.User;
import se.topofminds.accelerate.SystemX.model.UserModel;
import se.topofminds.accelerate.SystemX.repository.UserRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@RestController
public class UserController {
    @Autowired
    UserRepo repo;

    @PostMapping("/users")
    public void createUser(@RequestBody UserModel user) throws Exception {
        User u = repo.findByUsername(user.getUsername());
        if(u != null){
            throw new Exception("User allready exists");
        }
        long id = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        u = new User();
        u.setId(id);
        u.setPassword(user.getPassword());
        u.setUsername(user.getUsername());
        List<Privilege> privs = new ArrayList<Privilege>();
        for(String p : user.getPrivileges()) {
            Privilege pr = new Privilege();
            pr.setUserId(id);
            pr.setPrivilege(p);
            privs.add(pr);
        }
        u.setPrivileges(privs);
        repo.save(u);
    }
}
