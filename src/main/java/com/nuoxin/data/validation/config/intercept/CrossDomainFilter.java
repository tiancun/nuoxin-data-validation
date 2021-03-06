package com.nuoxin.data.validation.config.intercept;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter比Spring的Intercepter更早介入请求生命周期，所以可以更早的处理OPTIONS请求.
 * @author tiancun
 * @date 2018-04-13
 */
public class CrossDomainFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger(CrossDomainFilter.class);

    private volatile boolean allowCrossDomain = true;

    @Override
    public void init(FilterConfig config) throws ServletException {}

    @Override
    public void doFilter(ServletRequest rq, ServletResponse rs,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) rq;
        HttpServletResponse response = (HttpServletResponse) rs;
        if (allowCrossDomain) {
            //LOG.info("允许客户端跨域访问");
            // 重要：clientIp不能为*，否则session无法传递到服务器端.
            response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            //response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Credentials", "true");

            /**
             * 处理 Preflight 情况下的额外返回数据:
             * https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS#Preflighted_requests
             * 需要确认 Preflight 是有效的请求，而不是直接进行的OPTIONS操作.
             */
            LOG.info("Access-Control-Request-Method=" + request.getHeader("Access-Control-Request-Method") + ",OPTIONS=" + request.getMethod());
            if (request.getHeader("Access-Control-Request-Method") != null
                    && "OPTIONS".equalsIgnoreCase(request.getMethod())) {
                response.addHeader("Access-Control-Allow-Methods", "GET, POST");
                response.addHeader("Access-Control-Allow-Headers",
                        "X-Requested-With, Origin, Content-Type, Cookie, X-Access-Token");
            }
            filterChain.doFilter(request, response);
        }


    }

    @Override
    public void destroy() {}

    public void setAllowCrossDomain(boolean allowCrossDomain) {
        this.allowCrossDomain = allowCrossDomain;
    }

}
