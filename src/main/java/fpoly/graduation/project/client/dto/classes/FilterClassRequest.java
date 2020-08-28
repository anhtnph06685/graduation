package fpoly.graduation.project.client.dto.classes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fpoly.graduation.project.domain.Course;
import fpoly.graduation.project.service.dto.CustomInstantDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterClassRequest {
    private Integer id;
    private String name;
    private String description;
    private Course course;
    private Integer status;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant fromDate;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private  Instant toDate;
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant createdDate;
    private String createdBy;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant updatedDate;
    private String updatedBy;
}
