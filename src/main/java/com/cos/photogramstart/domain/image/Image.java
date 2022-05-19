package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String caption; // 오늘 나 너무 피곤해!
    private String postImageUrl;    // 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 - DB에 저장된 경로를 insert

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    // Jpa 오브젝트 콘솔 출력 시 무한 참조 방지를 위해 toString 새로 만듬
//    @Override
//    public String toString() {
//        return "Image{" +
//                "id=" + id +
//                ", caption='" + caption + '\'' +
//                ", postImageUrl='" + postImageUrl + '\'' +
//
//                ", createDate=" + createDate +
//                '}';
//    }
}
