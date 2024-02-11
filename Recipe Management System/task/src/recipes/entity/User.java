package recipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    @JsonIgnore
    private Long id;

    @Column(name = "email")
    @Email(regexp = ".+@.+\\..+", message = "email syntax is not correct")
    private String email;

    @Size(min = 8, message = "Password does not meet the requirements")
    @NotBlank
    @Column(name = "password")
    private String password;
}
