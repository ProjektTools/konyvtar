
import javax.servlet.ServletContext;
import org.ocpsoft.logging.Logger.Level;
import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.config.Log;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;

@RewriteConfiguration
public class ApplicationConfigurationProvider extends HttpConfigurationProvider {

    @Override
    public Configuration getConfiguration(ServletContext context) {
        return ConfigurationBuilder.begin()
                //
                .addRule()
                .when(Path.matches("category/{id}"))
                    .perform(Forward.to("/category.jsp"))
                .addRule()
                .when(Path.matches("/books/{id}"))
                 .perform(Forward.to("/book.jsp"))                 
                .addRule()
                .when(Path.matches("/index"))
                .perform(Forward.to("/index.jsp"))                
                .addRule()
                .when(Path.matches("/info"))
                .perform(Forward.to("/info.jsp"))
                .addRule()
                .when(Path.matches("/books"))
                .perform(Forward.to("/books.jsp"))
                .addRule()
                .when(Path.matches("/borrows"))
                .perform(Forward.to("/borrows.jsp"))
                .addRule()
                .when(Path.matches("/newbook"))
                .perform(Forward.to("/newbook.jsp"))
                .addRule()
                .when(Path.matches("/regist"))
                .perform(Forward.to("/regist.jsp"))
                .addRule()
                .when(Path.matches("/login"))
                .perform(Forward.to("/login.jsp")); 
    }

    @Override
    public int priority() {
        return 0;
    }
}
