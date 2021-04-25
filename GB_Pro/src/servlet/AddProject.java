package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class AddProject
 */
@WebServlet("/AddProject")
public class AddProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		com.ProjectsService pr= new com.ProjectsService();
		
		FileItem fi=null;
		String file_name = "C:\\img\\";
		
		 boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		 if (!isMultipartContent) {
		 return;
		 }
		 FileItemFactory factory = new DiskFileItemFactory();
		 ServletFileUpload upload = new ServletFileUpload(factory);
		 try {
		 List < FileItem > fields = upload.parseRequest( new ServletRequestContext(request));
		 Iterator < FileItem > it = fields.iterator();
		 if (!it.hasNext()) {
		 return;
		 }
		 int in=0;
		 while (it.hasNext()) {
		 FileItem fileItem = it.next();
		 boolean isFormField = fileItem.isFormField();
		 if (isFormField) {

				 if (fileItem.getFieldName().equals("proName")) {
					 pr.setName(fileItem.getString()) ;
				 }
				 if (fileItem.getFieldName().equals("proAbout")) {
					 pr.setAbout(fileItem.getString()) ;
				 }
				 if (fileItem.getFieldName().equals("proContext")) {
					 pr.setContext(fileItem.getString()) ;
				 }
				 if (fileItem.getFieldName().equals("proGoals")) {
					 pr.setGoals(fileItem.getString()) ;
				 }
				 if (fileItem.getFieldName().equals("proBudget")) {
					 pr.setBudget((Double.parseDouble(fileItem.getString()))) ;
				 }
				 if (fileItem.getFieldName().equals("proTimeline")) {
					 pr.setTimeline(fileItem.getString()) ;
				 }
				 if (fileItem.getFieldName().equals("proName1")) {
					 pr.setName1(fileItem.getString()) ;
				 }
				 if (fileItem.getFieldName().equals("proDis1")) {
					 pr.setDis1(fileItem.getString()) ;
				 }
				 if (fileItem.getFieldName().equals("proName2")) {
					 pr.setName2(fileItem.getString()) ;
				 }
				 if (fileItem.getFieldName().equals("proDis2")) {
					 pr.setDis2(fileItem.getString()) ;
				 }
				 else {
					 
				 }
			 
		 } 
		 }
		 
		 model.Projects p= new model.Projects();
		 int xid=p.addProject(pr);
		 
		 
		 it = fields.iterator();
		 while (it.hasNext()) {
			 FileItem fileItem = it.next();
			 boolean isFormField = fileItem.isFormField();
			 if (isFormField) {

					
			 } else {
			 if (fileItem.getSize() > 0) {
				 fi=fileItem;
				 in++;
				 fi.write(new File(file_name+xid+"_"+in+".jpg"));
			 }
			 }
			 }
		 
		
		 
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		
		
		
		
		
		 response.sendRedirect("add_projects.jsp?s=1");
        
     //response.getWriter().write("Thank you");	
        

    }
	}


