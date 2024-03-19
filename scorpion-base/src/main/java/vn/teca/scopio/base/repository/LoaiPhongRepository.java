package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.LoaiPhong;
@Repository
public interface LoaiPhongRepository extends JpaRepository<LoaiPhong, Integer> {
}