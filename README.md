# SpringBoot

# 2장 MVC 패턴 이해와 실습

## MVC패턴 활용해 뷰 템플릿 페이지 만들기

![스크린샷 2024-01-03 210123](https://github.com/suuu0719/springboot-study/assets/118423039/d821dee1-63b4-4c4c-8562-4b96671845ac)

### mustache(뷰)

뷰템플릿을 만드는 도구=뷰 템플릿 엔진

src > main > resources > templates에 머스테치 파일 저장하면 스프링부트에서 자동으로 로딩

제일 윗줄에 doc 입력 후 tab키 누르면 기본 html코드 자동으로 작성됨

이 페이지를 웹에서 보려면 컨트롤러, 모델 이용해야함

### 컨트롤러

src > main > java디렉터리의 기본 패키지 안에 컨트롤러 패키지로 생성

기본으로 입력된 패키지명 뒤에 controller  추가, 해당 패키지 안에 ###Controller Class 생성

이 클래스가 컨트롤러임을 선언하는 `@Controller` 어노테이션 작성, 뷰 반환할 메서드 생성 후 return문 안에 mustache 페이지 반환

Controller Class안에 `@GetMapping` 으로 URL주소 반환

### 모델 추가

컨트롤러 메서드의 매개변수로 받아옴

뷰템플릿에 변수 삽입 `{{변수명}}` 

뷰 반환하는 메서드에 Model 타입의 model 매개변수 추가

`model.addAttribute("변수명", "변숫값")`  : 모델에서 변수를 등록하는 메서드

### MVC 패턴 실습 요약

![image](https://github.com/suuu0719/springboot-study/assets/118423039/9ac8a6d2-5d08-47ae-af5e-9e756e9ff7c2)

# 3장

## 3.1 폼데이터란

`폼데이터`: html 요소인 <form>태그에 실려 전송되는 데이터

`<form> 태그` : 웹프라우저에서 서버로 데이터를 전송할 때 사용, 데이터를 전송할때 어디로, 어떻게 보낼지 등을 적어서 보냄
<form>태그에 실어 보낸 데이터는 서버의 컨트롤러가 객체에 담아서 받음

`DTO`: data transfer object, <form>태그에 실어보낸 데이터를 담아 받는 서버 컨트롤러의 객체, DTO로 받은 데이터는 최종적으로 데이터페이스에 저장됨

## 3.2 폼데이터 DTO로 받기

### < form>태그 속성

- action: 어디로 보낼지에 관한 정보, URL 연결 주소. ex) `action="/articles/create"` 해당 페이지로 폼데이터를 보낸다는 의미
- method: 어떻게 보낼지에 관한 정보, 속성값으로 get, post 2가지 설정 가능.

### 폼데이터 받기

뷰페이지에서 폼데이터를 post방식으로 전송하므로 컨트롤러에서 받을때도 `@PostMapping()`으로 받음.

### DTO 만들기

프로젝트 파일에서 new→package 생성 후 dto 패키지 생성 (컨트롤러와 같은 레벨)

새로운 자바 클래스 생성 하면 해당 자바파일이 폼 데이터를 받아올 그릇, DTO가 됨.

입력받는 창만큼의 필드 갯수 필요. 생성자와 toString 메서드 추가

### 폼데이터 DTO에 담기

DTO클래스를 컨트롤러 메서드의 매개변수로 받아옴, form 객체를 매개변수로 선언

### 입력폼과 DTO 필드 연결

mustache 입력폼에 필드명 지정하면 해당 입력폼이 DTO의 필드와 연결됨

### 요약

1. 뷰페이지 만들기 (form action, method 지정)
2. 컨트롤러 만들기 (PostMapping으로 URL 주소 연결)
3. DTO 만들기
4. 컨트롤러에서 폼 데이터 전송받아 DTO 객체에 담기

## 3.3 DTO를 데이터 베이스에 저장하기

`데이터베이스`: 데이터를 관리하는 창고, DB의 모든 데이터는 행과 열로 구성된 테이블에 저장해 관리

`JPA`: 자바 언어로 DB에 명령을 내리는 도구, 데이터를 객체 지향적으로 관리할 수 있게 해줌

`엔티티`: 자바 객체가 DB를 이해할 수 있게 만든 것, 이를 기반으로 테이블이 만들어짐

`리파지터리`: 엔티티가 DB속 테이블에 저장 및 관리될 수 있게 하는 인터페이스

폼 데이터를 DB에 저장하려면
1. DTO를 엔티티로 변환하기
2. 리파지터리를 이용해 엔티티를 DB에 저장하기

### DTO를 엔티티로 변환하기
`Article article = form.toEntity();` // form 객체의 toEntity() 메서드 호출, 그 반환 값을 Article 타입의 article 엔티티에 저장

`Article 클래스 만들기`: 프로젝트에 `entity` 패키지 만든 후 클래스 생성
1. `@Entity` 어노테이션 붙이기
2. `@Column` 어노테이션 붙이고 필드 생성
3. 대푯값 `@Id`로 선언 후 `@GeneratedValue`로 대푯값 자동 생성 -> 대푯값으로 중복된 데이터 있더라도 구분 가능
4. 생성자와 toString() 메서드 생성

`toEntity()`메서드 생성: DTO인 form 객체를 엔티티 객체로 변환하는 역할
1. `ArticleForm` (DTO 클래스)에 toEntity() 메서드 추가
2. DTO 객체 엔티티로 반환, `return new Article(null, title, content);` //id정보 제외한 ArticleForm 객체의 전달값 입력

### 리파지터리로 엔티티를 DB에 저장하기
1. 컨트롤러 필드 선언부에 리파지터리 객체 선언
2. `Article saved = articleRepository.save();` // save() 메서드 호출해 article 엔티티 저장. save() 메서드는 저장된 엔티티를 반환하여 Article 타입의 saved라는 객체에 받아옴 

`리파지터리 만들기`: 인터페이스 생성

1. 프로젝트에 `repository` 패키지 생성, `ArticleRepository` 인터페이스 생성
2. JPA에서 제공하는 인터페이스 활용. 리파지터리 이름 뒤에`extend CrudRepository<T, ID>` 선택, <> 안에 2개의 제네릭 요소를 받음
   1. `Article`: 관리 대상 엔티티의 클래스 타입, 여기서는 Article
   2. `Long`: 관리 대상 엔티티의 대푯값 타입. id가 대푯값이므로 Long타입 입력

`객체 주입하기`: 스프링 부트는 객체를 만들지 않아도 미리 생성해놓은 객체 가져다 연결해서 사용 가능

**의존성 주입(DI)**: 컨트롤러 클래스에 `@AutoWired` 어노테이션 붙이면 스프링부트가 만들어놓은 객체 가져와 주입
