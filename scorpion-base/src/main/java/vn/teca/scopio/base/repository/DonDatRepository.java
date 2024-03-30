package vn.teca.scopio.base.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.DonDat;

import java.util.List;

@Repository
public interface DonDatRepository extends JpaRepository<DonDat, Integer> {
    @Query(value = "select * from don_dat dd join " +
            "hinh_thuc_dat htd on " +
            "dd.hinh_thuc_dat_id_hinh_thuc_dat=htd.id_hinh_thuc_dat", countQuery = "select count(*)  from don_dat dd join hinh_thuc_dat htd on " +
            "dd.hinh_thuc_dat_id_hinh_thuc_dat=htd.id_hinh_thuc_dat",
            nativeQuery = true)
    List<DonDat> getList(Pageable pageable);

    @Query(value = "select lp.ten_loai_phong,lpd.so_luong,dd.thoi_gian_vao,dd.thoi_gian_ra,\n" +
            "dd.tong_tien,ttkd.ho_ten,ttkd.ngay_sinh,ttkd.so_dien_thoai,ttkd.email,ttkd.quoc_tich,ttkd.gioi_tinh,ttkd.dia_chi \n" +
            "from don_dat dd join loai_phong_dat lpd on dd.id_don_dat=lpd.don_dat_id_don_dat join thong_tin_khach_dat ttkd on\n" +
            "ttkd.id_khach_dat=dd.thong_tin_khach_dat_id_khach_dat join loai_phong lp \n" +
            "on lpd.loai_phong_Id_loai_phong=lp.Id_loai_phong where dd.id_don_dat=:idDonDat", nativeQuery = true)
    List<Object[]> detailThongTinPhongDat(@Param("idDonDat") Integer id);

    @Query(value = "select * from don_dat dd join hinh_thuc_dat htd " +
            "on dd.hinh_thuc_dat_id_hinh_thuc_dat=htd.id_hinh_thuc_dat " +
            "where htd.ten_hinh_thuc='online'", countQuery = "select count(*) from don_dat dd join hinh_thuc_dat htd " +
            "on dd.hinh_thuc_dat_id_hinh_thuc_dat=htd.id_hinh_thuc_dat " +
            "where htd.ten_hinh_thuc='online'", nativeQuery = true)
    List<DonDat> findAllOnline(Pageable pageable);

    @Query(value = "select * from don_dat dd join hinh_thuc_dat htd " +
            "on dd.hinh_thuc_dat_id_hinh_thuc_dat=htd.id_hinh_thuc_dat " +
            "where htd.ten_hinh_thuc='offline'", countQuery = "select count(*) from don_dat dd join hinh_thuc_dat htd " +
            "on dd.hinh_thuc_dat_id_hinh_thuc_dat=htd.id_hinh_thuc_dat " +
            "where htd.ten_hinh_thuc='offline'", nativeQuery = true)
    List<DonDat> findAllOffline(Pageable pageable);

    @Query(value = "select don_dat.thoi_gian_vao,don_dat.thoi_gian_ra,\n" +
            "loai_phong.ten_loai_phong,loai_phong.so_luong_nguoi_o,don_dat.tong_tien,\n" +
            "thong_tin_khach_dat.ho_ten  from loai_phong_dat join don_dat\n" +
            "on loai_phong_dat.don_dat_id_don_dat=don_dat_id_don_dat join loai_phong on\n" +
            "loai_phong.Id_loai_phong=loai_phong_dat.loai_phong_Id_loai_phong join thong_tin_khach_dat on thong_tin_khach_dat.id_khach_dat=don_dat.thong_tin_khach_dat_id_khach_dat\n" +
            "where don_dat.id_don_dat=:idDonDat" +
            "\n",nativeQuery = true)
    List<Object[]>detailTheoIdDonDat(@Param("idDonDat")Integer id);
}
