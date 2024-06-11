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

/**
 * The type Open api config.
 */
@Configuration
public class OpenAPIConfig {

  /**
   * The Dev url.
   */
  @Value("${booknbook.openapi.dev-url}")
  private String devUrl;

  /**
   * The Prod url.
   */
  @Value("${booknbook.openapi.prod-url}")
  private String prodUrl;

  /**
   * My open api open api.
   *
   * @return the open api
   */
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

  /**
   * Generate tags list.
   *
   * @return the list
   */
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
    tags.add(new Tag().name(ApiConstants.TAG_USUARIO).description("Endpoints que tratan los usuarios").externalDocs(new ExternalDocumentation(
    ).description("Documentacion Usuario").url("https://example.com")));
    tags.add(new Tag().name(ApiConstants.TAG_ADMIN).description("Endpoints que solo usan los admin").externalDocs(new ExternalDocumentation(
    ).description("Documentacion Admin").url("https://example.com")));
    tags.add(new Tag().name(ApiConstants.TAG_DENUNCIA).description("Endpoints que tratan las denuncias").externalDocs(new ExternalDocumentation(
    ).description("Documentacion Denuncia").url("https://example.com")));
    return tags;
  }

  /**
   * Generate info info.
   *
   * @param contact the contact
   * @param license the license
   * @return the info
   */
  private Info generateInfo(Contact contact, License license){
    return new Info()
            .title("Book n' Book Management API")
            .version("1.0")
            .contact(contact)
            .description("This API exposes endpoints to manage the app BooknBook.").termsOfService("https://www.example.com")
            .license(license);
  }

  /**
   * Generate contact contact.
   *
   * @return the contact
   */
  private Contact generateContact(){
    Contact contact = new Contact();
    contact.setEmail("Maria");
    contact.setName("Maria EV");
    contact.setUrl("MarEscVer");
    return contact;
  }

  /**
   * Generate license license.
   *
   * @return the license
   */
  private License generateLicense(){
    return new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
  }

  /**
   * Create api key scheme security scheme.
   *
   * @return the security scheme
   */
  private SecurityScheme createAPIKeyScheme() {
      return new SecurityScheme().type(SecurityScheme.Type.HTTP)
              .bearerFormat("JWT").scheme("bearer");
  }
}
