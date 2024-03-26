package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.teca.scopio.base.model.LoaiPhongDat;

import java.util.List;

public interface LoaiPhongDatRepository extends JpaRepository<LoaiPhongDat, Integer> {

    List<LoaiPhongDat> findByLoaiPhongIdLoaiPhong_Id(Integer id);
}