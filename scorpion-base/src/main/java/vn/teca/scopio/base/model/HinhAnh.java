package vn.teca.scopio.base.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hinh_anh")
public class HinhAnh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hinh_anh", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "loai_phong_Id_loai_phong", nullable = false)
    private LoaiPhong loaiPhongIdLoaiPhong;

    @NotNull
    @Column(name = "hinh_anh_loai_phong", nullable = false)
    private byte[] hinhAnhLoaiPhong;

}