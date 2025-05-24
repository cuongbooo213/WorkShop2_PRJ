
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    private static final boolean debug = true;

    private FilterConfig filterConfig = null;

    public AuthenticationFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenticationFilter:TruocKhiXuLy");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenticationFilter:SauKhiXuLy");
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("TaiNguyenDuocYeuCau::" + uri);

        HttpSession session = req.getSession(false);

        String contextPath = req.getContextPath();
        String relativeURI = uri.substring(contextPath.length());

        // Cho phép truy cập vào các tài nguyên công khai
        if ("/index.jsp".equals(relativeURI) || "/login.jsp".equals(relativeURI) || 
            "/login".equals(relativeURI) || "/listd".equals(relativeURI) || 
            "/logout".equals(relativeURI)) {
            chain.doFilter(request, response);
            return;
        }

        // Kiểm tra xác thực cho các trang khác
        if (session == null || session.getAttribute("user") == null) {
            this.context.log("YeuCauTruyCapKhongHopLe vao: " + uri);
            res.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    public FilterConfig getFilterConfig() {
        return this.filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("AuthenticationFilterDaDuocKhoiTao");
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}