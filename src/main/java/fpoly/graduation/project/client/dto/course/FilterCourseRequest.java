package fpoly.graduation.project.client.dto.course;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fpoly.graduation.project.service.dto.CustomInstantDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterCourseRequest {
    private Integer id;
    private String description;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant startDate;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant endDate;

    private Integer status;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant createdDate;
    private String createdBy;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant updatedDate;
    private String updateBy;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant fromDate;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant toDate;

}
