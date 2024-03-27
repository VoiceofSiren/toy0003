<div>
    <h1 align="center">Toy Project - JWT Basic</h1>
    <p>
    &nbsp; - 이 프로젝트는 JPA와 Spring Security를 이용하여 간단한 회원가입 및 로그인 검증을 수행하기 위한 애플리케이션입니다.<br>
    &nbsp; - JWT를 사용하여
    </p>
</div>
<br/>

## Index
- [Project Overview](#Project-Overview)
- [Essential Concepts](#Essential-Concepts)
- [Project Description](#Project-Description)
- [Architecture](#Architecture)
- [Depolyment](#Deployment)
- [Problems and Solutions](#Problems-and-Solutions)
- [Reference](#Reference)
<br/>

## Project Overview
- 프로젝트 주제: JWT Basic
- 프로젝트 개발 기간: 2024.03.25-2024.03.26
- 프로젝트 언어 및 개발 도구:
    + Backend<br>
      <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="20" height="20"/> </a> <span>&nbsp;- OpenJDK 17</span><br/>
      <a href="https://downloads.mysql.com/archives/installer/" target="_blank" rel="noreferrer"> <img src="https://www.svgrepo.com/show/303251/mysql-logo.svg" alt="mysql" width="20" height="20"/> </a> <span>&nbsp;- MySQL 8.0.32</span><br/>

  + Environment<br>
     <a href="https://www.jetbrains.com/idea/" target="_blank" rel="noreferrer"><img src="https://blog.jetbrains.com/wp-content/uploads/2019/01/idea_icon.svg" alt="spring" width="20" height="20"/> </a> <span>&nbsp;- IntelliJ IDEA Ultimate 2023.2.3</span><br/></a>
     <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="20" height="20"/> </a> <span>&nbsp;- Spring boot v3.2.3</span><br/>
     <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="20" height="20"/> </a> <span>&nbsp;- Postman</span><br/>
     <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="20" height="20"/> </a> <span>&nbsp;- Git</span><br/>
     <a href="https://aws.amazon.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/amazonwebservices/amazonwebservices-original-wordmark.svg" alt="aws" width="20" height="20"/> </a> <span>&nbsp;- EC2 (Ubuntu), RDS (MySQL)</span><br/>


- 개발자:
    + 박영무 ([@VoiceofSiren](https://github.com/VoiceofSiren))   
      DB / Validation / Security / Deployment
<br/>

## Essential Concepts

### #1. Spring Security
- JAVA 웹 애플리케이션의 보안을 강화하는 프레임워크입니다.

### #2. Filter
- 웹 요청을 중간에 가로채어 인증, 인가, 로깅 등에 대한 작업을 처리하는 객체입니다.

### #3. Servlet Container
- 웹 애플리케이션을 실행하는 서버 환경입니다.

### #4. ApplicationContext
- Spring Framework에서 관리하는 Bean 객체들을 모아놓은 컨텍스트입니다.

## Project Description
- Spring Data JPA를 이용하여 사용자 계정을 생성하고 조회합니다.
- JWT를 이용하여 로그인하려는 사용자 계정을 검증합니다.
- JWT 0.12.3 버전을 사용하였습니다.



### Architecture

### #1. Servlet Filters
- 단일 HTTP 요청에 따른 Handler들의 계층입니다.
- 클라이언트가 웹 애플리케이션에 요청을 보내면, 서버 컨테이너는 요청 URI 경로를 보고 해당 요청을 어떻게 처리할 것인지를 결정합니다.
- 이 결정 과정에서 컨테이너는 여러 개의 Filter를 순서대로 실행하는 FilterChain을 만들어 요청을 처리합니다.

  <img src="https://docs.spring.io/spring-security/reference/_images/servlet/architecture/filterchain.png" alt="servlet-filters" width=500 align="center">
<br/>

### #2. DelegatingFilterProxy
- Servlet Container와 ApplicationContext를 연결해주는 Filter 구현체입니다.
- doFilter() 메서드를 호출하여 Spring Bean에 모든 작업을 위임할 수 있습니다.

  <img src="https://docs.spring.io/spring-security/reference/_images/servlet/architecture/delegatingfilterproxy.png" alt="delegating-filter-proxy" width=500 align="center">
<br/>

### #3. FilterChainProxy
- Spring Security에서 제공하는 Filter입니다.
- DelegatingFilterProxy에 의해 감싸지는 Bean입니다.
- FilterChainProxy 자체는 Servlet Container가 알아볼 수 없기 때문에 DelegatingFilterProxy라는 중간 필터를 사용하여 Servlet Container와 연결합니다.

  <img src="https://docs.spring.io/spring-security/reference/_images/servlet/architecture/filterchainproxy.png" alt="filter-chain-proxy" width=500 align="center">
<br/>

### #4. SecurityFilterChain
- FilterChainProxy에 의해 사용됩니다.
- FilterChain에 대한 정보를 담고 있는 클래스입니다.
- Spring Security의 Filter 인스턴스들 중 무엇이 현재의 request에 적용되어야 할지를 결정합니다.

  <img src="https://docs.spring.io/spring-security/reference/_images/servlet/architecture/securityfilterchain.png" alt="security-filter-chain" width=800 align="center">
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
#### - Spring Security Architecture: 
#### https://docs.spring.io/spring-security/reference/servlet/architecture.html
#### - QueryDSL: 
#### https://assets.velcdn.com/@minu1117/QueryDsl-SpringBoot-3.x-gradle-%EC%84%A4%EC%A0%95
#### - Spring Security 6: 
#### https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter
#### - SSH config: 
#### https://donggu1105.tistory.com/168
