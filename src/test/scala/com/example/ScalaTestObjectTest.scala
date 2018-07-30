package com.example

import com.example.ScalaTestObject.method
import org.junit.Test

class ScalaTestObjectTest {

  @Test
  def testMethod() {
    assert(method == 5)
  }

}
