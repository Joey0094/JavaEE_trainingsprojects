package com.example.web;

import com.example.pojo.Cart;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import com.example.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends BaseServlet {

    private final UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        User loginUser = userService.login(user);

        if(loginUser == null) {
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username", user.getUsername());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }


    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        String key = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        req.getSession().removeAttribute("KAPTHCA_SESSION_KEY");

        if(key != null) {
            if(key.equalsIgnoreCase(req.getParameter("code"))) {
                if(userService.existsUsername(user.getUsername())) {
                    req.setAttribute("username", user.getUsername());
                    req.setAttribute("email", user.getEmail());
                    req.setAttribute("msg", "用户名已存在");
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
                } else {
                    userService.registUser(user);
                    req.getSession().setAttribute("user", user);
                    resp.sendRedirect(req.getContextPath() + "pages/user/regist_success.jsp");
                }
            } else {
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());
                req.setAttribute("msg", "验证码错误");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            req.setAttribute("msg", "验证码已失效，请单击验证码刷新");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }
}
