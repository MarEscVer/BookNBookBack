package org.reader.low.booknbook.config;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.models.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpenAPIConfig {

  @Value("${booknbook.openapi.dev-url}")
  private String devUrl;

  @Value("${booknbook.openapi.prod-url}")
  private String prodUrl;

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Server URL in Production environment");

    return new OpenAPI().info(generateInfo(generateContact(), generateLicense()))
            .tags(generateTags())
            .paths(new Paths())
            .servers(List.of(devServer, prodServer));
  }

  private List<Tag> generateTags(){
    List<Tag> tags = new ArrayList<>();
    tags.add(new Tag().name("Tutorial1 management").description("Tutorial1 management APIs"));
    tags.add(new Tag().name("Tutorial2").description("Tutorial2 management APIs"));
    tags.add(new Tag().name("Tutorial3").description("Tutorial3 management APIs"));
    return tags;
  }

  private Info generateInfo(Contact contact, License license){
    return new Info()
            .title("Book n' Book Management API")
            .version("1.0")
            .contact(contact)
            .description("This API exposes endpoints to manage the app BooknBook.").termsOfService("https://www.example.com")
            .license(license);
  }

  private Contact generateContact(){
    Contact contact = new Contact();
    contact.setEmail("Maria");
    contact.setName("Maria EV");
    contact.setUrl("MarEscVer");
    return contact;
  }

  private License generateLicense(){
    return new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
  }
}
