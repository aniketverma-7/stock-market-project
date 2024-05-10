package com.project.stock.controller.chart;

import com.project.stock.service.impl.ChartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/user/stock/chart")
public class ChartController {

    @Autowired
    private ChartServiceImpl chartService;
    @PostMapping()
    public ResponseEntity<?> getChart(@RequestBody Map<String,String> symbol) throws IOException {
        return ResponseEntity.ok(chartService.getChart(symbol.get("symbol")));
    }

}
