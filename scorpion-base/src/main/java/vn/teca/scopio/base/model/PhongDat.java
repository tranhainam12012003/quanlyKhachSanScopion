package vn.teca.scopio.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "phong_dat")
public class PhongDat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phong_dat", nullable = false)
    private Integer id;

    @Null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phong_id_phong")
    private Phong phongIdPhong;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "don_dat_id_don_dat", nullable = false)
    private DonDat donDatIdDonDat;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loai_phong_dat_id_loai_phong_dat", nullable = false)
    private LoaiPhongDat loaiPhongDatIdLoaiPhongDat;

    @Column(name = "thoi_gian_vao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private LocalDateTime thoiGianVao;

    @Column(name = "thoi_gian_ra")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private LocalDateTime thoiGianRa;

    @Column(name = "so_tien_phong", precision = 30, scale = 2)
    private BigDecimal soTienPhong;

    @Size(max = 50)
    @Column(name = "trang_thai", length = 50)
    private String trangThai;

}