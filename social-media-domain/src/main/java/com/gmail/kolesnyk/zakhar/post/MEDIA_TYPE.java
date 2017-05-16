package com.gmail.kolesnyk.zakhar.post;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * The {@code MEDIA_TYPE} enum class that contains types of media with supported extends
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.post.Post
 * @since JDK1.8
 */
public enum MEDIA_TYPE {

    /**
     * type media: Image
     */
    IMAGE,

    /**
     * type media: Audio
     */
    AUDIO,

    /**
     * type media: Video
     */
    VIDEO;
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

    /**
     * method allowed to get type media {@link MEDIA_TYPE} by extends
     *
     * @param ext extends
     * @return {@link MEDIA_TYPE} correspond media type
     */
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


    /**
     * method allowed to get all supported types of media {@link Set<String>}
     *
     * @return {@link Set<String>} supported media extends
     */
    public static Set<String> getAllExtends() {
        return allExtends;
    }
}
