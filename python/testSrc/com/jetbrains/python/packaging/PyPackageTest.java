package com.jetbrains.python.packaging;

import junit.framework.TestCase;
import org.junit.Assert;

import java.util.Collections;

/**
 * @author Ilya.Kazakevich
 */
public class PyPackageTest extends TestCase {

  // http://legacy.python.org/dev/peps/pep-0386/
  public void testIsAtLeastVersionNormal() throws Exception {
    final PyPackage pyPackage = new PyPackage("somePackage", "1.2.3.4", null, Collections.<PyRequirement>emptyList());
    Assert.assertTrue("Failed to check normal version", pyPackage.isVersionAtLeast(1, 2));
    Assert.assertTrue("Failed to check normal version", pyPackage.isVersionAtLeast(1, 2, 3));
    Assert.assertTrue("Failed to check normal version", pyPackage.isVersionAtLeast(1));
    Assert.assertTrue("Failed to check normal version", pyPackage.isVersionAtLeast(1, 2, 3, 4));

    Assert.assertFalse("Failed to check normal version", pyPackage.isVersionAtLeast(1, 2, 3, 4, 5));
    Assert.assertFalse("Failed to check normal version", pyPackage.isVersionAtLeast(2));
    Assert.assertFalse("Failed to check normal version", pyPackage.isVersionAtLeast(2, 2));
    Assert.assertFalse("Failed to check normal version", pyPackage.isVersionAtLeast(1, 9, 1));
    Assert.assertFalse("Failed to check normal version", pyPackage.isVersionAtLeast(1, 2, 3, 5));
  }


  public void testIsAtLeastVersionBeta() throws Exception {
    final PyPackage pyPackage = new PyPackage("somePackage", "0.5a3", null, Collections.<PyRequirement>emptyList());
    Assert.assertTrue("Failed to check normal version", pyPackage.isVersionAtLeast(0, 4));
    Assert.assertTrue("Failed to check normal version", pyPackage.isVersionAtLeast(0, 5));

    Assert.assertFalse("Failed to check normal version", pyPackage.isVersionAtLeast(0, 6));
    Assert.assertFalse("Failed to check normal version", pyPackage.isVersionAtLeast(0, 5, 1));
    Assert.assertFalse("Failed to check normal version", pyPackage.isVersionAtLeast(1));
  }
}
