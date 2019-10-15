package cn.com.hanbinit.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class JwtFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 2;
    }

    @Override
    public boolean shouldFilter() {
        // 先通过RequestContext获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();
        logger.info("request uri is: {}", requestURI);
        // 当requestURI为[jwt/login]时不进行token认证
        if("jwt/login".equals(requestURI)){
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 获取请求中header的属性authorization值
        final String authHeader = request.getHeader("authorization");

        // OPTION 请求不做处理
        if ("OPTIONS".equals(request.getMethod())) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            return null;
        }
        // 除了OPTIONS请求，其他请求都应该走jwt认证
        else {
            // 查看header中是否存在以Token开头的jwt token
            // 访问接口时我们的token规定为[Token ]开头，这里不是必需
            if (authHeader == null || !authHeader.startsWith("Token ")) {
                ctx.setSendZuulResponse(false); // 不对请求进行路由
                ctx.setResponseStatusCode(200);
                ctx.setResponseBody("Missing or invalid Authorization header");
                return null;
            }
            // 获取最终的jwt token字符串
            final String token = authHeader.substring(6);

            try {
                // 用密钥secretKey来解析jwt token
                final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
                // 将解析的claim添加到中请求中
                request.setAttribute("claims", claims);
                ctx.setSendZuulResponse(true); // 对请求进行路由
                ctx.setResponseStatusCode(200);
                return null;
            } catch (Exception e) {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(200);
                ctx.setResponseBody("Verify Jwt error");
                return null;
            }
        }

    }
}
