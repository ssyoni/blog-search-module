package modules.be.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j(topic = "SAVE_SEARCH_WORD_LOGGER")
@Component
public class LogInterceptor implements AsyncHandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.debug("url : {}", request.getRequestURL());
//        log.debug("request : {} response : {}", request.getMethod(), request.getRequestURL());
        AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
