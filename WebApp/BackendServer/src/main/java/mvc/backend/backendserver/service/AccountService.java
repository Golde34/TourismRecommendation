package mvc.backend.backendserver.service;

import mvc.backend.backendserver.dto.AccountDTO;
import mvc.backend.backendserver.dto.RegisterResponse;
import mvc.backend.backendserver.entity.Account;
import mvc.backend.backendserver.entity.Role;
import mvc.backend.backendserver.repository.AccountRepo;
import mvc.backend.backendserver.repository.RoleRepo;
import mvc.backend.backendserver.service.interfaces.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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



}
