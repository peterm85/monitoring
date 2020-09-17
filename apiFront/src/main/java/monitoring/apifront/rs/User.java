package monitoring.apifront.rs;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 6104966833360579070L;

    private Long id;
    private String name;
    private Date createdAt;
    private Date deletedAt;
}
