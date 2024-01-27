package com.caching.service.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

        /**
         * Handle error and return error page.
         *
         * @param  request   the HTTP servlet request
         * @param  model     the model containing the error status code
         * @return          the name of the error page
         */
        @GetMapping("/error")
        public String handleError(HttpServletRequest request, Model model) {

            Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

            if (status != null) {
                int statusCode = Integer.parseInt(status.toString());
                model.addAttribute("status", statusCode);
            }

            return "error";
        }


    }

