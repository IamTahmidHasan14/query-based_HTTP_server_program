import com.sun.net.httpserver.*;
import java.net.InetSocketAddress;
import java.io.*;

public class http_query
{
    public static int a = 0;
    public static int b = 0;
    static class aHandler implements HttpHandler
    {
        public void handle(HttpExchange he) throws IOException
        {
            a = Integer.parseInt(he.getRequestURI().getQuery()); 
            String s1 = "a = " + a; 
            he.sendResponseHeaders (200, s1.length()); 
            OutputStream os = he.getResponseBody(); 
            os.write(s1.getBytes()); 
            os.close();
        }
    }
    static class bHandler implements HttpHandler
    {
        public void handle(HttpExchange he) throws IOException
        {
            b = Integer.parseInt(he.getRequestURI().getQuery()); 
            String s1 = "b = " + b; 
            he.sendResponseHeaders (200, s1.length()); 
            OutputStream os = he.getResponseBody(); 
            os.write(s1.getBytes()); 
            os.close();
        }
    }
    static class cHandler implements HttpHandler 
    {
        public void handle(HttpExchange he) throws IOException 
        {
            int c = a + b;
            String s1 = "c = a + b =" + c;
            he.sendResponseHeaders(200, s1.length());
            OutputStream os = he.getResponseBody();
            os.write(s1.getBytes());
            os.close();
        }
    }
    
    public static void main(String arg[]) throws Exception
    {
        HttpServer s = HttpServer.create(new InetSocketAddress(8000),0);
        s.createContext("/a", new aHandler());
        s.createContext("/b", new bHandler());
        s.createContext("/c", new cHandler());
        s.setExecutor (null);
        s.start();
        System.out.println("My HTTP Server is running...");
    }
}
