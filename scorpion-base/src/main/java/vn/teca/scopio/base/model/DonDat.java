package vn.teca.scopio.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import vn.teca.scopio.base.util.DateDeserializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "don_dat")
public class DonDat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_don_dat")
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "thong_tin_khach_dat_id_khach_dat")
    private ThongTinKhachDat thongTinKhachDatIdKhachDat;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "hinh_thuc_dat_id_hinh_thuc_dat")
    private HinhThucDat hinhThucDatIdHinhThucDat;

    @NotNull
//    @JsonDeserialize(using = DateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "thoi_gian_vao")
    private LocalDateTime thoiGianVao;

    @NotNull
//    @JsonDeserialize(using = DateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "thoi_gian_ra")
    private LocalDateTime thoiGianRa;

    @NotNull
    @Column(name = "tong_tien",precision = 30, scale = 2)
    private BigDecimal tongTien;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "trang_thai", length = 50)
    private String trangThai;

    @Column(name = "ghi_chu", length = 200)
    private String ghiChu;

//    @JsonIgnore
//    @OneToMany(mappedBy = "donDatIdDonDat")
//    private Set<LoaiPhongDat> loaiPhongDats = new LinkedHashSet<>();
//
//    @OneToMany(mappedBy = "donDatIdDonDat")
//    private Set<PhongDat> phongDats = new LinkedHashSet<>();

}