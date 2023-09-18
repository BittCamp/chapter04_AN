package user.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import user.service.UserService;
@Component
public class HelloSpring {
	
	public void menu(ApplicationContext applicationContext) {
		Scanner scan = new Scanner(System.in);
		UserService userService = null; // 부모접근 가능 하도록 선언. 실행하려면 초기화가 되어야한다.
		int num;
		while(true) {
			System.out.println("**************");
			System.out.println("   1. 입력");
			System.out.println("   2. 출력");
			System.out.println("   3. 수정");
			System.out.println("   4. 삭제");
			System.out.println("   5. 끝");
			System.out.println("**************");
			System.out.println(" 번호 입력 : ");
			num = scan.nextInt();
			
			if(num == 5) break;	
			
			if( num == 1) 
				userService = (UserService) applicationContext.getBean("userInsertService");//부모 = 자식 //유저인서트
			else if( num == 2 ) 
				userService = (UserService) applicationContext.getBean("userSelectService"); //유저셀렉트
			else if( num == 3 ) 
				userService = applicationContext.getBean("userUpdateService",UserService.class); //유저셀렉트 // ,UserService.class 캐스팅.
			else if( num == 4 ) 
				userService = applicationContext.getBean("userDeleteService",UserService.class); //유저셀렉트 // ,UserService.class 캐스팅.
			
			userService.execute();//호출
		}//while
	}
	
	public static void main(String[] args) {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("spring/applicationContext.xml"); //전역쓰면 싫어해서 . 지역으로 쓴다.
		HelloSpring helloSpring = (HelloSpring) applicationContext.getBean("helloSpring");
		helloSpring.menu(applicationContext); // 매개변수로 쓰면 지역으로 쓸수있다.
		System.out.println("프로그램 종료합니다.");
		
	}

}
