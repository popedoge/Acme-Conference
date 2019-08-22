package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.TopicRepository;
import domain.Topic;

@Service
@Transactional
public class TopicService {

	@Autowired
	private TopicRepository topicRepo;

	public Topic create() {
		return new Topic();
	}

	public Topic save(Topic topic) {
		return this.topicRepo.save(topic);
	}

	public void delete(int id) {
		this.topicRepo.delete(id);
	}

	public List<Topic> findAll() {
		return this.topicRepo.findAll();
	}

	public Topic findById(int id) {
		return this.topicRepo.findOne(id);
	}
}
