package az.turing.jobportal.dto;

import az.turing.jobportal.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Indexed(unique=true)
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one lowercase, " +
                    "one uppercase letter, one special character and no whitespace"
    )
    private String password;
    private AccountType accountType;
    public User toEntity(){
        return new User(this.id, this.name, this.email, this.password, this.accountType);
    }

}
