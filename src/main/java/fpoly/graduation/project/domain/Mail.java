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
@Table(name = "mail")
public class Mail extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "mailSeq")
    @SequenceGenerator(name = "mailSeq",sequenceName = "fpoly_mail_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "send_to")
    private String sendTo;

    @Column(name = "status")
    private Integer status;

    @Column(name = "type")
    private  String type;
}
