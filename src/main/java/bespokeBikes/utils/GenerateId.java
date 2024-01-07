package bespokeBikes.utils;

import java.util.UUID;

public class GenerateId {
    public static String getId() {
        UUID num = UUID.randomUUID();
        return num.toString().substring(0,6);
    }
}
