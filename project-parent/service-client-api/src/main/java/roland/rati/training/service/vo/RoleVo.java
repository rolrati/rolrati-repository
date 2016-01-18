package roland.rati.training.service.vo;

import java.io.Serializable;

public class RoleVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public RoleVo() {
	}
	
	public Long getId() {
		return id;
	} 
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "RoleVo [id=" + id + ", name=" + name + "]";
	}

}
