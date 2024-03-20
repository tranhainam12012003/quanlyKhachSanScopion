package vn.teca.scopio.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import vn.teca.scopio.base.util.DateDeserializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "don_dat")
public class DonDat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_don_dat", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "thong_tin_khach_dat_id_khach_dat", nullable = false)
    private ThongTinKhachDat thongTinKhachDatIdKhachDat;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hinh_thuc_dat_id_hinh_thuc_dat", nullable = false)
    private HinhThucDat hinhThucDatIdHinhThucDat;

    @NotNull
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "thoi_gian_vao", nullable = false)
    private Instant thoiGianVao;

    @NotNull
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "thoi_gian_ra", nullable = false)
    private Instant thoiGianRa;

    @NotNull
    @Column(name = "tong_tien", nullable = false, precision = 30, scale = 2)
    private BigDecimal tongTien;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "trang_thai", nullable = false, length = 50)
    private String trangThai;

//    @OneToMany(mappedBy = "donDatIdDonDat")
//    private Set<LoaiPhongDat> loaiPhongDats = new LinkedHashSet<>();
//
//    @OneToMany(mappedBy = "donDatIdDonDat")
//    private Set<PhongDat> phongDats = new LinkedHashSet<>();

}