import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFReader;

import com.google.gson.Gson;

/**
 * Servlet implementation class TripleGeoReview
 */
public class GeoLiftReview extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static String filePath;
	
	// Options for writing the config file
	static String configFile;
	static String configTemplate;
	static String outputFormat;
	static String execType;
	   

	   public void init( ){
	      filePath = getServletContext().getRealPath("/");
	      // Replace backslashes with forward slashes - remove for Linux???
	      filePath = filePath.replace("\\", "/");
	      //System.out.println(filePath);
	   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE>Hello World!</TITLE>"
		+ "</HEAD><BODY>Hello Reviewer!!!</BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	 Model mod0 = ModelFactory.createDefaultModel();

	   	 RDFReader r0 = mod0.getReader( "N3" );
	   	 r0.read( mod0, "file:///"+filePath+"result/result.ttl" );
	   	 
	   	 //mod0.add(mod1);
	   	 String[] modArray = new String[2];
	   	 modArray[0] = mod0.toString();
	     
	     Gson gson = new Gson();
	     String json = gson.toJson(modArray);
	     response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(json);
    	}
}
