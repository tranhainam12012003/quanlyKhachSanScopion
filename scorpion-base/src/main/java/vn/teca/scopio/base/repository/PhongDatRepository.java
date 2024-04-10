package vn.teca.scopio.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.PhongDat;

import java.util.List;

@Repository
public interface PhongDatRepository extends JpaRepository<PhongDat, Integer> {
    @Query(value = "SELECT * FROM phong_dat  " +
            "WHERE don_dat_id_don_dat = :idDonDat ",nativeQuery = true)
    List<PhongDat> findPhongDatByIdDonDat(@Param("idDonDat") Integer idDonDat);
    List<PhongDat> findPhongDatByDonDatIdDonDat(Integer id);

    @Query(value = "SELECT * FROM phong_dat \n" +
            "WHERE loai_phong_dat_id_loai_phong_dat = :idLoaiPhongDat ",nativeQuery = true)
    List<PhongDat> findPhongDatByIdLoaiPhongDat(@Param("idLoaiPhongDat") Integer idLoaiPhongDat);

    //get phong dat theo id phong(detail cua phong da gan)
    @Query(value = "select dich_vu_dat.so_tien, thong_tin_khach_o.id_khach_o, loai_phong.ten_loai_phong\n" +
            ", pd.so_tien_phong,pd.thoi_gian_vao,pd.thoi_gian_ra,phong.so_phong from\n" +
            "phong_dat pd join phong on phong.id_phong=pd.phong_id_phong join thong_tin_khach_o on \n" +
            "thong_tin_khach_o.phong_dat_id_phong_dat=pd.id_phong_dat join loai_phong on\n" +
            "loai_phong.Id_loai_phong=phong.loai_phong_Id_loai_phong  left join dich_vu_dat on dich_vu_dat.phong_dat_id_phong_dat=pd.id_phong_dat\n" +
            " where pd.id_phong_dat=:idPhongDat",nativeQuery = true)
    List<Object[]>getThongTinPhongDatByIdPhong(@Param("idPhongDat")Integer idPhongDat);

}