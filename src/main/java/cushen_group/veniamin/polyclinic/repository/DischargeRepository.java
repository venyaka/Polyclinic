package cushen_group.veniamin.polyclinic.repository;

import cushen_group.veniamin.polyclinic.entity.Discharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DischargeRepository extends JpaRepository<Discharge, Long>, JpaSpecificationExecutor<Discharge> {

}
