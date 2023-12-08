package com.groupnine.mediasocial.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.groupnine.mediasocial.config.CloudinaryConfig;

@Service
//@RequiredArgsConstructor
public class CloudinaryService {
	
	@Autowired
    private final Cloudinary cloudinary = new CloudinaryConfig().getCloudinary();
	
    public Map uploadImage(MultipartFile file)  {
        try{
        	Map params = ObjectUtils.asMap(
        		    "folder", "media");
            Map data = this.cloudinary.uploader().upload(file.getBytes(), params);
            
            return data;
        }catch (IOException io){
            throw new RuntimeException("Image upload fail");
        }
    }
    
    public Map uploadAvatar(MultipartFile file)  {
        try{
        	Map params = ObjectUtils.asMap(
        		    "folder", "avatar");
            Map data = this.cloudinary.uploader().upload(file.getBytes(), params);
            
            return data;
        }catch (IOException io){
            throw new RuntimeException("Image upload fail");
        }
    }
    
    public String uploadVideo(MultipartFile file)  {
        try{
        	Map params = ObjectUtils.asMap(
        		    "folder", "media",
        		    "resource_type", "video"
        			);
        	Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), params);
            
        	String videoUrl = (String) result.get("secure_url");
            
            return videoUrl;
        }catch (IOException io){
            throw new RuntimeException("Video upload fail");
        }
    }
    
    public Map delete(String url)  {
        try{
        	String[] s = url.split("/");
        	String public_id = s[s.length-2] + "/" + s[s.length-1].split("\\.")[0];
        	
            Map data = this.cloudinary.uploader().destroy(public_id , Map.of());
            
            return data;
        }catch (IOException io){
            throw new RuntimeException("Image destroy fail");
        }
    }
    
    public Map deleteVideo(String url)  {
        try{
        	String[] s = url.split("/");
        	String public_id = s[s.length-2] + "/" + s[s.length-1].split("\\.")[0];
        	
        	Map params = ObjectUtils.asMap(
        		    "resource_type", "video"
        			);
        	
            Map data = this.cloudinary.uploader().destroy(public_id , params);
            
            return data;
        }catch (IOException io){
            throw new RuntimeException("Video destroy fail");
        }
    }
}

