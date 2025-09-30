package site.zxhy.amyadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AmyAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmyAdminApplication.class, args);
    }

    @GetMapping("/")
    public String index(){
        System.out.println("6666");
        return "amy";
    }
}
