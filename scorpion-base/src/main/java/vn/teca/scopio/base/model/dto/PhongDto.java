package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhongDto {
    private Integer idDonDat;
    private Integer idPhong;
    private String tenPhong;
    private Integer idLoaiPhong;
    private String tenLoaiPhong;
    private Integer idPhongDat;
    private Timestamp thoiGianVao;
    private Timestamp thoiGianRa;

}
