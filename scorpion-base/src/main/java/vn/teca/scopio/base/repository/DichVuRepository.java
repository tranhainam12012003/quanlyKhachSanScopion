package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.teca.scopio.base.model.DichVu;

import java.util.List;

public interface DichVuRepository extends JpaRepository<DichVu, Integer> {
    List<DichVu>findByTenDichVuContaining(String ten);
    List<DichVu>findByLoaiDichVuIdLoaiDichVu(Integer id);
}