package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.HinhAnh;
@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, Integer> {
    @Query(value = "select * from hinh_anh  inner join loai_phong on hinh_anh.loai_phong_Id_loai_phong=loai_phong.Id_loai_phong where loai_phong_Id_loai_phong=?",nativeQuery = true)
    public HinhAnh detail(Integer id);
}