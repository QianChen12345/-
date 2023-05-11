
import java.io.File;

import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class Imagestorage
 */
@WebServlet("/Imagestorage")
public class Imagestorage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Imagestorage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

//		BufferedReader reader = request.getReader();
//
//		String msg = null;
//
//		String line;
//
//		while ((line = reader.readLine()) != null) {
//			msg += line;
//		}

//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		FileItemIterator iter = upload.getItemIterator(request);
//		InputStream inputStream = iter.next().openStream();
//		System.out.println(inputStream);
//		System.out.println(inputStream.available());// 返回非阻塞情况下，一次性可读的字节数
//		System.out.println(msg);
		
		
		 DiskFileItemFactory factory = new DiskFileItemFactory();  
	        //获取文件需要上传到的路径  
	        String path = request.getRealPath("/upload");  
	        File file=new File(path);
	        if(!file.exists()){
	        	file.mkdirs();
	        }
	        factory.setRepository(new File(path));  
	        //设置 缓存的大小
	        factory.setSizeThreshold(1024*1024) ;  
	        //文件上传处理  
	        ServletFileUpload upload = new ServletFileUpload(factory);  
	        try {  
	            //可以上传多个文件  
	            List<FileItem> list = (List<FileItem>)upload.parseRequest(new ServletRequestContext (request));  
	            for(FileItem item : list){  
	                //获取属性名字  
	                String name = item.getFieldName();  
	                //如果获取的 表单信息是普通的 文本 信息  
	                if(item.isFormField()){                     
	                    //获取用户具体输入的字符串,因为表单提交过来的是 字符串类型的  
	                    String value = item.getString() ;  
	                    request.setAttribute(name, value);  
	                }else{  
	                    //获取路径名  
	                    String value = item.getName() ;  
	                    //索引到最后一个反斜杠  
	                    int start = value.lastIndexOf("\\");  
	                    //截取 上传文件的 字符串名字，加1是 去掉反斜杠，  
	                    String filename = value.substring(start+1);  
	                    request.setAttribute(name, filename);  
	                    //写到磁盘上  
	                    item.write( new File(path,filename) );//第三方提供的  
	                    System.out.println("上传成功："+filename);
	                    response.getWriter().print(filename);//将路径返回给客户端
	                }  
	            }  
	              
	        } catch (Exception e) {  
	        	System.out.println("上传失败");
	        	response.getWriter().print("上传失败："+e.getMessage());
	        }  

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	


}
