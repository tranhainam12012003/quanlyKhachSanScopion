package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.dto.DetailThongTinDonDatDTO_Dong;
import vn.teca.scopio.base.model.dto.KhachoDTO_Dong;
import vn.teca.scopio.base.model.dto.PhongChuaGan_DTO_Dong;
import vn.teca.scopio.base.model.dto.PhongDaGanDTO_Dong;
import vn.teca.scopio.base.repository.custom.DetailPhongGan_ChuaGanRepository_dong;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Repository
public class DetailPhongGan_ChuaGanRepoSitory_Impl_Dong implements DetailPhongGan_ChuaGanRepository_dong {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<DetailThongTinDonDatDTO_Dong> detailPhongDaGan(Integer idPhongDat) {
        List<Object[]> detalphongDaGanResults = entityManager.createNativeQuery("select loai_phong.so_luong_nguoi_o,loai_phong.ten_loai_phong\n" +
                        ", pd.so_tien_phong,pd.thoi_gian_vao,pd.thoi_gian_ra,phong.so_phong,pd.id_phong_dat from\n" +
                        "phong_dat pd join phong on phong.id_phong=pd.phong_id_phong  join loai_phong on\n" +
                        "loai_phong.Id_loai_phong=phong.loai_phong_Id_loai_phong where pd.id_phong_dat=:idPhongDat")
                .setParameter("idPhongDat", idPhongDat)
                .getResultList();

        List<Object[]> getKhachOTrongPhong = entityManager.createNativeQuery("select thong_tin_khach_o.ho_ten,thong_tin_khach_o.id_khach_o from thong_tin_khach_o join phong_dat on phong_dat.id_phong_dat=thong_tin_khach_o.phong_dat_id_phong_dat\n" +
                        "where thong_tin_khach_o.phong_dat_id_phong_dat=:idPhongDat")
                .setParameter("idPhongDat", idPhongDat)
                .getResultList();

        List<DetailThongTinDonDatDTO_Dong> listDetail = new ArrayList<>();

        // Process phongDaGanResults
        for (Object[] result : detalphongDaGanResults) {
            DetailThongTinDonDatDTO_Dong detailThongTinDonDatDTODong = new DetailThongTinDonDatDTO_Dong();
            List<KhachoDTO_Dong> listkhacho = new ArrayList<>();

            detailThongTinDonDatDTODong.setSoLuongNguoiO((Integer) result[0]);
            detailThongTinDonDatDTODong.setTenLoaiPhong((String) result[1]);
            detailThongTinDonDatDTODong.setTongTien((BigDecimal) result[2]);
            detailThongTinDonDatDTODong.setThoiGianVao((Timestamp) result[3]);
            detailThongTinDonDatDTODong.setThoiGianRa((Timestamp) result[4]);
            detailThongTinDonDatDTODong.setSoPhong((String) result[5]);
            detailThongTinDonDatDTODong.setIdPhongDat((Integer) result[6]);
            for (Object[] reObjects : getKhachOTrongPhong) {
                KhachoDTO_Dong khachoDTODong=new KhachoDTO_Dong();
               khachoDTODong.setTenKhachO((String) reObjects[0]);
               khachoDTODong.setIdKhachO((Integer) reObjects[1]);
                listkhacho.add(khachoDTODong);
            }
            detailThongTinDonDatDTODong.setKhachO(listkhacho);

            listDetail.add(detailThongTinDonDatDTODong);
        }


        return listDetail;
    }
}

