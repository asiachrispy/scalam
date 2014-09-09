/**
 * Description : some words
 * Author: chris
 * Date: 2014/8/1
 */
public class T {
    private static T ourInstance = new T();

    public static T getInstance() {
        return ourInstance;
    }

    private T() {
    }
}
