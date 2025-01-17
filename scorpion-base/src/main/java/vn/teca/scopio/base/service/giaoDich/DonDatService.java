package vn.teca.scopio.base.service.giaoDich;

import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.model.dto.DetailThongTinDonDatDTO_Dong;
import vn.teca.scopio.base.model.dto.DonDatDto;

import java.util.List;

public interface DonDatService {
    List<DonDat> findAllDonDat(int page);
    List<DonDat> findAllOnline(int page);
    List<DonDat> findAllOffline(int page);
    DonDat save(DonDat entity);

    List<DonDat> findById(Integer integer);

    List<DonDat> findBySDT(String sdt, int page);

    void deleteById(Integer integer);

    DonDat luuDonDat(DonDatDto donDatDto);

    void update(Integer i);

    List<DonDat> getListTheoKhach(Integer id);
//    DetailThongTinDonDatDTO_Dong mapToObject(Object[] result);
//    List<DetailThongTinDonDatDTO_Dong> getThongTinDonDat(Integer id);
//    DonDatDto createDonDat(DonDatDto donDatDTO);
//
//    //    public DonDat add(DonDat donDat,LoaiPhongDat loaiPhongDat){
//    //        loaiPhongDat.setDonDatIdDonDat(savedDonDat);
//    //        return donDatRepository.save(donDat) + loaiPhongDatRepository.save()
//    //
//    //    }
//    DonDatDto getDonDatById(Integer id) throws ResourceNotFoundException;
//
//    DonDatDto updateDonDat(Integer id, DonDatDto donDatDTO) throws ResourceNotFoundException;
//
//    void deleteDonDat(Integer id) throws ResourceNotFoundException;
//
////    Page<DonDat> findAll(Pageable pageable);
////
////    Optional<DonDat> findById(Integer id);
////
////    DonDat save(DonDat entity);
////
////    DonDat update(DonDat dt, Integer id);
////
////    void deleteById(Integer integer);
}
