package fpoly.graduation.project.repository;

import fpoly.graduation.project.domain.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface ClassesRepository extends JpaRepository<Classes,Integer> {

    @Query("from Classes cl where 1=1 " +
            "and (cl.name like  concat('%',:name,'%') or :name is null)" +
            "and (cl.id =:id or :id is null)" +
            "and (cl.status =:status or :status is null)" +
            "and (cl.createdDate >=:fromDate or :fromDate is null)" +
            "and (cl.createdDate < :toDate or :toDate is null)" +
            "and (cl.createdBy like concat('%',:createdBy,'%') or :createdBy is null )"
    )
    Page<Classes> getListClassesWithParams(Pageable pageable,
                                           @Param("name")  String name,
                                           @Param("id") Integer id,
                                           @Param("status") Integer status,
                                           @Param("fromDate")Instant fromDate,
                                           @Param("toDate") Instant toDate,
                                           @Param("createdBy") String createdBy
                                           );
}
