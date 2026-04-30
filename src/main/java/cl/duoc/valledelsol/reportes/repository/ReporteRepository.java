package cl.duoc.valledelsol.reportes.repository;

import cl.duoc.valledelsol.reportes.entity.ReporteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<ReporteEntity, Long> {
}