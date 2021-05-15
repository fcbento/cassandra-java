package login.com.fecb.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message="Email is required")
    @Email(message="Invalid email")
    private String email;

    @NotEmpty(message="Password is required")
    private String password;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Phone is quired")
    private long phoneNumber;

    @NotNull
    private Integer role;
}
