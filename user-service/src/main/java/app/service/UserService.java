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
	private String adminUsername;
	@Value("${authentication.admin.password}")
	private String adminPassword;
	@Value("${authentication.user.username}")
	private String userUsername;
	@Value("${authentication.user.password}")
	private String userPassword;
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
	private void createAdminAndUser()
	{
		if(userRepository.count()==0)
		{
			User user=new User();
			user.setAccountNonExpired(true);
			user.setEnabled(true);
			user.setCredentialsNonExpired(true);
			user.setAccountNonLocked(true);
			user.setUsername(adminUsername);
			user.setPassword(adminPassword);
			user.setRole("ADMIN");
			save(user);
			User user1=new User();
			user1.setAccountNonExpired(true);
			user1.setEnabled(true);
			user1.setCredentialsNonExpired(true);
			user1.setAccountNonLocked(true);
			user1.setUsername(userUsername);
			user1.setPassword(userPassword);
			user1.setRole("USER");
			save(user1);
		}
	}
	public boolean existsByUsername(String username)
	{
		return userRepository.existsByUsername(username);
	}
}