package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.model.LoaiPhongDat;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.model.PhongDat;
import vn.teca.scopio.base.model.dto.DetailThongTinDonDatDTO_Dong;
import vn.teca.scopio.base.model.dto.KhachoDTO_Dong;
import vn.teca.scopio.base.model.dto.PhongChuaGan_DTO_Dong;
import vn.teca.scopio.base.model.dto.PhongDaGanDTO_Dong;
import vn.teca.scopio.base.repository.custom.DetailPhongGan_ChuaGanRepository_dong;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DetailPhongGan_ChuaGanRepoSitory_Impl_Dong implements DetailPhongGan_ChuaGanRepository_dong {
    @Autowired
    private EntityManager entityManager;

    @Override
    public DetailThongTinDonDatDTO_Dong detailPhongDat(Integer id) {
        Object[] detailPhongDat = (Object[]) entityManager.createNativeQuery("select pd.id_phong_dat,pd.don_dat_id_don_dat,pd.phong_id_phong,pd.loai_phong_dat_id_loai_phong_dat,pd.so_tien_phong,\n" +
                "pd.thoi_gian_vao,pd.thoi_gian_ra,pd.trang_thai,lp.ten_loai_phong,phong.so_phong ,lp.gia_tien as 'tiền loại phòng'\n" +
                "from phong_dat pd left join loai_phong_dat lpd\n" +
                "on pd.loai_phong_dat_id_loai_phong_dat=lpd.id_loai_phong_dat\n" +
                "join loai_phong lp on lp.Id_loai_phong=lpd.loai_phong_Id_loai_phong  join phong on phong.id_phong=pd.phong_id_phong\n" +
                "where pd.id_phong_dat=:idPhongDat").setParameter("idPhongDat", id).getSingleResult();


        List<Object[]> getKhachO = entityManager.createNativeQuery("\n" +
                "select thong_tin_khach_o.id_khach_o,thong_tin_khach_o.ho_ten from thong_tin_khach_o left join phong_dat\n" +
                "on  thong_tin_khach_o.phong_dat_id_phong_dat=phong_dat.id_phong_dat\n" +
                "where thong_tin_khach_o.phong_dat_id_phong_dat=:idPhongDat").setParameter("idPhongDat", id).getResultList();

            List<KhachoDTO_Dong>khachoDTODongList=new ArrayList<>();
            DetailThongTinDonDatDTO_Dong detailThongTinDonDatDTODong = new DetailThongTinDonDatDTO_Dong();
            detailThongTinDonDatDTODong.setIdPhongDat((Integer) detailPhongDat[0]);
            detailThongTinDonDatDTODong.setIdDonDat((Integer) detailPhongDat[1]);
            detailThongTinDonDatDTODong.setIdPhong((Integer) detailPhongDat[2]);
            detailThongTinDonDatDTODong.setIdLoaiPhongDat((Integer) detailPhongDat[3]);
            detailThongTinDonDatDTODong.setSoTienPhong((BigDecimal) detailPhongDat[4]);
            detailThongTinDonDatDTODong.setThoiGianVao((Timestamp) detailPhongDat[5]);
            detailThongTinDonDatDTODong.setThoiGianRa((Timestamp) detailPhongDat[6]);
            detailThongTinDonDatDTODong.setTrangThai((String) detailPhongDat[7]);
            detailThongTinDonDatDTODong.setTenLoaiPhong((String) detailPhongDat[8]);
            detailThongTinDonDatDTODong.setTenPhong((String) detailPhongDat[9]);
            detailThongTinDonDatDTODong.setTienLoaiPhong((BigDecimal) detailPhongDat[10]);
            for (Object[] reObjectsKhachO:getKhachO){
                KhachoDTO_Dong khachoDTODong=new KhachoDTO_Dong();
                khachoDTODong.setIdKhachO((Integer) reObjectsKhachO[0]);
                khachoDTODong.setTenKhachO((String) reObjectsKhachO[1]);
                khachoDTODongList.add(khachoDTODong);

            }
            detailThongTinDonDatDTODong.setKhachO(khachoDTODongList);


        return detailThongTinDonDatDTODong;
    }

//    @Override
//    public List<DetailThongTinDonDatDTO_Dong> detailPhongDaGan(Integer idPhongDat) {
////        List<Object[]> detalphongDaGanResults = entityManager.createNativeQuery("select loai_phong.so_luong_nguoi_o,loai_phong.ten_loai_phong\n" +
////                        ", pd.so_tien_phong,pd.thoi_gian_vao,pd.thoi_gian_ra,phong.so_phong,pd.id_phong_dat from\n" +
////                        "phong_dat pd join phong on phong.id_phong=pd.phong_id_phong  join loai_phong on\n" +
////                        "loai_phong.Id_loai_phong=phong.loai_phong_Id_loai_phong where pd.id_phong_dat=:idPhongDat")
////                .setParameter("idPhongDat", idPhongDat)
////                .getResultList();
////
////        List<Object[]> getKhachOTrongPhong = entityManager.createNativeQuery("select thong_tin_khach_o.ho_ten,thong_tin_khach_o.id_khach_o from thong_tin_khach_o join phong_dat on phong_dat.id_phong_dat=thong_tin_khach_o.phong_dat_id_phong_dat\n" +
////                        "where thong_tin_khach_o.phong_dat_id_phong_dat=:idPhongDat")
////                .setParameter("idPhongDat", idPhongDat)
////                .getResultList();
////
////        List<DetailThongTinDonDatDTO_Dong> listDetail = new ArrayList<>();
////
////        // Process phongDaGanResults
////        for (Object[] result : detalphongDaGanResults) {
////            DetailThongTinDonDatDTO_Dong detailThongTinDonDatDTODong = new DetailThongTinDonDatDTO_Dong();
////            List<KhachoDTO_Dong> listkhacho = new ArrayList<>();
////
////            detailThongTinDonDatDTODong.setSoLuongNguoiO((Integer) result[0]);
////            detailThongTinDonDatDTODong.setTenLoaiPhong((String) result[1]);
////            detailThongTinDonDatDTODong.setTongTien((BigDecimal) result[2]);
////            detailThongTinDonDatDTODong.setThoiGianVao((Timestamp) result[3]);
////            detailThongTinDonDatDTODong.setThoiGianRa((Timestamp) result[4]);
////            detailThongTinDonDatDTODong.setSoPhong((String) result[5]);
////            detailThongTinDonDatDTODong.setIdPhongDat((Integer) result[6]);
////            for (Object[] reObjects : getKhachOTrongPhong) {
////                KhachoDTO_Dong khachoDTODong=new KhachoDTO_Dong();
////               khachoDTODong.setTenKhachO((String) reObjects[0]);
////               khachoDTODong.setIdKhachO((Integer) reObjects[1]);
////                listkhacho.add(khachoDTODong);
////            }
////            detailThongTinDonDatDTODong.setKhachO(listkhacho);
////
////            listDetail.add(detailThongTinDonDatDTODong);
////        }
////
////
////        return listDetail;
//    }
//    @Override
//    public List<DetailThongTinDonDatDTO_Dong> detailPhongChuaGan(Integer idDonDat){
//        List<Object[]> detalphongChuaGanResults = entityManager.createNativeQuery("select don_dat.thoi_gian_vao,don_dat.thoi_gian_ra,\n" +
//                        "loai_phong.ten_loai_phong,loai_phong.so_luong_nguoi_o,don_dat.tong_tien\n" +
//                        " from loai_phong_dat join don_dat\n" +
//                        "on loai_phong_dat.don_dat_id_don_dat=don_dat_id_don_dat join loai_phong on\n" +
//                        "loai_phong.Id_loai_phong=loai_phong_dat.loai_phong_Id_loai_phong \n" +
//                        "where don_dat.id_don_dat=:idDonDat")
//                .setParameter("idDonDat",  idDonDat)
//                .getResultList();
//        List<DetailThongTinDonDatDTO_Dong>detailThongTinDonDatDTODongList=new ArrayList<>();
//        for(Object[] reObjects:detalphongChuaGanResults){
//            DetailThongTinDonDatDTO_Dong detailThongTinDonDatDTODong=new DetailThongTinDonDatDTO_Dong();
//            detailThongTinDonDatDTODong.setThoiGianVao((Timestamp) reObjects[0]);
//            detailThongTinDonDatDTODong.setThoiGianRa((Timestamp) reObjects[1]);
//            detailThongTinDonDatDTODong.setTenLoaiPhong((String)reObjects[2]);
//            detailThongTinDonDatDTODong.setSoLuongNguoiO((int)reObjects[3]);
//            detailThongTinDonDatDTODong.setTongTien((BigDecimal) reObjects[4]);
//            detailThongTinDonDatDTODong.setKhachO(null);
//            detailThongTinDonDatDTODong.setSoPhong(null);
//            detailThongTinDonDatDTODong.setIdPhongDat(null);
//            detailThongTinDonDatDTODongList.add(detailThongTinDonDatDTODong);
//        }
//        return detailThongTinDonDatDTODongList;
//    }
}

