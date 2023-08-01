package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;

@RestController
public class HelloWorldController {
    /*
    Strategy interface for resolving messages, with support for the parameterization and internationalization of such messages.
     */
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        /*
        Here are a few things to understand:

        How are our requests handled?
        - Spring uses DispatcherServlet to implement the Front Controller pattern
        - Therefore, all requests first go to the DispatcherServlet as it is matched to the root(/) of the application
        - You can find this in your debug-level logs :- Mapping servlets: dispatcherServlet urls=[/]
        - How is the DispatcherServlet configured?:- It is autoconfigured by DispatcherServletAutoConfiguration class.

        How does HelloWorldBean get converted to JSON?
        - @ResponseBody + JacksonHttpMessageConvertersConfiguration
        - The @RestController has the @ResponseBody annotation which sends the message from the method as-is.
        - JacksonHttpMessageConvertersConfiguration is autoconfigured to convert any data/Object/Bean to JSON.

        Who is configuring the error mapping?
        - Auto Configuration of ErrorMvcAutoConfiguration class.
        - Have a look at the ErrorMvcAutoConfiguration class to find the White Error Label HTML code.
         */
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() throws UnsupportedEncodingException {
        // 'hi' -> Hindi(सुप्रभात)
        // 'en' -> English(Good Morning)
        // 'nl' -> Dutch(Goedemorgen)
        // 'fr' -> French(Bonjour)
        // 'ru' -> Russian(доброе утро)
        /*
        1. Define these values somewhere
            - We define them in the resources/messages_hi.properties files with the specific locale name like _hi for hindi or _fr for french so on.
        2. Write code to pick them up
            - Then we use the code below to pick them up.
         */
        Locale locale = LocaleContextHolder.getLocale(); // This gets the application-language header value for the current request or the system default otherwise.
        // For language scripts other latin, make sure to use the UTF-8 encoding for the specific properties file
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
    }
}
