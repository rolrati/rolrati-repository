package roland.rati.training.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringContextListener implements
		ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	CreateShema createShema;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		createShema.insertDefaultRoles();
		createShema.insertAdmin();
		createShema.addRoleToAdmin();

		for (int i = 1; i <= 10; i++) {
			String username = "user" + i;

			createShema.insertUsers(username);
			createShema.addRoleToUser(username);
		}
	}
}