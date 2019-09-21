package se.topofminds.accelerate.SystemX.api;

import org.springframework.web.bind.annotation.*;
import se.topofminds.accelerate.SystemX.model.Login;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String greeting(@RequestBody Login login) {
        return "Hello";
    }
    @GetMapping("/test")
    public String greeting() {
        return "Hello";
    }


}
