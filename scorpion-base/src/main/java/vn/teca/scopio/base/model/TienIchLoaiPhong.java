package vn.teca.scopio.base.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "tien_ich_loai_phong")
public class TienIchLoaiPhong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tien_ich_loai_phong")
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tien_ich_id_tien_ich")
    private TienIch tienIchIdTienIch;

    @ManyToOne
    @JoinColumn(name = "loai_phong_Id_loai_phong")
    private LoaiPhong loaiPhong;

}