package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity // DB가 해당 객체를 인식 가능 (해당 클래스로 테이블 만든다)
@Getter // GetId() 메소드 정의 대신 롬복 사용
public class Article {

    @Id //엔티티의 대푯값
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성 어노테이션
    private Long id;

    @Column //db에서 인식할 수 있도록 함, 두 필드가 DB테이블의 각 열과 연결됨
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if (article.title!=null){
            this.title = article.title;
        }
        if (article.content != null){
            this.content = article.content;
        }
    }


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
