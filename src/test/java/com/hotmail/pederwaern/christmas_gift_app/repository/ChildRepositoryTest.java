package com.hotmail.pederwaern.christmas_gift_app.repository;

import com.hotmail.pederwaern.christmas_gift_app.DockerBackendApplication;
import com.hotmail.pederwaern.christmas_gift_app.domain.Adult;
import com.hotmail.pederwaern.christmas_gift_app.domain.Child;
import org.junit.Before;
import org.junit.Ignore;
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
public class ChildRepositoryTest {

	@Before
	public void setUp() throws Exception {
		entityManager.clear();
	}

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ChildRepository childRepository;

	@Autowired
	private AdultRepository adultRepository;

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

	@Ignore
	@Test
	public void removeChildWorksIfAssociatedToAdult() {
		Child child = new Child();
		child.setFirstName("Ellen");
		child.setFirstName("Waern");
		entityManager.persist(child);
		entityManager.flush();

		Adult adult = new Adult();
		adult.setFirstName("Emma");
		adult.setLastName("Waern");
		adult.setEmail("pederwaern@hotmail.com");
		adult.getChildren().add(child);
		entityManager.persist(adult);
		entityManager.flush();

		entityManager.remove(child);
		entityManager.flush();

		assertThat(adultRepository.findByFirstName("Emma").getChildren().size(), is(0));
	}


}
