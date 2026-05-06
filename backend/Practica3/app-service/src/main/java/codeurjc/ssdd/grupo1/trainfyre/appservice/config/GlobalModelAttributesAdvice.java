package codeurjc.ssdd.grupo1.trainfyre.appservice.config;

import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.Role;
import codeurjc.ssdd.grupo1.trainfyre.appservice.dto.UsersDTOs.UserInfoDTO;
import codeurjc.ssdd.grupo1.trainfyre.appservice.service.UserService;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;


@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributesAdvice {

    private final Mustache.Compiler mustacheCompiler;
    private final UserService userService;
    private final DefaultImages defaultImages;

    @ModelAttribute
    public void globalAttributes(Model model, @AuthenticationPrincipal UserDetails user) {
        model.addAttribute("globalStyles", Arrays.asList(
                "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css",
                "/css/style.css"));
        model.addAttribute("globalScripts", Arrays.asList(
                "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"));

        UserInfoDTO userInfo = userService.findUser(user);
        if(userInfo != null) {
            model.addAttribute("username", userInfo.username());
            model.addAttribute("email", userInfo.email());
            model.addAttribute("role", userInfo.role());
            if (userInfo.profileImageId() != null) {
                model.addAttribute("profile_picture", "/api/v1/images/" + userInfo.profileImageId());
            } else {
                model.addAttribute("profile_picture", "/api/v1/images/" + defaultImages.getDefaultProfileImageId());
            }
            model.addAttribute("alerts", Arrays.asList(userInfo.alerts()));
            if(userInfo.role() == Role.ADMIN) {
                model.addAttribute("admin", true);
            }
        }

        model.addAttribute("logo", "/api/v1/images/" + defaultImages.getDefaultLogoImageId());
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
