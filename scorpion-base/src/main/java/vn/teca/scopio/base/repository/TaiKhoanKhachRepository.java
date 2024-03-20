package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.TaiKhoanKhach;
import vn.teca.scopio.base.model.authentication.TaiKhoanKhachDtoLogin;

import java.util.List;

@Repository
public interface TaiKhoanKhachRepository extends JpaRepository<TaiKhoanKhach, Integer> {
    // day là code login
    @Query(value = "SELECT thong_tin_khach_dat_id_khach_dat AS IdThongTinKhachDat FROM tai_khoan_khach WHERE so_dien_thoai = :soDienThoai\n" +
            "AND mat_khau = :matKhau", nativeQuery = true)
    TaiKhoanKhachDtoLogin getInfoKhachByLogin(@Param("soDienThoai") String soDienThoai, @Param("matKhau") String matKhau);

    @Query(value = "SELECT COUNT(*) FROM tai_khoan_khach WHERE so_dien_thoai LIKE :soDienThoai", nativeQuery = true)
    int isExistSoDienThoai(@Param("soDienThoai") String soDienThoai);

    @Query(value = "BEGIN TRANSACTION;\n" +
            "INSERT INTO thong_tin_khach_dat (ho_ten, so_dien_thoai,email,gioi_tinh)\n" +
            "VALUES ( :ho_ten, :so_dien_thoai, :email, :gioi_tinh);\n" +
            "INSERT INTO tai_khoan_khach (thong_tin_khach_dat_id_khach_dat, so_dien_thoai, mat_khau)\n" +
            "VALUES ((SELECT id_khach_dat FROM thong_tin_khach_dat WHERE so_dien_thoai LIKE :so_dien_thoai), :so_dien_thoai, :mat_khau);\n" +
            "COMMIT TRANSACTION;", nativeQuery = true)
    void addNewInfoTaiKhoanKhachAndThongTinKhachDat(@Param("ho_ten") String hoVaTen,
                                                    @Param("so_dien_thoai") String soDienThoai,
                                                    @Param("email") String email,
                                                    @Param("gioi_tinh") int gioiTinh,
                                                    @Param("mat_khau") String password);
}