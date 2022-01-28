package com.hs.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

// ORM -> Java 등의 언어에서 Object -> 테이블로 매핑해주는 기술

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스 안의 내용들이 MySQL에서 테이블로 생성
// @DynamicInsert // insert 할 때 null인 필드 제외
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 30)
    private String username; // 아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // DB는 Role Type이라는게 없다
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰는게 좋다 // ADMIN, USER

    @CreationTimestamp // 시간이 자동 입력
    private Timestamp createDate;
}
