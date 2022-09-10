package mvc.backend.backendserver.controller;


import mvc.backend.backendserver.dto.AccountDTO;
import mvc.backend.backendserver.entity.Account;
import mvc.backend.backendserver.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @PostMapping(value = "/admin/editAccount")
    public String editUserByAdmin(){
        return "Admin edited.";
    }
}
