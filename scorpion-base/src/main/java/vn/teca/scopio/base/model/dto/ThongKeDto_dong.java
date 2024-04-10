package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThongKeDto_dong {
    private String tenLoaiPhong;
    private int tongSoPhong;
    private int soLuongPhongDaDung;
    private int soLuongPhongTrong;
    private Timestamp thoiGian;
}
