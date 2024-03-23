package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.DonDat;

import java.util.List;

@Repository
public interface DonDatRepository extends JpaRepository<DonDat, Integer> {
    @Query(value = "select dd.id_don_dat,ttkd.ho_ten,ttkd.so_dien_thoai,dd.trang_thai from don_dat dd inner join thong_tin_khach_dat ttkd on dd.thong_tin_khach_dat_id_khach_dat=ttkd.id_khach_dat",nativeQuery = true)
    List<DonDat>getList();
}
