package fpoly.graduation.project.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO implements Serializable {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer gender;

    private String permanentAddress;

    private String tenporaryAddress;

    private Instant startDate;

    private Instant endDate;

    private Instant birth;

    private Integer classId;

    private Integer status;

    private Instant createdDate;

    private String createdBy;

    private Instant lastModifiedDate;

    private String lastModifiedBy;

    private UserDTO user;
}
