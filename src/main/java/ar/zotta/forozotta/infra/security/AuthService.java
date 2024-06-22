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

  public Boolean checkOwner(String ownerEmail) {
    Optional<User> user = userRepository.findUserByEmail(ownerEmail);

    if (user.isEmpty()) {
      throw new RuntimeException("El propietario del topic no existe");
    }

    User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return !user.get().getEmail().toString().equals(loggedUser.getEmail().toString());

  }
}
