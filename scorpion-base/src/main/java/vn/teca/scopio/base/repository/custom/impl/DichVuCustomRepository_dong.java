package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.DichVu;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.model.TienIch;
import vn.teca.scopio.base.model.dto.LoaiDichVuDto;
import vn.teca.scopio.base.model.dto.LoaiPhongDtoDetail_dong;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DichVuCustomRepository_dong {
    @Autowired
    private EntityManager entityManager;

    public List<LoaiDichVuDto> detailLoaiDichVu(Integer idLoaiDichVu) {
        List<Object[]> detailLoaiDichVu = entityManager.createNativeQuery("select loai_dich_vu.ten_loai_dich_vu,loai_dich_vu.id_loai_dich_vu " +
                        "from loai_dich_vu " +
                        "where loai_dich_vu.id_loai_dich_vu=:idLoaiDichVu")
                .setParameter("idLoaiDichVu", idLoaiDichVu)
                .getResultList();

        List<Object[]> detailDichVu = entityManager.createNativeQuery("  select dich_vu.ten_dich_vu,dich_vu.gia_tien,dich_vu.id_dich_vu " +
                        "from dich_vu " +
                        "where dich_vu.loai_dich_vu_id_loai_dich_vu=:idLoaiDichVu")
                .setParameter("idLoaiDichVu", idLoaiDichVu)
                .getResultList();


        List<LoaiDichVuDto> listDetail = new ArrayList<>();


        for (Object[] result : detailLoaiDichVu) {
            LoaiDichVuDto loaiDichVuDto = new LoaiDichVuDto();
            List<DichVu> dichVuList = new ArrayList<>();
           loaiDichVuDto.setTenLoaiDichVu((String) result[0]);
           loaiDichVuDto.setIdLoaiDichVu((Integer) result[1]);
            for (Object[] reObjects : detailDichVu) {
                DichVu dichVu=new DichVu();
              dichVu.setTenDichVu((String) reObjects[0]);
              dichVu.setGiaTien((BigDecimal) reObjects[1]);
              dichVu.setId((Integer) reObjects[2]);
                dichVuList.add(dichVu);
            }
            loaiDichVuDto.setDichVu(dichVuList);
            listDetail.add(loaiDichVuDto);
        }

        System.out.printf(listDetail.toString());
        return listDetail;

    }
}
