import static org.junit.Assert.fail;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.k2h2.counam.entity.User;
import com.k2h2.counam.mapper.UserMapper;

@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		, "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {

	@Autowired
	UserMapper um;
	
	@Test
	public void test() {
		User user = um.getUser("1");
		System.out.println(String.format("__[D29]__: %s", user));
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("sadf");
		user.setAccToken("asdfewofiwef");
		user.setAgreement(true);
		user.setAuthId("a09323");
		user.setAuthType("GOOGLE");
		user.setStatus("READY");
		this.um.createUser(user);
	}

}
