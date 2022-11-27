package app.controller;

import app.controller.response.MessageResponse;
import app.controller.response.TokenInfo;
import app.controller.response.TokenResponse;
import app.entity.User;
import app.security.TokenUtil;
import app.service.TokenService;
import app.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
public class Authenticate
{
    private AuthenticationManager authenticationManager;
    private TokenUtil tokenUtil;
    private UserService userService;
    private TokenService tokenService;
    public Authenticate(AuthenticationManager authenticationManager,TokenUtil tokenUtil,UserService userService,TokenService tokenService)
    {
        this.authenticationManager=authenticationManager;
        this.tokenUtil=tokenUtil;
        this.userService=userService;
        this.tokenService=tokenService;
    }
    @PostMapping
    public ResponseEntity<Object> authenticate(@RequestBody User user)
    {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        try
        {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            User userDetails=userService.findByUsername(user.getUsername());
            String token=tokenUtil.create(userDetails);
            return new ResponseEntity<>(new TokenResponse(token),HttpStatus.OK);
        }
        catch(AuthenticationException e)
        {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/info")
    public TokenInfo info(@RequestHeader(HttpHeaders.AUTHORIZATION) String token)
    {
        return tokenService.info(token);
    }
}