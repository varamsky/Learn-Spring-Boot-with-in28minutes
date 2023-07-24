package com.in28minutes.learnspringframework;

import java.io.Serializable;

class POJO { // every java object is a POJO(Plain old Java Object)
    private String text;
    private int number;

    public String toString() { // any method is sufficient(not necessary to use toString)
        return text + ":" + number;
    }
}

class JavaBean implements Serializable { // Years ago EJB(Enterprise Java Beans) introduced the concept of JavaBeans
    /*
    A JavaBean needs to have
    - a public default constructor,
    - private instance variables and
    - getters and setters for the instance variables
    - needs to be serializable(should implement java.io.Serializable)
    */

    /*
    Serializable means the object is persistent across devices
    Java objects are converted to Byte Stream
    As the Byte Stream are system independent
    So, an object with its properties and state can be retrieved from the ByteStream
    For this we don't have to do anything
    just implement the java.io.Serializable class
    it marks the class to be serialized by Java
     */
    public JavaBean() {
    }

    private String text;
    private int number;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

/**
 * Any java object that is managed by Spring is a SpringBean
 * Spring uses IOC Container(Bean Factory or Application Context) to manage these objects
 *
 * Now, we only need to know about POJO and SpringBean because JavaBean(EJB) are no longer in popular use
 */

public class JavaBeanVsSpringBeanVsPOJO {

    public static void main(String[] args) {
        POJO pojo = new POJO();
        System.out.println(pojo);
    }
}
