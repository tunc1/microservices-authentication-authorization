package app.service;

import app.client.response.TokenInfo;
import app.client.UserServiceClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService
{
    private UserServiceClient userServiceClient;
    public AuthenticationService(UserServiceClient userServiceClient)
    {
        this.userServiceClient=userServiceClient;
    }
    @Cacheable(value="token",key="#token")
    public TokenInfo tokenInfo(String token)
    {
        return userServiceClient.info(token);
    }
}