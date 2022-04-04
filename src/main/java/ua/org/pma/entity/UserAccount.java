package ua.org.pma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Entity
@Table(name = "user_accounts")
@Getter
@Setter
@NoArgsConstructor
public class UserAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_accounts_seq")
  @Column(name = "user_id")
  @SequenceGenerator(name = "user_accounts_seq", allocationSize = 1)
  private long userId;

  @Column(name = "username")
  private String userName;

  private String email;

  private String password;

  private boolean enabled = true;

}
