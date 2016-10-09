/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.arrays;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class GenericArraysTest {

	@Test public void testMake() {
		Assert.assertArrayEquals(new Object[] {}, GenericArrays.make());
		Assert.assertArrayEquals(new Byte[] {
			(byte) 0x01, (byte) 0x02, (byte) 0x03
		}, GenericArrays.make((byte) 0x01, (byte) 0x02, (byte) 0x03));
	}

	@Test public void testFromList() {
		Assert.assertArrayEquals(new Integer[] { 1, 2 }, GenericArrays.fromList(new ArrayList<Integer>() {
			{
				add(1);
				add(2);
			}
		}));
		final Integer[] tmp = GenericArrays.fromList(new ArrayList<Integer>() {
			{
				add(1);
				add(2);
				add(3);
			}
		});
		Assert.assertArrayEquals(new Integer[] { 1, 2, 3 }, tmp);
		Assert.assertArrayEquals(null, GenericArrays.fromList(new ArrayList<Integer>()));
		Assert.assertArrayEquals(null, GenericArrays.fromList(null));
	}

	@Test public void testHasContent() {
		Assert.assertEquals(true, GenericArrays.hasContent(GenericArrays.make(1, 2, 3)));
		Assert.assertEquals(false, GenericArrays.hasContent(GenericArrays.make()));
		Assert.assertEquals(false, GenericArrays.hasContent(null));
	}

	@Test public void testNullFree() {
		Assert.assertEquals(true, GenericArrays.nullFree(GenericArrays.make(1, 2, 3)));
		Assert.assertEquals(false, GenericArrays.nullFree(GenericArrays.make(1, null, 3)));
		Assert.assertEquals(false, GenericArrays.nullFree(null));
	}

	@Test public void testContains() {
		Assert.assertEquals(true, GenericArrays.contains(GenericArrays.make(1, 2, 3), 1));
		Assert.assertEquals(true, GenericArrays.contains(GenericArrays.make(1, 2, 3), 2));
		Assert.assertEquals(true, GenericArrays.contains(GenericArrays.make(1, 2, 3), 3));
		Assert.assertEquals(false, GenericArrays.contains(GenericArrays.make(1, 2, 3), 4));
		Assert.assertEquals(false, GenericArrays.contains(GenericArrays.make(1, 2, 3), null));
		Assert.assertEquals(false, GenericArrays.contains(null, 4));
	}

	@Test public void testGet() {
		Assert.assertEquals("def", GenericArrays.get(GenericArrays.make("abc", "def"), 1));
		Assert.assertEquals(null, GenericArrays.get(GenericArrays.make(), 0));
	}

	@Test public void testFirst() {
		Assert.assertEquals(Boolean.TRUE, GenericArrays.first(GenericArrays.make(true, false)));
		final int x = GenericArrays.first(GenericArrays.make(1, 2, 3));
		Assert.assertEquals(1, x);
	}

	@Test public void testLast() {
		Assert.assertEquals(Boolean.FALSE, GenericArrays.last(GenericArrays.make(true, false)));
		final int x = GenericArrays.last(GenericArrays.make(1, 2, 3));
		Assert.assertEquals(3, x);
	}

	@Test public void testFirstAndLast() {
		Assert.assertArrayEquals(new Integer[] { 1, 3 }, GenericArrays.firstAndLast(GenericArrays.make(1, 2, 3)));
		final Object a = new Object();
		final Object b = new Object();
		final Object c = null;
		final Object d = null;
		Assert.assertArrayEquals(new Object[] { a, d }, GenericArrays.firstAndLast(GenericArrays.make(a, b, c, d)));
		Assert.assertArrayEquals(new Integer[] { 1, 1 }, GenericArrays.firstAndLast(GenericArrays.make(1)));
	}

	@Test public void testChopBeginning() {
		Assert.assertArrayEquals(new Integer[] { 2, 3 }, GenericArrays.chopBeginning(GenericArrays.make(1, 2, 3), 1));
		Assert.assertArrayEquals(new Integer[] { 3, 4 }, GenericArrays.chopBeginning(GenericArrays.make(1, 2, 3, 4), 2));
		Assert.assertArrayEquals(null, GenericArrays.chopBeginning(GenericArrays.make(1, 2, 3, 4), 4));
		Assert.assertArrayEquals(null, GenericArrays.chopBeginning(GenericArrays.make(1, 2, 3, 4), 5));
	}

	@Test public void testChopEnding() {
		Assert.assertArrayEquals(new Integer[] { 1, 2 }, GenericArrays.chopEnding(GenericArrays.make(1, 2, 3), 1));
		Assert.assertArrayEquals(new Integer[] { 1, 2 }, GenericArrays.chopEnding(GenericArrays.make(1, 2, 3, 4), 2));
		Assert.assertArrayEquals(null, GenericArrays.chopEnding(GenericArrays.make(1, 2, 3, 4), 4));
		Assert.assertArrayEquals(null, GenericArrays.chopEnding(GenericArrays.make(1, 2, 3, 4), 5));
	}

	@Test public void testChopBoth() {
		Assert.assertArrayEquals(new Integer[] { 3, 4 }, GenericArrays.chopBoth(GenericArrays.make(1, 2, 3, 4, 5, 6), 2));
		Assert.assertArrayEquals(new Integer[] { 2 }, GenericArrays.chopBoth(GenericArrays.make(1, 2, 3), 1));
		Assert.assertArrayEquals(null, GenericArrays.chopBoth(GenericArrays.make(1, 2, 3, 4), 2));
		Assert.assertArrayEquals(null, GenericArrays.chopBoth(GenericArrays.make(1, 2, 3, 4), 3));
	}

	@Test public void testConcat() {
		final Integer[] tmp = GenericArrays.concat(GenericArrays.make(1, 2), GenericArrays.make(3, 4));
		Assert.assertArrayEquals(new Integer[] { 1, 2, 3, 4 }, tmp);
		Assert.assertArrayEquals(new Integer[] { 1, 2, 3, 4 }, GenericArrays.concat(new Integer[] { 1, 2 }, new Integer[] { 3, 4 }));
		Assert.assertArrayEquals(null, GenericArrays.concat());
	}

}