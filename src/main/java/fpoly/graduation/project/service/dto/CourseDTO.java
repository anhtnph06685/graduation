package fpoly.graduation.project.service.dto;

import fpoly.graduation.project.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO implements Serializable {

    private Integer id;

    private String description;

    private Instant startDate;

    private Instant endDate;

    private Integer status;

    private Instant createdDate;

    private String createdBy;

    private Instant lastModifiedDate;

    private String lastModifiedBy;

}
