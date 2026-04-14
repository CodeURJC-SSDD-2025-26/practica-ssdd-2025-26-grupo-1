package codeurjc.ssdd.grupo1.trainfyre;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 🔥 SMOKE TEST - Verifica que el contexto de Spring se carga correctamente
 * 
 * Este test es ligero y rápido, ideal para validaciones de configuración
 * sin hacer consultas a base de datos pesadas.
 * 
 * @author DevOps Team
 * @version 1.0
 */
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("🔥 TrainFyre Smoke Tests")
class SmokeTest {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * ✅ Verifica que el contexto de Spring Boot se carga sin errores
     */
    @Test
    @DisplayName("✅ El contexto de Spring debe cargarse correctamente")
    void contextShouldLoad() {
        assertThat(applicationContext)
            .as("ApplicationContext no debe ser nulo")
            .isNotNull();
    }

    /**
     * ✅ Verifica que la aplicación está disponible
     */
    @Test
    @DisplayName("✅ La aplicación debe estar disponible")
    void applicationShouldBeAvailable() {
        // Simplemente ejecutar la prueba sin excepciones es suficiente
        System.out.println("🚀 TrainFyre Backend is running!");
    }

    /**
     * ✅ Verifica que el bean de la aplicación principal existe
     */
    @Test
    @DisplayName("✅ El bean TrainFyreApplication debe existir")
    void mainBeanShouldExist() {
        boolean beanExists = applicationContext.containsBean("trainFyreApplication") 
                          || applicationContext.getBeansOfType(TrainFyreApplication.class).size() > 0;
        
        assertThat(beanExists)
            .as("TrainFyreApplication bean debe estar registrado")
            .isTrue();
    }

    /**
     * ✅ Verifica componentes web básicos
     */
    @Test
    @DisplayName("✅ Los componentes Spring Web deben estar configurados")
    void springWebComponentsShouldBeAvailable() {
        assertThat(applicationContext.getBeansOfType(
            org.springframework.web.servlet.DispatcherServlet.class
        ).size())
            .as("DispatcherServlet debe estar registrado")
            .isGreaterThan(0);
    }
}