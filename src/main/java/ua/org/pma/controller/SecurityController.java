package ua.org.pma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.org.pma.dao.UserAccountRepository;
import ua.org.pma.entity.UserAccount;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Controller
public class SecurityController {


  @Autowired
  UserAccountRepository accountRepository;

  @Autowired
  BCryptPasswordEncoder bCryptEncoder;


  @GetMapping("/register")
  public String register(Model model) {
    UserAccount userAccount = new UserAccount();
    model.addAttribute("userAccount", userAccount);

    return "security/register";
  }

  @PostMapping("/register/save")
  public String saveUser(Model model, UserAccount user) {
    user.setPassword(bCryptEncoder.encode(user.getPassword()));
    accountRepository.save(user);
    return "redirect:/";
  }
}
