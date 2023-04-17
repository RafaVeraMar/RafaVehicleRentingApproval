package com.babel.vehiclerentingapproval;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContextManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class VehicleRentingApprovalApplicationTests {

    private ApplicationContext applicationContext;
    private TestContextManager testContextManager;

    @BeforeEach
    void setUp ( ) throws Exception {
        testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
        applicationContext = testContextManager.getTestContext().getApplicationContext();
    }

    @Test
    void contextLoads ( ) {
        assertNotNull(applicationContext, "The application context should not be null");
    }


}
