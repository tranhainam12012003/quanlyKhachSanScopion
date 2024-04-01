package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.dto.PhongChuaGan_DTO_Dong;
import vn.teca.scopio.base.model.dto.PhongDaGanDTO_Dong;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PhongDatCustomRepositoryImpl_Dong {
    @Autowired
    private EntityManager entityManager;

    public List<PhongChuaGan_DTO_Dong> getPhongDaGanVaChuaGan(Integer idDonDat) {
        List<Object[]> phongDaGanResults = entityManager.createNativeQuery("select loai_phong.ten_loai_phong,phong.so_phong,pd.id_phong_dat \n" +
                        "from phong_dat pd inner join loai_phong_dat lp on \n" +
                        "pd.don_dat_id_don_dat=lp.don_dat_id_don_dat\n" +
                        "join phong on phong.id_phong=pd.phong_id_phong join loai_phong\n" +
                        "on loai_phong.Id_loai_phong=phong.loai_phong_Id_loai_phong\n" +
                        "where pd.don_dat_id_don_dat=:idDonDat group by loai_phong.ten_loai_phong,phong.so_phong,pd.id_phong_dat")
                .setParameter("idDonDat", idDonDat)
                .getResultList();

        List<Object[]> phongChuaGanResults = entityManager.createNativeQuery("SELECT dd.id_don_dat, lpd.so_luong - COALESCE(count(pd.id_phong_dat), 0) AS so_luong_con_lai\n" +
                        "FROM loai_phong_dat lpd\n" +
                        "JOIN don_dat dd ON lpd.don_dat_id_don_dat = dd.id_don_dat join loai_phong \n" +
                        " on loai_phong.Id_loai_phong=lpd.loai_phong_Id_loai_phong\n" +
                        "LEFT JOIN phong_dat pd ON dd.id_don_dat = pd.don_dat_id_don_dat\n" +
                        "GROUP BY  dd.id_don_dat, lpd.so_luong;\n")
                .getResultList();

        List<PhongChuaGan_DTO_Dong> phongDTOs = new ArrayList<>();

        // Process phongDaGanResults
        for (Object[] result : phongChuaGanResults) {
            PhongChuaGan_DTO_Dong phongDTO = new PhongChuaGan_DTO_Dong();
            List<PhongDaGanDTO_Dong> listPhongDaGan = new ArrayList<>();

            phongDTO.setIdDonDat((Integer) result[0]);
            phongDTO.setSoLuongChuaGan((Integer) result[1]);
            for (Object[] reObjects : phongDaGanResults) {
                PhongDaGanDTO_Dong phongDaGanDTODong=new PhongDaGanDTO_Dong();
                phongDaGanDTODong.setTenLoaiPhong((String) reObjects[0]);
                phongDaGanDTODong.setTenPhong((String) reObjects[1]);
                phongDaGanDTODong.setIdPhongDat((Integer) reObjects[2]);
                listPhongDaGan.add(phongDaGanDTODong);
            }
            phongDTO.setPhongDaGanDTODongs(listPhongDaGan);

            phongDTOs.add(phongDTO);
        }


        return phongDTOs;
    }

}
