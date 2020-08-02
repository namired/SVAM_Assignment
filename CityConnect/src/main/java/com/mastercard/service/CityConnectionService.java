package com.mastercard.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

public interface CityConnectionService {
    boolean isConnected(String org, String dest) throws IOException, URISyntaxException;
    Stream<String> getLinesStream() throws URISyntaxException, IOException;
}
