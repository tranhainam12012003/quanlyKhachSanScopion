package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.ThongTinKhachO;

import java.sql.Date;
import java.util.List;
@Repository
public interface ThongTinKhachORepository extends JpaRepository<ThongTinKhachO, Integer> {
    @Query(value = "SELECT *\n" +
            "FROM thong_tin_khach_o\n" +
            "INNER JOIN phong_dat ON thong_tin_khach_o.phong_dat_id_phong_dat = phong_dat.id_phong_dat\n" +
            "WHERE thong_tin_khach_o.ho_ten = ?1 \n" +
            "OR phong_dat.thoi_gian_vao >= ?2 \n" +
            "OR phong_dat.thoi_gian_ra <= ?3 \n" +
            "OR thong_tin_khach_o.so_giay_to = ?4", nativeQuery = true)
    List<ThongTinKhachO> timkiem(String ten, Date thoiGianVao, Date thoiGianRa, String soGiayTo);
}