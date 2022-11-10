package app.security;

import app.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService
{
    private UserService userService;
    public UserDetailsServiceImpl(UserService userService)
    {
        this.userService=userService;
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return userService.findByUsername(username);
    }
}