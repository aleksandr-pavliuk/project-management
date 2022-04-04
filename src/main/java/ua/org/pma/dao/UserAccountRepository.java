package ua.org.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.org.pma.entity.UserAccount;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Repository
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {

}
