package vn.teca.scopio.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.PhongDat;
import vn.teca.scopio.base.model.dto.DetailThongTinDonDatDTO_Dong;
import vn.teca.scopio.base.model.dto.PhongChuaGan_DTO_Dong;
import vn.teca.scopio.base.repository.PhongDatRepository;
import vn.teca.scopio.base.repository.custom.impl.DetailPhongGan_ChuaGanRepoSitory_Impl_Dong;
import vn.teca.scopio.base.repository.custom.impl.PhongDatCustomRepositoryImpl_Dong;

import java.util.List;
import java.util.Optional;

@Service
public class PhongDatServices_Dong {
    @Autowired
    private PhongDatRepository phongDatRepository;
    @Autowired
    PhongDatCustomRepositoryImpl_Dong phongDatCustomRepositoryImplDong;
    @Autowired
    DetailPhongGan_ChuaGanRepoSitory_Impl_Dong detailPhongGanChuaGanRepoSitoryImplDong;

    public List<PhongDat> getall() {
        return phongDatRepository.findAll();
    }

    public PhongDat detail(Integer id) {
        Optional<PhongDat> optional = phongDatRepository.findById(id);
        return optional.orElse(null);
    }

//    private PhongDTO_Dong mapToObjectPhongGan(Object[] result) {
//        PhongDTO_Dong phongDTO = new PhongDTO_Dong();
//        phongDTO.setTenLoaiPhong((String) result[0]);
//        phongDTO.setTenPhongDaGan((String) result[1]);
//        phongDTO.setSoLuongPhong((int) result[2]);
//        phongDTO.setIdPhong((int) result[3]);
//        return phongDTO;
//    }

//    private PhongDatDto_Dong mapToObjectPhongDat(Object[] result) {
//        PhongDatDto_Dong phongDatDto=new PhongDatDto_Dong();
//        phongDatDto.setTenKhach((String) result[0]);
//        phongDatDto.setTenLoaiPhong((String) result[1]);
//        phongDatDto.setSoTienPhong((BigDecimal) result[2]);
//        phongDatDto.setThoiGianVao((Timestamp) result[3]);
//        phongDatDto.setThoiGianRa((Timestamp) result[4]);
//        phongDatDto.setSoPhong((String) result[5]);
//        return phongDatDto;
//    }
//    public List<PhongDatDto_Dong> getDetailPhongDatByIdPhongDat(Integer id) {
//        List<Object[]> results = phongDatRepository.getThongTinPhongDatByIdPhong(id);
//        List<PhongDatDto_Dong> phongDatDtos = new ArrayList<>();
//        for (Object[] result : results) {
//            phongDatDtos.add(mapToObjectPhongDat(result));
//        }
//        return phongDatDtos;
//    }
//    private PhongDTO_Dong mapToObjectPhongChuaGan(Object[] result) {
//        PhongDTO_Dong phongDTO = new PhongDTO_Dong();
//        phongDTO.setTenLoaiPhong((String) result[0]);
//        phongDTO.setSoLuongPhongConLai((int) result[1]);
//        return phongDTO;
//    }

    //    public List<PhongDTO_Dong> getPhongDaGan(Integer id) {
//        List<Object[]> results = phongDatRepository.getPhongDaGan(id);
//        List<PhongDTO_Dong> phongDTOS = new ArrayList<>();
//        for (Object[] result : results) {
//            phongDTOS.add(mapToObjectPhongGan(result));
//        }
//        return phongDTOS;
//    }
//
//    public List<PhongDTO_Dong> getPhongChuaGan() {
//        List<Object[]> results = phongDatRepository.getPhongChuaGan();
//        List<PhongDTO_Dong> phongDTOS = new ArrayList<>();
//        for (Object[] result : results) {
//            phongDTOS.add(mapToObjectPhongChuaGan(result));
//        }
//        return phongDTOS;
//    }
    public List<PhongChuaGan_DTO_Dong> getPhongChuaGanVaDaGan(Integer id) {
        return phongDatCustomRepositoryImplDong.getPhongDaGanVaChuaGan(id);
    }

//    public List<DetailThongTinDonDatDTO_Dong> detailThongTinPhongDaGan(Integer idPhongDat) {
//        return detailPhongGanChuaGanRepoSitoryImplDong.detailPhongDaGan(idPhongDat);
//    }
//    public List<DetailThongTinDonDatDTO_Dong>detailThongTinPhongChuaGan(Integer idDonDat){
//
//        return detailPhongGanChuaGanRepoSitoryImplDong.detailPhongChuaGan(idDonDat);
//    }
    public DetailThongTinDonDatDTO_Dong getThongTinPhongDat(Integer id){
        return detailPhongGanChuaGanRepoSitoryImplDong.detailPhongDat(id);    }
}
