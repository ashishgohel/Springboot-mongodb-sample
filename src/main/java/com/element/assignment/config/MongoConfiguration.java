package com.element.assignment.config;

import com.element.assignment.repository.OrderRepository;
import com.element.assignment.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;

@EnableMongoRepositories(basePackageClasses = {OrderRepository.class, TripRepository.class})
@Configuration
public class MongoConfiguration {


    @Autowired
    private MongoTemplate template;

    @PostConstruct
    public void init(){
        template.indexOps("trip").ensureIndex(new Index("tripName", Sort.Direction.ASC).unique());
        template.indexOps("user").ensureIndex(new Index("email", Sort.Direction.ASC).unique());
        template.indexOps("order").ensureIndex(new Index("userEmailAddress", Sort.Direction.ASC));
    }

}
