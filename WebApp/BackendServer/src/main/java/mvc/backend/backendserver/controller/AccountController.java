package mvc.backend.backendserver.controller;


import mvc.backend.backendserver.dto.AccountDTO;
import mvc.backend.backendserver.entity.Account;
import mvc.backend.backendserver.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @PostMapping(value = "/admin/editAccount")
    public String editUserByAdmin(){
        return "Admin edited.";
    }

    @PostMapping(value = "/login")
    public Account login(@RequestBody AccountDTO accountDTO) {
        return accountService.login(accountDTO.getUsername(), accountDTO.getPassword());
    }

    @PostMapping(value = "/signup")
    public Map<String, String> signUp(@RequestBody AccountDTO accountDTO) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", accountService.signUp(accountDTO));
        return map;
    }
}
