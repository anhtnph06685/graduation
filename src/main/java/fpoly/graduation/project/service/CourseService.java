package fpoly.graduation.project.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.config.Constants;
import com.fis.egp.common.config.ValidationError;
import com.fis.egp.common.domain.OptimizedPage;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import fpoly.graduation.project.client.dto.course.FilterCourseRequest;
import fpoly.graduation.project.client.dto.course.GetListCourseRequest;
import fpoly.graduation.project.client.dto.course.GetListCourseResponse;
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
}
