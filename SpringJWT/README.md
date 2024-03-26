<div>
    <h1 align="center">Toy Project - Bulletin Board</h1>
    <p>
    &nbsp; - 이 프로젝트는 JPA와 Spring Security를 이용한 간단한 게시판 웹 애플리케이션입니다.<br>
    &nbsp; - JPA를 사용하여 연관 관계에 있는 여러 테이블의 데이터를 객체로 묶어서 <br>
    &nbsp;&nbsp;&nbsp; 간단한CRUD 연산을 수행하는 간단한 Spring boot 프로젝트입니다.<br>
    </p>
</div>
<br/>

## Index
- [Project Overview](#Project-Overview)
- [Project Description](#Project-Description)
- [Depolyment](#Deployment)
- [Problems and Solutions](#Problems-and-Solutions)
- [Reference](#Reference)
<br/>

## Project Overview
- 프로젝트명: JPA와 Spring Security를 활용한 기본적인 게시판 웹 애플리케이션 <a href="http://54.180.82.38:8086/" style="font-size: 15px">[해당 프로젝트 바로 가기]</a>
- 프로젝트 개발 기간: 2024.03.03-2024.03.19
- 프로젝트 언어 및 개발 도구:
    + Backend<br>
      <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="20" height="20"/> </a> <span>&nbsp;- OpenJDK 17</span><br/>
      <a href="https://mariadb.org/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/mariadb/mariadb-icon.svg" alt="mariadb" width="20" height="20"/> </a> <span>&nbsp;- MariaDB v11.3.2</span><br/>
  
    + Frontend<br>
      <a href="https://getbootstrap.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/bootstrap/bootstrap-plain-wordmark.svg" alt="bootstrap" width="20" height="20"/> </a> <span>&nbsp;- Bootstrap v5.1.3</span><br/>
      <a href="https://jquery.com/" target="_blank" rel="noreferrer"> <img src="https://cdn.worldvectorlogo.com/logos/jquery-4.svg" alt="bootstrap" width="20" height="20"/> </a> <span>&nbsp;- jQuery v3.7.1</span><br/>
      <a href="https://www.thymeleaf.org/download.html" target="_blank" rel="noreferrer"> <img src="https://www.thymeleaf.org/images/thymeleaf.png" alt="bootstrap" width="20" height="20"/> </a> <span>&nbsp;- Thymeleaf 3.1.2.RELEASE</span><br/>  

  + Environment<br>
     <a href="https://www.jetbrains.com/idea/" target="_blank" rel="noreferrer"><img src="https://blog.jetbrains.com/wp-content/uploads/2019/01/idea_icon.svg" alt="spring" width="20" height="20"/> </a> <span>&nbsp;- IntelliJ IDEA Ultimate 2023.2.3</span><br/></a>
     <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="20" height="20"/> </a> <span>&nbsp;- Spring boot v3.2.3</span><br/>
     <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="20" height="20"/> </a> <span>&nbsp;- Postman</span><br/>
     <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="20" height="20"/> </a> <span>&nbsp;- Git</span><br/>
     <a href="https://learn.microsoft.com/en-us/powershell/scripting/install/installing-powershell-on-windows?view=powershell-7.4" target="_blank" rel="noreferrer"> <img src="https://upload.wikimedia.org/wikipedia/commons/2/2f/PowerShell_5.0_icon.png" alt="aws" width="20" height="20"/> </a> <span>&nbsp;- Windows PowerShell</span><br/>
     <a href="https://aws.amazon.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/amazonwebservices/amazonwebservices-original-wordmark.svg" alt="aws" width="20" height="20"/> </a> <span>&nbsp;- EC2 (Ubuntu), RDS (MariaDB)</span><br/>


- 개발자:
    + 박영무 ([@VoiceofSiren](https://github.com/VoiceofSiren))   
      AJAX (jQuery) / DB / Validation / Security / Deployment
<br/>

## Project Description
- Back-end를 중점적으로 설명 드리겠습니다.

### Architecture
- 3-Tier 아키텍처를 사용하였습니다.

<p align="left">
  <ol>
    <li style="font-size: 20px">Presentation Layer</li>
      <ul>
        <li>Controller, View, Static resources</li>
      </ul>
    <li style="font-size: 20px">Application Layer</li>
      <ul>
        <li>Service Layer</li>
      </ul>
    <li style="font-size: 20px">Data Access Layer</li>
          <ol>
            <li>ORM Framework</li>
              <ul>
                <li>Spring Data JPA (Repository, Entity)</li>
                <li>MyBatis (Mapper, SQL)</li>
              </ul>
            <li>DBMS Connection</li>
              <ul>
                <li>MaraDB (Local DB for Development / RDS for Deployment)</li>
              </ul>
          </ol>
      </ul>
  </ol>
</p>
<br/>



### Front-end
- Bootstrap을 활용하여 간단한 레이아웃을 구현하였습니다.

#### - UX/UI

<table>
  <thead>
    <tr>
      <th align="center">홈 화면</th>
      <th align="center">로그인 화면</th>
      <th align="center">게시물 조회</th>
      <th align="center">게시물 입력</th>
    </tr>
  <tbody>
    <tr>
      <td align="center"><img src="src/main/resources/static/readme/home.png" width="300px;" alt=""/></td>
      <td align="center"><img src="src/main/resources/static/readme/sign-in.png" width="300px;" alt=""/></td>
      <td align="center"><img src="src/main/resources/static/readme/board-list.png" width="300px;" alt=""/></td>
      <td align="center"><img src="src/main/resources/static/readme/board-form.png" width="300px;" alt=""/></td>
     <tr/>
  </tbody>
</table>

1. Navigation Bar에 홈 화면과 게시물 화면으로 이동할 수 있는 링크를 추가하였습니다.
2. 현재 접속 중인 페이지에 따라 Navigation Bar의 링크를 활성화/비활성화시키도록 설정하였습니다.<br><br>

#### - AJAX
  - 로그인 시 입력한 데이터를 검증합니다.
<table>
  <thead>
    <tr>
      <th align="center">유효성 검증 전</th>
      <th align="center">유효성 검증 후</th>
    </tr>
  <tbody>
    <tr>
      <td align="center"><img src="src/main/resources/static/readme/sign-in-invalid-id.png" width="500px;" alt=""/></td>
      <td align="center"><img src="src/main/resources/static/readme/sing-in-failure.png" width="500px;" alt=""/></td>
    <tr/>
  </tbody>
</table>

1. 유효성 검증을 위해 Thymeleaf의 ${param.error} 변수를 사용하였습니다.


### Back-end
- Spring Data JPA, JPQL, MyBatis를 이용하여 DBMS로 CRUD 연산을 수행하였습니다.
- 개발 시에는 로컬 DB에 엑세스하였으며, 배포 단계에서는 AWS EC2 인스턴스에서 RDS에 엑세스하였습니다.

#### - ER Diagram

  <img src="src/main/resources/static/readme/ER-Diagram.png" alt="ER Diagram" width=800>
<br/>

1. USER와 ROLE은 기본적으로 N:M의 연관관계를 가지지만 USER_ROLE이라는 중간 조인 테이블을 설계하여 N:M 연관관계 매핑의 사용을 지양하였습니다.

#### - Entity

<table>
  <thead>
    <tr>
      <th align="center">USER</th>
      <th align="center">USER_ROLE</th>
      <th align="center">ROLE</th>
    </tr>
  <tbody>
    <tr>
      <td align="center"><img src="src/main/resources/static/readme/user-entity-userRoles.png" width="500px;" alt=""/></td>
      <td align="center"><img src="src/main/resources/static/readme/userRole-entity-user-role.png" width="500px;" alt=""/></td>
      <td align="center"><img src="src/main/resources/static/readme/role-entity-userRoles.png" width="500px;" alt=""/></td>
    <tr/>
  </tbody>
</table>

1. 조인 테이블인 USER_ROLE이 USER와 ROLE 테이블에 대한 외래키를 가지고 있으므로, USER_ROLE의 참조 필드인 user와 role을 연관 관계의 주인으로 지정하였습니다.
2. 지연 로딩 전략을 사용하여 연관관계에 있는 다른 객체들까지 한꺼번에 조회되는 상황을 방지하였습니다.
<br/>

#### - API
- Entity 객체를 직접 반환하지 않고 별도의 DTO 객체를 반환하는 방식으로 개발하였습니다.

<table>
  <thead>
    <tr>
      <th align="center">게시물 조회 API 요청</th>
      <th align="center">실행된 SELECT문</th>
    </tr>
  <tbody>
    <tr>
      <td align="center"><img src="src/main/resources/static/readme/postman-api-boards-result.png" width="500px;" alt=""/></td>
      <td align="center"><img src="src/main/resources/static/readme/api-boards-select.png" width="500px;" alt=""/></td>
    <tr/>
  </tbody>
</table>
<br/>

#### - Authentication
- Spring Security 6를 사용하였습니다.
    - Bean으로 등록할 SecurityFilterChain 메서드 내부에서 requestMatchers()를 사용하여 로그인한 사용자의 권한에 따라 접근 경로를 제한합니다.
<br/>

#### - Authorization
- Spring Security 6를 사용하였습니다.

<table>
  <thead>
    <tr>
      <th align="center">@EnableWebSecurity WebSecurityConfig 클래스</th>
      <th align="center">Thymeleaf - Spring Security</th>
    </tr>
  <tbody>
    <tr>
      <td align="center"><img src="src/main/resources/static/readme/WebSecurityConfig-configureGlobal.png" width="500px;" alt=""/></td>
      <td align="center">
        <img src="src/main/resources/static/readme/thymeleaf-spring-security-namespace.png" width="500px;" alt=""/><br>
        <img src="src/main/resources/static/readme/thymeleaf-authorize-hasRole.png" width="500px;" alt=""/>
      </td>
    <tr/>
  </tbody>
</table>

1. 관리자의 권한을 가진 사용자만 게시물을 삭제할 수 있도록 설정하였습니다.
<br/>

## Deployment

### AWS
- EC2 인스턴스와 RDS DB를 생성하여 배포하였습니다.

#### - RDS
- RDS DB에 엑세스하기 위한 설정 파일입니다.

+ application.yml
  <img src="src/main/resources/static/readme/rds-application-yml.png" alt="ER Diagram" width=800>

#### - EC2
- Local PC의 /.ssh 디렉터리 내부에 아래와 같은 config 파일을 생성합니다.

```plaintext
Host AWStest0002
    HostName ec2-xx-xxx-xx-xx.ap-northeast-2.compute.amazonaws.com
    User ubuntu
    IdentityFile ~/.ssh/awsTest0002.pem
```

- EC2 인스턴스에 SSH를 이용하여 원격으로 로그인한 다음 git 디렉터리로 이동하여 Spring boot 프로젝트로 .jar 파일을 build합니다.

  <img src="src/main/resources/static/readme/ec2-gradlew-build.png" alt="ER Diagram" width=800>
  
- build한 .jar 파일을 실행시킵니다.

  <img src="src/main/resources/static/readme/ec2-java-jar.png" alt="ER Diagram" width=800>

#### - Deployment
- 배포 후의 화면입니다.

  <img src="src/main/resources/static/readme/aws-deployed-web-application.png" alt="ER Diagram" width=800>
<br/>

## Problems and Solutions
### Mapping Entity
  - JPA에서는 Entity의 필드명과 실제 DB에 저장되어 있는 테이블의 칼럼명이 다른 경우 @Column(name="...")을 이용하여 처리할 수 있지만, MyBatis의 경우에는 그렇지 않았습니다.
  - MyBatis를 사용할 때는 \<resultMap\>을 이용하여 해결하였습니다.
  <br/>

### API
  - 게시물과 연관 관계에 있는 사용자(작성자)의 정보까지 JOIN하여 조회하는 API에서 User Entity 자체가 조회되지 않는 에러가 발생하였습니다.
  - 원하는 필드만 조회할 수 있도록 User DTO 객체를 만들어 조회하는 방식으로 해결하였습니다.

### DB
+ SQLNonTransientConnectionException
  - 배포 단계에서 발생한 에러이며, Local DB로 엑세스되지 않는 에러입니다.
  - 권한 설정, 원격 IP 설정 등을 시도해봤지만 해결되지 않았습니다.
  - RDS DB를 생성하여 엑세스하는 방식으로 해결하였습니다.
   <img src="src/main/resources/static/readme/SQLNonTransientConnectionException.png" alt="ER Diagram" width=800>
<br/>

## Reference
#### - Bootstrap: 
#### https://getbootstrap.com/docs/5.1/examples/
#### https://getbootstrap.com/docs/5.1/components/navbar/
#### - QueryDSL: 
#### https://assets.velcdn.com/@minu1117/QueryDsl-SpringBoot-3.x-gradle-%EC%84%A4%EC%A0%95
#### - Spring Security 6: 
#### https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter
#### - SSH config: 
#### https://donggu1105.tistory.com/168
