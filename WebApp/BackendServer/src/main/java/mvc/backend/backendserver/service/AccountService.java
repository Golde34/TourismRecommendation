package mvc.backend.backendserver.service;

import mvc.backend.backendserver.dto.AccountDTO;
import mvc.backend.backendserver.entity.Account;
import mvc.backend.backendserver.entity.Role;
import mvc.backend.backendserver.repository.RoleRepo;
import mvc.backend.backendserver.repository.AccountRepo;
import mvc.backend.backendserver.service.interfaces.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private final AccountRepo accountRepo;
    @Autowired
    private final RoleRepo roleRepo;
    @Autowired
    private ModelMapper mapper;

    public AccountService(AccountRepo accountRepo, RoleRepo roleRepo, ModelMapper mapper) {
        this.accountRepo = accountRepo;
        this.roleRepo = roleRepo;
        this.mapper = mapper;
    }


    @Override
    public Account login(String username, String password) {
        if (username.trim().equals("") ||
                password.trim().equals("")) {
            return null;
        }

        Account account = accountRepo.findAccountByUsername(username);

        if (null != account && account.getPassword().equals(password)) {
            return account;
        }

        return null;
    }

    @Override
    public String signUp(AccountDTO accountDTO) {
        String responseMessage = "Signup successfully";
        System.out.println("DEBUG" + accountDTO.toString());

        if (accountDTO.getUsername().trim().equals("") ||
                accountDTO.getPassword().trim().equals("") ||
                accountDTO.getEmail().trim().equals("") ||
                accountDTO.getPhoneNumber().trim().equals("")) {
            responseMessage = "Input cannot be blank";
        }

        if (accountRepo.existsAccountByUsername(accountDTO.getUsername())) {
            responseMessage = "Username is duplicated";
            return responseMessage;
        }

        if (accountRepo.existsAccountByEmail(accountDTO.getEmail())) {
            responseMessage = "Email is duplicated";
            return responseMessage;
        }

        if (accountRepo.existsAccountByPhoneNumber(accountDTO.getPhoneNumber())) {
            responseMessage = "Phone number is duplicated";
            return responseMessage;
        }

        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
        account.setPassword(accountDTO.getPassword());
        account.setEmail(accountDTO.getEmail());
        account.setPhoneNumber(accountDTO.getPhoneNumber());
        account.setFullName(accountDTO.getUsername());
        account.setStatus(1);

        Role role = roleRepo.findByRoleName("user");
        account.setRole(role);

        accountRepo.save(account);
        return responseMessage;
    }

}
