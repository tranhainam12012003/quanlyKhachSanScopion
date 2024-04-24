package vn.teca.scopio.base.service.giaoDich.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.teca.scopio.base.model.*;
import vn.teca.scopio.base.model.dto.DetailThongTinDonDatDTO_Dong;
import vn.teca.scopio.base.model.dto.LoadDonDatDto;
import vn.teca.scopio.base.model.dto.PhongDatDto;
import vn.teca.scopio.base.repository.*;
import vn.teca.scopio.base.repository.custom.impl.DetailPhongGan_ChuaGanRepoSitory_Impl_Dong;
import vn.teca.scopio.base.service.giaoDich.PhongDatServices;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    @Autowired
    private PhongRepository phongRepository;
    @Autowired
    DetailPhongGan_ChuaGanRepoSitory_Impl_Dong detailPhongGanChuaGanRepoSitoryImplDong;
    @Override
    public PhongDat save(PhongDatDto dto) {
        String trangThai = "WAIT FOR CHECKIN";
        Optional<PhongDat> phongDatOptional = phongDatRepository.findById(dto.getIdPhongDat());
        DonDat donDat = donDatRepository.findById(phongDatOptional.get().getDonDatIdDonDat().getId()).orElse(null);
//        PhongDat phongDat = phongDatRepository.findById(dto.getIdPhongDat()).orElse(null);
//        if (phongDatOptional.isPresent()) {

                PhongDat o = phongDatOptional.get();
                o.setPhongIdPhong(dto.getPhongIdPhong());

                o.setThoiGianVao(donDat.getThoiGianVao());
                o.setThoiGianRa(donDat.getThoiGianRa());
//                o.setSoTienPhong(dto.getSoTienPhong());
                o.setTrangThai(trangThai);
                return  phongDatRepository.save(o);


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
                    item.setLoaiPhongDatIdLoaiPhongDat(loaiPhongDat.getId());
                    item.setDonDatIdDonDat(donDat);
                    item.setTrangThai("Processing");
                    phongDat.add(item);

                }
                phongDatRepository.saveAll(phongDat);
            }
        }
        // lay phong dat theo don dat
        List<PhongDat> phongDat1 = phongDatRepository.findPhongDatByIdDonDat(idDonDat);
        for (PhongDat pd : phongDat1) {
            LoaiPhongDat loaiPhongDat = loaiPhongDatRepository.findById(pd.getLoaiPhongDatIdLoaiPhongDat()).orElse(null);
//            LoaiPhong lp = loaiPhongRepository.findById(loaiPhongDat.getLoaiPhongIdLoaiPhong())
            LoadDonDatDto dto = new LoadDonDatDto();
            dto.setIdDonDat(idDonDat);
            dto.setIdLoaiPhong(pd.getLoaiPhongDatIdLoaiPhongDat());
            dto.setTenLoaiPhong(loaiPhongDat.getLoaiPhongIdLoaiPhong().getTenLoaiPhong());
            dto.setIdPhongDat(pd.getId());
            // Cần lấy thông tin của phòng từ PhongDat và thiết lập cho LoadDonDatDto

            dto.setIdPhong(Objects.nonNull(pd.getPhongIdPhong()) ? pd.getPhongIdPhong().getId() : null);
            dto.setTenPhong(Objects.nonNull(pd.getPhongIdPhong()) ? pd.getPhongIdPhong().getSoPhong() : null);
            result.add(dto);
        }
        return result;
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
    public void update(PhongDatDto dto) {
//        PhongDat phongDat = new PhongDat();
//        phongDat.setId(id);
        Optional<PhongDat> optional = phongDatRepository.findById(dto.getIdPhongDat());
        DetailThongTinDonDatDTO_Dong detail = detailPhongGanChuaGanRepoSitoryImplDong.detailPhongDat(dto.getIdPhongDat());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        LocalDate thoiGianVao = LocalDate.parse(dto.getThoiGianVao().toString(), formatter);
        LocalDate thoiGianRa = LocalDate.parse(dto.getThoiGianRa().toString(), formatter);
        long soNgayChenhLech = ChronoUnit.DAYS.between(thoiGianVao, thoiGianRa);

        BigDecimal giaTien = detail.getTienLoaiPhong().multiply(BigDecimal.valueOf(soNgayChenhLech));
//        detail.setSoTienPhong(giaTien);
        optional.map(o -> {
            o.setThoiGianRa(dto.getThoiGianRa());
            o.setSoTienPhong(giaTien);
//            o.setTrangThai("Checkin");
            return phongDatRepository.save(o);
        }).orElse(null);
    }

    @Override
    public void checkout(Integer id) {
//        PhongDat phongDat = new PhongDat();
//        phongDat.setId(id);
        Optional<PhongDat> optional = phongDatRepository.findById(id);
//        PhongDat pd = phongDatRepository.findById(id).orElse(null);
        optional.map(o -> {
            o.setThoiGianRa(LocalDateTime.now());
            o.setTrangThai("Checkout");
            return phongDatRepository.save(o);
        }).orElse(null);
//        checkoutDonDat(optional.get().getDonDatIdDonDat().getId());

    }

    @Override
    public Integer countCheckout(Integer id) {
        List<PhongDat> phongDat = phongDatRepository.findPhongDatByIdDonDat(id);
        int count = 0;
        for (PhongDat pd : phongDat) {
            if (pd != null && pd.getTrangThai() != null && pd.getTrangThai().equalsIgnoreCase("Checkout")) {
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
