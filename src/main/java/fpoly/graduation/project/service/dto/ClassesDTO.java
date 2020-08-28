package fpoly.graduation.project.service.dto;

import fpoly.graduation.project.domain.Classes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassesDTO implements Serializable{

    private Integer id;

    private String name;

    private String description;

    private Integer courseId;

    private Integer status;

    private Instant createdDate;

    private String createdBy;

    private Instant lastModifiedDate;

    private String lastModifiedBy;

    private List<CourseDTO> course;


}
