package se.topofminds.accelerate.SystemX.model;

import se.topofminds.accelerate.SystemX.entity.Privilege;
import se.topofminds.accelerate.SystemX.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserModel {

    public static UserModel fromUser(User entity) {
        UserModel model = new UserModel();
        model.setUsername(entity.getUsername());
        model.setPassword(entity.getPassword());
        model.setPrivileges(
                entity.getPrivileges().stream()
                .map(Privilege::getPrivilege)
                .collect(Collectors.toList())
                );
        return model;
    }
    private String username;
    private String password;
    private List<String> privileges;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }
}
