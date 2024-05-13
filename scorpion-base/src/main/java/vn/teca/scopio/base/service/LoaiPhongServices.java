package vn.teca.scopio.base.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.LoaiPhong;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.model.TienIch;
import vn.teca.scopio.base.model.TienIchLoaiPhong;
import vn.teca.scopio.base.model.dto.LoaiPhongDTOAdd;
import vn.teca.scopio.base.model.dto.LoaiPhongDto;
import vn.teca.scopio.base.model.dto.LoaiPhongDtoDetail_dong;
import vn.teca.scopio.base.repository.HinhAnhRepository;
import vn.teca.scopio.base.repository.LoaiPhongRepository;
import vn.teca.scopio.base.repository.PhongRepository;
import vn.teca.scopio.base.repository.TienIchLoaiPhongRepository;
import vn.teca.scopio.base.repository.TienIchRepository;
import vn.teca.scopio.base.repository.custom.impl.DetailLoaiPhongCustomRepoSitory_dong;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class LoaiPhongServices {
    @Autowired
    LoaiPhongRepository loaiPhongRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    TienIchLoaiPhongRepository tienIchLoaiPhongRepository;

    @Autowired
    TienIchRepository tienIchRepository;

    @Autowired
    TienIchServices tienIchServices;
    @Autowired
    PhongRepository phongRepository;

    @Autowired
    DetailLoaiPhongCustomRepoSitory_dong detailLoaiPhongCustomRepoSitoryDong;
    public List<LoaiPhong> getall() {
        return loaiPhongRepository.findAll();
    }

    //    public List<LoaiPhongDto>getAllHinhAnh(){return loaiPhongRepository.findAllAndHinhAnh();};
    public LoaiPhong  delete(Integer id) {
        Optional<LoaiPhong> optional = loaiPhongRepository.findById(id);
        return optional.map(o -> {
            loaiPhongRepository.delete(o);
            return o;
        }).orElse(null);
    }

    public LoaiPhong findbyId(Integer id) {
        Optional<LoaiPhong> optional = loaiPhongRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

//    public LoaiPhongDTOAdd mapToObject(Object[] result) {
//        LoaiPhongDTOAdd loaiPhongDTOAdd = new LoaiPhongDTOAdd();
//        loaiPhongDTOAdd.setTenLoaiPhong((String) result[0]);
//        loaiPhongDTOAdd.setDienTich((String) result[1]);
//        loaiPhongDTOAdd.setGiaTien((BigDecimal) result[2]);
//        loaiPhongDTOAdd.setHuongNhin((String) result[3]);
//        loaiPhongDTOAdd.setMoTa((String) result[4]);
//        loaiPhongDTOAdd.setSoNguoi((Integer) result[5]);
//        Phong phong = phongRepository.findById((Integer) result[6]).get();
//        loaiPhongDTOAdd.setPhongidPhong(phong);
//        TienIch tienIch = tienIchRepository.findById((Integer) result[7]).get();
//        loaiPhongDTOAdd.setTienichidtienich(tienIch);
//        return loaiPhongDTOAdd;
//
//    }

    public LoaiPhongDTOAdd detaiiByIdLoaiPhong(Integer id) {
        return detailLoaiPhongCustomRepoSitoryDong.detailLoaiPhong(id);
    }

    public void add(LoaiPhongDTOAdd loaiPhongDTOAdd) {
        LoaiPhong loaiPhong = new LoaiPhong();
        loaiPhong.setTenLoaiPhong(loaiPhongDTOAdd.getTenLoaiPhong());
        loaiPhong.setHuongNhin(loaiPhongDTOAdd.getHuongNhin());
        loaiPhong.setSoLuongNguoiO(loaiPhongDTOAdd.getSoNguoi());
        loaiPhong.setDienTich(loaiPhongDTOAdd.getDienTich());
        loaiPhong.setGiaTien(loaiPhongDTOAdd.getGiaTien());
        loaiPhong.setMoTa(loaiPhongDTOAdd.getMoTa());
        loaiPhongRepository.save(loaiPhong);


//        List<Phong> list = loaiPhongDTOAdd.getPhongidPhong();
//        for (Phong phong : list) {
//            // Lấy ID của phòng từ đối tượng phòng
//            Integer idPhong = phong.getId();
//
//            // Lấy thông tin phòng từ Repository bằng ID
//            Phong existingPhong = phongRepository.getById(idPhong);
//
//            // Kiểm tra xem phòng có tồn tại hay không
//            if (existingPhong != null) {
//                existingPhong.setLoaiPhongIdLoaiPhong(loaiPhong); // Cập nhật thông tin loại phòng cho phòng
//
//                phongRepository.save(existingPhong); // Lưu phòng đã cập nhật vào cơ sở dữ liệu
//            } else {
//                // Xử lý khi không tìm thấy phòng
//                // Ví dụ: ghi log hoặc thông báo lỗi
//                System.out.println("Không tìm thấy phòng với ID: " + idPhong);
//            }
//
//        }
        // Lấy danh sách tiện ích từ DTO
        // Lấy danh sách tiện ích từ DTO
//        List<TienIch> listTienIch = loaiPhongDTOAdd.getTienichidtienich();
//
//// Lặp qua từng tiện ích
//        for (TienIch tienIch : listTienIch) {
//            // Tạo một đối tượng mới cho mỗi tiện ích loại phòng
//            TienIchLoaiPhong tienIchLoaiPhong = new TienIchLoaiPhong();
//            tienIchLoaiPhong.setLoaiPhong(loaiPhong);
//            tienIchLoaiPhong.setTienIchIdTienIch(tienIch);
//
//            // Lưu đối tượng mới vào cơ sở dữ liệu
//            tienIchLoaiPhongRepository.save(tienIchLoaiPhong);
//        }


    }
    @Transactional
    public void update(LoaiPhongDTOAdd loaiPhongDTOAdd) {
        // Cập nhật thông tin loại phòng
        LoaiPhong loaiPhong = loaiPhongRepository.getById(loaiPhongDTOAdd.getIdLoaiPhong());
        loaiPhong.setTenLoaiPhong(loaiPhongDTOAdd.getTenLoaiPhong());
        loaiPhong.setDienTich(loaiPhongDTOAdd.getDienTich());
        loaiPhong.setGiaTien(loaiPhongDTOAdd.getGiaTien());
        loaiPhong.setMoTa(loaiPhongDTOAdd.getMoTa());
        loaiPhong.setHuongNhin(loaiPhongDTOAdd.getHuongNhin());
        loaiPhong.setSoLuongNguoiO(loaiPhongDTOAdd.getSoNguoi());
        loaiPhongRepository.save(loaiPhong);

//        phongRepository.deleteLoaiPhong(id);
//        // Cập nhật thông tin loại phòng cho từng phòng
//        List<Phong> list = loaiPhongDTOAdd.getPhongidPhong();
//        for (Phong phong : list) {
//            // Lấy ID của phòng từ đối tượng phòng
//            Integer idPhong = phong.getId();
//
//            // Lấy thông tin phòng từ Repository bằng ID
//            Phong existingPhong = phongRepository.getById(idPhong);
//
//            // Kiểm tra xem phòng có tồn tại hay không
//            if (existingPhong != null) {
//                existingPhong.setLoaiPhongIdLoaiPhong(loaiPhong); // Cập nhật thông tin loại phòng cho phòng
//
//                phongRepository.save(existingPhong); // Lưu phòng đã cập nhật vào cơ sở dữ liệu
//            } else {
//                // Xử lý khi không tìm thấy phòng
//                // Ví dụ: ghi log hoặc thông báo lỗi
//                System.out.println("Không tìm thấy phòng với ID: " + idPhong);
//            }
//
//        }



        // Thêm mới hoặc cập nhật danh sách tiện ích cho loại phòng
//        tienIchLoaiPhongRepository.deleteTienIchLoaiPhong(loaiPhongDTOAdd.getIdLoaiPhong());
//
//// Lặp qua danh sách tiện ích từ DTO và thêm mới các bản ghi tiện ích loại phòng
//        List<TienIch> listTienIch = loaiPhongDTOAdd.getTienichidtienich();
//        for (TienIch tienIch : listTienIch) {
//            // Tạo mới đối tượng TienIchLoaiPhong cho mỗi tiện ích
//            TienIchLoaiPhong tienIchLoaiPhong = new TienIchLoaiPhong();
//            tienIchLoaiPhong.setLoaiPhong(loaiPhong); // Sử dụng ID của loại phòng cần cập nhật
//            tienIchLoaiPhong.setTienIchIdTienIch(tienIch); // Sử dụng ID của tiện ích từ DTO
//
//            tienIchLoaiPhongRepository.save(tienIchLoaiPhong);
//        }
    }

    public List<LoaiPhong> timthemten(String name) {
        return loaiPhongRepository.timKiemTheoTenLoaiPhong(name);

    }

    public List<LoaiPhong> timTheoGia(BigDecimal gia) {
        return loaiPhongRepository.findByGiaTienLessThanEqualOrderByGiaTienDesc(gia);
    }

    public List<LoaiPhong> timTheoSoNguoi(Integer sl) {
        return loaiPhongRepository.findBySoLuongNguoiOLessThanEqualOrderBySoLuongNguoiODesc(sl);
    }

    public List<LoaiPhongDto> findAllHinhAnh() {
        List<LoaiPhongDto> result = loaiPhongRepository.findAllHinhAnh();
//        List<LoaiPhongDto> newList = result;
        if (result != null) {
//            newList = result;
            result.forEach(p -> {
                p = getMoreInfro(p);
            });
//            return result;
        }
        return result;
    }
    public List<LoaiPhongDto> searchLoaiPhongTrong(LocalDateTime thoiGianVao, LocalDateTime thoiGianRa){
        List<LoaiPhongDto> result = loaiPhongRepository.searchLoaiPhongTrong(thoiGianVao,thoiGianRa);
        if (result!=null){
            result.forEach(p ->{
                p = getMoreInfro(p);
            });
        }
        return result;
//        return loaiPhongRepository.searchLoaiPhongTrong(thoiGianVao,thoiGianRa);
    }

    public LoaiPhongDto getMoreInfro(LoaiPhongDto dto) {
        // set hinh anh
        try {
            dto.setHinhAnh(hinhAnhRepository.findByIdLoaiPhong(dto.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            dto.setHinhAnh(null);
        }
        // set tien ich
        try {
//            List<TienIch> tienI =
            dto.setTienTienIch(tienIchRepository.findByIdLoaiPhong(dto.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            dto.setTienTienIch(null);
        }
        return dto;
    }

}
