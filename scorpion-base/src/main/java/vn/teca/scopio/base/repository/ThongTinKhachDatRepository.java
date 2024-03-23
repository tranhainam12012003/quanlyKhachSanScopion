package vn.teca.scopio.base.repository;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.teca.scopio.base.model.ThongTinKhachDat;

import java.util.Optional;

public interface ThongTinKhachDatRepository extends JpaRepository<ThongTinKhachDat, Integer> {
    @Query(value = "SELECT * FROM thong_tin_khach_dat WHERE so_dien_thoai = :soDienThoai ",nativeQuery = true)
    Optional<ThongTinKhachDat> getThongTinKhachDatBySoDienThoai(@Param("soDienThoai") String soDienThoai);

}