package happylearning.arithmeticservice.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class UrlDecodeFilter implements Filter {

	private final Logger logger = LoggerFactory.getLogger(UrlDecodeFilter.class);
	
    @Override
    public void doFilter(
      ServletRequest request, 
      ServletResponse response, 
      FilterChain chain) throws ServletException, IOException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        
        String originalUrl = req.getRequestURI();
        String decodedUrl = UriUtils.decode(originalUrl, "UTF-8");
        System.out.println("original url from js = " + originalUrl);
        System.out.println("decoded url from js = " + decodedUrl);
        
        logger.info(
          "Starting a transaction for req : {}", 
          req.getRequestURI());
 
        chain.doFilter(request, response);
        logger.info(
          "Committing a transaction for req : {}", 
          req.getRequestURI());
    }

    // other methods 
}
