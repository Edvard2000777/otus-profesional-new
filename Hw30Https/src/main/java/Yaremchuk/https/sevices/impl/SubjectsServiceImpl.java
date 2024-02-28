package Yaremchuk.https.sevices.impl;

import Yaremchuk.https.entities.Subject;
import Yaremchuk.https.sevices.Approval;
import Yaremchuk.https.dtos.CreateOrUpdateSubjectDtoRq;
import Yaremchuk.https.sevices.SubjectsService;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import Yaremchuk.repositories.SubjectsRepository;


import javax.net.ssl.SSLContext;
import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectsServiceImpl implements SubjectsService {
    private final SubjectsRepository subjectsRepository;
    private final RestTemplateBuilder templateBuilder;
    @Value("${server.truststore.store}")
    private Resource trustStore;

    @Value("${server.truststore.password}")
    private String trustStorePassword;

    @Autowired
    public SubjectsServiceImpl(SubjectsRepository subjectsRepository, RestTemplateBuilder templateBuilder) {
        this.subjectsRepository = subjectsRepository;
        this.templateBuilder = templateBuilder;
    }

    @Override
    public Optional<Subject> findById(Long id) {
        return subjectsRepository.findById(id);
    }

    @Override
    public List<Subject> findAll() {
        return subjectsRepository.findAll();
    }

    @Override
    public void createNewSubject(CreateOrUpdateSubjectDtoRq createOrUpdateSubjectDtoRq) throws Exception {
        if (checkApproval(createOrUpdateSubjectDtoRq.getName())) {
            Subject newCategory = new Subject(createOrUpdateSubjectDtoRq.getId(), createOrUpdateSubjectDtoRq.getName(), createOrUpdateSubjectDtoRq.getDescription());
            subjectsRepository.save(newCategory);
        }
    }

    private boolean checkApproval(String subjectName) throws Exception {
        File truststoreFile = new File(".", trustStore.getFilename());

        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(truststoreFile, trustStorePassword.toCharArray())
                .build();

        SSLConnectionSocketFactory sslConFactory = new SSLConnectionSocketFactory(sslContext);

        HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslConFactory).build();

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        RestTemplate template = new RestTemplate(requestFactory);

        ResponseEntity<Approval> result =
                template.getForEntity("https://localhost:8181/approval?name=" + subjectName, Approval.class);
        return result.getBody().approved();
    }
}
