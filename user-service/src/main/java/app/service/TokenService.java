package app.service;

import app.security.TokenUtil;
import org.springframework.stereotype.Service;

@Service
public class TokenService
{
    private TokenUtil tokenUtil;
    private UserService userService;
    public TokenService(TokenUtil tokenUtil,UserService userService)
    {
        this.tokenUtil=tokenUtil;
        this.userService=userService;
    }
    public boolean verify(String token)
    {
        if(tokenUtil.validate(token))
        {
            String username=tokenUtil.get(token,"username");
            return userService.existsByUsername(username);
        }
        return false;
    }
}