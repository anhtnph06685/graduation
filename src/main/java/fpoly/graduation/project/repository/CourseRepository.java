package fpoly.graduation.project.repository;

import fpoly.graduation.project.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    @Query("from Course c join Classes cl on c.id = cl.course.id where cl.course.id=:id")
    List<Course> getListCourseById(@Param("id") Integer id);

    @Query("from Course c where 1=1 " +
            "and (c.id =:id or :id is null)" +
            "and (c.status =:status or :status is null)" +
            "and (c.createdBy like concat('%',:createdBy,'%') or :createdBy is null )" +
            "and (c.createdDate >= :fromDate or :fromDate is null)" +
            "and (c.createdDate < :toDate or :toDate is null)" +
            "and (c.startDate >= :startDate or :startDate is  null)" +
            "and (c.endDate < :endDate or :endDate is null)"
    )
    Page<Course> getListCourseWithParams(Pageable pageable,
                                         @Param("id") Integer id,
                                         @Param("status") Integer status,
                                         @Param("createdBy") String createdBy,
                                         @Param("fromDate")Instant fromDate,
                                         @Param("toDate") Instant toDate,
                                         @Param("startDate") Instant startDate,
                                         @Param("endDate") Instant endDate
                                         );
}
