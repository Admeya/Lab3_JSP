package ru.lab5.controllers.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.lab5.Entities.ClientEntity;
import ru.lab5.services.IClientService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ирина on 13.03.2017.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private IClientService clientService;
    @Autowired
    private HttpServletRequest request;

    @Autowired(required = true)
    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("Login is " + s);

        ClientEntity newUser = clientService.authorize(s);
        System.out.println(newUser.getLogin() + " " + newUser.getPass());
        if (newUser == null) {
            throw new UsernameNotFoundException("User details not found with this username: " + s);
        }
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));

        request.getSession().setAttribute("PRINCIPAL", newUser);
        System.out.println(newUser.getPass().trim() + " non encrypted password");

        User user = new User(newUser.getLogin().trim(), newUser.getPass(), authList);

        return user;
    }
}
