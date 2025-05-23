package io.camunda.demo.process_payments;

import java.util.Map;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.client.ZeebeClient;

@SpringBootApplication
@Deployment(resources = "classpath:process-payments.bpmn")
public class ProcessPaymentsApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessPaymentsApplication.class);

	@Autowired
	private ZeebeClient zeebeClient;

	public static void main(String[] args) {
		SpringApplication.run(ProcessPaymentsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		var bpmnProcessId = "Process_1ojkac4";
		var event = zeebeClient.newCreateInstanceCommand()
				.bpmnProcessId(bpmnProcessId)
				.latestVersion()
				.variables(Map.of("total", 100))
				.send()
				.join();
		LOG.info("started a process instance: {}", event.getProcessInstanceKey());
		 */
	}
}
