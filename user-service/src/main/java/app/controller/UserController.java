package app.controller;

import app.dto.UserDTO;
import app.entity.User;
import app.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/user")
public class UserController
{
	private UserService userService;
	public UserController(UserService userService)
	{
		this.userService=userService;
	}
	@GetMapping("/{username}")
	public UserDTO findByUsername(@PathVariable String username)
	{
		User user=userService.findByUsername(username);
		return new UserDTO(user.getId(),user.getUsername());
	}
}