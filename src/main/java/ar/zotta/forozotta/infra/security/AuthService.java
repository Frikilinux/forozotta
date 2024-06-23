package ar.zotta.forozotta.infra.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.zotta.forozotta.domain.user.User;
import ar.zotta.forozotta.domain.user.UserRepository;

@Service
public class AuthService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findUserByUsername(email);
  }

  public User getAutorizedUser() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  public Boolean checkOwner(Long ownerId) {
    Optional<User> user = userRepository.findUserById(ownerId);

    if (user.isEmpty()) {
      throw new RuntimeException("El propietario del topic no existe");
    }

    User loggedUser = getAutorizedUser();

    return !ownerId.equals(loggedUser.getId());

  }
}
