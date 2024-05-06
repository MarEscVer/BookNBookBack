package org.reader.low.booknbook.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.reader.low.booknbook.constants.ApiConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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

    return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
            .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
            .info(generateInfo(generateContact(), generateLicense()))
            .tags(generateTags())
            .paths(new Paths())
            .servers(List.of(devServer, prodServer));
  }

  private List<Tag> generateTags(){
    List<Tag> tags = new ArrayList<>();

    tags.add(new Tag().name(ApiConstants.TAG_PUBLICO).description("Métodos que se usan sin token, ya que son necesarios para un uso mínimo").externalDocs(new ExternalDocumentation(
    ).description("Documentacion Dominio publico").url("https://example.com")));
    tags.add(new Tag().name(ApiConstants.TAG_AUTOR).description("Endpoints que tratan al autor de los libros").externalDocs(new ExternalDocumentation(
    ).description("Documentacion Autor").url("https://example.com")));
    tags.add(new Tag().name(ApiConstants.TAG_LIBRO).description("Endpoints que tratan los libros").externalDocs(new ExternalDocumentation(
    ).description("Documentacion Libro").url("https://example.com")));
    tags.add(new Tag().name(ApiConstants.TAG_GRUPO).description("Endpoints que tratan los grupos").externalDocs(new ExternalDocumentation(
    ).description("Documentacion Grupo").url("https://example.com")));
    tags.add(new Tag().name(ApiConstants.TAG_COMBO).description("Endpoints que devuelven combos para formularios").externalDocs(new ExternalDocumentation(
    ).description("Documentacion Combo").url("https://example.com")));
    tags.add(new Tag().name(ApiConstants.TAG_ADMIN).description("Endpoints que solo usan los admin").externalDocs(new ExternalDocumentation(
    ).description("Documentacion Admin").url("https://example.com")));
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

  private SecurityScheme createAPIKeyScheme() {
      return new SecurityScheme().type(SecurityScheme.Type.HTTP)
              .bearerFormat("JWT").scheme("bearer");
  }
}
