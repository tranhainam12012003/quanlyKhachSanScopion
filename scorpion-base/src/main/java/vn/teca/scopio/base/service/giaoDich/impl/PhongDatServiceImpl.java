package vn.teca.scopio.base.service.giaoDich.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.model.LoaiPhong;
import vn.teca.scopio.base.model.LoaiPhongDat;
import vn.teca.scopio.base.model.PhongDat;
import vn.teca.scopio.base.model.dto.LoadDonDatDto;
import vn.teca.scopio.base.model.dto.PhongDatDto;
import vn.teca.scopio.base.repository.DonDatRepository;
import vn.teca.scopio.base.repository.LoaiPhongDatRepository;
import vn.teca.scopio.base.repository.LoaiPhongRepository;
import vn.teca.scopio.base.repository.PhongDatRepository;
import vn.teca.scopio.base.service.giaoDich.PhongDatServices;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhongDatServiceImpl implements PhongDatServices {
    @Autowired
    PhongDatRepository phongDatRepository;

    @Autowired
    LoaiPhongDatRepository loaiPhongDatRepository;

    @Autowired
    DonDatRepository donDatRepository;
    @Autowired
    private LoaiPhongRepository loaiPhongRepository;

    @Override
    public PhongDat save(PhongDatDto dto) {
        String trangThai = "WAIT FOR CHECKIN";
        PhongDat phongDat = new PhongDat();
        phongDat.setPhongIdPhong(dto.getPhongIdPhong());
        phongDat.setDonDatIdDonDat(dto.getDonDatIdDonDat());
        phongDat.setLoaiPhongDatIdLoaiPhongDat(dto.getLoaiPhongDat());
        phongDat.setThoiGianVao(dto.getThoiGianVao());
        phongDat.setThoiGianRa(dto.getThoiGianRa());
        phongDat.setSoTienPhong(dto.getSoTienPhong());
        phongDat.setTrangThai(trangThai);

        return phongDatRepository.save(phongDat);

    }

    @Override
    public List<LoadDonDatDto> getDonDatToChekIn(Integer idDonDat) {
        // B1 lay thong tin don dat
        DonDat donDat = donDatRepository.findById(idDonDat).orElse(null);
        List<LoaiPhongDat> lpd = loaiPhongDatRepository.findByIdDonDat(idDonDat);

        List<LoadDonDatDto> result = new ArrayList<>();

        for (LoaiPhongDat loaiPhongDat : lpd) {
            // B2 khoi tao ra so luong phong dat theo so luong cua loai phong dat cho check in
            // lay danh sach phong dat theo loai phong dat
            List<PhongDat> phongDat = phongDatRepository.findPhongDatByIdLoaiPhongDat(loaiPhongDat.getId());
            // new phong dat chua co du lieu thi khoi tao tho so luong cua loai phong dat
            if (phongDat.isEmpty()) {
//                phongDat = new ArrayList<>();
                // khoi tao phong dat chua gan phong

                for (int i = 1; i <= loaiPhongDat.getSoLuong(); i++) {
                    PhongDat item = new PhongDat();
                    item.setLoaiPhongDatIdLoaiPhongDat(loaiPhongDat);
                    item.setDonDatIdDonDat(donDat);
                    phongDat.add(item);

                }
                phongDatRepository.saveAll(phongDat);

//                item.setId(idDonDat);
//                PhongDat pdd = phongDat.get(i);
//                item.setPhongIdPhong(pdd.getPhongIdPhong());
//                item.set
            }

        }


//                phongDatRepository.save(phongDat);


        // lay phong dat theo don dat
        List<PhongDat> phongDat1 = phongDatRepository.findPhongDatByIdDonDat(idDonDat);
        for (PhongDat pd : phongDat1) {
            LoadDonDatDto dto = new LoadDonDatDto();
            dto.setIdDonDat(idDonDat);
            dto.setIdLoaiPhong(pd.getLoaiPhongDatIdLoaiPhongDat().getId());
//                dto.setTenLoaiPhong(loaiPhong.getTenLoaiPhong());
            dto.setIdPhongDat(pd.getId());
            // Cần lấy thông tin của phòng từ PhongDat và thiết lập cho LoadDonDatDto
//             dto.setIdPhong(pd.getPhongIdPhong().getId());
//             dto.setTenPhong(pd.getPhongIdPhong().getSoPho ng());
            result.add(dto);
        }


        return result;
//        phongDat = phongDatRepository.findPhongDatByIdDonDat(idDonDat);
//        LoaiPhong loaiPhong = loaiPhongRepository.getLoaiPhongByIdLoaiPhongDat(lpd.getId());
//            // Lặp qua danh sách phòng đặt để tạo danh sách kết quả
//            for (PhongDat pd : phongDat) {
//                LoadDonDatDto dto = new LoadDonDatDto();
//                dto.setIdDonDat(pd.getDonDatIdDonDat().getId());
//                dto.setIdLoaiPhong(loaiPhong.getId());
//                dto.setTenLoaiPhong(loaiPhong.getTenLoaiPhong());
//                dto.setIdPhongDat(pd.getId());
////                dto.setIdPhong(pd.getPhongIdPhong().getId());
////                dto.setTenPhong(pd.getPhongIdPhong().getSoPhong());
//                result.add(dto);
//            }
//            phongDat = phongDatRepository.findPhongDatByIdDonDat(idDonDat);


    }


    @Override
    public void checkin(Integer id) {
//        PhongDat phongDat = new PhongDat();
//        phongDat.setId(id);
        Optional<PhongDat> optional = phongDatRepository.findById(id);
        optional.map(o -> {
            o.setThoiGianVao(LocalDateTime.now());
            o.setTrangThai("Checkin");
            return phongDatRepository.save(o);
        }).orElse(null);
        Integer idDonDat = optional.get().getDonDatIdDonDat().getId();
        Optional<DonDat> dd = donDatRepository.findById(idDonDat);
        dd.map(o -> {
            o.setTrangThai("DANG O");
            return donDatRepository.save(o);
        }).orElse(null);
    }

    @Override
    public void update(PhongDatDto dto, Integer id) {
//        PhongDat phongDat = new PhongDat();
//        phongDat.setId(id);
        Optional<PhongDat> optional = phongDatRepository.findById(id);
        optional.map(o -> {
            o.setThoiGianRa(dto.getThoiGianRa());
            o.setSoTienPhong(dto.getSoTienPhong());
            o.setTrangThai("Checkin");
            return phongDatRepository.save(o);
        }).orElse(null);
    }

    @Override
    public void checkout(Integer id) {
//        PhongDat phongDat = new PhongDat();
//        phongDat.setId(id);
        Optional<PhongDat> optional = phongDatRepository.findById(id);
        optional.map(o -> {
            o.setThoiGianRa(LocalDateTime.now());
            o.setTrangThai("Checkout");
            return phongDatRepository.save(o);
        }).orElse(null);
//        checkoutDonDat(idDonDat);

    }

    @Override
    public Integer countCheckout(Integer id) {
        List<PhongDat> phongDat = phongDatRepository.findPhongDatByIdDonDat(id);
        int count = 0;
        for (PhongDat pd : phongDat) {
            if (pd.getTrangThai().equalsIgnoreCase("Checkout")) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void checkoutDonDat(Integer id) {
        // Bước 1: Lấy danh sách LoaiPhongDat
        List<LoaiPhongDat> loaiPhongDatList = loaiPhongDatRepository.findByIdDonDat(id);

        // Bước 2: Tính tổng số lượng phòng đặt từ danh sách các LoaiPhongDat
        int totalSoLuong = loaiPhongDatList.stream().mapToInt(LoaiPhongDat::getSoLuong).sum();
        int soLuongChechOut = countCheckout(id);
        if (totalSoLuong == soLuongChechOut) {
            Optional<DonDat> donDat = donDatRepository.findById(id);
            donDat.map(o -> {
                o.setTrangThai("Checkout");
                return donDatRepository.save(o);
            }).orElse(null);
        }

    }

}
