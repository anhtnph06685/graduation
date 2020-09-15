package fpoly.graduation.project.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.config.Constants;
import com.fis.egp.common.config.ValidationError;
import com.fis.egp.common.domain.OptimizedPage;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import fpoly.graduation.project.client.dto.course.*;
import fpoly.graduation.project.domain.Course;
import fpoly.graduation.project.repository.CourseRepository;
import fpoly.graduation.project.service.dto.CourseDTO;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = {
        ServiceException.class,
        Exception.class
})
public class CourseService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(
            CourseRepository courseRepository,
            CourseMapper courseMapper
    ){
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }
    public GetListCourseResponse getListCourse(GetListCourseRequest request) throws ServiceException, Exception{
        try {
            if(request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if(request.getCourse() == null){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("course", ValidationError.NotNull))
                        .build();
            }
            if(request.getPageNumber() < 0){
                request.setPageNumber(1);
            }
            if(request.getPageSize() < 0 ){
                request.setPageSize(Constants.DEFAULT_PAGE_SIZE);
            }
            Pageable pageable = PageRequest.of(request.getPageNumber() - 1,
                    request.getPageSize(),
                    Sort.by(Sort.Direction.ASC, StringUtils.isEmpty(request.getOrderBy()) ? "id" : request.getOrderBy()));
            FilterCourseRequest searchParams = request.getCourse();
            Page<CourseDTO> page = courseRepository.getListCourseWithParams(pageable,
                    searchParams.getId() !=null ? searchParams.getId() :null,
                    searchParams.getStatus() !=null ? searchParams.getStatus() :null,
                    searchParams.getCreatedBy() !=null ?  searchParams.getCreatedBy() : null,
                    searchParams.getFromDate() !=null ?searchParams.getFromDate() : null,
                    searchParams.getToDate() !=null ? searchParams.getToDate() : null,
                    searchParams.getStartDate() !=null ?searchParams.getStartDate() :null,
                    searchParams.getEndDate() !=null ? searchParams.getEndDate() : null
                    ).map(courseMapper :: toDto);

            GetListCourseResponse response  = new GetListCourseResponse();
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
    public CreateCourseResponse createCourse(CreateCourseRequest request) throws ServiceException, Exception{
        try {
            if(request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if(request.getCourse() == null){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("course",ValidationError.NotNull))
                        .build();
            }
            FilterCourseRequest filter = request.getCourse();
            Course course = new Course();

            course.setDescription(filter.getDescription());
            course.setStartDate(filter.getStartDate());
            course.setEndDate(filter.getEndDate());
            course.setStatus(filter.getStatus());
            course.setCreatedBy("admin");
            course.setCreatedDate(Instant.now());
            course.setLastModifiedBy("admin");
            course.setLastModifiedDate(Instant.now());

            CreateCourseResponse response = new CreateCourseResponse();

            courseRepository.save(course);
            response.setCourse(courseMapper.toDto(course));

            return response;
        } catch (Exception e){
            throw e;
        }
    }
    public UpdateCourseResponse update(UpdateCourseRequest request) throws ServiceException,Exception{
        try {
            if (request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if(request.getCourse() == null){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("course",ValidationError.NotNull))
                        .build();
            }
            Optional<Course> optionalCourse = courseRepository.findById(request.getCourse().getId());

            if(!optionalCourse.isPresent()){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("course", ValidationError.NotNull))
                        .build();
            }
            FilterCourseRequest filter = request.getCourse();
            Course course = new Course();

            course.setId(filter.getId());
            course.setDescription(filter.getDescription());
            course.setStartDate(filter.getStartDate());
            course.setEndDate(filter.getEndDate());
            course.setStatus(filter.getStatus());
            course.setCreatedBy(filter.getCreatedBy());
            course.setCreatedDate(Instant.now());
            course.setLastModifiedBy("anhtn39");
            course.setLastModifiedDate(Instant.now());

            UpdateCourseResponse response = new UpdateCourseResponse();

            courseRepository.save(course);
            response.setCourse(courseMapper.toDto(course));

            return response;
        }
        catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
    public DeleteCourseResponse delete(DeleteCourseRequest request) throws ServiceException, Exception {
        try {
            if(request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if (request.getId() == null){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("id",ValidationError.NotNull))
                        .build();
            }
            if(request.getId() < 0){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("id",ValidationError.NegativeOrZero))
                        .build();
            }
            courseRepository.deleteById(request.getId());
            DeleteCourseResponse response = new DeleteCourseResponse();
            List<Course> list = courseRepository.findAll();
            response.setCourses(courseMapper.toDto(list));
            return response;
        }
        catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
    public Instant stringToInstant(String s) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(s);
        Instant reInstant = date.toInstant();
        return reInstant;
    }
}
