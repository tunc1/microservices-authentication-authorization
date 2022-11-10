package app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import app.entity.User;
import app.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

@Service
public class UserService
{
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	@Value("${authentication.admin.username}")
	private String username;
	@Value("${authentication.admin.password}")
	private String password;
	public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder)
	{
		this.userRepository=userRepository;
		this.passwordEncoder=passwordEncoder;
	}
	public User save(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	public User findByUsername(String username)
	{
		return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("No User Found by this Username"));
	}
	@PostConstruct
	private void createAdmin()
	{
		if(userRepository.count()==0)
		{
			User user=new User();
			user.setAccountNonExpired(true);
			user.setEnabled(true);
			user.setCredentialsNonExpired(true);
			user.setAccountNonLocked(true);
			user.setUsername(username);
			user.setPassword(password);
			save(user);
		}
	}
	public boolean existsByUsername(String username)
	{
		return userRepository.existsByUsername(username);
	}
}