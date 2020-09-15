package fpoly.graduation.project.web.rest;

import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ResponseUtil;
import fpoly.graduation.project.client.dto.classes.CreateClassRequest;
import fpoly.graduation.project.client.dto.classes.CreateClassResponse;
import fpoly.graduation.project.client.dto.student.CreateStudentRequest;
import fpoly.graduation.project.client.dto.student.CreateStudentResponse;
import fpoly.graduation.project.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/student")
public class StudentResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private StudentService studentService;

    public StudentResource(
            StudentService studentService
    ){
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<BaseDataResponse<CreateStudentResponse>> create(
            @RequestBody BaseDataRequest<CreateStudentRequest> request
            ){
        try {
            CreateStudentResponse response = studentService.createStudent(request.getBody());
            return ResponseUtil.wrap(response);

        }
        catch (ServiceException e){
            return ResponseUtil.generateErrorResponse(e);
        }
        catch (Exception e){
            return ResponseUtil.generateErrorResponse(e);
        }
    }
}
