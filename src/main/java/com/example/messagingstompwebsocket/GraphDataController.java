package com.example.messagingstompwebsocket;

import com.example.messagingstompwebsocket.components.GraphData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalTime;
import java.util.HashMap;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class GraphDataController {

  private final SimpMessagingTemplate simpMessagingTemplate;
  private final GraphData graphData;

  public GraphDataController(
      final SimpMessagingTemplate simpMessagingTemplate, final GraphData graphData) {

    this.graphData = graphData;
    this.simpMessagingTemplate = simpMessagingTemplate;
  }

  //    @Async
  @Scheduled(fixedRate = 1000)
  public void sendPeriodicMessages() throws JsonProcessingException {
    log.info("server periodic message %s via the broker {}", LocalTime.now());
    try {
      Object data = new ObjectMapper().writeValueAsString(this.graphData.getHeadObject());

      HashMap<String, Object> header = new HashMap<>();
      header.put("Content-Type", "application/json");
      log.info("sending data : {}", data);

      simpMessagingTemplate.convertAndSend("/topic/graph_data", data, header);
    } catch (Exception exception) {
      log.error(exception.getMessage());
    }
  }
}
