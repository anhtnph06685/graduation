package fpoly.graduation.project.domain;

import com.fis.egp.common.domain.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lecturrer")
public class Lecturer extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "lecturerSeq")
    @SequenceGenerator(name = "lecturerSeq",sequenceName = "fpoly_lecturer_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "pemanent_address")
    @Size(max = 1024)
    private String permanentAddress;

    @Column(name = "tenporary_address")
    @Size(max = 1024)
    private String tenporaryAddress;

    @Column(name = "bird")
    private String bird;

    @Column(name = "status")
    private Integer status;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @ManyToOne
    @JoinColumn(name = "unit_subject_id",referencedColumnName = "id")
    private  UnitSubject unitSubject;
}
