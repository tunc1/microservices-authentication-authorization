package app.service;

import app.controller.response.TokenInfo;
import app.entity.User;
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
    public TokenInfo info(String token)
    {
        if(tokenUtil.validate(token))
        {
            String username=tokenUtil.get(token,"username");
            User user=userService.findByUsername(username);
            if(user==null)
                return new TokenInfo(false,null);
            else
                return new TokenInfo(true,user.getRole());
        }
        return new TokenInfo(false,null);
    }
}