package az.turing.jobportal.entity;

import az.turing.jobportal.dto.AccountType;
import az.turing.jobportal.dto.UserDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Long id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String password;
    private AccountType accountType;
    public UserDTO toDto(){
        return new UserDTO(this.id, this.name, this.email,this.password, this.accountType);
    }
}
