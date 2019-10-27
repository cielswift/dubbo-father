package com.ciel.provider.controller;


import com.ang.customstart.config.KillHuman;
import com.ang.customstart.config.Xpx;
import com.ciel.provider.common.Other;
import com.ciel.provider.condit.Other5;
import com.ciel.provider.config.Ciel;
import com.ciel.provider.config.CielSwift;
import com.ciel.provider.searchDao.CielElsearch;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/appSearch")
public class AppElSearch {

    @Autowired
    private JestClient jestClient;

    @Autowired
    private CielElsearch cielElsearch;

    @Autowired
    private Ciel ciel;

    @Autowired
    private CielSwift xiapeixin;

    @Autowired
    private Other other;

    @Autowired
    private Other5 other5;

    @Autowired
    private KillHuman killHuman;

    @Autowired
    private Xpx xpx;

    @RequestMapping("/app3")
    public boolean searh() throws IOException {
       ciel.setId(9);

       //127.0.0.1:9200/xiatian/xia/9
        Index build = new Index.Builder(ciel).index("xiatian").type("xia").build(); //构建索引
        jestClient.execute(build);

        return true;
    }

    @RequestMapping("/app4")
    public boolean rtg() throws IOException {
        String string = "{\n" +
                "    \"query\": {\n" +
                "        \"match\": {\n" +
                "            \"name\": \"xiapeixin\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Search builder = new Search.Builder(string).addIndex("xiatian").addType("xia").build();

        SearchResult execute = jestClient.execute(builder);
        String jsonString = execute.getJsonString();

        System.out.println(jsonString);
        return true;
    }

    @RequestMapping("/app5")
    public boolean template() throws IOException {
        ciel.setId(2);
        cielElsearch.index(ciel); //保存

        List<Ciel> xia = cielElsearch.findByNameLike("xia"); //自定义方法查找

        return true;
    }

}
