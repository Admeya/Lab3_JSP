package controller;

import Entities.ClientEntity;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ирина on 22.02.2017.
 */
public class AuthFilter implements Filter {
    private List<String> pathFilters = Arrays.asList(new String[]{"add", "remove", "update"});

    public AuthFilter() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        String path = StringUtils.substringAfterLast(uri, "/");
        if (!pathFilters.contains(path)) {
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = ((HttpServletRequest) request).getSession();
        ClientEntity user = (ClientEntity) session.getAttribute("PRINCIPAL");

        if (user != null) {
            chain.doFilter(request, response);
            return;
        } else {
            ((HttpServletResponse) response).sendRedirect("/login.jsp?loginorpass=invalid");
        }
    }

    @Override
    public void destroy() {

    }
}
