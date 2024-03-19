package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.TaiKhoanNv;

import java.util.Optional;
@Repository
public interface TaiKhoanNvRepository extends JpaRepository<TaiKhoanNv, Integer> {
//    @Query(value = "SELECT quyen_han_id_quyen_han AS idQuyenHan, ten_tai_khoan AS tenTaiKhoan  FROM tai_khoan_nv " +
//            "WHERE so_dien_thoai = :soDienThoai AND mat_khau = :matKhau"
//            , nativeQuery = true)
//    Optional<TaiKhoanNVDtoLogin> getInfoLoginNV(@Param("soDienThoai") String soDienThoai, @Param("matKhau") String matKhau);
}