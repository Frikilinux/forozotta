package ar.zotta.forozotta.domain.User;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Table(name = "users")
@Entity(name = "User")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
  @Id
  @UuidGenerator(style = UuidGenerator.Style.TIME) // Generación basada en tiempo
  private UUID id; // Me gustan los UUIDs

  private String name;
  private String email;
  private String password;
  private String profiles;
}
