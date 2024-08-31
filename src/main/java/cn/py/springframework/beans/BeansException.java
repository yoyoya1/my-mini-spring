package cn.py.springframework.beans;

public class BeansException extends Throwable{
    private final String info;
    public BeansException(String instantiationOfBeanFailed) {
        this.info = instantiationOfBeanFailed;
    }
}
