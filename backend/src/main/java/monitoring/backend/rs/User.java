package monitoring.backend.rs;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

  private static final long serialVersionUID = 6104966833360579070L;

  private Long id;
  private String name;
  private Date createdAt;
}
