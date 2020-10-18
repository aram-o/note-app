package com.aram.etlservice.batch;

import com.aram.etlservice.dto.NoteDTO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author aram
 */
@Configuration
public class JDBCPaginationJobConfig {
    
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public ItemReader<NoteDTO> jdbcPaginationItemReader(PagingQueryProvider queryProvider) {
        return new JdbcPagingItemReaderBuilder<NoteDTO>()
                .name("pagingItemReader")
                .dataSource(dataSource)
                .pageSize(1000)
                .queryProvider(queryProvider)
                .rowMapper(new BeanPropertyRowMapper<>(NoteDTO.class))
                .build();
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean queryProvider() {
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();

        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("SELECT id, user_email, title, note, create_date, update_date");
        queryProvider.setFromClause("FROM note_note");
        queryProvider.setSortKeys(sortByEmailAddressAsc());

        return queryProvider;
    }

    private Map<String, Order> sortByEmailAddressAsc() {
        Map<String, Order> sortConfiguration = new HashMap<>();
        sortConfiguration.put("id", Order.ASCENDING);
        return sortConfiguration;
    }
    
    @Bean
    public JsonFileItemWriter<NoteDTO> jsonItemWriter(
        @Value("${file.outpup}") String output
    ) throws IOException {
        JsonFileItemWriterBuilder<NoteDTO> builder = new JsonFileItemWriterBuilder<>();
        JacksonJsonObjectMarshaller<NoteDTO> marshaller = new JacksonJsonObjectMarshaller<>();
        return builder
          .name("noteItemWriter")
          .jsonObjectMarshaller(marshaller)
          .resource(new FileSystemResource(output))
          .build();
    }

    /**
     * Creates a bean that represents the only step of our batch job.
     * @param reader
     * @param writer
     * @param stepBuilderFactory
     * @return
     */
    @Bean
    public Step jdbcPaginationStep(
        @Qualifier("jdbcPaginationItemReader") ItemReader<NoteDTO> reader,
        @Qualifier("jsonItemWriter") ItemWriter<NoteDTO> writer,
        StepBuilderFactory stepBuilderFactory
    ) {
        return stepBuilderFactory.get("jdbcPaginationStep")
                .<NoteDTO, NoteDTO>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    /**
     * Creates a bean that represents our example batch job.
     * @param exampleJobStep
     * @param jobBuilderFactory
     * @return
     */
    @Bean
    public Job jdbcPaginationJob(@Qualifier("jdbcPaginationStep") Step exampleJobStep,
                                 JobBuilderFactory jobBuilderFactory) {
        Job databaseCursorJob = jobBuilderFactory.get("jdbcPaginationJob")
                .incrementer(new RunIdIncrementer())
                .flow(exampleJobStep)
                .end()
                .build();
        return databaseCursorJob;
    }
    
}
