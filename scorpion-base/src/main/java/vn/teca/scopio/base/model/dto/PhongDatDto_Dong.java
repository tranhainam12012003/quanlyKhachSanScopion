package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhongDatDto_Dong {
    private String tenKhach;
    private String tenLoaiPhong;
    private BigDecimal soTienPhong;
    private Timestamp thoiGianVao;
    private Timestamp thoiGianRa;
    private String soPhong;

}
