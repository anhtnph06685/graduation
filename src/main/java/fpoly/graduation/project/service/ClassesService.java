package fpoly.graduation.project.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.config.Constants;
import com.fis.egp.common.config.ValidationError;
import com.fis.egp.common.domain.OptimizedPage;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.security.SecurityUtils;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import fpoly.graduation.project.client.dto.classes.*;
import fpoly.graduation.project.client.dto.course.UpdateCourseResponse;
import fpoly.graduation.project.domain.Classes;
import fpoly.graduation.project.domain.Course;
import fpoly.graduation.project.repository.ClassesRepository;
import fpoly.graduation.project.repository.CourseRepository;
import fpoly.graduation.project.service.dto.ClassesDTO;
import fpoly.graduation.project.service.dto.CourseDTO;
import fpoly.graduation.project.service.mapper.ClassesMapper;
import fpoly.graduation.project.service.mapper.CourseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = {
        ServiceException.class,
        Exception.class
})
public class ClassesService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ClassesRepository classesRepository;

    private final ClassesMapper classesMapper;

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    public ClassesService(
            ClassesRepository classesRepository,
            ClassesMapper classesMapper,
            CourseRepository courseRepository,
            CourseMapper courseMapper
            ){
        this.classesRepository= classesRepository;
        this.classesMapper = classesMapper;
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public GetListClassResponse getListClasses(GetListClassRequest request) throws ServiceException, Exception{
        try {
            if(request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if(request.getClasses() == null){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("class", ValidationError.NotNull))
                        .build();
            }
            if (request.getPageNumber() <0){
                request.setPageNumber(1);
            }
            if(request.getPageSize() < 0){
                request.setPageSize(Constants.DEFAULT_PAGE_SIZE);
            }
            Pageable pageable = PageRequest.of(request.getPageNumber() - 1,
                    request.getPageSize(),
                    Sort.by(Sort.Direction.ASC, StringUtils.isEmpty(request.getOrderBy()) ? "id" : request.getOrderBy()));
            FilterClassRequest searchParams = request.getClasses();
            Page<ClassesDTO> page = classesRepository.getListClassesWithParams(pageable,
                    searchParams.getName() !=null ? searchParams.getName() : null,
                    searchParams.getId() !=null  ? searchParams.getId() : null,
                    searchParams.getStatus() !=null  ? searchParams.getStatus() : null,
                    searchParams.getFromDate() !=null ? searchParams.getFromDate() : null,
                    searchParams.getToDate() !=null ? searchParams.getToDate() : null,
                    searchParams.getCreatedBy() !=null ? searchParams.getCreatedBy() : null
                    ).map(classesMapper :: toDto);

//            page.getContent().forEach(classesDTO -> {
//                List<CourseDTO> courseDTOS = courseRepository.getListCourseById(classesDTO.getId())
//                        .stream()
//                        .map(courseMapper :: toDto)
//                        .collect(Collectors.toCollection(LinkedList::new));
//                classesDTO.setCourse(courseDTOS);
//            });

            GetListClassResponse response = new GetListClassResponse();
            response.setPage(OptimizedPage.convert(page));

            return response;
        }
        catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
    public CreateClassResponse createClass(CreateClassRequest  request) throws  ServiceException, Exception{
        try {
            if(request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if(request.getClasses() == null ){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("class", ValidationError.NotNull))
                        .build();
            }
            Optional<Course> optionalCourse = courseRepository.findById(request.getClasses().getCourse().getId());
            if(!optionalCourse.isPresent()){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("course",ValidationError.NotNull))
                        .build();
            }

            Classes classes = new Classes();
            classes.setName(request.getClasses().getName());
            classes.setDescription(request.getClasses().getDescription());
            classes.setCourse(optionalCourse.get());
            classes.setCreatedDate(Instant.now());
            classes.setCreatedBy("admin");
            classes.setStatus(request.getClasses().getStatus());
            classes.setLastModifiedBy("admin");
            classes.setLastModifiedDate(Instant.now());

            CreateClassResponse response = new CreateClassResponse();

            classesRepository.save(classes);
            response.setClasses(classesMapper.toDto(classes));

            return response;
        }
        catch (ServiceException e){
            throw  e;
        }
        catch (Exception e){
            throw e;
        }
    }
    public UpdateClassResponse update(UpdateClassRequest request) throws ServiceException, Exception{
        try {
            if(request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if(request.getClasses() == null){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("classes",ValidationError.NotNull))
                        .build();
            }
            Optional<Classes> optionalClasses = classesRepository.findById(request.getClasses().getId());
            if(!optionalClasses.isPresent()){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("classes",ValidationError.NotNull))
                        .build();
            }
            Optional<Course> optionalCourse = courseRepository.findById(request.getClasses().getCourse().getId());
            if (!optionalCourse.isPresent()){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("course",ValidationError.NotNull))
                        .build();
            }
            Classes classes = new Classes();

            classes.setId(request.getClasses().getId());
            classes.setName(request.getClasses().getName());
            classes.setDescription(request.getClasses().getDescription());
            classes.setCourse(optionalCourse.get());
            classes.setCreatedDate(Instant.now());
            classes.setCreatedBy("admin");
            classes.setStatus(request.getClasses().getStatus());
            classes.setLastModifiedBy("admin");
            classes.setLastModifiedDate(Instant.now());

            UpdateClassResponse response = new UpdateClassResponse();

            classesRepository.save(classes);
            response.setClasses(classesMapper.toDto(classes));

            return response;
        }
        catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
    public DeleteClassResponse delete(DeleteClassRequest request ) throws ServiceException, Exception{
        try {
            if (request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if(request.getId() == null){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("id",ValidationError.NotNull))
                        .build();
            }
            if(request.getId() < 0){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("id",ValidationError.NegativeOrZero))
                        .build();
            }
            classesRepository.deleteById(request.getId());
            List<Classes> list = classesRepository.findAll();
            DeleteClassResponse response = new DeleteClassResponse();
            response.setClasses(classesMapper.toDto(list));

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
