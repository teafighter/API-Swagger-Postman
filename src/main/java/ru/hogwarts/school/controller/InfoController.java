package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
public class InfoController {
    @Value("${server.port}")
    private Integer port;

    @GetMapping("/port") // GET - read
    public Integer getPort() {
        return port;
    }

    @GetMapping("/sum")
    public long calculateSum() {

        // slow way
        long startTime = System.currentTimeMillis();
        long sum1 = Stream.iterate(1, a -> a + 1)
                .limit(10_000_000)
                .reduce(0, (a, b) -> a + b);
        long finishTime = System.currentTimeMillis();
        System.out.println(sum1);
        System.out.println("1st method takes " + (finishTime - startTime) + " milliseconds");

        // fast way
        startTime = System.currentTimeMillis();
        long sum2 = Stream.iterate(1, a -> a + 1)
                .limit(10_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        finishTime = System.currentTimeMillis();
        System.out.println(sum2);
        System.out.println("2nd method takes " + (finishTime - startTime) + " milliseconds");

        return sum2;
    }
}