
# Spring中的拦截器

```

public class FormInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(FormInterceptor.class);
    private final static String START_TIME = "start_time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer logPrint = new StringBuffer();
        logPrint.append("request url : ").append(request.getRequestURI()).append("\n")
                .append("request contentType: ").append(request.getContentType()).append("\n");

        Enumeration<String> paramKeys = request.getParameterNames();
        while (paramKeys.hasMoreElements()) {
            String paramKey = paramKeys.nextElement();
            Object paramValue = request.getParameter(paramKey);
            logPrint.append("request param key ->").append(paramKey)
                    .append("  value ->").append(paramValue).append("\n");
        }

        logger.debug(logPrint.toString());
        long dealRequestStartTime = System.currentTimeMillis();
        request.setAttribute(START_TIME, dealRequestStartTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long dealRequestStartTime = (long) request.getAttribute(START_TIME);
        long dealRequestFinishTime = System.currentTimeMillis();
        long dealRequestTime = (dealRequestFinishTime - dealRequestStartTime) / 1000;
        logger.debug("deal url " + request.getRequestURI() + " use time " + dealRequestTime + "s");
    }
}
```

preHandle方法在方法执行前调用
postHandle在方法正常执行后调用，如果出现异常则不会调用该方法