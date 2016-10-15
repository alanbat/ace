/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.text;

import java.util.ArrayList;
import java.util.ListIterator;
import org.junit.Assert;
import org.junit.Test;

public class StringListTest {

	@Test public void testEnsureCapacity() {
		final StringList sl = new StringList("1", "2", "3");
		sl.ensureCapacity(200);
		Assert.assertTrue(sl.capacity() > 199);
	}

	@Test public void testCapacity() {
		Assert.assertTrue(new StringList().capacity() > -1);
	}

	@Test public void testSize() {
		Assert.assertEquals(3, new StringList("1", "2", "3").size());
	}

	@Test public void testClear() {
		Assert.assertTrue(new StringList("1", "2", "3").clear().isEmpty());
	}

	@Test public void testIsEmpty() {
		Assert.assertTrue(new StringList().isEmpty());
	}

	@Test public void testToArray() {
		Assert.assertArrayEquals(new String[] { "1", "2", "3" }, new StringList("1", "2", "3").toArray());
	}

	@Test public void testAdd() {
		final StringList sl = new StringList("1", "2", "3");
		sl.add("4", "5", "6");
		Assert.assertArrayEquals(new String[] { "1", "2", "3", "4", "5", "6" }, sl.toArray());
	}

	@Test public void testInclude() {
		final StringList sl = new StringList("1", "2", "3");
		sl.include(new ArrayList() {{ add("4"); add("5"); add("6"); }});
		Assert.assertArrayEquals(new String[] { "1", "2", "3", "4", "5", "6" }, sl.toArray());
	}

	@Test public void testExclude() {
		final StringList sl = new StringList("1", "2", "3");
		sl.exclude(new ArrayList() {{ add("1"); add("3"); }});
		Assert.assertArrayEquals(new String[] { "2" }, sl.toArray());
	}

	@Test public void testInsert() {
		final StringList sl = new StringList("1", "2", "3");
		sl.insert(1, "4", "5", "6");
		Assert.assertArrayEquals(new String[] { "1", "4", "5", "6", "2", "3" }, sl.toArray());
	}

	@Test public void testGet() {
		Assert.assertEquals("2", new StringList("1", "2", "3").get(1));
	}

	@Test public void testRemove() {
		final StringList sl = new StringList("1", "2", "3");
		Assert.assertEquals("2", sl.remove(1));
		Assert.assertArrayEquals(new String[] { "1", "3" }, sl.toArray());
	}

	@Test public void testSet() {
		final StringList sl = new StringList("1", "2", "3");
		Assert.assertNull(sl.set(-1, "test"));
		Assert.assertEquals("2", sl.set(1, "test"));
		Assert.assertArrayEquals(new String[] { "1", "test", "3" }, sl.toArray());
	}

	@Test public void testContains() {
		Assert.assertTrue(new StringList("1", "2", "3").contains("1"));
		Assert.assertFalse(new StringList("1", "2", "3").contains("4"));
	}

	@Test public void testIndexOf() {
		Assert.assertEquals(0, new StringList("1", "2", "3").indexOf("1"));
		Assert.assertEquals(-1, new StringList("1", "2", "3").indexOf("4"));
	}

	@Test public void testLastIndexOf() {
		Assert.assertEquals(2, new StringList("1", "2", "3").lastIndexOf("3"));
		Assert.assertEquals(4, new StringList("1", "2", "3", "0", "3").lastIndexOf("3"));
		Assert.assertEquals(-1, new StringList("1", "2", "3").lastIndexOf("4"));
	}

	@Test public void testIterator() {
		for (final String s : new StringList("1", "2", "3")) {
			Assert.assertNotNull(s);
		}
	}

	@Test public void testListIterator_0args() {
		final ListIterator<String> li = new StringList("1", "2", "3").listIterator();
		while (li.hasNext()) {
			Assert.assertNotNull(li.next());
		}
	}

	@Test public void testListIterator_int() {
		final ListIterator<String> li = new StringList("1", "2", "3").listIterator(1);
		while (li.hasNext()) {
			Assert.assertNotNull(li.next());
		}
	}

	@Test public void testFromString() {
		Assert.assertArrayEquals(new String[] { "1", "2", "3" }, new StringList().fromString("1\n2\n3").toArray());
	}

	@Test public void testToString() {
		Assert.assertEquals("1\n2\n3", new StringList("1", "2", "3").toString());
	}

}
