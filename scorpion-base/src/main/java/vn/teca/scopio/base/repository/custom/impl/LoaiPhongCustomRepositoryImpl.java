package vn.teca.scopio.base.repository.custom.impl;

import org.springframework.stereotype.Repository;
import vn.teca.scopio.base.model.dto.HinhAnhDto;
import vn.teca.scopio.base.model.dto.LoaiPhongDto;
import vn.teca.scopio.base.repository.custom.LoaiPhongCustomRepository;
import vn.teca.scopio.base.util.DataConvertUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class LoaiPhongCustomRepositoryImpl implements LoaiPhongCustomRepository {
    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;

    @Override
    public List<LoaiPhongDto> findAllHinhAnh() {
        StringBuilder str = new StringBuilder();
        str.append("SELECT lp.Id_loai_phong, lp.ten_loai_phong, lp.huong_nhin, lp.dien_tich, lp.gia_tien, lp.mo_ta,lp.so_luong_nguoi_o ");
//        str.append("ha.hinh_anh_loai_phong,ti.ten_tien_ich ");
        str.append("FROM loai_phong lp ");
//        str.append("INNER JOIN hinh_anh ha ON lp.Id_loai_phong = ha.loai_phong_Id_loai_phong ");
//        str.append("INNER JOIN tien_ich_loai_phong tilp ON lp.Id_loai_phong = tilp.loai_phong_Id_loai_phong ");
//        str.append("INNER JOIN tien_ich ti ON tilp.tien_ich_id_tien_ich = ti.id_tien_ich;");
        Query query = entityManager.createNativeQuery(str.toString());
        List<Object[]> lst = query.getResultList();
        List<LoaiPhongDto> result = new ArrayList<>();
        try {
            if (!DataConvertUtil.isNullOrEmpty(lst)) {
                lst.forEach(objects -> {
                    LoaiPhongDto dto = new LoaiPhongDto();
                    dto.setId(DataConvertUtil.safeToInt(objects[0]));
                    dto.setTenLoaiPhong(DataConvertUtil.safeToString(objects[1]));
                    dto.setHuongNhin(DataConvertUtil.safeToString(objects[2]));
                    dto.setDienTich(DataConvertUtil.safeToString(objects[3]));
                    dto.setGiaTien(DataConvertUtil.safeToBigDecimal(objects[4]));
                    dto.setMoTa(DataConvertUtil.safeToString(objects[5]));
                    dto.setSoLuongNguoiO(DataConvertUtil.safeToInt(objects[6]));
                    result.add(dto);
                });
                return new ArrayList<>(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(result);
    }
    @Override
    public List<LoaiPhongDto> searchLoaiPhongTrong(LocalDateTime thoiGianVao, LocalDateTime thoiGianRa){
        StringBuilder str = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        str.append(" WITH PhongDaDat AS ( ");
        str.append(" SELECT DISTINCT lpd.loai_phong_Id_loai_phong ");
        str.append(" FROM loai_phong_dat lpd ");
        str.append(" INNER JOIN don_dat dd ON lpd.don_dat_id_don_dat = dd.id_don_dat ");
        str.append(" WHERE dd.trang_thai = 'THANG CONG' AND dd.trang_thai = 'DANG O' ");
        str.append(" AND ( ");
        str.append(" (dd.thoi_gian_vao BETWEEN :thoiGianVao AND :thoiGianRa) ");
        str.append(" OR (dd.thoi_gian_ra BETWEEN :thoiGianVao AND :thoiGianRa) ");
        str.append(" OR (dd.thoi_gian_vao <= :thoiGianVao AND dd.thoi_gian_ra >= :thoiGianRa) ");
        str.append(" ) ");
        str.append(" ), ");
        str.append(" SoLuongLoaiPhong AS ( ");
        str.append(" SELECT lp.Id_loai_phong, COUNT(*) AS SoLuongPhong ");
        str.append(" FROM loai_phong lp ");
        str.append(" LEFT JOIN phong p ON lp.Id_loai_phong = p.loai_phong_Id_loai_phong ");
        str.append(" GROUP BY lp.Id_loai_phong ");
        str.append(" ),");
        str.append(" SoLuongLoaiPhongDat AS ( ");
        str.append(" SELECT lpd.loai_phong_Id_loai_phong, SUM(lpd.so_luong) AS SoLuongPhongDat ");
        str.append(" FROM loai_phong_dat lpd ");
        str.append(" INNER JOIN don_dat dd ON lpd.don_dat_id_don_dat = dd.id_don_dat ");
        str.append(" WHERE dd.trang_thai = 'THANG CONG' ");
        str.append(" AND ( ");
        str.append(" (dd.thoi_gian_vao BETWEEN :thoiGianVao AND :thoiGianRa) ");
        str.append(" OR (dd.thoi_gian_ra BETWEEN :thoiGianVao AND :thoiGianRa) ");
        str.append(" OR (dd.thoi_gian_vao <= :thoiGianVao AND dd.thoi_gian_ra >= :thoiGianRa) ");
        str.append(" ) ");
        str.append(" GROUP BY lpd.loai_phong_Id_loai_phong ");
        str.append(" ) ");
        str.append(" SELECT lp.Id_loai_phong, lp.ten_loai_phong, lp.huong_nhin, lp.dien_tich, lp.gia_tien, ");
        str.append(" lp.mo_ta, lp.trang_thai, lp.so_luong_nguoi_o, ");
        str.append(" (ISNULL(SoLuongPhong, 0) - ISNULL(SoLuongPhongDat, 0)) AS SoLuongConTrong ");
        str.append(" FROM loai_phong lp ");
        str.append(" LEFT JOIN SoLuongLoaiPhong splp ON lp.Id_loai_phong = splp.Id_loai_phong ");
        str.append(" LEFT JOIN SoLuongLoaiPhongDat splpd ON lp.Id_loai_phong = splpd.loai_phong_Id_loai_phong ");
        params.put("thoiGianVao", thoiGianVao);
        params.put("thoiGianRa",thoiGianRa);
        Query query = entityManager.createNativeQuery(str.toString());
        query.setParameter("thoiGianVao", thoiGianVao);
        query.setParameter("thoiGianRa",thoiGianRa);
        List<Object[]> lst = query.getResultList();
        List<LoaiPhongDto> results = new ArrayList<>();
        try{
            if(!DataConvertUtil.isNullOrEmpty(lst)){
                lst.forEach(objects -> {
                    LoaiPhongDto dto = new LoaiPhongDto();
                    dto.setId(DataConvertUtil.safeToInt(objects[0]));
                    dto.setTenLoaiPhong(DataConvertUtil.safeToString(objects[1]));
                    dto.setHuongNhin(DataConvertUtil.safeToString(objects[2]));
                    dto.setDienTich(DataConvertUtil.safeToString(objects[3]));
                    dto.setGiaTien(DataConvertUtil.safeToBigDecimal(objects[4]));
                    dto.setMoTa(DataConvertUtil.safeToString(objects[5]));
                    dto.setSoLuongNguoiO(DataConvertUtil.safeToInt(objects[7]));
                    dto.setSoLuongTrong(DataConvertUtil.safeToInt(objects[8]));
                    results.add(dto);
                });
                return new ArrayList<>(results);

            }

        } catch (Exception e){
            e.printStackTrace();

        }
        return new ArrayList<>(results);
    }

//    @Override
//    public List<HinhAnhDto> findByIdLoaiPhong(Integer id){
//        StringBuilder str = new StringBuilder();
//        str.append(" SELECT ha.hinh_anh_loai_phong FROM hinh_anh ha ");
//        str.append(" JOIN loai_phong lp ON ha.loai_phong_Id_loai_phong = lp.Id_loai_phong ");
//        str.append(" WHERE lp.Id_loai_phong = :idLoaiPhong ");
//        Query query = entityManager.createNativeQuery(str.toString());
//        query.setParameter("idLoaiPhong",id);
//        List<Object[]> lst = query.getResultList();
//        List<HinhAnhDto> result = new ArrayList<>();
//        try{
//            if(!DataConvertUtil.isNullOrEmpty(lst)){
//                lst.forEach(objects -> {
//                    HinhAnhDto dto = new HinhAnhDto();
//                    dto.setHinhAnhLoaiPhong((byte[]) objects[0]);
//                    result.add(dto);
//                });
//                return new ArrayList<>(result);
//
//            }
//
//        } catch (Exception e){
//            e.printStackTrace();
//
//        }
//        return new ArrayList<>(result);
//    }
}
