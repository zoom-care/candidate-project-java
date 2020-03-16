package com.zoomcare.candidatechallenge.employee.dataaccess;

import org.springframework.context.annotation.Configuration;

/**
 * This configuration class configures the persistence layer of our example application and
 * enables annotation driven transaction management.
 *
 * This configuration is put to a single class because this way we can write integration
 * tests for our persistence layer by using the configuration used by our example
 * application. In other words, we can ensure that the persistence layer of our application
 * works as expected.
 *
 * @author Petri Kainulainen
 */
@Configuration
class PersistenceContext {


}