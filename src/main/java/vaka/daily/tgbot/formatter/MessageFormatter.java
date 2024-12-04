package vaka.daily.tgbot.formatter;

public interface MessageFormatter<T> {
    String format(T object);
}
