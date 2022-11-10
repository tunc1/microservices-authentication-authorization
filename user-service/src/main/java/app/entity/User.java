package app.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="users")
public class User implements UserDetails
{
    @Id
    @SequenceGenerator(name="User_SEQUENCE_GENERATOR",sequenceName="User_SEQUENCE",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="User_SEQUENCE_GENERATOR")
    private Long id;
    private String username,password;
    private boolean accountNonExpired,accountNonLocked,credentialsNonExpired,enabled;
    public boolean isAccountNonExpired()
    {
        return accountNonExpired;
    }
    public void setAccountNonExpired(boolean accountNonExpired)
    {
        this.accountNonExpired=accountNonExpired;
    }
    public boolean isAccountNonLocked()
    {
        return accountNonLocked;
    }
    public void setAccountNonLocked(boolean accountNonLocked)
    {
        this.accountNonLocked=accountNonLocked;
    }
    public boolean isCredentialsNonExpired()
    {
        return credentialsNonExpired;
    }
    public void setCredentialsNonExpired(boolean credentialsNonExpired)
    {
        this.credentialsNonExpired=credentialsNonExpired;
    }
    public boolean isEnabled()
    {
        return enabled;
    }
    public void setEnabled(boolean enabled)
    {
        this.enabled=enabled;
    }
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id=id;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username=username;
    }
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
}