package com.mastercard.service;

import com.mastercard.util.Graph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CityConnectionServiceImpl implements CityConnectionService{

    public static final String CITIES_TXT = "cities.txt";
    public static final String REGEX = ",";
    private static final Logger logger = LogManager.getLogger(CityConnectionServiceImpl.class);

    @Override
    public boolean isConnected(String origin, String destination) throws IOException, URISyntaxException {
        boolean isConnected = false;
        try {
            Stream<String> lines = getLinesStream();
            Map<String, String> cityMap
                    =  lines.map(l->l.split(REGEX))
                    .collect(Collectors.toMap((l->StringUtils.trimWhitespace(l[0])), l->StringUtils.trimWhitespace(l[1])));
            Graph graph = buildAndGetGraph(cityMap);
            Optional<Set<String>> optionalTraversalSet = Graph.depthFirstTraversal(graph, origin);
            if(optionalTraversalSet.isPresent()) {
                logger.log(logger.getLevel(),
                        new StringBuffer()
                                .append("Cities Connected to ")
                                .append(origin).append(": ")
                                .append(optionalTraversalSet.get().toString()));
                isConnected = optionalTraversalSet.get().contains(destination);
            }
        } catch (Exception e) {
            logger.error("Exception occured in CityConnectionServiceImpl.isConnected" + e.getMessage());
            throw e;
        }
        return isConnected;
    }

    public Stream<String> getLinesStream() throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource(CITIES_TXT).toURI());
        return Files.lines(path);
    }

    private Graph buildAndGetGraph(Map<String, String> cityMap) {
        Graph graph = new Graph();
        for(String key: cityMap.keySet()) {
            graph.addVertex(key);
            graph.addVertex(cityMap.get(key));
            graph.addEdge(key, cityMap.get(key));
        }
        return graph;
    }
}
