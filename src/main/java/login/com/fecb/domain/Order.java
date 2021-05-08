
package login.com.fecb.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "PRODUCT_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long productOrderId;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "userId")
    private User user;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "productId")
    private Product product;

    @NotNull
    private String orderDate;

    @NotNull
    private int orderStatus;
}

