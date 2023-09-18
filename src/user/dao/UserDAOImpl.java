package user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import user.bean.UserDTO;

@Repository // @Component 써도 되는데 DB와 연동된 빈이라고 표시해줄때는  @Repository로 써줄수 있다. 컴포넌트 랑 기능은 동일하다.
public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements UserDAO {  
	//NamedParameterJdbcDaoSupport 데이터베이스 작업을 더 편리하게 수행할 수 있도록 지원해주는
	@Autowired //이타입을 찾아서 매핑한 다음에 데이터를 전달해라.
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource); //JDBCDataSupport에 있는 setDataSource(~~)를 호출
	}
	
	@Override
	public void write(UserDTO userDTO) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", userDTO.getName());
		map.put("id", userDTO.getId());
		map.put("pwd", userDTO.getPwd());
		
		String sql = "insert into usertable values(:name,:id,:pwd)";
		getNamedParameterJdbcTemplate().update(sql, map);
	}

	@Override
	public List<UserDTO> getUserList() {
		String sql = "select * from usertable"; // new BeanPropertyRowMapper()
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<UserDTO>(UserDTO.class));
	}

	@Override
	public UserDTO getUser(String id) {
		String sql = "select * from usertable where id=?";
		try {
			return getJdbcTemplate().queryForObject(
													sql, 
													new BeanPropertyRowMapper<UserDTO>(UserDTO.class),
												    id);
		} catch (EmptyResultDataAccessException e) {
			return null; //에러메시지 찍지말고 에러 뜨면 널로보내버려라
		}
	}

	@Override
	public void userUpdate(Map<String, String> map) {
		// TODO Auto-generated method stub
		String sql = "update usertable set name = :name ,pwd =(:pwd) where id = (:id)";
		getNamedParameterJdbcTemplate().update(sql, map); //update 인서트 업데이트 딜리트 전부 업데이트다
	}

	@Override
	public void delete(String id) { //?대신에 :id써도되
		String sql = "delete usertable where id = :id";
		getJdbcTemplate().update(sql,id);
	}
	
}
