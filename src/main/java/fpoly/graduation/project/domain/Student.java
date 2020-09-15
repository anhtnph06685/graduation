package fpoly.graduation.project.domain;

import com.fis.egp.common.domain.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "student")
public class Student extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentSeq")
    @SequenceGenerator(name = "studentSeq",sequenceName = "fpoly_student_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "first_name")
    @Size(max = 50)
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 50)
    private String lastName;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "permanent_address")
    @Size(max = 1024)
    private String permanentAddress;

    @Column(name = "tenporary_address")
    @Size(max = 1024)
    private String tenporaryAddress;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "birth")
    private Instant birth;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private Classes classes;

    @Column(name = "status")
    private Integer status;
}
