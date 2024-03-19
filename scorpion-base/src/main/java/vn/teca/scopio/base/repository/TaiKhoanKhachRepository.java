package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.TaiKhoanKhach;

import java.util.List;

@Repository
public interface TaiKhoanKhachRepository extends JpaRepository<TaiKhoanKhach, Integer> {
    // câu lệnh join 3 bảng --(taiKhoanKhach-thongtinkhachdat-dondat) theo id tai khoan
//    @Query(value = "SELECT ttkd.ho_ten, ttkd.ngay_sinh, ttkd.gioi_tinh, ttkd.email, ttkd.dia_chi, dd.id_don_dat, dd.thoi_gian_vao, dd.thoi_gian_ra, tkk.so_dien_thoai\n" +
//            "FROM thong_tin_khach_dat AS ttkd\n" +
//            "JOIN don_dat AS dd ON ttkd.id_khach_dat = dd.thong_tin_khach_dat_id_khach_dat\n" +
//            "JOIN tai_khoan_khach AS tkk ON ttkd.id_khach_dat = tkk.thong_tin_khach_dat_id_khach_dat\n" +
//            "WHERE tkk.id_tai_khoan_khach = ?1;", nativeQuery = true)
//    TaiKhoanKhach detail(Integer id);

// câu lệnh join 3 bảng lấy loại phòng và số lượng (loai_phong,don_dat,loai_phong_dat)
}