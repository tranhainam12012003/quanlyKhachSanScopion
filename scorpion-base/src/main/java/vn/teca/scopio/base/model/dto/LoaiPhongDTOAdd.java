package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.model.TienIch;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LoaiPhongDTOAdd {
    private Integer idLoaiPhong;
    private String tenLoaiPhong;
    private String huongNhin;
    private int soNguoi;
    private String dienTich;
    private BigDecimal giaTien;
    private String moTa;
    private List<Phong> phongidPhong;
//    private List<TienIch> tienichidtienich;

}
