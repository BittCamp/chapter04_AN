package spring.conf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import user.bean.UserDTO;
import user.dao.UserDAOImpl;

@Configuration
@PropertySource("classpath:spring/db.properties")
public class SpringConfiguration {
	@Value("${jdbc.driver}")
	private String driver;
	@Value("${jdbc.url}")
	private String url;
	private @Value("${jdbc.username}") String username;
	private @Value("${jdbc.password}") String password;
	
	@Bean
	public BasicDataSource dataSource(){ //applicationContext.xml 과 비교하면서 보시오. 샘쌤임. 형태변형임.
		/*BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicDataSource.setUrl("jdbc:oracle:thin:@locatlhost:1521:xe");
		basicDataSource.setUsername("c##java");
		basicDataSource.setPassword("1234");
		*/
		
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		
		return basicDataSource;
	}
	/*@Bean(name="userDAOImpl") // 자바 문법이랑 비슷하게 
	public UserDAOImpl getuserDAOImpl(){ //함수에 리턴되는 얘를 빈으로 써먹겠다.
		return new UserDAOImpl();
	} 
	@Bean
	public List<UserDTO> arrayList(){
		return new ArrayList<UserDTO>();	
	} // 각각의 서비스 메소드에  List 선언해주고 썼기때문에 이걸 안써줬다.
	*/ 
}
