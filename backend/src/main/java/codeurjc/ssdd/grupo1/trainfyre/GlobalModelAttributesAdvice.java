package codeurjc.ssdd.grupo1.trainfyre;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;


@ControllerAdvice
public class GlobalModelAttributesAdvice {

    private final Mustache.Compiler mustacheCompiler;

    public GlobalModelAttributesAdvice(Mustache.Compiler mustacheCompiler) {
        this.mustacheCompiler = mustacheCompiler;
    }

    @ModelAttribute
    public void globalAttributes(Model model) {
        model.addAttribute("globalStyles", Arrays.asList(
                "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css",
                "/css/style.css"));
        model.addAttribute("globalScripts", Arrays.asList(
                "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"));
    }

    @ModelAttribute("Layout")
    public Mustache.Lambda layout() {
        return new Layout(mustacheCompiler);
    }

    private record Layout(Mustache.Compiler compiler) implements Mustache.Lambda {

        @Override
            public void execute(Template.Fragment fragment, Writer writer) throws IOException {

                compiler.compile("{{> partials/_header}}")
                        .execute(fragment.context(), writer);

                fragment.execute(writer);

                compiler.compile("{{> partials/_footer}}")
                        .execute(fragment.context(), writer);
            }
        }
}
