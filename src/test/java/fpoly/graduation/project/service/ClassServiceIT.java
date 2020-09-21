package fpoly.graduation.project.service;

import com.fis.egp.common.exception.ServiceException;
import fpoly.graduation.project.client.dto.classes.CreateClassRequest;
import fpoly.graduation.project.client.dto.classes.FilterClassRequest;
import fpoly.graduation.project.client.dto.classes.GetListClassRequest;
import fpoly.graduation.project.domain.Classes;
import fpoly.graduation.project.domain.Course;
import fpoly.graduation.project.repository.CourseRepository;
import fpoly.graduation.project.service.dto.ClassesDTO;
import fpoly.graduation.project.service.dto.CourseDTO;
import fpoly.graduation.project.service.mapper.ClassesMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sun.reflect.generics.repository.ClassRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
public class ClassServiceIT {

    @Autowired
    private ClassesService classesService;

    @MockBean
    private ClassRepository classRepository;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private EntityManager entityManager;

    @Autowired
    private ClassesMapper classesMapper;

    private ClassesDTO  classes;

    private CourseDTO course;

    @BeforeEach
    public void init(){
        course = new CourseDTO();

        course.setDescription("abc");
        course.setId(1);
        course.setStatus(1);
        course.setStartDate(Instant.now());
        course.setEndDate(Instant.now());
        course.setCreatedBy("admin");
        course.setCreatedDate(Instant.now());
        course.setLastModifiedBy("admin");
        course.setLastModifiedDate(Instant.now());

        classes = new ClassesDTO();
        classes.setName("abc");
        classes.setDescription("abc");
        classes.setStatus(1);
        classes.setCourse(course);
        classes.setCreatedBy("admin");
        classes.setCreatedDate(Instant.now());
        classes.setLastModifiedBy("admin");
        classes.setLastModifiedDate(Instant.now());
    }

    @Test
    public void demo(){
        Assertions.assertNotNull(course.getId());
        System.out.println(course.toString());
    }

    @Test
    public void addClass(){
        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.of(new Course()));

        CreateClassRequest request = new CreateClassRequest();
        request.setClasses(classes);
        Assertions.assertThrows(Exception.class, () -> {
            classesService.createClass(request);
        });
    }
    @Test
    public void addClass_02(){
        Mockito.when(courseRepository.findById(course.getId())).thenReturn(Optional.of(new Course()));
        CreateClassRequest request = new CreateClassRequest();
        request.setClasses(classes);

        Assertions.assertThrows(ServiceException.class,() -> {
            classesService.createClass(request);
        });
        Assertions.assertNotNull(classes);
    }

    @Test
    public void getListClasses(){
        TypedQuery query = Mockito.mock(TypedQuery.class);

        Mockito.when(entityManager.createQuery(Mockito.anyString(),Mockito.any())).thenReturn(query);

        Mockito.when(query.getResultList())
                .thenReturn(Arrays.asList(new Classes()));

        GetListClassRequest request = new GetListClassRequest();
        FilterClassRequest filter = new FilterClassRequest();
        request.setClasses(filter);
        request.setPageSize(10);
        request.setPageNumber(1);
        request.setOrderBy(null);
        Assertions.assertDoesNotThrow(() -> {
            classesService.getListClasses(request);
        });
    }
}
