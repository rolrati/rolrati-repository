package roland.rati.training.web;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import roland.rati.training.service.UserService;
import roland.rati.training.service.vo.RoleVo;
import roland.rati.training.service.vo.UserVo;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class.getName());
	
	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		 
		UserVo user;
		
		try{
			user = userService.findUserByName(username);
			
			if (user == null) {
				throw new UsernameNotFoundException(username);
			}
			
			List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
			
			return buildUserForAuthentication(user, authorities);
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	private UserDetails buildUserForAuthentication(UserVo user,
			List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<RoleVo> roles) {
		List<GrantedAuthority> result = new LinkedList<GrantedAuthority>();
		
		for (RoleVo role : roles) {
			result.add(new SimpleGrantedAuthority(role.getName()));
		}
		return result;
	}

}
