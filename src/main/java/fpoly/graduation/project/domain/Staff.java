package fpoly.graduation.project.domain;

import com.fis.egp.common.domain.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class Staff extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "staffSeq")
    @SequenceGenerator(name = "staffSeq",sequenceName = "fpoly_staff_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "permanent_address")
    private String permanentAddress;

    @Column(name = "tenporary_address")
    private String  tenporaryAddress;

    @Column(name = "bird")
    private String bird;

    @Column(name = "status")
    private Integer status;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @ManyToOne
    @JoinColumn(name = "department_id",referencedColumnName = "id")
    private Department department;

}
