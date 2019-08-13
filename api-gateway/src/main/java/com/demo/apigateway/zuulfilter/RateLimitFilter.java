package com.demo.apigateway.zuulfilter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RateLimitFilter extends ZuulFilter {

    //初始化 放入 1000令牌/s  时间窗口为 1s
    private final RateLimiter rateLimiter = RateLimiter.create(1000.0);


    public String filterType() {
        return "pre";
    }

    public int filterOrder() {
        return 0;
    }

    public boolean shouldFilter() {
        return true;
    }

    public Object run() throws ZuulException {
        RequestContext ctx =  RequestContext.getCurrentContext();
        HttpServletResponse response;
        response = ctx.getResponse();

        if(!rateLimiter.tryAcquire()) {
            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            try {
                response.getWriter().write("TOO MANY REQUESTS");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            ctx.setResponseStatusCode(200);
        }

        return null;
    }
}
