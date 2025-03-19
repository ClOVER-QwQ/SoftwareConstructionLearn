package chapter03;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class Construction06_BinaryOperationTest {

	private Construction06_BinaryOperation bo = new Construction06_BinaryOperation();
	
	@BeforeEach
	void setUp() throws Exception {
		bo=new Construction06_BinaryOperation();
	}

	@Test
    public void test1() {
	
		assertEquals(100, bo.construct(70,30,'+'));
		
	}
	
	@Test
    public void test2() {
	
		assertEquals(99, bo.construct(100,1,'-'));
		
	}
	
	@Test
    public void test3() {
	
		assertEquals(100, bo.construct(100,0,'-'));
		
	}
}
