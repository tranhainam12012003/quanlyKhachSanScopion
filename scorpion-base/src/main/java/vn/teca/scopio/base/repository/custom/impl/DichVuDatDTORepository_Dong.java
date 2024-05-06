package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.DichVu;
import vn.teca.scopio.base.model.dto.DichVuDatDTO_dong;
import vn.teca.scopio.base.model.dto.LoaiDichVuDto;
import vn.teca.scopio.base.repository.custom.DichvuDatDto_dongImpl;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DichVuDatDTORepository_Dong implements DichvuDatDto_dongImpl {
    @Autowired
    private EntityManager entityManager;

    @Override
    public DichVuDatDTO_dong detail(Integer idDichVuDat) {
        List<Object[]> detailDichVuDat = entityManager.createNativeQuery("SELECT dich_vu_dat.id_dich_vu_dat, dich_vu.ten_dich_vu, dich_vu_dat.so_luong, dich_vu_dat.so_tien \n" +
                        "            FROM dich_vu_dat \n" +
                        "            INNER JOIN dich_vu ON dich_vu.id_dich_vu = dich_vu_dat.dich_vu_id_dich_vu\n" +
                        "            WHERE dich_vu_dat.id_dich_vu_dat =:idDichVuDat")
                .setParameter("idDichVuDat", idDichVuDat)
                .getResultList();

        for (Object[] result : detailDichVuDat) {
            DichVuDatDTO_dong dichVuDatDTODong = new DichVuDatDTO_dong();
            dichVuDatDTODong.setIdDichVuDat((Integer) result[0]);
            dichVuDatDTODong.setTenDichVuDat((String) result[1]);
            dichVuDatDTODong.setSoLuong((Integer) result[2]);
            dichVuDatDTODong.setGiaDichVu((BigDecimal) result[3]);
            return dichVuDatDTODong;
        }
        return null;
    }
}
