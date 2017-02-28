package filters;

import Entities.ClientEntity;
import Entities.EmployeeEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

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
    private static Logger logger = Logger.getLogger(AuthFilter.class);
    private List<String> pathFilters = Arrays.asList(new String[]{"login", "registration", "error", "accessDenied"});

    public AuthFilter() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();

        boolean isContains = false;
        for (String paths : pathFilters) {
            isContains = StringUtils.contains(uri, paths);
            if (isContains)
                break;
            logger.trace("filter enabled " + uri + " paths " + paths);
        }
        if (isContains) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = ((HttpServletRequest) request).getSession();
        ClientEntity user = (ClientEntity) session.getAttribute("PRINCIPAL");
        EmployeeEntity empl = (EmployeeEntity) session.getAttribute("EMPLOYER");
        EmployeeEntity admin = (EmployeeEntity) session.getAttribute("ADMIN");

        if (user != null) {
            logger.trace("user not null" + user.getName());
            chain.doFilter(request, response);
            return;
        } else if (empl != null) {
            logger.trace("empl not null " + empl.getName());
            chain.doFilter(request, response);
            return;
        } else if (admin != null) {
            logger.trace("admin not null " + admin.getName());
            chain.doFilter(request, response);
            return;
        } else {
            logger.trace("user null");
            ((HttpServletResponse) response).sendRedirect("/tour/accessDenied.jsp");
        }
        logger.trace("filter disabled");
    }

    @Override
    public void destroy() {

    }
}
