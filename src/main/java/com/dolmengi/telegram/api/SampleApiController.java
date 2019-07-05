package com.dolmengi.telegram.api;

import com.dolmengi.telegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleApiController {

  private final MessageService messageService;

  @GetMapping(value = {"/", ""})
  public boolean sendMessage(
      @RequestParam(value = "message", required = false, defaultValue = "Sample Message") String message
      ) {

    return messageService.sendMessage(message);
  }

}
