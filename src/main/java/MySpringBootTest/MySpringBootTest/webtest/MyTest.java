package MySpringBootTest.MySpringBootTest.webtest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MySpringBootTest.MySpringBootTest.service.MyTestService;

@RestController
@RequestMapping("/mytest")
public class MyTest {

	@Autowired
	private MyTestService myTestService;
	
	
	@RequestMapping("/str")
	public String reStr(){
		return myTestService.readSqliteData();
	}
}
