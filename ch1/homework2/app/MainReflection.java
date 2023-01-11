package gr.aueb.cf.ch1.homework2.app;


import gr.aueb.cf.ch1.homework2.model.User;
import gr.aueb.cf.ch1.homework2.model.UserCredentials;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Creates two instances of User and UserCredentials with Reflection API and print them.
 */
public class MainReflection {

    public static void main(String[] args) {
        try {
            Class<?> clazz1 = Class.forName("gr.aueb.cf.ch1.homework2.model.User");
            Class<?> clazz2 = Class.forName("gr.aueb.cf.ch1.homework2.model.UserCredentials");

            Constructor<?> intCtr1 = clazz1.getConstructor(Long.class, String.class, String.class);
            Constructor<?> intCtr2 = clazz2.getConstructor(Long.class, String.class, String.class);

            User user = (User) intCtr1.newInstance(2L, "george", "chou");
            UserCredentials userCr = (UserCredentials) intCtr2.newInstance(2L, "aaa", "bbb");

            Method convertToStringUser = clazz1.getMethod("convertToString");
            System.out.println(convertToStringUser.invoke(user));
            Method convertToStringUserCr = clazz2.getMethod("convertToString");
            System.out.println(convertToStringUserCr.invoke(userCr));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
