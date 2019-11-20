/*
 * OpenAPI Petstore
 * This spec is mainly for testing Petstore server and contains fake endpoints, models. Please do not use this for any other purpose. Special characters: \" \\
 *
 * OpenAPI spec version: 1.0.0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.Pet;
import org.openapitools.client.auth.*;
import org.openapitools.client.model.*;
import org.openapitools.client.*;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * API tests for PetApi
 */
public class PetApiTest {

    private PetApi api = new PetApi();

    @Before
    public void setup() {
        // setup authentication
        ApiKeyAuth apiKeyAuth = (ApiKeyAuth) api.getApiClient().getAuthentication("api_key");
        apiKeyAuth.setApiKey("special-key");
    }

    @Test
    public void testApiClient() {
        // the default api client is used
        assertEquals(Configuration.getDefaultApiClient(), api.getApiClient());
        assertNotNull(api.getApiClient());
        assertEquals("http://petstore.swagger.io:80/v2", api.getApiClient().getBasePath());
        assertFalse(api.getApiClient().isDebugging());

        ApiClient oldClient = api.getApiClient();

        ApiClient newClient = new ApiClient();
        newClient.setVerifyingSsl(true);
        newClient.setBasePath("http://example.com");
        newClient.setDebugging(true);

        // set api client via constructor
        api = new PetApi(newClient);
        assertNotNull(api.getApiClient());
        assertEquals("http://example.com", api.getApiClient().getBasePath());
        assertTrue(api.getApiClient().isDebugging());

        // set api client via setter method
        api.setApiClient(oldClient);
        assertNotNull(api.getApiClient());
        assertEquals("http://petstore.swagger.io:80/v2", api.getApiClient().getBasePath());
        assertFalse(api.getApiClient().isDebugging());
    }

    @Test
    public void testCreateAndGetPet() throws Exception {
        Pet pet = createPet();
        api.addPet(pet);

        Pet fetched = api.getPetById(pet.getId());
        assertPetMatches(pet, fetched);
    }

    @Test
    public void testCreateAndGetPetWithHttpInfo() throws Exception {
        Pet pet = createPet();
        api.addPetWithHttpInfo(pet);

        ApiResponse<Pet> resp = api.getPetByIdWithHttpInfo(pet.getId());
        assertEquals(200, resp.getStatusCode());
        assertEquals("application/json", resp.getHeaders().get("Content-Type").get(0));
        Pet fetched = resp.getData();

        assertPetMatches(pet, fetched);
    }

    @Test
    public void testCreateAndGetPetAsync() throws Exception {
        Pet pet = createPet();
        api.addPet(pet);
        // to store returned Pet or error message/exception
        final Map<String, Object> result = new HashMap<String, Object>();

        api.getPetByIdAsync(pet.getId(), new ApiCallback<Pet>() {
            @Override
            public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                result.put("error", e.getMessage());
            }

            @Override
            public void onSuccess(Pet pet, int statusCode, Map<String, List<String>> responseHeaders) {
                result.put("pet", pet);
            }

            @Override
            public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
                //empty
            }

            @Override
            public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
                //empty
            }
        });
        // the API call should be executed asynchronously, so result should be empty at the moment
        assertTrue(result.isEmpty());

        // wait for the asynchronous call to finish (at most 10 seconds)
        final int maxTry = 10;
        int tryCount = 1;
        Pet fetched = null;
        do {
            if (tryCount > maxTry) fail("have not got result of getPetByIdAsync after 10 seconds");
            Thread.sleep(1000);
            tryCount += 1;
            if (result.get("error") != null) fail((String) result.get("error"));
            if (result.get("pet") != null) {
                fetched = (Pet) result.get("pet");
                break;
            }
        } while (result.isEmpty());
        assertPetMatches(pet, fetched);

        // test getting a nonexistent pet
        result.clear();
        api.getPetByIdAsync(-10000L, new ApiCallback<Pet>() {
            @Override
            public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                result.put("exception", e);
            }

            @Override
            public void onSuccess(Pet pet, int statusCode, Map<String, List<String>> responseHeaders) {
                result.put("pet", pet);
            }

            @Override
            public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
                //empty
            }

            @Override
            public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
                //empty
            }
        });

        // wait for the asynchronous call to finish (at most 10 seconds)
        tryCount = 1;
        ApiException exception = null;
        do {
            if (tryCount > maxTry) fail("have not got result of getPetByIdAsync after 10 seconds");
            Thread.sleep(1000);
            tryCount += 1;
            if (result.get("pet") != null) fail("expected an error");
            if (result.get("exception") != null) {
                exception = (ApiException) result.get("exception");
                break;
            }
        } while (result.isEmpty());
        assertNotNull(exception);
        assertEquals(404, exception.getCode());
        assertEquals("Not Found", exception.getMessage());
        assertEquals("application/json", exception.getResponseHeaders().get("Content-Type").get(0));
    }

    @Test
    public void testCreateAndGetMultiplePetsAsync() throws Exception {
        Pet pet1 = createPet();
        Pet pet2 = createPet();

        final CountDownLatch addLatch = new CountDownLatch(2);
        final TestApiCallback<Void> addCallback1 = new TestApiCallback<Void>(addLatch);
        final TestApiCallback<Void> addCallback2 = new TestApiCallback<Void>(addLatch);

        // Make 2 simultaneous calls
        api.addPetAsync(pet1, addCallback1);
        api.addPetAsync(pet2, addCallback2);

        // wait for both asynchronous calls to finish (at most 10 seconds)
        assertTrue(addLatch.await(10, TimeUnit.SECONDS));

        assertTrue(addCallback1.isDone());
        assertTrue(addCallback2.isDone());

        if (!addCallback1.isSuccess()) throw addCallback1.getException();
        if (!addCallback2.isSuccess()) throw addCallback2.getException();

        assertValidProgress(addCallback1.getUploadProgress());
        assertValidProgress(addCallback2.getUploadProgress());

        final CountDownLatch getLatch = new CountDownLatch(3);
        final TestApiCallback<Pet> getCallback1 = new TestApiCallback<Pet>(getLatch);
        final TestApiCallback<Pet> getCallback2 = new TestApiCallback<Pet>(getLatch);
        final TestApiCallback<Pet> getCallback3 = new TestApiCallback<Pet>(getLatch);

        api.getPetByIdAsync(pet1.getId(), getCallback1);
        api.getPetByIdAsync(pet2.getId(), getCallback2);
        // Get nonexistent pet
        api.getPetByIdAsync(-10000L, getCallback3);

        // wait for all asynchronous calls to finish (at most 10 seconds)
        assertTrue(getLatch.await(10, TimeUnit.SECONDS));

        assertTrue(getCallback1.isDone());
        assertTrue(getCallback2.isDone());
        assertTrue(getCallback3.isDone());

        if (!getCallback1.isSuccess()) throw getCallback1.getException();
        if (!getCallback2.isSuccess()) throw getCallback2.getException();

        assertPetMatches(pet1, getCallback1.getResult());
        assertPetMatches(pet2, getCallback2.getResult());

        assertValidProgress(getCallback1.getDownloadProgress());
        assertValidProgress(getCallback2.getDownloadProgress());

        // Last callback should fail with ApiException
        assertFalse(getCallback3.isSuccess());
        final ApiException exception = getCallback3.getException();
        assertNotNull(exception);
        assertEquals(404, exception.getCode());
    }


    @Test
    public void testUpdatePet() throws Exception {
        Pet pet = createPet();
        pet.setName("programmer");

        api.updatePet(pet);

        Pet fetched = api.getPetById(pet.getId());
        assertPetMatches(pet, fetched);
    }

    @Test
    public void testFindPetsByStatus() throws Exception {
        Pet pet = createPet();
        pet.setName("programmer");
        pet.setStatus(Pet.StatusEnum.PENDING);

        api.updatePet(pet);

        List<Pet> pets = api.findPetsByStatus(Arrays.asList("pending"));
        assertNotNull(pets);

        boolean found = false;
        for (Pet fetched : pets) {
            if (fetched.getId().equals(pet.getId())) {
                found = true;
                break;
            }
        }

        assertTrue(found);

        api.deletePet(pet.getId(), null);
    }

    @Test
    @Ignore
    public void testFindPetsByTags() throws Exception {
        Pet pet = createPet();
        pet.setName("monster");
        pet.setStatus(Pet.StatusEnum.AVAILABLE);

        List<Tag> tags = new ArrayList<Tag>();
        Tag tag1 = new Tag();
        tag1.setName("friendly");
        tags.add(tag1);
        pet.setTags(tags);

        api.updatePet(pet);

        List<Pet> pets = api.findPetsByTags(Arrays.asList("friendly"));
        assertNotNull(pets);

        boolean found = false;
        for (Pet fetched : pets) {
            if (fetched.getId().equals(pet.getId())) {
                found = true;
                break;
            }
        }
        assertTrue(found);

        api.deletePet(pet.getId(), null);
    }

    @Test
    public void testUpdatePetWithForm() throws Exception {
        Pet pet = createPet();
        pet.setName("frank");
        api.addPet(pet);

        Pet fetched = api.getPetById(pet.getId());

        api.updatePetWithForm(fetched.getId(), "furt", null);
        Pet updated = api.getPetById(fetched.getId());

        assertEquals(updated.getName(), "furt");
    }

    @Test
    public void testDeletePet() throws Exception {
        Pet pet = createPet();
        api.addPet(pet);

        Pet fetched = api.getPetById(pet.getId());
        api.deletePet(fetched.getId(), null);

        try {
            fetched = api.getPetById(fetched.getId());
            fail("expected an error");
        } catch (ApiException e) {
            assertEquals(404, e.getCode());
        }
    }

    @Test
    public void testUploadFile() throws Exception {
        Pet pet = createPet();
        api.addPet(pet);

        File file = new File("hello.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("Hello world!");
        writer.close();

        api.uploadFile(pet.getId(), "a test file", new File(file.getAbsolutePath()));
    }

    @Test
    public void testEqualsAndHashCode() {
        Pet pet1 = new Pet();
        Pet pet2 = new Pet();
        assertTrue(pet1.equals(pet2));
        assertTrue(pet2.equals(pet1));
        assertTrue(pet1.hashCode() == pet2.hashCode());
        assertTrue(pet1.equals(pet1));
        assertTrue(pet1.hashCode() == pet1.hashCode());

        pet2.setName("really-happy");
        pet2.setPhotoUrls(Arrays.asList("http://foo.bar.com/1", "http://foo.bar.com/2"));
        assertFalse(pet1.equals(pet2));
        assertFalse(pet2.equals(pet1));
        assertFalse(pet1.hashCode() == (pet2.hashCode()));
        assertTrue(pet2.equals(pet2));
        assertTrue(pet2.hashCode() == pet2.hashCode());

        pet1.setName("really-happy");
        pet1.setPhotoUrls(Arrays.asList("http://foo.bar.com/1", "http://foo.bar.com/2"));
        assertTrue(pet1.equals(pet2));
        assertTrue(pet2.equals(pet1));
        assertTrue(pet1.hashCode() == pet2.hashCode());
        assertTrue(pet1.equals(pet1));
        assertTrue(pet1.hashCode() == pet1.hashCode());
    }


    private Pet createPet() {
        Pet pet = new Pet();
        pet.setId(1234567L);
        pet.setName("gorilla");

        Category category = new Category();
        category.setName("really-happy");

        pet.setCategory(category);
        pet.setStatus(Pet.StatusEnum.AVAILABLE);
        List<String> photos = Arrays.asList("http://foo.bar.com/1", "http://foo.bar.com/2");
        pet.setPhotoUrls(photos);

        return pet;
    }

    private String serializeJson(Object o, ApiClient apiClient) {
        return apiClient.getJSON().serialize(o);
    }

    private <T> T deserializeJson(String json, Type type, ApiClient apiClient) {
        return (T) apiClient.getJSON().deserialize(json, type);
    }

    private void assertPetMatches(Pet expected, Pet actual) {
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertNotNull(actual.getCategory());
        assertEquals(expected.getCategory().getName(),
                     actual.getCategory().getName());
    }

    /**
     * Assert that the given upload/download progress list satisfies the
     * following constraints:
     *
     *     - List is not empty
     *     - Byte count should be nondecreasing
     *     - The last element, and only the last element, should have done=true
     */
    private void assertValidProgress(List<Progress> progressList) {
        assertFalse(progressList.isEmpty());

        Progress prev = null;
        int index = 0;
        for (Progress progress : progressList) {
            if (prev != null) {
                if (prev.done || prev.bytes > progress.bytes) {
                    fail("Progress list out of order at index " + index
                         + ": " + progressList);
                }
            }
            prev = progress;
            index += 1;
        }

        if (!prev.done) {
            fail("Last progress item should have done=true: " + progressList);
        }
    }

    private static class TestApiCallback<T> implements ApiCallback<T> {

        private final CountDownLatch latch;
        private final ConcurrentLinkedQueue<Progress> uploadProgress =
            new ConcurrentLinkedQueue<Progress>();
        private final ConcurrentLinkedQueue<Progress> downloadProgress =
            new ConcurrentLinkedQueue<Progress>();

        private boolean done;
        private boolean success;
        private ApiException exception;
        private T result;

        public TestApiCallback(CountDownLatch latch) {
            this.latch = latch;
            this.done = false;
        }

        @Override
        public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
            exception = e;
            this.done = true;
            this.success = false;
            latch.countDown();
        }

        @Override
        public void onSuccess(T result, int statusCode, Map<String, List<String>> responseHeaders) {
            this.result = result;
            this.done = true;
            this.success = true;
            latch.countDown();
        }

        @Override
        public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
            uploadProgress.add(new Progress(bytesWritten, contentLength, done));
        }

        @Override
        public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
            downloadProgress.add(new Progress(bytesRead, contentLength, done));
        }

        public boolean isDone() {
            return done;
        }

        public boolean isSuccess() {
            return success;
        }

        public ApiException getException() {
            return exception;
        }

        public T getResult() {
            return result;
        }

        public List<Progress> getUploadProgress() {
            return new ArrayList<Progress>(uploadProgress);
        }

        public List<Progress> getDownloadProgress() {
            return new ArrayList<Progress>(downloadProgress);
        }
    }

    private static class Progress {
        public final long bytes;
        public final long contentLength;
        public final boolean done;

        public Progress(long bytes, long contentLength, boolean done) {
            this.bytes = bytes;
            this.contentLength = contentLength;
            this.done = done;
        }

        @Override
        public String toString() {
            return "<Progress " + bytes + " " + contentLength + " " + done + ">";
        }
    }
}
