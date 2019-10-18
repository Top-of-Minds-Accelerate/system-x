package se.topofminds.accelerate.SystemX.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.topofminds.accelerate.SystemX.entity.Privilege;
import se.topofminds.accelerate.SystemX.entity.User;
import se.topofminds.accelerate.SystemX.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysXUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo repo;

    @Autowired
    public PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Privilege p : user.getPrivileges()){
            authorities.add(new SimpleGrantedAuthority(p.getPrivilege()));
        }

        UserDetails ud = new org.springframework.security.core.userdetails.User(user.getUsername(), encoder.encode(user.getPassword()) , authorities );
        return ud;
    }
}
