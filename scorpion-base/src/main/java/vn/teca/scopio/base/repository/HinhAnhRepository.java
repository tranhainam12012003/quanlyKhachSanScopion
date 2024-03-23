package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.HinhAnh;

import java.util.List;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, Integer> {
    List<HinhAnh>findByLoaiPhongIdLoaiPhong_Id(Integer id);


}