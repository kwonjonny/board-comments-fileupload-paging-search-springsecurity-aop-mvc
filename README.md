![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=300&section=header&text=Kwon's%20Study%20Repo&fontSize=60&animation=fadeIn&fontAlignY=38&desc=Whoever%20knocks%20persistently,%20ends%20by%20entering.&descAlignY=51&descAlign=62)
<p align='center'>
  <a href="#demo">
<body>
  <h2>기술 스택</h2>
  <p>
    <img src="https://img.shields.io/badge/MyBatis-FAAF15?style=for-the-badge&logo=MyBatis&logoColor=white" alt="MyBatis">
    <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white" alt="Spring Boot">
    <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white" alt="Spring">
    <img src="https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white" alt="Spring Security">
    <img src="https://img.shields.io/badge/NGINX-009639?style=for-the-badge&logo=NGINX&logoColor=white" alt="NGINX">
    <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white" alt="Thymeleaf">
    <img src="https://img.shields.io/badge/Kakao-FFCD00?style=for-the-badge&logo=Kakao&logoColor=black" alt="Kakao Social Login">
    <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white" alt="Java">
    <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=black" alt="JavaScript">
    <img src="https://img.shields.io/badge/Ajax-005C5C?style=for-the-badge&logo=Ajax&logoColor=white" alt="Ajax">
  </p>
</body>
</html>




# 개발 상태 : [개발 완료]
# [게시판 CRUD, 조회수, 검색, 댓글대댓글, 파일업로드 개발 완료 , Spring Security(kakao social login) 개발 완료, 좋아요 개발 완료, 공지사항 개발 완료]

# board-comments-fileupload-paging-search-mvc
- 프로젝트 유형: Board 게시판 토이 프로젝트
- 목표: JavaScript, Thymeleaf, Spring Boot 를 활용하여 페이지네이션, 검색 기능이 포함된 Board 애플리케이션 개발
- 목표: 개발의 순서 정립 DataBase , Index 설계 , Query 속도 향상 & Query 문법 정립 

## Spring Boot 프로젝트 설정

### 빌드 플러그인
- `java`: Java 플러그인을 사용하여 프로젝트에 Java 컴파일 기능을 추가합니다.
- `org.springframework.boot`: Spring Boot 기능을 프로젝트에 추가합니다. 버전은 3.1.1으로 설정됩니다.
- `io.spring.dependency-management`: Spring의 의존성 관리 기능을 추가합니다. 버전은 1.1.0입니다.

### 프로젝트 정보
- `group`: 프로젝트 그룹 ID를 'board.file'로 설정합니다.
- `version`: 프로젝트 버전을 '0.0.1-SNAPSHOT'으로 설정합니다.
- `sourceCompatibility`: 프로젝트 Java 버전을 17로 설정합니다.

## 시작하기(Getting Start)

### 사전 요구 사항
- Java 17
- MySQL
- 웹 브라우저

### 데이터베이스 설정
프로젝트를 실행하기 전에 MySQL 데이터베이스를 설정하고 `application.properties` 파일에서 데이터베이스 연결 속성을 구성하세요.

### 빌드 및 실행 방법
1. 이 저장소를 복제하거나 다운로드합니다: `git clone [repository_url]`
2. 프로젝트를 빌드합니다: `./gradlew build`
3. 애플리케이션을 실행합니다: `./gradlew bootRun`
4. 웹 브라우저에서 애플리케이션에 접속합니다: `http://localhost:8084/board/list`

## 부트스트랩 사용
이 프로젝트는 부트스트랩 템플릿을 사용하여 사용자 인터페이스를 구성합니다. 템플릿에 대한 자세한 정보는 [AdminLTE](https://adminlte.io/)를 참조하세요.

### 주요 기능
- Board 항목 생성, 조회, 수정, 삭제
- 페이지네이션 지원
- Board 항목 title, content, writer 검색 지원 
- Board 항목 필터링 및 정렬 기능
- 페이지네이션 지원 

- Reply 항목 생성, 조회, 수정, 삭제
- 페이지네이션 지원

- File 항목 생성, 조회, 수정, 삭제
- File 항목 생성 수정 시 Thumnail 생성
- Eningx 를 통한 Ajax 통신

- 공지사항 항목 생성, 조회, 수정, 삭제
- 공지사항 Board List 상단 고정 기능
- 공지사항 항목 title, content, writer 검색 지원
- 공지사항 항목 필터링 및 정렬 기능
- 페이지네이션 지원 

- 좋아요 기능 회원을 통한 생성, 삭제, 좋아요 수 Count

- 조회수 기능 Cookie 를 통한 생성 

- Bootstrap을 이용한 반응형 웹 디자인

## 개발자 정보
- 이름: [권성준]
- 이메일: [thistrik@gmail.com]
- 이메일: [thistrik@naver.com]

## 피드백 기여 및 지원 기타 문의 
- 프로젝트에 대한 의견과 피드백은 언제나 환영합니다! 만약 프로젝트에 대한 개선 사항이나 버그 제보, 그 외 다른 의견이 있으시면, 언제든지 연락해주시기 바랍니다.

## Database_Diagram
![Database Diagram](./35D8FAE1-7656-4053-94D3-643DCC541D21.jpeg)

# Database Indexing
다음은 데이터베이스 내 각 테이블에 대한 인덱싱 방법을 안내합니다. 

## tbl_board
`tbl_board` 테이블에 대해 `title`, `content`, `writer` 필드에 인덱스를 추가합니다:
```sql
ALTER TABLE tbl_board ADD INDEX idx_title (title);
ALTER TABLE tbl_board ADD INDEX idx_content (content);
ALTER TABLE tbl_board ADD INDEX idx_writer (writer);
```

## tbl_notice
`tbl_notice` 테이블에 대해 `title`, `content`, `writer` 필드에 인덱스를 추가합니다:
```sql
ALTER TABLE tbl_notice ADD INDEX idx_title (title);
ALTER TABLE tbl_notice ADD INDEX idx_content (content);
ALTER TABLE tbl_notice ADD INDEX idx_writer (writer);
```

## tbl_reply
`tbl_reply` 테이블에 대해 `tno`, `replyer` 필드에 인덱스를 추가합니다:
```sql
ALTER TABLE tbl_reply ADD INDEX idx_tno (tno);
ALTER TABLE tbl_reply ADD INDEX idx_replyer (replyer);
```

## tbl_member & tbl_member_role
`tbl_member` 및 `tbl_member_role` 테이블에 대해 email 필드에 인덱스를 추가합니다:
```sql
ALTER TABLE tbl_member ADD INDEX idx_email (email);
ALTER TABLE tbl_member_role ADD INDEX idx_email (email);
```

## tbl_like, tbl_board_img, tbl_like_notice, tbl_notice_img
`tbl_like`, `tbl_board_img`, `tbl_like_notice`, `tbl_notice_img` 테이블에 대해 외래키 필드에 인덱스를 추가합니다:
```sql
ALTER TABLE tbl_like ADD INDEX idx_tno (tno);
ALTER TABLE tbl_like ADD INDEX idx_email (email);

ALTER TABLE tbl_board_img ADD INDEX idx_tno (tno);

ALTER TABLE tbl_like_notice ADD INDEX idx_nno (nno);
ALTER TABLE tbl_like_notice ADD INDEX idx_email (email);

ALTER TABLE tbl_notice_img ADD INDEX idx_nno (nno);
```
이렇게 README 파일에 작성하면, 각 테이블에 대한 인덱스 추가 방법을 쉽게 파악하고 참조할 수 있습니다. 이 문서를 필요에 따라 수정하거나 확장해서 사용하시면 됩니다.


SQL 스키마:
```sql

CREATE TABLE tbl_board (
	tno int AUTO_INCREMENT PRIMARY KEY,
	title varchar(500) NOT NULL,
	content varchar(700) NOT NULL,
	writer varchar(100) NOT NULL,
	registDate TIMESTAMP DEFAULT NOW(),
	updateDate TIMESTAMP DEFAULT NOW(),
	replyCnt INT DEFAULT 0,
	viewCnt INT DEFAULT 0
)
;

CREATE TABLE tbl_like (
    tno INT NOT NULL,
    email VARCHAR(100) NOT NULL,
    registDate TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (tno, email),
    FOREIGN KEY (tno) REFERENCES tbl_board(tno) ON DELETE CASCADE,
    FOREIGN KEY (email) REFERENCES tbl_member(email) ON DELETE CASCADE
)
;

CREATE TABLE tbl_board_img (
	uuid varchar(50) PRIMARY KEY,
	filename varchar(200) not null,
	tno int not null,
	ord int default 0,
	FOREIGN KEY (tno) REFERENCES tbl_board(tno) ON DELETE CASCADE
)
;

CREATE TABLE tbl_reply (
    rno INT AUTO_INCREMENT PRIMARY KEY,
    tno INT NOT NULL,
    reply VARCHAR(1000) NOT NULL,
    replyer VARCHAR(100) NOT NULL,
    replyDate TIMESTAMP DEFAULT NOW(),
    modifyDate TIMESTAMP DEFAULT NOW(),
    gno INT DEFAULT 0,
    isDeleted TINYINT DEFAULT 0,
    FOREIGN KEY (tno) REFERENCES tbl_board(tno) ON DELETE CASCADE
)
;

CREATE TABLE tbl_notice (
    nno INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(500) NOT NULL,
    content VARCHAR(700) NOT NULL,
    writer VARCHAR(100) NOT NULL,
    registDate TIMESTAMP DEFAULT NOW(),
    updateDate TIMESTAMP DEFAULT NOW(),
    viewCnt INT DEFAULT 0
)
;

CREATE TABLE tbl_like_notice (
    nno INT NOT NULL,
    email VARCHAR(100) NOT NULL,
    registDate TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (nno, email),
    FOREIGN KEY (nno) REFERENCES tbl_notice(nno) ON DELETE CASCADE,
    FOREIGN KEY (email) REFERENCES tbl_member(email) ON DELETE CASCADE
)
;

CREATE TABLE tbl_notice_img (
    uuid VARCHAR(50) NOT NULL PRIMARY KEY,
    filename VARCHAR(200) NOT NULL,
    nno INT NOT NULL,
    ord INT NOT NULL,
    FOREIGN KEY(nno) REFERENCES tbl_notice(nno) ON DELETE CASCADE
)
;

CREATE TABLE tbl_member (
	email varchar(100) PRIMARY KEY,
	mpw varchar(100) NOT NULL,
	mname varchar(100) NOT NULL
)
;

CREATE TABLE tbl_member_role (
	email varchar(100) not null,
	rolename varchar(50) not null
)
;

create table persistent_logins (
       username varchar(64) not null,
       series varchar(64) primary key,
       token varchar(64) not null,
       last_used timestamp not null
)
;

```
