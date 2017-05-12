package com.gmail.kolesnyk.zakhar.post;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum MEDIA_TYPE {
    IMAGE, AUDIO, VIDEO;
    private static Set<String> imageExtends = new HashSet<>(Arrays.asList("jpeg", "jpg", "gif", "bmp", "png"));
    private static Set<String> audioExtends = new HashSet<>(Collections.singletonList("mp3"));
    private static Set<String> videoExtends = new HashSet<>(Arrays.asList("mp4", "flv", "m4v", "3gp"));
    private static Set<String> allExtends;

    static {
        allExtends = new HashSet<>();
        allExtends.addAll(imageExtends);
        allExtends.addAll(audioExtends);
        allExtends.addAll(videoExtends);
    }

    public static MEDIA_TYPE getType(String ext) {
        if (imageExtends.contains(ext.toLowerCase())) {
            return IMAGE;
        }
        if (audioExtends.contains(ext.toLowerCase())) {
            return AUDIO;
        }
        if (videoExtends.contains(ext.toLowerCase())) {
            return VIDEO;
        }
        throw new IllegalArgumentException("Unknown format");
    }

    public static Set<String> getAllExtends() {
        return allExtends;
    }
}
