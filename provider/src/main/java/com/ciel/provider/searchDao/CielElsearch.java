package com.ciel.provider.searchDao;

import com.ciel.provider.config.Ciel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CielElsearch extends ElasticsearchRepository<Ciel,Integer> {

    List<Ciel> findByNameLike(String name);

}
