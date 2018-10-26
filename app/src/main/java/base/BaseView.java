package base;


public interface BaseView<T> {
    void success(T t);

    void failed(Exception e);
}
