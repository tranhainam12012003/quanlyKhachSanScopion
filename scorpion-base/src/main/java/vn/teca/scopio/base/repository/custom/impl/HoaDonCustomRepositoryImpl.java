package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.teca.scopio.base.model.dto.HoaDonRequestDto;
import vn.teca.scopio.base.repository.custom.HoaDonCustomRepository;
import vn.teca.scopio.base.util.DataConvertUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
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
                str.append(" dd.thoi_gian_vao,dd.thoi_gian_ra, ");
                str.append(" ht.ten_hinh_thuc,lp.gia_tien ");
                str.append(" FROM phong_dat pd ");
                str.append(" LEFT JOIN dich_vu_dat dvd ON pd.id_phong_dat = dvd.phong_dat_id_phong_dat  ");
                str.append(" INNER JOIN don_dat dd ON pd.don_dat_id_don_dat = dd.id_don_dat ");
                str.append(" INNER JOIN hinh_thuc_dat ht ON dd.hinh_thuc_dat_id_hinh_thuc_dat = ht.id_hinh_thuc_dat ");
                str.append(" INNER JOIN loai_phong_dat lpd ON pd.loai_phong_dat_id_loai_phong_dat = lpd.id_loai_phong_dat ");
                str.append(" INNER JOIN loai_phong lp ON lpd.loai_phong_Id_loai_phong = lp.Id_loai_phong ");
                str.append(" WHERE id_phong_dat = :id_phong_dat ");
                str.append(" GROUP BY pd.id_phong_dat, pd.so_tien_phong, dd.thoi_gian_vao,dd.thoi_gian_ra, ");
                str.append(" ht.ten_hinh_thuc,lp.gia_tien ; ");

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
                                       dto.setThoiGianVao((Timestamp) objects[3]);
                                       dto.setThoiGianRa((Timestamp) objects[4]);
                                       dto.setHinhThucDat(DataConvertUtil.safeToString(objects[5]));
                                       dto.setTienLoaiPhong(DataConvertUtil.safeToBigDecimal(objects[6]));
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
