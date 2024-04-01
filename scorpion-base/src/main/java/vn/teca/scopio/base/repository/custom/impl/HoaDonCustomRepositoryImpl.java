package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.teca.scopio.base.model.dto.HoaDonRequestDto;
import vn.teca.scopio.base.repository.custom.HoaDonCustomRepository;
import vn.teca.scopio.base.util.DataConvertUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class HoaDonCustomRepositoryImpl implements HoaDonCustomRepository {
        @PersistenceContext(unitName = "entityManagerFactory")
        EntityManager entityManager;

        @Override
        public List<HoaDonRequestDto> hienThiHoaDon(Integer idPhongDat){
                StringBuilder str = new StringBuilder();
                str.append(" SELECT pd.id_phong_dat,pd.so_tien_phong, ");
                str.append(" SUM(dvd.so_tien) AS tong_tien_dich_vu_dat, ");
                str.append(" dd.tong_tien / lp.so_luong AS tien_da_thanh_toan_truoc ");
                str.append(" FROM phong_dat pd");
                str.append(" LEFT JOIN dich_vu_dat dvd ON pd.id_phong_dat = dvd.phong_dat_id_phong_dat ");
                str.append(" LEFT JOIN don_dat dd ON pd.don_dat_id_don_dat = dd.id_don_dat ");
                str.append(" LEFT JOIN loai_phong_dat lp ON pd.don_dat_id_don_dat = lp.don_dat_id_don_dat ");
                str.append(" WHERE pd.id_phong_dat = :id_phong_dat ");
                str.append(" GROUP BY pd.id_phong_dat, pd.so_tien_phong, dd.tong_tien, lp.so_luong; ");
                Query query = entityManager.createNativeQuery(str.toString());
                query.setParameter("id_phong_dat",idPhongDat);
                List<Object[]> lst = query.getResultList();
                List<HoaDonRequestDto> result = new ArrayList<>();
                try {
                        if (!DataConvertUtil.isNullOrEmpty(lst)){
                               lst.forEach(objects -> {
                                       HoaDonRequestDto dto = new HoaDonRequestDto();
                                       dto.setPhong_dat_id_phong_dat(DataConvertUtil.safeToInt(objects[0]));
                                       dto.setTienPhong(DataConvertUtil.safeToBigDecimal(objects[1]));
                                       dto.setTienDichVu(DataConvertUtil.safeToBigDecimal(objects[2]));
                                       dto.setTienDaThanhToan(DataConvertUtil.safeToBigDecimal(objects[3]));
                                       result.add(dto);
                               });
                               return new ArrayList<>(result);

                        }

                }catch (Exception e){
                        e.printStackTrace();
                }
                return new ArrayList<>(result);


        }
}
