package io.sunshower.aidenticon;

import org.junit.jupiter.api.Test;

import static io.sunshower.aidenticon.Utilities.substr;
import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

  @Test
  void ensureSubstrWorks1() {
    var str = "Mozilla";
    assertEquals("M", substr(str, 0, 1));
  }

  @Test
  void ensureSubstrWorks2() {
    var str = "Mozilla";
    assertEquals("", substr(str, 1, 0));
  }

  @Test
  void ensureNegativeIndexWorks() {
    var str = "Mozilla";
    assertEquals("a", substr(str, -1, 1));
  }

  @Test
  void ensureNegativeIndex3Works() {
    var str = "Mozilla";
    assertEquals("lla", substr(str, -3));
  }


  @Test
  void ensureNegativeIndex2Works() {
    var str = "Mozilla";
    assertEquals("", substr(str, 1, -1));
  }

}
