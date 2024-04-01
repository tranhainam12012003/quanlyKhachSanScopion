package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.apache.tomcat.jni.Local;
import org.apache.tomcat.jni.Time;
import vn.teca.scopio.base.model.DichVuDat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class HoaDonResponseDto {
    private Integer idPhongDat;
    private String loaiPhong;
    private String tenPhong;
    private Timestamp thoiGianVao;
    private Timestamp thoiGianRa;
    private BigDecimal tienPhong;
    private List<DichVuDatDto> dichVuDat;
}
