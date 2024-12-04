package vaka.daily.tgbot.core.update;

import lombok.Getter;
import lombok.Setter;

public enum MessageType {
    TEXT("Text"), COMMAND("Command"), STICKER("Sticker"), ANIMATION("Animation"), PHOTO("Photo"), UNDEFINED(
            "Undefined");

    @Getter
    private final String name;

    MessageType(String name) {
        this.name = name;
    }
}
