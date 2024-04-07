<div>
    <h1 align="center">Toy Project - JWT Basic</h1>
    <p>
    &nbsp; - 이 프로젝트는 JWT를 사용해 보는 API 개발용 JAVA 애플리케이션입니다.<br>
      - Spring Security 6의 구조에 대한 내용을 포함하고 있습니다.
    </p>
</div>
<br/>

## Index
- [Project Overview](#Project-Overview)
- [Essential Concepts](#Essential-Concepts)
- [Architecture](#Architecture)
- [Project Description](#Project-Description)
- [Depolyment](#Deployment)
- [Trouble Shooting](#Trouble-Shooting)
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
<br/>

## Architecture
- Spring Security의 내부 구조입니다.

### #1. Servlet Filters
- 단일 HTTP 요청에 따른 Handler들의 계층입니다.
- 클라이언트가 웹 애플리케이션에 요청을 보내면, 서버 컨테이너는 요청 URI 경로를 보고 해당 요청을 어떻게 처리할 것인지를 결정합니다.
- 이 결정 과정에서 컨테이너는 여러 개의 Filter를 순서대로 실행하는 FilterChain을 만들어 요청을 처리합니다.
<br/>

  <img src="https://docs.spring.io/spring-security/reference/_images/servlet/architecture/filterchain.png" alt="servlet-filters" width=200 align="center">
<br/>

### #2. DelegatingFilterProxy
- Servlet Container와 ApplicationContext를 연결해주는 Filter 구현체입니다.
- WAS (Apache Tomcat)의 Filter들 중에서 다음 Filter로 넘어가기 전에 중간에 가로채어 SecurityFilterChain의 로직을 실행합니다.
- Spring Bean을 찾아 요청을 넘겨줍니다.
- doFilter() 메서드를 호출하여 Spring Bean에 모든 작업을 위임할 수 있습니다.
<br/>

  <img src="https://docs.spring.io/spring-security/reference/_images/servlet/architecture/delegatingfilterproxy.png" alt="delegating-filter-proxy" width=200 align="center">
<br/>

### #3. FilterChainProxy
- Spring Security에서 제공하는 Filter입니다.
- DelegatingFilterProxy에 의해 감싸지는 Bean입니다.
- DelegatingFilterProxy가 요청을 가로채어 전달해 줄 목적지입니다.
- FilterChainProxy 자체는 Servlet Container가 알아볼 수 없기 때문에 DelegatingFilterProxy라는 중간 필터를 사용하여 Servlet Container와 연결합니다.
<br/>

  <img src="https://docs.spring.io/spring-security/reference/_images/servlet/architecture/filterchainproxy.png" alt="filter-chain-proxy" width=400 align="center">
<br/>

### #4. SecurityFilterChain
- FilterChainProxy를 매개로 하여 사용됩니다.
- FilterChain에 대한 정보를 담고 있는 클래스입니다.
- 인증, 인가, 검증 등을 수행하는 Filter들의 묶음입니다.
- 아래와 같은 방법을 통해 여러 개의 SecurityFilterChain을 등록할 수 있습니다.
```java
import java.beans.BeanProperty;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
        
        http
                .securityMatchers((auth) -> auth.requestMatchers("/user"));
        
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/user").permitAll());
        
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {

        http
                .securityMatchers((auth) -> auth.requestMatchers("/admin"));

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/admin").permitAll());
        return http.build();
    }
}
```
- FilterChainProxy는 여러 개의 SecurityFilterChain 중 하나를 선택하여 요청을 전달하는데, SecurityFilterChain이 등록된 순서 (메서드가 정의된 순서 또는 @Order로 지정한 순서) 또는 매핑되어 있는 RequestMatcher 값이 일치하는지 확인하여 선택합니다.
<br/>

  <img src="https://docs.spring.io/spring-security/reference/_images/servlet/architecture/securityfilterchain.png" alt="security-filter-chain" width=400 align="center">
<br/>

<br/>

## Project Description
- Spring Data JPA를 이용하여 회원 가입 시 사용자 계정을 생성하고 로그인 시 조회합니다.
- JWT를 이용하여 로그인하려는 사용자 계정을 검증합니다.
- JWT 0.12.3 버전을 사용하였습니다.
  <br/>


### - Entity
  + User

    | 필드명 | 특징                                               |
    |---|---|
    | id | User Entity의 Primary Key |
    | username | UserDetailsService 구현 클래스에서 username을 파라미터로 입력 받는 loadUserByUsername() 메서드를 호출 시 사용 |
    | password | BCryptPasswordEncoder로 암호화한 비밀번호 |
    | role | ROLE_로 시작하는 String 타입의 문자열 |
<br/>

### - JWT
  + JwtUtil
    - String 타입이 아닌 SecretKey 타입의 비밀키를 생성자에 주입하여 사용합니다.
    - 비밀키를 통해 토큰을 검증하고 해당 토큰으로부터 username, role, expiration을 추출합니다.
    - 토큰에서 추출한 값과 비밀키를 사용하여 새로운 signature을 생성합니다.
<br/>

  + CustomedUserDetails
    - UserDetails의 구현 클래스입니다.
    - User Entity를 생성자에 주입하여 사용합니다.
    - User Entity의 상세 정보를 추출하는 메서드를 포함하고 있습니다.
<br/>

  + CustomedUserDetailsService
    - UserDetailsService의 구현 클래스입니다.
    - 반환형이 UserDetails 타입인 loadUserByUsername(String username) 메서드를 overriding 합니다.
    - DB에서 username을 이용하여 User Entity를 조회하고 AuthenticationManager가 해당 Entity를 검증하도록 합니다.
<br/>

  + JwtFilter
    - JwtUtil을 생성자에 주입하여 사용합니다.
    - 내부 로직을 아래와 같은 순서대로 구현하였습니다.
      - [내부 로직]

            1. request에서 Authorization이라는 Header를 추출
            2. Authorization Header를 검증
            3. 순수 token만을 추출
            4. token 소멸 시간 검증
            5. token에서 username과 role을 추출
            6. User Entity를 생성
            7. UserDetails에 User Entity를 담기
            8. Spring Security의 Authentication Token 생성
            9. 세션에 사용자 정보를 등록
            10. 다음 Filter로 요청을 전달
<br/>

  + LoginFilter
    - UsernamePasswordAuthenticationFilter을 상속하는 클래스입니다.
    - AuthenticationManager와 JwtUtil을 생성자에 주입하여 사용합니다.
    - 클라이언트의 HttpServletRequest로부터 username과 password를 추출하여 UsernamePasswordAuthenticationToken 객체를 생성한 후 검증을 위해 AuthenticationManager에게 해당 객체를 전달합니다.
    - 검증에 성공하면 HttpServletResponse 헤더에 JWT를 담아서 응답합니다.
    - 검증에 실패하면 상태코드 401을 응답합니다.
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

## Trouble Shooting
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
