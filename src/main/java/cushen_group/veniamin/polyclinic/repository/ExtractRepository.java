package cushen_group.veniamin.polyclinic.repository;

import cushen_group.veniamin.polyclinic.entity.Extract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractRepository extends JpaRepository<Extract, Long>, JpaSpecificationExecutor<Extract> {

}
