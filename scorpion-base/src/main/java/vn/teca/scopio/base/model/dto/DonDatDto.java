package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import vn.teca.scopio.base.model.HinhThucDat;
import vn.teca.scopio.base.model.LoaiPhong;
import vn.teca.scopio.base.model.ThongTinKhachDat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link vn.teca.scopio.base.model.DonDat}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DonDatDto implements Serializable {

    private ThongTinKhachDat thongTinKhachDatIdKhachDat;
    private HinhThucDat hinhThucDatIdHinhThucDat;
    private LocalDateTime thoiGianVao;
    private LocalDateTime thoiGianRa;
    private BigDecimal tongTien;
    private String trangThai;
//    private Integer soLuong;
//    private LoaiPhong loaiPhongIdLoaiPhong;
    private List<LoaiPhongDatDto> loaiPhongDatDto;
}