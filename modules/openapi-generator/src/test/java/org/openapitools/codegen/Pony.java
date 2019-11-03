package org.openapitools.codegen;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.ParseOptions;
import org.openapitools.codegen.languages.AbstractJavaCodegen;
import org.openapitools.codegen.languages.JavaClientCodegen;
import org.openapitools.codegen.languages.JavaJerseyServerCodegen;
import org.openapitools.codegen.languages.KotlinClientCodegen;
import org.openapitools.codegen.languages.KotlinServerCodegen;
import org.openapitools.codegen.languages.KotlinSpringServerCodegen;
import org.openapitools.codegen.languages.KotlinVertxServerCodegen;
import org.openapitools.codegen.languages.SpringCodegen;
import org.testng.annotations.Test;

import static org.openapitools.codegen.languages.SpringCodegen.USE_TAGS;

public class Pony {

    @Test
    public void SpringCodegen() {
        AbstractJavaCodegen config = new SpringCodegen();
        //AbstractJavaCodegen config = new SpringCodegen();
        //config.setArtifactId("artifactId");
        //config.setJava8Mode(true);typeInfoAnnotation.mustache
        //
        // config.setHideGenerationTimestamp(true);
        config.setOutputDir("/tmp/pony");
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
                openApiParser.readLocation("src/test/resources/api.yml", null, options)
                        .getOpenAPI();

//        openAPI.getComponents().getParameters().get("operationsQueryParam").setSchema(new StringSchema()._default("default"));
        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }


    @Test
    public void KotlinServerCodegen() {
        KotlinServerCodegen config = new KotlinServerCodegen();

        config.setOutputDir("/tmp/pony");
        config.setSourceFolder("/src/main/kotlin");
        config.additionalProperties.put("interfaceOnly", true);

        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation("src/test/resources/api.yml", null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }


    @Test
    public void KotlinSpringServerCodegen() {
        KotlinSpringServerCodegen config = new KotlinSpringServerCodegen();

        config.setOutputDir("/tmp/pony");
        config.setSourceFolder("/src/main/kotlin");
        config.additionalProperties.put("reactive", true);

        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation("src/test/resources/api.yml", null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }


    @Test
    public void KotlinClientCodegen() {
        KotlinClientCodegen config = new KotlinClientCodegen();

        config.setOutputDir("/tmp/pony");
        config.setSourceFolder("/src/main/kotlin");

        config.additionalProperties.put(KotlinClientCodegen.MODEL_MUTABLE, false);
//        config.additionalProperties.put(KotlinClientCodegen.COLLECTION_TYPE, "spring-list");

        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation("src/test/resources/api.yml", null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }

    @Test
    public void KotlinVertxServerCodegen() {
        KotlinVertxServerCodegen config = new KotlinVertxServerCodegen();

        config.setOutputDir("/tmp/pony");
        config.setSourceFolder("/src/main/kotlin");

        config.additionalProperties.put(KotlinClientCodegen.MODEL_MUTABLE, false);
//        config.additionalProperties.put(KotlinClientCodegen.COLLECTION_TYPE, "spring-list");

        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation("src/test/resources/api.yml", null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }

    @Test
    public void javaClientCodegen() {
        JavaClientCodegen config = new JavaClientCodegen();

        config.setOutputDir("/tmp/pony");
        config.setSourceFolder("/src/main/java");
        //config.setLibrary("feign");


        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation("src/test/resources/api.yml", null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }

    @Test
    public void javaServerCodegen() {
        JavaJerseyServerCodegen config = new JavaJerseyServerCodegen();

        config.setOutputDir("/tmp/pony");
        config.setSourceFolder("/src/main/java");
        config.setModelNamePrefix("pony");
        config.setLibrary("jersey1");


        final OpenAPIParser openApiParser = new OpenAPIParser();
        final ParseOptions options = new ParseOptions();
        final OpenAPI openAPI =
                openApiParser.readLocation("src/test/resources/api.yml", null, options)
                        .getOpenAPI();

        final ClientOptInput opts = new ClientOptInput();
        opts.config(config);
        opts.openAPI(openAPI);
        new DefaultGenerator().opts(opts).generate();
    }


}
