package login.com.fecb.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long categoryId;

    @NotNull
    private String name;

    @NotNull
    private Date createdAt;

}
