package example.micronaut.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.views.View;

import javax.annotation.Nullable;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Secured("isAnonymous()") // <1>
@Controller("/")  // <2>
public class HomeController {

    @Get("/") // <3>
    @View("home") // <4>
    Map<String, Object> index(@Nullable Principal principal) { // <5>
        Map<String, Object> data = new HashMap<>();
        data.put("loggedIn", principal != null);
        if (principal != null) {
            data.put("username", principal.getName());
        }
        return data;
    }
}
