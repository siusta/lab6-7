package pl.siusta.why.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siusta.why.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
