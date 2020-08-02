package com.mastercard.controller;

import com.mastercard.service.CityConnectionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CityConnectionController.class)
public class CityConnectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityConnectionService cityConnectionService;
    @Before
    public void setup() {
    }

    @Test
    public void givenSourceAndDestination_whenConnected_thenReturnTrue() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/connected").param("origin","").param("destination","").accept(
                MediaType.TEXT_HTML);
        Mockito.when(cityConnectionService.isConnected(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getContentAsString(), "Yes");
    }
    @Test(expected = Exception.class)
    public void givenSourceAndDestination_whenConnected_thenReturnException() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/connected").param("origin","").param("destination","").accept(
                MediaType.TEXT_HTML);
        Mockito.when(cityConnectionService.isConnected(Mockito.anyString(), Mockito.anyString())).thenThrow(new NullPointerException());
        mockMvc.perform(requestBuilder).andReturn();
    }
}