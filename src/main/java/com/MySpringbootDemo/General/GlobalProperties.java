package com.MySpringbootDemo.General;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class GlobalProperties {

    @Value("${IMAGE_UPLOAD_BASE_DIR}")
    private String imageUploadBaseDir;

	public String getImageUploadBaseDir() {
		return imageUploadBaseDir;
	}

	public void setImageUploadBaseDir(String imageUploadBaseDir) {
		this.imageUploadBaseDir = imageUploadBaseDir;
	}
}
