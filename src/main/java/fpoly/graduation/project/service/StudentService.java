package fpoly.graduation.project.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.config.ValidationError;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import fpoly.graduation.project.client.dto.student.CreateStudentRequest;
import fpoly.graduation.project.client.dto.student.CreateStudentResponse;
import fpoly.graduation.project.client.dto.student.FilterStudentRequest;
import fpoly.graduation.project.domain.Classes;
import fpoly.graduation.project.domain.Student;
import fpoly.graduation.project.domain.User;
import fpoly.graduation.project.repository.ClassesRepository;
import fpoly.graduation.project.repository.StudentRepository;
import fpoly.graduation.project.repository.UserRepository;
import fpoly.graduation.project.service.dto.StudentDTO;
import fpoly.graduation.project.service.mapper.ClassesMapper;
import fpoly.graduation.project.service.mapper.StudentMapper;
import fpoly.graduation.project.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.repository.ClassRepository;

import java.time.Instant;
import java.util.Optional;

@Service
@Transactional(rollbackFor = {
        ServiceException.class,
        Exception.class
})
public class StudentService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final ClassesRepository classRepository;

    private final ClassesMapper classesMapper;

    public StudentService(
            StudentRepository studentRepository,
            StudentMapper studentMapper,
            ClassesRepository classRepository,
            ClassesMapper classesMapper,
            UserRepository userRepository,
            UserMapper userMapper
    ){
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.classRepository = classRepository;
        this.classesMapper = classesMapper;
    }

    public CreateStudentResponse createStudent(CreateStudentRequest request) throws ServiceException, Exception{
        try {
            if(request == null ){
                ServiceUtil.generateEmptyPayloadError();
            }
            if(request.getStudent() == null){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("student", ValidationError.NotNull))
                        .build();
            }

            Optional<Classes> optionalClass = classRepository.findById(request.getStudent().getClasses().getId());
            if (!optionalClass.isPresent()){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("class", ValidationError.NotNull))
                        .build();
            }
            FilterStudentRequest filter=  request.getStudent();

            Student student = new Student();
            student.setFirstName(filter.getFirstName());
            student.setLastName(filter.getLastName());
            student.setGender(filter.getGender());
            student.setPermanentAddress(filter.getPermanentAddress());
            student.setTenporaryAddress(filter.getTenporaryAddress());
            student.setStartDate(filter.getStartDate());
            student.setEndDate(filter.getEndDate());
            student.setBirth(filter.getBirth());
            student.setClasses(filter.getClasses());
            student.setStatus(filter.getStatus());
            student.setCreatedDate(Instant.now());
            student.setCreatedBy("admin");
            student.setLastModifiedDate(Instant.now());
            student.setLastModifiedBy("admin");
            studentRepository.save(student);

            CreateStudentResponse response = new CreateStudentResponse();

            response.setStudent(studentMapper.toDto(student));

            return response;

        }
        catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }

    }


}
