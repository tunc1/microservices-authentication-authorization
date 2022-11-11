package app.filter;

import app.client.UserServiceClient;
import app.client.response.TokenInfo;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter
{
    private UserServiceClient userServiceClient;
    public AuthenticationFilter(UserServiceClient userServiceClient)
    {
        this.userServiceClient=userServiceClient;
    }
    public Mono<Void> filter(ServerWebExchange exchange,GatewayFilterChain chain)
    {
        ServerHttpRequest request=exchange.getRequest();
        if(request.getMethod().equals(HttpMethod.GET))
            return chain.filter(exchange);
        if(request.getMethod().equals(HttpMethod.POST)&&request.getPath().value().equals("/authenticate"))
            return chain.filter(exchange);
        String authorization=request.getHeaders().getFirst("Authorization");
        if(authorization!=null)
        {
            if(authorization.contains("Bearer "))
            {
                TokenInfo tokenInfo=userServiceClient.info(authorization);
                if(tokenInfo.verified())
                {
                    if(request.getMethod().equals(HttpMethod.POST)&&request.getPath().value().equals("/content"))
                        return chain.filter(exchange);
                    else if(tokenInfo.role().equals("ADMIN"))
                        return chain.filter(exchange);
                }
            }
        }
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}
