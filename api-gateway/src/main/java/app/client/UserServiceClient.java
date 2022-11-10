package app.client;

import app.client.response.TokenVerifyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url="${service.user-service}",value="user-service")
public interface UserServiceClient
{
    @PostMapping("/authenticate/verify")
    TokenVerifyResponse verify(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization);
}