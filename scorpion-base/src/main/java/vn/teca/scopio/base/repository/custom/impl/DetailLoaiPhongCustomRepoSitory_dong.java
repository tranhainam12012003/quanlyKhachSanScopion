package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.model.TienIch;
import vn.teca.scopio.base.model.dto.DetailThongTinDonDatDTO_Dong;
import vn.teca.scopio.base.model.dto.KhachoDTO_Dong;
import vn.teca.scopio.base.model.dto.LoaiPhongDTOAdd;
import vn.teca.scopio.base.model.dto.LoaiPhongDtoDetail_dong;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DetailLoaiPhongCustomRepoSitory_dong {
    @Autowired
    private EntityManager entityManager;

    public LoaiPhongDTOAdd detailLoaiPhong(Integer idLoaiPhong) {
        List<Object[]> detailLoaiPhong = entityManager.createNativeQuery("  select loai_phong.Id_loai_phong,loai_phong.ten_loai_phong,loai_phong.dien_tich,\n" +
                        "  loai_phong.gia_tien,loai_phong.huong_nhin,loai_phong.mo_ta,loai_phong.so_luong_nguoi_o\n" +
                        "  from \n" +
                        "  loai_phong where Id_loai_phong=:idLoaiPhong")
                .setParameter("idLoaiPhong", idLoaiPhong)
                .getResultList();

        List<Object[]> getPhong = entityManager.createNativeQuery("select phong.so_phong,phong.id_phong from phong \n" +
                        "  join loai_phong on loai_phong.Id_loai_phong=phong.loai_phong_Id_loai_phong where loai_phong.Id_loai_phong=:idLoaiPhong")
                .setParameter("idLoaiPhong", idLoaiPhong)
                .getResultList();

//        List<Object[]> getTienIch = entityManager.createNativeQuery(" select tien_ich.id_tien_ich,tien_ich.ten_tien_ich from tien_ich join tien_ich_loai_phong\n" +
//                        "  on tien_ich.id_tien_ich=tien_ich_loai_phong.tien_ich_id_tien_ich \n" +
//                        "  where tien_ich_loai_phong.loai_phong_Id_loai_phong=:idLoaiPhong")
//                .setParameter("idLoaiPhong", idLoaiPhong)
//                .getResultList();

        List<LoaiPhongDtoDetail_dong> listDetail = new ArrayList<>();


        LoaiPhongDTOAdd loaiPhongDtoDetailDong = new LoaiPhongDTOAdd();
        for (Object[] result : detailLoaiPhong) {
            List<Phong> listPhong = new ArrayList<>();
            List<TienIch> listTienIch = new ArrayList<>();

            loaiPhongDtoDetailDong.setIdLoaiPhong((Integer) result[0]);
            loaiPhongDtoDetailDong.setTenLoaiPhong((String) result[1]);
            loaiPhongDtoDetailDong.setDienTich((String) result[2]);
            loaiPhongDtoDetailDong.setGiaTien((BigDecimal) result[3]);
            loaiPhongDtoDetailDong.setHuongNhin((String) result[4]);
            loaiPhongDtoDetailDong.setMoTa((String) result[5]);
            loaiPhongDtoDetailDong.setSoNguoi((Integer) result[6]);
            for (Object[] reObjects : getPhong) {
               Phong phong=new Phong();
                phong.setSoPhong((String) reObjects[0]);
                phong.setId((Integer) reObjects[1]);
                listPhong.add(phong);
            }
            loaiPhongDtoDetailDong.setPhongidPhong(listPhong);

//            for(Object[] reObjectsTienIch: getTienIch){
//                TienIch tienIch=new TienIch();
//                tienIch.setId((Integer) reObjectsTienIch[0]);
//                tienIch.setTenTienIch((String) reObjectsTienIch[1]);
//                listTienIch.add(tienIch);
//            }
//            loaiPhongDtoDetailDong.setTienichidtienich(listTienIch);
////            listDetail.add(loaiPhongDtoDetailDong);
        }

        System.out.printf(listDetail.toString());
        return loaiPhongDtoDetailDong;

    }


}
