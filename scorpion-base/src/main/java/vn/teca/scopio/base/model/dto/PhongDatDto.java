package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import vn.teca.scopio.base.model.DonDat;
import vn.teca.scopio.base.model.LoaiPhongDat;
import vn.teca.scopio.base.model.Phong;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class  PhongDatDto {
    private Integer idPhongDat;
    private Phong phongIdPhong;
    private LocalDateTime thoiGianVao;
    private LocalDateTime thoiGianRa;
    private BigDecimal soTienPhong;


}
