package GameOfLife;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({})
public class AllTests {
	public static void main(String[] args) throws Exception {                    
	       JUnitCore.main(
	         "GameOfLife.GameOfLifeTest");            
	}
}
