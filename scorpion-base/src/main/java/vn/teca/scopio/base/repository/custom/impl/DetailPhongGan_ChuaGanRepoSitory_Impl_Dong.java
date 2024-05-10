package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.model.LoaiPhongDat;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.model.PhongDat;
import vn.teca.scopio.base.model.dto.DetailThongTinDonDatDTO_Dong;
import vn.teca.scopio.base.model.dto.DetailThongTinPhongDatDTO_Dong;
import vn.teca.scopio.base.model.dto.DichVuDatDTO_dong;
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
    public DetailThongTinDonDatDTO_Dong detailDonDat(Integer id) {
        Object[] detailPhongDat = (Object[]) entityManager.createNativeQuery("select pd.id_phong_dat,pd.don_dat_id_don_dat,pd.phong_id_phong,pd.loai_phong_dat_id_loai_phong_dat,pd.so_tien_phong,\n" +
                "pd.thoi_gian_vao,pd.thoi_gian_ra,pd.trang_thai,lp.ten_loai_phong,phong.so_phong ,lp.gia_tien as 'tiền loại phòng',lp.Id_loai_phong\n" +
                "from phong_dat pd left join loai_phong_dat lpd\n" +
                "on pd.loai_phong_dat_id_loai_phong_dat=lpd.id_loai_phong_dat\n" +
                "left join loai_phong lp on lp.Id_loai_phong=lpd.loai_phong_Id_loai_phong  left join phong on phong.id_phong=pd.phong_id_phong\n" +
                "where pd.id_phong_dat=:idPhongDat").setParameter("idPhongDat", id).getSingleResult();


        List<Object[]> getKhachO = entityManager.createNativeQuery("\n" +
                "select thong_tin_khach_o.id_khach_o,thong_tin_khach_o.ho_ten from thong_tin_khach_o left join phong_dat\n" +
                "on  thong_tin_khach_o.phong_dat_id_phong_dat=phong_dat.id_phong_dat\n" +
                "where thong_tin_khach_o.phong_dat_id_phong_dat=:idPhongDat").setParameter("idPhongDat", id).getResultList();
        List<KhachoDTO_Dong> khachoDTODongList = new ArrayList<>();
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
        detailThongTinDonDatDTODong.setIdLoaiPhong((Integer) detailPhongDat[11]);
        for (Object[] reObjectsKhachO : getKhachO) {
            KhachoDTO_Dong khachoDTODong = new KhachoDTO_Dong();
            khachoDTODong.setIdKhachO((Integer) reObjectsKhachO[0]);
            khachoDTODong.setTenKhachO((String) reObjectsKhachO[1]);
            khachoDTODongList.add(khachoDTODong);

        }
        detailThongTinDonDatDTODong.setKhachO(khachoDTODongList);
        return detailThongTinDonDatDTODong;
    }

    @Override
    public DetailThongTinPhongDatDTO_Dong detailPhongDat(Integer id) {
        Object[] detailPhongDat = (Object[]) entityManager.createNativeQuery("select pd.id_phong_dat,pd.don_dat_id_don_dat,pd.phong_id_phong,pd.loai_phong_dat_id_loai_phong_dat,pd.so_tien_phong,\n" +
                "                pd.thoi_gian_vao,pd.thoi_gian_ra,pd.trang_thai,lp.ten_loai_phong,phong.so_phong ,lp.gia_tien \n" +
                "\t\t\t\tas 'tiền loại phòng',lp.Id_loai_phong,don_dat.ghi_chu\n" +
                "                from phong_dat pd left join loai_phong_dat lpd\n" +
                "                on pd.loai_phong_dat_id_loai_phong_dat=lpd.id_loai_phong_dat\n" +
                "                left join loai_phong lp on lp.Id_loai_phong=lpd.loai_phong_Id_loai_phong  left join phong on phong.id_phong=pd.phong_id_phong\n" +
                "\t\t\t\tleft join don_dat on don_dat.id_don_dat=lpd.don_dat_id_don_dat\n" +
                "                where pd.id_phong_dat=:idPhongDat").setParameter("idPhongDat", id).getSingleResult();


        List<Object[]> getKhachO = entityManager.createNativeQuery("\n" +
                "select thong_tin_khach_o.id_khach_o,thong_tin_khach_o.ho_ten from thong_tin_khach_o left join phong_dat\n" +
                "on  thong_tin_khach_o.phong_dat_id_phong_dat=phong_dat.id_phong_dat\n" +
                "where thong_tin_khach_o.phong_dat_id_phong_dat=:idPhongDat").setParameter("idPhongDat", id).getResultList();

        List<Object[]> getDichVuDat = entityManager.createNativeQuery("select dich_vu_dat.id_dich_vu_dat,dich_vu.ten_dich_vu,dich_vu_dat.so_luong,dich_vu_dat.so_tien from dich_vu_dat left join \n" +
                "phong_dat on dich_vu_dat.phong_dat_id_phong_dat=phong_dat.id_phong_dat\n" +
                "left join dich_vu on dich_vu.id_dich_vu=dich_vu_dat.dich_vu_id_dich_vu\n" +
                "where phong_dat.id_phong_dat=:idPhongDat").setParameter("idPhongDat", id).getResultList();
        List<DichVuDatDTO_dong> dichVuDatDTOList = new ArrayList<>();
        List<KhachoDTO_Dong> khachoDTODongList = new ArrayList<>();
        DetailThongTinPhongDatDTO_Dong detailThongTinDonDatDTODong = new DetailThongTinPhongDatDTO_Dong();
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
        detailThongTinDonDatDTODong.setIdLoaiPhong((Integer) detailPhongDat[11]);
        detailThongTinDonDatDTODong.setGhiChu((String) detailPhongDat[12]);
        for (Object[] reObjectsKhachO : getKhachO) {
            KhachoDTO_Dong khachoDTODong = new KhachoDTO_Dong();
            khachoDTODong.setIdKhachO((Integer) reObjectsKhachO[0]);
            khachoDTODong.setTenKhachO((String) reObjectsKhachO[1]);
            khachoDTODongList.add(khachoDTODong);

        }
        detailThongTinDonDatDTODong.setKhachO(khachoDTODongList);
        for (Object[] reObjectsDichVuDat : getDichVuDat) {
            DichVuDatDTO_dong dichVuDatDTODong = new DichVuDatDTO_dong();
            dichVuDatDTODong.setIdDichVuDat((Integer) reObjectsDichVuDat[0]);
            dichVuDatDTODong.setTenDichVuDat((String) reObjectsDichVuDat[1]);
            dichVuDatDTODong.setSoLuong((Integer) reObjectsDichVuDat[2]);
            dichVuDatDTODong.setGiaDichVu((BigDecimal) reObjectsDichVuDat[3]);
            dichVuDatDTOList.add(dichVuDatDTODong);
        }
        detailThongTinDonDatDTODong.setDichVuDat(dichVuDatDTOList);
        BigDecimal tongTienDichVu = BigDecimal.ZERO;
        for (DichVuDatDTO_dong dichVu : dichVuDatDTOList) {
            BigDecimal giaDichVu = dichVu.getGiaDichVu();
            tongTienDichVu = tongTienDichVu.add(giaDichVu);
        }

        detailThongTinDonDatDTODong.setTongTienDichVu(tongTienDichVu);
        return detailThongTinDonDatDTODong;
    }


}

