package com.fitness.gateway;

import com.fitness.gateway.dto.RegisterRequestDto;
import com.fitness.gateway.service.UserService;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class KeycloakUserSyncFilter implements WebFilter {

    private final UserService userService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String userId = exchange.getRequest().getHeaders().getFirst("X-User-ID");
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (userId != null && token != null) {
            return userService.validateUser(userId)
                    .flatMap(exist -> {
                        if (!exist) {
                            RegisterRequestDto registerRequest = getUserDetails(token);
                            if (registerRequest != null) {
                                return userService.registerUser(registerRequest)
                                        .then(Mono.empty());
                            } else {
                                return Mono.empty();
                            }
                        } else {
                            log.info("User already exist, Skipping sync.");
                            return Mono.empty();
                        }
                    })
                    .then(Mono.defer(() -> {
                        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                                .header("X-USER-ID", userId)
                                .build();
                        return chain.filter(exchange.mutate().request(mutatedRequest).build());
                    }));

        }
        return chain.filter(exchange);

    }


    private RegisterRequestDto getUserDetails(String token) {
        try{

            String tokenWithoutBearer = token.replace("Bearer", "").trim();
            SignedJWT signedJWT = SignedJWT.parse(tokenWithoutBearer);
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

            RegisterRequestDto registerRequest = new RegisterRequestDto();
            registerRequest.setEmail(claims.getStringClaim("email"));
            registerRequest.setKeycloakId(claims.getStringClaim("sub"));
            registerRequest.setPassword("dummy@123");
            registerRequest.setFirstName(claims.getStringClaim("given_name"));
            registerRequest.setLastName(claims.getStringClaim("family_name"));
            return registerRequest;


        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
