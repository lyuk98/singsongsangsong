package com.ssafy.singsongsangsong.job;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.ssafy.singsongsangsong.entity.Play;

public class PlayWriter implements ItemWriter<Play> {
	
	private MongoOperations mongoOperations;

    public PlayWriter(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

	@Override
	public void write(Chunk<? extends Play> chunk) throws Exception {
		for (Play item : chunk) {
            int temp = mongoOperations.findOne(Query.query(Criteria.where("part").is("all")), Play.class).getAge() + 1;
            Query query = new Query(Criteria.where("trend").is("genre")); // 필요에 따라 적절한 필드와 값으로 변경
            Update update = new Update().set(item.getGenre(), temp + 1); // 필요에 따라 적절한 업데이트할 필드와 값을 설정
            mongoOperations.updateFirst(query, update, Play.class);
        }
		
	}

}
