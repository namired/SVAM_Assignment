package com.mastercard.service;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class CityConnectionServiceTest {
    @InjectMocks
    private CityConnectionService service = new CityConnectionServiceImpl();

    @DataProvider(name = "citiesData")
    public Object[][] dataProvider() {
        return new Object[][] {
                { "Boston", "Newark", true },
                { "Boston", "Philadelphia", true },
                { "Philadelphia", "Boston", true },
                { "Philadelphia", "Albany", false },
                { "New York", "Newark", true },
                { "Albany", "Newark", false }
        };
    }

    @Test(dataProvider = "citiesData")
    public void givenOriginAndDestination_whenIsConnected_thenExpectedTrue(String source, String destination, boolean expected) throws IOException, URISyntaxException {
        Assert.assertEquals(service.isConnected(source, destination), expected);
    }
    @Test(expectedExceptions = {IOException.class})
    public void givenOriginAndDestination_whenIsConnected_thenExpectedException() throws IOException, URISyntaxException {
        //Given
        String source = "";
        String destination = "";
        CityConnectionService spyService = Mockito.spy(service);
        Mockito.doThrow(new IOException()).when(spyService).getLinesStream();
        //When
        spyService.isConnected(source, destination);

        //Then Exception

    }
}
