package com.in28minutes.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping("check-login")
    public String goToCheckLoginPage(@RequestParam String name, ModelMap model) {
        /*
        Understanding DispatcherServlet, Model 1, Model 2 and Front Controller

        Model 1 Architecture:
        Earlier all the work was done in the JSP.
        So, the jsp:-
        - handle view logic
        - handle flow logic
        - talked to the database
        - got the data
        - combined the data with the view and sent it to the user
        Therefore, jsp files got very complex

        Model 2 Architecture:
        Here, we had very clear separation of concerns
        - Model -> Data to generate the view
        - View -> Show information to the user
        - Controller(also called Servlets) -> Controls the flow
        Therefore, this was comparatively simpler to maintain.
        But, we still had concerns:
        - Where to implement the logic which is common to all Controllers(for example, authentication logic)?

        Front Controller Pattern(also called Model 2 Architecture with a Front controller):
        So, all the request from the browser go to just one Controller.
        Therefore, you can implement all the Security and authentication at one place.
        Once, the Security is taken care of you can delegate the control to the required Controller.


        Spring MVC Front Controller - Dispatcher Servlet:
        All the requests are received by the Dispatcher Servlet.

        This is a simple representation of a request received by the LoginController /check-login URL:-
        A. Receives HTTP Request
        B. Process HTTP Request
            B1. Identifies the correct Controller method
                - Based on the request URL
            B2. Executes Controller method
                - Puts data into model
                - Returns view name(in this case, "check-login")
            B3. Identifies correct view(Using ViewResolver)
                - "/WEB-INF/jsp" and ".jsp" from application.properties
                - "check-login" as view name returned by the Controller
                - Merges both to get "/WEB-INF/jsp" + "login" + ".jsp" => "/WEB-INF/jsp/check-login.jsp"
            B4. Executes the view
        C. Returns HTTP Response
         */
        logger.debug("Request param is {}", name);
        model.put("name", name); // Once you add something to the model the view(in this case, jsp) can automatically have access to it
        return "check-login";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String goToLoginPage() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String goToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) { // For query param and form data we can use @RequestParam
        if (authenticationService.authenticate(name, password)) {
            model.put("name", name);
            return "welcome";
        }
        model.put("errorMessage", "Invalid Credentials! Please try again");
        return "login";
    }
}
