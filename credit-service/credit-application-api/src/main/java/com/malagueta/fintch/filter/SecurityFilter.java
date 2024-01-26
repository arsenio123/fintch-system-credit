package com.malagueta.fintch.filter;
import com.malagueta.fintch.filter.io.GenericHttpResponse;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityFilter  implements Filter {

    private final String origin="http://localhost:4200";
    private static HashMap<String, String> origins=new HashMap<>();
    static {
        origins.put("http://localhost:4200","http://localhost:4200");
        origins.put("http://localhost:9080","http://localhost:9080");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;

        String actualOrigins=((HttpServletRequest) req).getHeader(HttpHeaders.ORIGIN);

        if(origins.containsValue(actualOrigins)){
            response.setHeader("Access-Control-Allow-Origin",actualOrigins);
        }


        response.setHeader("Access-Control-Allow-Credentials","true");

        HttpServletRequest servletRequest =(HttpServletRequest) req;
        String accessToken=servletRequest.getHeader("Authorization");

        String uri=servletRequest.getRequestURI();

        System.out.println("uri: "+uri);
        System.out.println("accessToken: "+accessToken);
        GenericHttpResponse autheResponse=validateAutentication(uri,accessToken);


        System.out.println();

        if ("OPTIONS".equals(request.getMethod())&&actualOrigins.equals(request.getHeader(HttpHeaders.ORIGIN))){

            response.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE,PUT,OPTIONS");
            response.setHeader("Access-Control-Allow-Headers","Authorization,Content-Type,Accept");
            response.setHeader("Access-Control-Max-Age","3600");
            response.setStatus(HttpStatus.OK.value());

        }else{
            System.out.println("a tentar autenticar com a chave: "+accessToken);

            if(autheResponse.getHttpErrorCode()==HttpStatus.OK.value()){
                chain.doFilter(req,resp);
            }
            else{
                response.setStatus((HttpStatus.valueOf(autheResponse.getHttpErrorCode()).value()));
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private GenericHttpResponse validateAutentication(String uri,String token){
        System.out.println("uri::"+uri);
        System.out.println("token::"+token);
        // token="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTI3MjkzNzMsInVzZXJfbmFtZSI6Intub29wfWFkbWluIiwiYXV0aG9yaXRpZXMiOlsiQURNSU4iXSwianRpIjoiNF9QVjJIQnZMQzJVUUtHbmU1TVU4RzVZdWZVIiwiY2xpZW50X2lkIjoicmVhY3QiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.zi71zQvdgQfcQhHX1wMWg_4LFZ2d2XdDMx5Lcx3OUmM";
        String cleanToken="";
        if(token!=null){
            cleanToken=token.replace("Bearer ","");
        }

        String body="{\"token\":\"" +
                cleanToken+"\",\"uri\":\""+uri+"\""+"}";

        GenericHttpResponse authResponse= HttpSessionValidater.sendPOST(body,token);
        System.out.println("http code: "+authResponse.getHttpErrorCode());
        return authResponse;
    }
}
