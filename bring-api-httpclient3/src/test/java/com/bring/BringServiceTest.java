package com.bring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bring.connection.HttpClient3Adapter;

public class BringServiceTest {

    @Test
    public void shouldUseHttpClient3WhenAvailable() {
        BringService bringService = new BringService("id");
        assertEquals(HttpClient3Adapter.class, bringService.detectAndLoadConnectionAdapter("id").getClass());
    }
}