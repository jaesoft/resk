package io.resk.message.command.vm;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class AndroidNotification extends Notification {
	String icon;
	String color;
	String sound;
}
