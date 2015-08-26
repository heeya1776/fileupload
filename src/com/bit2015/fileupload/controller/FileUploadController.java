package com.bit2015.fileupload.controller;

import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	
	// Logger 객체를 얻어옴
	private static final Log LOG = LogFactory.getLog( FileUploadController.class );
	
	// 파일 저장 경로 지정
	private static final String SAVE_PATH = "C:\\temp";
	  
	@RequestMapping( "/form" )
	public String form() {
		return "form";
	}
	
	@RequestMapping( "/upload" )
	public String upload( @RequestParam String email, @RequestParam( "file1" ) MultipartFile file1, @RequestParam( "file2" ) MultipartFile file2, @RequestParam( "file3" ) MultipartFile file3, Model model ) {
        
		// 단순 파라미터 값
		LOG.debug( " ######## email : " + email );

		// 첫 번째 파일 처리
		if( file1.isEmpty() == false ) {
			
	        String fileOriginalName = file1.getOriginalFilename();
	        String extName = fileOriginalName.substring( fileOriginalName.lastIndexOf(".") + 1, fileOriginalName.length() );
	        String fileName = file1.getName();
	        Long size = file1.getSize();
	        
	        String saveFileName = genSaveFileName( extName );
	        String url = "/product-images/" + saveFileName;
	
	        LOG.debug( " ######## fileOriginalName : " + fileOriginalName );
	        LOG.debug( " ######## fileName : " + fileName );
	        LOG.debug( " ######## fileSize : " + size );
	        LOG.debug( " ######## fileExtensionName : " + extName );
	        LOG.debug( " ######## saveFileName : " + saveFileName );        
	
	        writeFile( file1, SAVE_PATH, saveFileName );
	        
	        model.addAttribute( "productImageUrl1", url );
		}
		
		// 두 번째 파일 처리
			if( file1.isEmpty() == false ) {
					
			     String fileOriginalName = file2.getOriginalFilename();
			     String extName = fileOriginalName.substring( fileOriginalName.lastIndexOf(".") + 1, fileOriginalName.length() );
			     String fileName = file2.getName();
		         Long size = file2.getSize();
			        
			        String saveFileName = genSaveFileName( extName );
			        String url = "/product-images/" + saveFileName;
			
			        LOG.debug( " ######## fileOriginalName : " + fileOriginalName );
			        LOG.debug( " ######## fileName : " + fileName );
			        LOG.debug( " ######## fileSize : " + size );
			        LOG.debug( " ######## fileExtensionName : " + extName );
			        LOG.debug( " ######## saveFileName : " + saveFileName );        
			
			        writeFile( file2, SAVE_PATH, saveFileName );
			        
			        model.addAttribute( "productImageUrl2", url );
				}
			
			// 세 번째 파일 처리
			if( file3.isEmpty() == false ) {
				
		        String fileOriginalName = file3.getOriginalFilename();
		        String extName = fileOriginalName.substring( fileOriginalName.lastIndexOf(".") + 1, fileOriginalName.length() );
		        String fileName = file3.getName();
		        Long size = file3.getSize();
		        
		        String saveFileName = genSaveFileName( extName );
		        String url = "/product-images/" + saveFileName;
		
		        LOG.debug( " ######## fileOriginalName : " + fileOriginalName );
		        LOG.debug( " ######## fileName : " + fileName );
		        LOG.debug( " ######## fileSize : " + size );
		        LOG.debug( " ######## fileExtensionName : " + extName );
		        LOG.debug( " ######## saveFileName : " + saveFileName );        
		        LOG.debug( " ######## urlName : " + url);
		        
		        writeFile( file3, SAVE_PATH, saveFileName );
		        
		        model.addAttribute( "productImageUrl3", url );
			}
			
				
        return "result";
	}
	
	private void writeFile( MultipartFile file, String path, String fileName ) {
		FileOutputStream fos = null;
		try {
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream( path + "\\" + fileName );
			fos.write(fileData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	private String genSaveFileName( String extName ) {
		
        Calendar calendar = Calendar.getInstance();
		String fileName = "";
        
        fileName += calendar.get( Calendar.YEAR );
        fileName += calendar.get( Calendar.MONTH );
        fileName += calendar.get( Calendar.DATE );
        fileName += calendar.get( Calendar.HOUR );
        fileName += calendar.get( Calendar.MINUTE );
        fileName += calendar.get( Calendar.SECOND );
        fileName += calendar.get( Calendar.MILLISECOND );
        fileName += ( "." + extName );
        
        return fileName;
	}
}
