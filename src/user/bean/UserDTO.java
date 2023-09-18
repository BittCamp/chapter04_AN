package user.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
public class UserDTO {
	private String name;
	private String id;
	private String pwd;
	
	@Override
	public String toString() { // 투스트링 안만들면 UserDTO 찍을때 주소 값 나온다.
		return name + "\t" + id + "\t" + pwd;
	}

	
}
