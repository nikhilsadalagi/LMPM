package cz.lpmp.jba.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cz.lpmp.jba.entity.Media;
import cz.lpmp.jba.repository.MediaRepository;
import cz.lpmp.jba.service.PostService;

@Controller
public class MediaController {
	
	
	
	@Autowired
	PostService postService;
	
	@Autowired
	MediaRepository mediaRepository;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(UploadController.class);
	private String folderToUpload="/uploads";
	
	@ModelAttribute("media")
	public Media construct(){
		return new Media();
	}

	@RequestMapping(value="/upload",method=RequestMethod.GET)
	public void upload(@RequestParam int postid){
		
	} 
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String formupload(HttpSession session,@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request,@RequestParam int postid,@RequestParam("title") String title,@RequestParam("description") String description){
		
		//String description=request.getAttribute("title").toString();
		String locationUpload=toUpload(session).getAbsolutePath();
		LOGGER.debug("Name [{}]:",multipartFile.getOriginalFilename());
		LOGGER.debug("Size [{}]:",multipartFile.getSize());
		LOGGER.debug("Type [{}]:",multipartFile.getContentType());
		
		File item=new File(locationUpload + File.separator + multipartFile.getOriginalFilename());
		//String description=request.getAttribute("title").toString();
		Media media1=new Media();
		//if(description==null){
		//}else{
			//media1.setDescription(description);
		//}
		media1.setTitle(title.toString());
		media1.setPublisheddate(new Date());
		media1.setFileURI(multipartFile.getOriginalFilename());
		media1.setFile(item);
		media1.setPost(postService.getPost(postid));
		media1.setDescription(description.toString());
		mediaRepository.save(media1);
		
		try {
			multipartFile.transferTo(item);
			LOGGER.debug("file has been saved [{}]",locationUpload);
		} catch (IllegalStateException e) {
			LOGGER.error("failed to save the file to upload results");
			LOGGER.error("cause :[{}]",e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/account.html";
	} 
	
	@RequestMapping(value="/list")
	public ModelMap ulpoadIt(){
		ModelMap mm=new ModelMap();
		return mm;
	}
	
	public File toUpload(HttpSession session){
		String fullpath=session.getServletContext().getRealPath(folderToUpload);
		LOGGER.debug("Location full path [{}]",fullpath);
		File nii=new File(fullpath);
		if(!nii.exists()){
			LOGGER.debug("location to be uploaded ,has been created");
			nii.mkdirs();
		}
		return nii;	
	}
	
	@RequestMapping("/post/add/{id}")
	public String addMedia(Model model,Principal principal,@PathVariable int id){
		//model.addAttribute("post",postService.findThePost(id));
		return "upload";
	}
}
