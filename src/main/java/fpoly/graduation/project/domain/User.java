package fpoly.graduation.project.domain;

import com.fis.egp.common.domain.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userSeq")
    @SequenceGenerator(name = "userSeq",sequenceName = "fpoly_user_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "username")
    @Size(max = 50)
    private String username;

    @Column(name = "password")
    @Size(max = 50)
    private String password;

    @Column(name = "status")
    private Integer status;
}
