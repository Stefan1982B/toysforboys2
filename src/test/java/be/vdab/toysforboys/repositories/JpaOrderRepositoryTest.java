package be.vdab.toysforboys.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.toysforboys.entities.Order;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Sql("/insertCountry.sql")
@Sql("/insertCustomer.sql")
@Sql("/insertProductline.sql")
@Sql("/insertProduct.sql")
@Sql("/insertOrder.sql")

@Import(JpaOrderRepository.class)
public class JpaOrderRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private JpaOrderRepository repository;
	
	private long idVanTestOrder() {
		return super.jdbcTemplate.queryForObject("select id from orders where comments = 'testComment'", Long.class);
	}

	@Test
	public void read() {
		Order order = repository.read(idVanTestOrder()).get();
		assertEquals("testComment", order.getComments());
	}

}
