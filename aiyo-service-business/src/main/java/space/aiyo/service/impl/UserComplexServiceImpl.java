package space.aiyo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import space.aiyo.entity.AccountRepositoryEntity;
import space.aiyo.repository.AccountRepository;
import space.aiyo.repository.AuthRepository;
import space.aiyo.repository.UserRepository;
import space.aiyo.service.UserComplexService;

/**
 * CREATE BY Yo ON 2018/1/21 13:04
 */
@Service("userComplexService")
public class UserComplexServiceImpl implements UserComplexService {

  @Value("${user.salt}")
  private String salt;
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private AuthRepository authRepository;
  @Autowired
  private UserRepository userRepository;


  @Override
  public void register(String username, String password) {

  }

  @Override
  public void userLogin() {

  }

  @Override
  public void phoneLogin() {

  }

  private void addAccount() {

    AccountRepositoryEntity account = new AccountRepositoryEntity();

    accountRepository.save(account);
  }

  private void addUser() {

  }


}
