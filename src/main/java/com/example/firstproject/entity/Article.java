package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Getter // GetId() 메소드 정의 대신 롬복 사용
public class Article {
    @Id //엔티티의 대푯값
    @GeneratedValue //대푯값 자동으로 생성하게 함 (제목, 내용 같은 것이 있더라도 대푯값 id로 다른 글임을 구분 가능)
    private Long id;
    @Column //db에서 인식할 수 있도록 함, 두 필드가 DB테이블의 각 열과 연결됨
    private String title;
    @Column
    private String content;


    // Article 생성자 추가
//    public Article(Long id, String title, String content) {
//        Id = id;
//        this.title = title;
//        this.content = content;
//    }

    // toString 메서드 추가
//    @Override
//    public String toString() {
//        return "Article{" +
//                "Id=" + Id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
