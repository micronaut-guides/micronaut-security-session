package example.micronaut.controllers;

import example.micronaut.services.VelocityService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.Secured;

import javax.annotation.Nullable;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Secured("isAnonymous()") // <1>
@Controller("/")  // <2>
public class HomeController {

    protected final VelocityService velocityService;

    public HomeController(VelocityService velocityService) { // <3>
        this.velocityService = velocityService;
    }

    @Produces(MediaType.TEXT_HTML) // <4>
    @Get("/")  // <5>
    String index(@Nullable Principal principal) {  // <6>
        return velocityService.render("home.vm", homeModel(principal));
    }

    private Map homeModel(@Nullable Principal principal) {
        Map<String, Object> data = new HashMap<>();
        data.put("loggedIn", principal!=null);
        if (principal!=null) {
            data.put("username", principal.getName());
        }
        return data;
    }
}
