package com.cos.photogramstart.domain.user;

import com.cos.photogramstart.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 번호 증가 전략이 db를 따라간다.
    private int id;

    @Column(unique = true)
    private String username;
    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String name;
    private String website; // 웹 사이트
    private String bio; // 자기소개
    @Column(nullable=false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; // 사진
    private String role;    // 권한

    // 나는 연관관계의 주인이 아니다. 그러므로 테이블에 컬럼을 만들지마
    // User Select할때 해당 User id로 등록된 images를 다 가져와
    // Lasy = User를 Select할 때 해당 User id로 등록된 image들을 바로 가져오진 마 - 대신 getImages할 때 가져와
    // Eager = User를 Select할 때 해당 User id로 등록된 image들을 바로 가져와
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Image> images; // 양방향 매핑

    private LocalDateTime createDate;

    @PrePersist // db에 insert 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
