package umc.study.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.study.domain.common.BaseEntity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @ColumnDefault("0.0")
    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", score=" + score +
                ", region=" + (region != null ? region.getName() : "N/A") + // region의 이름 출력
                '}';
    }

    // 양방향 관계 설정 메서드
    public void setRegion(Region region) {
        if (this.region != null && this.region.getStoreList() != null) {
            this.region.getStoreList().remove(this);
        }
        this.region = region;
        if (region != null && region.getStoreList() != null && !region.getStoreList().contains(this)) {
            region.getStoreList().add(this);
        }
    }

    public void addReview(Review review) {
        if (!this.reviewList.contains(review)) {
            this.reviewList.add(review);
            review.setStore(this); // 양방향 관계 설정
        }
    }

    public void removeReview(Review review) {
        if (this.reviewList.contains(review)) {
            this.reviewList.remove(review);
            review.setStore(null); // 관계 해제
        }
    }
}
