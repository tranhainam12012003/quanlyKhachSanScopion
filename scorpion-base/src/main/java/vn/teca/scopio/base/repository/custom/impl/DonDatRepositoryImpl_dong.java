package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.model.TienIch;
import vn.teca.scopio.base.model.dto.DTOThongTinLoaiPhong;
import vn.teca.scopio.base.model.dto.LoaiPhongDatDto_Dong;
import vn.teca.scopio.base.model.dto.LoaiPhongDtoDetail_dong;
import vn.teca.scopio.base.repository.custom.DonDatRepoSitoryDTO_dong;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DonDatRepositoryImpl_dong implements DonDatRepoSitoryDTO_dong {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<DTOThongTinLoaiPhong> detailDonDat(Integer idDonDat) {
        List<Object[]> detailDonDat = entityManager.createNativeQuery("select  DISTINCT dd.thoi_gian_vao,dd.thoi_gian_ra,\n" +
                        "   dd.tong_tien,ttkd.ho_ten,ttkd.ngay_sinh,ttkd.so_dien_thoai,ttkd.email,ttkd.quoc_tich,ttkd.gioi_tinh,ttkd.dia_chi,dd.ghi_chu\n" +
                        "from don_dat dd join loai_phong_dat lpd on dd.id_don_dat=lpd.don_dat_id_don_dat join thong_tin_khach_dat ttkd on\n" +
                        "ttkd.id_khach_dat=dd.thong_tin_khach_dat_id_khach_dat where dd.id_don_dat=:idDonDat")
                .setParameter("idDonDat", idDonDat)
                .getResultList();

        List<Object[]> getLoaiPhongDat = entityManager.createNativeQuery("select DISTINCT loai_phong.ten_loai_phong,loai_phong_dat.so_luong from loai_phong_dat join don_dat on loai_phong_dat.don_dat_id_don_dat=don_dat.id_don_dat\n" +
                        "join loai_phong on loai_phong.Id_loai_phong=loai_phong_dat.loai_phong_Id_loai_phong where don_dat.id_don_dat=:idDonDat")
                .setParameter("idDonDat", idDonDat)
                .getResultList();

        List<DTOThongTinLoaiPhong> listDetail = new ArrayList<>();


        for (Object[] result : detailDonDat) {
            DTOThongTinLoaiPhong dtoThongTinLoaiPhong = new DTOThongTinLoaiPhong();
            List<LoaiPhongDatDto_Dong> loaiPhongDatDtoDongList = new ArrayList<>();
            dtoThongTinLoaiPhong.setThoiGianVao((Timestamp) result[0]);
            dtoThongTinLoaiPhong.setThoiGianRa((Timestamp) result[1]);
            dtoThongTinLoaiPhong.setTienPhong((BigDecimal) result[2]);
            dtoThongTinLoaiPhong.setTen((String) result[3]);
            dtoThongTinLoaiPhong.setNgaySinh((Date) result[4]);
            dtoThongTinLoaiPhong.setSdt((String) result[5]);
            dtoThongTinLoaiPhong.setEmail((String) result[6]);
            dtoThongTinLoaiPhong.setQuocTich((String) result[7]);
            dtoThongTinLoaiPhong.setGioiTinh((Boolean) result[8]);
            dtoThongTinLoaiPhong.setDiaChi((String) result[9]);
            dtoThongTinLoaiPhong.setGhiChu((String) result[10]);
            for (Object[] reObjects : getLoaiPhongDat) {
                LoaiPhongDatDto_Dong loaiPhongDatDtoDong = new LoaiPhongDatDto_Dong();
                loaiPhongDatDtoDong.setTenLoaiPhong((String) reObjects[0]);
                loaiPhongDatDtoDong.setSoLuong((Integer) reObjects[1]);
                loaiPhongDatDtoDongList.add(loaiPhongDatDtoDong);
            }
            dtoThongTinLoaiPhong.setLoaiphongDat(loaiPhongDatDtoDongList);
            listDetail.add(dtoThongTinLoaiPhong);
        }
        return listDetail;
    }
}
