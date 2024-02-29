package dan.ms.tp.msusuarios.rest.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/health")
public class HealthController {

  @GetMapping
  public ResponseEntity<Map<String, String>> health(HttpServletRequest request) {
    Map<String, String> res = new LinkedHashMap<>();
    res.put("serverName", request.getServerName());
    res.put("app", "ms-usuarios");
    res.put("status", "OK");
    res.put("timestamp", Instant.now().toString());
    try {
      InetAddress localhost = InetAddress.getLocalHost();
      res.put("serverIp", localhost.getHostAddress());
    } catch (UnknownHostException e) {
      res.put("serverIp", "unknown");
      res.put("status", "ERROR");
      res.put("error", e.getMessage());
      return ResponseEntity.status(500).body(res);
    }
    return ResponseEntity.ok().body(res);
  }
}
