package ar.zotta.forozotta.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.email = :email")
  public Optional<User> findUserByEmail(String email);

  @Query("SELECT u FROM User u WHERE u.email = :email")
  public UserDetails findUserByUsername(String email);

}
