package fpoly.graduation.project.domain;

import com.fis.egp.common.domain.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "uss")
public class Uss extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ussSeq")
    @SequenceGenerator(name = "ussSeq",sequenceName = "fpoly_uss_seq",allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "unit_subject_id",referencedColumnName = "id")
    private UnitSubject unitSubject;

    @ManyToOne
    @JoinColumn(name = "specialized_id",referencedColumnName = "id")
    private Specicalized specicalized;
}
