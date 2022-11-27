package app.client;

import app.client.response.TokenInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url="${service.user-service}",value="user-service")
public interface UserServiceClient
{
    @PostMapping("/authenticate/info")
    TokenInfo info(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}