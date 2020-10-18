package org.openapitools.codegen;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.ParseOptions;
import org.openapitools.codegen.languages.*;
import org.testng.annotations.Test;

import static org.openapitools.codegen.languages.SpringCodegen.*;

public class Pony {

    private static final String GENERATION_ROOT = "/home/zomzog/git/zomzog/openapi-generator/test/";
    private static final String CONTRACT = "src/test/resources/api.yml";

    @Test
    public void SpringCodegen() {
        AbstractJavaCodegen config = new SpringCodegen();
        //AbstractJavaCodegen config = new SpringCodegen();
        //config.setArtifactId("artifactId");
        //config.setJava8Mode(true);typeInfoAnnotation.mustache
        //
        // config.setHideGenerationTimestamp(true);
        config.setOutputDir(GENERATION_ROOT + "spring");
        config.setSourceFolder("/src/main/java");
//        config.additionalProperties.put("async", true);
        //      config.additionalProperties.put("library", "spring-cloud");
        config.additionalProperties.put("library", "spring-mvc");
        config.additionalProperties.put(USE_TAGS, true);
//        config.additionalProperties.put("reactive", true);
//        config.additionalProperties.put("interfaceOnly", true);
        //config.additionalProperties.put("java8", true);
        //config.additionalProperties.put("responseWrapper", "RxSingle");

        //GeneratorProperties.setProperty("models", "");

        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation(CONTRACT, null, options)
                        .getOpenAPI();

//        openAPI.getComponents().getParameters().get("operationsQueryParam").setSchema(new StringSchema()._default("default"));
        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }

    @Test
    public void SpringBootCodegen() {
        AbstractJavaCodegen config = new SpringCodegen();
        config.setOutputDir(GENERATION_ROOT + "springboot");
        config.setSourceFolder("/src/main/java");
        config.setLibrary("spring-boot");
//        config.setLibrary("spring-mvc");
        config.additionalProperties.put(USE_TAGS, true);
        config.additionalProperties.put(REACTIVE, false);
        config.additionalProperties.put(INTERFACE_ONLY, true);
        config.additionalProperties.put(USE_OPTIONAL, true);
        config.additionalProperties.put(USE_BEANVALIDATION, true);
        config.additionalProperties.put(SKIP_DEFAULT_INTERFACE, true);
        config.additionalProperties.put(HATEOAS, false);

        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation(CONTRACT, null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }

    @Test
    public void KotlinServerCodegen() {
        KotlinServerCodegen config = new KotlinServerCodegen();

        config.setOutputDir(GENERATION_ROOT + "kotlin-server");
        config.setSourceFolder("/src/main/kotlin");
        config.additionalProperties.put("interfaceOnly", true);

        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation(CONTRACT, null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }


    @Test
    public void KotlinSpringServerCodegen() {
        KotlinSpringServerCodegen config = new KotlinSpringServerCodegen();

        config.setOutputDir(GENERATION_ROOT + "kotlin-spring");
        config.setSourceFolder("/src/main/kotlin");
        config.additionalProperties.put("reactive", true);

        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation(CONTRACT, null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }


    @Test
    public void KotlinClientCodegen() {
        KotlinClientCodegen config = new KotlinClientCodegen();

        config.setOutputDir(GENERATION_ROOT + "kotlin-client");
        config.setSourceFolder("/src/main/kotlin");

//        config.additionalProperties.put(KotlinClientCodegen.MODEL_MUTABLE, false);
//        config.additionalProperties.put(KotlinClientCodegen.COLLECTION_TYPE, "spring-list");

        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation(CONTRACT, null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }

    @Test
    public void KotlinVertxServerCodegen() {
        KotlinVertxServerCodegen config = new KotlinVertxServerCodegen();

        config.setOutputDir(GENERATION_ROOT + "kotlin-vertx");
        config.setSourceFolder("/src/main/kotlin");

//        config.additionalProperties.put(KotlinClientCodegen.MODEL_MUTABLE, false);
//        config.additionalProperties.put(KotlinClientCodegen.COLLECTION_TYPE, "spring-list");

        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation(CONTRACT, null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }

    @Test
    public void javaClientCodegen() {
        JavaClientCodegen config = new JavaClientCodegen();

        config.setOutputDir(GENERATION_ROOT + "java-client");
        config.setSourceFolder("/src/main/java");
        config.setLibrary("jersey2");


        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation(CONTRACT, null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }

    @Test
    public void javaServerCodegen() {
        JavaJerseyServerCodegen config = new JavaJerseyServerCodegen();

        config.setOutputDir(GENERATION_ROOT + "java-server");
        config.setSourceFolder("/src/main/java");
        config.setModelNamePrefix("pony");
        config.setLibrary("jersey1");


        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation(CONTRACT, null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }

    @Test
    public void testCodegen() {
        DefaultCodegen config = new OCamlClientCodegen();

        config.setOutputDir(GENERATION_ROOT + "tmpTest");

        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation("src/test/resources/test.yml", null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }

}
