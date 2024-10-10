package io.github.t0m0h1de.togglehealth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Run integration tests with Arquillian to be able to test CDI beans
 */
@ExtendWith(ArquillianExtension.class)
public class  ToggleHealthAppServiceIT {

    @Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "ToggleHealthAppServiceIT.war")
                .addClass(ToggleHealthAppService.class);
    }

    @Test
    public void testService() {
        ToggleHealthAppService.applyMessage("healthy");
        assertEquals(true, ToggleHealthAppService.isHealthy);

        ToggleHealthAppService.applyMessage("confuse");
        assertEquals(false, ToggleHealthAppService.isHealthy);
    }
}