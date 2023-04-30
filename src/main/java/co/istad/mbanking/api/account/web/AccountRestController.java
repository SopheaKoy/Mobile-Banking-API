package co.istad.mbanking.api.account.web;

import co.istad.mbanking.base.BaseRest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AccountRestController {

    @GetMapping
    public BaseRest<?> findAll(){
        return null;
    }
}
