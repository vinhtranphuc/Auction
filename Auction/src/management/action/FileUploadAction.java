/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package management.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import config.Validate;
import form.FileUploadForm;
import management.model.bo.AuctionInforBO;

/**
 *
 * @author eswar@vaannila.com
 */
public class FileUploadAction extends org.apache.struts.action.Action {
	
	// http session
	private HttpSession httpSession;

	private String userNameSession;
	
	private String memberID;

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    private AuctionInforBO auctionInforBO;

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
 
            throws Exception {
    	
    	FileUploadForm uploadForm = (FileUploadForm) form;
    	
    	if(!StringUtils.equals("register", uploadForm.getForwardUrl())) {
    		
    		httpSession = request.getSession();

    		userNameSession = (String) httpSession.getAttribute("userName");
    		
    		auctionInforBO = new AuctionInforBO();
    		
    		memberID = auctionInforBO.getMemberIdBasedUserName(userNameSession);
    		
    		if (!Validate.isExistsData(memberID)) {
    			
    			return mapping.findForward("login");
    		}
    	}
 
    	System.out.println("fileUploadAction");
    	String projectFolder = "C:\\Users\\Administrator\\git\\myLocalRepository\\Auction\\WebContent\\images";
        
        FileOutputStream outputStream = null;
        FormFile formFile = null;
        
        String fileName = "";
        
        String categoryImg = "";
        try {
            formFile = uploadForm.getFile();
            
            fileName = uploadForm.getFileName();
            
            auctionInforBO = new AuctionInforBO();
            
            if(StringUtils.equals(fileName, "productNew"))
            	fileName = "product"+auctionInforBO.getNewProductID(memberID);
            
            if(StringUtils.equals(fileName, "memberNew"))
            	fileName = "member"+auctionInforBO.getNewMemberID();
            
            categoryImg = uploadForm.getCategoryImg();
            
            String path = projectFolder+"\\"+categoryImg+"\\"+fileName+".jpg";
            System.out.println(path);
            outputStream = new FileOutputStream(new File(path));
            
            outputStream.write(formFile.getFileData());
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
       // uploadForm.setMessage("The file "+formFile.getFileName()+" is uploaded successfully.");
	
        return mapping.findForward(uploadForm.getForwardUrl());
    }
    
    public String getPath() throws UnsupportedEncodingException {
    	String path = this.getClass().getClassLoader().getResource("").getPath();
    	String fullPath = URLDecoder.decode(path, "UTF-8");
    	String pathArr[] = fullPath.split("/WEB-INF/classes/");
    	System.out.println(fullPath);
    	System.out.println(pathArr[0]);
    	fullPath = pathArr[0];
    	String reponsePath = "";
    	// to read a file from webcontent
    	reponsePath = new File(fullPath).getPath();
    	return reponsePath;
    	}

    public static void main(String[] args) {
    	System.out.println(System.getProperty("user.dir")+"WebContent\\images\\member\\");

	}
}
