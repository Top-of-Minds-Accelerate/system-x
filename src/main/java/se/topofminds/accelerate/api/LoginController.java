package se.topofminds.accelerate.api;

import org.springframework.web.bind.annotation.*;
import se.topofminds.accelerate.model.Login;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String greeting(@RequestBody Login login) {
        return "Hello";
    }

}
