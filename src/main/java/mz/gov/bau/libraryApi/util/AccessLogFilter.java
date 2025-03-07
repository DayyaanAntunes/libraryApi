package mz.gov.bau.libraryApi.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class AccessLogFilter extends OncePerRequestFilter implements Ordered {
    @Override
    public int getOrder() {
        return -1;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            int responseStatus = response.getStatus();
            String statusColor = setStatusColor(responseStatus);

            log.info("{} {} {} - {} {}ms", statusColor + responseStatus + "\u001B[0m", request.getMethod(), request.getRequestURI(), duration + "\u001B[0m", LocalDateTime.now().format(formatter));
        }
    }

    private String setStatusColor(int status) {
        if (status >= 200 && status < 300) {
            return "\u001B[32m";
        } else if (status >= 300 && status < 400) {
            return "\u001B[35m";
        } else if (status >= 400 && status < 500) {
            return "\u001B[33m";
        } else if (status >= 500 && status < 600) {
            return "\u001B[31m";
        } else {
            return "\u001B[0m";
        }

    }
}