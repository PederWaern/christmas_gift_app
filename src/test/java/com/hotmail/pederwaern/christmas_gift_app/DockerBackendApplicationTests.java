package com.hotmail.pederwaern.christmas_gift_app;

import com.hotmail.pederwaern.christmas_gift_app.domain.Child;
import com.hotmail.pederwaern.christmas_gift_app.repository.ChildRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = DockerBackendApplication.class)
public class DockerBackendApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ChildRepository childRepository;

	@Test
	public void contextLoads() {

	}

	@Test
	public void testChildRepo() {
		Child child = new Child();
		child.setFirstName("Peder");
		child.setLastName("Waern");
		entityManager.persist(child);
		entityManager.flush();

		Child foundChild = childRepository.findByFirstName(child.getFirstName());
		assertThat(foundChild.getFirstName(), is(child.getFirstName()));
	}

}
