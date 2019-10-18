package se.topofminds.accelerate.SystemX.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import se.topofminds.accelerate.SystemX.entity.Privilege;
import se.topofminds.accelerate.SystemX.entity.User;
import se.topofminds.accelerate.SystemX.model.UserModel;
import se.topofminds.accelerate.SystemX.repository.UserRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    UserRepo repo;

    @PostMapping("/users")
    public void createUser(@RequestBody UserModel user) throws Exception {
        User u = repo.findByUsername(user.getUsername());
        if (u != null) {
            throw new Exception("User already exists");
        }
        long id = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        u = new User();
        u.setId(id);
        u.setPassword(user.getPassword());
        u.setUsername(user.getUsername());
        List<Privilege> privs = new ArrayList<Privilege>();
        for (String p : user.getPrivileges()) {
            Privilege pr = new Privilege();
            pr.setUserId(id);
            pr.setPrivilege(p);
            privs.add(pr);
        }
        u.setPrivileges(privs);
        repo.save(u);
    }

    @GetMapping("/users")
    public List<UserModel> listUsers() throws Exception {
        List<UserModel> users = new ArrayList<>();
        repo.findAll().forEach(entity -> users.add(UserModel.fromUser(entity)));
        return users;
    }

    @PutMapping("/users/{username}/privileges")
    public void updateUser(@RequestBody List<String> privileges, @PathVariable String username) throws Exception {
        User user = repo.findByUsername(username);
        if (user == null) {
            throw new Exception("User doesn't exist");
        }
        List<Privilege> updatedPrivileges = privileges.stream()
                .map((String privilegeString) -> {
                    Privilege privilege = new Privilege();
                    privilege.setPrivilege(privilegeString);
                    privilege.setUserId(user.getId());
                    return privilege;
                })
                .collect(Collectors.toList());
        user.setPrivileges(updatedPrivileges);
        repo.save(user);

    }
}
