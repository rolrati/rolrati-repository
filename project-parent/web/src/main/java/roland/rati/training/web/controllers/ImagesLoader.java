package roland.rati.training.web.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="imagesController")
public class ImagesLoader {
	
	private List<String> imgNames;
	
	@PostConstruct
	public void init(){
		imgNames = new LinkedList<String>();
		
		for (int i = 683; i <= 689  ; i++) {
			imgNames.add("IMG_0" + i + ".JPG");
		}

	}
	
	public ImagesLoader(){
	}

	public List<String> getImgNames() {
		return imgNames;
	}

	public void setImgNames(List<String> imgNames) {
		this.imgNames = imgNames;
	}

}
