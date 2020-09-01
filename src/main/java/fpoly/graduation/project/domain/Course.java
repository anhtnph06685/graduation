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
@Table(name = "course")
public class Course extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "courseSeq")
    @SequenceGenerator(name = "courseSeq",sequenceName = "fpoly_course_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name="end_date")
    private Instant endDate;

    @Column(name = "status")
    private Integer status;

//    @Column(name ="created_by")
//    private String createdBy;
//
//    @Column(name = "created_date")
//    private Instant createdDate;
//
//    @Column(name = "updated_by")
//    private String lastModifiedBy;


}
