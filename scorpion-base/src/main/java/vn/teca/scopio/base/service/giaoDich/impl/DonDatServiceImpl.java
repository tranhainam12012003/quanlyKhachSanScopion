package vn.teca.scopio.base.service.giaoDich.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.model.LoaiPhongDat;
import vn.teca.scopio.base.model.dto.DonDatDto;
import vn.teca.scopio.base.model.dto.LoaiPhongDatDto;
import vn.teca.scopio.base.model.dto.LoaiPhongDto;
import vn.teca.scopio.base.repository.DonDatRepository;
import vn.teca.scopio.base.repository.LoaiPhongDatRepository;
import vn.teca.scopio.base.service.LoaiPhongServices;
import vn.teca.scopio.base.service.giaoDich.DonDatService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DonDatServiceImpl implements DonDatService {

    @Autowired
    private DonDatRepository donDatRepository;

    @Autowired
    private LoaiPhongDatRepository loaiPhongDatRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LoaiPhongServices loaiPhongServices;

    @Override
    public List<DonDat> findAllDonDat(int page) {
        Pageable pageable=PageRequest.of(page,15);
        return donDatRepository.getList(pageable);
    }

    @Override
    public List<DonDat> findAllOnline(int page) {
        Pageable pageable = PageRequest.of(page, 15);
        return donDatRepository.findAllOnline(pageable);
    }

    @Override
    public List<DonDat> findAllOffline(int page) {
        Pageable pageable = PageRequest.of(page, 15);
        return donDatRepository.findAllOffline(pageable);
    }

    @Override
    public DonDat save(DonDat entity) {
        return donDatRepository.save(entity);
    }

    @Override
    public List<DonDat> findById(Integer integer) {
        Optional<DonDat> optional = donDatRepository.findById(integer);
        List<DonDat> dt = new ArrayList<>();
        if(optional.isPresent()){
            dt.add(optional.get());
        }
        return dt;
    }
    @Override
    public List<DonDat> findBySDT(String sdt, int page){
        Pageable pageable = PageRequest.of(page,15);
        return donDatRepository.findDonDatByThongTinKhachDatIdKhachDat_SoDienThoai(sdt,pageable);

    }

//    public DonDat Page<DonDat> findAll(Example<S> example, Pageable pageable) {
//        return donDatRepository.findAll(example, pageable);
//    }

    @Override
    public void deleteById(Integer integer) {
        donDatRepository.deleteById(integer);
    }

    @Override
    public DonDat luuDonDat(DonDatDto donDatDto) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        LocalDateTime vao = LocalDateTime.parse(thoiGianVao, formatter);
//        LocalDateTime ra = LocalDateTime.parse(thoiGianRa, formatter);
//        List<LoaiPhongDto> loaiPhongDtos = loaiPhongServices.searchLoaiPhongTrong(donDatDto.getThoiGianVao(),donDatDto.getThoiGianRa());
//        loaiPhongDtos.forEach(loaiPhongDto -> {
//            for ()
//        });
        DonDat donDat = new DonDat();
        donDat.setTongTien(donDatDto.getTongTien());
        donDat.setThoiGianVao(donDatDto.getThoiGianVao());
        donDat.setTrangThai(donDatDto.getTrangThai());
        donDat.setHinhThucDatIdHinhThucDat(donDatDto.getHinhThucDatIdHinhThucDat());
        donDat.setThongTinKhachDatIdKhachDat(donDatDto.getThongTinKhachDatIdKhachDat());
        donDat.setThoiGianRa(donDatDto.getThoiGianRa());
        donDat.setGhiChu(donDatDto.getGhiChu());
        donDatRepository.save(donDat);


        List<LoaiPhongDat> loaiPhongDatList = new ArrayList<>();
        for (LoaiPhongDatDto loaiPhongDatDto : donDatDto.getLoaiPhongDatDto()) {
            LoaiPhongDat loaiPhongDat = new LoaiPhongDat();
            loaiPhongDat.setDonDatIdDonDat(donDat);
            loaiPhongDat.setLoaiPhongIdLoaiPhong(loaiPhongDatDto.getLoaiPhongIdLoaiPhong());
            loaiPhongDat.setSoLuong(loaiPhongDatDto.getSoLuong());
            loaiPhongDatList.add(loaiPhongDat);
        }
        loaiPhongDatRepository.saveAll(loaiPhongDatList);
//         donDatDto.setLoaiPhongDatDto(loaiPhongDat);

        return donDat;

    }

    @Override
    public void update(Integer i) {
//        DonDat donDat = new DonDat();
//        donDat.setId(donDat.getId());
//        DonDat donDat = new DonDat();
//        donDat.setId(donDat.getId());
        Optional<DonDat> optional = donDatRepository.findById(i);
        optional.map(o ->{
            o.setTrangThai("HUY DON DAT");
            return donDatRepository.save(o);
        }).orElse(null);
    }



//    @Override
//    public DetailThongTinDonDatDTO_Dong mapToObject(Object[] result) {
//        DetailThongTinDonDatDTO_Dong detailThongTinDonDatDTO=new DetailThongTinDonDatDTO_Dong();
//        detailThongTinDonDatDTO.setThoiGianVao((Timestamp) result[0]);
//        detailThongTinDonDatDTO.setThoiGianRa((Timestamp) result[1]);
//        detailThongTinDonDatDTO.setTenLoaiPhong((String) result[2]);
//        detailThongTinDonDatDTO.setSoLuongNguoiO((Integer) result[3]);
//        detailThongTinDonDatDTO.setTongTien((BigDecimal) result[4]);
//        detailThongTinDonDatDTO.setTenKhachDat((String) result[5]);
//        return detailThongTinDonDatDTO;
//    }
//
//    @Override
//    public List<DetailThongTinDonDatDTO_Dong> getThongTinDonDat(Integer id) {
//        List<Object[]> results = donDatRepository.detailTheoIdDonDat(id);
//        List<DetailThongTinDonDatDTO_Dong> detailThongTinDonDatDTOS = new ArrayList<>();
//        for (Object[] result : results) {
//            detailThongTinDonDatDTOS.add(mapToObject(result));
//        }
//        return detailThongTinDonDatDTOS;
//    }
    @Override
    public List<DonDat> getListTheoKhach(Integer id){
        return donDatRepository.findByIdKhachDat(id);
    }

}
