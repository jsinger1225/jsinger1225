package jbs;

import org.junit.Test;

public class FooTest {

    public static void main( String... args ) {
        System.out.println( "Foo main ..." );
    }

    @Test
    public void testfoo() {
        Foo foo = new Foo();
        foo.foo();
    }
}
