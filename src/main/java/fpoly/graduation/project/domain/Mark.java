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
@Table(name = "mark")
public class Mark extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "markSeq")
    @SequenceGenerator(name = "markSeq",sequenceName = "fpoly_mark_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "lab_1")
    private Integer lab1;

    @Column(name = "lab_2")
    private Integer lab2;

    @Column(name = "lab_3")
    private Integer lab3;

    @Column(name = "lab_4")
    private Integer lab4;

    @Column(name = "lab_5")
    private Integer lab5;

    @Column(name = "lab_6")
    private Integer lab6;

    @Column(name = "lab_7")
    private Integer lab7;

    @Column(name = "lab_8")
    private Integer lab8;

    @Column(name = "assignment_1")
    private Integer assignment1;

    @Column(name = "assignment_2")
    private Integer assignment2;

    @Column(name = "average")
    private Integer average;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "class_subjects_id",referencedColumnName = "id")
    private ClassSubjects classSubjects;

}
