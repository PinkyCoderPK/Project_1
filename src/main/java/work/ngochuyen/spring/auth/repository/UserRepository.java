package work.ngochuyen.spring.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.ngochuyen.spring.auth.entity.User;

import java.util.Optional;

@Repository
//tuong tac voi User co id kieu long
//tuong tac voi data
//JPA co tat ca cac lenh tuong tac voi data
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
}
