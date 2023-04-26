package groupthree.shopflipmart.repository;

import groupthree.shopflipmart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update user u set u.password = ?1 where u.email= ?2")
    void updatePasswordByEmail(String password, String email);

}
