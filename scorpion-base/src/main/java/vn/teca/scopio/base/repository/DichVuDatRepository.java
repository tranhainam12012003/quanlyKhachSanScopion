package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.teca.scopio.base.model.DichVuDat;
import vn.teca.scopio.base.repository.custom.LoaiPhongCustomRepository;

public interface DichVuDatRepository extends JpaRepository<DichVuDat, Integer> {
}