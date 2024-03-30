package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.PhongDat;

import java.util.List;
import java.util.Objects;

@Repository
public interface PhongDatRepository extends JpaRepository<PhongDat, Integer> {
    List<PhongDat> findPhongDatByDonDatIdDonDat(Integer id);
     @Query(value = "\tSELECT \n" +
             "    loai_phong.ten_loai_phong, phong.so_phong,\n" +
             "    COUNT(pd.phong_id_phong) AS so_luong_phong,pd.phong_id_phong \n" +
             "FROM \n" +
             "    phong_dat pd\n" +
             "INNER JOIN \n" +
             "    loai_phong_dat lp ON pd.don_dat_id_don_dat = lp.don_dat_id_don_dat join phong on phong.id_phong=pd.phong_id_phong join loai_phong on\n" +
             "\tloai_phong.Id_loai_phong=phong.loai_phong_Id_loai_phong where pd.don_dat_id_don_dat=:idDonDat\n" +
             "GROUP BY \n" +
             "  phong.so_phong,loai_phong.ten_loai_phong,  pd.phong_id_phong",nativeQuery = true)
    List<Object[]>getPhongDaGan(@Param("idDonDat") Integer id);

   @Query(value = "SELECT \n" +
           "    loai_phong.ten_loai_phong,\n" +
           "    lpd.so_luong - COALESCE(count(pd.id_phong_dat), 0) AS so_luong_con_lai\n" +
           "FROM \n" +
           "    loai_phong_dat lpd\n" +
           "JOIN \n" +
           "    don_dat dd ON lpd.don_dat_id_don_dat = dd.id_don_dat join loai_phong on loai_phong.Id_loai_phong=lpd.loai_phong_Id_loai_phong\n" +
           "LEFT JOIN \n" +
           "    phong_dat pd ON dd.id_don_dat = pd.don_dat_id_don_dat\n" +
           "GROUP BY \n" +
           "    loai_phong.ten_loai_phong, lpd.so_luong;",nativeQuery = true)
    List<Object[]>getPhongChuaGan();

   //get phong dat theo id phong
   @Query(value = "select thong_tin_khach_o.ho_ten, loai_phong.ten_loai_phong\n" +
           ", pd.so_tien_phong,pd.thoi_gian_vao,pd.thoi_gian_ra,phong.so_phong from\n" +
           "phong_dat pd join phong on phong.id_phong=pd.phong_id_phong join thong_tin_khach_o on \n" +
           "thong_tin_khach_o.phong_dat_id_phong_dat=pd.id_phong_dat join loai_phong on\n" +
           "loai_phong.Id_loai_phong=phong.loai_phong_Id_loai_phong where pd.phong_id_phong=:idPhong\n",nativeQuery = true)
    List<Object[]>getThongTinPhongDatByIdPhong(@Param("idPhong")Integer idPhong);



}