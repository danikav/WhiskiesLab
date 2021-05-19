package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canFindWhiskiesByYear(){
		List<Whisky> foundWhiskies = whiskyRepository.findWhiskiesByYear(2018);
		assertEquals(6, foundWhiskies.size());
	}
	@Test
	public void canFindDistilleryByRegion(){
		List<Distillery> foundDistilleries = distilleryRepository.findDistilleryByRegion("Highland");
		assertEquals(3, foundDistilleries.size());
	}

	@Test
	public void canFindWhiskiesByDistilleryAndAge(){
		Distillery distillery = distilleryRepository.getOne(1L);
		List<Whisky> found = whiskyRepository.findWhiskyByDistilleryAndAge(distillery, 15);
		assertEquals(1, found.size());
	}
}
