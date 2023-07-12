package com.example.StockOrderSystem.order;

import com.example.StockOrderSystem.publisher.KoreaStockPublisher;
import com.example.StockOrderSystem.publisher.USAStockPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private KoreaStockPublisher koreaStockPublisher;
    private USAStockPublisher usaStockPublisher;

    @Autowired
    public OrderController(KoreaStockPublisher koreaStockPublisher,
            USAStockPublisher usaStockPublisher) {
        this.koreaStockPublisher = koreaStockPublisher;
        this.usaStockPublisher = usaStockPublisher;
    }

    @PostMapping("/korea-stock")
    public ResponseEntity<String> sendJsonMessageToKoreaStock(@RequestBody Order order) {
        koreaStockPublisher.sendJsonMessage(order);
        return ResponseEntity.ok("Json message sent to Korea Stock Queue ...");
    }

    @PostMapping("/usa-stock")
    public ResponseEntity<String> sendJsonMessageToUSAStock(@RequestBody Order order) {
        usaStockPublisher.sendJsonMessage(order);
        return ResponseEntity.ok("Json message sent to USA Stock Queue");
    }

}
