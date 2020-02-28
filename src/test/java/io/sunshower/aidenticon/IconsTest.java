package io.sunshower.aidenticon;

import io.sunshower.aidenticon.svg.SvgElement;
import io.sunshower.aidenticon.svg.SvgRenderer;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.dsig.DigestMethod;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class IconsTest {

    @Test
    void ensureRendererWorksCorrectly() throws NoSuchAlgorithmException {
        var renderer = new SvgRenderer(new SvgElement(), 100);
        var digest = MessageDigest.getInstance(DigestMethod.SHA1);
        var hash = digest.digest("adsfasdfqwetqwert".getBytes());

//        Icons.generate(renderer, hash, 0, 0, 100, );
    }

}