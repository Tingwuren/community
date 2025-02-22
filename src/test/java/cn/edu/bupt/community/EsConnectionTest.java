package cn.edu.bupt.community;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class EsConnectionTest {

    @Autowired
    private RestHighLevelClient client;

    @Test
    void testConnection() throws Exception {
        boolean pingSuccess = client.ping(RequestOptions.DEFAULT);
        assertTrue(pingSuccess);
    }
}
