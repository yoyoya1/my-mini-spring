package cn.py.springframework.beans;

public class BeansException extends RuntimeException{
    private final String info;
    public BeansException(String instantiationOfBeanFailed) {
        this.info = instantiationOfBeanFailed;
    }

    @Override
    public String toString() {
        return info;
    }
}
