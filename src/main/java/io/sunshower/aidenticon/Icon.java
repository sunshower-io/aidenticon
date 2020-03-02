package io.sunshower.aidenticon;

public interface Icon {
    byte[] getData();

    /**
     *
     * @return the inline, base64 URL of this icon
     */
    String toURL();
}
