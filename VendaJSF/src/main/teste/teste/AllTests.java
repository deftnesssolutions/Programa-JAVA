package teste;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClienteTest.class, ProdutoTest.class, ProdutoTestSemMVC.class,
		VendaTest.class })
public class AllTests
{

}
