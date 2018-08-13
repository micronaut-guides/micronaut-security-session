package example.micronaut.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.Secured;
import io.micronaut.views.View;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Secured("isAnonymous()") // <1>
@Controller("/login")  // <2>
public class LoginAuthController {

    @Get("/auth") // <3>
    @View("auth") // <4>
    public Map<String, Object> auth() {
        return new HashMap<>();
    }

    @Get("/authFailed") // <5>
    @View("auth") // <4>
    public Map<String, Object> authFailed() {
        return Collections.singletonMap("errors", true);
    }
}
