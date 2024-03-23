package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.LoaiDichVu;

import java.util.List;

@Repository
public interface LoaiDichVuRepository extends JpaRepository<LoaiDichVu, Integer> {
    List<LoaiDichVu>findByTenLoaiDichVu(String ten);
}