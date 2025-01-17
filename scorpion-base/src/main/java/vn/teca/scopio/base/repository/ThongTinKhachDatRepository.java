package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.teca.scopio.base.model.ThongTinKhachDat;
import vn.teca.scopio.base.model.ThongTinKhachO;

import java.util.Optional;

public interface ThongTinKhachDatRepository extends JpaRepository<ThongTinKhachDat, Integer> {
    @Query(value = "SELECT * FROM thong_tin_khach_dat WHERE so_dien_thoai = :soDienThoai ",nativeQuery = true)
    Optional<ThongTinKhachDat> getThongTinKhachDatBySoDienThoai(@Param("soDienThoai") String soDienThoai);

    ThongTinKhachDat findThongTinKhachDatByEmail(String email);

}