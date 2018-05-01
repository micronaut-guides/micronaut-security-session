package demo.controllers;

import demo.services.VelocityService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.Secured;

import java.util.HashMap;
import java.util.Map;

@Secured("isAnonymous()") // <1>
@Controller("/login")  // <2>
public class LoginAuthController {

    protected final VelocityService velocityService;

    public LoginAuthController(VelocityService velocityService) {  // <3>
        this.velocityService = velocityService;
    }

    @Produces(MediaType.TEXT_HTML) // <4>
    @Get("/auth") // <5>
    public String auth() {
        return velocityService.render("auth.vm", new HashMap<>()); // <6>
    }

    @Produces(MediaType.TEXT_HTML) // <4>
    @Get("/authFailed") // <7>
    public String authFailed() {
        return velocityService.render("auth.vm", authFailedModel()); // <6>
    }

    private Map<String, Object> authFailedModel() {
        Map<String, Object> data = new HashMap<>();
        data.put("errors", true);
        return data;
    }
}
