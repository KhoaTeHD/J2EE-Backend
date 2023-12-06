package com.groupnine.mediasocial.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {
	
	@Bean
    public Cloudinary getCloudinary(){
        Map<String, Comparable> config = new HashMap();
        config.put("cloud_name", "ddc4rolln");
        config.put("api_key", "354589341255464");
        config.put("api_secret", "mjCqgD3DSaJ2sZjp-RnOCMRqQZ4");
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
