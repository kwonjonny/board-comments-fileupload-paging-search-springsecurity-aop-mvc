package board.file.aop;

import java.util.Enumeration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* board.file.service..*.*(..))")
    public void applicationPackagePointcut() {
    }

    @Before("applicationPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String logMessage = buildLogMessage(request);
        String argumentList = buildArgumentList(joinPoint);

        log.info(
                "Enter: {}.{}() with argument[s] = {}\n from URL: {}, Details: {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                argumentList,
                request.getRequestURI(),
                logMessage);
    }

    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info(
                "Exit: {}.{}() with result = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                result);
    }

    // Helper method to build the log message
    private String buildLogMessage(HttpServletRequest request) {
        String remoteIP = request.getRemoteAddr();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String headers = getHeadersAsString(request);
        String parameters = request.getQueryString() != null ? request.getQueryString() : "No parameters";

        return String.format(
                "\n Request Details:\n Remote IP: %s\n Headers: %s\n Method: %s\n URI: %s\n Parameters: %s",
                remoteIP, headers, method, uri, parameters);
    }

    // Helper method to get headers as a string
    private String getHeadersAsString(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.append(headerName).append(": ").append(request.getHeader(headerName)).append("\n");
        }

        return headers.toString();
    }

    // Helper method to build the argument list
    private String buildArgumentList(JoinPoint joinPoint) {
        StringBuilder argumentList = new StringBuilder();
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            argumentList.append(arg.toString()).append("\n");
        }

        return argumentList.toString();
    }
}
