package se.topofminds.accelerate.SystemX.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    long id;
    @Column(unique = true)
    String username;
    String password;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Role> roles;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Privilege> privileges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
