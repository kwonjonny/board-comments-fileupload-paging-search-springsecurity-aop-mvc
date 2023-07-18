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
   <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white" alt="MySQL">
  </p>
</body>
</html>

# 개발 상태 : [개발 완료]
## 개발자 정보
- 이름: [권성준]
- 이메일: [thistrik@gmail.com]
- 이메일: [thistrik@naver.com]

## 피드백 기여 및 지원 기타 문의 
- 프로젝트에 대한 의견과 피드백은 언제나 환영합니다! 만약 프로젝트에 대한 개선 사항이나 버그 제보, 그 외 다른 의견이 있으시면, 언제든지 연락해주시기 바랍니다.

### 주요 기능
| 기능               | 설명                                                              |
|------------------|-----------------------------------------------------------------|
| **Board 기능**     | Board 항목의 생성, 조회, 수정, 삭제를 지원합니다. 게시물 검색, 필터링 및 정렬, 페이지네이션 기능이 있습니다. |
| **Reply 기능**     | Reply 항목의 생성, 조회, 수정, 삭제를 지원하며, 페이지네이션 기능이 있습니다. |
| **File 기능**      | File 항목의 생성, 조회, 수정, 삭제를 지원합니다. 생성/수정 시에는 썸네일이 생성되며, Ajax 통신은 NGINX를 통해 이루어집니다. |
| **공지사항 기능**  | 공지사항 항목의 생성, 조회, 수정, 삭제를 지원합니다. 공지사항 게시물 검색, 필터링 및 정렬, 페이지네이션 기능이 있으며, 게시물 리스트 상단에 공지사항을 고정할 수 있습니다. |
| **좋아요 기능**    | 회원을 통한 좋아요 생성, 삭제, 그리고 좋아요 수 카운트를 지원합니다. |
| **조회수 기능**    | 쿠키를 통해 조회수를 생성합니다.                                   |
| **웹 디자인**     | Bootstrap을 이용한 반응형 웹 디자인을 지원합니다.                      |

## 부트스트랩 사용
이 프로젝트는 부트스트랩 템플릿을 사용하여 사용자 인터페이스를 구성합니다. 템플릿에 대한 자세한 정보는 [AdminLTE](https://adminlte.io/)를 참조하세요.

## Database_Diagram
![Database Diagram](./35D8FAE1-7656-4053-94D3-643DCC541D21.jpeg)

# Database Indexing
다음은 데이터베이스 내 각 테이블에 대한 인덱싱 방법을 안내합니다. 
<div style="font-size: 0.2em;">

<h2>tbl_board</h2>
<p><code>tbl_board</code> 테이블에 대해 <code>title</code>, <code>content</code>, <code>writer</code> 필드에 인덱스를 추가합니다:</p>
<pre><code>
ALTER TABLE tbl_board ADD INDEX idx_title (title);
ALTER TABLE tbl_board ADD INDEX idx_content (content);
ALTER TABLE tbl_board ADD INDEX idx_writer (writer);
</code></pre>

<h2>tbl_notice</h2>
<p><code>tbl_notice</code> 테이블에 대해 <code>title</code>, <code>content</code>, <code>writer</code> 필드에 인덱스를 추가합니다:</p>
<pre><code>
ALTER TABLE tbl_notice ADD INDEX idx_title (title);
ALTER TABLE tbl_notice ADD INDEX idx_content (content);
ALTER TABLE tbl_notice ADD INDEX idx_writer (writer);
</code></pre>

<h2>tbl_reply</h2>
<p><code>tbl_reply</code> 테이블에 대해 <code>tno</code>, <code>replyer</code> 필드에 인덱스를 추가합니다:</p>
<pre><code>
ALTER TABLE tbl_reply ADD INDEX idx_tno (tno);
ALTER TABLE tbl_reply ADD INDEX idx_replyer (replyer);
</code></pre>

<h2>tbl_member & tbl_member_role</h2>
<p><code>tbl_member</code> 및 <code>tbl_member_role</code> 테이블에 대해 email 필드에 인덱스를 추가합니다:</p>
<pre><code>
ALTER TABLE tbl_member ADD INDEX idx_email (email);
ALTER TABLE tbl_member_role ADD INDEX idx_email (email);
</code></pre>

<h2>tbl_like, tbl_board_img, tbl_like_notice, tbl_notice_img</h2>
<p><code>tbl_like</code>, <code>tbl_board_img</code>, <code>tbl_like_notice</code>, <code>tbl_notice_img</code> 테이블에 대해 외래키 필드에 인덱스를 추가합니다:</p>
<pre><code>
ALTER TABLE tbl_like ADD INDEX idx_tno (tno);
ALTER TABLE tbl_like ADD INDEX idx_email (email);

ALTER TABLE tbl_board_img ADD INDEX idx_tno (tno);

ALTER TABLE tbl_like_notice ADD INDEX idx_nno (nno);
ALTER TABLE tbl_like_notice ADD INDEX idx_email (email);

ALTER TABLE tbl_notice_img ADD INDEX idx_nno (nno);
</code></pre>

</div>
