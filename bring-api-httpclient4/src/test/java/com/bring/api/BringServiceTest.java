package com.bring.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bring.api.connection.HttpClient4Adapter;

public class BringServiceTest {

    @Test
    public void shouldUseHttpClient4WhenAvailable() {
        BringService bringService = new BringService("id");
        assertEquals(HttpClient4Adapter.class, bringService.detectAndLoadConnectionAdapter("id").getClass());
    }
}