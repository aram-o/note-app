package com.aram.etlservice.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author aram
 */
@Slf4j
@Component
public class JdbcPaginationJobLauncher {
    
    private final Job job;
    private final JobLauncher jobLauncher;

    @Autowired
    public JdbcPaginationJobLauncher(@Qualifier("jdbcPaginationJob") Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
    }

    @Scheduled(cron = "0 0 15 * * *")
    public void runSpringBatchExampleJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("JDBC pagination example job was started");

        jobLauncher.run(job, newExecution());

        log.info("JDBC pagination example job was stopped");
    }

    private JobParameters newExecution() {
        Map<String, JobParameter> parameters = new HashMap<>();

        JobParameter parameter = new JobParameter(new Date());
        parameters.put("currentTime", parameter);

        return new JobParameters(parameters);
    }
}
