package fpoly.graduation.project.client.dto.student;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fpoly.graduation.project.domain.Classes;
import fpoly.graduation.project.domain.User;
import fpoly.graduation.project.service.dto.CustomInstantDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterStudentRequest {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer gender;

    private String permanentAddress;

    private String tenporaryAddress;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant startDate;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant endDate;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant birth;

    private Integer classId;

    private Integer status;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant createdDate;

    private String createdBy;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant lastModifiedDate;

    private String lastModifiedBy;

    private Classes classes;

    private User user;
}
